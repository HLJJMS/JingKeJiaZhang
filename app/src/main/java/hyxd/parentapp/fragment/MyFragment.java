package hyxd.parentapp.fragment;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import hyxd.parentapp.R;
import hyxd.parentapp.activity.MainActivity;
import hyxd.parentapp.activity.MyOrderActivity;
import hyxd.parentapp.activity.SettingActivity;
import hyxd.parentapp.activity.UserInfoActivity;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.util.AlertDialogUtil;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by wlm on 2018/3/27.
 */
//我的碎片
public class MyFragment extends Fragment implements View.OnClickListener{

    private TextView onBack;
    private TextView title_tv;
    private LinearLayout ll_setting;
    private LinearLayout ll_my;

    private LinearLayout ll_user_info, ll_my_order;

    public WebView webView;

    private Context context;
    private MainActivity activity;
    private String userCode;
    private String tel;

    private SharedPreferences spConfig;

    private String url;

    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        activity = (MainActivity) getActivity();
        spConfig = activity.getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        tel = spConfig.getString(SPCommonInfoBean.tel,"");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragmen_my, container, false);
        findView(contentView);

        return contentView;

    }

    private void findView (View view){
        webView = view.findViewById(R.id.web);
        onBack = (TextView) view.findViewById(R.id.onBack);
        title_tv = (TextView) view.findViewById(R.id.common_title_tv);
        ll_setting = (LinearLayout) view.findViewById(R.id.ll_setting);
        ll_user_info = (LinearLayout) view.findViewById(R.id.ll_user_info);
        ll_my_order = (LinearLayout) view.findViewById(R.id.ll_my_order);
        ll_my = (LinearLayout) view.findViewById(R.id.ll_my);

        title_tv.setText("我的");

        initWebView();
        ll_my.setVisibility(View.GONE);

        onBack.setVisibility(View.GONE);
        ll_setting.setOnClickListener(this);
        ll_user_info.setOnClickListener(this);
        ll_my_order.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_setting:
                //跳转设置页
                Intent intent2 = new Intent(context, SettingActivity.class);
                startActivity(intent2);

                break;
            case R.id.ll_user_info:
                //跳转个人资料界面
                Intent intent = new Intent(context, UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_order:
                //跳转我的订单界面
                Intent intent1 = new Intent(context, MyOrderActivity.class);
                startActivity(intent1);
                break;
        }
    }


    private void initWebView(){
//        webView.loadUrl("about:blank");

//        url = "http://192.168.0.141/jingkeOnlineWeChat/personCenter/personCenter.html";
        url = "http://wxt.jingke.net/web/jingkeWEB/personCenter/personCenter.html";

        if (Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        webView.setWebChromeClient(new WebChromeClient(){
            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
                Log.e("openFileChooser","openFileChooser");
            }

            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
            {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try
                {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(context, "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                Log.e("onShowFileChooser","onShowFileChooser");
                return true;
            }


            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
            {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
                Log.e("openFileChooser","openFileChooser");
            }
        });


        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                view.clearCache(true);
                if (url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else {
                    Map<String, String> extraHeaders = new HashMap<String, String>();
//                    extraHeaders.put("Referer", "http://wxt.wenxinedu.com");
                    extraHeaders.put("Referer", "http://wxt.jingke.net");
                    view.loadUrl(url, extraHeaders);
                }
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) { // 重写此方法可以让webview处理https请求
                handler.proceed();
            }
        };


        webView.setWebViewClient(webViewClient);
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        webView.clearCache(true);

//        webView.loadData(str1, "text/html; charset=UTF-8", null);
        webView.loadUrl(url);


        webView.addJavascriptInterface(new Object(){
            @SuppressWarnings("unused")
            @JavascriptInterface
            public String setting() {
                //跳转到设置界面
                Intent intent2 = new Intent(context, SettingActivity.class);
                startActivity(intent2);
                return "";
            }

            //将个人信息传给后台
            @SuppressWarnings("unused")
            @JavascriptInterface
            public String getMyInfo() {
                Log.d("222222222     ", userCode);
                if (userCode == null || userCode.equals("")){
                    AlertDialogUtil.showAlertDialog(context,"提示", "用户编号为空，请退出重新登陆!");
                    return "";
                }

                if (tel == null || tel.equals("")){
                    AlertDialogUtil.showAlertDialog(context,"提示", "未获取到手机号，请退出重新登陆!");
                    return "";
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userCode", userCode);
                    jsonObject.put("PhoneNumber", tel);
//                    jsonObject.put("userCode", "CN000167");
//                    jsonObject.put("PhoneNumber", "15012312312");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return jsonObject.toString();
            }


        }, "android");


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode == REQUEST_SELECT_FILE)
            {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(context, "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

}
