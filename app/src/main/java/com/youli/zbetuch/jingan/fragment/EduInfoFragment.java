package com.youli.zbetuch.jingan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.CommonAdapter;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.EduInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class EduInfoFragment extends Fragment{

    private  View contentView;
    private ListView lv;
    private List<EduInfo> data=new ArrayList<>();
    private CommonAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=LayoutInflater.from(getContext()).inflate(R.layout.framgment_education_info,container,false);

        initView(contentView);

        return contentView;
    }

    private void initView(View view){

        data.add(new EduInfo("学校名称1","大专","计算机","2017-08-15","2017-08-15"));
        data.add(new EduInfo("学校名称2","大专","计算机","2017-08-15","2017-08-15"));
        data.add(new EduInfo("学校名称3","大专","计算机","2017-08-15","2017-08-15"));
        lv= (ListView) view.findViewById(R.id.lv_fmt_education_info);
        adapter=new CommonAdapter<EduInfo>(getActivity(),data,R.layout.item_fmt_education_info_lv) {

            @Override
            public void convert(CommonViewHolder holder, EduInfo item, int position) {

                TextView name=holder.getView(R.id.tv_item_fmt_education_info_name);
                name.setText(item.getName());
                TextView edu=holder.getView(R.id.tv_item_fmt_education_info_edu);
                edu.setText(item.getEdu());
                TextView major=holder.getView(R.id.tv_item_fmt_education_info_major);
                major.setText(item.getMajor());
                TextView startTime=holder.getView(R.id.tv_item_fmt_education_info_starttime);
                startTime.setText(item.getStartTime());
                TextView endTime=holder.getView(R.id.tv_item_fmt_education_info_endtime);
                endTime.setText(item.getEndTime());
            }
        };

        lv.setAdapter(adapter);

    }

}
