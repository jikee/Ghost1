package com.mph.ghost.ghost1.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.utils.AllActivitiesHolder;
import com.mph.ghost.ghost1.utils.TheApplication;
import com.mph.ghost.ghost1.widget.DrawableTextView;
import com.mph.ghost.ghost1.widget.TitleBar;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 马鹏昊
 * @date {}
 * @des 乐透兵团页(大乐透的前后区都选)
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class LTBTActivity extends BaseActivity {

    private static final int RED_BALL = 0;
    private static final int BLUE_BALL = 1;

    @BindView(R.id.title)
    TitleBar mTitle;
    @BindView(R.id.numbersContainer)
    GridView mNumbersContainer;
    @BindView(R.id.latterNumbersContainer)
    GridView mLatterNumbersContainer;
    @BindView(R.id.randomBet)
    DrawableTextView mRandomBet;
    @BindView(R.id.buy)
    Button mBuy;

    //前区号码
    private MyBaseAdapter mAdapter;
    //后区号码
    private MyLatterBaseAdapter mLatterAdapter;

    private static final int MIN_NUMBERS = 5;
    private static final int LATTER_MIN_NUMBERS = 2;
    private static final int MAX_NUMBERS = 18;
    private static final int LATTER_MAX_NUMBERS = 12;
    private static final int SUM_NUMBERS = 35;
    private static final int LATTER_SUM_NUMBERS = 12;
    //机选次数
    private static final int SUM_SELECT = 10;

    //当前自动选到第几次
    private int currentSelectNum = 0;

    //保存前区选择的结果(存取不会重复,方便存删)
    private Set<Integer> mResult;
    //保存后区选择的结果(存取不会重复,方便存删)
    private Set<Integer> mLatterResult;

    //机选的类型
    private int randomType = -1;
    //机选三分之一
    private static final int TYPE_ONE_THIRD = 1;
    //机选二分之一
    private static final int TYPE_TWO_THIRD = 2;
    //机选全部金额
    private static final int TYPE_ALL = 3;
    //机选一注（摇一摇）
    private static final int TYPE_ONLY_ONE = 4;
    private String mPeriodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitle.setRightButtonEnable(false);
        mTitle.setEvents(new TitleBar.AddClickEvents() {
            @Override
            public void clickLeftButton() {
                AllActivitiesHolder.removeAct(LTBTActivity.this);
            }

            @Override
            public void clickRightButton() {
            }
        });

        init();

        getData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ltbt;
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


    private void init() {

        mResult = new HashSet();
        mLatterResult = new HashSet();
        mAdapter = new MyBaseAdapter();
        mNumbersContainer.setAdapter(mAdapter);

        mLatterAdapter = new MyLatterBaseAdapter();
        mLatterNumbersContainer.setAdapter(mLatterAdapter);

    }

    private class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return SUM_NUMBERS;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(LTBTActivity.this).inflate(R.layout.number_item, null);
            final TextView number = (TextView) view.findViewById(R.id.text);
            int index = position + 1;
            if (index < 10)
                number.setText("0" + index);
            else
                number.setText("" + index);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //当点击的是未选中的时候判断是否超出最大个数
                    if (mResult.size() >= MAX_NUMBERS && !number.isSelected()) {
                        Toast.makeText(LTBTActivity.this, "最多只能选" + MAX_NUMBERS + "个号码哦~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //添加点击事件
                    if (number.isSelected()) {
                        number.setSelected(false);
                        int ballInt = Integer.parseInt(number.getText().toString());
                        mResult.remove(ballInt);
                    } else {
                        number.setSelected(true);
                        int ballInt = Integer.parseInt(number.getText().toString());
                        mResult.add(ballInt);
                    }

                }
            });

            return view;
        }

    }


    private class MyLatterBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return LATTER_SUM_NUMBERS;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(LTBTActivity.this).inflate(R.layout.latter_number_item, null);
            final TextView number = (TextView) view.findViewById(R.id.text);
            int index = position + 1;
            if (index < 10)
                number.setText("0" + index);
            else
                number.setText("" + index);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //当点击的是未选中的时候判断是否超出最大个数
                    if (mLatterResult.size() >= LATTER_MAX_NUMBERS && !number.isSelected()) {
                        Toast.makeText(LTBTActivity.this, "最多只能选" + LATTER_MAX_NUMBERS + "个号码哦~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //添加点击事件
                    if (number.isSelected()) {
                        number.setSelected(false);
                        int ballInt = Integer.parseInt(number.getText().toString());
                        mLatterResult.remove(ballInt);
                    } else {
                        number.setSelected(true);
                        int ballInt = Integer.parseInt(number.getText().toString());
                        mLatterResult.add(ballInt);

                    }
                }
            });

            return view;
        }

    }

    @OnClick({R.id.randomBet, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.randomBet:
                //弹出选项
                randomSelectNumber();
                break;
            case R.id.buy:
                //确认选择
                if (mResult.size() < MIN_NUMBERS || mLatterResult.size() < LATTER_MIN_NUMBERS) {
                    Toast.makeText(this, "前区至少选" + MIN_NUMBERS + "个号码," + "后区至少选" + LATTER_MIN_NUMBERS + "个号码哦~", Toast.LENGTH_SHORT).show();
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

    /**
     * 机选动画
     */
    private void randomSelectNumber() {
        //防止同一时间多次点击
        mRandomBet.setEnabled(false);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currentSelectNum > SUM_SELECT) {
                            timer.cancel();
                            currentSelectNum = 0;
                            mRandomBet.setEnabled(true);
                        }
                        //先清空已选的
                        clearAlreadySelect();
                        //按照最少个数去选前区号
                        int i = 0;
                        while (i < MIN_NUMBERS) {
                            int position = new Random().nextInt(SUM_NUMBERS);
                            LinearLayout layout = (LinearLayout) mNumbersContainer.getChildAt(position);
                            TextView number = (TextView) layout.getChildAt(0);
                            if (number.isSelected()) {
                                continue;
                            }
                            number.setSelected(true);
                            int ballInt = Integer.parseInt(number.getText().toString());
                            mResult.add(ballInt);
                            i++;
                        }
                        //按照最少个数去选后区号
                        int j = 0;
                        while (j < LATTER_MIN_NUMBERS) {
                            int position = new Random().nextInt(LATTER_SUM_NUMBERS);
                            LinearLayout layout = (LinearLayout) mLatterNumbersContainer.getChildAt(position);
                            TextView number = (TextView) layout.getChildAt(0);
                            if (number.isSelected()) {
                                continue;
                            }
                            number.setSelected(true);
                            int ballInt = Integer.parseInt(number.getText().toString());
                            mLatterResult.add(ballInt);
                            j++;
                        }
                        currentSelectNum++;
                    }
                });
            }
        }, 0, 100);

    }

    /**
     * 清空已选的
     */
    public void clearAlreadySelect() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            LinearLayout layout = (LinearLayout) mNumbersContainer.getChildAt(i);
            layout.getChildAt(0).setSelected(false);
        }
        mResult.clear();
        for (int i = 0; i < mLatterAdapter.getCount(); i++) {
            LinearLayout layout = (LinearLayout) mLatterNumbersContainer.getChildAt(i);
            layout.getChildAt(0).setSelected(false);
        }
        mLatterResult.clear();
    }

}
