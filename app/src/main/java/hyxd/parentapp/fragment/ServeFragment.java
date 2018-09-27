package hyxd.parentapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import hyxd.parentapp.R;
import hyxd.parentapp.activity.DownloadFileActivity;
import hyxd.parentapp.activity.FeedBackActivity;
import hyxd.parentapp.activity.QuestionAnswerActivity;
import hyxd.parentapp.activity.TestAnalysisActivity;

/**
 * Created by wlm on 2018/3/27.
 */
//服务碎片
public class ServeFragment extends Fragment implements View.OnClickListener{

    private TextView onBack;
    private Context context;
    private TextView title_tv;

    private LinearLayout ll_feed_back, ll_test, ll_question_answer, ll_download;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_serve, container, false);

        findView(contentView);

        return contentView;

    }

    private void findView (View view){
        onBack = (TextView) view.findViewById(R.id.onBack);
        title_tv = (TextView) view.findViewById(R.id.common_title_tv);
        ll_feed_back = (LinearLayout) view.findViewById(R.id.ll_feed_back);
        ll_test = (LinearLayout) view.findViewById(R.id.ll_test);
        ll_question_answer = (LinearLayout) view.findViewById(R.id.ll_question_answer);
        ll_download = (LinearLayout) view.findViewById(R.id.ll_download);

        title_tv.setText("服务");
        ll_test.setVisibility(View.GONE);
        ll_question_answer.setVisibility(View.GONE);
        ll_download.setVisibility(View.GONE);

        onBack.setVisibility(View.GONE);
        ll_feed_back.setOnClickListener(this);
        ll_test.setOnClickListener(this);
        ll_question_answer.setOnClickListener(this);
        ll_download.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_feed_back:
                Intent intent = new Intent(context, FeedBackActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_test:
                Intent intent1 = new Intent(context, TestAnalysisActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_question_answer:
                Intent intent2 = new Intent(context, QuestionAnswerActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_download:
                Intent intent3 = new Intent(context, DownloadFileActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
