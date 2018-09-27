package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import hyxd.parentapp.R;
import hyxd.parentapp.bean.ThisClassroomFeedDetailsBean;
import hyxd.parentapp.bean.ThisClassroomFeedbackBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//课堂反馈详情界面
public class FBackDetailsActivity extends BaseActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.rl_back)
    RelativeLayout rlBack;
    @InjectView(R.id.onBack)
    TextView onBack;
    @InjectView(R.id.iv_back1)
    ImageView ivBack1;
    @InjectView(R.id.rl_back1)
    RelativeLayout rlBack1;
    @InjectView(R.id.common_title_tv)
    TextView commonTitleTv;
    @InjectView(R.id.iv_home)
    ImageView ivHome;
    @InjectView(R.id.rl_home)
    RelativeLayout rlHome;
    @InjectView(R.id.tv_cancle_edit)
    TextView tvCancleEdit;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;
    @InjectView(R.id.rl_add)
    RelativeLayout rlAdd;
    @InjectView(R.id.ll_head_home)
    RelativeLayout llHeadHome;
    @InjectView(R.id.rl_title)
    RelativeLayout rlTitle;
    @InjectView(R.id.et_teacher_content)
    EditText etTeacherContent;
    @InjectView(R.id.tv_stu_name)
    TextView tvStuName;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_into_correct)
    TextView tvIntoCorrect;
    @InjectView(R.id.tv_into_error)
    TextView tvIntoError;
    @InjectView(R.id.tv_into_kp)
    TextView tvIntoKp;
    @InjectView(R.id.tv_out_correct)
    TextView tvOutCorrect;
    @InjectView(R.id.tv_out_error)
    TextView tvOutError;
    @InjectView(R.id.tv_out_kp)
    TextView tvOutKp;
    @InjectView(R.id.ll_five_star)
    LinearLayout llFiveStar;
    @InjectView(R.id.ll_five_star1)
    LinearLayout llFiveStar1;
    @InjectView(R.id.ll_four_star)
    LinearLayout llFourStar;
    @InjectView(R.id.ll_four_star1)
    LinearLayout llFourStar1;
    @InjectView(R.id.ll_three_star)
    LinearLayout llThreeStar;
    @InjectView(R.id.ll_three_star1)
    LinearLayout llThreeStar1;
    @InjectView(R.id.ll_two_star)
    LinearLayout llTwoStar;
    @InjectView(R.id.ll_two_star1)
    LinearLayout llTwoStar1;
    @InjectView(R.id.ll_one_star)
    LinearLayout llOneStar;
    @InjectView(R.id.ll_one_star1)
    LinearLayout llOneStar1;
    @InjectView(R.id.et_stu_comment)
    EditText etStuComment;
    @InjectView(R.id.ll_last_work3)
    LinearLayout llLastWork3;
    @InjectView(R.id.ll_last_five_star)
    LinearLayout llLastFiveStar;
    @InjectView(R.id.ll_last_five_star1)
    LinearLayout llLastFiveStar1;
    @InjectView(R.id.ll_last_four_star)
    LinearLayout llLastFourStar;
    @InjectView(R.id.ll_last_four_star1)
    LinearLayout llLastFourStar1;
    @InjectView(R.id.ll_last_three_star)
    LinearLayout llLastThreeStar;
    @InjectView(R.id.ll_last_three_star1)
    LinearLayout llLastThreeStar1;
    @InjectView(R.id.ll_last_two_star)
    LinearLayout llLastTwoStar;
    @InjectView(R.id.ll_last_two_star1)
    LinearLayout llLastTwoStar1;
    @InjectView(R.id.ll_last_one_star)
    LinearLayout llLastOneStar;
    @InjectView(R.id.ll_last_one_star1)
    LinearLayout llLastOneStar1;
    @InjectView(R.id.ll_last_work2)
    LinearLayout llLastWork2;
    @InjectView(R.id.et_last_comment)
    EditText etLastComment;
    @InjectView(R.id.tv_last_total)
    TextView tvLastTotal;
    @InjectView(R.id.tv_last_correct)
    TextView tvLastCorrect;
    @InjectView(R.id.tv_last_error)
    TextView tvLastError;
    @InjectView(R.id.tv_last_kp)
    TextView tvLastKp;
    @InjectView(R.id.ll_last_work)
    LinearLayout llLastWork;
    @InjectView(R.id.tv_current_kp)
    TextView tvCurrentKp;
    @InjectView(R.id.tv_current_item)
    TextView tvCurrentItem;
    @InjectView(R.id.tv_current_total)
    TextView tvCurrentTotal;
    @InjectView(R.id.et_current_comment)
    EditText etCurrentComment;
    @InjectView(R.id.et_other)
    EditText etOther;
    @InjectView(R.id.log_bt)
    Button logBt;
    @InjectView(R.id.last_work)
    TextView lastWork;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Context context;
    private String userCode,title,classId,classNo,TTeacherNo,F0013;
    private SharedPreferences spConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fback_details);
        ButterKnife.inject(this);
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        userCode = "SU0038674";
        Intent intent = getIntent();
        classNo = intent.getStringExtra("classNo");
        TTeacherNo = intent.getStringExtra("TTeacherNo");
        classId =  intent.getStringExtra("classId");
        F0013 = intent.getStringExtra("F0013");
        commonTitleTv.setText(intent.getStringExtra("title"));
        context = this;
        ThisClassroomFeedback thisClassroomFeedback = new ThisClassroomFeedback();
        thisClassroomFeedback.execute();
        ThisClassroomFeedDetails thisClassroomFeedDetails = new ThisClassroomFeedDetails();
        thisClassroomFeedDetails.execute();
    }


    @OnClick(R.id.onBack)
    public void onViewClicked() {
        finish();
    }

    private class ThisClassroomFeedback extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
//            handler = NetWorkRequest.getHandler();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String ok = "";
            String url = Constants.HTTP_URL + Constants.ThisClassroomFeedback + "?" + "StudentNo=" + userCode + "&LessonCode=" + classId;
            Log.e("okhttp", url);
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {

                e.printStackTrace();
            }
            return ok;
        }

        protected void onPostExecute(String ok) {
            closeLoading();
            Log.e("结果", ok);
            if (null != ok && !"".equals(ok)) {
                if (!ok.contains("code")) {
                    Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject j = JSONObject.parseObject(ok);
                String code = j.getString("code");
                String message = j.getString("message");
                if (null != code && !"".equals(code)) {
                    Gson gson = new Gson();
                    ThisClassroomFeedbackBean bean = gson.fromJson(j.toJSONString(), ThisClassroomFeedbackBean.class);
                    if (bean != null) {
                        if (bean.getMyDynamicData().size() != 0) {
                            etTeacherContent.setText(bean.getMyDynamicData().get(0).getTeachingRecords());
                            etOther.setText(bean.getMyDynamicData().get(0).getOther());
                            tvStuName.setText(bean.getMyDynamicData().get(0).getStudent_Name());
                            etStuComment.setText(bean.getMyDynamicData().get(0).getThisMaster());
                            etCurrentComment.setText(bean.getMyDynamicData().get(0).getThisExplain());
                            tvTime.setText(bean.getMyDynamicData().get(0).getStartTime());
                            if (null != bean.getMyDynamicData().get(0).getLastMaster() && !"".equals(bean.getMyDynamicData().get(0).getLastMaster())) {
                                etLastComment.setText(bean.getMyDynamicData().get(0).getLastMaster());
                                switch (bean.getMyDynamicData().get(0).getLastTime()) {
                                    case "1":
                                        llLastOneStar.setVisibility(View.GONE);
                                        llLastOneStar1.setVisibility(View.VISIBLE);
                                        break;
                                    case "2":
                                        llLastTwoStar.setVisibility(View.GONE);
                                        llLastTwoStar1.setVisibility(View.VISIBLE);
                                        break;
                                    case "3":
                                        llLastThreeStar.setVisibility(View.GONE);
                                        llLastThreeStar1.setVisibility(View.VISIBLE);
                                        break;
                                    case "4":
                                        llLastFourStar.setVisibility(View.GONE);
                                        llLastFourStar1.setVisibility(View.VISIBLE);
                                        break;
                                    case "5":
                                        llLastFiveStar.setVisibility(View.GONE);
                                        llLastFiveStar1.setVisibility(View.VISIBLE);
                                        break;
                                }
                            } else {
                                lastWork.setVisibility(View.GONE);
                                llLastWork2.setVisibility(View.GONE);
                                etLastComment.setVisibility(View.GONE);
                            }
                            switch (bean.getMyDynamicData().get(0).getThisTime()) {
                                case "1":
                                    llOneStar.setVisibility(View.GONE);
                                    llOneStar1.setVisibility(View.VISIBLE);
                                    break;
                                case "2":
                                    llTwoStar.setVisibility(View.GONE);
                                    llTwoStar1.setVisibility(View.VISIBLE);
                                    break;
                                case "3":
                                    llThreeStar.setVisibility(View.GONE);
                                    llThreeStar1.setVisibility(View.VISIBLE);
                                    break;
                                case "4":
                                    llFourStar.setVisibility(View.GONE);
                                    llFourStar1.setVisibility(View.VISIBLE);
                                    break;
                                case "5":
                                    llFiveStar.setVisibility(View.GONE);
                                    llFiveStar1.setVisibility(View.VISIBLE);
                                    break;
                            }
                        } else {
                            Toast.makeText(context, "暂无已课堂反馈信息", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(context, "请求失败，数据格式有误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class ThisClassroomFeedDetails extends AsyncTask<Void,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
//            handler = NetWorkRequest.getHandler();
        }
        @Override
        protected String doInBackground(Void... voids) {
            String ok="";
            String url = Constants.HTTP_URL + Constants.ThisClassroomFeedDetails + "?" +"classId="+classId+"&StudentNo="+userCode+"&subjects="+F0013;
            Log.e("okhttp",url);
            Request request = new  Request.Builder().url(url).build();

            try {
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok;
        }
        protected void onPostExecute(String ok) {
            Log.e("结果",ok);
            closeLoading();
            if(null!=ok&&!"".equals(ok)){
                JSONObject j =JSONObject.parseObject(ok);
                String code = j.getString("code");
                String message = j.getString("message");
                if(null!=code&&!"".equals(code)&&"0".equals(code)){
                    Gson gson = new Gson();
                    ThisClassroomFeedDetailsBean bean = gson.fromJson(j.toJSONString(), ThisClassroomFeedDetailsBean.class);
                    if(null!=bean.getMyDynamicData()){
                        if(null!=bean.getMyDynamicData().getTable()&&0!=bean.getMyDynamicData().getTable().size()){
                            tvIntoCorrect.setText(bean.getMyDynamicData().getTable().get(0).getCurrCount());
                            tvIntoError.setText(bean.getMyDynamicData().getTable().get(0).getErrorCount());
                            tvOutCorrect.setText(bean.getMyDynamicData().getTable().get(1).getCurrCount());
                            tvOutError.setText(bean.getMyDynamicData().getTable().get(1).getErrorCount());
                        }
                        if(null!=bean.getMyDynamicData().getTable1()&&0!=bean.getMyDynamicData().getTable1().size()){
                            tvCurrentKp.setText(bean.getMyDynamicData().getTable1().get(0).getChaptername());
                            tvCurrentTotal.setText(bean.getMyDynamicData().getTable1().get(0).getQidcount());
                        }
                        if(null!=bean.getMyDynamicData().getTable2()&&0!=bean.getMyDynamicData().getTable2().size()){
                            tvLastTotal.setText(bean.getMyDynamicData().getTable2().get(0).getECount());
                            tvLastCorrect.setText(bean.getMyDynamicData().getTable2().get(0).getCurrCount());
                            tvLastError.setText(bean.getMyDynamicData().getTable2().get(0).getErrorCount());
                        }



                    }else{
                        Toast.makeText(context, "暂无作业反馈信息", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
