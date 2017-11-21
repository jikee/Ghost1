package com.mph.ghost.ghost1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.activities.DCKJActivity;
import com.mph.ghost.ghost1.activities.JumpWebShow;
import com.mph.ghost.ghost1.activities.LTBTActivity;
import com.mph.ghost.ghost1.activities.SSBTActivity;
import com.mph.ghost.ghost1.data.BannerInfo;
import com.mph.ghost.ghost1.utils.TheApplication;
import com.mph.ghost.ghost1.widget.LooperTextView;
import com.mph.ghost.ghost1.widget.sivin.Banner;
import com.mph.ghost.ghost1.widget.sivin.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag1 extends Fragment {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.looperview)
    LooperTextView mLooperview;
    Unbinder unbinder;
    List<String> mNoticeList;
    @BindView(R.id.toDLT)
    TextView mToDLT;
    @BindView(R.id.toZC)
    TextView mToZC;
    @BindView(R.id.toSSQ)
    TextView mToSSQ;
    //banner数据
    private List<BannerInfo> mBannerData;
    //banner的数据适配器
    private BannerAdapter mBannerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);
        unbinder = ButterKnife.bind(this, view);
        initBanner();
        initLooper();
        return view;
    }

    private void initLooper() {
        mNoticeList = new ArrayList<>();
        mNoticeList.add("恭喜湖北张先生喜获大乐透1000万");
        mNoticeList.add("恭喜山东王先生喜获足彩二等奖");
        mNoticeList.add("恭喜重庆李女士喜获七星彩一等奖");
        mNoticeList.add("恭喜上海仲先生喜获双色球一等奖");
        mLooperview.setTipList(mNoticeList);
    }

    public void initBanner() {
        mBannerData = new ArrayList<>();
        BannerInfo info1 = new BannerInfo();
        info1.setTitle("双色球");
        info1.setPhoto("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=754188189,2240270937&fm=27&gp=0.jpg");
        info1.setTarget("http://www.zhcw.com/");
        BannerInfo info2 = new BannerInfo();
        info2.setTitle("大乐透");
        info2.setPhoto("http://img5.imgtn.bdimg.com/it/u=868812425,404448300&fm=27&gp=0.jpg");
        info2.setTarget("http://www.lottery.gov.cn/dlt/index.html");
        BannerInfo info3 = new BannerInfo();
        info3.setTitle("足彩");
        info3.setPhoto("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3665427451,808607012&fm=27&gp=0.jpg");
        info3.setTarget("http://www.lottery.gov.cn/zc/index.html");
        mBannerData.add(info1);
        mBannerData.add(info2);
        mBannerData.add(info3);
        //banner宽高比例3:1.2
        mBanner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) (((float) TheApplication.screenWidth) / 3.0f * 1.3f)));
        mBanner.setOnBannerItemClickListener(new Banner.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BannerInfo bannerInfo = mBannerData.get(position);
                final String title = bannerInfo.getTitle();
                final String linkUrl = bannerInfo.getTarget();
                if (!TextUtils.isEmpty(linkUrl)) {
                    //点击图片事件
                    Intent intent = new Intent(getActivity(), JumpWebShow.class);
                    intent.putExtra("title", title);
                    intent.putExtra("linkUrl", linkUrl);
                    getActivity().startActivity(intent);
                }
            }
        });
        mBannerAdapter = new BannerAdapter(mBannerData) {
            @Override
            protected void bindTips(TextView tv, Object o) {
                //添加标题
            }

            @Override
            public void bindImage(ImageView imageView, Object o) {
                BannerInfo bannerInfo = (BannerInfo) o;
                String imgUrl = bannerInfo.getPhoto();
                Glide.with(getActivity())
                        .load(imgUrl)
                        .into(imageView);
            }
        };
        mBanner.setBannerAdapter(mBannerAdapter);
        //初始化要有这行
        mBanner.notifiDataHasChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.toDLT, R.id.toZC, R.id.toSSQ})
    public void onViewClicked(View view) {
        Class desClass = null ;
        switch (view.getId()) {
            case R.id.toDLT:
                desClass = LTBTActivity.class;
                break;
            case R.id.toZC:
                desClass = DCKJActivity.class;
                break;
            case R.id.toSSQ:
                desClass = SSBTActivity.class;
                break;
        }
        if (desClass!=null) {
            Intent intent = new Intent(getActivity(), desClass);
            startActivity(intent);
        }
    }
}
