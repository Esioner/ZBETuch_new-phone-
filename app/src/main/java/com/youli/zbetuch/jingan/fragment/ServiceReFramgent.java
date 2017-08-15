package com.youli.zbetuch.jingan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.CommonAdapter;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.ServiceReInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class ServiceReFramgent extends Fragment {

    private View contentview;
    private ListView lv;
    private List<ServiceReInfo> data = new ArrayList<>();
    private Button newCreateBtn;
    private CommonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        contentview = LayoutInflater.from(getContext()).inflate(R.layout.framgment_service_record, container, false);

        initView(contentview);

        return contentview;
    }

    private void initView(View view) {

        newCreateBtn = (Button) view.findViewById(R.id.btn_service_record_new_create);
        newCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNewDialog();

            }
        });


        data.add(new ServiceReInfo("张三", "2017-8-14", "职介指导", "备注备注备注"));
        data.add(new ServiceReInfo("李四", "2017-8-14", "职介指导", "备注备注备注"));
        data.add(new ServiceReInfo("王五", "2017-8-15", "职介指导", "备注备注备注"));
        lv = (ListView) view.findViewById(R.id.lv_fmt_service_record);

        adapter = new CommonAdapter<ServiceReInfo>(getActivity(), data, R.layout.item_fmt_service_record_lv) {
            @Override
            public void convert(CommonViewHolder holder, ServiceReInfo item, final int position) {

                TextView name = holder.getView(R.id.tv_item_fmt_service_record_name);
                name.setText(item.getName());
                TextView date = holder.getView(R.id.tv_item_fmt_service_record_date);
                date.setText(item.getDate());
                TextView content = holder.getView(R.id.tv_item_fmt_service_record_content);
                content.setText(item.getContent());
                TextView remarks = holder.getView(R.id.tv_item_fmt_service_record_remarks);
                remarks.setText(item.getRemarks());

                Button btnModify = holder.getView(R.id.btn_item_fmt_service_record_modify);
                btnModify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity(), "修改，第" + position + "个", Toast.LENGTH_SHORT).show();

                    }
                });

                Button btnDelete = holder.getView(R.id.btn_item_fmt_service_record_delete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showDeleteDialog(position);


                    }
                });
            }
        };

        lv.setAdapter(adapter);//修改信息提示 您确定删除此项服务信息吗？

    }

    private void showDeleteDialog(final int i) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("修改信息提示");
        builder.setMessage("您确定删除此项服务信息吗？");
        builder.setNegativeButton("取消", null);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.remove(i);
                adapter.notifyDataSetChanged();
            }
        });

        builder.show();
    }

    private void showNewDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fmt_service_record_new,null);
        builder.setView(view);

        AlertDialog dialog=builder.create();
        dialog.show();


    }

}
