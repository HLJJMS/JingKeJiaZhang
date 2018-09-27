package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.adapter.ReadTheAnswerAdapter;
import hyxd.parentapp.adapter.ReadTheRankingAdapter;
import hyxd.parentapp.bean.ReadTheRankingBean;
import hyxd.parentapp.bean.ReadThepersonalBean;
import hyxd.parentapp.bean.readTheAnswerBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.CommonWay;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.util.FileUtils;
import hyxd.parentapp.util.RecorderUtil;
import hyxd.parentapp.view.CircleImageView;
import hyxd.parentapp.view.HorizontalListView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//修改作业界面
public class AlterWorkActivity extends BaseActivity implements View.OnClickListener {
    //    private ReadTheAnswerAdapter answerAdapter ;
    private ReadTheRankingAdapter readTheRankingAdapter;
    private HorizontalListView horizontalListView;
    private CommonAdapter hAdapter;
    private List<String> hListData;
    private List<readTheAnswerBean.MyDynamicDataBean> listAnswerbean = new ArrayList<>();
    private List<ReadTheRankingBean.MyDynamicDataBean> listKing = new ArrayList<>();
    private String classId;
    private TextView onBack;
    private WebView webView;
    private TextView title_tv, ssum, bsum;
    private TextView tv_analy;
    private CircleImageView iv_main_teacher;
    private Boolean isGood;
    private ListView listView;
    private List<String> listData;
    private CommonAdapter adapter;
    private Context context;
    private String userCode,title;
    private int i =0,sizeForListKing;

    private SharedPreferences spConfig;

    //录音工具类
    private RecorderUtil recorderUtil;
    //录音开始时间和结束时间
    private long raduioStartTime, raduioEndTime;

    private boolean isCanMore = true;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_work);
        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode, "");
        userCode = "SU0038674";
//        Constants.userCode = userCode;
        classId = getIntent().getStringExtra("ClassId");
        title = getIntent().getStringExtra("title");
        ActivityManage.addActivity(this);
        recorderUtil = new RecorderUtil();
        readTheRankingAdapter = new ReadTheRankingAdapter(context);
        findView();

    }

    public void goItem(int i){
        listView.setSelection( i);
    }

    private void findView() {
        listView = (ListView) findViewById(R.id.auto_lv);
        horizontalListView = (HorizontalListView) findViewById(R.id.h_course_lv);
        listView.setAdapter(readTheRankingAdapter);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        tv_analy = (TextView) findViewById(R.id.tv_analy);
        iv_main_teacher = findViewById(R.id.iv_main_teacher);
        ssum = (TextView) findViewById(R.id.ssum);
        bsum = (TextView) findViewById(R.id.bsum);

        title_tv.setText(title);
        listView.setAdapter(readTheRankingAdapter );
        onBack.setOnClickListener(this);
        tv_analy.setOnClickListener(this);
        iv_main_teacher.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(sizeForListKing<4){
                    if(position==i){
                        readTheRankingAdapter.setData();
                        listView.smoothScrollToPosition(i);
                    }
                }else{
                    if(position==3){
                        readTheRankingAdapter.setData();
                        listView.smoothScrollToPosition(i);
                    }
                }
            }
        });
        horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AlterWorkActivity.this, WorkConditionActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        readThepersonal task = new readThepersonal();
        task.execute();
        ReadTheAnswer answerTask = new ReadTheAnswer();
        answerTask.execute();
        King king = new King();
        king.execute();
    }

    //请求数据
    private class readThepersonal extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
//            handler = NetWorkRequest.getHandler();

        }
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            String url = Constants.HTTP_URL + "ReadThepersonal" + "?" + "CitemNo=" + classId + "&StudentNo=" + userCode;
            Log.e("okhttp", url);
            Request request = new Request.Builder().url(url).build();
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
            closeLoading();
            if (null != result && !"".equals(result)) {
                Log.e("结果", result);

                JSONObject j = JSONObject.parseObject(result);
                String code = j.getString("code");
                String message = j.getString("message");
                if ("0".equals(code)) {
                    Gson gson = new Gson();
                    ReadThepersonalBean readThepersonalBean = gson.fromJson(j.toJSONString(), ReadThepersonalBean.class);

                    if(null!=readThepersonalBean&&readThepersonalBean.getMyDynamicData().size() != 0){
                        ssum.setText(readThepersonalBean.getMyDynamicData().get(0).getSsum());
                        String bsumText = "班级平均分" + readThepersonalBean.getMyDynamicData().get(0).getBsum() + "分";
                        bsum.setText(bsumText);
                    }else{
                        Toast.makeText(context, "暂无分数信息", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "请求失败，数据格式有误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private class ReadTheAnswer extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
//            handler = NetWorkRequest.getHandler();

        }
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            String url = Constants.HTTP_URL + "ReadTheAnswer" + "?" + "StudentNo=" + userCode + "&CitemNo=" + classId;
            Log.e("okhttp", url);
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            closeLoading();
            if (null != result && !"".equals(result)) {
                Log.e("结果", result);
                JSONObject j = JSONObject.parseObject(result);
                String code = j.getString("code");
                if ("0".equals(code)) {
                    Gson gson = new Gson();
                    readTheAnswerBean answer = gson.fromJson(j.toJSONString(), readTheAnswerBean.class);
                    if(null!=answer&&answer.getMyDynamicData().size() != 0){
                        listAnswerbean.addAll(answer.getMyDynamicData());
//                    answerAdapter.setData(listAnswerbean);
                        horizontalListView.setAdapter(new ReadTheAnswerAdapter(context, listAnswerbean));
                    }else{
                        Toast.makeText(context, "暂无答题信息", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(context, "请求失败，数据格式有误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private class King extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
//            handler = NetWorkRequest.getHandler();

        }
        @Override
        protected String doInBackground(Void... voids) {
            String ok = "";
            String url = Constants.HTTP_URL + "ReadTheRanking" + "?" + "CitemNo=" + "1000458444";
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok;
        }

        protected void onPostExecute(String result) {
            closeLoading();
            if (null != result && !"".equals(result)) {
                Log.e("结果", result);
                JSONObject j = JSONObject.parseObject(result);
                String code = j.getString("code");
                if ("0".equals(code)) {
                    Gson gson = new Gson();
                    ReadTheRankingBean readTheRankingBean = gson.fromJson(j.toJSONString(), ReadTheRankingBean.class);
                   if(null!=readTheRankingBean&&readTheRankingBean.getMyDynamicData().size() != 0){
                       listKing.addAll(readTheRankingBean.getMyDynamicData());
                       for(int o = 0;o<listKing.size();o++){
                           if(userCode.equals(listKing.get(o).getStudentNo()))
                               i=o;
                       }
                       sizeForListKing = listKing.size();
                       readTheRankingAdapter.setFristData(listKing);
                       listView.setAdapter(readTheRankingAdapter);
                   }else{
                       Toast.makeText(context, "暂无排名信息", Toast.LENGTH_SHORT).show();
                   }

                } else {
                    Toast.makeText(context, "请求失败，数据格式有误", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onBack:
                finish();

                break;
            case R.id.tv_analy:
                Toast.makeText(context, "开始播放", Toast.LENGTH_SHORT).show();
                recorderUtil.startPlay(context, new FileUtils().getSDPATH() + "audio/" + "12345" + ".m4a");
                break;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonWay.deleteDir(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio/");
    }
}
