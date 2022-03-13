package com.crazyloong.cat.rishang.controller;

import com.alibaba.fastjson.JSONObject;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rishang.dto.*;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderConvolutionCodeService;
import com.crazyloong.cat.pojo.RSUserList;
import com.crazyloong.cat.rishang.service.RiShangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
    @Qualifier(value = "taskExecutor")
    private ThreadPoolTaskExecutor poolTaskExecutor;
    @Autowired
    private RiOrderConvolutionCodeService riOrderConvolutionCodeService;

    @Resource
    private CacheManager cacheManager;



    @PostMapping("/riLogin")
    public R<String> riLogin(@RequestBody RiReq riReq){
        List<String> phoneList = riReq.getPhoneList();
        Map<String,String> phoneToken = new HashMap<>();
        cacheManager.getCache("user_cache").clear();
        phoneList.forEach(phone->{
            phoneToken.put(phone,"Bearer "+riShangService.login(phone,riReq.getPassword()));
        });
        cacheManager.getCache("user_cache").put("phoneToken",phoneToken);
        // 返回第一笔数据的登录token
        return success(phoneToken.get(phoneList.get(0)));
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

    @PostMapping("/checkUnUsedVipCode")
    public R<Boolean> checkUnUsedVipCode(@RequestBody RiReq riReq) throws InterruptedException {
        RiOrderConvolutionCode selectCase = new RiOrderConvolutionCode();
        selectCase.setInputTime("2022-03-07 21");
        List<RiOrderConvolutionCode> codeList  = riOrderConvolutionCodeService.listCodes(selectCase);
        //VipCodeRsp vipCode = riShangService.getVipCode(riReq.getToken(),codeList.get(0).getCode());
        for(RiOrderConvolutionCode code: codeList){
            Thread.sleep(7000);
            VipCodeRsp vipCode = riShangService.getVipCode(riReq.getToken(),code.getCode());
            // 如果优惠码无效则更新状态继续查询
            if (vipCode == null) {
                code.setIsInuse(1);
                riOrderConvolutionCodeService.saveOrUpdate(code);
            } else {
                code.setIsInuse(0);
                code.setIsUsed(0);
                riOrderConvolutionCodeService.saveOrUpdate(code);
            }
        }
        return null;
    }

    @PostMapping("/placeOrderByCode")
    public R<String> placeOrderByCode(@RequestBody RiOrderReq riOrderReq){
        Map<String,String> phoneToken = cacheManager.getCache("user_cache").get("phoneToken",Map.class);
        phoneToken.forEach((phone,token)->{
            riOrderReq.setPhone(phone);
            riOrderReq.setToken(token);
            // 异步调用线程下单
            poolTaskExecutor.execute(()->{
                try {
                    riShangService.placeOrderByCode(riOrderReq);
                } catch (InterruptedException e) {
                    logger.error("线程终止：",e);
                }
            });
        });
        return success("下单调用成功，可稍后登录app查看！");
    }


}
