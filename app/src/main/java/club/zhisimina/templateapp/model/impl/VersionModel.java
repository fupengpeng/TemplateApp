package club.zhisimina.templateapp.model.impl;

import com.alibaba.fastjson.JSON;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import club.zhisimina.templateapp.common.Consts;
import club.zhisimina.templateapp.common.DataEvent;
import club.zhisimina.templateapp.common.ObjectCallBack;
import club.zhisimina.templateapp.entity.response.NewVersionResponse;
import club.zhisimina.templateapp.model.interf.IVersionModel;
import club.zhisimina.templateapp.util.EventBusUtils;

/**
 * 版本业务
 */
public class VersionModel implements IVersionModel {
    /**
     * 请求队列
     */
    private static RequestQueue requestQueue = NoHttp.newRequestQueue(1);
    /**
     * 下载队列
     */
    private static DownloadQueue downloadQueue = NoHttp.newDownloadQueue(1);

    /**
     * 获取最新版本信息
     *
     * @param callBack 回调
     */
    @Override
    public void getNewVersionInfo(final ObjectCallBack<NewVersionResponse> callBack) {
        // 创建String请求
        Request<String> request = NoHttp.createStringRequest(Consts.NetUrl.VERSION_LOAD_NEW_INFO, RequestMethod.POST);

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
                NewVersionResponse newVersionResponse = JSON.parseObject(result, NewVersionResponse.class);
                // 判断是否成功
//                callBack.onSuccess(newVersionResponse);
                // 判断是否成功
                if (NewVersionResponse.getCode() == 0) { // 成功
                    callBack.onSuccess(newVersionResponse);
                } else { // 失败
                    Exception e = new Exception(newVersionResponse.getInfo());
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
     * 下载APK文件
     *
     * @param what 版本更新Service启动标识
     */
    @Override
    public void downloadAPK(int what, String url) {
        // 下载路径
        File file = new File(Consts.DOWNLOAD_BASE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        //下载请求
        // url 下载地址。
        // fileFolder 保存的文件夹。
        // fileName 文件名。
        // isRange 是否断点续传下载。
        // isDeleteOld 如果发现存在同名文件，是否删除后重新下载，如果不删除，则直接下载成功。
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(
                url,
                Consts.DOWNLOAD_BASE_PATH,
                Consts.APK_NAME,
                true,
                false);

        // what 区分下载。
        // downloadRequest 下载请求对象。
        // downloadListener 下载监听。
        downloadQueue.add(what, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                // 发送下载失败事件
                EventBusUtils.postEvent(Consts.DataEventType.VERSION_DOWNLOAD_ERROR, Consts.APK_NAME, what);
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                // 发送下载进度
//                EventBusUtils.postEvent(Consts.DataEventType.VERSION_DOWNLOAD_PROGRESS, 0, what);

                long count = allCount;
            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {
                // 发送下载进度
                EventBusUtils.postEvent(Consts.DataEventType.VERSION_DOWNLOAD_PROGRESS, progress, what);
            }

            @Override
            public void onFinish(int what, String filePath) {
                // 发送下载完成事件
                EventBus.getDefault().post(new DataEvent(Consts.DataEventType.VERSION_DOWNLOAD_FINISH, null, what));
            }

            @Override
            public void onCancel(int what) {

            }
        });
    }

}
