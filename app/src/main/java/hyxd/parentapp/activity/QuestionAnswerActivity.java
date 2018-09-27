package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.xorzlistview.xlistview.XListView;

//提问答疑界面
public class QuestionAnswerActivity extends BaseActivity implements View.OnClickListener {

    private XListView listView;
    private TextView empty;
    private TextView onBack;

    private TextView title_tv;
    private TextView sure_tv;

    private List<String> listData;
    private CommonAdapter adapter;
    private Context context;
    private String userCode;

    private SharedPreferences spConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);


        findView();


    }

    private void findView(){
        listView = (XListView) findViewById(R.id.scheduling_lv);
        empty = (TextView) findViewById(R.id.empty);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        sure_tv = (TextView) findViewById(R.id.sure_tv);


        title_tv.setText("提问");
        listView.setEmptyView(empty);


        listData = new ArrayList<>();
        listData.add("dddddddddd");
        listData.add("eeeeeee");
        listData.add("cccc");


        adapter = new CommonAdapter<String>(context, listData, R.layout.item_test_analysis) {
            @Override
            public void convert(ViewHolder holder, final String rowsBean) {
                int i = listData.indexOf(rowsBean);


                holder.getView(R.id.ll_test_item).setVisibility(View.GONE);
                TextView tv_see = holder.getView(R.id.tv_see_analy);
                tv_see.setText("查看老师回复");
                tv_see.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(context, CheckAnalyActivity.class);
//
//                        startActivity(intent);
                        Toast.makeText(context, "查看老师回复", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

        listView.setAdapter(adapter);
//        listView.setXListViewListener(this);
        listView.setPullLoadEnable(false);//激活加载更多
        listView.setPullRefreshEnable(false);
        onBack.setOnClickListener(this);
        sure_tv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();

                break;
            case R.id.sure_tv:

                Intent intent = new Intent(context, UploadTestActivity.class);
                startActivity(intent);

                break;
        }

    }
}
