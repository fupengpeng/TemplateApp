package club.zhisimina.templateapp.model.impl;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import club.zhisimina.templateapp.application.MyApplication;
import club.zhisimina.templateapp.common.Consts;
import club.zhisimina.templateapp.common.ObjectCallBack;
import club.zhisimina.templateapp.entity.LoginResponseData;
import club.zhisimina.templateapp.entity.response.LoginResponse;
import club.zhisimina.templateapp.entity.response.ReturnStringResponse;
import club.zhisimina.templateapp.model.interf.IUserModel;
import club.zhisimina.templateapp.util.BitmapUtils;
import club.zhisimina.templateapp.util.DateUtils;
import club.zhisimina.templateapp.util.LogUtils;
import club.zhisimina.templateapp.util.MD5Util;
import club.zhisimina.templateapp.util.SPUtils;

/**
 * 用户业务
 */
public class UserModel implements IUserModel {
    /**
     * 请求队列
     */
    private static RequestQueue requestQueue = NoHttp.newRequestQueue(3);

    /**
     * 登录
     *
     * @param phoneNumber 手机号
     * @param password    密码
     * @param callBack    回调
     */
    @Override
    public void login(String phoneNumber, String password,
                      final ObjectCallBack<LoginResponseData> callBack) {
        // 创建String请求
        Request<String> request = NoHttp.createStringRequest(Consts.NetUrl.BASE_NET_URL, Consts.NET_REQUEST_METHOD);

        // 添加参数
        request.add("a", "login");
        request.add("shopId", "x");
        request.add("userId", "x");
        request.add("sessionId", "x");
        request.add("token", MD5Util.getMD5("xxxlogin<*?hello.Kaiyuan.com//" + DateUtils.getCurrDate()));
        request.add("mobile", phoneNumber);
        request.add("pwd", password);

        //将request加入requestQueue
        requestQueue.add(0, request, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                // 服务器响应码。
                int responseCode = response.getHeaders().getResponseCode();
                // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                if (responseCode != 200) {
                    // 请求失败
                    Exception e = new Exception(Consts.NET_REQUEST_EXCEPTION);
                    callBack.onFail(e);
                    return;
                }

                // 响应结果。
                String result = response.get();
                // 解析
                LoginResponse loginResponse = JSON.parseObject(result, LoginResponse.class);
                LogUtils.e("登录数据 = " + result);
                // 判断是否成功
                if (loginResponse.getCode() == 0) { // 成功
                    callBack.onSuccess(loginResponse.getData());
                } else { // 失败
                    Exception e = new Exception(loginResponse.getInfo());
                    callBack.onFail(e);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                // 请求失败
                Exception e = new Exception(Consts.NET_REQUEST_EXCEPTION);
                callBack.onFail(e);
            }
        });
    }

    /**
     * 更改密码
     *
     * @param newpwd   新密码（大于6位）
     * @param opwd     原密码
     * @param callBack 回调
     */
    @Override
    public void alterPassword(String newpwd , String opwd, final ObjectCallBack<String> callBack) {

        // 创建String请求
        Request<String> request = NoHttp.createStringRequest(Consts.NetUrl.BASE_NET_URL, Consts.NET_REQUEST_METHOD);

        // 添加参数
        String a = "userpwd";
        request.add("a", a);
        request.add("newpwd", newpwd);
        request.add("opwd", opwd);

        // 请求的公共参数
        requestCommonPerm(a, request);

        //将request加入requestQueue
        requestQueue.add(0, request, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                // 服务器响应码。
                int responseCode = response.getHeaders().getResponseCode();
                // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                if (responseCode != 200) {
                    // 请求失败
                    Exception e = new Exception(Consts.NET_REQUEST_EXCEPTION);
                    callBack.onFail(e);
                    return;
                }

                // 响应结果。
                String result = response.get();
                LogUtils.e("更改密码 = " + result);
                // 解析
                ReturnStringResponse stringResponse = JSON.parseObject(result, ReturnStringResponse.class);
                // 判断是否成功
                if (stringResponse.getCode() == 0 || stringResponse.getCode() == 2) { // 成功
                    callBack.onSuccess(stringResponse.getInfo());
                } else { // 失败
                    Exception e = new Exception(stringResponse.getInfo());
                    callBack.onFail(e);

                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                // 请求失败
                Exception e = new Exception(Consts.NET_REQUEST_EXCEPTION);
                callBack.onFail(e);
            }
        });


    }

    /**
     * 上传图片
     *
     * @param bitmap   图片
     * @param callBack 上传成功响应
     */
    public void uploadingImage(Bitmap bitmap, final ObjectCallBack<String> callBack) {
        // 创建String请求
        Request<String> request = NoHttp.createStringRequest(Consts.NetUrl.BASE_NET_URL, Consts.NET_REQUEST_METHOD);

        // 添加参数
        String a = "upfile";
        request.add("a", a);
        request.add("imagecontent", BitmapUtils.bitmap2StrByBase64(bitmap));
        LogUtils.e("" + BitmapUtils.bitmap2StrByBase64(bitmap));
        // 请求的公共参数
        requestCommonPerm(a, request);

        //将request加入requestQueue
        requestQueue.add(0, request, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                // 服务器响应码。
                int responseCode = response.getHeaders().getResponseCode();
                // 这里一定要判断状态码，经测试，404错误时也走这个回调方法
                if (responseCode != 200) {
                    // 请求失败
                    Exception e = new Exception(Consts.NET_REQUEST_EXCEPTION);
                    callBack.onFail(e);
                    return;
                }

                // 响应结果。
                String result = response.get();
                // 解析
                ReturnStringResponse stringResponse = JSON.parseObject(result, ReturnStringResponse.class);
                // 判断是否成功
                if (stringResponse.getCode() == 0) { // 成功
                    callBack.onSuccess(stringResponse.getData());
                } else { // 失败
                    Exception e = new Exception(stringResponse.getInfo());
                    callBack.onFail(e);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                // 请求失败
                Exception e = new Exception(Consts.NET_REQUEST_EXCEPTION);
                callBack.onFail(e);
            }
        });
    }



    /**
     * 请求的公共参数
     *
     * @param a       操作
     * @param request 请求
     */
    private void requestCommonPerm(String a, Request<String> request) {
        // 获取公共数据
        String userId = MyApplication.getInstance().getUser().getId();
        String sessionId = (String) SPUtils.get(MyApplication.getInstance(), Consts.SESSION, "x");
        String shopId = MyApplication.getInstance().getCurrShop().getId();

        request.add("shopId", shopId);
        request.add("userId", userId);
        request.add("sessionId", sessionId);
        request.add("token", MD5Util.getMD5(sessionId + shopId + userId + a
                + "<*?hello.Kaiyuan.com//" + DateUtils.getCurrDate()));
    }

}
