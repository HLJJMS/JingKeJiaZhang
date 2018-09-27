package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.FBackClassBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.xorzlistview.xlistview.XListView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//课堂反馈列表界面
public class FeedBackActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private XListView listView;
    private TextView empty;
    private TextView onBack;

    private TextView title_tv;


    private List<FBackClassBean.MyDynamicDataBean> listData;
    private CommonAdapter adapter;
    private Context context;
    private String userCode;

    private SharedPreferences spConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);
        userCode = "SU0038674";

        findView();

        GetFBackClassTask task = new GetFBackClassTask();
        task.execute();

    }

    private void findView(){
        listView = (XListView) findViewById(R.id.scheduling_lv);
        empty = (TextView) findViewById(R.id.empty);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);


        title_tv.setText("课堂反馈");
        listView.setEmptyView(empty);


        listData = new ArrayList<>();

        adapter = new CommonAdapter<FBackClassBean.MyDynamicDataBean>(context, listData, R.layout.item_child_class) {
            @Override
            public void convert(ViewHolder holder, FBackClassBean.MyDynamicDataBean rowsBean) {
                holder.setText(R.id.tv_class_name,rowsBean.getClassName());
                holder.setText(R.id.tv_main_name, rowsBean.getTTeacherName());
                holder.setText(R.id.tv_help_name, rowsBean.getBTeacherName());

                String st_season = "";
                st_season = rowsBean.getF0004();
                String st_subject = "";
                st_subject = rowsBean.getF0013();

                if (("春").equals(st_season)){
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_spring);
                }else if (("秋").equals(st_season)){
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_autumn);
                }else if (("寒").equals(st_season)){
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_cold);
                }else{
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_heat);
                }

                if (st_subject.equals("语文")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_language);
                }else if (st_subject.equals("数学")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_math);
                }else if (st_subject.equals("英语")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_english);
                }else if (st_subject.equals("物理")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_physics);
                }else if (st_subject.equals("化学")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_chemistry);
                }else if (st_subject.equals("生物")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_biology);
                }else if (st_subject.equals("历史")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_hostory);
                }else if (st_subject.equals("地理")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_geography);
                }else if (st_subject.equals("政治")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_political);
                }

                String st_start = "";
                st_start = rowsBean.getPeriod_From();
                String st_end = "";
                st_end = rowsBean.getPeriod_To();
                if (st_start.length() >= 10 && st_end.length() >= 10){
                    holder.setText(R.id.tv_time_date, st_start.substring(0,10) + "~" + st_end.substring(0,10));
                }

            }
        };




        listView.setAdapter(adapter);
//        listView.setXListViewListener(this);
        listView.setPullLoadEnable(false);//激活加载更多
        listView.setPullRefreshEnable(false);
        listView.setOnItemClickListener(this);
        onBack.setOnClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(FeedBackActivity.this, FBackSpeachActivity.class);
        intent.putExtra("title",listData.get(position-1).getClassName());
        intent.putExtra("classNo",listData.get(position-1).getClassNo());
        intent.putExtra("TTeacherNo",listData.get(position-1).getTTeacherNo());
        intent.putExtra("F0013",listData.get(position-1).getF0013());

        Log.e("F0013---1",listData.get(position-1).getF0013());
        startActivity(intent);
    }

    //获取课堂反馈班级
    private class GetFBackClassTask extends AsyncTask<Void, Void, String> {

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

            String data = "StudentNo=" + userCode;

            String url = Constants.HTTP_URL + Constants.GetFBClassMN + "?" + data;
            Log.d("ggggggggggggggggg", url);
            String result="";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
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
                    FBackClassBean fBackClassBean = gson.fromJson(jsonObject.toJSONString(), FBackClassBean.class);
                    if (fBackClassBean != null){
                        if (fBackClassBean.getMyDynamicData().size() != 0){
                            listData.addAll(fBackClassBean.getMyDynamicData());
                            adapter.setData(listData);

                        }else {
                            Toast.makeText(context, "暂无已反馈课程信息", Toast.LENGTH_SHORT).show();
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
