package hyxd.parentapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.ReadTheanalysisBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.util.ActivityManage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WorkConditionActivity extends BaseActivity implements View.OnClickListener {
    private final OkHttpClient client = new OkHttpClient();
    private Context context;
    private String userCode, posion;
    private WebView webViewTitle, webViewStudent, webViewSystem, webViewDetail,webViewSelect;
    private SharedPreferences spConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_condition);
        posion = getIntent().getStringExtra("position");
        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode, "");
        ActivityManage.addActivity(this);
        findView();

    }

    private void findView() {
        webViewTitle = (WebView) findViewById(R.id.wb_exercises1);
        webViewStudent = (WebView) findViewById(R.id.wb_exercises4);
        webViewSystem = (WebView) findViewById(R.id.wb_exercises2);
        webViewDetail = (WebView) findViewById(R.id.wb_exercises3);
        webViewSelect = (WebView) findViewById(R.id.wb_selectForQuerst);
        ReadTheanalysis read = new ReadTheanalysis();
        read.execute();
    }

    @Override
    public void onClick(View v) {

    }

    protected class ReadTheanalysis extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String ok = "";
            String url = Constants.HTTP_URL + "ReadTheanalysis" + "?" + "EID=" + "cb4de30d-5a14-4640-a9b7-046bdb948a1f" + "&QID=" + "76a61cc3-0cab-4831-8c47-799c01f3398a";
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = client.newCall(request).execute();

                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ok;
        }

        protected void onPostExecute(String result) {
            if (null != result && !"".equals(result)) {
                JSONObject j = JSON.parseObject(result);
                String code = j.getString("code");
                if ("0".equals(code)) {
                    Gson gson = new Gson();
                    ReadTheanalysisBean bean = gson.fromJson(j.toJSONString(), ReadTheanalysisBean.class);
                    doWebView(bean);

                } else {
                    Toast.makeText(context, "无数据", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void doWebView(ReadTheanalysisBean bean) {
        StringBuilder title = new StringBuilder();
        title.append(bean.getMyDynamicData().get(0).getStem());
        Log.e("titleTexTs", title.toString());
        webViewTitle.loadDataWithBaseURL(null, title.toString(), "text/html", "utf-8", null);
        StringBuilder student = new StringBuilder();
        StringBuilder system = new StringBuilder();
        StringBuilder detail = new StringBuilder();
        StringBuilder select = new StringBuilder();
        student.append(bean.getMyDynamicData().get(0).getQusAnswer());
        system.append(bean.getMyDynamicData().get(0).getCorrect());
        detail.append(bean.getMyDynamicData().get(0).getAnalysis());
        select.append(bean.getMyDynamicData().get(0).getChoses());
        webViewSystem.loadDataWithBaseURL(null, system.toString(), "text/html", "utf-8", null);
        webViewDetail.loadDataWithBaseURL(null, detail.toString(), "text/html", "utf-8", null);
        webViewSelect.loadDataWithBaseURL(null,select.toString(),"text/html", "utf-8", null);
        webViewStudent.loadDataWithBaseURL(null, student.toString(), "text/html", "utf-8", null);
    }

}
