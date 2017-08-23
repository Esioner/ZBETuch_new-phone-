package com.youli.zbetuch.jingan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.youli.zbetuch.jingan.R;

/**
 * Created by liutao on 2017/8/22.
 */

public class RecruitInfoActivity extends BaseActivity implements View.OnClickListener{

    private Context mContext=this;
    private Button queryBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recruit_info);

        queryBtn= (Button) findViewById(R.id.btn_recruit_info_find);
        queryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_recruit_info_find:

                Toast.makeText(mContext,"查询按钮",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(mContext,JobInfoListActivity.class);
                startActivity(intent);

                break;

        }

    }
}
