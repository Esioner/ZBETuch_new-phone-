package com.youli.zbetuch.jingan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class PersonReFragment extends Fragment{

    private View contentView;

    private Button findWorkBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        contentView=LayoutInflater.from(getContext()).inflate(R.layout.framgment_personal_resume,container,false);

        initView(contentView);


        return contentView;
    }

    private void initView(View view){

        findWorkBtn= (Button) view.findViewById(R.id.btn_person_resume_find_work);
        findWorkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"查找对应工作",Toast.LENGTH_SHORT).show();

            }
        });

    }

}
