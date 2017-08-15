package com.youli.zbetuch.jingan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;
import com.youli.zbetuch.jingan.adapter.MyFpAdapter;
import com.youli.zbetuch.jingan.fragment.EduInfoFragment;
import com.youli.zbetuch.jingan.fragment.FamilyInfoFragment;
import com.youli.zbetuch.jingan.fragment.PersonInfoFragment;
import com.youli.zbetuch.jingan.fragment.PersonReFragment;
import com.youli.zbetuch.jingan.fragment.ServiceReFramgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHengBin on 2017/8/12.
 */

public class PersonInfoActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;

    private Button caidanBtn,jiugonggeBtn,zhuyeBtn,guanzhuBtn;
    private ImageView xiugaiIv;
   private LinearLayout pifLl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        if(mFragments==null) {

            initData();
            initView();
        }

    }

    private void initData(){

        mFragments=new ArrayList<Fragment>();
        mFragments.add(new PersonInfoFragment());
        mFragments.add(new FamilyInfoFragment());
        mFragments.add(new PersonReFragment());
        mFragments.add(new ServiceReFramgent());
        mFragments.add(new EduInfoFragment());

    }

    private void initView(){

        pifLl= (LinearLayout) findViewById(R.id.ll_person_info_pif);

        xiugaiIv= (ImageView) findViewById(R.id.iv_person_info_modify);
        xiugaiIv.setOnClickListener(this);
        caidanBtn= (Button) findViewById(R.id.btn_person_info_caidan);
        caidanBtn.setOnClickListener(this);
        jiugonggeBtn= (Button) findViewById(R.id.btn_person_info_jiugongge);
        jiugonggeBtn.setOnClickListener(this);
        zhuyeBtn= (Button) findViewById(R.id.btn_person_info_zhuye);
        zhuyeBtn.setOnClickListener(this);
        guanzhuBtn= (Button) findViewById(R.id.btn_person_info_follow);
        guanzhuBtn.setOnClickListener(this);

          mViewPager= (ViewPager) findViewById(R.id.vp_person_info);
          mRadioGroup= (RadioGroup) findViewById(R.id.rg_person_info);
        MyFpAdapter adapter=new MyFpAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){

                    case 0:
                        mRadioGroup.check(R.id.rb_person_info);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_family_info);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.rb_person_resume);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.rb_service_record);
                        break;
                    case 4:
                        mRadioGroup.check(R.id.rb_edu_info);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.rb_person_info:
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.rb_family_info:
                        mViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.rb_person_resume:
                        mViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.rb_service_record:
                        mViewPager.setCurrentItem(3,false);
                        break;
                    case R.id.rb_edu_info:
                        mViewPager.setCurrentItem(4,false);
                        break;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_person_info_caidan:

                if(pifLl.getVisibility()==View.GONE){
                    pifLl.setVisibility(View.VISIBLE);
                }else if(pifLl.getVisibility()==View.VISIBLE){
                    pifLl.setVisibility(View.GONE);
                }

                break;


            case R.id.btn_person_info_jiugongge:

                Intent fIntent=new Intent(this,FunctionListActivity.class);
                startActivity(fIntent);

                break;

            case R.id.btn_person_info_zhuye:

                Intent mIntent=new Intent(this,MainLayoutActivity.class);
                startActivity(mIntent);

                break;

            case R.id.btn_person_info_follow:

                Toast.makeText(this,"关注",Toast.LENGTH_SHORT).show();

                break;

            case R.id.iv_person_info_modify:

                Toast.makeText(this,"修改",Toast.LENGTH_SHORT).show();

                break;
        }


    }
}
