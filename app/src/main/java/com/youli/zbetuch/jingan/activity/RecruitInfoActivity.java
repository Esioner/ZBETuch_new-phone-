package com.youli.zbetuch.jingan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 2017/8/22.
 */

public class RecruitInfoActivity extends BaseActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener,CheckBox.OnCheckedChangeListener{

    private Context mContext=this;
    private Button queryBtn;

    private Spinner eduSp,companySp,commonSp,industryBigSp,industrySmallSp,occBigSp,occSmallSp,workSp,classSp,
            areaOneSp,areaTwoSp,areaThreeSp;
    private List<String> eduSpData=new ArrayList<>();
    private List<String> companySpData=new ArrayList<>();
    private List<String> commonSpData=new ArrayList<>();
    private List<String> industryBigSpData=new ArrayList<>();
    private List<String> industrySmallSpData=new ArrayList<>();
    private List<String> occBigSpData=new ArrayList<>();
    private List<String> occSmallSpData=new ArrayList<>();
    private List<String> workSpData=new ArrayList<>();
    private List<String> classSpData=new ArrayList<>();
    private List<String> areaOneSpData=new ArrayList<>();
    private List<String> areaTwoSpData=new ArrayList<>();
    private List<String> areaThreeSpData=new ArrayList<>();

    private RadioGroup rg;
    private RadioButton rbno, rbtoday, rbthreeday, rbweek, rbmonth;
    private CheckBox cbDirectInterview, cbNewGraduates, cbDisabledPerson,
            cbAssurance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recruit_info);

        initViews();


    }

    private void initViews(){

        //单位性质
        companySp= (Spinner) findViewById(R.id.sp_recruit_info_company_nature);

        companySpData.add("请选择");
        companySpData.add("单位性质1");
        companySpData.add("单位性质2");
        companySpData.add("单位性质3");

        companySp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,companySpData));

      //常见工种
        commonSp= (Spinner) findViewById(R.id.sp_recruit_info_common_work);

        commonSpData.add("请选择");
        commonSpData.add("常见工种1");
        commonSpData.add("常见工种2");
        commonSpData.add("常见工种3");

        commonSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,commonSpData));


        //文化程度
        eduSp= (Spinner) findViewById(R.id.sp_recruit_info_edu);

        eduSpData.add("请选择");
        eduSpData.add("学历1");
        eduSpData.add("学历2");
        eduSpData.add("学历3");

        eduSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,eduSpData));

        //行业大类
        industryBigSp= (Spinner) findViewById(R.id.sp_recruit_info_industry_big);

        industryBigSpData.add("请选择");
        industryBigSpData.add("行业大类1");
        industryBigSpData.add("行业大类2");
        industryBigSpData.add("行业大类3");

        industryBigSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,industryBigSpData));


        //行业小类
        industrySmallSp= (Spinner) findViewById(R.id.sp_recruit_info_industry_small);

        industrySmallSpData.add("请选择");
        industrySmallSpData.add("行业小类1");
        industrySmallSpData.add("行业小类2");
        industrySmallSpData.add("行业小类3");

        industrySmallSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,industrySmallSpData));

        //职业大类
        occBigSp= (Spinner) findViewById(R.id.sp_recruit_info_occupation_big);

        occBigSpData.add("请选择");
        occBigSpData.add("职业大类1");
        occBigSpData.add("职业大类2");
        occBigSpData.add("职业大类3");

        occBigSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,occBigSpData));


        //职业小类
        occSmallSp= (Spinner) findViewById(R.id.sp_recruit_info_occupation_small);

        occSmallSpData.add("请选择");
        occSmallSpData.add("职业小类1");
        occSmallSpData.add("职业小类2");
        occSmallSpData.add("职业小类3");

        occSmallSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,occSmallSpData));

        //工作性质
        workSp= (Spinner) findViewById(R.id.sp_recruit_info_work_nature);

        workSpData.add("请选择");
        workSpData.add("工作性质1");
        workSpData.add("工作性质2");
        workSpData.add("工作性质3");

        workSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,workSpData));

        //工作班时
        classSp= (Spinner) findViewById(R.id.sp_recruit_info_work_class);

        classSpData.add("请选择");
        classSpData.add("工作班时1");
        classSpData.add("工作班时2");
        classSpData.add("工作班时3");

        classSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classSpData));

        //工作地区1
        areaOneSp= (Spinner) findViewById(R.id.sp_recruit_info_work_area_one);

        areaOneSpData.add("请选择");
        areaOneSpData.add("1工作地区1");
        areaOneSpData.add("1工作地区2");
        areaOneSpData.add("1工作地区3");

        areaOneSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,areaOneSpData));

        //工作地区2
        areaTwoSp= (Spinner) findViewById(R.id.sp_recruit_info_work_area_two);

        areaTwoSpData.add("请选择");
        areaTwoSpData.add("2工作地区1");
        areaTwoSpData.add("2工作地区2");
        areaTwoSpData.add("2工作地区3");

        areaTwoSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,areaTwoSpData));

        //工作地区3
        areaThreeSp= (Spinner) findViewById(R.id.sp_recruit_info_work_area_three);

        areaThreeSpData.add("请选择");
        areaThreeSpData.add("3工作地区1");
        areaThreeSpData.add("3工作地区2");
        areaThreeSpData.add("3工作地区3");

        areaThreeSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,areaThreeSpData));

        queryBtn= (Button) findViewById(R.id.btn_recruit_info_find);
        queryBtn.setOnClickListener(this);

        rg= (RadioGroup) findViewById(R.id.rg_recruit_info);
        rg.setOnCheckedChangeListener(this);

        rbno= (RadioButton) findViewById(R.id.rb_recruit_info1);
        rbtoday= (RadioButton) findViewById(R.id.rb_recruit_info2);
        rbthreeday= (RadioButton) findViewById(R.id.rb_recruit_info3);
        rbweek= (RadioButton) findViewById(R.id.rb_recruit_info4);
        rbmonth= (RadioButton) findViewById(R.id.rb_recruit_info5);

        rbno.setChecked(true);

        cbDirectInterview= (CheckBox) findViewById(R.id.cb_recruit_info1);
        cbNewGraduates= (CheckBox) findViewById(R.id.cb_recruit_info2);
        cbDisabledPerson= (CheckBox) findViewById(R.id.cb_recruit_info3);
        cbAssurance= (CheckBox) findViewById(R.id.cb_recruit_info4);

        cbDirectInterview.setOnCheckedChangeListener(this);
        cbNewGraduates.setOnCheckedChangeListener(this);
        cbDisabledPerson.setOnCheckedChangeListener(this);
        cbAssurance.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_recruit_info_find:

                Intent intent=new Intent(mContext,JobInfoListActivity.class);
                startActivity(intent);

                break;

        }

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){

            case R.id.rb_recruit_info1:

                Toast.makeText(mContext,rbno.getText().toString(),Toast.LENGTH_SHORT).show();

                break;
            case R.id.rb_recruit_info2:

                Toast.makeText(mContext,rbtoday.getText().toString(),Toast.LENGTH_SHORT).show();

                break;
            case R.id.rb_recruit_info3:

                Toast.makeText(mContext,rbthreeday.getText().toString(),Toast.LENGTH_SHORT).show();

                break;
            case R.id.rb_recruit_info4:

                Toast.makeText(mContext,rbweek.getText().toString(),Toast.LENGTH_SHORT).show();

                break;
            case R.id.rb_recruit_info5:

                Toast.makeText(mContext,rbmonth.getText().toString(),Toast.LENGTH_SHORT).show();

                break;

        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()){

            case R.id.cb_recruit_info1:


                if(isChecked){
                    Toast.makeText(mContext,"111被选中",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"111取消选中",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.cb_recruit_info2:


                if(isChecked){
                    Toast.makeText(mContext,"222被选中",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"222取消选中",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.cb_recruit_info3:


                if(isChecked){
                    Toast.makeText(mContext,"333被选中",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"333取消选中",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.cb_recruit_info4:


                if(isChecked){
                    Toast.makeText(mContext,"444被选中",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"444取消选中",Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
}
