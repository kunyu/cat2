package com.crazyloong.cat.rishang.dto;

import java.util.List;

/**
 * @author YPLI
 * @description 生成订单返回对象
 * @date 2022/1/8 0008 20:33
 **/
public class CreateOrderRsp {

    /**
     * wishid : 46956555
     * type : 2
     * sunpay : 0
     * maxpoint : 0
     * usepoint : 0
     * amount : 1120
     * damount : 881
     * sign : null
     * count : 2
     * discount : -239
     * issue_int : null
     * issue_str :
     * Issues : {"Df":{"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"},"KeyValues":[{"Key":"部分商品缺货时,我希望按可配送商品数量继续履行定单","Value":"0"},{"Key":"有商品缺货时,我希望自动取消此定单","Value":"1"}]}
     * paylist : {"Df":{"Key":"在线支付","Value":"1"},"KeyValues":[{"Key":"在线支付","Value":"1"}]}
     * ischk : 0
     * details : [{"abiid":177412,"bcode":"06123","ccode":"0405","pcode":"202201061718198210","num":1,"abname":"[促销]祖·玛珑英国梨与小苍兰香氛满室幽香香薰","abname_en":"Jo Malone London  EP&F REED DIFFUSER 165ml","price":560,"rprice":405,"integrate":0,"maxnum":100,"tax_type":20,"ischeck":10,"img_url":"http://b2cimage.lib.cdn.srgow.com/images/177412/0_180.jpg","tags":["已享受1件75折","积分立减15",""],"repromotions":[]},{"abiid":323648,"bcode":"06123","ccode":"0405","pcode":"202201061718201570","num":1,"abname":"[促销]祖·玛珑英国梨与小苍兰满室幽香香薰-预包装版","abname_en":"Jo Malone London  ENGLISH PEAR & FREESIA SCENT SURROUND DIFFUSER PRE-PACK 165ml","price":560,"rprice":476,"integrate":0,"maxnum":100,"tax_type":20,"ischeck":10,"img_url":"http://b2cimage.lib.cdn.srgow.com/images/323648/0_180.jpg","tags":["已享受1件85折",""],"repromotions":[]}]
     * DiscountItem : [{"Type":1,"Name":null,"Item":"商品总额","Amount":1120},{"Type":-1,"Name":null,"Item":"促销优惠","Amount":-224},{"Type":-1,"Name":null,"Item":"积分抵扣","Amount":-15}]
     * isshowrights : 0
     * rightslbl : 权益卡包
     * rightsselectcode : -999
     * rightsshowtip : null
     * rights : null
     * iscouponupdate : null
     * couponupdatemsg : null
     * isshowtraveller : 10
     * ismusttraveller : 5
     * traveltitle : 国际/海南旅客身份认证
     * musttravellermsg : 需要完成国际/海南旅客身份认证
     * istraveller : 10
     * travellermsg : 国际/海南旅客身份已认证
     * tphone : 17600133533
     * tflighttime : 2021-12-24
     * tfilgthmintime : 2019-01-09
     * tflightmaxtime : 2022-01-08
     * nogoodsmsg : null
     * isshowconfirm : 10
     * confirmbox : {"ops":100,"html":"<div style=\"background-color:#fff;position:relative;height:100%;border-radius:6px;\"><div class=\"nc_wrap\" style=\"padding: 10px;\"><div class=\"nc_header_wrap\">购物须知<\/div><div id = \"box_desc_wrap\" class=\"desc_wrap\" style=\"overflow-y:scroll;height:280px; \"><p class=\"MsoNormal\">\n\t所有日上商城（以下称\u201c平台\u201d）在售商品均为正品，是日上免税行保税展示店销售的商品。您可自行来店提取商品；如您不便来店自取，请在订单中注明快递地址，视同您委托并接受有资质的第三方物流公司将商品快递给您。\n<\/p>\n<p class=\"MsoNormal\">\n\t凡商品上标有\u201c海南离岛免税专供\u201d字样的，均系从中免集团海南公司调运的商品，税已付讫。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t如您需要开具发票，请于收货后扫描小票上的二维码，即可开具有效发票。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t平台保留调整商品销售价格的权利。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t平台将不定期推出折扣活动，但<span style=\"color:#E53333;\"><strong>无保价和退差价服务<\/strong><\/span>，商品价格以实际支付价格为准。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t应品牌供应商要求，部分商品提交订单前可能需要填写旅行认证信息。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t一元体验装商品不单独出售，您可于换购活动上线后，在满足换购条件时下单购买。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【退换货服务】<span><\/span><\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t<span style=\"color:#E53333;\"><strong>平台不支持<\/strong><\/span><span style=\"color:#E53333;\"><strong>7<\/strong><\/span><span style=\"color:#E53333;\"><strong>天无理由退换货。<\/strong><\/span><span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t平台所售商品是日上免税行保税展示店销售的商品，不支持<span>7<\/span>天无理由退货，请您下单前仔细阅读商品详情页信息。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【关于临期及质保期标注】<span><\/span><\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t香化类商品（包括香水、护肤品、化妆品等）的剩余有效期在<span>6<\/span>个月以下的属于临期商品。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t香化类临期商品、酒类商品剩余有效期在<span>2<\/span>个月以下的和食品商品剩余有效期在<span>3<\/span>个月以下，平台售卖时会加以标注，请您谨慎购买，对于前述标注商品，平台不支持以商品临期为理由的退货申请。<span><\/span> \n<\/p>\n<p class=\"MsoListParagraph\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t【<b>商品版本<\/b>】<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t因商品产地或批次不同，商品外包装、质地、气味等会有所不同属于正常现象，不同版本是根据各国法规和针对当地消费者做出的调整，请放心使用。平台不接受以上述理由提出的退货要求。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t【<b>商品包装<\/b>】<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t因品牌方更新包装或升级产品原因，商品会存在新老包装交替发货情况，请以实际收到商品为准，商品图片和详情页面仅供参考。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t所有商品均为原厂包装出库，部分国际品牌倡导环保理念，有可能原包装即为简易包装，且并无精美礼盒包装或精美手提袋。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t平台不接受以上述理由提出的退货要求或收到商品后要求平台额外补寄包装。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<br />\n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【发货及物流】<\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t发货时间为您提交订单并支付货款后的<span>1-4<\/span>天内，如遇大规模促销活动，发货时间可能会顺延<span>1-5<\/span>天，请您耐心等待。如遇特殊情况（自然灾害或疫情防控）造成的交通管制，您的快递包裹可能延期配送。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t平台所售商品由韵达或顺丰随机派送，不支持指定快递公司。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t收货时请仔细检查，如发现商品存在溢漏、破损等情况，请在货物签收后<span>48<\/span>小时内联系快递客服人员反馈并提供图片、视频等信息，逾期将不予受理。（商品包装压坏不予受理）<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<br />\n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【取消订单】<\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t如您需要取消已提交的订单，请点开\u201c更多\u201d<span>-<\/span>\u201c我的订单\u201d<span>-<\/span>\u201c已提交\u201d订单详情页，下拉到底部，如页面底部有\u201c取消订单\u201d选项，且点击\u201c确认取消\u201d后，系统显示\n\u201c已取消\u201d，则成功取消订单；若没有该选项，则代表商品在保税区仓库开始配货后已报送海关，此时订单已无法取消，请理解。<span><\/span> \n<\/p>\n<p class=\"MsoListParagraph\" style=\"margin-left:21.2pt;text-indent:0cm;\">\n\t<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【缺货】<\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t发货前我们会检查货品质量，在部分商品存在缺陷，又无其他存货可替换的情况下，为了保证品质，我们将对该部分商品按缺货选项处理。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t缺货商品，平台将予退款，不予补发。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<br />\n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【退款】<\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t如您取消订单，您实际支付的货款将于订单成功取消后的<span>3-20<\/span>个工作日内由系统自动退回。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t如存在缺货商品，缺货商品对应的货款将于发货后的<span>3-20<\/span>个工作日内由系统自动退回。如出现订单整单缺货，该订单将显示\u201c订单取消\u201d，货款将于显示\u201c订单取消\u201d后<span>3-20<\/span>个工作日内由系统自动退回。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t如出现重复支付，重复支付的货款将于下单成功后的<span>3-20<\/span>个工作日内由系统自动退回。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t因物流原因导致您未收到商品，您可联系客服解决，若申请退款，退款将于问题核实确认后的<span>1-2<\/span>个月内完成。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t系统完成退款操作后，所有的退款都将通过您支付时的通道原路返还，请注意查收。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<br />\n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【精品百货类商品特别提示】<span><\/span><\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t精品百货类商品（包括眼镜、首饰腕表、箱包、鞋履服饰等）发货清关过程中长途跋涉，若有细小划痕、胶水痕迹、线头、轻微气味等不影响商品质量、性能、用途的瑕疵，不属于商品质量问题，不支持退货。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t新品可能会存在来自于皮革、合成革、橡胶、织物、发泡材料、胶水等鞋用材料的一些特定气味，不影响商品使用，不属于商品质量问题，不支持退货。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\">\n\t精品百货类商品的参数、图片仅供参考，服装类商品尺寸<span>1-3cm<\/span>误差属正常范围。平台不接受以尺寸不符为理由的退货；因个人情况不同，建议下单前自行确认清楚个人尺码信息，平台推荐尺码仅供参考，不作为退换货的理由或者依据。<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.15pt;text-indent:-28.15pt;\">\n\t<span><\/span> \n<\/p>\n<p class=\"MsoNormal\" style=\"margin-left:28.25pt;text-indent:-28.25pt;\">\n\t<b>【食品类商品特别提示】<\/b> \n<\/p>\n<p class=\"MsoNormal\">\n\t因运输途中温度不可控因素，可能造成巧克力糖果等商品融化，平台售卖时会加以提醒，请您谨慎购买。如发生商品融化事件，不属于质量问题，不支持退货退款。<span><\/span> \n<\/p><\/div><\/div><div class=\"nc_btn_wrap\" style=\"position: absolute;left: 10px;right: 10px;bottom: 10px;\"><div style=\"font-size:12px; color:#f1641e;margin-bottom:10px;\"><label id =\"btn_service\">&lt;&lt;服务协议&gt;&gt;<\/label><label id=\"btn_buyservice\">&lt;&lt;购买须知&gt;&gt;<\/label><\/div><div style=\"display: flex;justify-content: center;align-items: center;\"><input id =\"btn_agree\" type=\"button\" value=\"同意并提交\" class=\"btn\" style=\"border-color: #f1641e;color: #fff;background-color:#f1641e;\" /><input id = \"btn_giveup\" type=\"button\" value=\"再看看\" class=\"btn\" style=\"margin-left: 20px;color: #6D6D6D;\" /><\/div><\/div>"}
     * paycardmoney : -1
     */

    private int wishid;
    private int type;
    private int sunpay;
    private int maxpoint;
    private int usepoint;
    private int amount;
    private int damount;
    private Object sign;
    private int count;
    private int discount;
    private Object issue_int;
    private String issue_str;
    private IssuesBean Issues;
    private PaylistBean paylist;
    private int ischk;
    private int isshowrights;
    private String rightslbl;
    private String rightsselectcode;
    private Object rightsshowtip;
    private Object rights;
    private Object iscouponupdate;
    private Object couponupdatemsg;
    private int isshowtraveller;
    private int ismusttraveller;
    private String traveltitle;
    private String musttravellermsg;
    private int istraveller;
    private String travellermsg;
    private String tphone;
    private String tflighttime;
    private String tfilgthmintime;
    private String tflightmaxtime;
    private Object nogoodsmsg;
    private String isshowconfirm;
    private ConfirmboxBean confirmbox;
    private int paycardmoney;
    private List<DetailsBean> details;
    private List<DiscountItemBean> DiscountItem;

    public int getWishid() {
        return wishid;
    }

    public void setWishid(int wishid) {
        this.wishid = wishid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getUsepoint() {
        return usepoint;
    }

    public void setUsepoint(int usepoint) {
        this.usepoint = usepoint;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDamount() {
        return damount;
    }

    public void setDamount(int damount) {
        this.damount = damount;
    }

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public PaylistBean getPaylist() {
        return paylist;
    }

    public void setPaylist(PaylistBean paylist) {
        this.paylist = paylist;
    }

    public int getIschk() {
        return ischk;
    }

    public void setIschk(int ischk) {
        this.ischk = ischk;
    }

    public int getIsshowrights() {
        return isshowrights;
    }

    public void setIsshowrights(int isshowrights) {
        this.isshowrights = isshowrights;
    }

    public String getRightslbl() {
        return rightslbl;
    }

    public void setRightslbl(String rightslbl) {
        this.rightslbl = rightslbl;
    }

    public String getRightsselectcode() {
        return rightsselectcode;
    }

    public void setRightsselectcode(String rightsselectcode) {
        this.rightsselectcode = rightsselectcode;
    }

    public Object getRightsshowtip() {
        return rightsshowtip;
    }

    public void setRightsshowtip(Object rightsshowtip) {
        this.rightsshowtip = rightsshowtip;
    }

    public Object getRights() {
        return rights;
    }

    public void setRights(Object rights) {
        this.rights = rights;
    }

    public Object getIscouponupdate() {
        return iscouponupdate;
    }

    public void setIscouponupdate(Object iscouponupdate) {
        this.iscouponupdate = iscouponupdate;
    }

    public Object getCouponupdatemsg() {
        return couponupdatemsg;
    }

    public void setCouponupdatemsg(Object couponupdatemsg) {
        this.couponupdatemsg = couponupdatemsg;
    }

    public int getIsshowtraveller() {
        return isshowtraveller;
    }

    public void setIsshowtraveller(int isshowtraveller) {
        this.isshowtraveller = isshowtraveller;
    }

    public int getIsmusttraveller() {
        return ismusttraveller;
    }

    public void setIsmusttraveller(int ismusttraveller) {
        this.ismusttraveller = ismusttraveller;
    }

    public String getTraveltitle() {
        return traveltitle;
    }

    public void setTraveltitle(String traveltitle) {
        this.traveltitle = traveltitle;
    }

    public String getMusttravellermsg() {
        return musttravellermsg;
    }

    public void setMusttravellermsg(String musttravellermsg) {
        this.musttravellermsg = musttravellermsg;
    }

    public int getIstraveller() {
        return istraveller;
    }

    public void setIstraveller(int istraveller) {
        this.istraveller = istraveller;
    }

    public String getTravellermsg() {
        return travellermsg;
    }

    public void setTravellermsg(String travellermsg) {
        this.travellermsg = travellermsg;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public String getTflighttime() {
        return tflighttime;
    }

    public void setTflighttime(String tflighttime) {
        this.tflighttime = tflighttime;
    }

    public String getTfilgthmintime() {
        return tfilgthmintime;
    }

    public void setTfilgthmintime(String tfilgthmintime) {
        this.tfilgthmintime = tfilgthmintime;
    }

    public String getTflightmaxtime() {
        return tflightmaxtime;
    }

    public void setTflightmaxtime(String tflightmaxtime) {
        this.tflightmaxtime = tflightmaxtime;
    }

    public Object getNogoodsmsg() {
        return nogoodsmsg;
    }

    public void setNogoodsmsg(Object nogoodsmsg) {
        this.nogoodsmsg = nogoodsmsg;
    }

    public String getIsshowconfirm() {
        return isshowconfirm;
    }

    public void setIsshowconfirm(String isshowconfirm) {
        this.isshowconfirm = isshowconfirm;
    }

    public ConfirmboxBean getConfirmbox() {
        return confirmbox;
    }

    public void setConfirmbox(ConfirmboxBean confirmbox) {
        this.confirmbox = confirmbox;
    }

    public int getPaycardmoney() {
        return paycardmoney;
    }

    public void setPaycardmoney(int paycardmoney) {
        this.paycardmoney = paycardmoney;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public List<DiscountItemBean> getDiscountItem() {
        return DiscountItem;
    }

    public void setDiscountItem(List<DiscountItemBean> DiscountItem) {
        this.DiscountItem = DiscountItem;
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

    public static class PaylistBean {
        /**
         * Df : {"Key":"在线支付","Value":"1"}
         * KeyValues : [{"Key":"在线支付","Value":"1"}]
         */

        private DfBeanX Df;
        private List<KeyValuesBeanX> KeyValues;

        public DfBeanX getDf() {
            return Df;
        }

        public void setDf(DfBeanX Df) {
            this.Df = Df;
        }

        public List<KeyValuesBeanX> getKeyValues() {
            return KeyValues;
        }

        public void setKeyValues(List<KeyValuesBeanX> KeyValues) {
            this.KeyValues = KeyValues;
        }

        public static class DfBeanX {
            /**
             * Key : 在线支付
             * Value : 1
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

        public static class KeyValuesBeanX {
            /**
             * Key : 在线支付
             * Value : 1
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

    public static class ConfirmboxBean {
        /**
         * ops : 100
         * html : <div style="background-color:#fff;position:relative;height:100%;border-radius:6px;"><div class="nc_wrap" style="padding: 10px;"><div class="nc_header_wrap">购物须知</div><div id = "box_desc_wrap" class="desc_wrap" style="overflow-y:scroll;height:280px; "><p class="MsoNormal">
         所有日上商城（以下称“平台”）在售商品均为正品，是日上免税行保税展示店销售的商品。您可自行来店提取商品；如您不便来店自取，请在订单中注明快递地址，视同您委托并接受有资质的第三方物流公司将商品快递给您。
         </p>
         <p class="MsoNormal">
         凡商品上标有“海南离岛免税专供”字样的，均系从中免集团海南公司调运的商品，税已付讫。<span></span>
         </p>
         <p class="MsoNormal">
         如您需要开具发票，请于收货后扫描小票上的二维码，即可开具有效发票。<span></span>
         </p>
         <p class="MsoNormal">
         平台保留调整商品销售价格的权利。<span></span>
         </p>
         <p class="MsoNormal">
         平台将不定期推出折扣活动，但<span style="color:#E53333;"><strong>无保价和退差价服务</strong></span>，商品价格以实际支付价格为准。<span></span>
         </p>
         <p class="MsoNormal">
         应品牌供应商要求，部分商品提交订单前可能需要填写旅行认证信息。<span></span>
         </p>
         <p class="MsoNormal">
         一元体验装商品不单独出售，您可于换购活动上线后，在满足换购条件时下单购买。<span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【退换货服务】<span></span></b>
         </p>
         <p class="MsoNormal">
         <span style="color:#E53333;"><strong>平台不支持</strong></span><span style="color:#E53333;"><strong>7</strong></span><span style="color:#E53333;"><strong>天无理由退换货。</strong></span><span></span>
         </p>
         <p class="MsoNormal">
         平台所售商品是日上免税行保税展示店销售的商品，不支持<span>7</span>天无理由退货，请您下单前仔细阅读商品详情页信息。<span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【关于临期及质保期标注】<span></span></b>
         </p>
         <p class="MsoNormal">
         香化类商品（包括香水、护肤品、化妆品等）的剩余有效期在<span>6</span>个月以下的属于临期商品。<span></span>
         </p>
         <p class="MsoNormal">
         香化类临期商品、酒类商品剩余有效期在<span>2</span>个月以下的和食品商品剩余有效期在<span>3</span>个月以下，平台售卖时会加以标注，请您谨慎购买，对于前述标注商品，平台不支持以商品临期为理由的退货申请。<span></span>
         </p>
         <p class="MsoListParagraph" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         【<b>商品版本</b>】<span></span>
         </p>
         <p class="MsoNormal">
         因商品产地或批次不同，商品外包装、质地、气味等会有所不同属于正常现象，不同版本是根据各国法规和针对当地消费者做出的调整，请放心使用。平台不接受以上述理由提出的退货要求。<span></span>
         </p>
         <p class="MsoNormal">
         <span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         【<b>商品包装</b>】<span></span>
         </p>
         <p class="MsoNormal">
         因品牌方更新包装或升级产品原因，商品会存在新老包装交替发货情况，请以实际收到商品为准，商品图片和详情页面仅供参考。<span></span>
         </p>
         <p class="MsoNormal">
         所有商品均为原厂包装出库，部分国际品牌倡导环保理念，有可能原包装即为简易包装，且并无精美礼盒包装或精美手提袋。<span></span>
         </p>
         <p class="MsoNormal">
         平台不接受以上述理由提出的退货要求或收到商品后要求平台额外补寄包装。<span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <br />
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【发货及物流】</b>
         </p>
         <p class="MsoNormal">
         发货时间为您提交订单并支付货款后的<span>1-4</span>天内，如遇大规模促销活动，发货时间可能会顺延<span>1-5</span>天，请您耐心等待。如遇特殊情况（自然灾害或疫情防控）造成的交通管制，您的快递包裹可能延期配送。<span></span>
         </p>
         <p class="MsoNormal">
         平台所售商品由韵达或顺丰随机派送，不支持指定快递公司。<span></span>
         </p>
         <p class="MsoNormal">
         收货时请仔细检查，如发现商品存在溢漏、破损等情况，请在货物签收后<span>48</span>小时内联系快递客服人员反馈并提供图片、视频等信息，逾期将不予受理。（商品包装压坏不予受理）<span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <br />
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【取消订单】</b>
         </p>
         <p class="MsoNormal">
         如您需要取消已提交的订单，请点开“更多”<span>-</span>“我的订单”<span>-</span>“已提交”订单详情页，下拉到底部，如页面底部有“取消订单”选项，且点击“确认取消”后，系统显示
         “已取消”，则成功取消订单；若没有该选项，则代表商品在保税区仓库开始配货后已报送海关，此时订单已无法取消，请理解。<span></span>
         </p>
         <p class="MsoListParagraph" style="margin-left:21.2pt;text-indent:0cm;">
         <span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【缺货】</b>
         </p>
         <p class="MsoNormal">
         发货前我们会检查货品质量，在部分商品存在缺陷，又无其他存货可替换的情况下，为了保证品质，我们将对该部分商品按缺货选项处理。<span></span>
         </p>
         <p class="MsoNormal">
         缺货商品，平台将予退款，不予补发。<span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <br />
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【退款】</b>
         </p>
         <p class="MsoNormal">
         如您取消订单，您实际支付的货款将于订单成功取消后的<span>3-20</span>个工作日内由系统自动退回。<span></span>
         </p>
         <p class="MsoNormal">
         如存在缺货商品，缺货商品对应的货款将于发货后的<span>3-20</span>个工作日内由系统自动退回。如出现订单整单缺货，该订单将显示“订单取消”，货款将于显示“订单取消”后<span>3-20</span>个工作日内由系统自动退回。<span></span>
         </p>
         <p class="MsoNormal">
         如出现重复支付，重复支付的货款将于下单成功后的<span>3-20</span>个工作日内由系统自动退回。<span></span>
         </p>
         <p class="MsoNormal">
         因物流原因导致您未收到商品，您可联系客服解决，若申请退款，退款将于问题核实确认后的<span>1-2</span>个月内完成。<span></span>
         </p>
         <p class="MsoNormal">
         系统完成退款操作后，所有的退款都将通过您支付时的通道原路返还，请注意查收。<span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <br />
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【精品百货类商品特别提示】<span></span></b>
         </p>
         <p class="MsoNormal">
         精品百货类商品（包括眼镜、首饰腕表、箱包、鞋履服饰等）发货清关过程中长途跋涉，若有细小划痕、胶水痕迹、线头、轻微气味等不影响商品质量、性能、用途的瑕疵，不属于商品质量问题，不支持退货。<span></span>
         </p>
         <p class="MsoNormal">
         新品可能会存在来自于皮革、合成革、橡胶、织物、发泡材料、胶水等鞋用材料的一些特定气味，不影响商品使用，不属于商品质量问题，不支持退货。<span></span>
         </p>
         <p class="MsoNormal">
         精品百货类商品的参数、图片仅供参考，服装类商品尺寸<span>1-3cm</span>误差属正常范围。平台不接受以尺寸不符为理由的退货；因个人情况不同，建议下单前自行确认清楚个人尺码信息，平台推荐尺码仅供参考，不作为退换货的理由或者依据。<span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.15pt;text-indent:-28.15pt;">
         <span></span>
         </p>
         <p class="MsoNormal" style="margin-left:28.25pt;text-indent:-28.25pt;">
         <b>【食品类商品特别提示】</b>
         </p>
         <p class="MsoNormal">
         因运输途中温度不可控因素，可能造成巧克力糖果等商品融化，平台售卖时会加以提醒，请您谨慎购买。如发生商品融化事件，不属于质量问题，不支持退货退款。<span></span>
         </p></div></div><div class="nc_btn_wrap" style="position: absolute;left: 10px;right: 10px;bottom: 10px;"><div style="font-size:12px; color:#f1641e;margin-bottom:10px;"><label id ="btn_service">&lt;&lt;服务协议&gt;&gt;</label><label id="btn_buyservice">&lt;&lt;购买须知&gt;&gt;</label></div><div style="display: flex;justify-content: center;align-items: center;"><input id ="btn_agree" type="button" value="同意并提交" class="btn" style="border-color: #f1641e;color: #fff;background-color:#f1641e;" /><input id = "btn_giveup" type="button" value="再看看" class="btn" style="margin-left: 20px;color: #6D6D6D;" /></div></div>
         */

        private int ops;
        private String html;

        public int getOps() {
            return ops;
        }

        public void setOps(int ops) {
            this.ops = ops;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }

    public static class DetailsBean {
        /**
         * abiid : 177412
         * bcode : 06123
         * ccode : 0405
         * pcode : 202201061718198210
         * num : 1
         * abname : [促销]祖·玛珑英国梨与小苍兰香氛满室幽香香薰
         * abname_en : Jo Malone London  EP&F REED DIFFUSER 165ml
         * price : 560
         * rprice : 405
         * integrate : 0
         * maxnum : 100
         * tax_type : 20
         * ischeck : 10
         * img_url : http://b2cimage.lib.cdn.srgow.com/images/177412/0_180.jpg
         * tags : ["已享受1件75折","积分立减15",""]
         * repromotions : []
         */

        private int abiid;
        private String bcode;
        private String ccode;
        private String pcode;
        private int num;
        private String abname;
        private String abname_en;
        private int price;
        private int rprice;
        private int integrate;
        private int maxnum;
        private int tax_type;
        private int ischeck;
        private String img_url;
        private List<String> tags;
        private List<?> repromotions;

        public int getAbiid() {
            return abiid;
        }

        public void setAbiid(int abiid) {
            this.abiid = abiid;
        }

        public String getBcode() {
            return bcode;
        }

        public void setBcode(String bcode) {
            this.bcode = bcode;
        }

        public String getCcode() {
            return ccode;
        }

        public void setCcode(String ccode) {
            this.ccode = ccode;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getRprice() {
            return rprice;
        }

        public void setRprice(int rprice) {
            this.rprice = rprice;
        }

        public int getIntegrate() {
            return integrate;
        }

        public void setIntegrate(int integrate) {
            this.integrate = integrate;
        }

        public int getMaxnum() {
            return maxnum;
        }

        public void setMaxnum(int maxnum) {
            this.maxnum = maxnum;
        }

        public int getTax_type() {
            return tax_type;
        }

        public void setTax_type(int tax_type) {
            this.tax_type = tax_type;
        }

        public int getIscheck() {
            return ischeck;
        }

        public void setIscheck(int ischeck) {
            this.ischeck = ischeck;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<?> getRepromotions() {
            return repromotions;
        }

        public void setRepromotions(List<?> repromotions) {
            this.repromotions = repromotions;
        }
    }

    public static class DiscountItemBean {
        /**
         * Type : 1
         * Name : null
         * Item : 商品总额
         * Amount : 1120.0
         */

        private int Type;
        private Object Name;
        private String Item;
        private double Amount;

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public Object getName() {
            return Name;
        }

        public void setName(Object Name) {
            this.Name = Name;
        }

        public String getItem() {
            return Item;
        }

        public void setItem(String Item) {
            this.Item = Item;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }
    }
}
