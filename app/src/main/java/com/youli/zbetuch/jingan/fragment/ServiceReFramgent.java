package com.youli.zbetuch.jingan.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.CommonAdapter;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.ServiceReInfo;
import com.youli.zbetuch.jingan.entity.ServiceTypeInfo;
import com.youli.zbetuch.jingan.utils.MyOkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class ServiceReFramgent extends Fragment {

    private final int SUCCESS=10000;

    private View contentview;
    private ListView lv;
    private List<ServiceReInfo> data = new ArrayList<>();
    private Button newCreateBtn;
   private  Spinner sp;
    private CommonAdapter adapter;

    private List<ServiceTypeInfo> spList=new ArrayList<>();

    private Handler mHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case SUCCESS:

                    spList.add(new ServiceTypeInfo(0,"请选择",0));
                    spList.addAll((List<ServiceTypeInfo>)msg.obj);
                    ArrayAdapter<ServiceTypeInfo> spAdapter=new ArrayAdapter<ServiceTypeInfo>(getActivity(),android.R.layout.simple_spinner_item,spList);
                    sp.setAdapter(spAdapter);
                    break;

            }

        }
    };

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


        initDatas();

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
                        showNewDialog();
                       // Toast.makeText(getActivity(), "修改，第" + position + "个", Toast.LENGTH_SHORT).show();

                    }
                });

                Button btnDelete = holder.getView(R.id.btn_item_fmt_service_record_delete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showDeleteDialog(position,"修改信息提示","您确定删除此项服务信息吗？","delete",null);


                    }
                });
            }
        };

        lv.setAdapter(adapter);

    }

    private void initDatas(){

        data.add(new ServiceReInfo("张三", "2017-8-14", "职介指导", "备注备注备注"));
        data.add(new ServiceReInfo("李四", "2017-8-14", "职介指导", "备注备注备注"));
        data.add(new ServiceReInfo("王五", "2017-8-15", "职介指导", "备注备注备注"));

    }


    private void getServiceType(){

        spList.clear();

      new Thread(

              new Runnable() {
                  @Override
                  public void run() {

                      String url=MyOkHttpUtils.BaseUrl+"/Json/Get_Service_Type.aspx";

                      Response response= MyOkHttpUtils.okHttpGet(url);

                      if(response!=null){

                          try {
                              String responseStr=response.body().string();
                              Gson gson=new Gson();

                              Message msg=Message.obtain();
                              msg.what=SUCCESS;
                              msg.obj=gson.fromJson(responseStr,new TypeToken<List<ServiceTypeInfo>>(){}.getType());
                              mHandler.sendMessage(msg);

                          } catch (IOException e) {
                              e.printStackTrace();
                          }




                      }


                  }
              }

      ).start();

    }

    private void showDeleteDialog(final int i, String title, String message, final String sign, final AlertDialog alertDialog) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("取消", null);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(TextUtils.equals(sign,"delete")){
                    data.remove(i);
                }else if(TextUtils.equals(sign,"modify")){
                    alertDialog.dismiss();
                }

                adapter.notifyDataSetChanged();
            }
        });

        builder.show();
    }

    private void showNewDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fmt_service_record_new,null);
        builder.setView(view);
        final AlertDialog dialog=builder.create();

         sp= (Spinner) view.findViewById(R.id.sp_dialog_fmt_service_record);
        getServiceType();

        Button btnSure= (Button) view.findViewById(R.id.btn_dialog_fmt_service_record_sure);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sp.getSelectedItemPosition()==0){
                    Toast.makeText(getActivity(), "请选择服务内容", Toast.LENGTH_SHORT).show();
                }else{
                    showDeleteDialog(0,"保存信息提示","您确定保存此服务选项吗？","modify",dialog);
                }
            }
        });

        Button btnCancel= (Button) view.findViewById(R.id.btn_dialog_fmt_service_record_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();
        dialog.setCanceledOnTouchOutside(false);


    }

}
