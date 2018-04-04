package club.zhisimina.templateapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 服务项目
 */
public class ServiceItem implements Serializable {
    // 服务ID
    private String serviceitemid;
    // 服务名称
    private String serviceitemname;
    // 服务编号
    private String hotkey;
    // 价格
    private String saleprice;
    // 缩略图
    private String imageurl;
    // 优惠折扣
    private String discount;
    // 折扣价
    private String discountprice;
    // 是否可用
    private boolean enable;
    // 服务次数
    private int num;
    // 操作状态
    private String acstate;
    // 操作人id
    private String acuid;
    // 是否可预约  0：不可预约  1：可预约
    private String appoint;
    // 可预约人员数量
    private String appointunum;
    // 可预约店员列表
    private List<ShopAssistant> appointulist;
    // 预约价
    private String appointPrice;

    // id ---- 同serviceitemid
    private String id;
    // price ---- ???
    private String price;
    // name ---- 同serviceitemname
    private String name;


    public String getServiceitemid() {
        return serviceitemid;
    }

    public void setServiceitemid(String serviceitemid) {
        this.serviceitemid = serviceitemid;
    }

    public String getServiceitemname() {
        return serviceitemname;
    }

    public void setServiceitemname(String serviceitemname) {
        this.serviceitemname = serviceitemname;
    }

    public String getHotkey() {
        return hotkey;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAcstate() {
        return acstate;
    }

    public void setAcstate(String acstate) {
        this.acstate = acstate;
    }

    public String getAcuid() {
        return acuid;
    }

    public void setAcuid(String acuid) {
        this.acuid = acuid;
    }

    public String getAppoint() {
        return appoint;
    }

    public void setAppoint(String appoint) {
        this.appoint = appoint;
    }

    public String getAppointunum() {
        return appointunum;
    }

    public void setAppointunum(String appointunum) {
        this.appointunum = appointunum;
    }

    public List<ShopAssistant> getAppointulist() {
        return appointulist;
    }

    public void setAppointulist(List<ShopAssistant> appointulist) {
        this.appointulist = appointulist;
    }

    public String getAppointPrice() {
        return appointPrice;
    }

    public void setAppointPrice(String appointPrice) {
        this.appointPrice = appointPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
