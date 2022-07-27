package com.crazyloong.cat.rsNew.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.rishang.constant.PlaceOrderType;
import com.crazyloong.cat.rishang.dto.RiOrderReq;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderAddress;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderAddressService;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import com.crazyloong.cat.rsNew.constant.PhoneFlag;
import com.crazyloong.cat.rsNew.dto.*;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderPlaced;
import com.crazyloong.cat.rsNew.mybatis.service.RsnOrderCouponService;
import com.crazyloong.cat.rsNew.mybatis.service.RsnOrderPlacedService;
import com.crazyloong.cat.rsNew.service.RSNUseService;
import com.crazyloong.cat.rsNew.service.RSNewService;
import com.crazyloong.cat.util.CacheUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:22
 * @Description :中免日上服务类 使用类
 */
@Service
public class RSNUseServiceImpl  implements RSNUseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private RSNewService rsNewService;
    @Autowired
    private RiOrderPhoneService riOrderPhoneService;
    @Autowired
    private RiOrderAddressService addressService;
    @Autowired
    private RsnOrderCouponService rsnOrderCouponService;
    @Autowired
    private RsnOrderPlacedService rsnOrderPlacedService;

    /**
     * 功能描述： 领取优惠券
     * @Param:
     * @Return: void
     * @Author:
     * @Date: 2022/6/17 18:44
     * @Description:
     */
    public void drawCoupon(List<RiOrderPhone> phoneList,DrawCouponReq drawCouponReq) {
        // 获取优惠券数据
        String accessToken = cacheUtil.get("deviceToken",String.class);
        if (StringUtils.isEmpty(accessToken)) {
            rsNewService.deviceLogin();
        }

        /*HintRsp hintRsp = this.hint();
        String urlStr = hintRsp.getHintText().getLinkUrl();
        String jsonId = urlStr.split("=")[1];*/
        /*CouponsJson couponsJson = rsNewService.getContent("62c46b154dfd000001be200e");
        List<String> couponIdList = getCouponIdList(couponsJson);*/
        List<String> couponIdList = new ArrayList<>();
        if (drawCouponReq.getNewFlag()!= null && drawCouponReq.getNewFlag()) {
            couponIdList.add("894322948378624");
        } else {
            couponIdList.add("839722463596544");
        }
        for (RiOrderPhone phone: phoneList) {
            for (String couponId:couponIdList) {
                try {
                    RSNewUserInfo rsNewUserInfo = cacheUtil.getRSNewToken(phone.getPhone());
                    GetUserCouponReq getUserCouponReq = new GetUserCouponReq();
                    getUserCouponReq.setUserId(rsNewUserInfo.getUserId());
                    getUserCouponReq.setMemberCode(rsNewUserInfo.getMemberCode());
                    getUserCouponReq.setShowToast(true);
                    getUserCouponReq.setCouponId(couponId);
                    logger.info(phone.getPhone()+"开始领取");
                    try {
                        rsNewService.getUserCoupon(getUserCouponReq,rsNewUserInfo.getAccessToken());

                    } catch (Exception e) {
                        logger.info("领取失败:"+e.getMessage());
                    }
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (drawCouponReq.getNewFlag()!= null && drawCouponReq.getNewFlag()) {
                        phone.setIsNew(1);
                        riOrderPhoneService.updateById(phone);
                    }
                } catch (Exception e) {
                    logger.info("领取优惠券失败:"+e.getMessage());
                }
            }

        }

    }

    /**
     * 功能描述： 获取用户优惠券
     * @Param: [phone]
     * @Return: com.crazyloong.cat.rsNew.dto.MyCouponRsp
     * @Author:
     * @Date: 2022/6/18 12:22
     * @Description:
     */
    public MyCouponRsp getAllMyCoupon(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return new MyCouponRsp();
        } else if ("all".equals(phone)) {
            List<RiOrderPhone> phoneList = riOrderPhoneService.getPhonesByType(PhoneFlag.RS_NEW.code);
            MyCouponRsp allCoupon = new MyCouponRsp();
            for (RiOrderPhone riOrderPhone: phoneList) {
                try {
                    MyCouponRsp phoneCoupon = rsNewService.getMyCoupon(riOrderPhone.getPhone());
                    rsnOrderCouponService.deleteByPhone(riOrderPhone.getPhone());
                    List<MyCouponRsp.MyCouponListDTO> myCouponListDTO = phoneCoupon.getMyCouponList();
                    myCouponListDTO.forEach(myCoupon ->{
                        RsnOrderCoupon rsnOrderCoupon = new RsnOrderCoupon();
                        rsnOrderCoupon.setCodeid(myCoupon.getCouponID());
                        rsnOrderCoupon.setCodename(myCoupon.getCouponName());
                        rsnOrderCoupon.setCodePhone(riOrderPhone.getPhone());
                        rsnOrderCouponService.save(rsnOrderCoupon);
                    });
                    allCoupon.getMyCouponList().addAll(myCouponListDTO);
                    allCoupon.setAmount(allCoupon.getAmount()+phoneCoupon.getAmount());

                } catch (Exception e) {
                    logger.info("获取用户优惠券失败:"+e.getMessage());
                } finally {
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            return allCoupon;
        } else {
            return rsNewService.getMyCoupon(phone);
        }
    }

    /**
     * 功能描述：获取优惠券ID
     * @Param: [couponsJson]
     * @Return: java.util.List<java.lang.String>
     * @Author:
     * @Date: 2022/6/17 19:00
     * @Description:
     */
    private List<String> getCouponIdList(CouponsJson couponsJson) {
        List<CouponsJson.DataDTO.ElementsDTO> elements = couponsJson.getData().getElements();
        List<String> couponIdList = new ArrayList<>();
        try {
            for (CouponsJson.DataDTO.ElementsDTO elementsDTO: elements) {
                if (elementsDTO != null) {
                    String moduleType = elementsDTO.getModuleType();
                    if ("sr_cms_coupon".equals(moduleType)) {
                        couponIdList.add(elementsDTO.getContent().get(0).getList().get(0).getCouponId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return couponIdList;
    }

    /**
    * 功能描述： 下单
    * @Param: [riOrderReq]
    * @Return: void
    * @Author:
    * @Date: 2022/6/29 18:56
    * @Description:
    */
    @Async("taskExecutor")
    public void submitBatchOrder(RiOrderReq riOrderReq) {
        logger.info("中免日上开始下单");
        RiOrderAddress address = addressService.getById(riOrderReq.getAddressId());
        OrderDetailRsp orderDetailRsp = rsNewService.getOrderDetail(riOrderReq.getMyOrderId(),riOrderReq.getPhone());
        SubmitOrderRep submitOrderRep = addressResolution(address.getAddress());
        submitOrderRep.setName(address.getUserName());
        submitOrderRep.setPhone(address.getUserPhone());
        submitOrderRep.setOrderDetailRsp(orderDetailRsp);
        submitOrderRep.setCouponContant(riOrderReq.getPreferentialSum());
        int total = 0;
        int currentNum = 0;
        for (int i = 0; i < riOrderReq.getOrderNum(); i++) {
            long start = System.currentTimeMillis();
            List<RiOrderPhone> phonesByType = riOrderPhoneService.getPhonesUnPlacedToday(PhoneFlag.RS_NEW.code);
            if (!StringUtils.isEmpty(riOrderReq.getPreferentialSum())) {
                // 获取含有优惠券的手机号
                outLoop:
                for (RiOrderPhone orderPhone : phonesByType) {
                    List<RsnOrderCoupon> byPhone = rsnOrderCouponService.getByPhone(orderPhone.getPhone());
                    inLoop:
                    for (RsnOrderCoupon rsnOrderCoupon : byPhone) {
                        if (rsnOrderCoupon.getCodename().contains(riOrderReq.getPreferentialSum())) {
                            submitOrderRep.setOrderPhone(orderPhone.getPhone());
                            rsnOrderCouponService.updateUnused(rsnOrderCoupon.getCodeid());
                            break outLoop;
                        }
                    }
                }
            } else {
                // 如果 当前下订单的数量大于等于限制数 且 订单数量取余限制数等于0 则换账户
                currentNum++;
                submitOrderRep.setOrderPhone(phonesByType.get(currentNum/riOrderReq.getOrderNumLimit()).getPhone());
            }



            if (StringUtils.isEmpty(submitOrderRep.getOrderPhone())) {
                throw new RiBizExecption("无可用优惠券");
            }
            logger.info("开始下第"+i+"单，下单账号为"+submitOrderRep.getOrderPhone());
            RsnOrderPlaced rsnOrderPlaced = new RsnOrderPlaced();
            rsnOrderPlaced.setPlacedPhone(submitOrderRep.getOrderPhone());
            try {
                this.submitOrder(submitOrderRep);
                rsnOrderPlaced.setStatus(0);
                logger.info("开始下第"+i+"单，下单账号为"+submitOrderRep.getOrderPhone()+",下单成功");
                total++;
            } catch (Exception e) {
                e.printStackTrace();
                logger.info(submitOrderRep.getOrderPhone()+"下单失败！"+e.getMessage());
                rsnOrderPlaced.setStatus(1);
                logger.info("开始下第"+i+"单，下单账号为"+submitOrderRep.getOrderPhone()+",下单失败");
            }


            rsnOrderPlacedService.save(rsnOrderPlaced);
            // 保证每5秒下一单
            long useTime = (System.currentTimeMillis()-start);
            if (6000 - useTime > 0) {
                try {
                    Thread.sleep(6000 - useTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("下单完成，共下"+total+"单");
    }


    /**
     * 功能描述： 生成用户订单
     * @Param: []
     * @Return: void
     * @Author:
     * @Date: 2022/6/19 21:37
     * @Description:
     */
    @Transactional
    public void submitOrder(SubmitOrderRep submitOrderRep) {
        List<OrderDetailRsp.DetailListDTO.GoodsListDTO> goodsListDTO = submitOrderRep.getOrderDetailRsp().getDetailList().get(0).getGoodsList();
        RSNewUserInfo rsNewToken = cacheUtil.getRSNewToken(submitOrderRep.getOrderPhone());
        ConfirmOrderRep confirmOrderRep = new ConfirmOrderRep();
        confirmOrderRep.setMemberCode(rsNewToken.getMemberCode());
        for (OrderDetailRsp.DetailListDTO.GoodsListDTO goods: goodsListDTO) {
            if (!goods.getGiveaway()) {
                ConfirmOrderRep.MerchantListDTO.ItemListDTO listDTO = new ConfirmOrderRep.MerchantListDTO.ItemListDTO();
                listDTO.setGoodsID(goods.getGoodsId());
                listDTO.setLeFoxID(Integer.valueOf(goods.getLeFoxID()));
                listDTO.setQuantity(goods.getQuantity());
                confirmOrderRep.getMerchantList().get(0).getItemList().add(listDTO);
            }
        }
        logger.info(JSONObject.toJSONString(confirmOrderRep));
        ConfirmOrderRsp confirmOrderRsp = rsNewService.comfirmOrder(confirmOrderRep,submitOrderRep.getOrderPhone());
        if (!confirmOrderRsp.getResponseHead().getIsSuccess()) {
            logger.info("订单确认失败:"+confirmOrderRsp.getResponseHead().getResultMessage());
            throw new RiBizExecption(confirmOrderRsp.getResponseHead().getResultMessage());
        }
        // 下订单
        CreateOrderRep createOrderRep = new CreateOrderRep();
        createOrderRep.setMemberCode(rsNewToken.getMemberCode());
        // 更新收件地址获取地址ID
        UpdateAddressRep updateAddressRep = new UpdateAddressRep();
        updateAddressRep.setAddress(submitOrderRep.getAddress());
        updateAddressRep.setAreaName(submitOrderRep.getDistrict());
        updateAddressRep.setCityName(submitOrderRep.getCity());
        updateAddressRep.setIsDefault(true);
        updateAddressRep.setAid("");
        updateAddressRep.setIsLimitOrderConfirm(false);
        updateAddressRep.setLinkName(submitOrderRep.getName());
        updateAddressRep.setPhone(submitOrderRep.getPhone());
        updateAddressRep.setProvinceName(submitOrderRep.getProvince());
        updateAddressRep.setTimestamp(0);
        String aid = rsNewService.updateAddress(updateAddressRep,submitOrderRep.getOrderPhone());
        // 系统优惠
        if (confirmOrderRsp.getPlatFormSubsidy() != null ) {
            createOrderRep.setPlatFormSubsidy(getSubsidy(confirmOrderRsp.getPlatFormSubsidy()));
        }

        // 地址
        CreateOrderRep.RecipientDTO recipientDTO = new CreateOrderRep.RecipientDTO();
        BeanUtils.copyProperties(submitOrderRep,recipientDTO);
        recipientDTO.setFullAddress("");
        recipientDTO.setAddressId(aid);
        recipientDTO.setCountry("中国");
        createOrderRep.setRecipient(recipientDTO);
        // 用户电话
        createOrderRep.setUserMobile(submitOrderRep.getOrderPhone());
        // 优惠券
        if (confirmOrderRsp.getMerchantList().get(0).getMerchantCouponList() != null) {
            CreateOrderRep.MerchantListDTO.MerchantCouponListDTO coupon = getCoupoon(confirmOrderRsp.getMerchantList().get(0).getMerchantCouponList(),submitOrderRep.getCouponContant());
            if (coupon != null) {
                List<CreateOrderRep.MerchantListDTO.MerchantCouponListDTO> couponList = new ArrayList<>();
                couponList.add(coupon);
                createOrderRep.getMerchantList().get(0).setMerchantCouponList(couponList);
            }
        }
        // 商品信息
        List<CreateOrderRep.MerchantListDTO.ItemListDTO> itemListDTOList = new ArrayList<>();
        for (ConfirmOrderRsp.MerchantListDTO.ItemListDTO item: confirmOrderRsp.getMerchantList().get(0).getItemList()) {
            CreateOrderRep.MerchantListDTO.ItemListDTO itemListDTO = new CreateOrderRep.MerchantListDTO.ItemListDTO();
            BeanUtils.copyProperties(item,itemListDTO);
            CreateOrderRep.MerchantListDTO.ItemListDTO.PurchaseTypeDTO purchaseTypeDTO = new CreateOrderRep.MerchantListDTO.ItemListDTO.PurchaseTypeDTO();
            purchaseTypeDTO.setPurchaseModeType("1");
            purchaseTypeDTO.setPurchaseModeName("");
            itemListDTO.setPurchaseType(purchaseTypeDTO);
            itemListDTO.setGiftKeyID("");
            itemListDTOList.add(itemListDTO);
        }
        createOrderRep.getMerchantList().get(0).setItemList(itemListDTOList);

        if (confirmOrderRsp.getMerchantList().get(0).getMerchantGiftList() != null) {
            // 赠品
            for (ConfirmOrderRsp.MerchantListDTO.MerchantGiftListDTO gift: confirmOrderRsp.getMerchantList().get(0).getMerchantGiftList()) {
                Integer amout = gift.getAmount();
                if (amout != 0) {
                    for (int i = 0; i< amout; i ++) {

                        ConfirmOrderRsp.MerchantListDTO.MerchantGiftListDTO.GiftListDTO myGift = gift.getGiftList().get(i);
                        if (myGift.getGiftCount() != 0) {
                            CreateOrderRep.MerchantListDTO.ItemListDTO itemListDTO = new CreateOrderRep.MerchantListDTO.ItemListDTO();
                            itemListDTO.setGiftKeyID(myGift.getGiftKeyID());
                            itemListDTO.setGoodsID(myGift.getGiftID());
                            itemListDTO.setPrice(myGift.getGiftPrice());
                            itemListDTO.setIsGift(true);
                            itemListDTO.setQuantity(1);
                            CreateOrderRep.MerchantListDTO.ItemListDTO.PurchaseTypeDTO purchaseTypeDTO = new CreateOrderRep.MerchantListDTO.ItemListDTO.PurchaseTypeDTO();
                            purchaseTypeDTO.setPurchaseModeType("1");
                            purchaseTypeDTO.setPurchaseModeName("");
                            itemListDTO.setPurchaseType(purchaseTypeDTO);
                            itemListDTOList.add(itemListDTO);
                        }
                    }
                }
            }
        }

        // 订单信息
        CreateOrderRep.MerchantListDTO.OrderInfoDTO orderInfoTarget = new CreateOrderRep.MerchantListDTO.OrderInfoDTO();
        ConfirmOrderRsp.MerchantListDTO.OrderInfoDTO orderInfoSource = confirmOrderRsp.getMerchantList().get(0).getOrderInfo();
        BeanUtils.copyProperties(orderInfoSource,orderInfoTarget);
        createOrderRep.getMerchantList().get(0).setOrderInfo(orderInfoTarget);
        CreatOrderRsp order = rsNewService.createOrder(createOrderRep, submitOrderRep.getOrderPhone());
        if (!order.getResponseHead().getIsSuccess()) {
            logger.info("提交订单:"+order.getResponseHead().getResultMessage());
            throw new RiBizExecption(order.getResponseHead().getResultMessage());
        }

    }

    /**
    * 功能描述：添加购物车及地址
    * @Param: [riOrderReq]
    * @Return: void
    * @Author:
    * @Date: 2022/7/27 22:24
    * @Description:
    */
    public void addCarAndAddress(RiOrderReq riOrderReq) {
        RiOrderAddress address = addressService.getById(riOrderReq.getAddressId());
        // 循环添加购物车
        OrderDetailRsp orderDetailRsp = rsNewService.getOrderDetail(riOrderReq.getMyOrderId(),riOrderReq.getPhone());
        List<SearchGoodsRsp> searchGoodsRspList = new ArrayList<>();
        for (OrderDetailRsp.DetailListDTO.GoodsListDTO goodsListDTO : orderDetailRsp.getDetailList().get(0).getGoodsList()) {
            SearchGoodsRsp searchGoodsRsp = rsNewService.searchGoods(goodsListDTO.getGoodsId(),riOrderReq.getPhone());
            searchGoodsRsp.setAddCartNum(goodsListDTO.getQuantity());
            searchGoodsRspList.add(searchGoodsRsp);
        }
        List<RiOrderPhone> phonesByType = riOrderPhoneService.getPhonesUnPlacedToday(PhoneFlag.RS_NEW.code);
        for (RiOrderPhone riOrderPhone: phonesByType) {
            long start = System.currentTimeMillis();
            SubmitOrderRep submitOrderRep = addressResolution(address.getAddress());
            submitOrderRep.setName(address.getUserName());
            submitOrderRep.setPhone(address.getUserPhone());
            // 更新收件地址获取地址ID
            UpdateAddressRep updateAddressRep = new UpdateAddressRep();
            updateAddressRep.setAddress(submitOrderRep.getAddress());
            updateAddressRep.setAreaName(submitOrderRep.getDistrict());
            updateAddressRep.setCityName(submitOrderRep.getCity());
            updateAddressRep.setIsDefault(true);
            updateAddressRep.setAid("");
            updateAddressRep.setIsLimitOrderConfirm(false);
            updateAddressRep.setLinkName(submitOrderRep.getName());
            updateAddressRep.setPhone(submitOrderRep.getPhone());
            updateAddressRep.setProvinceName(submitOrderRep.getProvince());
            updateAddressRep.setTimestamp(0);
            rsNewService.updateAddress(updateAddressRep,riOrderPhone.getPhone());
            // 添加购物车
            for (SearchGoodsRsp searchGoods : searchGoodsRspList) {
                AddCartReq addCartReq = new AddCartReq();
                AddCartReq.ItemDTO itemDTO = new AddCartReq.ItemDTO();
                itemDTO.setGoodsID(searchGoods.getGoodsList().get(0).getGoodsID());
                itemDTO.setGoodsVersion(searchGoods.getGoodsList().get(0).getGoodsVersion());
                itemDTO.setPrice(searchGoods.getGoodsList().get(0).getPrice());
                itemDTO.setQuantity(searchGoods.getAddCartNum());
                itemDTO.setTermTermID(searchGoods.getTermTermID());
                addCartReq.setItem(itemDTO);
                rsNewService.addCart(addCartReq,riOrderPhone.getPhone());
            }
            // 保证每5秒下一单
            long useTime = (System.currentTimeMillis()-start);
            if (3000 - useTime > 0) {
                try {
                    Thread.sleep(3000 - useTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }















    private List<CreateOrderRep.PlatFormSubsidyDTO> getSubsidy(List<ConfirmOrderRsp.PlatFormSubsidyDTO> subsidyDTO) {
        List<CreateOrderRep.PlatFormSubsidyDTO> returnDto = new ArrayList<>();
        for (ConfirmOrderRsp.PlatFormSubsidyDTO checked: subsidyDTO) {
            if (checked.getAmount() > 0 ) {
                CreateOrderRep.PlatFormSubsidyDTO copyed = new CreateOrderRep.PlatFormSubsidyDTO();
                BeanUtils.copyProperties(checked,copyed);
                returnDto.add(copyed);
            }
        }
        return returnDto;
    }

    private CreateOrderRep.MerchantListDTO.MerchantCouponListDTO getCoupoon(List<ConfirmOrderRsp.MerchantListDTO.MerchantCouponListDTO> couponList,String couponContant) {
        CreateOrderRep.MerchantListDTO.MerchantCouponListDTO returnDto = null;
        for (ConfirmOrderRsp.MerchantListDTO.MerchantCouponListDTO coupon: couponList) {
            if (coupon.getCouponName().contains(couponContant)) {
                returnDto = new CreateOrderRep.MerchantListDTO.MerchantCouponListDTO();
                BeanUtils.copyProperties(coupon,returnDto);
                break;
            }
        }
        return returnDto;
    }

    /**
     * 从地址串中解析提取出省市区等信息
     * @param address   地址信息
     * @return          解析后的地址Map
     */
    private SubmitOrderRep addressResolution(String address){
        SubmitOrderRep submitOrderRep = new SubmitOrderRep();
        //1.地址的正则表达式
        String regex = "(?<province>[^省]+省|.+自治区|[^澳门]+澳门|[^香港]+香港|[^市]+市)?(?<city>[^自治州]+自治州|[^特别行政区]+特别行政区|[^市]+市|.*?地区|.*?行政单位|.+盟|市辖区|[^县]+县)(?<county>[^县]+县|[^市]+市|[^镇]+镇|[^区]+区|[^乡]+乡|.+场|.+旗|.+海域|.+岛)?(?<address>.*)";
        //2、创建匹配规则
        Matcher m = Pattern.compile(regex).matcher(address);
        String province;
        String city;
        String county;
        String detailAddress;

        while (m.find()){
            //加入省
            province = m.group("province");
            if (StringUtils.isEmpty(province)) {
                throw new RiBizExecption("地址错误:缺失省");
            }
            submitOrderRep.setProvince(province);
            //加入市
            city = m.group("city");
            if (StringUtils.isEmpty(city)) {
                throw new RiBizExecption("地址错误:缺失市");
            }
            submitOrderRep.setCity(city);
            //加入区
            county = m.group("county");
            if (StringUtils.isEmpty(county)) {
                throw new RiBizExecption("地址错误:缺失区");
            }
            submitOrderRep.setDistrict(county);
            //详细地址
            detailAddress = m.group("address");
            if (StringUtils.isEmpty(detailAddress)) {
                throw new RiBizExecption("地址错误:详细地址");
            }
            submitOrderRep.setAddress(detailAddress);
        }
        return submitOrderRep;
    }


}
