package com.youli.zbetuch.jingan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.CommonAdapter;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.ServiceReInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class ServiceReFramgent extends Fragment{

    private View contentview;
    private ListView lv;
    private List<ServiceReInfo> data=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {

        contentview=LayoutInflater.from(getContext()).inflate(R.layout.framgment_service_record,container,false);

         data.clear();
        data.add(new ServiceReInfo("张三","2017-8-14","职介指导","备注备注备注"));
        data.add(new ServiceReInfo("李四","2017-8-14","职介指导","备注备注备注"));
        lv= (ListView) contentview.findViewById(R.id.lv_fmt_service_record);


        lv.setAdapter(new CommonAdapter<ServiceReInfo>(getActivity(),data,R.layout.item_fmt_service_record_lv) {
            @Override
            public void convert(CommonViewHolder holder, ServiceReInfo item, int position) {

                TextView name=holder.getView(R.id.tv_item_fmt_service_record_name);
                name.setText(item.getName());
                TextView date=holder.getView(R.id.tv_item_fmt_service_record_date);
                date.setText(item.getDate());
                TextView content=holder.getView(R.id.tv_item_fmt_service_record_content);
                content.setText(item.getContent());
                TextView remarks=holder.getView(R.id.tv_item_fmt_service_record_remarks);
                remarks.setText(item.getRemarks());
            }
        });

        return contentview;
    }
}
