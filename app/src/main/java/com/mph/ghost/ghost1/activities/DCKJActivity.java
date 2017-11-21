package com.mph.ghost.ghost1.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.utils.AllActivitiesHolder;
import com.mph.ghost.ghost1.utils.TheApplication;
import com.mph.ghost.ghost1.widget.TitleBar;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 马鹏昊
 * @date {}
 * @des 单场空军页
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class DCKJActivity extends BaseActivity {

    @BindView(R.id.title)
    TitleBar mTitle;
    @BindView(R.id.battleHistory)
    TextView mBattleHistory;
    @BindView(R.id.recentlyGrade)
    TextView mRecentlyGrade;
    @BindView(R.id.averageRate)
    TextView mAverageRate;
    @BindView(R.id.betRate)
    TextView mBetRate;
    @BindView(R.id.homeWin)
    TextView mHomeWin;
    @BindView(R.id.fare)
    TextView mFare;
    @BindView(R.id.homeLose)
    TextView mHomeLose;
    @BindView(R.id.randomBet)
    Button mRandomBet;
    @BindView(R.id.confirm)
    Button mConfirm;


    private static final int SUM = 3;
    //记录选择结果
    private static final int HOME_WIN = 3;
    private static final int FARE = 1;
    private static final int HOME_LOSE = 0;
    private static final int NON_SELECT = -1;
    private int selectResult = NON_SELECT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitle.setRightButtonEnable(false);
        mTitle.setEvents(new TitleBar.AddClickEvents() {
            @Override
            public void clickLeftButton() {
                AllActivitiesHolder.removeAct(DCKJActivity.this);
            }

            @Override
            public void clickRightButton() {
            }
        });

        getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dckj;
    }

    private void getData() {

        final Dialog progressDialog = TheApplication.createLoadingDialog(this, "");
        progressDialog.show();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 1000);


    }

    @OnClick({ R.id.homeWin, R.id.fare, R.id.homeLose, R.id.randomBet, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeWin:
                clickSelect(HOME_WIN);
                break;
            case R.id.fare:
                clickSelect(FARE);
                break;
            case R.id.homeLose:
                clickSelect(HOME_LOSE);
                break;
            case R.id.randomBet:
                randomSelect();
                break;
            case R.id.confirm:
                if (selectResult == NON_SELECT) {
                    Toast.makeText(this, "请先投注~", Toast.LENGTH_SHORT).show();
                    return;
                }
                fakeHttpRequest();
                break;
        }
    }

    private void fakeHttpRequest() {
        final Dialog progressDialog = TheApplication.createLoadingDialog(this, "");
        progressDialog.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Toast.makeText(TheApplication.getContext(), "购买成功~", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, 1000);
    }

    private void randomSelect() {
        Random random = new Random();
        int a = random.nextInt(SUM);
        select(a);
    }

    private void clickSelect(int flag) {
        mHomeWin.setSelected(false);
        mFare.setSelected(false);
        mHomeLose.setSelected(false);
        switch (flag) {
            case HOME_WIN:
                mHomeWin.setSelected(true);
                selectResult = HOME_WIN;
                break;
            case FARE:
                mFare.setSelected(true);
                selectResult = FARE;
                break;
            case HOME_LOSE:
                mHomeLose.setSelected(true);
                selectResult = HOME_LOSE;
                break;
        }
    }

    private void select(int flag) {
        mHomeWin.setSelected(false);
        mFare.setSelected(false);
        mHomeLose.setSelected(false);
        switch (flag) {
            //0,1,2是产生的随机数
            case 0:
                mHomeWin.setSelected(true);
                selectResult = HOME_WIN;
                break;
            case 1:
                mFare.setSelected(true);
                selectResult = FARE;
                break;
            case 2:
                mHomeLose.setSelected(true);
                selectResult = HOME_LOSE;
                break;
        }

    }

}
