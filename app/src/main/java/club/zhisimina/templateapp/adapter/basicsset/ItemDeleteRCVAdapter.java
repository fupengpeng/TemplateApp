package club.zhisimina.templateapp.adapter.basicsset;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import club.zhisimina.templateapp.R;
import club.zhisimina.templateapp.application.MyApplication;
import club.zhisimina.templateapp.entity.TimeItemSet;

/**
 * @description
 * @author  fupengpeng
 * @date  2018/4/3 0003 11:56
 */
public class ItemDeleteRCVAdapter extends RecyclerView.Adapter<ItemDeleteRCVAdapter.Holder>{
    private List<TimeItemSet> list = new ArrayList<>();
    private Context context;

    public ItemDeleteRCVAdapter(Context context, List<TimeItemSet> list) {
        this.list =  list;
        this.context = context;

        Log.e("-ss----", String.valueOf(list));
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_item_baset_time_price, parent, false);
            Holder holder = new Holder(view);


            return holder;

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvAtvtBasetTimePriceBegin.setText(MyApplication.getInstance().getUser().getAddress().toString());
        holder.tvAtvtBasetTimePriceEnd.setText(MyApplication.getInstance().getUser().getAddress().toString());

        if (list.get(position).getPs() != null){
            if (holder.tvAtvtBasetTimePriceItemNum.getVisibility() == View.GONE){
                holder.tvAtvtBasetTimePriceItemNum.setVisibility(View.VISIBLE);
            }
            holder.tvAtvtBasetTimePriceItemNum.setText(list.get(position).getPs().size() + "个项目单独定价");
        }else {
            if (holder.tvAtvtBasetTimePriceItemNum.getVisibility() == View.VISIBLE){
                holder.tvAtvtBasetTimePriceItemNum.setVisibility(View.GONE);
            }
        }
        holder.tvAtvtBasetTimePriceItemDiscount.setText(list.get(position).getPr() + "%");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvAtvtBasetTimePriceBegin;
        public TextView tvAtvtBasetTimePriceEnd;
        public TextView tvAtvtBasetTimePriceItemNum;
        public TextView tvAtvtBasetTimePriceItemDiscount;
        public TextView tvAtvtBasetTimePriceItemDelete;
        public LinearLayout llAtvtBasetTimePrice;

        public Holder(View itemView) {
            super(itemView);

            tvAtvtBasetTimePriceBegin = (TextView) itemView.findViewById(R.id.tv_atvt_baset_time_price_begin);
            tvAtvtBasetTimePriceEnd = (TextView) itemView.findViewById(R.id.tv_atvt_baset_time_price_end);
            tvAtvtBasetTimePriceItemNum = (TextView) itemView.findViewById(R.id.tv_atvt_baset_time_price_item_num);
            tvAtvtBasetTimePriceItemDiscount = (TextView) itemView.findViewById(R.id.tv_atvt_baset_time_price_item_discount);
            tvAtvtBasetTimePriceItemDelete = (TextView) itemView.findViewById(R.id.tv_atvt_baset_time_price_item_delete);
            llAtvtBasetTimePrice = (LinearLayout) itemView.findViewById(R.id.ll_atvt_baset_time_price);


        }
    }


//    //define interface
//    public static interface OnItemClickListener {
//        void onItemClick(View view , int position);
//    }
//
//    private OnItemClickListener mOnItemClickListener = null;
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }

}
