package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.adapter.DataCompareFragmentAdapter;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.TagTitleResponseBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.fragment.NewsPageFragment;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.NewsGroupPresenter;
import com.haoeyou.user.mvp.view.NewsGroupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名: {@link NewsGroupActivity}
 * <br/> 功能描述: 更多咨询新闻内页
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/6/5
 */
public class NewsGroupActivity extends BaseActivity implements NewsGroupView {
    public final static String TAG = "NewsGroupActivity";
    private Context mContext;
    /**
     * 选项卡总数
     */
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.tab_layout)
    TabLayout mLayoutTab;
    @Bind(R.id.vp_tab_pager)
    ViewPager mPageVp;
    List<Fragment> fragmentList;
    // 定义Tab标题
    private String[] tabTitles;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_group;
    }

    @Override
    protected void initInjector() {
        mContext = this;
        ButterKnife.bind(this);
    }

    @Override
    protected void initEventAndData() {
        //        mTitleTv.setText("新闻咨询");
        ((NewsGroupPresenter) mPresenter).getArticleTags(mContext);
    }

    @Override
    public BasePresenter getPresenter() {
        return new NewsGroupPresenter();
    }


    /**
     * 初始化UI
     */
    private void initUI() {
        // 把TabLayout和ViewPager关联起来
        DataCompareFragmentAdapter adapter = new DataCompareFragmentAdapter(getSupportFragmentManager(), tabTitles, 
                fragmentList);
        mPageVp.setAdapter(adapter);
        mLayoutTab.setupWithViewPager(mPageVp);
    }


    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showLoadProgressDialog(String str) {

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void getArticleTagsSuccess(TagTitleResponseBean bean) {
        if (bean.getTags() == null)
            return;
        tabTitles = bean.getTags();
        fragmentList = new ArrayList<Fragment>();
        for (String tag : tabTitles) {
            //创建Fragment对象，并通过Bundle对象传递值
            NewsPageFragment fragment = new NewsPageFragment();
            Bundle bundle = new Bundle();
            bundle.putString("TITLE_TAG", tag);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        initUI();
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String from) {
        Intent itt = new Intent(ct, NewsGroupActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }
}