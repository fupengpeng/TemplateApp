package club.zhisimina.templateapp.model.interf;

import android.graphics.Bitmap;

import club.zhisimina.templateapp.common.ObjectCallBack;
import club.zhisimina.templateapp.entity.LoginResponseData;

/**
 * 用户业务接口
 */
public interface IUserModel {
    /**
     * 登录
     *
     * @param phoneNumber 手机号
     * @param password    密码
     * @param callBack    回调
     */
    void login(String phoneNumber, String password, final ObjectCallBack<LoginResponseData> callBack);


    /**
     * 更改密码
     *
     * @param newpwd   新密码（大于6位）
     * @param opwd     原密码
     * @param callBack 回调
     */
    void alterPassword(String newpwd, String opwd, final ObjectCallBack<String> callBack);


    /**
     * 上传图片
     *
     * @param bitmap   图片
     * @param callBack 上传成功响应
     */
    void uploadingImage(Bitmap bitmap, final ObjectCallBack<String> callBack);




}
