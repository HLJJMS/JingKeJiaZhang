package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.MsgCodeBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.network.NetWorkRequest;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.util.AlertDialogUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


//短信验证码登录
public class MsgLoginActivity extends BaseActivity implements View.OnClickListener{

    private TextView onBack;
    private TextView sure_tv;
    private Context context;
    private TextView tv_forget_password, tv_mesg;
    private EditText et_phone, et_mesg;
    private TextView title_tv;
    private TextView tv_get_mesg;

    private SharedPreferences spConfig;

    private String st_phone, st_mesg, st_password;

    private String token = "2";

    //返回短信验证码，手机号暗码
    private String st_msg_code, st_phone_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_login);

        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ActivityManage.addActivity(this);
        context = this;


        findView();
    }

    private void findView (){
        sure_tv = (TextView) findViewById(R.id.sure_tv);
        onBack = (TextView) findViewById(R.id.onBack);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        tv_mesg = (TextView) findViewById(R.id.tv_mesg);
        tv_get_mesg = (TextView) findViewById(R.id.tv_get_mesg);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_mesg = (EditText) findViewById(R.id.et_mesg);
        title_tv = (TextView) findViewById(R.id.common_title_tv);


        title_tv.setText("手机号登录");

        tv_forget_password.setOnClickListener(this);
        tv_mesg.setOnClickListener(this);
        onBack.setOnClickListener(this);
        sure_tv.setOnClickListener(this);
        tv_get_mesg.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.onBack:
                finish();
                break;
            case R.id.sure_tv:
                //登录
                st_phone = et_phone.getText().toString().trim();
                st_mesg = et_mesg.getText().toString().trim();

                if (st_phone == null || st_phone.equals("")){
                    AlertDialogUtil.showAlertDialog(context, "提示", "用户账号不能为空，请填写");
                    return;
                }
                if (st_mesg == null || st_mesg.equals("")){
                    AlertDialogUtil.showAlertDialog(context, "提示", "验证码不能为空，请填写");
                    return;
                }

                //请求网络登录并跳转
                //请求网络登录并跳转
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.execute();
                break;

            case R.id.tv_forget_password:

                Intent intent1 = new Intent(context, ForgetPassActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_mesg:

                Intent intent2 = new Intent(context, LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.tv_get_mesg:
                //获取短信验证码
                st_phone = et_phone.getText().toString().trim();
                if (st_phone == null || st_phone.equals("")){
                    AlertDialogUtil.showAlertDialog(context, "提示", "请输入手机号");
                    return;
                }

                GetInfo task1 = new GetInfo();
                task1.execute();

                break;

        }


    }

    //用户登录
    private class LoginInfo extends AsyncTask<Void, Void, String> {

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

            String data = "Company=" + Constants.CPCode + "&" +  "Tel=" + st_phone + "&" + "Yzm=" + st_mesg + "&" + "pwd=" + st_password
                    + "&" + "token=" + token + "&" + "TelPhone="
                    + st_phone_code + "&" + "yzmCode=" + st_msg_code;

            String url = Constants.HTTP_URL + Constants.LoginMN + "?" + data;
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

            Log.d("333333333333333", result);
            closeLoading();

            if (result != null && !result.equals("")){
                if (!result.contains("code")){
                    Toast.makeText(MsgLoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");

                if (code != null && code.equals("0")){
                    saveInfo(jsonObject);
                    Toast.makeText(MsgLoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MsgLoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

        }
    }

    //获取短信验证码
    private class GetInfo extends AsyncTask<Void, Void, String> {

        String result = "";
        Handler handler;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
            tv_get_mesg.setEnabled(false);
            tv_get_mesg.setText("请输入");
            handler = NetWorkRequest.getHandler();

        }

        @Override
        protected String doInBackground(Void... arg0) {
            // 刷新时，重置地址



            String url = Constants.HTTP_URL + Constants.GetMsgMN + "?" + "Tel=" + st_phone;
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

            Log.d("333333333333333", result);
            closeLoading();

            if (result != null && !result.equals("")){
                if (!result.contains("code")){
                    tv_get_mesg.setEnabled(true);
                    tv_get_mesg.setText("重新获取验证码");
                    Toast.makeText(MsgLoginActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");

                if (code != null && code.equals("0")){
                    Gson gson = new Gson();
                    MsgCodeBean msgCodeBean = gson.fromJson(result, MsgCodeBean.class);
                    st_msg_code = msgCodeBean.getMyDynamicData().getYzm();
                    st_phone_code = msgCodeBean.getMyDynamicData().getTel();
                }else {
                    tv_get_mesg.setEnabled(true);
                    tv_get_mesg.setText("重新获取验证码");
                    Toast.makeText(MsgLoginActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }


    /**
     * 保存用户信息
     * @param jsonObject
     */
    private void saveInfo(JSONObject jsonObject) {
        SharedPreferences.Editor editor = spConfig.edit();

        String st_user = jsonObject.getString("myDynamicData");
        JSONObject jsonObject1 = JSONObject.parseObject(st_user);

        editor.putString(SPCommonInfoBean.passWord, st_password);
        editor.putString(SPCommonInfoBean.userName, jsonObject1.getString("UserName"));
        editor.putString(SPCommonInfoBean.userCode, jsonObject1.getString("UserCode"));
        editor.putString(SPCommonInfoBean.tel, jsonObject1.getString("Tel"));

        editor.commit();

    }



}
