package hyxd.parentapp.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.HomerWorkBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonWay;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.GameWebViewClient;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.network.NetWorkRequest;
import hyxd.parentapp.network.WebServiceUtils;
import hyxd.parentapp.util.ActivityManage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//测评答题界面
public class AppraisalAnswerActivity extends BaseActivity implements View.OnClickListener {

    private TextView onBack;
    private WebView webView1, webView2;
    private RadioGroup rg_bottom_bar;

    private TextView title_tv;

    private TextView tv_last_step, sure_tv, tv_next_step, tv_currout_no, tv_total_no, tv_item_no;

    private LinearLayout ll_select_answer, ll_iv_answer, ll_iv_function;
    private RelativeLayout rl_web_upload, rl_photo_upload;
    private ImageView iv_new_photos;

    private Context context;
    private String userCode;

    private SharedPreferences spConfig;

    private String imageFilePath;

    private String classId, speakName;

    private List<HomerWorkBean.MyDynamicDataBean> listData;

    private int curroutNO, totalTest;
    private String stuAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraisal_answer);

        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);
        userCode = "SU0038674";

        speakName = getIntent().getStringExtra("SpeakName");
        classId = getIntent().getStringExtra("ClassId");

        findView();

        initWebView();
        setRGListener();
    }


    private void findView(){

        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        tv_currout_no = (TextView) findViewById(R.id.tv_currout_no);
        tv_total_no = (TextView) findViewById(R.id.tv_total_no);
        tv_item_no = (TextView) findViewById(R.id.tv_item_no);
        tv_last_step = (TextView) findViewById(R.id.tv_last_step);
        sure_tv = (TextView) findViewById(R.id.sure_tv);
        tv_next_step = (TextView) findViewById(R.id.tv_next_step);
        ll_select_answer = (LinearLayout) findViewById(R.id.ll_select_answer);
        ll_iv_answer = (LinearLayout) findViewById(R.id.ll_iv_answer);
        ll_iv_function = (LinearLayout) findViewById(R.id.ll_iv_function);
        iv_new_photos = (ImageView) findViewById(R.id.iv_new_photos);
        rl_photo_upload = findViewById(R.id.rl_photo_upload);
        rl_web_upload = findViewById(R.id.rl_web_upload);
        webView1 = findViewById(R.id.wb_exercises1);
        webView2 = findViewById(R.id.wb_exercises2);
        rg_bottom_bar = findViewById(R.id.rg_bottom_bar);

        listData = new ArrayList<>();

        title_tv.setText(speakName);

        ll_select_answer.setVisibility(View.VISIBLE);
        ll_iv_answer.setVisibility(View.GONE);
        ll_iv_function.setVisibility(View.GONE);
        sure_tv.setVisibility(View.GONE);

        onBack.setOnClickListener(this);
        tv_last_step.setOnClickListener(this);
        sure_tv.setOnClickListener(this);
        tv_next_step.setOnClickListener(this);
        rl_web_upload.setOnClickListener(this);
        rl_photo_upload.setOnClickListener(this);

        tv_last_step.setBackgroundResource(R.drawable.shape_tv_bg);
        tv_last_step.setEnabled(false);
        tv_next_step.setBackgroundResource(R.drawable.shape_tv_bg);
        tv_next_step.setEnabled(false);

        getTestTask testTask = new getTestTask();
        testTask.execute();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();

                break;
            case R.id.tv_next_step:
                //下一题
                //提交正确答案
                submitAnswerTask task = new submitAnswerTask();
                task.execute();

                break;
            case R.id.tv_last_step:
                //上一题
                stuAnswer = "";
                curroutNO = curroutNO-1;
                rg_bottom_bar.clearCheck();
                tv_currout_no.setText((curroutNO + 1) +"");
                tv_item_no.setText((curroutNO + 1) +"");
                if (curroutNO == 0){
                    tv_last_step.setBackgroundResource(R.drawable.shape_tv_bg);
                    tv_last_step.setEnabled(false);
                }
                if (curroutNO < (totalTest-1)){
                    tv_next_step.setText("下一题");
                }
                loadTest();
                break;

            case R.id.rl_web_upload:
//                Toast.makeText(context, "本地上传", Toast.LENGTH_SHORT).show();
//                // 相册选取
//                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
//                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent1, 103);
                break;
            case R.id.rl_photo_upload:
//                Toast.makeText(context, "拍照上传", Toast.LENGTH_SHORT).show();
//                //设置图片的保存路径,作为全局变量
//                imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/filename.jpg";
//                File temp = new File(imageFilePath);
//                Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
//                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
//                it.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
//                startActivityForResult(it, 102);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 102:
                if (resultCode == Activity.RESULT_OK) {

                    Bitmap bmp = BitmapFactory.decodeFile(imageFilePath);
                    iv_new_photos.setImageBitmap(bmp);
                }
                break;
            case 103:
                Bitmap bm = null;
                // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
                ContentResolver resolver = getContentResolver();

                try {
                    if (data != null){
                        Uri originalUri = data.getData(); // 获得图片的uri

                        bm = MediaStore.Images.Media.getBitmap(resolver, originalUri); // 显得到bitmap图片

                        // 这里开始的第二部分，获取图片的路径：
                        String[] proj = { MediaStore.Images.Media.DATA };

                        // 好像是android多媒体数据库的封装接口，具体的看Android文档
                        @SuppressWarnings("deprecation")
                        Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                        // 按我个人理解 这个是获得用户选择的图片的索引值
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                        cursor.moveToFirst();
                        // 最后根据索引值获取图片路径
                        String path = cursor.getString(column_index);
                        iv_new_photos.setImageURI(originalUri);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }


    //初始化webview
    private void initWebView() {
        webView1.loadUrl("about:blank");
        webView2.loadUrl("about:blank");


        if (Build.VERSION.SDK_INT >= 19) {
            webView1.getSettings().setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webView1.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                activity.setTitle("Loading...");
//                activity.setProgress(newProgress * 100);
                if (newProgress == 100) {
//                    activity.setTitle(R.string.app_name);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView1.setWebViewClient(new GameWebViewClient());
        WebSettings s = webView1.getSettings();
        s.setJavaScriptEnabled(true);


        if (Build.VERSION.SDK_INT >= 19) {
            webView2.getSettings().setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webView2.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                activity.setTitle("Loading...");
//                activity.setProgress(newProgress * 100);
                if (newProgress == 100) {
//                    activity.setTitle(R.string.app_name);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView2.setWebViewClient(new GameWebViewClient());
        WebSettings s1 = webView2.getSettings();
        s1.setJavaScriptEnabled(true);


    }

    //加载题目
    private void loadTest(){
        webView1.loadUrl("about:blank");
        webView2.loadUrl("about:blank");
        HomerWorkBean.MyDynamicDataBean rowsBean = listData.get(curroutNO);

        String stem = rowsBean.getStem();
        String choses = rowsBean.getChoses();
        if (stem != null && !stem.equals("") ){
//            String str1 = CommonWay.getHtmlData2(stem + choses);
            String str1 = CommonWay.getHtmlData(stem);
            webView1.loadData(str1, "text/html; charset=UTF-8", null);
        }else {
            webView1.setVisibility(View.GONE);
        }

        if (choses != null && !choses.equals("") ){
//            String str1 = CommonWay.getHtmlData2(stem + choses);
            String str2 = CommonWay.getHtmlData(choses);
            webView2.loadData(str2, "text/html; charset=UTF-8", null);
        }else {
            webView2.setVisibility(View.GONE);
        }

    }


    private void setRGListener(){
        rg_bottom_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_answer_a:
                        stuAnswer = "A";
                        break;
                    case R.id.rb_answer_b:
                        stuAnswer = "B";
                        break;
                    case R.id.rb_answer_c:
                        stuAnswer = "C";
                        break;
                    case R.id.rb_answer_d:
                        stuAnswer = "D";
                        break;
                }
            }
        });
    }


    //请求课程列表
    private class getTestTask extends AsyncTask<Void, Void, String> {

        String result = "";
        Handler handler;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
            handler = NetWorkRequest.getHandler();

        }

        @Override
        protected String doInBackground(Void... arg0) {
            // 刷新时，重置地址
            String data = "ClassID=" + classId + "&" + "StuNo=" + userCode;

            String url = Constants.HTTP_URL + Constants.GetHomerWorkMN + "?" + data;
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

            WebServiceUtils.logLength("33333333333333333", result);
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
                    HomerWorkBean homerWorkBean = gson.fromJson(jsonObject.toJSONString(), HomerWorkBean.class);
                    if (homerWorkBean != null){
                        totalTest = homerWorkBean.getMyDynamicData().size();
                        tv_total_no.setText(totalTest + "");
                        tv_currout_no.setText(curroutNO + "");
                        tv_item_no.setText(curroutNO + "");
                        if (totalTest != 0){
                            listData.addAll(homerWorkBean.getMyDynamicData());
                            loadTest();
                            tv_currout_no.setText((curroutNO + 1) +"");
                            tv_item_no.setText((curroutNO + 1) +"");
                            tv_last_step.setBackgroundResource(R.drawable.shape_tv_bg);
                            tv_last_step.setEnabled(false);
                            tv_next_step.setBackgroundResource(R.drawable.shape_tv_bg1);
                            tv_next_step.setEnabled(true);
                            if (totalTest == 1){
                                tv_next_step.setText("提交");
                            }
                        }else {

                            Toast.makeText(context, "未获取到作业请重试", Toast.LENGTH_SHORT).show();
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


    //提交学生答案
    private class submitAnswerTask extends AsyncTask<Void, Void, String> {

        String result = "";
        Handler handler;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
            handler = NetWorkRequest.getHandler();

        }

        @Override
        protected String doInBackground(Void... arg0) {
            String eID = listData.get(curroutNO).getEID();
            String qID = listData.get(curroutNO).getQID();
            String data = "";
            if (curroutNO == (totalTest-1)){
                // 刷新时，重置地址
                data = "EID=" + eID + "&" + "QID=" + qID + "&" + "QusAnswer=" + stuAnswer +"&" + "EIDType=" + "1";
            }else {
                // 刷新时，重置地址
                data = "EID=" + eID + "&" + "QID=" + qID + "&" + "QusAnswer=" + stuAnswer +"&" + "EIDType=" + "0";
            }


            String url = Constants.HTTP_URL + Constants.SubmitAnswerMN + "?" + data;
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

            WebServiceUtils.logLength("33333333333333333", result);
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
                    stuAnswer = "";
                    rg_bottom_bar.clearCheck();
                    curroutNO++;
                    if (curroutNO == totalTest){
                        //提交左后一题成功后返回
                        tv_next_step.setBackgroundResource(R.drawable.shape_tv_bg);
                        tv_next_step.setEnabled(false);
                        tv_last_step.setBackgroundResource(R.drawable.shape_tv_bg);
                        tv_last_step.setEnabled(false);
                        tv_next_step.setText("已提交");
                        finish();
                    }else if (curroutNO == (totalTest-1)){
                        tv_currout_no.setText((curroutNO + 1) +"");
                        tv_item_no.setText((curroutNO + 1) +"");
                        tv_last_step.setBackgroundResource(R.drawable.shape_tv_bg1);
                        tv_last_step.setEnabled(true);
                        tv_next_step.setBackgroundResource(R.drawable.shape_tv_bg1);
                        tv_next_step.setEnabled(true);
                        tv_next_step.setText("提交");
                        loadTest();
                    }else {
                        tv_currout_no.setText((curroutNO + 1) +"");
                        tv_item_no.setText((curroutNO + 1) +"");
                        tv_last_step.setBackgroundResource(R.drawable.shape_tv_bg1);
                        tv_last_step.setEnabled(true);
                        tv_next_step.setBackgroundResource(R.drawable.shape_tv_bg1);
                        tv_next_step.setEnabled(true);
                        tv_next_step.setText("下一题");
                        loadTest();
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
