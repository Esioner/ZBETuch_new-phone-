package com.youli.zbetuch.jingan.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.CommonAdapter;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.JobInfoListInfo;
import com.youli.zbetuch.jingan.utils.MyOkHttpUtils;
import com.youli.zbetuch.jingan.view.MyListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;

/**
 * Created by liutao on 2017/8/23.
 */

public class JobInfoListActivity extends BaseActivity{

    private Context mContext=this;
    private final int SUCCEED=10000;
    private final int  PROBLEM=10001;

    private MyListView lv;
    private View headerLv;
    private List<JobInfoListInfo> jobInfoList=new ArrayList<>();
    private CommonAdapter commonAdapter;
    private PullToRefreshScrollView hsv;

    private Handler mHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

             switch (msg.what){

                 case SUCCEED:

                     jobInfoList.clear();
                    jobInfoList.addAll((List<JobInfoListInfo>)msg.obj);

                     lvSetAdapter(jobInfoList);


                     break;

                 case PROBLEM:

                     Toast.makeText(mContext,"网络不给力",Toast.LENGTH_SHORT).show();

                     break;

             }

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);


        initViews();
    }

    private void initViews() {

        hsv = (PullToRefreshScrollView) findViewById(R.id.hsv_job_info);
        hsv.setMode(PullToRefreshBase.Mode.BOTH);
        hsv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
              //  refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
               // moreData();
            }
        });

        lv = (MyListView) findViewById(R.id.lv_job_info);

        headerLv= LayoutInflater.from(this).inflate(R.layout.header_job_info_lv,lv,false);

        lv.addHeaderView(headerLv);

        getData();
//
//        for(int i=0;i<30;i++) {
//
//            jobInfoList.add(new JobInfoListInfo());
//        }
//
//
        commonAdapter = new CommonAdapter<JobInfoListInfo>(this, jobInfoList, R.layout.item_job_info_lv) {

            @Override
            public void convert(CommonViewHolder holder, JobInfoListInfo item, int position) {
//
//                TextView comnameTv = holder.getView(R.id.tv_item_job_info_comname);
//                comnameTv.setText(item.getComname());
//                TextView jobnameTv = holder.getView(R.id.tv_item_job_info_jobname);
//                jobnameTv.setText(item.getJobname());
//                TextView jobnoTv = holder.getView(R.id.tv_item_job_info_jobno);
//                jobnoTv.setText(item.getJobno());
//                TextView edunameTv = holder.getView(R.id.tv_item_job_info_eduname);
//                edunameTv.setText(item.getEduname());
//                TextView ageTv = holder.getView(R.id.tv_item_job_info_age);
//                ageTv.setText(item.getStartage() + "-" + item.getEndage());
//                TextView numsTv = holder.getView(R.id.tv_item_job_info_recruitnums);
//                numsTv.setText(item.getRecruitnums() + " ");
//                TextView salaryTv = holder.getView(R.id.tv_item_job_info_salary);
//                salaryTv.setText(item.getStartsalary() + "-" + item.getEndsalary());
//                TextView timeTv = holder.getView(R.id.tv_item_job_info_time);
//                timeTv.setText(item.getModifydate());
            }
        };

        lv.setAdapter(commonAdapter);

    }


    private void getData(){

       // http://web.youli.pw:89/Json/GetJobs_Search.aspx?PageRecCnts=15&ZyflId=-1&Age=-1&GZXZId=-1&ZyflChildId=-1&JobName=&IsDisabledPerson=false&IsDirectInterview=false&ComPropertyId=-1&JobNo=&ModifyStartDate=2010-01-01&IsAssurance=false&EndSalary=0&IndustryClassChildId=-1&ComName=&GZBSId=-1&EduID=-1&AreaId3=-1&AreaId2=-1&AreaId1=-1&ModifyEndDate=2030-01-01&StartSalary=0&IndustryClassId=-1&IsNewGraduates=false&PageIndex=0

        new Thread(

                new Runnable() {
                    @Override
                    public void run() {

                       String url= MyOkHttpUtils.BaseUrl+"/Json/GetJobs_Search.aspx?PageRecCnts=15&ZyflId=-1&Age=-1&GZXZId=-1&ZyflChildId=-1&JobName=&IsDisabledPerson=false&IsDirectInterview=false&ComPropertyId=-1&JobNo=&ModifyStartDate=2010-01-01&IsAssurance=false&EndSalary=0&IndustryClassChildId=-1&ComName=&GZBSId=-1&EduID=-1&AreaId3=-1&AreaId2=-1&AreaId1=-1&ModifyEndDate=2030-01-01&StartSalary=0&IndustryClassId=-1&IsNewGraduates=false&PageIndex=0";

                        Response response=MyOkHttpUtils.okHttpGet(url);

                        Message msg=Message.obtain();

                        if(response!=null){

                            try {
                                String resStr=response.body().string();

                                Gson gson=new Gson();

                                msg.obj=gson.fromJson(resStr,new TypeToken<List<JobInfoListInfo>>(){}.getType());
                                msg.what=SUCCEED;
                                mHandler.sendMessage(msg);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }else{

                            msg.what=PROBLEM;
                            mHandler.sendMessage(msg);

                        }

                    }
                }

        ).start();

    }

    private void lvSetAdapter(List<JobInfoListInfo> list){
if(commonAdapter==null){
    commonAdapter = new CommonAdapter<JobInfoListInfo>(this,list, R.layout.item_job_info_lv) {
        @Override
        public void convert(CommonViewHolder holder, JobInfoListInfo item, int position) {

            TextView comnameTv = holder.getView(R.id.tv_item_job_info_comname);
            comnameTv.setText(item.getComname());
            TextView jobnameTv = holder.getView(R.id.tv_item_job_info_jobname);
            jobnameTv.setText(item.getJobname());
            TextView jobnoTv = holder.getView(R.id.tv_item_job_info_jobno);
            jobnoTv.setText(item.getJobno());
            TextView edunameTv = holder.getView(R.id.tv_item_job_info_eduname);
            edunameTv.setText(item.getEduname());
            TextView ageTv = holder.getView(R.id.tv_item_job_info_age);
            ageTv.setText(item.getStartage() + "-" + item.getEndage());
            TextView numsTv = holder.getView(R.id.tv_item_job_info_recruitnums);
            numsTv.setText(item.getRecruitnums() + " ");
            TextView salaryTv = holder.getView(R.id.tv_item_job_info_salary);
            salaryTv.setText(item.getStartsalary() + "-" + item.getEndsalary());
            TextView timeTv = holder.getView(R.id.tv_item_job_info_time);
            timeTv.setText(item.getModifydate());
        }
    };
    lv.setAdapter(commonAdapter);
}else{
    commonAdapter.notifyDataSetChanged();
}

    }

    //刷新
//    private void refresh(){
//
//        jobInfoList.clear();
//        for(int i=0;i<30;i++){
//            jobInfoList.add(new JobInfoListInfo("上海联家超市有限公司大场店"+i,"生鲜部、蔬果部员工"+i,"158296291","高中/中专/技校",18,40,i,2000,2200,"2017-02-14"));
//
//        }
//        commonAdapter.notifyDataSetChanged();
//        hsv.onRefreshComplete();
//}

    //更多
//    private void moreData(){
//
//        for(int i=0;i<30;i++){
//
//            jobInfoList.add(new JobInfoListInfo("更多数据"+i,"生鲜部、蔬果部员工"+i,"158296291","高中/中专/技校",18,40,i,2000,2200,"2017-02-14"));
//
//        }
//        commonAdapter.notifyDataSetChanged();
//
//        hsv.onRefreshComplete();
//
//
//    }

}
