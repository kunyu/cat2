package com.crazyloong.cat.rishang.controller;

import com.alibaba.fastjson.JSONObject;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rishang.dto.*;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderConvolutionCodeService;
import com.crazyloong.cat.pojo.RSUserList;
import com.crazyloong.cat.rishang.service.RiShangService;
import com.crazyloong.cat.util.CacheUtil;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("ri")
public class RiShangController extends ApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String PHONE = "19912122212";
    @Autowired
    RiShangService riShangService;
    @Autowired
    Environment environment;
    @Autowired
    RSUserList userList;
    @Autowired
    private RiOrderConvolutionCodeService riOrderConvolutionCodeService;

    @Resource
    private CacheUtil cacheUtil;



    @PostMapping("/riLogin")
    public R<String> riLogin(@RequestBody RiReq riReq){
        List<String> phoneList = riReq.getPhoneList();
        Map<String,String> phoneToken = new HashMap<>();
        phoneList.forEach(phone->{
            phoneToken.put(phone,riShangService.login(phone,riReq.getPassword()));
        });
        cacheUtil.put("phoneToken",phoneToken);
        // 返回第一笔数据的登录token
        return success(phoneToken.get(phoneList.get(0)));
    }

    @PostMapping("/getwishs")
    public R<List<WishPageRsp>> getwishs(@RequestBody WishReq wishReq){
        RiReturnRsp<List<Object>> wishsBack = riShangService.getWishs(wishReq);
        List<Object> wishRspListStr = wishsBack.getData();
        if (wishRspListStr == null || wishRspListStr.size() == 0) {
            return success(null);
        }
        List<WishPageRsp> wishPageRspList = new ArrayList<>(wishRspListStr.size());
        for (int i = 0; i < wishRspListStr.size(); i++) {
            JSONObject jsonObject = (JSONObject)wishRspListStr.get(i);
            WishRsp wishRsp = jsonObject.toJavaObject(WishRsp.class);
            PlacedOrderRsp placedOrderRsp = riShangService.getPlacedOrder(wishReq.getToken(), String.valueOf(wishRsp.getId()));
            WishPageRsp wishPageRsp = new WishPageRsp();
            BeanUtils.copyProperties(wishRsp,wishPageRsp);
            BeanUtils.copyProperties(placedOrderRsp,wishPageRsp);
            wishPageRsp.setPrices(placedOrderRsp.getPrices());
            wishPageRsp.setOprices(placedOrderRsp.getOprices());
            wishPageRsp.setCode(wishRsp.getCode());
            wishPageRsp.setCreatdate(placedOrderRsp.getCreatdate());
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

    @PostMapping("/checkUnUsedVipCode")
    public R<Boolean> checkUnUsedVipCode(@RequestBody RiReq riReq) throws InterruptedException {
        RiOrderConvolutionCode selectCase = new RiOrderConvolutionCode();
        List<RiOrderConvolutionCode> codeList  = riOrderConvolutionCodeService.listCodes(selectCase);
        for(RiOrderConvolutionCode code: codeList){
            VipCodeRsp vipCode = null;
            try {
                vipCode = riShangService.getVipCode(riReq.getToken(),code.getCode());
            } catch (Exception e) {
                logger.info("券码查询失败");
            }

            // 如果优惠码无效则更新状态继续查询
            if (vipCode == null) {
                code.setIsInuse(1);
                riOrderConvolutionCodeService.saveOrUpdate(code);
            } else {
                code.setIsInuse(0);
                code.setIsUsed(0);
                riOrderConvolutionCodeService.saveOrUpdate(code);
            }
            Thread.sleep(6000);
        }
        return null;
    }

    @PostMapping("/getVipCodeMyself")
    public R<Boolean> getVipCodeMyself(@RequestBody RiReq riReq) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            riShangService.getVipCodeMyself(riReq.getToken(),"");
            Thread.sleep(5000);
        }
        return null;
    }


    @PostMapping("/placeOrderByCode")
    public R<String> placeOrderByCode(@RequestBody RiOrderReq riOrderReq){
        Map<String,String> phoneToken = cacheUtil.get("phoneToken",Map.class);
        if (phoneToken == null) {
            return failed("无可用账号，请返回重新选择用户登录");
        }
        phoneToken.forEach((phone,token)->{
            riOrderReq.setPhone(phone);
            riOrderReq.setToken(token);
            // 异步调用线程下单
            try {
                riShangService.placeOrderByCode(riOrderReq);
            } catch (InterruptedException e) {
                logger.error("线程终止：",e);
            }
        });
        return success("下单调用成功，可稍后登录app查看！");
    }


    @PostMapping("/getTops")
    public R<List<GoodsRsp>> getTops() {
        String token = cacheUtil.getRiToken(PHONE);
        RiReturnRsp<List<GoodsRsp>> goodsRsp = riShangService.getTops(token);
        return success(goodsRsp.getData());
    }

    @PostMapping("/searchGoods")
    public R<List<GoodsRsp>> searchGoods(@RequestBody WishReq wishReq) {
        RiReturnRsp<List<GoodsRsp>> goodsRsp = riShangService.searchGoods(cacheUtil.getRiToken(PHONE),wishReq.getKey());
        return success(goodsRsp.getData());
    }

    @PostMapping("/setToken")
    public void setToken(String token) {
        cacheUtil.setRiFirstToken(token);
    }

    @PostMapping("/getToken")
    public R<String> getToken(@RequestBody RiOrderReq riOrderReq) {
        if (!StringUtils.isNullOrEmpty(riOrderReq.getFirstToken())) {
            cacheUtil.setRiFirstToken(riOrderReq.getFirstToken());
        }
        return success(cacheUtil.getRiToken(riOrderReq.getPhone()));
    }




}
