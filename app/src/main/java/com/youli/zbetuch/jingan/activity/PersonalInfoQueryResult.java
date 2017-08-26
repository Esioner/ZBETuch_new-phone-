package com.youli.zbetuch.jingan.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.bean.personalInfoBean.PersonalInfoBean;
import com.youli.zbetuch.jingan.utils.MyOkHttpUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;


public class PersonalInfoQueryResult extends BaseActivity {
    private Context mContext = this;
    private ProgressDialog progressDialog;
    private List<PersonalInfoBean> personalInfoList = new ArrayList<>();
    int index = 30;
    private String url_suffix;
    private PullToRefreshListView lv_personalInfo;
    private PersonalInfoListAdapter personalInfoListAdapter;
    private boolean isFirst = true;
    int i = 0;
    List<PersonalInfoBean> infoList = new ArrayList<PersonalInfoBean>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info_query_result_list_layout);

        initListView();

        Intent intent = getIntent();
        url_suffix = intent.getStringExtra("queryUrl");
        Log.e("2017/8/26", "onCreate: " + url_suffix);
        String url = jointUrl(index);

        loadDates(url);
    }

    private void initListView() {
        lv_personalInfo = (PullToRefreshListView) findViewById(R.id.lv);
        lv_personalInfo.setMode(PullToRefreshBase.Mode.BOTH);

        //设置下拉刷新内容
        ILoadingLayout startLabels = lv_personalInfo
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在刷新...");// 刷新时
        startLabels.setReleaseLabel("释放来刷新");// 下来达到一定距离时，显示的提示
        //设置上拉加载更多内容
        ILoadingLayout endLabels = lv_personalInfo.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉加载更多");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在加载...");// 刷新时
        endLabels.setReleaseLabel("释放以加载更多...");// 下来达到一定距离时，显示的提示


        lv_personalInfo.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String refreshUrl = MyOkHttpUtils.BaseUrl + "/Json/Get_BASIC_INFORMATION" +
                        ".aspx?page=0&rows=30" + url_suffix;
                personalInfoList.clear();

                loadDates(refreshUrl);
                personalInfoListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
               // Toast.makeText(mContext, "上拉加载", Toast.LENGTH_SHORT).show();
                loadMore();

            }
        });

        lv_personalInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(mContext,"*********"+personalInfoList.get(position-1).getName(),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(mContext,PersonInfoActivity.class);
                intent.putExtra("mySFZ",personalInfoList.get(position-1).getSfz());
                startActivity(intent);

            }
        });
    }


    /**
     * 拼接URL
     *
     * @param index
     */
    private String jointUrl(int index) {
        String url = MyOkHttpUtils.BaseUrl + "/Json/Get_BASIC_INFORMATION.aspx?" +
                "page=0&rows=" +
                index + url_suffix;
        return url;
    }

    //加载更多
    private void loadMore() {

        String url = MyOkHttpUtils.BaseUrl + "/Json/Get_BASIC_INFORMATION.aspx?" +
                "page=" + (++i) + "&rows=30" + url_suffix;
        loadDates(url);
        for (PersonalInfoBean list : personalInfoList) {
            Log.w("2017/08/17", "loadMore: " + list.getName());
        }
        personalInfoListAdapter.notifyDataSetChanged();

    }

    private void initData() {
        if (personalInfoList != null) {
            personalInfoListAdapter = new PersonalInfoListAdapter(personalInfoList);
        } else {
            Toast.makeText(mContext, "数据异常", Toast.LENGTH_SHORT).show();
        }

        if (personalInfoListAdapter != null && isFirst) {
            lv_personalInfo.setAdapter(personalInfoListAdapter);
            isFirst = false;
        }

    }

    private void loadDates(final String url) {

        Log.e("2017/8/22","===url==="+url);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = MyOkHttpUtils.okHttpGet(url);
                try {
                    final String responseBody = response.body().string().trim();
                    Log.e("TAG", responseBody);
                    if (!responseBody.equals("[]")) {
                        Type type = new TypeToken<List<PersonalInfoBean>>() {}.getType();
                        infoList = new Gson().fromJson(responseBody, type);
                        personalInfoList.addAll(infoList);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isFirst) {
                                    initData();
                                }
                            }
                        });
                    } else {
                        Looper.prepare();
                        Toast.makeText(mContext, "对不起，没有信息", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (lv_personalInfo.isRefreshing()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lv_personalInfo.onRefreshComplete();
                            }
                        });

                    }
                }
            }
        }

        ).start();
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

            viewHolder.tv_id.setText(position + 1 + "");
            viewHolder.tv_name.setText(personalInfoBean.getName());
            viewHolder.tv_sex.setText(personalInfoBean.getSex());
            String birthDate = personalInfoBean.getBirthDate().substring(0, (personalInfoBean
                    .getBirthDate().indexOf("T")));
            viewHolder.tv_birth_date.setText(birthDate);
            viewHolder.tv_type.setText(personalInfoBean.getType());
            viewHolder.tv_situation.setText(personalInfoBean.getCurrentSituation());
            if (position % 2 == 0) {
                convertView.setBackgroundResource(R.drawable.selector_ziyuandiaocha_item1);
            } else if (position % 2 != 0) {
                convertView.setBackgroundResource(R.drawable.selector_ziyuandiaocha_item2);
            }

//            if (position % 2 == 0) {
//                convertView.setBackgroundResource(R.color.skyBlue);
//            } else if (position % 2 != 0) {
//                convertView.setBackgroundResource(R.color.white);
//            }
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

