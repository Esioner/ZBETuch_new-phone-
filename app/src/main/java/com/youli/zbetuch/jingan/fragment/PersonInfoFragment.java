package com.youli.zbetuch.jingan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youli.zbetuch.jingan.R;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class PersonInfoFragment extends Fragment{

    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        contentView=LayoutInflater.from(getContext()).inflate(R.layout.framgment_personal_info,container,false);

        return contentView;
    }
}
