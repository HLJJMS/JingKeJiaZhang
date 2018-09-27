package hyxd.parentapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.GameWebViewClient;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.util.AlertDialogUtil;
//学情分析界面
public class StudyAnalysisActivity extends BaseActivity implements View.OnClickListener {

    private TextView onBack;
    private TextView title_tv;
    public WebView webView;

    private Context context;
    private Activity activity;
    private String userCode;
    private String url;

    private SharedPreferences spConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_analysis);
        context = this;
        activity = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);

        findView();

    }


    private void findView(){
        webView = findViewById(R.id.web);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);

        title_tv.setText("学情分析");
        initWebView();

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

    private void initWebView() {

        //        url = "http://192.168.0.141/jingkeOnlineWeChat/webHome/";
        url = "http://wxt.jingke.net/web/analysis/analysis2.html?classId=1000747472&StudentNo=SU0046495&stName=%E5%8F%B6%E6%B7%BB1&timeStar=2018-07-26T10:10:00";
//        url = "http://wxt.jingke.net/%E7%B2%BE%E8%AF%BE%E8%AF%BE%E5%A0%82%E5%8F%8D%E9%A6%88/analysis/analysis2.html?classId=1000747472&StudentNo=SU0046495&stName=%E5%8F%B6%E6%B7%BB1&timeStar=2018-07-26T10:10:00";

          // 清除cookie即可彻底清除缓存
//        CookieSyncManager.createInstance(context);
//        CookieManager.getInstance().removeAllCookie();

        if (Build.VERSION.SDK_INT >= 19) {
//            webView.getSettings().setCacheMode(
//                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
            //webView.getSettings().setCacheMode(
            //        WebSettings.LOAD_CACHE_ELSE_NETWORK)；
            //不使用缓存：
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                activity.setTitle("Loading...");
                activity.setProgress(newProgress * 100);
                if (newProgress == 100) {
                    activity.setTitle(R.string.app_name);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView.setWebViewClient(new GameWebViewClient());
        WebSettings s = webView.getSettings();
        s.setDomStorageEnabled(true);
        s.setJavaScriptEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        webView.loadUrl(url);

        webView.addJavascriptInterface(new Object(){
            //将个人信息传给后台
            @SuppressWarnings("unused")
            @JavascriptInterface
            public String getMyInfo() {
                Log.d("222222222     ", userCode);
                if (userCode == null || userCode.equals("")){
                    AlertDialogUtil.showAlertDialog(context,"提示", "用户编号为空，请退出重新登陆!");
                    return "";
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userCode", userCode);

//                    jsonObject.put("userCode", "CN000167");
//                    jsonObject.put("PhoneNumber", "15012312312");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return jsonObject.toString();
            }

            @SuppressWarnings("unused")
            @JavascriptInterface
            public void getString(String str) {

//                AlertDialogUtil.showAlertDialog(context,"提示", str);

            }

        }, "android");



    }

}
