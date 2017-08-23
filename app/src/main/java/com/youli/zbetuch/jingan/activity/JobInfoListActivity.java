package com.youli.zbetuch.jingan.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshHorizontalScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.CommonAdapter;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.JobInfoListInfo;
import com.youli.zbetuch.jingan.view.JobInfoListView;
import com.youli.zbetuch.jingan.view.MyListView;
import com.youli.zbetuch.jingan.view.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 2017/8/23.
 */

public class JobInfoListActivity extends BaseActivity{

    private MyListView lv;
    private View headerLv;
    private List<JobInfoListInfo> jobInfoList=new ArrayList<>();
    private CommonAdapter commonAdapter;
    private PullToRefreshScrollView hsv;


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
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                moreData();
            }
        });

        lv = (MyListView) findViewById(R.id.lv_job_info);

        headerLv= LayoutInflater.from(this).inflate(R.layout.header_job_info_lv,lv,false);

        lv.addHeaderView(headerLv);

        lv.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lv.setSelection(0);
//                if (firstVisibleItem >= 0) {
//                    headerLv.setVisibility(View.VISIBLE);
//                }else{
//                    headerLv.setVisibility(View.GONE);
//                }
            }
        });

        for (int i = 0; i < 30; i++) {

            jobInfoList.add(new JobInfoListInfo("上海联家超市有限公司大场店" + i, "生鲜部、蔬果部员工" + i, "158296291", "高中/中专/技校", 18, 40, i, 2000, 2200, "2017-02-14"));

        }

        commonAdapter = new CommonAdapter<JobInfoListInfo>(this, jobInfoList, R.layout.item_job_info_lv) {

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

    }


    //刷新
    private void refresh(){

        jobInfoList.clear();
        for(int i=0;i<30;i++){
            jobInfoList.add(new JobInfoListInfo("上海联家超市有限公司大场店"+i,"生鲜部、蔬果部员工"+i,"158296291","高中/中专/技校",18,40,i,2000,2200,"2017-02-14"));

        }
        commonAdapter.notifyDataSetChanged();
        hsv.onRefreshComplete();
}

    //更多
    private void moreData(){

        for(int i=0;i<30;i++){

            jobInfoList.add(new JobInfoListInfo("更多数据"+i,"生鲜部、蔬果部员工"+i,"158296291","高中/中专/技校",18,40,i,2000,2200,"2017-02-14"));

        }
        commonAdapter.notifyDataSetChanged();

        hsv.onRefreshComplete();


    }

}
