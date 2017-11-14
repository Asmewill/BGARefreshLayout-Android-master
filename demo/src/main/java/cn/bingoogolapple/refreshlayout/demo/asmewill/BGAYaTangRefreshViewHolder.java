package cn.bingoogolapple.refreshlayout.demo.asmewill;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import cn.bingoogolapple.refreshlayout.demo.R;

/**
 * Created by shuij on 2017/11/11 0011.
 */

public class BGAYaTangRefreshViewHolder extends BGARefreshViewHolder {
    private TextView mHeaderStatusTv;
    private String mPullDownRefreshText = "下拉刷新";
    private String mReleaseRefreshText = "松开刷新";
    private String mRefreshingText = "正在刷新...";
    private boolean mIsLoadingMoreEnabled = true;
    /**
     * 整个加载更多控件的背景颜色资源id
     */
    private int mLoadMoreBackgroundColorRes = -1;
    /**
     * 整个加载更多控件的背景drawable资源id
     */
    private int mLoadMoreBackgroundDrawableRes = -1;

    /**
     * @param context
     * @param isLoadingMoreEnabled 上拉加载更多是否可用
     */
    public BGAYaTangRefreshViewHolder(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
    }
    /****
     * 重新自定义FooterView
     * @return
     */
    @Override
    public View getRefreshHeaderView() {
        if (mRefreshHeaderView == null) {
            mRefreshHeaderView = View.inflate(mContext, R.layout.refresh_head, null);
            mRefreshHeaderView.setBackgroundColor(mContext.getResources().getColor(R.color.Cf2f2f5));
            if (mRefreshViewBackgroundColorRes != -1) {
                mRefreshHeaderView.setBackgroundResource(mRefreshViewBackgroundColorRes);
            }
            if (mRefreshViewBackgroundDrawableRes != -1) {
                mRefreshHeaderView.setBackgroundResource(mRefreshViewBackgroundDrawableRes);
            }
            mHeaderStatusTv = (TextView) mRefreshHeaderView.findViewById(R.id.tipTextView);
            mHeaderStatusTv.setText(mPullDownRefreshText);

        }
        return mRefreshHeaderView;
    }

    /****
     * 重新自定义FooterView
     * @return
     */

    @Override
    public View getLoadMoreFooterView() {
        if (!mIsLoadingMoreEnabled) {
            return null;
        }
        if (mLoadMoreFooterView == null) {
            mLoadMoreFooterView = View.inflate(mContext, R.layout.listview_footer, null);
            mLoadMoreFooterView.setBackgroundColor(Color.TRANSPARENT);
            if (mLoadMoreBackgroundColorRes != -1) {
                mLoadMoreFooterView.setBackgroundResource(mLoadMoreBackgroundColorRes);
            }
            if (mLoadMoreBackgroundDrawableRes != -1) {
                mLoadMoreFooterView.setBackgroundResource(mLoadMoreBackgroundDrawableRes);
            }
            mFooterStatusTv = (TextView) mLoadMoreFooterView.findViewById(R.id.tv_load);
            mFooterStatusTv.setText("拼命"+mLodingMoreText);
        }
        return mLoadMoreFooterView;
    }

    @Override
    public void handleScale(float scale, int moveYDistance) {

    }

    @Override
    public void changeToIdle() {

    }

    @Override
    public void changeToPullDown() {
        mHeaderStatusTv.setText(mPullDownRefreshText);
    }

    @Override
    public void changeToReleaseRefresh() {
        mHeaderStatusTv.setText(mReleaseRefreshText);
    }

    @Override
    public void changeToRefreshing() {
        mHeaderStatusTv.setText(mRefreshingText);
    }

    @Override
    public void onEndRefreshing() {
        mHeaderStatusTv.setText(mPullDownRefreshText);

    }

    /***
     * 设置刷新提示语句
     * @param mRefreshingText
     */

    public void setmRefreshingText(String mRefreshingText) {
        this.mRefreshingText = mRefreshingText;
    }

    public void setmPullDownRefreshText(String mPullDownRefreshText) {
        this.mPullDownRefreshText = mPullDownRefreshText;
    }

    public void setmReleaseRefreshText(String mReleaseRefreshText) {
        this.mReleaseRefreshText = mReleaseRefreshText;
    }

    /***
     * 设置控件的背景资源id
     * @param mLoadMoreBackgroundColorRes
     */

    public void setmLoadMoreBackgroundColorRes(int mLoadMoreBackgroundColorRes) {
        this.mLoadMoreBackgroundColorRes = mLoadMoreBackgroundColorRes;
    }

    public void setmLoadMoreBackgroundDrawableRes(int mLoadMoreBackgroundDrawableRes) {
        this.mLoadMoreBackgroundDrawableRes = mLoadMoreBackgroundDrawableRes;
    }
}
