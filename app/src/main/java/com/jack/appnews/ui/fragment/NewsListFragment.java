package com.jack.appnews.ui.fragment;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jack.appnews.R;
import com.jack.appnews.adapter.NewsListRecycleAdapter;
import com.jack.appnews.bean.ItemNewsBean;
import com.jack.appnews.ui.BaseFragment;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * 新闻列表
 */
public class NewsListFragment extends BaseFragment {
    @BindView(R.id.x_recycle_list)
    XRecyclerView mRecyclerView;

    private NewsListRecycleAdapter mAdapter;
    private List<ItemNewsBean> mList = new ArrayList<>();
    private List<String> imgList1 = new ArrayList<>();
    private List<String> imgList2 = new ArrayList<>();
    private int times = 0;

    @Override
    protected int inflateLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void prepare() {
        genImgList();
    }

    protected void initViews() {
        //1)初始化RecyclerView设置
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        //2)添加布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //3)配置Adapter
        mList = genData();
        mAdapter = new NewsListRecycleAdapter(getContext(), mList);
        mRecyclerView.setAdapter(mAdapter);

        //4) 监听 点击,注意是监听 mAdapter ，而不是 mRecyclerView
        mAdapter.setOnItemClickListener(new NewsListRecycleAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getContext(), "click, pos:" + position, Toast.LENGTH_LONG).show();
            }
        });

        //5)实现 下拉刷新和加载更多 接口
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear(); //先要清掉数据

                        List<ItemNewsBean> list = genRefreshData();
                        mList.addAll(list); //再将数据插入到前面

                        mAdapter.notifyDataSetChanged();

                        mRecyclerView.refreshComplete(); //下拉刷新完成
                        Toast.makeText(getContext(), "刷新完成，新加" + list.size() + "条新闻", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }

            @Override
            public void onLoadMore() {
                if (times < 20) {//加载20次后，就不再加载更多
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            List<ItemNewsBean> list = genLoadMoreData();
                            mList.addAll(list); //直接将数据追加到后面
                            Toast.makeText(getContext(), "加载完成，新加" + list.size() + "条新闻", Toast.LENGTH_SHORT).show();

                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            List<ItemNewsBean> list = genLoadMoreData();
                            mList.addAll(list); //将数据追加到后面

                            mAdapter.notifyDataSetChanged();
                            mRecyclerView.setNoMore(true);
                        }
                    }, 1000);
                }
                times++;
            }
        });
    }

    /**
     * 初始化需要显示的图片
     */
    private void genImgList() {
        imgList1.add("http://p3-tt.bytecdn.cn/list/300x196/pgc-image/ROLsHtn2l4vdxK");
        imgList2.add("http://p3-tt.bytecdn.cn/list/300x196/1f50e0009802d39b66b76");
    }

    @NonNull
    /**
     * 生成数据实例
     */
    private List<ItemNewsBean> genData() {
        List<ItemNewsBean> mList = new ArrayList<>();
        mList.add(new ItemNewsBean("奔驰之后是宝马，汽车“召回”和“不召回”哪个更可怕？", "央视网", imgList1, 1));
        mList.add(new ItemNewsBean("全台所有村长遭恐吓：访大陆如签协议，将被罚50万", "大连阳光新闻网", 1));
        mList.add(new ItemNewsBean("中国驻斯里兰卡大使馆：中国公民确认死亡人数修正为1人", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("特斯拉回应车辆自燃:已联络车主 最大努力协助善后", "一起来育儿", imgList2, 1));
        mList.add(new ItemNewsBean("中国驻斯里兰卡大使馆：中国公民确认死亡人数修正为1人", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("住建部预警房价地价波动较大城市 部分区域楼市调控或升级", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("中国驻斯里兰卡大使馆：中国公民确认死亡人数修正为1人", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("全台所有村长遭恐吓：访大陆如签协议，将被罚50万", "大连阳光新闻网", 1));
        mList.add(new ItemNewsBean("特斯拉回应车辆自燃:已联络车主 最大努力协助善后", "一起来育儿", imgList2, 1));

        Collections.shuffle(mList);//打乱顺序输出，为了美观
        return mList;
    }

    private List<ItemNewsBean> genRefreshData() {
        List<ItemNewsBean> mList = new ArrayList<>();
        mList.add(new ItemNewsBean("刷新数据：中石化新任总经理，有颗小行星以他命名", "腾讯新闻网", imgList1, 1));
        mList.add(new ItemNewsBean("刷新数据：互联护苗·2019活动启动", "一点咨询新闻", imgList2, 1));
        mList.add(new ItemNewsBean("刷新数据：全台所有村长遭恐吓：访大陆如签协议，将被罚50万", "大连阳光新闻网", 1));
        mList.add(new ItemNewsBean("刷新数据：中国驻斯里兰卡大使馆：中国公民确认死亡人数修正为1人", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("刷新数据：住建部预警房价地价波动较大城市 部分区域楼市调控或升级", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("刷新数据：中国驻斯里兰卡大使馆：中国公民确认死亡人数修正为1人", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("刷新数据：住建部之所以对部分热点城市发出预警，意在对市场预期进行适度引导", "一点咨询新闻", 1));
        mList.add(new ItemNewsBean("刷新数据：其他政策的调整对楼市影响将相对有限", "一点咨询新闻", 1));

        Collections.shuffle(mList);//打乱顺序输出，为了美观
        return mList;
    }

    private List<ItemNewsBean> genLoadMoreData() {
        List<ItemNewsBean> mList = new ArrayList<>();
        mList.add(new ItemNewsBean("加载数据：清华大学：博士生不再强制要求在学期间发表论文", "百度新闻网", imgList1, 1));
        mList.add(new ItemNewsBean("加载数据：这几位部长去了同一个地方，事关高层部署", "一点咨询新闻", 1));

        Collections.shuffle(mList);//打乱顺序输出，为了美观
        return mList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRecyclerView != null) {
            mRecyclerView.destroy();
            mRecyclerView = null;
        }
    }
}