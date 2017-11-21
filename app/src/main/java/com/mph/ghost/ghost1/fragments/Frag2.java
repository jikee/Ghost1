package com.mph.ghost.ghost1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.activities.JumpWebShow;
import com.mph.ghost.ghost1.data.NewsData;
import com.mph.ghost.ghost1.widget.pull2refresh.MyListener;
import com.mph.ghost.ghost1.widget.pull2refresh.PullToRefreshLayout;
import com.mph.ghost.ghost1.widget.pull2refresh.pullableview.PullableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag2 extends Fragment {

    Unbinder unbinder;
    MyAdapter mAdapter;
    @BindView(R.id.newsList)
    PullableListView mNewsList;
    @BindView(R.id.refreshLayout)
    PullToRefreshLayout mRefreshLayout;
    private List<NewsData> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag2, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRefreshLayout.setOnRefreshListener(new MyListener(){
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                super.onRefresh(pullToRefreshLayout);
                mRefreshLayout.refreshFinish(0);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                super.onLoadMore(pullToRefreshLayout);
                mRefreshLayout.loadmoreFinish(0);
            }
        });
        initData();
        return view;
    }

    private void initData() {
        addData();
        mAdapter = new MyAdapter();
        mNewsList.setAdapter(mAdapter);
    }

    private void addData() {
        mData = new ArrayList();
        NewsData data1 = new NewsData();
        data1.setLinkUrl("http://www.lottery.gov.cn/gyjb/20171119/60906.html");
        data1.setIconUrl("http://img2.imgtn.bdimg.com/it/u=649233889,809774869&fm=200&gp=0.jpg");
        data1.setTitle("生活皆因体彩而精彩 点亮梦想体彩就在我身边");
        data1.setDesc("中国体育彩票自1994年全国统一发行以来，已筹集公益金超过3700亿元，为社会公益事业和体育事业的发展贡献了重要力量。在庞大的数字后面，不论是明星还是普通人，很多人的生活因体彩而更加多姿多彩。");
        data1.setDate("2017-11-19 09:08:01");
        mData.add(data1);
        NewsData data2 = new NewsData();
        data2.setLinkUrl("http://www.lottery.gov.cn/gyjb/20171119/60907.html");
        data2.setIconUrl("http://img2.imgtn.bdimg.com/it/u=1305542291,1319601483&fm=200&gp=0.jpg");
        data2.setTitle("搭建服务平台提供志愿服务 体彩致力大家帮大家");
        data2.setDesc("近日，江苏体彩启动“爱行走”第三季，所募集的30万元公益金将用于“体彩春蕾班”项目，改善孩子们的学习环境；深圳体彩收集闲置体育用品，送到偏远贫困地区学校，丰富孩子们的课余生活。青海、山东、浙江的体彩志愿者长年参与公益活动，为弱势群体送关爱。");
        data2.setDate("2017-11-19 09:14:51");
        mData.add(data2);
        NewsData data3 = new NewsData();
        data3.setLinkUrl("http://www.lottery.gov.cn/gyjb/20171117/60872.html");
        data3.setIconUrl("http://img0.imgtn.bdimg.com/it/u=1038896168,1144457746&fm=27&gp=0.jpg");
        data3.setTitle("爱心助学 全运惠民——2017年天津体彩公益之路");
        data3.setDesc("前不久，中国体育彩票“新长城助学基金”捐赠仪式在南开大学商学院举行。在这次的捐赠仪式上，天津体彩向南开大学10名大一新生进行了爱心捐助，帮助他们减轻生活重担。这也是继今年多次举办的“公益体彩·快乐操场”活动之后，天津体彩又一次将");
        data3.setDate("2017-11-18 12:34:45");
        mData.add(data3);
        NewsData data4 = new NewsData();
        data4.setLinkUrl("http://www.lottery.gov.cn/tzgg/20171104/60573.html");
        data4.setIconUrl("http://img5.imgtn.bdimg.com/it/u=362818495,4200832760&fm=27&gp=0.jpg");
        data4.setTitle("关于11月4日中国体育彩票摇奖直播中断的说明");
        data4.setDesc("11月4 日，中国体育彩票超级大乐透游戏第17129 期开奖过程中，摇出的后区第二个号球意外滑出展示球道。 按照相关规定，现场公证人员将滑落的号球放回展示球道，并确认该滑落号球有效。");
        data4.setDate("2017-11-18 08:22:12");
        mData.add(data4);
        NewsData data5 = new NewsData();
        data5.setLinkUrl("http://www.lottery.gov.cn/tzgg/20171018/60199.html");
        data5.setIconUrl("http://img1.imgtn.bdimg.com/it/u=1147897019,4261734516&fm=27&gp=0.jpg");
        data5.setTitle("关于央广体彩开奖结果播报时间调整的公告");
        data5.setDesc("由于直播“十九大”特别直播节目《央广公开课》，2017年10月18日至10月25日中央人民广播电台体育彩票开奖结果的播报时间由21:01调整到约22:31。若直播节目延时，体育彩票开奖结果播报顺延。由此给广大购彩者带来的不便，敬请谅解。");
        data5.setDate("2017-11-18 09:27:15");
        mData.add(data5);
        NewsData data6 = new NewsData();
        data6.setLinkUrl("http://www.lottery.gov.cn/gpgddt/20171117/60855.html");
        data6.setIconUrl("http://img2.imgtn.bdimg.com/it/u=2163208603,1365766303&fm=27&gp=0.jpg");
        data6.setTitle("吉林体彩高频“11选5”3000万元大派奖来啦！");
        data6.setDesc("体彩高频11选5一直用自己独特的魅力——简单、易中、返奖高，赢得了很多彩友的青睐，吉林省体育彩票管理中心为回馈本省彩友对体彩高频11选5游戏的");
        data6.setDate("2017-11-17 10:05:42");
        mData.add(data6);
        NewsData data7 = new NewsData();
        data7.setLinkUrl("http://www.lottery.gov.cn/gpzjzx/20171116/60839.html");
        data7.setIconUrl("http://img5.imgtn.bdimg.com/it/u=2020875290,792167061&fm=27&gp=0.jpg");
        data7.setTitle("京城彩站晒红单：多期投注11选5夺奖“放长线”");
        data7.setDesc("此前，本报“京城彩站晒红单”栏目曾陆续晒出并介绍过“多期投注”的中奖票，引起了很多彩友的关注。随着11选5游戏在京城彩友中的日益普及，投注者对“多期投注”这种形式也愈");
        data7.setDate("2017-11-17 11:24:56");
        mData.add(data7);
        NewsData data8 = new NewsData();
        data8.setLinkUrl("http://www.lottery.gov.cn/zczx/20171117/60854.html");
        data8.setIconUrl("http://img2.imgtn.bdimg.com/it/u=3976651635,149131373&fm=27&gp=0.jpg");
        data8.setTitle("山东济南超级足彩季 球迷沙龙邀您来");
        data8.setDesc("中国足球彩票史上最强的3亿元派奖活动正在火热进行，球迷们跨年度的盛世——英超、意甲、德甲、西甲、法甲五大联赛饕餮盛宴");
        data8.setDate("2017-11-17 16:37:33");
        mData.add(data8);
        NewsData data9 = new NewsData();
        data9.setLinkUrl("http://www.lottery.gov.cn/zqzjxw/20171110/60728.html");
        data9.setIconUrl("http://img0.imgtn.bdimg.com/it/u=2284916258,1821359506&fm=27&gp=0.jpg");
        data9.setTitle("福建福州22927站彩友8场串7关拿走38万");
        data9.setDesc("今年刚刚上线的竞彩自由串方式已被越来越多的彩友欣然接收并在实际投注中广泛采纳。这不，又一位彩友利用自由串的方式，选了8场比较，进行7关投注，最终虽然");
        data9.setDate("2017-11-17 13:15:34");
        mData.add(data9);
        NewsData data10 = new NewsData();
        data10.setLinkUrl("http://www.lottery.gov.cn/jczq/20171110/60742.html");
        data10.setIconUrl("http://img1.imgtn.bdimg.com/it/u=418635830,1852776947&fm=27&gp=0.jpg");
        data10.setTitle("复选连中4关1比2 重庆神手32元力擒14万");
        data10.setDesc("本周是国际比赛日，主流联赛都暂时偃旗息鼓，使得赛事选择相对有限，可以竞猜的比赛并不太多，但仍然有熟谙竞彩赛事的彩民能够中出大奖");
        data10.setDate("2017-11-17 09:10:37");
        mData.add(data10);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(getActivity()).inflate(R.layout.news_item, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else
                viewHolder = (ViewHolder) view.getTag();

            final NewsData newsData = mData.get(i);
            viewHolder.mIcon.setImageURI(newsData.getIconUrl());
            viewHolder.mTitle.setText(newsData.getTitle());
            viewHolder.mDesc.setText(newsData.getDesc());
            viewHolder.mDate.setText(newsData.getDate());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), JumpWebShow.class);
                    intent.putExtra("linkUrl", newsData.getLinkUrl());
                    intent.putExtra("title", newsData.getTitle());
                    startActivity(intent);
                }
            });
            return view;
        }

        class ViewHolder {
            @BindView(R.id.icon)
            SimpleDraweeView mIcon;
            @BindView(R.id.title)
            TextView mTitle;
            @BindView(R.id.desc)
            TextView mDesc;
            @BindView(R.id.date)
            TextView mDate;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
