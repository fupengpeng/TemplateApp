package club.zhisimina.templateapp.entity.response;

/**
 * 获取最新版本信息的响应
 */
public class NewVersionResponse {
    //版本号
    String version;
    //最新版本地址
    String update;
    static int code;
    private String info;
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public static int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
//    /**
//     * id
//     */
//    private String id;
//    /**
//     * 版本名
//     */
//    private String verName;
//    /**
//     * 版本号
//     */
//    private String verCode;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getVerName() {
//        return verName;
//    }
//
//    public void setVerName(String verName) {
//        this.verName = verName;
//    }
//
//    public String getVerCode() {
//        return verCode;
//    }
//
//    public void setVerCode(String verCode) {
//        this.verCode = verCode;
//    }
}
