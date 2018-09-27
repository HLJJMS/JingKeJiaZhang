package hyxd.parentapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.readTheAnswerBean;

/**
 * Created by wlm on 2018/8/23.
 */

public class ReadTheAnswerAdapter extends BaseAdapter {

    private List<readTheAnswerBean.MyDynamicDataBean> mList = new ArrayList<>();//数据源
    private LayoutInflater mInflater;//布局装载器对象
    protected Context context;

    public ReadTheAnswerAdapter(Context context, List<readTheAnswerBean.MyDynamicDataBean> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        String p = String.valueOf(position+1);
        if(null==convertView){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_h_lv, null);
            viewHolder.tv_circle = (TextView) convertView.findViewById(R.id.tv_item_circle);
            viewHolder.tv_correct = (TextView) convertView.findViewById(R.id.tv_item_correct);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }
        readTheAnswerBean.MyDynamicDataBean bean = mList.get(position);
        if ("1".equals(bean.getStatus())) {
            viewHolder.tv_circle.setText(p);
            viewHolder.tv_circle.setBackgroundResource(R.drawable.iv_alter_error);
            viewHolder.tv_correct.setText("正确");
        } else {
            viewHolder.tv_circle.setText(p);
            viewHolder.tv_circle.setBackgroundResource(R.drawable.iv_correct);
            viewHolder.tv_correct.setText("错误");
        }
        return convertView;
    }
    class ViewHolder{
    TextView tv_circle ;
    TextView tv_correct ;
    }
    public void setData(List<readTheAnswerBean.MyDynamicDataBean> datas) {
        mList = datas;
        this.context = context;
        this.notifyDataSetChanged();
    }
    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
