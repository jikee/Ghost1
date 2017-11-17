package com.mph.ghost.ghost1.activities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.dao.MainDao;
import com.mph.ghost.ghost1.data.GlobalData;
import com.mph.ghost.ghost1.utils.AllActivitiesHolder;
import com.mph.ghost.ghost1.utils.JumpActivityUtil;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 马鹏昊
 * @date {2.17.8.2}
 * @des 启动页
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class LaunchActivity extends BaseActivity {

    private SimpleDraweeView mContent;

    private MainDao mainDao;

    private RelativeLayout mJumpArea;
    private TextView mCountDownShow;
    private static final int COUNT_DOWN_TIME = 3;
    private int residualTime = COUNT_DOWN_TIME;
    private Timer timer = new Timer();

    private boolean hasLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //防止长时间服务无响应
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toGetData();
            }
        }, 3000);

    }

    private void toGetData() {

        mainDao = new MainDao();

        Observable.create(new ObservableOnSubscribe<JSONObject>() {
            @Override
            public void subscribe(ObservableEmitter<JSONObject> e) throws Exception {
                JSONObject nr = mainDao.getRequestData();
                e.onNext(nr);
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Consumer<JSONObject>() {
                    @Override
                    public void accept(JSONObject result) throws Exception {
                        //根据type和code去决定是显示h5还是原生界面
//                        switch ()
                        GlobalData.url = "";
                        JumpActivityUtil.openActivity(LaunchActivity.this,NativeMainActivity.class);

                        AllActivitiesHolder.removeAct(LaunchActivity.this);
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }
}
