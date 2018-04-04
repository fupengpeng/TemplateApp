package club.zhisimina.templateapp.entity.response;

import club.zhisimina.templateapp.entity.LoginResponseData;

/**
 * 登录的响应
 */
public class LoginResponse extends BaseResponse {
    /**
     * 登录的响应数据
     */
    private LoginResponseData data;

    public LoginResponseData getData() {
        return data;
    }

    public void setData(LoginResponseData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                '}';
    }
}
