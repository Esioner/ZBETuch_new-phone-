package com.youli.zbetuch.jingan.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.bean.personalInfoBean.PersonalInfoBean;
import com.youli.zbetuch.jingan.utils.MyOkHttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;


public class PersonalInfoQueryResult extends BaseActivity {
    private Context mContext = this;
    private ProgressDialog progressDialog;
    private List<PersonalInfoBean> personalInfoList;
    private ListView lv_personalInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info_query_result_list_layout);
        lv_personalInfo = (ListView) findViewById(R.id.lv);
        Intent intent = getIntent();
        String url = intent.getStringExtra("queryUrl");
        Log.e("URL", "onCreate: "+url);
        loadDates(url);

    }

    private void initData() {
        if (personalInfoList != null){
            PersonalInfoListAdapter adapter = new PersonalInfoListAdapter(personalInfoList);
            lv_personalInfo.setAdapter(adapter);
        }else {
            Toast.makeText(mContext,"数据异常",Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDates(final String url) {
        showProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String responseBody = null;
                Response response = MyOkHttpUtils.okHttpGet(url);
                try {
                    responseBody = response.body().string().trim();
                    Log.e("TAG",responseBody);
                    if (!responseBody.equals("[]")) {
                        personalInfoList = new Gson().fromJson(responseBody, new
                                TypeToken<List<PersonalInfoBean>>() {}.getType());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                                progressDialog.dismiss();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        });
                        Looper.prepare();
                        Toast.makeText(mContext, "对不起，没有信息", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void showProgress() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("请稍等");
        progressDialog.show();
    }


    class PersonalInfoListAdapter extends BaseAdapter {
        private List<PersonalInfoBean> personInfoList;

        public PersonalInfoListAdapter(List<PersonalInfoBean> list) {
            personInfoList = list;
        }

        @Override
        public int getCount() {
            return personInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return personInfoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            PersonalInfoBean personalInfoBean = personInfoList.get(position);
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .personal_info_query_result_list_item_layout, null);
                viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_personal_id);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_personal_name);
                viewHolder.tv_sex = (TextView) convertView.findViewById(R.id.tv_personal_sex);
                viewHolder.tv_birth_date = (TextView) convertView.findViewById(R.id
                        .tv_personal_birth_date);
                viewHolder.tv_type = (TextView) convertView.findViewById(R.id
                        .tv_personal_current_type);
                viewHolder.tv_situation = (TextView) convertView.findViewById(R.id
                        .tv_personal_situation);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_id.setText(personalInfoBean.getId());
            viewHolder.tv_name.setText(personalInfoBean.getName());
            viewHolder.tv_sex.setText(personalInfoBean.getSex());
            viewHolder.tv_birth_date.setText(personalInfoBean.getBirthDate());
            viewHolder.tv_type.setText(personalInfoBean.getType());
            viewHolder.tv_situation.setText(personalInfoBean.getCurrentSituation());
            return convertView;
        }

        class ViewHolder {
            TextView tv_id;
            TextView tv_name;
            TextView tv_sex;
            TextView tv_birth_date;
            TextView tv_type;
            TextView tv_situation;
        }
    }
}


