package hyxd.parentapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.activity.AlterWorkActivity;
import hyxd.parentapp.bean.ReadTheRankingBean;

/**
 * Created by wlm on 2018/8/23.
 */

public class ReadTheRankingAdapter extends BaseAdapter {
    private List<ReadTheRankingBean.MyDynamicDataBean> mlist = new ArrayList<>();
    private List<ReadTheRankingBean.MyDynamicDataBean> listCopy = new ArrayList<>();
    private LayoutInflater mInflater;
    private AlterWorkActivity alterWorkActivity = new AlterWorkActivity();
    private int meNo;
    private Boolean isGood = false, isSetData = false;
//    private String userCode = Constants.userCode;
    protected Context context;
    private String userCode = "SU0038638";

    public ReadTheRankingAdapter(Context context) {

        mInflater = LayoutInflater.from(context);
    }
    public void setFristData(List<ReadTheRankingBean.MyDynamicDataBean> list){
        listCopy = list;
        if (list.size() > 3) {
            for (int i = 0; i < 4; i++) {
                mlist.add(list.get(i));
                if (userCode.equals(list.get(i).getStudentNo())) {
                    meNo = i;
                    isGood = true;
                }
            }
            if (isGood == false) {
                for (int i = 4; i < list.size(); i++) {
                    if (userCode.equals(list.get(i).getStudentNo())) {
                        mlist.remove(3);
                        meNo = i;
                        mlist.add(list.get(i));
                    }
                }
            }

        } else {
            //直接干满
            this.mlist = list;
        }
    }
    public void setData(){
//        mlist.clear();
        mlist = listCopy;
        this.notifyDataSetChanged();
        isSetData=true;
    }

    @Override
    public int getCount() {
        return mlist == null ? 0 : mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_auto_lv, null);
            viewHolder.more_down = (LinearLayout) convertView.findViewById(R.id.ll_more_down);
            viewHolder.rankingImageView = (ImageView) convertView.findViewById(R.id.iv_ranking);
            viewHolder.rankingTextView = (TextView) convertView.findViewById(R.id.tv_ranking);
            viewHolder.tv_ranking_name = (TextView) convertView.findViewById(R.id.tv_ranking_name);
            viewHolder.tv_ranking_score = (TextView) convertView.findViewById(R.id.tv_ranking_score);
            viewHolder.ll_more = (LinearLayout) convertView.findViewById(R.id.ll_more);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ReadTheRankingAdapter.ViewHolder) convertView.getTag();
        }
         if (isSetData == false && position == 3) {
            if(isGood==true){
                viewHolder.more_down.setVisibility(View.VISIBLE);
                viewHolder.ll_more.setVisibility(View.GONE);
            }else{
                viewHolder.ll_more.setVisibility(View.VISIBLE);
                viewHolder.more_down.setVisibility(View.GONE);
            }
             Log.e("adapter",mlist.get(position).getStName());
        } else  {
            viewHolder.ll_more.setVisibility(View.GONE);
             viewHolder.more_down.setVisibility(View.GONE);
        }

        viewHolder.tv_ranking_name.setText(mlist.get(position).getStName());
        viewHolder.tv_ranking_score.setText(String.valueOf(mlist.get(position).getFraction()));

        if (position == 0) {
            viewHolder.rankingImageView.setVisibility(View.VISIBLE);
            viewHolder.rankingTextView.setVisibility(View.GONE);
            viewHolder.rankingImageView.setImageResource(R.drawable.first);
        } else if (position == 1) {
            viewHolder.rankingImageView.setVisibility(View.VISIBLE);
            viewHolder.rankingTextView.setVisibility(View.GONE);
            viewHolder.rankingImageView.setImageResource(R.drawable.second);
        } else if (position == 2) {
            viewHolder.rankingImageView.setVisibility(View.VISIBLE);
            viewHolder.rankingTextView.setVisibility(View.GONE);
            viewHolder.rankingImageView.setImageResource(R.drawable.third);

        } else {
            viewHolder.rankingImageView.setVisibility(View.GONE);
            viewHolder.rankingTextView.setVisibility(View.VISIBLE);
//                    rankingTextView.setText("" + (i + 1));
            if (isSetData == false) {
                if(isGood==true){
                    viewHolder.rankingTextView.setText(String.valueOf(position+1));
                }else{
                    viewHolder.rankingTextView.setText(String.valueOf(meNo+1));
                }
            } else {
                viewHolder.rankingTextView.setText(String.valueOf(position+1));
            }

        }
//        viewHolder.ll_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setData();
//                int position = (Integer) v.getTag();
//                alterWorkActivity.goItem(position);
//            }
//        });
//        viewHolder.more_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setData();
//                int position = (Integer) v.getTag();
//                alterWorkActivity.goItem(position);
//            }
//        });

        return convertView;
    }

    class ViewHolder {
        LinearLayout ll_more,more_down;
        TextView rankingTextView, tv_ranking_name, tv_ranking_score;
        ImageView rankingImageView;

    }
}
