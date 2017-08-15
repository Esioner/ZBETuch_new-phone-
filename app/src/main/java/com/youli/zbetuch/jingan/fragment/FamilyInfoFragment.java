package com.youli.zbetuch.jingan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.CommonAdapter;
import com.youli.zbetuch.jingan.entity.CommonViewHolder;
import com.youli.zbetuch.jingan.entity.FamilyAddressInfo;
import com.youli.zbetuch.jingan.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class FamilyInfoFragment extends Fragment {

    private View contentView;

    private ListView lv;

    private List<FamilyAddressInfo> data=new ArrayList<>();

    private List<FamilyAddressInfo.FamilyAddressInfoList> childData=new ArrayList<>();

    private CommonAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.framgment_family_info, container, false);


        initView(contentView);

        return contentView;

    }

    private void initView(View view){

        childData.add(new FamilyAddressInfo.FamilyAddressInfoList("张三", "男", "1999-11-11", "123456789098765432"));
        childData.add(new FamilyAddressInfo.FamilyAddressInfoList("李四", "女", "2000-10-10", "543212345678909876"));
        childData.add(new FamilyAddressInfo.FamilyAddressInfoList("王五", "男", "2001-09-09", "234567890987654321"));
        data.add(new FamilyAddressInfo(true, "户籍地址:", childData));
        data.add(new FamilyAddressInfo(false, "居住地址:", childData));

        lv = (ListView) view.findViewById(R.id.lv_fmt_family_info);

        adapter = new CommonAdapter<FamilyAddressInfo>(getActivity(), data, R.layout.item_family_info_lv) {

            @Override
            public void convert(CommonViewHolder holder, FamilyAddressInfo item, final int position) {

                ImageView iv=holder.getView(R.id.iv_item_family_info_title);

                TextView title=holder.getView(R.id.tv_item_family_info_title);
                title.setText(item.getTitle());

                MyListView childLv=holder.getView(R.id.lv_item_fmt_family_info);

                if(data.get(position).isChecked()){
                    iv.setImageResource(R.drawable.sj1);
                    childLv.setVisibility(View.VISIBLE);
                }else{
                    iv.setImageResource(R.drawable.sj);
                    childLv.setVisibility(View.GONE);
                }


                LinearLayout ll=holder.getView(R.id.ll_item_family_info_title);
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(data.get(position).isChecked()){
                            data.get(position).setChecked(false);
                        }else{
                            for(FamilyAddressInfo fai:data){
                                fai.setChecked(false);
                            }

                            data.get(position).setChecked(!data.get(position).isChecked());

                        }


                        adapter.notifyDataSetChanged();

                    }
                });

                childLv.setAdapter(new CommonAdapter<FamilyAddressInfo.FamilyAddressInfoList>(getActivity(),data.get(position).getList(),R.layout.item_item_family_info_lv) {


                    @Override
                    public void convert(CommonViewHolder holder, FamilyAddressInfo.FamilyAddressInfoList item, int position) {

                        TextView name=holder.getView(R.id.tv_item_item_family_info_name);
                        name.setText(item.getName());
                        TextView sex=holder.getView(R.id.tv_item_item_family_info_sex);
                        sex.setText(item.getSex());
                        TextView birthday=holder.getView(R.id.tv_item_item_family_info_birthday);
                        birthday.setText(item.getBirthday());
                        TextView idCard=holder.getView(R.id.tv_item_item_family_info_idcard);
                        idCard.setText(item.getIdcard());
                    }
                });

            }
        };


        lv.setAdapter(adapter);

    }

}
