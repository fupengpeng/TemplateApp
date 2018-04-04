package club.zhisimina.templateapp.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.zhisimina.templateapp.R;
import club.zhisimina.templateapp.util.LogUtils;




/**
 * @author fupengpeng
 * @description 全部服务单
 * @date 2017/12/21 0021 8:59
 */

public class AllFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_all);

        LogUtils.e("------------------------全部----------------------------");

        return view;
    }









//    /**
//     * 时间格式
//     */
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
//
//    /**
//     * 当前页
//     */
//    private int currentPage;
//
//    /**
//     * 总页数
//     */
//    private int totalPage;
//
//
//    /**
//     * 数据源
//     */
//    List<Order> list;
//
//    /**
//     * Adapter
//     */
//    private ServiceOrderAllFragmentAdapter serviceOrderAllFragmentAdapter;
//
//    /**
//     * 空数据提示
//     */
//    @BindView(R.id.tv_data_empty)
//    TextView tvDataEmpty;
//
//    /**
//     * 全部服务单列表
//     */
//    @BindView(R.id.lv_data_list)
//    PullToRefreshListView lvDataList;
//
//    /**
//     * 主导器
//     */
//    private IAllViewPresenter presenter;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = super.onCreateView(inflater, container, savedInstanceState, R.layout.fragment_all);
//        //主导器
//        presenter = AllViewPresenterFactory.newInstance(this);
//
//        // 初始化
//        init();
//        // 设置监听器
//        setListeners();
//        return view;
//    }
//
//
//    /**
//     * 根据数据对象设置适配器数据
//     *
//     * @return
//     */
//    private List<Order> getData() {
//        List<Order> orders = new ArrayList<Order>();
//        for (int i = 0; i < 10; i++) {
//            Order order = new Order();
//            order.setOrderId("" + i);
//            order.setOrderId("1001");
//            order.setOrderNumber("" + i);
//            order.setOrderStatus("进行中----全部");
//            order.setOrderDate(formatter.format(new Date()));
//            order.setOrderUserStatus("不是会员");
//            order.setOrderUserName("张三");
//            order.setOrderUserPhoneNumber("1234567894");
//            order.setOrderUserPic("a;sldkfjal");
//            order.setOrderServiceItemName("洗护");
//            order.setOrderWaiterName("服务员" + i);
//            order.setOrderServiceItemStatus("服务中");
//            order.setOrderServiceTime("25:25");
//            orders.add(order);
//        }
//        return orders;
//    }
//
//    /**
//     * 初始化
//     */
//    private void init() {
//        // 绑定数据
//        bindData();
//        // 设置加载模式
//        PullToRefreshUtils.setPullBoth(lvDataList);
//        // 刷新数据
//        refreshData(1);
//    }
//
//    /**
//     * 设置监听器
//     */
//    private void setListeners() {
//        // 刷新监听器
//        lvDataList.setOnRefreshListener(new InnerOnRefreshListener2());
//        // 子项点击监听器
//        lvDataList.setOnItemClickListener(new InnerOnItemClickListener());
//    }
//
//
//    /**
//     * 刷新监听器
//     */
//    private class InnerOnRefreshListener2 implements PullToRefreshBase.OnRefreshListener2 {
//        @Override
//        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
//            // 下拉刷新
//            refreshData(1);
//            List<Order> orderX = new ArrayList<Order>();
//            for (int i = 0; i < 5; i++) {
//                Order order = new Order();
//                order.setOrderId("" + i);
//                order.setOrderId("1001");
//                order.setOrderNumber("" + i);
//                order.setOrderStatus("进行中");
//                order.setOrderDate(formatter.format(new Date()));
//                order.setOrderUserStatus("不是会员");
//                order.setOrderUserName("张三"  + "----下拉刷新");
//                order.setOrderUserPhoneNumber("1234567894");
//                order.setOrderUserPic("a;sldkfjal");
//                order.setOrderServiceItemName("洗护");
//                order.setOrderWaiterName("服务员" + i);
//                order.setOrderServiceItemStatus("服务中");
//                order.setOrderServiceTime("25:25");
//                orderX.add(order);
//            }
//
//            serviceOrderAllFragmentAdapter.addFirst(orderX);
//            new FinishRefresh().execute();
//            serviceOrderAllFragmentAdapter.notifyDataSetChanged();
//        }
//
//        @Override
//        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
//            totalPage = 5;
//            // 判断是否还有更多数据
//            if (currentPage >= totalPage) {
//                Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_SHORT).show();
//                // 加载完成
//                PullToRefreshUtils.refreshComplete(lvDataList);
//                return;
//            }
//            // 上拉加载
//            currentPage++;
//            refreshData(currentPage);
//
//            List<Order> orderS = new ArrayList<Order>();
//            for (int i = 0; i < 5; i++) {
//                Order order = new Order();
//                order.setOrderId("" + i);
//                order.setOrderId("1001");
//                order.setOrderNumber("" + i);
//                order.setOrderStatus("进行中");
//                order.setOrderDate(formatter.format(new Date()));
//                order.setOrderUserStatus("不是会员");
//                order.setOrderUserName("张三"  + "----上拉加载");
//                order.setOrderUserPhoneNumber("1234567894");
//                order.setOrderUserPic("a;sldkfjal");
//                order.setOrderServiceItemName("洗护");
//                order.setOrderWaiterName("服务员" + i);
//                order.setOrderServiceItemStatus("服务中");
//                order.setOrderServiceTime("25:25");
//                orderS.add(order);
//            }
//            serviceOrderAllFragmentAdapter.addLast(orderS);
//            new FinishRefresh().execute();
//            serviceOrderAllFragmentAdapter.notifyDataSetChanged();
//        }
//    }
//
//    /**
//     * 子项点击监听器
//     */
//    public class InnerOnItemClickListener implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            // 数据源当前索引
//            int pos = position - 1;
//
//        }
//    }
//
//    /**
//     * 绑定数据
//     */
//    private void bindData() {
//        list = getData();
//        if (list == null) {
//            lvDataList.setVisibility(View.GONE);
//        } else {
//            serviceOrderAllFragmentAdapter = new ServiceOrderAllFragmentAdapter(getActivity(), list);
//            lvDataList.setAdapter(serviceOrderAllFragmentAdapter);
//        }
//
//    }
//
//    /**
//     * 刷新数据
//     */
//    private void refreshData(int page) {
//        currentPage = page;
//    }
//
//    /**
//     * 异步任务，模仿网络请求
//     */
//    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... params) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            serviceOrderAllFragmentAdapter.notifyDataSetChanged();
//            lvDataList.onRefreshComplete();
//        }
//    }
//
//
//    static class ServiceOrderAllFragmentAdapter extends BaseAdapter {
//        public static final String TAG = "OrderCenterAllFragmentAdapter";
//
//        private LayoutInflater mInflater = null;
//        private Context context;
//        private List<Order> data;
//
//        public ServiceOrderAllFragmentAdapter(Context context, List<Order> data) {
//            //根据context上下文加载布局，这里的是Activity本身，即this
//            this.mInflater = LayoutInflater.from(context);
//            this.context = context;
//            this.data = data;
//        }
//
//        /**
//         * 下拉刷新加载数据
//         *
//         * @param list
//         */
//        public void addFirst(List<Order> list) {
//            for (int i = 0; i < list.size(); i++) {
//                data.add(0, list.get(i));
//            }
//        }
//
//        /**
//         * 上拉加载加载数据
//         *
//         * @param list
//         */
//        public void addLast(List<Order> list) {
//            Log.e(TAG, "addLast: " + "----008");
//            for (int i = 0; i < list.size(); i++) {
//                data.add(list.get(i));
//            }
//        }
//
//        @Override
//        public int getCount() {
//            //在此适配器中所代表的数据集中的条目数
//            return data.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            //获取数据集中与指定索引对应的数据项
//            return data.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            //获取在列表中与指定索引对应的行id
//            return position;
//        }
//
//        //获取一个在数据集中指定索引的视图来显示数据
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder = null;
//            //如果缓存convertView为空，则需要创建View
//            if (convertView == null) {
//                //根据自定义的Item布局加载布局
//                convertView = mInflater.inflate(R.layout.list_view_item_service_order, null);
//
//                viewHolder = new ViewHolder(convertView);
//                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//
//            viewHolder.tvLvItemServiceOrderNumber.setText(data.get(position).getOrderNumber());
//            viewHolder.tvLvItemServiceOrderStatus.setText(data.get(position).getOrderStatus());
//            viewHolder.tvLvItemServiceOrderDate.setText(data.get(position).getOrderDate());
//            viewHolder.ivLvItemServiceOrderUserPic.setImageResource(R.drawable.ic_account_circle_black_24dp);
//            viewHolder.tvLvItemServiceOrderUserName.setText(data.get(position).getOrderUserName());
//            viewHolder.tvLvItemServiceOrderUserPhoneNumber.setText(data.get(position).getOrderUserPhoneNumber());
//            if (data.get(position).getOrderUserStatus().equals("true")){
//                viewHolder.ivLvItemServiceOrderDial.setImageResource(R.drawable.task_call_mobile);
//                viewHolder.tvLvItemServiceOrderDial.setText("拨打电话");
//            }else {
//                viewHolder.ivLvItemServiceOrderDial.setImageResource(R.drawable.task_vip_tag);
//                viewHolder.tvLvItemServiceOrderDial.setText("转为会员");
//            }
//            viewHolder.tvLvItemServiceOrderItemName.setText(data.get(position).getOrderServiceItemName());
//            viewHolder.tvLvItemServiceOrderWaiterName.setText(data.get(position).getOrderWaiterName());
//            viewHolder.tvLvItemServiceOrderServiceStatus.setText(data.get(position).getOrderServiceItemStatus());
//            viewHolder.tvLvItemServiceOrderServiceTime.setText(data.get(position).getOrderServiceTime());
//
//
//            // TODO: 2017/12/11
//
//            return convertView;
//        }
//
//        //ViewHolder静态类
//        static class ViewHolder {
//            @BindView(R.id.tv_lv_item_service_order_number)
//            TextView tvLvItemServiceOrderNumber;
//            @BindView(R.id.tv_lv_item_service_order_status)
//            TextView tvLvItemServiceOrderStatus;
//            @BindView(R.id.tv_lv_item_service_order_date)
//            TextView tvLvItemServiceOrderDate;
//            @BindView(R.id.iv_lv_item_service_order_user_pic)
//            ImageView ivLvItemServiceOrderUserPic;
//            @BindView(R.id.tv_lv_item_service_order_user_name)
//            TextView tvLvItemServiceOrderUserName;
//            @BindView(R.id.tv_lv_item_service_order_user_phone_number)
//            TextView tvLvItemServiceOrderUserPhoneNumber;
//            @BindView(R.id.iv_lv_item_service_order_dial)
//            ImageView ivLvItemServiceOrderDial;
//            @BindView(R.id.tv_lv_item_service_order_dial)
//            TextView tvLvItemServiceOrderDial;
//            @BindView(R.id.ll_lv_item_service_order_turn_member)
//            LinearLayout llLvItemServiceOrderTurnMember;
//            @BindView(R.id.tv_lv_item_service_order_item_name)
//            TextView tvLvItemServiceOrderItemName;
//            @BindView(R.id.tv_lv_item_service_order_waiter_name)
//            TextView tvLvItemServiceOrderWaiterName;
//            @BindView(R.id.tv_lv_item_service_order_service_status)
//            TextView tvLvItemServiceOrderServiceStatus;
//            @BindView(R.id.tv_lv_item_service_order_service_time)
//            TextView tvLvItemServiceOrderServiceTime;
//
//            ViewHolder(View view) {
//                ButterKnife.bind(this, view);
//            }
//        }
//    }


}
