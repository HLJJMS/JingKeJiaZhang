package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.ClassSpeakBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.util.AlertDialogUtil;
import hyxd.parentapp.xorzlistview.xlistview.XListView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClassDetailsActivity extends BaseActivity implements View.OnClickListener{

    private XListView listView;
    private TextView empty;
    private TextView onBack;

    private TextView title_tv, tv_bteacher, tv_tteacher;

    private List<ClassSpeakBean.MyDynamicDataBean> listData;
    private CommonAdapter adapter;
    private Context context;
    private String userCode;

    private SharedPreferences spConfig;
    private String classNo, TTeacher, BTeacher, className;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);
        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);
        userCode = "SU0038674";

        classNo = getIntent().getStringExtra("ClassNo");
        TTeacher = getIntent().getStringExtra("tTeacher");
        BTeacher = getIntent().getStringExtra("bTeacher");
        className = getIntent().getStringExtra("ClassName");

        findView();

    }

    private void findView(){
        listView = (XListView) findViewById(R.id.scheduling_lv);
        empty = (TextView) findViewById(R.id.empty);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        tv_tteacher = (TextView) findViewById(R.id.tv_tteacher);
        tv_bteacher = (TextView) findViewById(R.id.tv_bteacher);


        title_tv.setText(className);
        tv_tteacher.setText(TTeacher);
        tv_bteacher.setText(BTeacher);
        listView.setEmptyView(empty);


        listData = new ArrayList<>();

        adapter = new CommonAdapter<ClassSpeakBean.MyDynamicDataBean>(context, listData, R.layout.item_class_details) {
            @Override
            public void convert(ViewHolder holder, final ClassSpeakBean.MyDynamicDataBean rowsBean) {
                final int i = listData.indexOf(rowsBean);

                holder.setText(R.id.tv_test_no, (i+1) + "、");
                holder.setText(R.id.tv_class_name, rowsBean.getSpeakName());
                holder.setText(R.id.tv_time_date, rowsBean.getTimes());
                holder.setText(R.id.tv_class_time, rowsBean.getStart() + "~" +rowsBean.getEnds());
                
                final int status = rowsBean.getStatus();
                if (status == 1){
                    holder.setText(R.id.tv_home_work, "已做作业");
                    holder.setImageResource(R.id.iv_home_work, R.drawable.iv_homework_see);
                }else if (status == 0){
                    holder.setText(R.id.tv_home_work, "未做作业");
                    holder.setImageResource(R.id.iv_home_work, R.drawable.iv_homework);
                }else {
                    holder.setText(R.id.tv_home_work, "未布置");
                    holder.setImageResource(R.id.iv_home_work, R.drawable.iv_homeworking);
                }

                holder.getView(R.id.ll_alter_work).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (status == 1){
                            Intent intent = new Intent(ClassDetailsActivity.this, AlterWorkActivity.class);
                            intent.putExtra("ClassId", rowsBean.getClassID());
                            intent.putExtra("title",rowsBean.getSpeakName());
                            startActivity(intent);
                        }else if (status == 0){
                            Intent intent = new Intent(ClassDetailsActivity.this, AppraisalAnswerActivity.class);
                            intent.putExtra("SpeakName", rowsBean.getSpeakName());
                            intent.putExtra("ClassId", rowsBean.getClassID());

                            startActivity(intent);
                        }else {
                            Toast.makeText(context, "本节课未布置作业", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                holder.getView(R.id.ll_study_analysis).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ClassDetailsActivity.this, StudyAnalysisActivity.class);
                        startActivity(intent);

                    }
                });

            }
        };

        listView.setAdapter(adapter);
//        listView.setXListViewListener(this);
        listView.setPullLoadEnable(false);//激活加载更多
        listView.setPullRefreshEnable(false);
        onBack.setOnClickListener(this);

//        if (classNo != null && !classNo.equals("")){
//            getClassSpeakTask task = new getClassSpeakTask();
//            task.execute();
//        }else {
//            AlertDialogUtil.showAlertDialog(context,"提示", "未获取到班级编码，请退出重新选择");
//        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        listData.clear();
        if (classNo != null && !classNo.equals("")){
            getClassSpeakTask task = new getClassSpeakTask();
            task.execute();
        }else {
            AlertDialogUtil.showAlertDialog(context,"提示", "未获取到班级编码，请退出重新选择");
        }
    }

    //请求课程列表
    private class getClassSpeakTask extends AsyncTask<Void, Void, String> {

        String result = "";
        Handler handler;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
//            handler = NetWorkRequest.getHandler();

        }

        @Override
        protected String doInBackground(Void... arg0) {
            // 刷新时，重置地址

            String data = "ClassCode=" + classNo + "&" + "StuNo=" + userCode;

            String url = Constants.HTTP_URL + Constants.GetClassSpeakMN + "?" + data;
            Log.d("ggggggggggggggggg", url);
            String result="";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
//                Log.d("33222222222222", response.)
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            Log.d("333333333333333", result);
            closeLoading();

            if (result != null && !result.equals("")){
                if (!result.contains("code")){
                    Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");

                if (code != null && code.equals("0")){
                    Gson gson = new Gson();
                    ClassSpeakBean classSpeakBean = gson.fromJson(jsonObject.toJSONString(), ClassSpeakBean.class);
                    if (classSpeakBean != null){
                        if (classSpeakBean.getMyDynamicData().size() != 0){
                            listData.addAll(classSpeakBean.getMyDynamicData());
                            adapter.setData(listData);
                            adapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(context, "暂无课程信息", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(context, "请求失败，数据格式有误", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();

            }

        }
    }


}
