package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.im.preference.Preferences;
import hyxd.parentapp.util.ActivityManage;
//import static com.netease.nimlib.sdk.NIMClient.getService;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener{

    private TextView tv_logo, tv_register;
    private Context context;
    private SharedPreferences spConfig;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        context = this;
        ActivityManage.addActivity(this);
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);

        findView();
        isLog();

    }


    private void findView (){
        tv_logo = (TextView) findViewById(R.id.tv_logo);
        tv_register = (TextView) findViewById(R.id.tv_register);

        tv_logo.setOnClickListener(this);
        tv_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.tv_logo:
                //登录
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.tv_register:
                Intent intent1 = new Intent(context, RegisterActivity.class);
                startActivity(intent1);
                finish();
                break;

        }

    }



    //判断要不要再走登录页
    public void isLog(){
        userName = spConfig.getString(SPCommonInfoBean.userName, "");
        String token = Preferences.getUserToken();
        String account = Preferences.getUserAccount();

        if ( userName == null || userName.equals("")){
            return;
        }
        if ( token == null || token.equals("")){
            return;
        }
        if ( account == null || account.equals("")){
            return;
        }

        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
