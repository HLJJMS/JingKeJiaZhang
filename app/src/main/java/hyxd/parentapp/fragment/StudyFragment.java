package hyxd.parentapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import hyxd.parentapp.R;

/**
 * Created by wlm on 2018/3/27.
 */
//学习碎片
public class StudyFragment extends Fragment{

    private TextView onBack;
    private Context context;
    private TextView title_tv;

    private RadioGroup rg_class_select;
    private TextView tv_class_bg, tv_appraisal_bg;
    private LinearLayout ll_rb_line;

    private AppraisalChildFragment appraisalChildFragment;
    private ClassChildFragment classChildFragment;

    //声明碎片管理者
    private FragmentManager childFragmentManager;
    private FragmentTransaction ft;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        appraisalChildFragment = new AppraisalChildFragment();
        classChildFragment = new ClassChildFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_study, container, false);
        findView(contentView);
        initFragment();
        return contentView;
    }

    private void findView (View view){
        onBack = (TextView) view.findViewById(R.id.onBack);
        title_tv = (TextView) view.findViewById(R.id.common_title_tv);
        tv_appraisal_bg = (TextView) view.findViewById(R.id.tv_appraisal_bg);
        tv_class_bg = (TextView) view.findViewById(R.id.tv_class_bg);
        rg_class_select = (RadioGroup) view.findViewById(R.id.rg_class_select);
        ll_rb_line = (LinearLayout) view.findViewById(R.id.ll_rb_line);
        rg_class_select.setVisibility(View.GONE);
        ll_rb_line.setVisibility(View.GONE);
        title_tv.setText("学习");
        onBack.setVisibility(View.GONE);
        setRgChange();
    }


    private void setRgChange(){
        rg_class_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                switch (i){
                    case R.id.rb_class:

                        tv_class_bg.setVisibility(View.VISIBLE);
                        tv_appraisal_bg.setVisibility(View.INVISIBLE);
                        if (classChildFragment == null){
                            classChildFragment = new ClassChildFragment();
                        }
                        showFragmentAtPosition1(classChildFragment);
                        break;
                    case R.id.rb_appraisal:
                        tv_class_bg.setVisibility(View.INVISIBLE);
                        tv_appraisal_bg.setVisibility(View.VISIBLE);
                        if (appraisalChildFragment == null){
                            appraisalChildFragment = new AppraisalChildFragment();
                        }
                        showFragmentAtPosition1(appraisalChildFragment);
                        break;
                }


            }
        });



    }


    //初始化碎片
    private void initFragment() {
        //获取碎片管理者
        childFragmentManager = getChildFragmentManager();
        //开启碎片事物
        ft = childFragmentManager.beginTransaction();
        //处理事务 最后一次添加的为默认展示的碎片
        if (classChildFragment == null){
            classChildFragment = new ClassChildFragment();
        }
        ft.add(R.id.frame_layout_child, classChildFragment);

        //提交事务(汇报工作)
        ft.commit();
    }

    //展示指定碎片
    private void showFragmentAtPosition1(Fragment fragment) {
        //开启碎片事物
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        //提交事物
        ft.replace(R.id.frame_layout_child, fragment);
        ft.commit();

    }

}
