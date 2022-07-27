package com.crazyloong.cat.rsNew.controller;

import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rishang.dto.RiOrderReq;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import com.crazyloong.cat.rsNew.constant.PhoneFlag;
import com.crazyloong.cat.rsNew.dto.*;
import com.crazyloong.cat.rsNew.service.RSNUseService;
import com.crazyloong.cat.rsNew.service.RSNewService;
import com.crazyloong.cat.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 22:22
 * @Description :中免日上对外服务
 */
@RestController
@RequestMapping("rsnew")
public class RSNewController extends ApiController {

    @Autowired
    private RSNewService rsNewService;
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private RiOrderPhoneService riOrderPhoneService;
    @Autowired
    private RSNUseService rsnUseService;


    /**
     * 功能描述：
     * @Param:  中免日上设备登录
     * @Return: com.crazyloong.cat.rsNew.dto.DeviceLoginRsp
     * @Author:
     * @Date: 2022/6/14 22:31
     * @Description:
     */
    @PostMapping("/deviceLogin")
    public R<Boolean> deviceLogin() {
         rsNewService.deviceLogin();
         return success(true);
    }

    /**
    * 功能描述：中免日上登录
    * @Param: phone
    * @Return: com.crazyloong.cat.api.R<com.crazyloong.cat.rsNew.dto.LoginRsp>
    * @Author:
    * @Date: 2022/6/14 23:58
    */
    @PostMapping("/login")
    public R<RSNewUserInfo> login(@RequestBody String phone) {
        return success(cacheUtil.getRSNewToken(phone));
    }

    /**
    * 功能描述：领取优惠券
    * @Param: []
    * @Return: com.crazyloong.cat.api.R<java.lang.Boolean>
    * @Author:
    * @Date: 2022/6/17 19:31
    * @Description:
    */
    @PostMapping("/drawCoupon")
    public R<Boolean> drawCoupon(@RequestBody DrawCouponReq drawCouponReq){
        List<RiOrderPhone> phoneList = riOrderPhoneService.getPhones(drawCouponReq);
        rsnUseService.drawCoupon(phoneList,drawCouponReq);
        return success(true);
    }

    /**
     * 功能描述： 获取订单列表
     * @Param: [orderRep]
     * @Return: com.crazyloong.cat.rsNew.dto.OrderListRsp
     * @Author:
     * @Date: 2022/6/17 20:31
     * @Description:
     */
    @PostMapping("/getOrderList")
    public R<OrderListRsp> getOrderList(@RequestBody OrderRep orderRep) {
        return success(rsNewService.getOrderList(orderRep));
    }

    /**
     * 功能描述： 获取用户优惠券
     * @Param: [orderRep]
     * @Return: com.crazyloong.cat.rsNew.dto.OrderListRsp
     * @Author:
     * @Date: 2022/6/17 20:31
     * @Description:
     */
    @PostMapping("/getAllMyCoupon")
    public R<MyCouponRsp> getAllMyCoupon(@RequestBody OrderRep orderRep) {
        return success(rsnUseService.getAllMyCoupon(orderRep.getPhone()));
    }
    /**
    * 功能描述：变更密码
    * @Param: [changePasswordRep]
    * @Return: com.crazyloong.cat.api.R<java.lang.Boolean>
    * @Author:
    * @Date: 2022/6/18 18:59
    * @Description:
    */
    @PostMapping("/changePassword")
    public R<Boolean> changePassword(@RequestBody ChangePasswordRep changePasswordRep) {
        rsNewService.changePassword(changePasswordRep);
        return success(true);
    }

    /**
     * 功能描述：下订单
     * @Param: [changePasswordRep]
     * @Return: com.crazyloong.cat.api.R<java.lang.Boolean>
     * @Author:
     * @Date: 2022/6/18 18:59
     * @Description:
     */
    @PostMapping("/submitBatchOrder")
    public R<Boolean> submitBatchOrder(@RequestBody RiOrderReq riOrderReq) {
        rsnUseService.submitBatchOrder(riOrderReq);
        return success(true);
    }


    @PostMapping("/addCarAndAddress")
    public R<Boolean> addCarAndAddress(@RequestBody RiOrderReq riOrderReq) {
        rsnUseService.addCarAndAddress(riOrderReq);
        return success(true);
    }


}
