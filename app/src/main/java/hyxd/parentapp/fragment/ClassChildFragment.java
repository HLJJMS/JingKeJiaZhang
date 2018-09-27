package hyxd.parentapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.activity.ClassDetailsActivity;
import hyxd.parentapp.activity.MainActivity;
import hyxd.parentapp.bean.ClassInfoBean;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.Constants;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.network.NetWorkRequest;
import hyxd.parentapp.util.AlertDialogUtil;
import hyxd.parentapp.xorzlistview.xlistview.XListView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by wlm on 2018/4/3.
 */
//学习里课程碎片
public class ClassChildFragment extends Fragment implements AdapterView.OnItemClickListener{

    private XListView listView;
    private TextView empty;
    private RadioGroup rg_class_type;

    private List<ClassInfoBean.MyDynamicDataBean> listData;
    private CommonAdapter adapter;
    private Context context;
    private MainActivity activity;
    private String userCode;
    //1 代表已结课 2 代表未结课
    private String status;

    private SharedPreferences spConfig;

    private String classNo;

    //当用户可见时实现懒加载数据
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        activity = (MainActivity) getActivity();
        spConfig = activity.getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        status = "2";
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
//        userCode = "SU0036396";
        userCode = "SU0038674";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_child_class, container, false);

        findView(contentView);

        return contentView;
    }

    private void findView(View view){

        listView = (XListView) view.findViewById(R.id.scheduling_lv);
        empty = (TextView) view.findViewById(R.id.empty);
        rg_class_type = (RadioGroup) view.findViewById(R.id.rg_class_type);

        listView.setEmptyView(empty);

        listData = new ArrayList<>();

        adapter = new CommonAdapter<ClassInfoBean.MyDynamicDataBean>(context, listData, R.layout.item_child_class) {
            @Override
            public void convert(ViewHolder holder, final ClassInfoBean.MyDynamicDataBean rowsBean) {
                int i = listData.indexOf(rowsBean);
                String st_season = "";
                st_season = rowsBean.getF0004();
                String st_subject = "";
                st_subject = rowsBean.getF0013();

                if (("春").equals(st_season)){
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_spring);
                }else if (("秋").equals(st_season)){
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_autumn);
                }else if (("寒").equals(st_season)){
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_cold);
                }else{
                    holder.setImageResource(R.id.iv_season, R.drawable.iv_heat);
                }

                if (st_subject.equals("语文")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_language);
                }else if (st_subject.equals("数学")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_math);
                }else if (st_subject.equals("英语")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_english);
                }else if (st_subject.equals("物理")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_physics);
                }else if (st_subject.equals("化学")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_chemistry);
                }else if (st_subject.equals("生物")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_biology);
                }else if (st_subject.equals("历史")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_hostory);
                }else if (st_subject.equals("地理")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_geography);
                }else if (st_subject.equals("政治")){
                    holder.setImageResource(R.id.iv_subject, R.drawable.iv_political);
                }

                holder.setText(R.id.tv_class_name, rowsBean.getClassName());
                holder.setText(R.id.tv_main_name, rowsBean.getTTeacherName());
                holder.setText(R.id.tv_help_name, rowsBean.getBTeacherName());

                String st_start = "";
                st_start = rowsBean.getPeriod_From();
                String st_end = "";
                st_end = rowsBean.getPeriod_To();
                if (st_start.length() >= 10 && st_end.length() >= 10){
                    holder.setText(R.id.tv_time_date, st_start.substring(0,10) + "~" + st_end.substring(0,10));
                }
//                holder.setText(R.id.tv_class_time, rowsBean.getClassTime());

            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
//        listView.setXListViewListener(this);
        listView.setPullLoadEnable(false);//激活加载更多
        listView.setPullRefreshEnable(false);

        setRgChange();


        if (userCode != null && !userCode.equals("")){
            getClassListTask task = new getClassListTask();
            task.execute();
        }else {
            AlertDialogUtil.showAlertDialog(context,"提示", "未获取到用户编码，请退出重新登录");
        }

    }


    private void setRgChange(){
        rg_class_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                switch (i){
                    case R.id.rb_no_finish:
                        status = "2";
                        listData.clear();
                        adapter.setData(listData);
                        getClassListTask task = new getClassListTask();
                        task.execute();

                        break;
                    case R.id.rb_finish:
                        status = "1";
                        listData.clear();
                        adapter.setData(listData);
                        getClassListTask task1 = new getClassListTask();
                        task1.execute();
                        break;
                }


            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        classNo = listData.get(position-1).getClassNo();

        String TTeacher = listData.get(position-1).getTTeacherName();
        String BTeacher = listData.get(position-1).getBTeacherName();
        String className = listData.get(position-1).getClassName();

        Intent intent = new Intent(context, ClassDetailsActivity.class);
        intent.putExtra("ClassNo", classNo);
        intent.putExtra("tTeacher", TTeacher);
        intent.putExtra("bTeacher", BTeacher);
        intent.putExtra("ClassName", className);
        startActivity(intent);


    }

    //请求课程列表
    private class getClassListTask extends AsyncTask<Void, Void, String> {

        String result = "";
        Handler handler;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity.showLoading();
            handler = NetWorkRequest.getHandler();

        }

        @Override
        protected String doInBackground(Void... arg0) {
            // 刷新时，重置地址

            String data = "CompanyCode=" + Constants.CPCode + "&" + "state=" + status + "&" + "UserCode=" + userCode;

            String url = Constants.HTTP_URL + Constants.GetClassListMN + "?" + data;
            Log.e("okhttp", url);
            String result="";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            Log.e("结果", result);
            activity.closeLoading();

            if (result != null && !result.equals("")){
                if (!result.contains("code")){
                    Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");

                if (code != null && code.equals("0")){
                    Gson gson = new Gson();
                    ClassInfoBean classInfoBean = gson.fromJson(jsonObject.toJSONString(), ClassInfoBean.class);
                    if (classInfoBean != null){
                        if (classInfoBean.getMyDynamicData().size() != 0){
                            listData.addAll(classInfoBean.getMyDynamicData());
                            adapter.setData(listData);
                            adapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(context, "暂无课程信息", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(context, "请求失败，数据格式有误", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();

            }

        }
    }


}