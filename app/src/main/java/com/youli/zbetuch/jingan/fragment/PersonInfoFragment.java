package com.youli.zbetuch.jingan.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.activity.LoginActivity;
import com.youli.zbetuch.jingan.entity.PersonInfo;
import com.youli.zbetuch.jingan.entity.StaffMarkInfo;
import com.youli.zbetuch.jingan.utils.MyOkHttpUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Response;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class PersonInfoFragment extends Fragment implements View.OnClickListener{

    private final int SUCCEED=10000;
    private final int  PROBLEM=10001;

    private PersonInfo pInfo;

    public PersonInfoFragment(PersonInfo pInfo) {
        this.pInfo = pInfo;
    }

    public PersonInfo getpInfo() {
        return pInfo;
    }

    private LinearLayout markLl;
    private View contentView;
    private PopupWindow popupWin;
    private View markView;
    private ImageView yjbyshIv;//应届毕业生
    private ImageView jdryIv;//戒毒人员
    private ImageView jyknIv;//就业困难
    private ImageView qhryIv;//启航人员
    private ImageView wjyjIv;//无就业家庭
    private ImageView xjryIv;//刑解人员

    private TextView nameTv,sexTv,birthdayTv,nationTv,jiguanTv,stateTv,
            sfzTv,currIntTv,eduTv,mdTv,jdTv,jwhTv,hujiTv,phoneTv,juzhuTv,
            markTv,upDateTimeTv;

    private List<StaffMarkInfo> markData=new ArrayList<>();

    private Handler mHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case SUCCEED:

                    markData.addAll(( List<StaffMarkInfo>)msg.obj);

                    for(StaffMarkInfo info : markData){

                        TextView tv=new TextView(getActivity());
            tv.setText(info.getType_Name());
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            tv.setPadding(0,0,10,0);
            tv.setTextSize(16);
            markLl.addView(tv);

                    }

                    break;

                case PROBLEM:

                    Toast.makeText(getActivity(),"网络不给力",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        contentView=LayoutInflater.from(getContext()).inflate(R.layout.framgment_personal_info,container,false);


        if(pInfo!=null) {
            initViews(contentView);
        }
        return contentView;
    }


    private void initViews(View view){

        nameTv= (TextView) view.findViewById(R.id.tv_person_info_name);
        sexTv= (TextView) view.findViewById(R.id.tv_person_info_sex);
        birthdayTv= (TextView) view.findViewById(R.id.tv_person_info_birthday);
        nationTv= (TextView) view.findViewById(R.id.tv_person_info_nation);
        jiguanTv= (TextView) view.findViewById(R.id.tv_person_info_jiguan);
        stateTv= (TextView) view.findViewById(R.id.tv_person_info_state);
        sfzTv= (TextView) view.findViewById(R.id.tv_person_info_sfz);
        currIntTv= (TextView) view.findViewById(R.id.tv_person_info_curr_intent);
        eduTv= (TextView) view.findViewById(R.id.tv_person_info_edu);
        mdTv= (TextView) view.findViewById(R.id.tv_person_info_md);
        jdTv= (TextView) view.findViewById(R.id.tv_person_info_jd);
        jwhTv= (TextView) view.findViewById(R.id.tv_person_info_jwh);
        hujiTv= (TextView) view.findViewById(R.id.tv_person_info_huji);
        phoneTv= (TextView) view.findViewById(R.id.tv_person_info_phone);
        juzhuTv= (TextView) view.findViewById(R.id.tv_person_info_juzhu);
        markTv= (TextView) view.findViewById(R.id.tv_person_info_mark);
        upDateTimeTv= (TextView) view.findViewById(R.id.tv_person_info_update_time);

        markLl= (LinearLayout) view.findViewById(R.id.ll_person_special_mark);

        nameTv.setText(pInfo.getNAME());
        sexTv.setText(pInfo.getSEX());
        birthdayTv.setText(pInfo.getBIRTH_DATE().split("T")[0]);
        nationTv.setText(pInfo.getNATIONS());
        jiguanTv.setText(pInfo.getNATIVE());
        stateTv.setText(pInfo.getTYPE());
        sfzTv.setText(pInfo.getSFZ());
        currIntTv.setText(pInfo.getCurrent_intent());
        eduTv.setText(pInfo.getCULTURAL_CODE());
        mdTv.setText(pInfo.getCurrent_situation());
        jdTv.setText(pInfo.getCenter().getQ户口所属街道());
        jwhTv.setText(pInfo.getCenter().getQ居委会());
        hujiTv.setText(pInfo.getCenter().getQ户口地址());
        phoneTv.setText(pInfo.getCONTACT_NUMBER());
        sexTv.setText(pInfo.getSEX());
        juzhuTv.setText(pInfo.getNOW_ROAD()+pInfo.getNOW_LANE()+pInfo.getNOW_NO()+pInfo.getNOW_ROOM());
        markTv.setText(pInfo.getRemark());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date= null;
        try {
            date = sdf.parse(pInfo.getCenter().getCREATE_DATE().replace("T"," "));
            upDateTimeTv.setText(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }



       yjbyshIv= (ImageView) view.findViewById(R.id.iv_person_info_yjbysh);
        yjbyshIv.setOnClickListener(this);
        jdryIv= (ImageView) view.findViewById(R.id.iv_person_info_jdry);
        jdryIv.setOnClickListener(this);
        jyknIv= (ImageView) view.findViewById(R.id.iv_person_info_jykn);
        jyknIv.setOnClickListener(this);
         qhryIv= (ImageView) view.findViewById(R.id.iv_person_info_qhry);
        qhryIv.setOnClickListener(this);
        wjyjIv= (ImageView) view.findViewById(R.id.iv_person_info_wjyj);
        wjyjIv.setOnClickListener(this);
         xjryIv= (ImageView) view.findViewById(R.id.iv_person_info_xjry);
        xjryIv.setOnClickListener(this);

        //专项标识的URL：http://web.youli.pw:89/Json/Get_TB_Staff_Marks.aspx?sfz=310108198004026642

        getMarksInfo();
    }

    private void getMarksInfo(){

        new Thread(

                new Runnable() {
                    @Override
                    public void run() {

                        String markUrl= MyOkHttpUtils.BaseUrl+"/Json/Get_TB_Staff_Marks.aspx?sfz="+getpInfo().getSFZ();

                        Response response=MyOkHttpUtils.okHttpGet(markUrl);

                        Message  msg=Message.obtain();

                        if(response!=null){

                            try {
                                String resStr=response.body().string();

                                Gson gson=new Gson();

                                msg.what=SUCCEED;
                                msg.obj=gson.fromJson(resStr,new TypeToken<List<StaffMarkInfo>>(){}.getType());
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

    @Override
    public void onClick(View v) {

switch (v.getId()){

    case R.id.iv_person_info_yjbysh:
        showPopupWindow(v);
        break;
    case R.id.iv_person_info_jdry:
        showPopupWindow(v);
        break;
    case R.id.iv_person_info_jykn:
        showPopupWindow(v);
        break;
    case R.id.iv_person_info_qhry:
        showPopupWindow(v);
        break;
    case R.id.iv_person_info_wjyj:
        showPopupWindow(v);
        break;
    case R.id.iv_person_info_xjry:
        showPopupWindow(v);
        break;

}

    }

    private void showPopupWindow(View parent){

       if(popupWin==null){
           markView=LayoutInflater.from(getActivity()).inflate(R.layout.popup_person_mark,null);

           popupWin=new PopupWindow(markView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       }

        popupWin.setFocusable(true);
        popupWin.setOutsideTouchable(true);
        popupWin.setBackgroundDrawable(new BitmapDrawable());
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
      //  popupWin.setWindowLayoutMode();
        popupWin.showAsDropDown(parent,-250,10);
    }

}
