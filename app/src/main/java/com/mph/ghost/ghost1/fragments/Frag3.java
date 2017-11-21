package com.mph.ghost.ghost1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.activities.JumpWebShow;
import com.mph.ghost.ghost1.activities.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag3 extends Fragment {

    @BindView(R.id.portrait)
    SimpleDraweeView mPortrait;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.sign)
    TextView mSign;
    @BindView(R.id.balance)
    TextView mBalance;
    @BindView(R.id.setting)
    LinearLayout mSetting;
    @BindView(R.id.contact)
    LinearLayout mContact;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag3, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.setting, R.id.contact})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.setting:
                intent = new Intent(getActivity(),SettingActivity.class);
                break;
            case R.id.contact:
                intent = new Intent(getActivity(), JumpWebShow.class);
                intent.putExtra("title","《彩票管理条例》");
                intent.putExtra("linkUrl","http://zt.cnhubei.com/2012-03/18/cms530892article.shtml");
                break;
        }
        if (intent!=null)
            startActivity(intent);
    }
}
