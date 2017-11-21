package com.mph.ghost.ghost1.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.utils.AllActivitiesHolder;
import com.mph.ghost.ghost1.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar mTitle;
    @BindView(R.id.quitBtn)
    Button mQuitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mTitle.setRightButtonEnable(false);
        mTitle.setEvents(new TitleBar.AddClickEvents() {
            @Override
            public void clickLeftButton() {
                AllActivitiesHolder.removeAct(SettingActivity.this);
            }

            @Override
            public void clickRightButton() {
                //分享
            }
        });

    }

    @OnClick(R.id.quitBtn)
    public void onViewClicked() {
        AllActivitiesHolder.removeAct(this);
    }
}
