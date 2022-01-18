package com.crazyloong.cat.dto;

import java.util.List;

/**
 * @author YPLI
 * @description
 * @date 2022/1/16 0016 15:59
 **/
public class PlacedOrderRsp {


    /**
     * vipid : 0
     * vipname : null
     * accept : 0
     * contacts : 赵楠
     * tel : 18678836908
     * address : 山东省 济南市 历城区 工业北路钢城新苑西区
     * sjcode : BA97684759
     * invtitle : 电子发票
     * taketype : 0
     * paytype : 2
     * point : 0
     * payurl : null
     * invstate : 99
     * invurl : null
     * discout : 358
     * showds : s
     * discounts : [{"Type":1,"Name":"订单金额","Item":null,"Amount":1790},{"Type":-1,"Name":"促销优惠","Item":null,"Amount":-358},{"Type":1,"Name":"实付金额","Item":null,"Amount":1432}]
     * aftersale : null
     * isprize : null
     * prizeurl : null
     * id : 48701937
     * uid : 0
     * code : AP220136897442
     * orderid : null
     * type : 0
     * typestr : 其他
     * count : 1
     * creatdate : 2022/01/16 15:53:30
     * remark : null
     * state : 2
     * statedesc : 等待付款
     * options : newapi
     * oprices : 1790
     * prices : 1432
     * friendname : null
     * frienddesc : null
     * goods : [{"id":0,"abiid":324527,"num":1,"abname":"雅诗兰黛特润修护肌活精华露双支装(限量版)","abname_en":"ESTÉE LAUDER ADVANCED NIGHT REPAIR ANR SERUM DUO HNY 200ml","oprice":1790,"price":1432,"pricetype":0,"limit":50,"stock":101}]
     * sunpay : 0
     * maxpoint : 0
     * stocktype : 0
     * paytime : null
     * aboutprice : 0
     * complete : null
     * RefundStr : null
     * RefundComplete : null
     * RefundDesc : null
     * couponcode :
     * issue_int : 0
     * issue_str : 部分商品缺货时,我希望按可配送商品数量继续履行定单
     * Issues : {"Df":{"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"},"KeyValues":[{"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"},{"Key":"有商品缺货时,我希望自动取消此定单","Value":"1"}]}
     * ischk : 0
     * rights_card : null
     * refundtip : 确认取消订单吗?订单取消后退款于3-5日内自动退回.如有异常,会有客服联系沟通,于20个工作日内完成退款!
     */

    private int vipid;
    private Object vipname;
    private int accept;
    private String contacts;
    private String tel;
    private String address;
    private String sjcode;
    private String invtitle;
    private int taketype;
    private int paytype;
    private int point;
    private Object payurl;
    private int invstate;
    private Object invurl;
    private int discout;
    private String showds;
    private Object aftersale;
    private Object isprize;
    private Object prizeurl;
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
    private String couponcode;
    private String issue_int;
    private String issue_str;
    private IssuesBean Issues;
    private int ischk;
    private Object rights_card;
    private String refundtip;
    private List<DiscountsBean> discounts;
    private List<GoodsBean> goods;

    public int getVipid() {
        return vipid;
    }

    public void setVipid(int vipid) {
        this.vipid = vipid;
    }

    public Object getVipname() {
        return vipname;
    }

    public void setVipname(Object vipname) {
        this.vipname = vipname;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSjcode() {
        return sjcode;
    }

    public void setSjcode(String sjcode) {
        this.sjcode = sjcode;
    }

    public String getInvtitle() {
        return invtitle;
    }

    public void setInvtitle(String invtitle) {
        this.invtitle = invtitle;
    }

    public int getTaketype() {
        return taketype;
    }

    public void setTaketype(int taketype) {
        this.taketype = taketype;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Object getPayurl() {
        return payurl;
    }

    public void setPayurl(Object payurl) {
        this.payurl = payurl;
    }

    public int getInvstate() {
        return invstate;
    }

    public void setInvstate(int invstate) {
        this.invstate = invstate;
    }

    public Object getInvurl() {
        return invurl;
    }

    public void setInvurl(Object invurl) {
        this.invurl = invurl;
    }

    public int getDiscout() {
        return discout;
    }

    public void setDiscout(int discout) {
        this.discout = discout;
    }

    public String getShowds() {
        return showds;
    }

    public void setShowds(String showds) {
        this.showds = showds;
    }

    public Object getAftersale() {
        return aftersale;
    }

    public void setAftersale(Object aftersale) {
        this.aftersale = aftersale;
    }

    public Object getIsprize() {
        return isprize;
    }

    public void setIsprize(Object isprize) {
        this.isprize = isprize;
    }

    public Object getPrizeurl() {
        return prizeurl;
    }

    public void setPrizeurl(Object prizeurl) {
        this.prizeurl = prizeurl;
    }

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

    public String getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(String couponcode) {
        this.couponcode = couponcode;
    }

    public String getIssue_int() {
        return issue_int;
    }

    public void setIssue_int(String issue_int) {
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

    public List<DiscountsBean> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountsBean> discounts) {
        this.discounts = discounts;
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

    public static class DiscountsBean {
        /**
         * Type : 1
         * Name : 订单金额
         * Item : null
         * Amount : 1790.0
         */

        private int Type;
        private String Name;
        private Object Item;
        private double Amount;

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public Object getItem() {
            return Item;
        }

        public void setItem(Object Item) {
            this.Item = Item;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }
    }

    public static class GoodsBean {
        /**
         * id : 0
         * abiid : 324527
         * num : 1
         * abname : 雅诗兰黛特润修护肌活精华露双支装(限量版)
         * abname_en : ESTÉE LAUDER ADVANCED NIGHT REPAIR ANR SERUM DUO HNY 200ml
         * oprice : 1790
         * price : 1432
         * pricetype : 0
         * limit : 50
         * stock : 101
         */

        private int id;
        private int abiid;
        private int num;
        private String abname;
        private String abname_en;
        private int oprice;
        private int price;
        private int pricetype;
        private int limit;
        private int stock;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAbiid() {
            return abiid;
        }

        public void setAbiid(int abiid) {
            this.abiid = abiid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getAbname() {
            return abname;
        }

        public void setAbname(String abname) {
            this.abname = abname;
        }

        public String getAbname_en() {
            return abname_en;
        }

        public void setAbname_en(String abname_en) {
            this.abname_en = abname_en;
        }

        public int getOprice() {
            return oprice;
        }

        public void setOprice(int oprice) {
            this.oprice = oprice;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPricetype() {
            return pricetype;
        }

        public void setPricetype(int pricetype) {
            this.pricetype = pricetype;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }
}
