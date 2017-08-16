package com.youli.zbetuch.jingan.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.entity.ResourcesDetailInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liutao on 2017/8/11.
 */

public class ShiwuyeDetailActivity extends BaseActivity implements View.OnClickListener{


    private Context mContext=ShiwuyeDetailActivity.this;
    private boolean isCheck;
    private Button diaochaBtn,submitBtn,detailInfoBtn,resultBtn;
    private LinearLayout diaochaLl,shidengLi,shidengAndphenoLi;
    private TextView titleTv,diaochaDateTv;
    private ResourcesDetailInfo RDInfo;
    private EditText sfzEt,nameEt,sexEt,birthdayEt,nationEt,educationEt,hujiAddressEt,
            streetEt,jwEt,detailAddressEt,presentStatusEt,masterDateEt,presentIntentionEt,
            shidengDateEt,shidengVidEt,phoneEt,diaocharenEt;

    private Spinner currStaSp,currIntSp;
    private String [] currStaData,currIntData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiwuye_detail);

        initViews();
    }


    private void initViews(){

        RDInfo=(ResourcesDetailInfo)getIntent().getSerializableExtra("RDInfo");
        isCheck=getIntent().getBooleanExtra("IsCheck",false);

        diaochaBtn= (Button) findViewById(R.id.shiwuye_detail_diaocha_btn);
        diaochaBtn.setOnClickListener(this);
        submitBtn= (Button) findViewById(R.id.shiwuye_detail_submit_btn);
        submitBtn.setOnClickListener(this);
        detailInfoBtn= (Button) findViewById(R.id.shiwuye_detail_info_btn);
        detailInfoBtn.setOnClickListener(this);
          resultBtn= (Button) findViewById(R.id.shiwuye_detail_result_btn);
        resultBtn.setOnClickListener(this);

        titleTv= (TextView) findViewById(R.id.shiwuye_detail_title_tv);
        shidengLi= (LinearLayout) findViewById(R.id.shiwuye_detail_shideng_ll);
        shidengAndphenoLi= (LinearLayout) findViewById(R.id.shiwuye_detail_shideng_and_phone_ll);
        diaochaLl= (LinearLayout) findViewById(R.id.shiwuye_detail_diaocha_ll);

        sfzEt= (EditText) findViewById(R.id.shiwuye_detail_sfz_et);
        nameEt= (EditText) findViewById(R.id.shiwuye_detail_name_et);
        sexEt= (EditText) findViewById(R.id.shiwuye_detail_sex_et);
        birthdayEt= (EditText) findViewById(R.id.shiwuye_detail_birthday_et);
        nationEt= (EditText) findViewById(R.id.shiwuye_detail_nation_et);
        educationEt= (EditText) findViewById(R.id.shiwuye_detail_education_et);
        hujiAddressEt= (EditText) findViewById(R.id.shiwuye_detail_huji_address_et);
                streetEt= (EditText) findViewById(R.id.shiwuye_detail_street_et);
        jwEt= (EditText) findViewById(R.id.shiwuye_detail_jw_et);
        detailAddressEt= (EditText) findViewById(R.id.shiwuye_detail_detail_address_et);
        presentStatusEt= (EditText) findViewById(R.id.shiwuye_detail_present_status_et);
        masterDateEt= (EditText) findViewById(R.id.shiwuye_detail_master_date_et);
        presentIntentionEt= (EditText) findViewById(R.id.shiwuye_detail_present_intention_et);
                shidengDateEt= (EditText) findViewById(R.id.shiwuye_detail_shideng_date_et);
        shidengVidEt= (EditText) findViewById(R.id.shiwuye_detail_shideng_vid_et);
        phoneEt= (EditText) findViewById(R.id.shiwuye_detail_phone_et);
        diaocharenEt= (EditText) findViewById(R.id.shiwuye_detail_diaocharen_et);



         diaochaDateTv= (TextView) findViewById(R.id.shiwuye_detail_diaochadate_tv);
        diaochaDateTv.setOnClickListener(this);

        currStaSp= (Spinner) findViewById(R.id.sp_shiwuye_detail_curr_sta);
        currIntSp= (Spinner) findViewById(R.id.sp_shiwuye_detail_curr_int);

        initData();

    }

    private void initData(){

        diaocharenEt.setText(MainLayoutActivity.adminName);

        if(isCheck){
            diaochaLl.setVisibility(View.VISIBLE);
            submitBtn.setClickable(false);
            submitBtn.setBackgroundResource(R.drawable.button_enabled);
            diaochaDateTv.setEnabled(false);
        }else{
            diaochaDateTv.setEnabled(true);
            diaochaLl.setVisibility(View.GONE);
            submitBtn.setClickable(true);
        }

        if(TextUtils.equals(RDInfo.getType(),"失业")){
            currStaData=getResources().getStringArray(R.array.resources_shiye_status);
            titleTv.setText("失业详细");
            shidengAndphenoLi.setVisibility(View.VISIBLE);
            shidengLi.setVisibility(View.VISIBLE);
            shidengDateEt.setText(RDInfo.getZJSYDJRQ().split("T")[0]);
            shidengVidEt.setText(RDInfo.getSYDJYXQ().split("T")[0]);
                    phoneEt.setText(RDInfo.getPHONE());
        }else if(TextUtils.equals(RDInfo.getType(),"无业")){
            currStaData=getResources().getStringArray(R.array.resources_wuye_status);
            titleTv.setText("无业详细");
            shidengAndphenoLi.setVisibility(View.GONE);
            shidengLi.setVisibility(View.INVISIBLE);
        }

        sfzEt.setText(RDInfo.getSFZ());
        nameEt.setText(RDInfo.getNAME());
        sexEt.setText(RDInfo.getSEX());
        birthdayEt.setText(RDInfo.getCSRQ().split("T")[0]);
        nationEt.setText(RDInfo.getMZ());
        educationEt.setText(RDInfo.getEDU());
        hujiAddressEt.setText(RDInfo.getHKDZ());
                streetEt.setText(RDInfo.getJD());
        jwEt.setText(RDInfo.getJW());
        detailAddressEt.setText(RDInfo.getHKDZ());
        presentStatusEt.setText(RDInfo.getMQZK());
        masterDateEt.setText(RDInfo.getMDRQ());
        presentIntentionEt.setText(RDInfo.getDQYX());
        diaochaDateTv.setText(RDInfo.getSURVEY_DATE().split("T")[0]);


        ArrayAdapter<String> currStaAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,currStaData);
       currStaSp.setAdapter(currStaAdapter);


        currIntData=getResources().getStringArray(R.array.resources_wuye_yixiang);
        ArrayAdapter<String> currIntAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,currIntData);
        currIntSp.setAdapter(currIntAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.shiwuye_detail_diaocha_btn:

                if (diaochaLl.getVisibility() == View.GONE) {
                    diaochaLl.setVisibility(View.VISIBLE);

                }

                break;

            case R.id.shiwuye_detail_submit_btn:

                if(TextUtils.equals((String)currStaSp.getSelectedItem(),"请选择")){
                    Toast.makeText(this,"请选择目前状况!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.equals((String)currIntSp.getSelectedItem(),"请选择")){
                    Toast.makeText(this,"请选择当前意向!",Toast.LENGTH_SHORT).show();
                    return;
                }

                showSubmitDialog();


                break;

            case R.id.shiwuye_detail_result_btn:



                 spinnerSetSelection();


                break;

            case R.id.shiwuye_detail_info_btn:

                Intent intent=new Intent(mContext,PersonInfoActivity.class);
                startActivity(intent);

                break;

            case R.id.shiwuye_detail_diaochadate_tv:

                Calendar c = Calendar.getInstance();

                new DatePickerDialog(mContext,new DatePickerDialog.OnDateSetListener(){


                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        diaochaDateTv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();


                break;
        }

    }

    private void spinnerSetSelection(){

        for(int i=0; i<currStaData.length;i++){

            if(TextUtils.equals(RDInfo.getMQZK(),currStaData[i])){

                currStaSp.setSelection(i);
            }

        }

        for(int i=0; i<currIntData.length;i++){

            if(TextUtils.equals(RDInfo.getDQYX(),currIntData[i])){

                currIntSp.setSelection(i);
            }

        }

    }


    private void showSubmitDialog(){

        AlertDialog.Builder builder=new  AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("确定保存此信息吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "提交", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();


    }

}
