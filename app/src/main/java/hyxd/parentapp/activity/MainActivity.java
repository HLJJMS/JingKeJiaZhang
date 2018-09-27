package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.netease.nim.uikit.business.recent.RecentContactsFragment;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.auth.LoginInfo;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.fragment.ClassFragment;
import hyxd.parentapp.fragment.MyFragment;
import hyxd.parentapp.fragment.ServeFragment;
import hyxd.parentapp.fragment.StudyFragment;
import hyxd.parentapp.im.DemoCache;
import hyxd.parentapp.im.LogoutHelper;
import hyxd.parentapp.im.preference.Preferences;
import hyxd.parentapp.util.ActivityManage;

import static com.netease.nimlib.sdk.NIMClient.getService;

public class MainActivity extends BaseActivity {

    private Context context;
    private Handler mHandler;

    private RadioGroup rg_bottom_bar;

    //声明碎片管理者
    private FragmentManager fragmentManager;

    private RecentContactsFragment messageFragment;
    private ClassFragment classFragment;
    private StudyFragment studyFragment;
    private ServeFragment serveFragment;
    private MyFragment myFragment;

    private SharedPreferences spConfig;
    private boolean isClassFragment;
    private boolean isMyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        ActivityManage.addActivity(this);
        mHandler = new Handler();

        isClassFragment = false;
        isMyFragment = false;

        findView();
        initFragment();

        registerObservers(true);


    }



    private void findView(){
        rg_bottom_bar = (RadioGroup) findViewById(R.id.rg_bottom_bar);

        rg_bottom_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.rb_message:
                        isClassFragment = false;
                        isMyFragment = false;
                        if (messageFragment == null){
                            messageFragment.onDestroy();
                        }
                        messageFragment = new RecentContactsFragment();
                        showFragmentAtPosition1(messageFragment);
                        break;
                    case R.id.rb_class:
                        isClassFragment = true;
                        isMyFragment = false;
                        if (classFragment == null){
                            classFragment = new ClassFragment();
                        }

                        showFragmentAtPosition1(classFragment);
                        break;
                    case R.id.rb_study:
                        isClassFragment = false;
                        isMyFragment = false;
                        if (studyFragment == null){
                            studyFragment = new StudyFragment();
                        }

                        showFragmentAtPosition1(studyFragment);

                        break;
                    case R.id.rb_serve:
                        isClassFragment = false;
                        isMyFragment = false;
                        if (serveFragment == null){
                            serveFragment = new ServeFragment();
                        }

                        showFragmentAtPosition1(serveFragment);

                        break;
                    case R.id.rb_my:
                        isClassFragment = false;
                        isMyFragment = true;
                        if (myFragment == null){
                            myFragment = new MyFragment();
                        }

                        showFragmentAtPosition1(myFragment);

                        break;

                }

            }
        });

    }


    //初始化碎片
    private void initFragment() {
        //获取碎片管理者
        fragmentManager = getSupportFragmentManager();
        //开启碎片事物
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //处理事务 最后一次添加的为默认展示的碎片
        if (messageFragment == null){
            messageFragment = new RecentContactsFragment();
            // 设置要集成联系人列表fragment的布局文件

        }
        ft.add(R.id.frame_layout, messageFragment);

        //提交事务(汇报工作)
        ft.commit();

    }


    //展示指定碎片
    private void showFragmentAtPosition1(Fragment fragment) {
        //开启碎片事物
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //提交事物
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();

    }

    private void registerObservers(boolean register) {
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(userStatusObserver, register);
    }


    /**
     * 用户状态变化
     */
    Observer<StatusCode> userStatusObserver = new Observer<StatusCode>() {

        @Override
        public void onEvent(StatusCode code) {
            if (code.wontAutoLogin()) {

                kickOut(code);
            } else {
                if (code == StatusCode.NET_BROKEN) {
                    Toast.makeText(context, "当前网络不可用", Toast.LENGTH_SHORT).show();
                } else if (code == StatusCode.UNLOGIN) {
                    Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();
                    logingWy();
                } else if (code == StatusCode.CONNECTING) {
                    Toast.makeText(context, "连接中...", Toast.LENGTH_SHORT).show();

                } else if (code == StatusCode.LOGINING) {
                    Toast.makeText(context, "登录中...", Toast.LENGTH_SHORT).show();

                } else {

                }
            }
        }
    };

    private void kickOut(StatusCode code) {
        Preferences.saveUserToken("");

        if (code == StatusCode.PWD_ERROR) {
            Toast.makeText(context, "账号或密码错误", Toast.LENGTH_SHORT).show();
        } else {

        }
        // 清理缓存&注销监听&清除状态
        LogoutHelper.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy() {
        registerObservers(false);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            //选课碎片里webview
            if (isClassFragment){
                WebView webView = classFragment.webView;
                if (webView.canGoBack()) {
                    webView.goBack(); //goBack()表示返回WebView的上一页面
                }else {
                    return super.onKeyDown(keyCode, event);
                }
                return true;
            }

            //我的碎片里webview
            if (isMyFragment){
                WebView webView = myFragment.webView;
                if (webView.canGoBack()) {
                    webView.goBack(); //goBack()表示返回WebView的上一页面
                }else {
                    return super.onKeyDown(keyCode, event);
                }
                return true;
            }

        }

        return super.onKeyDown(keyCode, event);
    }


    /**
     * 登陆网易云信(自动登录)
     */
    private void logingWy(){

        final String account = spConfig.getString(SPCommonInfoBean.userName, "");
        final String token = spConfig.getString(SPCommonInfoBean.token, "");

        LoginInfo info = new LoginInfo(account, token); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        DemoCache.setAccount(account);
                        saveLoginInfo(account, token);

                    }

                    @Override
                    public void onFailed(int code) {
                        Toast.makeText(MainActivity.this, "登录失败,请重新登陆", Toast.LENGTH_SHORT).show();
                        spConfig.edit().clear().commit();
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onException(Throwable exception) {
                        Toast.makeText(MainActivity.this, "登录异常,请重新登陆", Toast.LENGTH_SHORT).show();
                        spConfig.edit().clear().commit();
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                };
        getService(AuthService.class).login(info)
                .setCallback(callback);
    }

    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }

}
