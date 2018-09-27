package hyxd.parentapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.activity.AppraisalAnswerActivity;
import hyxd.parentapp.activity.MainActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.xorzlistview.xlistview.XListView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by wlm on 2018/4/3.
 */
//学习测评子碎片
public class AppraisalChildFragment extends Fragment {

    private XListView listView;
    private TextView empty;


    private List<String> listData;
    private CommonAdapter adapter;
    private Context context;
    private MainActivity activity;
    private String userCode;

    private SharedPreferences spConfig;


    //当用户可见时实现懒加载数据
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        activity = (MainActivity) getActivity();
        spConfig = activity.getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        userCode = "SU0036396";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_child_appraisal, container, false);

        findView(contentView);

        return contentView;
    }


    private void findView(View view){

        listView = (XListView) view.findViewById(R.id.scheduling_lv);
        empty = (TextView) view.findViewById(R.id.empty);


        listView.setEmptyView(empty);

        listData = new ArrayList<>();
        listData.add("dddddddddd");
        listData.add("eeeeeee");
        listData.add("cccc");


        adapter = new CommonAdapter<String>(context, listData, R.layout.item_child_appraisal) {
            @Override
            public void convert(ViewHolder holder, final String rowsBean) {
                int i = listData.indexOf(rowsBean);

                holder.getView(R.id.tv_start_appraisal).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, AppraisalAnswerActivity.class);
                        startActivity(intent);
                    }
                });

            }
        };

        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
//        listView.setXListViewListener(this);
        listView.setPullLoadEnable(false);//激活加载更多
        listView.setPullRefreshEnable(false);


//        if (userCode != null && !userCode.equals("")){
//            ClassChildFragment.getClassListTask task = new ClassChildFragment.getClassListTask();
//            task.execute();
//        }else {
//            AlertDialogUtil.showAlertDialog(context,"提示", "未获取到用户编码，请退出重新登录");
//        }

    }


}



