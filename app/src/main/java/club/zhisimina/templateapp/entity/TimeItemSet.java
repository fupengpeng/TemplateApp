package club.zhisimina.templateapp.entity;

import java.util.List;

/**
 * @author fupengpeng
 * @description  时段条目设置
 * @data 2018/2/5 0005 13:22
 */

public class TimeItemSet {

    // 开始时间
    private String ta;
    // 结束时间
    private String tz;
    // 预约价格基础比例
    private String pr;
    // 特别价格设置
    private List<ServiceItem> ps;

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public List<ServiceItem> getPs() {
        return ps;
    }

    public void setPs(List<ServiceItem> ps) {
        this.ps = ps;
    }
}
