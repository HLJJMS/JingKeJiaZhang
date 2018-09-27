package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.MsgCodeBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.network.NetWorkRequest;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.util.AlertDialogUtil;
import hyxd.parentapp.util.PopupWindowUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//注册忘记密码界面
public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private TextView onBack;
    private TextView sure_tv;
    private Context context;
    private TextView title_tv;
    private LinearLayout ll_grade;

    private TextView tv_get_mesg;
    private EditText et_phone, et_mesg, et_password;

    private String st_phone, st_mesg, st_password, st_grade;

    //返回短信验证码，手机号暗码
    private String st_msg_code, st_phone_code;


    private TextView tv_grade;

    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActivityManage.addActivity(this);
        context = this;


        findView();

    }

    private void findView (){
        sure_tv = (TextView) findViewById(R.id.sure_tv);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        tv_get_mesg = (TextView) findViewById(R.id.tv_get_mesg);
        tv_grade = (TextView) findViewById(R.id.tv_grade);
        ll_grade = (LinearLayout) findViewById(R.id.ll_grade);
        et_mesg = (EditText) findViewById(R.id.et_mesg);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);


        title_tv.setText("用户注册");

        list = new ArrayList<>();
        list.add("小一");
        list.add("小二");
        list.add("小三");
        list.add("小四");
        list.add("小五");
        list.add("小六");
        list.add("初一");
        list.add("初二");
        list.add("初三");
        list.add("高一");
        list.add("高二");
        list.add("高三");

        ll_grade.setOnClickListener(this);
        sure_tv.setOnClickListener(this);
        tv_get_mesg.setOnClickListener(this);
        onBack.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.onBack:
                finish();
                break;
            case R.id.ll_grade:
                //登录
                showPopupWindow();
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
            case R.id.sure_tv:
                //注册
                st_phone = et_phone.getText().toString().trim();
                st_mesg = et_mesg.getText().toString().trim();
                st_password = et_password.getText().toString().trim();
                st_grade = tv_grade.getText().toString();

                if (st_phone == null || st_phone.equals("")){
                    AlertDialogUtil.showAlertDialog(context, "提示", "请输入手机号");
                    return;
                }
                if (st_mesg == null || st_mesg.equals("")){
                    AlertDialogUtil.showAlertDialog(context, "提示", "请获取短信验证码，并输入");
                    return;
                }
                if (st_password == null || st_password.equals("")){
                    AlertDialogUtil.showAlertDialog(context, "提示", "请输入密码");
                    return;
                }

                RegisterInfo registerInfo = new RegisterInfo();
                registerInfo.execute();
                break;
        }


    }



    private void showPopupWindow(){

        View view = LayoutInflater.from(context).inflate(R.layout.pop_view1, null);

        final PopupWindow popupWindow = PopupWindowUtil.getPopuWindow(context,view,1,3,0.5f,-1);

        ListView listView = (ListView) view.findViewById(R.id.lv);
        CommonAdapter adapter = new CommonAdapter<String>(context, list, R.layout.item_pop_lv) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.item_pop_tv,s);
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_grade.setText(list.get(i));
                popupWindow.dismiss();
            }
        });

        PopupWindowUtil.showAtLoactionBottom(ll_grade);

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
                    Toast.makeText(RegisterActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RegisterActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }


    //用户注册
    private class RegisterInfo extends AsyncTask<Void, Void, String> {

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
                    + "&" + "grade=" + st_grade + "&" + "TelPhone="
                    + st_phone_code + "&" + "yzmCode=" + st_msg_code;

            String url = Constants.HTTP_URL + Constants.RegisterMN + "?" + data;
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
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");

                if (code != null && code.equals("0")){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }else {
                    tv_get_mesg.setEnabled(true);
                    tv_get_mesg.setText("重新获取验证码");
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

        }
    }


}
