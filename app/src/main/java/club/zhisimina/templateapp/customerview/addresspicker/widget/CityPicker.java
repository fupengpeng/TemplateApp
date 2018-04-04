package club.zhisimina.templateapp.customerview.addresspicker.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import club.zhisimina.templateapp.R;
import club.zhisimina.templateapp.customerview.addresspicker.model.CityModel;
import club.zhisimina.templateapp.customerview.addresspicker.model.DistrictModel;
import club.zhisimina.templateapp.customerview.addresspicker.model.ProvinceModel;
import club.zhisimina.templateapp.customerview.addresspicker.widget.wheel.OnWheelChangedListener;
import club.zhisimina.templateapp.customerview.addresspicker.widget.wheel.WheelView;
import club.zhisimina.templateapp.customerview.addresspicker.widget.wheel.adapters.ArrayWheelAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fupengpeng
 * @description 省市区三级选择
 * @date 2018/3/20 0020 15:19
 */
public class CityPicker implements CanShow, OnWheelChangedListener {

    private Context context;

    private PopupWindow popwindow;

    private View popview;

    /**
     * 省份选择栏
     */
    private WheelView mViewProvince;
    /**
     * 城市选择栏
     */
    private WheelView mViewCity;
    /**
     * 区县选择栏
     */
    private WheelView mViewDistrict;

    /**
     * 详细地址输入框
     */
    private final EditText mEtAddress;

    private RelativeLayout mRelativeTitleBg;

    /**
     * 确认按钮
     */
    private TextView mTvOK;
    /**
     * popupwindow标题
     */
    private TextView mTvTitle;
    /**
     * 取消按钮
     */
    private TextView mTvCancel;

    /**
     * 所有省
     */
    protected static String[] mProvinceDatas;

    /**
     * 所有省
     */
    protected static List<ProvinceModel> mProvinceDataList;

    /**
     * 所有市
     */
    protected static List<CityModel> mCityDataList = new ArrayList<CityModel>();

    /**
     * 所有区县
     */
    protected static List<DistrictModel> mDistrictDataList = new ArrayList<DistrictModel>();

    /**
     * key - 省 value - 市
     */
    protected static Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

    /**
     * key - 市 values - 区
     */
    protected static Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 邮编
     */
    protected static Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    protected static String mCurrentProviceName;

    /**
     * 当前省
     */
    protected static ProvinceModel mCurrentProvice;

    /**
     * 当前市的名称
     */
    protected static String mCurrentCityName;

    /**
     * 当前市
     */
    protected static CityModel mCurrentCity;

    /**
     * 当前区的名称
     */
    protected static String mCurrentDistrictName = "";

    /**
     * 当前区
     */
    protected DistrictModel mCurrentDistrict;

    /**
     * 当前区的邮政编码
     */
    protected static String mCurrentZipCode = "";


    private OnCityItemClickListener listener;

    private final RelativeLayout mRelativeLayout;
    /**
     * 区县id
     */
    private String mCurrentDistrictId;
    /**
     * 省份id
     */
    private String mCurrentProviceId = null;
    /**
     * 城市id
     */
    private String mCurrentCityId = null;

    /**
     * 选择栏变化监听
     */
    public interface OnCityItemClickListener {
        void onSelected(String... citySelected);

        void onSelectedId(String... id);
    }

    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Default text color
     */
    public static final int DEFAULT_TEXT_COLOR = 0xFF585858;

    /**
     * Default text size
     */
    public static final int DEFAULT_TEXT_SIZE = 18;

    // Text settings
    private int textColor = DEFAULT_TEXT_COLOR;

    private int textSize = DEFAULT_TEXT_SIZE;
    private String textAddress = null;

    /**
     * 滚轮显示的item个数
     */
    private static final int DEF_VISIBLE_ITEMS = 5;

    // Count of visible items
    private int visibleItems = DEF_VISIBLE_ITEMS;

    /**
     * 省滚轮是否循环滚动
     */
    private boolean isProvinceCyclic = true;

    /**
     * 市滚轮是否循环滚动
     */
    private boolean isCityCyclic = true;

    /**
     * 区滚轮是否循环滚动
     */
    private boolean isDistrictCyclic = true;

    /**
     * item间距
     */
    private int padding = 5;


    /**
     * Color.BLACK
     */
    private String cancelTextColorStr = "#000000";


    /**
     * Color.BLUE
     */
    private String confirmTextColorStr = "#0000FF";

    /**
     * 标题背景颜色
     */
    private String titleBackgroundColorStr = "#E9E9E9";

    /**
     * 第一次默认的显示省份，一般配合定位，使用
     */
    private String defaultProvinceName = "江苏";

    /**
     * 第一次默认得显示城市，一般配合定位，使用
     */
    private String defaultCityName = "常州";

    /**
     * 第一次默认得显示，一般配合定位，使用
     */
    private String defaultDistrictName = "新北区";

    /**
     * 两级联动
     */
    private boolean showProvinceAndCity = false;

    /**
     * 标题
     */
    private String mTitle = "选择地区";


    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            popwindow.setHeight(h);
        }
        popwindow.showAsDropDown(anchor);
    }


    /**
     * 构造方法
     *
     * @param builder
     */
    private CityPicker(Builder builder) {
        this.textColor = builder.textColor;
        this.textSize = builder.textSize;
        this.textAddress = builder.textAddress;
        this.visibleItems = builder.visibleItems;
        this.isProvinceCyclic = builder.isProvinceCyclic;
        this.isDistrictCyclic = builder.isDistrictCyclic;
        this.isCityCyclic = builder.isCityCyclic;
        this.context = builder.mContext;
        this.padding = builder.padding;
        this.mTitle = builder.mTitle;
        this.titleBackgroundColorStr = builder.titleBackgroundColorStr;
        this.confirmTextColorStr = builder.confirmTextColorStr;
        this.cancelTextColorStr = builder.cancelTextColorStr;

        this.defaultDistrictName = builder.defaultDistrictName;
        this.defaultCityName = builder.defaultCityName;
        this.defaultProvinceName = builder.defaultProvinceName;

        this.showProvinceAndCity = builder.showProvinceAndCity;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        popview = layoutInflater.inflate(R.layout.pop_citypicker, null);

        mRelativeLayout = (RelativeLayout) popview.findViewById(R.id.ll_root);
        mViewProvince = (WheelView) popview.findViewById(R.id.id_province);
        mViewCity = (WheelView) popview.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) popview.findViewById(R.id.id_district);
        mEtAddress = (EditText) popview.findViewById(R.id.et_address);
        mRelativeTitleBg = (RelativeLayout) popview.findViewById(R.id.rl_title);
        mTvOK = (TextView) popview.findViewById(R.id.tv_confirm);
        mTvTitle = (TextView) popview.findViewById(R.id.tv_title);
        mTvCancel = (TextView) popview.findViewById(R.id.tv_cancel);


        popwindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popwindow.setBackgroundDrawable(new ColorDrawable(0x80000000));
        popwindow.setAnimationStyle(R.style.AnimBottom);
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);

        // TODO: 2018/3/20 0020 标题栏设置
//        popwindow.showAsDropDown(mRelativeLayout);

        //popview添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        popview.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int height = popview.findViewById(R.id.ll_title_background).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        popwindow.dismiss();
                    }
                }
                return true;
            }
        });


        /**
         * 设置标题背景颜色
         */
        if (!TextUtils.isEmpty(this.titleBackgroundColorStr)) {
            mRelativeTitleBg.setBackgroundColor(Color.parseColor(this.titleBackgroundColorStr));
        }

        /**
         * 设置标题
         */
        if (!TextUtils.isEmpty(this.mTitle)) {
            mTvTitle.setText(this.mTitle);
        }

        //设置确认按钮文字颜色
        if (!TextUtils.isEmpty(this.confirmTextColorStr)) {
            mTvOK.setTextColor(Color.parseColor(this.confirmTextColorStr));
        }

        //设置取消按钮文字颜色
        if (!TextUtils.isEmpty(this.cancelTextColorStr)) {
            mTvCancel.setTextColor(Color.parseColor(this.cancelTextColorStr));
        }


        //只显示省市两级联动
        if (this.showProvinceAndCity) {
            mViewDistrict.setVisibility(View.GONE);
        } else {
            mViewDistrict.setVisibility(View.VISIBLE);
        }

        //初始化城市数据
        initProvinceDatas(context);

        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        mTvOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String address = mEtAddress.getText().toString().trim();
                // 根据输入地址名获取地址id
                getAddressId();
                if (!TextUtils.isEmpty(address) && address.length() > 0) {
                    if (showProvinceAndCity) {
                        listener.onSelected(mCurrentProviceName, mCurrentCityName, "", mCurrentZipCode, address);
                        listener.onSelectedId(mCurrentProviceId, mCurrentCityId, null);

                    } else {
                        listener.onSelected(mCurrentProviceName, mCurrentCityName, mCurrentDistrictName, mCurrentZipCode, address);

                        listener.onSelectedId(mCurrentProviceId, mCurrentCityId, mCurrentDistrictId);
                    }
                    hide();
                } else {
                    Toast.makeText(context, "请输入详细地址！", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    private void getAddressId() {
        // TODO: 2018/3/20 0020 省市区转码
        List<ProvinceModel> provinceList = mProvinceDataList;
        if (!TextUtils.isEmpty(mCurrentProviceName) && provinceList.size() > 0) {
            for (ProvinceModel p : provinceList) {
                if (p.getName().equals(mCurrentProviceName)) {
                    mCurrentProviceId = p.getId();
                }
            }

        }
        List<CityModel> cityList = mCityDataList;
        if (!TextUtils.isEmpty(mCurrentCityName) && cityList.size() > 0) {
            for (CityModel c : cityList) {
                if (c.getName().equals(mCurrentCityName)) {
                    mCurrentCityId = c.getId();
                }
            }

        }
        List<DistrictModel> districtList = mDistrictDataList;
        if (!TextUtils.isEmpty(mCurrentDistrictName) && districtList.size() > 0) {
            for (DistrictModel d : districtList) {
                if (d.getName().equals(mCurrentDistrictName)) {
                    mCurrentDistrictId = d.getId();
                }
            }
        }
    }

    public static String getProvinceName(String provinceId){
        String provinceName = null;
        List<ProvinceModel> provinceList = mProvinceDataList;
        if (!TextUtils.isEmpty(provinceId) && !"0".equals(provinceId) && provinceList.size() > 0) {
            for (ProvinceModel p : provinceList) {
                if (p.getId().equals(provinceId)) {
                    provinceName = p.getName();
                }
            }

        }
        return provinceName;
    }
    public static String getCityName(String cityId){
        String cityName = null;
        List<CityModel> cityList = mCityDataList;
        if (!TextUtils.isEmpty(cityId) && !"0".equals(cityId) && cityList.size() > 0) {
            for (CityModel c : cityList) {
                if (c.getId().equals(cityId)) {
                    cityName = c.getName();
                }
            }

        }
        return cityName;
    }
    public static String getDistrictIdName(String districtId){
        String districtName = null;
        List<DistrictModel> districtList = mDistrictDataList;
        if (!TextUtils.isEmpty(districtId) && !"0".equals(districtId) && districtList.size() > 0) {
            for (DistrictModel d : districtList) {
                if (d.getId().equals(districtId)) {
                    districtName = d.getName();
                }
            }
        }
        return districtName;
    }

    public void getAddressName() {
        // TODO: 2018/3/20 0020 省市区转码
        List<ProvinceModel> provinceList = mProvinceDataList;
        if (!TextUtils.isEmpty(mCurrentProviceId) && provinceList.size() > 0) {
            for (ProvinceModel p : provinceList) {
                if (p.getId().equals(mCurrentProviceId)) {
                    mCurrentProviceName = p.getName();
                }
            }
        }
        List<CityModel> cityList = mCityDataList;
        if (!TextUtils.isEmpty(mCurrentCityId) && cityList.size() > 0) {
            for (CityModel c : cityList) {
                if (c.getId().equals(mCurrentCityId)) {
                    mCurrentCityName = c.getName();
                }
            }

        }
        List<DistrictModel> districtList = mDistrictDataList;
        if (!TextUtils.isEmpty(mCurrentDistrictId) && districtList.size() > 0) {
            for (DistrictModel d : districtList) {
                if (d.getId().equals(mCurrentDistrictId)) {
                    mCurrentDistrictName = d.getName();
                }
            }
        }
    }

    /**
     *
     */
    public static class Builder {
        /**
         * Default text color
         */
        public static final int DEFAULT_TEXT_COLOR = 0xFF585858;

        /**
         * Default text size
         */
        public static final int DEFAULT_TEXT_SIZE = 18;

        // Text settings
        private int textColor = DEFAULT_TEXT_COLOR;

        private int textSize = DEFAULT_TEXT_SIZE;
        private String textAddress = null;

        /**
         * 滚轮显示的item个数
         */
        private static final int DEF_VISIBLE_ITEMS = 5;

        // Count of visible items
        private int visibleItems = DEF_VISIBLE_ITEMS;

        /**
         * 省滚轮是否循环滚动
         */
        private boolean isProvinceCyclic = true;

        /**
         * 市滚轮是否循环滚动
         */
        private boolean isCityCyclic = true;

        /**
         * 区滚轮是否循环滚动
         */
        private boolean isDistrictCyclic = true;

        private Context mContext;

        /**
         * item间距
         */
        private int padding = 5;


        /**
         * Color.BLACK
         */
        private String cancelTextColorStr = "#000000";


        /**
         * Color.BLUE
         */
        private String confirmTextColorStr = "#0000FF";

        /**
         * 标题背景颜色
         */
        private String titleBackgroundColorStr = "#E9E9E9";

        /**
         * 第一次默认的显示省份，一般配合定位，使用
         */
        private String defaultProvinceName = "江苏";

        /**
         * 第一次默认得显示城市，一般配合定位，使用
         */
        private String defaultCityName = "常州";

        /**
         * 第一次默认得显示，一般配合定位，使用
         */
        private String defaultDistrictName = "新北区";

        /**
         * 标题
         */
        private String mTitle = "选择地区";

        /**
         * 两级联动
         */
        private boolean showProvinceAndCity = false;

        public Builder(Context context) {
            this.mContext = context;
        }

        /**
         * 设置标题背景颜色
         *
         * @param colorBg
         * @return
         */
        public Builder titleBackgroundColor(String colorBg) {
            this.titleBackgroundColorStr = colorBg;
            return this;
        }

        /**
         * 设置标题
         *
         * @param mtitle
         * @return
         */
        public Builder title(String mtitle) {
            this.mTitle = mtitle;
            return this;
        }

        /**
         * 是否只显示省市两级联动
         *
         * @param flag
         * @return
         */
        public Builder onlyShowProvinceAndCity(boolean flag) {
            this.showProvinceAndCity = flag;
            return this;
        }

        /**
         * 第一次默认的显示省份，一般配合定位，使用
         *
         * @param defaultProvinceName
         * @return
         */
        public Builder province(String defaultProvinceName) {
            this.defaultProvinceName = defaultProvinceName;
            return this;
        }

        /**
         * 第一次默认得显示城市，一般配合定位，使用
         *
         * @param defaultCityName
         * @return
         */
        public Builder city(String defaultCityName) {
            this.defaultCityName = defaultCityName;
            return this;
        }

        /**
         * 第一次默认地区显示，一般配合定位，使用
         *
         * @param defaultDistrictName
         * @return
         */
        public Builder district(String defaultDistrictName) {
            this.defaultDistrictName = defaultDistrictName;
            return this;
        }

        //        /**
        //         * 确认按钮文字颜色
        //         * @param color
        //         * @return
        //         */
        //        public Builder confirTextColor(int color) {
        //            this.confirmTextColor = color;
        //            return this;
        //        }

        /**
         * 确认按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder confirTextColor(String color) {
            this.confirmTextColorStr = color;
            return this;
        }

        //        /**
        //         * 取消按钮文字颜色
        //         * @param color
        //         * @return
        //         */
        //        public Builder cancelTextColor(int color) {
        //            this.cancelTextColor = color;
        //            return this;
        //        }

        /**
         * 取消按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder cancelTextColor(String color) {
            this.cancelTextColorStr = color;
            return this;
        }

        /**
         * item文字颜色
         *
         * @param textColor
         * @return
         */
        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        /**
         * item文字大小
         *
         * @param textSize
         * @return
         */
        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }
        /**
         * EditText详细地址
         *
         * @param textAddress
         * @return
         */
        public Builder textAddress(String textAddress) {
            this.textAddress = textAddress;
            return this;
        }

        /**
         * 滚轮显示的item个数
         *
         * @param visibleItems
         * @return
         */
        public Builder visibleItemsCount(int visibleItems) {
            this.visibleItems = visibleItems;
            return this;
        }

        /**
         * 省滚轮是否循环滚动
         *
         * @param isProvinceCyclic
         * @return
         */
        public Builder provinceCyclic(boolean isProvinceCyclic) {
            this.isProvinceCyclic = isProvinceCyclic;
            return this;
        }

        /**
         * 市滚轮是否循环滚动
         *
         * @param isCityCyclic
         * @return
         */
        public Builder cityCyclic(boolean isCityCyclic) {
            this.isCityCyclic = isCityCyclic;
            return this;
        }

        /**
         * 区滚轮是否循环滚动
         *
         * @param isDistrictCyclic
         * @return
         */
        public Builder districtCyclic(boolean isDistrictCyclic) {
            this.isDistrictCyclic = isDistrictCyclic;
            return this;
        }

        /**
         * item间距
         *
         * @param itemPadding
         * @return
         */
        public Builder itemPadding(int itemPadding) {
            this.padding = itemPadding;
            return this;
        }

        public CityPicker build() {
            CityPicker cityPicker = new CityPicker(this);
            return cityPicker;
        }

    }


    /**
     * 设置默认展示的省市区县
     * 根据设置的默认省份直接定位到该省份
     */
    private void setUpData() {
        int provinceDefault = -1;
        if (!TextUtils.isEmpty(defaultProvinceName) &&
                mProvinceDatas.length > 0) {
            for (int i = 0; i < mProvinceDatas.length; i++) {
                if (mProvinceDatas[i].contains(defaultProvinceName)) {
                    provinceDefault = i;
                    break;
                }
            }
        }
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<String>(context, mProvinceDatas);
        mViewProvince.setViewAdapter(arrayWheelAdapter);
        //获取所设置的省的位置，直接定位到该位置
        if (-1 != provinceDefault) {
            mViewProvince.setCurrentItem(provinceDefault);
        }
        // 设置可见条目数量
        mViewProvince.setVisibleItems(visibleItems);
        mViewCity.setVisibleItems(visibleItems);
        mViewDistrict.setVisibleItems(visibleItems);
        mViewProvince.setCyclic(isProvinceCyclic);
        mViewCity.setCyclic(isCityCyclic);
        mViewDistrict.setCyclic(isDistrictCyclic);
        arrayWheelAdapter.setPadding(padding);
        arrayWheelAdapter.setTextColor(textColor);
        arrayWheelAdapter.setTextSize(textSize);
        mEtAddress.setText(textAddress);

        updateCities();
        updateAreas();
    }

    public static String getJson(Context context) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager asset = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(asset.open("simple_cities_pro_city_dis.json")));

            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
                Log.d("AAA", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 解析省市区的XML数据
     */

    public  static void initProvinceDatas(Context context) {
        List<ProvinceModel> provinceList = null;
//        AssetManager asset = context.getAssets();
        try {
//            InputStream input = asset.open("province_data.xml");
//            // 创建一个解析xml的工厂对象
//            SAXParserFactory spf = SAXParserFactory.newInstance();
//            // 解析xml
//            SAXParser parser = spf.newSAXParser();
//            XmlParserHandler handler = new XmlParserHandler();
//            parser.parse(input, handler);
//            input.close();
//            // 获取解析出来的数据
//            provinceList = handler.getDataList();

            provinceList = JSON.parseArray(
                    getJson(context),
                    ProvinceModel.class);


            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                mCurrentProvice = provinceList.get(0);
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    mCurrentCity = cityList.get(0);
                    List<DistrictModel> districtList = cityList.get(0).getCityList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            mProvinceDataList = provinceList;
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                mCityDataList.addAll(cityList);
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getCityList();
                    mDistrictDataList.addAll(districtList);
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(),
                                districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }

        int districtDefault = -1;
        if (!TextUtils.isEmpty(defaultDistrictName) && areas.length > 0) {
            for (int i = 0; i < areas.length; i++) {
                if (areas[i].contains(defaultDistrictName)) {
                    districtDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter districtWheel = new ArrayWheelAdapter<String>(context, areas);
        // 设置可见条目数量
        districtWheel.setTextColor(textColor);
        districtWheel.setTextSize(textSize);
        mViewDistrict.setViewAdapter(districtWheel);
        if (-1 != districtDefault) {
            mViewDistrict.setCurrentItem(districtDefault);
            //获取默认设置的区
            mCurrentDistrictName = defaultDistrictName;
        } else {
            mViewDistrict.setCurrentItem(0);
            //获取第一个区名称
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];

        }
        districtWheel.setPadding(padding);
        //获取第一个区名称
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }

        int cityDefault = -1;
        if (!TextUtils.isEmpty(defaultCityName) && cities.length > 0) {
            for (int i = 0; i < cities.length; i++) {
                if (cities[i].contains(defaultCityName)) {
                    cityDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter cityWheel = new ArrayWheelAdapter<String>(context, cities);
        // 设置可见条目数量
        cityWheel.setTextColor(textColor);
        cityWheel.setTextSize(textSize);
        mViewCity.setViewAdapter(cityWheel);
        if (-1 != cityDefault) {
            mViewCity.setCurrentItem(cityDefault);
        } else {
            mViewCity.setCurrentItem(0);
        }

        cityWheel.setPadding(padding);
        updateAreas();
    }

    @Override
    public void setType(int type) {
    }

    @Override
    public void show() {
        if (!isShow()) {
            setUpData();
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void hide() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }

    @Override
    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {

            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public PopupWindow getPopwindow() {
        return popwindow;
    }

    public void setPopwindow(PopupWindow popwindow) {
        this.popwindow = popwindow;
    }

    public List<ProvinceModel> getmProvinceDataList() {
        return mProvinceDataList;
    }

    public void setmProvinceDataList(List<ProvinceModel> mProvinceDataList) {
        this.mProvinceDataList = mProvinceDataList;
    }

    public List<CityModel> getmCityDataList() {
        return mCityDataList;
    }

    public void setmCityDataList(List<CityModel> mCityDataList) {
        this.mCityDataList = mCityDataList;
    }

    public List<DistrictModel> getmDistrictDataList() {
        return mDistrictDataList;
    }

    public void setmDistrictDataList(List<DistrictModel> mDistrictDataList) {
        this.mDistrictDataList = mDistrictDataList;
    }
}
