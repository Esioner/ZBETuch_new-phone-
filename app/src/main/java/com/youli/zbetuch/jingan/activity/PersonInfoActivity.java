package com.youli.zbetuch.jingan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
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

public class PersonInfoActivity extends FragmentActivity{

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
int i=0;
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

          mViewPager= (ViewPager) findViewById(R.id.vp_person_info);
          mRadioGroup= (RadioGroup) findViewById(R.id.rg_person_info);
        MyFpAdapter adapter=new MyFpAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
}
