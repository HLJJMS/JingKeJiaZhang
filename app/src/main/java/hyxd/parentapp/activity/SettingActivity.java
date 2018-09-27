package hyxd.parentapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.im.LogoutHelper;
import hyxd.parentapp.im.preference.Preferences;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.util.AlertDialogUtil;

//设置页
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private TextView empty;
    private TextView onBack;

    private TextView title_tv;
    private Context context;
    private String userCode;

    private SharedPreferences spConfig;

    private LinearLayout ll_clear_cache, ll_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);

        findView();
    }

    private void findView(){

        empty = (TextView) findViewById(R.id.empty);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        ll_clear_cache = (LinearLayout) findViewById(R.id.ll_clear_cache);
        ll_exit = (LinearLayout) findViewById(R.id.ll_exit);


        title_tv.setText("设置");

        onBack.setOnClickListener(this);
        ll_clear_cache.setOnClickListener(this);
        ll_exit.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();
                break;
            case R.id.ll_exit:
                //退出登录
                initUser();
                break;
            case R.id.ll_clear_cache:
                //清除缓存
                AlertDialogUtil.showAlertDialog(context, "提示", "该功能正在开发中，敬请期待!");
                break;

        }

    }


    /**
     * 注销登录
     */
    private void initUser(){
        AlertDialogUtil.showAlertDialog(context, "提示", "是否注销当前账号", new AlertDialogUtil.ClickListener() {
            @Override
            public void positionClick() {
                Preferences.saveUserToken("");
                Preferences.saveUserAccount("");
                // 清理缓存&注销监听&清除状态
                LogoutHelper.logout();

                spConfig.edit().clear().commit();
                ActivityManage.finishAll();
                System.exit(0);
            }

            @Override
            public void positionClick(String content) {

            }
            @Override
            public void negetiveClick() {

            }
        });
    }

}
