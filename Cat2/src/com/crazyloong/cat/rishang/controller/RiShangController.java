package com.crazyloong.cat.rishang.controller;

import com.alibaba.fastjson.JSONObject;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rishang.constant.PlaceOrderType;
import com.crazyloong.cat.rishang.dto.*;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderAddress;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPlaced;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderAddressService;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderConvolutionCodeService;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPlacedService;
import com.crazyloong.cat.pojo.Message;
import com.crazyloong.cat.pojo.PostBody;
import com.crazyloong.cat.pojo.RSUser;
import com.crazyloong.cat.pojo.RSUserList;
import com.crazyloong.cat.rishang.service.RiShangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ri")
public class RiShangController extends ApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RiShangService riShangService;

    @Autowired
    Environment environment;

    @Autowired
    RSUserList userList;
    @Autowired
    private RiOrderPhoneService riOrderPhoneService;
    @Autowired
    private RiOrderAddressService addressService;
    @Autowired
    private RiOrderPlacedService riOrderPlacedService;
    @Autowired
    private RiOrderConvolutionCodeService riOrderConvolutionCodeService;

    @PostMapping("/rishang")
    public String rishang(Message message){
        PostBody postBody = new PostBody();
        postBody.setName(message.getName());
        postBody.setAddress(message.getAddress());
        postBody.setTel(message.getPhone());
        List<RSUser> list = userList.getUserList();
        for (int i = 0; i < list.size(); i++) {
            RSUser user = list.get(i);
            logger.debug("user："+user.getName()+"开始登陆");
            user.setPwd("Bearer "+riShangService.login(user.getName(),"Bearer f5012b2861224a5c8efc2064b5128efd"));
            if (user.getName().equals(message.getTel())) {
                logger.debug("获取"+user.getName()+"的购物车");
                Map<String,Integer> goods = riShangService.getCart(user.getPwd());
                postBody.setGoods(goods);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            RSUser user = list.get(i);
            logger.debug("user："+user.getName()+"开始下订单");
            riShangService.buyGoods(user.getPwd(),postBody);
        }

        return "";
    }

    @PostMapping("/riLogin")
    public R<String> riLogin(@RequestBody RiReq riReq){
        logger.info("user："+riReq.getPhone()+"开始登陆");
        return success("Bearer "+riShangService.login(riReq.getPhone(),riReq.getPassword()));
    }

    @PostMapping("/getwishs")
    public R<List<WishPageRsp>> getwishs(@RequestBody WishReq wishReq){
        RiReturnRsp<List<Object>> wishsBack = riShangService.getWishs(wishReq.getToken(),wishReq.getType());
        List<Object> wishRspListStr = wishsBack.getData();
        if (wishRspListStr == null || wishRspListStr.size() == 0) {
            return success(null);
        }
        List<WishPageRsp> wishPageRspList = new ArrayList<>(wishRspListStr.size());
        for (int i = 0; i < wishRspListStr.size(); i++) {
            JSONObject jsonObject = (JSONObject)wishRspListStr.get(i);
            WishRsp wishRsp = jsonObject.toJavaObject(WishRsp.class);
            WishPageRsp wishPageRsp = new WishPageRsp();
            wishPageRsp.setCode(wishRsp.getCode());
            List<GoodsBean> goodsBeanList = wishRsp.getGoods();
            String abname = "";
            String price = "";
            String num = "";
            String abiid = "";
            for(GoodsBean goods: goodsBeanList) {
                abname = abname +goods.getAbname() +",";
                price = price +goods.getPrice() +",";
                num = num +goods.getNum() +",";
                abiid = abiid +goods.getAbiid() +",";
            }
            wishPageRsp.setAbname(abname.substring(0,abname.length()-1));
            wishPageRsp.setPrice(price.substring(0,price.length()-1));
            wishPageRsp.setNum(num.substring(0,num.length()-1));
            wishPageRsp.setAbiid(abiid.substring(0,abiid.length()-1));
            wishPageRspList.add(wishPageRsp);
        }
        return success(wishPageRspList);
    }

    @PostMapping("/placeOrderByCode")
    public R<String> placeOrderByCode(@RequestBody RiOrderReq riOrderReq){
        RiOrderPhone riOrderPhone = riOrderPhoneService.getById(riOrderReq.getPhoneId());
        RiOrderAddress address = addressService.getById(riOrderReq.getAddressId());
        SubmitOrderReq submitOrderReq = new SubmitOrderReq();
        submitOrderReq.setName(address.getUserName());
        submitOrderReq.setAddress(address.getAddress());
        submitOrderReq.setTel(address.getUserPhone());
        submitOrderReq.setIssue("0");
        submitOrderReq.setType(2);
        submitOrderReq.setRightscode("-999");
        WishPageRsp wishPageRsp = riOrderReq.getWishPageRsp();
        // 根据订单数量 循环下单
        for (int i = 0; i < riOrderReq.getOrderNum(); i++) {
            List<Integer> abiids = new ArrayList<>();
            // 加入购物车
            List<String> abiidList = Arrays.asList(wishPageRsp.getAbiid().split(","));
            List<String> numList = Arrays.asList(wishPageRsp.getNum().split(","));
            for (int j = 0; j < abiidList.size(); j++) {
                abiids.add(Integer.parseInt(abiidList.get(j)));
                riShangService.addCart(Integer.parseInt(abiidList.get(j)),Integer.parseInt(numList.get(j)),riOrderReq.getToken());
            }
            VipCodeRsp vipCode = null;
            if (PlaceOrderType.publicCode.code.equals(riOrderReq.getType())) {
                // 获取优惠券信息
                RiOrderConvolutionCode selectCase = new RiOrderConvolutionCode();
                selectCase.setIsInuse(0);
                selectCase.setIsUsed(0);
                List<RiOrderConvolutionCode> codeList  = riOrderConvolutionCodeService.listCodes(selectCase);
                RiOrderConvolutionCode codeEnd = null;
                for(RiOrderConvolutionCode code: codeList){
                    vipCode = riShangService.getVipCode(riOrderReq.getToken(),code.getCode());
                    // 如果优惠码无效则更新状态继续查询
                    if (vipCode == null) {
                        code.setIsInuse(1);
                        riOrderConvolutionCodeService.saveOrUpdate(code);
                    } else {
                        codeEnd = code;
                        code.setIsInuse(1);
                        code.setIsUsed(1);
                        riOrderConvolutionCodeService.saveOrUpdate(code);
                        break;
                    }
                }
            } else {
                // 如果下单方式为用户优惠券则获取用户自己的优惠券
                vipCode = riShangService.getVipCodeMyself(riOrderReq.getToken(),riOrderReq.getPreferentialSum());
            }

            if (vipCode == null) {
                throw new RuntimeException("无可用优惠券！");
            }
            // 生成订单
            CreateOrderReq createOrderReq = new CreateOrderReq();
            createOrderReq.setType(2);
            createOrderReq.setAbiids(abiids);
            createOrderReq.setCouponscode(vipCode.getCode());
            createOrderReq.setRightscode("-999");
            CreateOrderRsp createOrderRsp = riShangService.createOrder(riOrderReq.getToken(),createOrderReq);
            if (createOrderRsp == null) {
                throw new RuntimeException("生成订单失败！");
            }
            // 提交订单
            submitOrderReq.setCouponscode(vipCode.getCode());
            submitOrderReq.setAbiids(abiids);
            submitOrderReq.setWishid(createOrderRsp.getWishid());
            Integer riReturnRsp = riShangService.submitOrder(riOrderReq.getToken(),submitOrderReq);
            PlacedOrderRsp placedOrderRsp = riShangService.getPlacedOrder(riOrderReq.getToken(),String.valueOf(riReturnRsp));
            RiOrderPlaced riOrderPlaced = new RiOrderPlaced();
            riOrderPlaced.setOrderCode(placedOrderRsp.getSjcode());
            riOrderPlaced.setOrderUser(riOrderPhone.getPhone());
            riOrderPlaced.setAddressName(placedOrderRsp.getContacts());
            riOrderPlaced.setAddressPhone(placedOrderRsp.getTel());
            riOrderPlaced.setAddress(placedOrderRsp.getAddress());
            riOrderPlaced.setGoodsName(wishPageRsp.getAbname());
            riOrderPlaced.setGoodsNum(wishPageRsp.getNum());
            riOrderPlaced.setGoodsPrice(String.valueOf(placedOrderRsp.getPrices()));
            riOrderPlaced.setGoodsOprice(String.valueOf(placedOrderRsp.getOprices()));
            riOrderPlacedService.save(riOrderPlaced);
        }
        return success("");
    }


}
