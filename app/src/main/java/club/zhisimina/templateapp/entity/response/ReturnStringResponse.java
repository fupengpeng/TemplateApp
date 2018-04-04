package club.zhisimina.templateapp.entity.response;

/**
 * 返回数据中data是String的响应的统一解析类
 */
public class ReturnStringResponse extends BaseResponse {
    // 返回的String数据
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
