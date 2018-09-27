package hyxd.parentapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.util.ActivityManage;

//上传试卷界面
public class UploadTestActivity extends BaseActivity implements View.OnClickListener {

    private TextView onBack;

    private TextView title_tv;
    private TextView sure_tv;



    private Context context;
    private String userCode;

    private SharedPreferences spConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_test);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);


        findView();

    }


    private void findView(){
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        sure_tv = (TextView) findViewById(R.id.sure_tv);


        title_tv.setText("试卷分析");

        onBack.setOnClickListener(this);
        sure_tv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();
                break;

            case R.id.sure_tv:
               //提交
                break;
        }

    }
}
