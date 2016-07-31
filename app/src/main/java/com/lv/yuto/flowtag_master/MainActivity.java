package com.lv.yuto.flowtag_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.lv.yuto.library.FlowTagLayout;
import com.lv.yuto.library.OnTagClickListener;
import com.lv.yuto.library.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FlowTagLayout mColorFlowTagLayout;
    private FlowTagLayout mSizeFlowTagLayout;
    private FlowTagLayout mMobileFlowTagLayout;
    private FlowTagLayout mAreaFlowTagLayout;
    private TagAdapter mSizeTagAdapter;
    private TagAdapter mColorTagAdapter;
    private TagAdapter mMobileTagAdapter;
    private TagAdapter mAreaTagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColorFlowTagLayout = (FlowTagLayout) findViewById(R.id.color_flow_layout);
        mSizeFlowTagLayout = (FlowTagLayout) findViewById(R.id.size_flow_layout);
        mMobileFlowTagLayout = (FlowTagLayout) findViewById(R.id.mobile_flow_layout);
        mAreaFlowTagLayout = (FlowTagLayout) findViewById(R.id.area_flow_layout);

        //颜色
        mColorTagAdapter = new TagAdapter(this);
        mColorFlowTagLayout.setAdapter(mColorTagAdapter);
        mColorFlowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                Toast.makeText(MainActivity.this, "颜色:" + parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        //尺寸
        mSizeTagAdapter = new TagAdapter(this);
        mSizeFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mSizeFlowTagLayout.setAdapter(mSizeTagAdapter);
        mSizeFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast.makeText(MainActivity.this, "移动研发:" + sb.toString(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "没有选择标签:", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //移动研发标签
        mMobileTagAdapter = new TagAdapter(this);
        mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);
        mMobileFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast.makeText(MainActivity.this, "移动研发:" + sb.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "没有选择标签:", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //地区标签
        mAreaTagAdapter = new TagAdapter(this);
        mAreaFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_FIRSTVIEW_CHECKED_SINGLE_OTHER_CHECKED_MULTI);
        mAreaFlowTagLayout.setAdapter(mAreaTagAdapter);
        mAreaFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast.makeText(MainActivity.this, "地区:" + sb.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "没有选择标签:", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initColorData();

        initSizeData();

        initMobileData();

        initAreaData();
    }

    private void initAreaData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"不限",true));
        dataSource.add(new Tag(1,"大陆",false));
        dataSource.add(new Tag(2,"港澳台",false));
        dataSource.add(new Tag(3,"欧美",false));
        dataSource.add(new Tag(4,"日韩",true));
        dataSource.add(new Tag(5,"新加坡",false));
        dataSource.add(new Tag(6,"马来西亚",false));
        dataSource.add(new Tag(7,"泰国",false));
        dataSource.add(new Tag(8,"其他",false));
        mAreaTagAdapter.onlyAddAll(dataSource);
    }

    private void initMobileData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"android",true));
        dataSource.add(new Tag(1,"安卓",false));
        dataSource.add(new Tag(2,"SDK源码",false));
        dataSource.add(new Tag(3,"IOS",false));
        dataSource.add(new Tag(4,"iPhone",false));
        dataSource.add(new Tag(5,"游戏",true));
        dataSource.add(new Tag(6,"fragment",false));
        dataSource.add(new Tag(7,"viewcontroller",false));
        dataSource.add(new Tag(8,"cocoachina",false));
        dataSource.add(new Tag(8,"移动研发工程师",false));
        dataSource.add(new Tag(9,"移动互联网",true));
        dataSource.add(new Tag(10,"高薪+期权",false));
        mMobileTagAdapter.onlyAddAll(dataSource);
    }

    private void initColorData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"红色",false));
        dataSource.add(new Tag(1,"黑色",false));
        dataSource.add(new Tag(2,"花边色",false));
        dataSource.add(new Tag(3,"深蓝色",false));
        dataSource.add(new Tag(4,"白色",false));
        dataSource.add(new Tag(5,"玫瑰红色",true));
        dataSource.add(new Tag(7,"葡萄红色",false));
        dataSource.add(new Tag(8,"屎黄色",false));

        mColorTagAdapter.onlyAddAll(dataSource);
    }

    /**
     * 初始化数据
     */
    private void initSizeData() {
        List<Tag> dataSource = new ArrayList<>();
        dataSource.add(new Tag(0,"28 (2.1尺)",false));
        dataSource.add(new Tag(1,"29 (2.2尺)",false));
        dataSource.add(new Tag(2,"30 (2.3尺)",false));
        dataSource.add(new Tag(3,"31 (2.4尺)",false));
        dataSource.add(new Tag(4,"32 (2.5尺)........",false));
        dataSource.add(new Tag(5,"33 (2.6尺)",false));
        dataSource.add(new Tag(6,"34 (2.7尺)",false));
        dataSource.add(new Tag(7,"35 (2.8尺)",false));
        dataSource.add(new Tag(8,"36 (2.9尺)",false));
        dataSource.add(new Tag(8,"37 (3.0尺)",false));
        dataSource.add(new Tag(9,"38 (3.1尺)",false));
        mSizeTagAdapter.onlyAddAll(dataSource);
    }



}
