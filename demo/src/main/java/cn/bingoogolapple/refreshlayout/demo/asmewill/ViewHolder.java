package cn.bingoogolapple.refreshlayout.demo.asmewill;

import android.view.View;

/**
 * Created by shui on 2017/11/14.
 *
 * 子类和父类构造方法继承的关系
 * 1.如果父类有参数的构造方法一个，那么子类必须实现其带参数的构造方法，并通过super回调
 * 2.如果父类有参数的构造方法二个，那么子类必须实现其带参数的构造方法的其中一个或者二个，并通过super回调
 * 3.如果父类中有无参数的构造方法，那么子类无需实现任何父类的构造方法即可继承
 */

public class ViewHolder extends MyViewHolder {
    public ViewHolder(boolean isLoadingMore) {
        super(isLoadingMore);
    }

    public ViewHolder(boolean isLoadingMore, View view) {
        super(isLoadingMore, view);
    }

//    public ViewHolder(boolean isLoadingMore) {
//        super(isLoadingMore);
//    }


}
