package club.zhisimina.templateapp.util;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * 图片工具类
 */
public class BitmapUtils {

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     *
     * @param bit 图片
     * @return Base64字符串
     */
    public static String bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return "data:image/jpg;base64,"+Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
