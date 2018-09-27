package hyxd.parentapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hyxd.parentapp.R;
import hyxd.parentapp.common.BaseActivity;
import hyxd.parentapp.common.CommonAdapter;
import hyxd.parentapp.common.SPCommonInfoBean;
import hyxd.parentapp.common.ViewHolder;
import hyxd.parentapp.util.ActivityManage;
import hyxd.parentapp.xorzlistview.xlistview.XListView;

//我的订单界面
public class MyOrderActivity extends BaseActivity implements View.OnClickListener {

    private XListView listView;
    private TextView empty;
    private TextView onBack;

    private TextView title_tv;

    private RadioGroup rg_order_select, rg_order_select1;

    private List<String> listData;
    private CommonAdapter adapter;
    private Context context;
    private String userCode;

    private SharedPreferences spConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        context = this;
        spConfig = getSharedPreferences(SPCommonInfoBean.SPName, MODE_PRIVATE);
        userCode = spConfig.getString(SPCommonInfoBean.userCode,"");
        ActivityManage.addActivity(this);

        findView();


    }


    private void findView(){
        listView = (XListView) findViewById(R.id.scheduling_lv);
        empty = (TextView) findViewById(R.id.empty);
        onBack = (TextView) findViewById(R.id.onBack);
        title_tv = (TextView) findViewById(R.id.common_title_tv);
        rg_order_select = (RadioGroup) findViewById(R.id.rg_order_select);
        rg_order_select1 = (RadioGroup) findViewById(R.id.rg_order_select1);

        title_tv.setText("我的订单");
        listView.setEmptyView(empty);

        listData = new ArrayList<>();
        listData.add("dddddddddd");
        listData.add("eeeeeee");
        listData.add("cccc");

        adapter = new CommonAdapter<String>(context, listData, R.layout.item_order) {
            @Override
            public void convert(ViewHolder holder, final String rowsBean) {
                int i = listData.indexOf(rowsBean);

            }
        };

        listView.setAdapter(adapter);
//        listView.setXListViewListener(this);
        listView.setPullLoadEnable(false);//激活加载更多
        listView.setPullRefreshEnable(false);
        onBack.setOnClickListener(this);

        setRgChange();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBack:
                finish();

                break;

        }

    }


    private void setRgChange(){
        rg_order_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.rb_all:
                        //全部
                        rg_order_select1.check(R.id.rb_all1);

                        break;
                    case R.id.rb_pay:
                        //已付款
                        rg_order_select1.check(R.id.rb_pay1);
                        break;
                    case R.id.rb_no_pay:
                        //未付款
                        rg_order_select1.check(R.id.rb_no_pay1);
                        break;
                    case R.id.rb_cancel:
                        //已取消
                        rg_order_select1.check(R.id.rb_cancel1);

                        break;

                }


            }
        });



    }



}
