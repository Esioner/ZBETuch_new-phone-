package com.youli.zbetuch.jingan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.JobsInfo;
import com.youli.zbetuch.jingan.entity.MainContent;
import com.youli.zbetuch.jingan.entity.MeetInfo;
import com.youli.zbetuch.jingan.entity.NewsInfo;
import com.youli.zbetuch.jingan.entity.WorkNoticeInfo;
import com.youli.zbetuch.jingan.view.MyListView;

import java.util.List;
import java.util.Objects;

/**
 * Created by liutao on 2017/8/2.
 */

public class MainAdapter extends BaseAdapter{

    private List<MainContent> data;
    private Context context;
    private CommonAdapter commonAdapter;

    public MainAdapter(List<MainContent> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int p, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView==null){

            vh=new ViewHolder();

            convertView=LayoutInflater.from(context).inflate(R.layout.item_main_layout_gv,parent,false);

            vh.titleTv= (TextView) convertView.findViewById(R.id.item_main_layout_gv_title_tv);
            vh.contentLv= (MyListView) convertView.findViewById(R.id.item_main_layout_gv_content_lv);
            convertView.setTag(vh);
        }else{

            vh= (ViewHolder) convertView.getTag();

        }


        if(p==0) {
            commonAdapter = new CommonAdapter<MeetInfo>(context, data.get(p).getMeetInfos(), R.layout.item_main_layout_lv) {
                @Override
                public void convert(CommonViewHolder holder, MeetInfo item, int position) {
                    TextView title = holder.getView(R.id.item_main_layout_lv_content_tv);
                    title.setText(data.get(p).getMeetInfos().get(position).getTITLE());
                    TextView time = holder.getView(R.id.item_main_layout_lv_time_tv);
                    time.setText(data.get(p).getMeetInfos().get(position).getMEETING_TIME());
                }

            };

        }else if(p==1){

            commonAdapter=new CommonAdapter<WorkNoticeInfo>(context,data.get(p).getWorkNoticeInfos(),R.layout.item_main_layout_lv) {

                @Override
                public void convert(CommonViewHolder holder, WorkNoticeInfo item, int position) {

                    TextView title = holder.getView(R.id.item_main_layout_lv_content_tv);
                    title.setText(data.get(p).getWorkNoticeInfos().get(position).getTITLE());
                    TextView time = holder.getView(R.id.item_main_layout_lv_time_tv);
                    time.setText(data.get(p).getWorkNoticeInfos().get(position).getCREATE_DATE().split("T")[0]);

                }
            };

        }else if(p==2){

            commonAdapter=new CommonAdapter<JobsInfo>(context,data.get(p).getJobsInfos(),R.layout.item_main_layout_lv) {

                @Override
                public void convert(CommonViewHolder holder, JobsInfo item, int position) {
                    TextView title = holder.getView(R.id.item_main_layout_lv_content_tv);
                    title.setText(data.get(p).getJobsInfos().get(position).getJobname());
                    TextView time = holder.getView(R.id.item_main_layout_lv_time_tv);
                    time.setText(data.get(p).getJobsInfos().get(position).getModifydate().split("T")[0]);
                }
            };

        }else if(p==3){

            commonAdapter=new CommonAdapter<NewsInfo>(context,data.get(p).getNewsInfos(),R.layout.item_main_layout_lv) {

                @Override
                public void convert(CommonViewHolder holder, NewsInfo item, int position) {
                    TextView title = holder.getView(R.id.item_main_layout_lv_content_tv);
                    title.setText(data.get(p).getNewsInfos().get(position).getTITLE());
                    TextView time = holder.getView(R.id.item_main_layout_lv_time_tv);
                    time.setText(data.get(p).getNewsInfos().get(position).getCREATE_TIME().split("T")[0]);
                }
            };

        }

       vh.contentLv.setAdapter(commonAdapter);
//       vh.contentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               switch (p){
//                   case 0:
//                       Toast.makeText(context,data.get(p).getMeetInfos().get(position).getTITLE(),Toast.LENGTH_SHORT).show();
//                       break;
//                   case 1:
//                       Toast.makeText(context,data.get(p).getWorkNoticeInfos().get(position).getTITLE(),Toast.LENGTH_SHORT).show();
//                       break;
//                   case 2:
//                       Toast.makeText(context,data.get(p).getJobsInfos().get(position).getJobname(),Toast.LENGTH_SHORT).show();
//                       break;
//                   case 3:
//                       Toast.makeText(context,data.get(p).getNewsInfos().get(position).getTITLE(),Toast.LENGTH_SHORT).show();
//                       break;
//               }
//
//           }
//       });
        vh.titleTv.setText(data.get(p).getTitle());
        return convertView;
    }

    class ViewHolder{


        TextView titleTv;
        MyListView contentLv;
    }

}
