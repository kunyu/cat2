package com.crazyloong.cat.dto;

import java.util.List;

/**
 * @author YPLI
 * @description 获取我的订单信息返回对象
 * @date 2022/1/8 0008 22:32
 **/
public class WishRsp {

    /**
     * id : 45075024
     * uid : 0
     * code : BA94777453
     * orderid : null
     * type : 15
     * typestr : 在线商城
     * count : 5
     * creatdate : 2021/12/28 19:10:47
     * remark : null
     * state : -1
     * statedesc : 已取消
     * options : newapi
     * oprices : 0
     * prices : 1509
     * friendname : null
     * frienddesc : null
     * goods : [{"id":0,"abiid":313036,"num":3,"abname":"圣罗兰新明彩轻垫粉底液全新墨水气垫（B20号）","abname_en":null,"oprice":0,"price":377,"pricetype":0,"limit":0,"stock":0},{"id":0,"abiid":317126,"num":1,"abname":"圣罗兰新明彩轻垫粉底液全新墨水气垫迷你版(赠送品)","abname_en":null,"oprice":0,"price":1,"pricetype":0,"limit":0,"stock":0},{"id":0,"abiid":313035,"num":1,"abname":"圣罗兰新明彩轻垫粉底液全新墨水气垫（B10号）","abname_en":null,"oprice":0,"price":377,"pricetype":0,"limit":0,"stock":0}]
     * sunpay : 0
     * maxpoint : 0
     * stocktype : 0
     * paytime : null
     * aboutprice : 0
     * complete : null
     * RefundStr : null
     * RefundComplete : null
     * RefundDesc : null
     * couponcode : null
     * issue_int : null
     * issue_str :
     * Issues : {"Df":{"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"},"KeyValues":[{"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"},{"Key":"有商品缺货时,我希望自动取消此定单","Value":"1"}]}
     * ischk : 0
     * rights_card : null
     * refundtip : 确认取消订单吗?订单取消后退款于3-5日内自动退回.如有异常,会有客服联系沟通,于20个工作日内完成退款!
     */

    private int id;
    private int uid;
    private String code;
    private Object orderid;
    private int type;
    private String typestr;
    private int count;
    private String creatdate;
    private Object remark;
    private int state;
    private String statedesc;
    private String options;
    private int oprices;
    private int prices;
    private Object friendname;
    private Object frienddesc;
    private int sunpay;
    private int maxpoint;
    private int stocktype;
    private Object paytime;
    private int aboutprice;
    private Object complete;
    private Object RefundStr;
    private Object RefundComplete;
    private Object RefundDesc;
    private Object couponcode;
    private Object issue_int;
    private String issue_str;
    private IssuesBean Issues;
    private int ischk;
    private Object rights_card;
    private String refundtip;
    private List<GoodsBean> goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getOrderid() {
        return orderid;
    }

    public void setOrderid(Object orderid) {
        this.orderid = orderid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypestr() {
        return typestr;
    }

    public void setTypestr(String typestr) {
        this.typestr = typestr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStatedesc() {
        return statedesc;
    }

    public void setStatedesc(String statedesc) {
        this.statedesc = statedesc;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getOprices() {
        return oprices;
    }

    public void setOprices(int oprices) {
        this.oprices = oprices;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public Object getFriendname() {
        return friendname;
    }

    public void setFriendname(Object friendname) {
        this.friendname = friendname;
    }

    public Object getFrienddesc() {
        return frienddesc;
    }

    public void setFrienddesc(Object frienddesc) {
        this.frienddesc = frienddesc;
    }

    public int getSunpay() {
        return sunpay;
    }

    public void setSunpay(int sunpay) {
        this.sunpay = sunpay;
    }

    public int getMaxpoint() {
        return maxpoint;
    }

    public void setMaxpoint(int maxpoint) {
        this.maxpoint = maxpoint;
    }

    public int getStocktype() {
        return stocktype;
    }

    public void setStocktype(int stocktype) {
        this.stocktype = stocktype;
    }

    public Object getPaytime() {
        return paytime;
    }

    public void setPaytime(Object paytime) {
        this.paytime = paytime;
    }

    public int getAboutprice() {
        return aboutprice;
    }

    public void setAboutprice(int aboutprice) {
        this.aboutprice = aboutprice;
    }

    public Object getComplete() {
        return complete;
    }

    public void setComplete(Object complete) {
        this.complete = complete;
    }

    public Object getRefundStr() {
        return RefundStr;
    }

    public void setRefundStr(Object RefundStr) {
        this.RefundStr = RefundStr;
    }

    public Object getRefundComplete() {
        return RefundComplete;
    }

    public void setRefundComplete(Object RefundComplete) {
        this.RefundComplete = RefundComplete;
    }

    public Object getRefundDesc() {
        return RefundDesc;
    }

    public void setRefundDesc(Object RefundDesc) {
        this.RefundDesc = RefundDesc;
    }

    public Object getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(Object couponcode) {
        this.couponcode = couponcode;
    }

    public Object getIssue_int() {
        return issue_int;
    }

    public void setIssue_int(Object issue_int) {
        this.issue_int = issue_int;
    }

    public String getIssue_str() {
        return issue_str;
    }

    public void setIssue_str(String issue_str) {
        this.issue_str = issue_str;
    }

    public IssuesBean getIssues() {
        return Issues;
    }

    public void setIssues(IssuesBean Issues) {
        this.Issues = Issues;
    }

    public int getIschk() {
        return ischk;
    }

    public void setIschk(int ischk) {
        this.ischk = ischk;
    }

    public Object getRights_card() {
        return rights_card;
    }

    public void setRights_card(Object rights_card) {
        this.rights_card = rights_card;
    }

    public String getRefundtip() {
        return refundtip;
    }

    public void setRefundtip(String refundtip) {
        this.refundtip = refundtip;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class IssuesBean {
        /**
         * Df : {"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"}
         * KeyValues : [{"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"},{"Key":"有商品缺货时,我希望自动取消此定单","Value":"1"}]
         */

        private DfBean Df;
        private List<KeyValuesBean> KeyValues;

        public DfBean getDf() {
            return Df;
        }

        public void setDf(DfBean Df) {
            this.Df = Df;
        }

        public List<KeyValuesBean> getKeyValues() {
            return KeyValues;
        }

        public void setKeyValues(List<KeyValuesBean> KeyValues) {
            this.KeyValues = KeyValues;
        }

        public static class DfBean {
            /**
             * Key : 部分商品缺货时,我希望按可配送商品数量继续履行定单
             * Value : 0
             */

            private String Key;
            private String Value;

            public String getKey() {
                return Key;
            }

            public void setKey(String Key) {
                this.Key = Key;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }
        }

        public static class KeyValuesBean {
            /**
             * Key : 部分商品缺货时,我希望按可配送商品数量继续履行定单
             * Value : 0
             */

            private String Key;
            private String Value;

            public String getKey() {
                return Key;
            }

            public void setKey(String Key) {
                this.Key = Key;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }
        }
    }


}
