package club.zhisimina.templateapp.entity;

/**
 * 门店
 */
public class Shop {
    // 门店id
    private String id;
    // 是否该用户创建，0.否，1.是
    private String boss;
    // 门店名称
    private String name;
    // 状态：0.正常，1.系统禁用
    private String state;
    // 是否试用期，0.是，1.否
    private String isprobation;
    // 使用期限
    private String closingdate;
    // 是否禁用，由老板控制，true.禁用，false.启用
    private String isenable;
    // 门店LOGO
    private String imageurl;
    // 创建者ID
    private String bid;
    // 门店大小
    private String size;
    // 开始营业时间
    private String begintime;
    // 闭店时间
    private String endtime;
    // 电话
    private String phone;
    // 省
    private String province;
    // 市
    private String city;
    // 区
    private String area;
    // 详细地址
    private String address;
    // 经度
    private String longitude;
    // 纬度
    private String latitude;
    // 备注
    private String intro;
    // 关账日
    private String accountfrozendate;
    // 是否允许跨店消费，0允许
    private String allowothercard;
    // 门头照
    private String mainimage;
    // 收银台照
    private String cashierimage;
    // 内景照
    private String interiorimage;
    // 门店类型
    private String type;
    // 主营业务
    private String businessvalue;
    // 主体信息状态
    private String authenticate_state;

    // 审计信息
    private String auditmessage;
    // 门店二维码
    private String qr;

    // 门店地址(门店切换用)
    private String shopaddress;
    // 验证状态
    private String authenticatestate;



    public Shop() {
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }

    public String getAuthenticatestate() {
        return authenticatestate;
    }

    public void setAuthenticatestate(String authenticatestate) {
        this.authenticatestate = authenticatestate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsprobation() {
        return isprobation;
    }

    public void setIsprobation(String isprobation) {
        this.isprobation = isprobation;
    }

    public String getClosingdate() {
        return closingdate;
    }

    public void setClosingdate(String closingdate) {
        this.closingdate = closingdate;
    }

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAccountfrozendate() {
        return accountfrozendate;
    }

    public void setAccountfrozendate(String accountfrozendate) {
        this.accountfrozendate = accountfrozendate;
    }

    public String getAllowothercard() {
        return allowothercard;
    }

    public void setAllowothercard(String allowothercard) {
        this.allowothercard = allowothercard;
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
    }

    public String getCashierimage() {
        return cashierimage;
    }

    public void setCashierimage(String cashierimage) {
        this.cashierimage = cashierimage;
    }

    public String getInteriorimage() {
        return interiorimage;
    }

    public void setInteriorimage(String interiorimage) {
        this.interiorimage = interiorimage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusinessvalue() {
        return businessvalue;
    }

    public void setBusinessvalue(String businessvalue) {
        this.businessvalue = businessvalue;
    }

    public String getAuthenticate_state() {
        return authenticate_state;
    }

    public void setAuthenticate_state(String authenticate_state) {
        this.authenticate_state = authenticate_state;
    }


    public String getAuditmessage() {
        return auditmessage;
    }

    public void setAuditmessage(String auditmessage) {
        this.auditmessage = auditmessage;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // 如果为同一对象的不同引用,则相同
        if (this == obj) {
            return true;
        }
        // 如果传入的对象为空,则返回false
        if (obj == null) {
            return false;
        }

        // 如果两者属于不同的类型,不能相等
        if (getClass() != obj.getClass()) {
            return false;
        }

        // 类型相同, 比较内容是否相同
        Shop other = (Shop) obj;

        if (other.id == null) {
            return false;
        }

        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", boss='" + boss + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", isprobation='" + isprobation + '\'' +
                ", closingdate='" + closingdate + '\'' +
                ", isenable='" + isenable + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", bid='" + bid + '\'' +
                ", size='" + size + '\'' +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", intro='" + intro + '\'' +
                ", accountfrozendate='" + accountfrozendate + '\'' +
                ", allowothercard='" + allowothercard + '\'' +
                ", mainimage='" + mainimage + '\'' +
                ", cashierimage='" + cashierimage + '\'' +
                ", interiorimage='" + interiorimage + '\'' +
                ", type='" + type + '\'' +
                ", businessvalue='" + businessvalue + '\'' +
                ", authenticate_state='" + authenticate_state + '\'' +

                ", auditmessage='" + auditmessage + '\'' +
                ", qr='" + qr + '\'' +
                ", shopaddress='" + shopaddress + '\'' +
                ", authenticatestate='" + authenticatestate + '\'' +
                '}';
    }
}
