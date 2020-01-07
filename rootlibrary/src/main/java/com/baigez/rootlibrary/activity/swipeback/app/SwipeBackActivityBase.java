package com.baigez.rootlibrary.activity.swipeback.app;


import com.baigez.rootlibrary.activity.swipeback.SwipeBackLayout;



public interface SwipeBackActivityBase {
/**
 * @return the SwipeBackLayout associated with this activity.
 */
public abstract SwipeBackLayout getSwipeBackLayout();

public abstract void setSwipeBackEnable(boolean enable);

/**
 * Scroll out contentView and finish the activity
 */
public abstract void scrollToFinishActivity();

}
