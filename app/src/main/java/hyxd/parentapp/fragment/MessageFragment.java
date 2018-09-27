package hyxd.parentapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hyxd.parentapp.R;

/**
 * Created by wlm on 2018/3/27.
 */
//消息碎片
public class MessageFragment extends Fragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragmen_message, container, false);

        return contentView;
    }



}
