package hyxd.parentapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.bean.PersonalClassFeedBackBean;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.xorzlistview.xlistview.XListView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//课堂反馈讲界面
public class FBackSpeachActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private XListView listView;
    private TextView empty;
    private TextView onBack;

    private TextView title_tv;
    private final OkHttpClient client = new OkHttpClient();
private List<PersonalClassFeedBackBean.MyDynamicDataBean> listData = new ArrayList<PersonalClassFeedBackBean.MyDynamicDataBean>();
//    private List<String> listData;
    private CommonAdapter adapter;
    private Context context;
    private String userCode,title,classNo,TTeacherNo,F0013;

    private SharedPreferences spConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fback_speach);
        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        userCode = "SU0038674";
        ActivityManage.addActivity(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        classNo = intent.getStringExtra("classNo");
        F0013 = intent.getStringExtra("F0013");
        TTeacherNo = intent.getStringExtra("TTeacherNo");
        findView();


    }

    private void findView(){
        listView = (XListView) findViewById(R.id.scheduling_lv);
        empty = (TextView) findViewById(R.id.empty);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        title_tv.setText(title);
        listView.setEmptyView(empty);

        adapter = new CommonAdapter<PersonalClassFeedBackBean.MyDynamicDataBean>(context, listData, R.layout.item_fback_speach) {

            @Override
            public void convert(ViewHolder holder, PersonalClassFeedBackBean.MyDynamicDataBean myDynamicDataBean) {
                holder.setText(R.id.tv_class_name,myDynamicDataBean.getSpeakName());
                holder.setText(R.id.tv_time_date,String.valueOf(myDynamicDataBean.getTimes()+" "+myDynamicDataBean.getTimestar()+" -- "+myDynamicDataBean.getTimeend()));
                holder.setText(R.id.tv_main_name,myDynamicDataBean.getStudents());

            }
        };

        listView.setAdapter(adapter);
//        listView.setXListViewListener(this);
        listView.setPullLoadEnable(false);//激活加载更多
        listView.setPullRefreshEnable(false);
        listView.setOnItemClickListener(this);
        onBack.setOnClickListener(this);
        PersonalClassFeedBack t = new PersonalClassFeedBack();
        t.execute();

    }
    //网络请求
    private  class PersonalClassFeedBack extends AsyncTask<Void,Void,String>{
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading();
//            handler = NetWorkRequest.getHandler();

        }

        @Override
        protected String doInBackground(Void... voids) {
            String ok ="";
            String url = Constants.HTTP_URL + Constants.GetPersonalClassFeedBack + "?" + "StudentNo=" +userCode + "&ClassNo=" + classNo ;
            Log.e("okhttp",url);

            Request request = new Request.Builder().url(url).build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok;
        }
        protected  void onPostExecute (String ok){
            closeLoading();
            Log.e("结果",ok);
            if(null!=ok&&!"".equals(ok)){
                JSONObject j= JSONObject.parseObject(ok);
                String code = j.getString("code");
                if (null!=code&&"0".equals(code)){
                    Gson gson = new Gson();
                    PersonalClassFeedBackBean bean = gson.fromJson(j.toJSONString() , PersonalClassFeedBackBean.class);
                    if (bean != null){
                        if (bean.getMyDynamicData().size() != 0){
                            listData.addAll(bean.getMyDynamicData());
                            adapter.setData(listData);

                        }else {
                            Toast.makeText(context, "暂无已反馈课程信息", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(context, "请求失败，数据格式有误", Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }else{
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(FBackSpeachActivity.this, FBackDetailsActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("classNo",classNo);
        intent.putExtra("classId",listData.get(position-1).getClassId());
        intent.putExtra("TTeacherNo",TTeacherNo);
        intent.putExtra("F0013",F0013);
        startActivity(intent);
    }
}
