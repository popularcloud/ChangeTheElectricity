package com.younge.changetheelectricity.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;

public class SlideBottomLayout extends LinearLayout {

    /**
     * 手势按下位置记录
     */
    private float downY;
    /**
     * 手势移动位置记录
     */
    private float moveY;
    /**
     * 手势移动距离
     */
    private int movedDis;
    /**
     * 移动的最大值
     */
    private int movedMaxDis;
    /**
     * SlideBottom 的子视图
     */
    private View childView;
    /**
     * SlideBottom状态
     * isShow的两种状态 伸张与收缩
     */
    private Boolean isShow = false;
    /**
     * 状态切换阈值
     */
    private float hideWeight = 0.3f;
    /**
     * 拦截器参数相关
     * 记录Action.Down按下位置
     * @param hideWeight
     */
    private int CurrentY;

    /**
     * 视图滚动辅助
     */
    private Scroller mScroller;

    /**
     *
     * 标记：childView到达parent或者其他的顶部
     */
    private boolean arriveTop = false;

    /**
     * 设置：childView的初始可见高度
     */
    private float visibilityHeight;
    /**
     * 绑定的Rc
     */
    private RecyclerView recyclerview;

    //private ShortSlideListener shortSlideListener;


    public SlideBottomLayout(@NonNull Context context) {
        super(context);
    }

    public SlideBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public SlideBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    /**
     *初始化属性配置
     * @param context the {@link Context}
     * @param attrs   the configs in layout attrs.
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SlideBottomLayout);
        visibilityHeight = ta.getDimension(R.styleable.SlideBottomLayout_handler_height, 0);
        ta.recycle();

        initConfig(context);
    }

    /**
     *  实现视图平滑滚动利器
     * @param context
     */
    private void initConfig(Context context) {
        if (mScroller == null) {
            mScroller = new Scroller(context);
        }
    }

    /**
     * 使用前判断/单一子视图
     * 该方法在OnMeasure(int,int)调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 0 || getChildAt(0) == null) {
            throw new RuntimeException("SlideBottom里面没有子布局");
        }
        if (getChildCount() > 1) {
            throw new RuntimeException("SlideBottom里只可以放置一个子布局");
        }
        childView = getChildAt(0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        movedMaxDis = (int) (childView.getMeasuredHeight() - visibilityHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        childView.layout(0, movedMaxDis, childView.getMeasuredWidth(), childView.getMeasuredHeight() + movedMaxDis);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float interceptY = ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                RecordY(interceptY);
                break;
            case MotionEvent.ACTION_MOVE:
                if(interceptJudge(interceptY)){
                    return onTouchEvent(ev);
                }
                return false;
            case MotionEvent.ACTION_UP:
                if(interceptJudge(interceptY)){
                    return onTouchEvent(ev);
                }
                return false;
        }
        return super.onInterceptTouchEvent(ev);
    }


    /**
     * 记录下拦截器传来的Y值
     * @param interceptY
     */
    private void RecordY(float interceptY) {
        CurrentY = (int)interceptY;
    }

    /**
     * 拦截判断
     * @param interceptY
     * @return
     */
    private boolean interceptJudge(float interceptY) {
        float judgeY = CurrentY - interceptY;
        if(judgeY > 0){
            //向上滑动
            if(!arriveTop()){
                return true;
            }
        }
        if(judgeY < 0){
            //向下滑动
            if(arriveTop() && isTop(recyclerview)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float dy = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (touchActionDown(dy)) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (touchActionMove(dy)) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (touchActionUp(dy)) {
                    return true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * scroll的更新方法
     * computeScrollOffset 返回true表示动画未完成
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller == null)
            mScroller = new Scroller(getContext());
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }


    public boolean touchActionUp(float eventY) {
        //移动的位置是否大于阈值
        if (movedDis > movedMaxDis * hideWeight) {
            switchVisible();
        } else {
            //提供一个接口用于处理没有达到阈值的手势
           /* if (shortSlideListener != null) {
                shortSlideListener.onShortSlide(eventY);
            } else {
                hide();
            }*/
        }
        return true;
    }

    public boolean touchActionMove(float eventY) {
        moveY =  eventY;
        //dy是移动距离的和 如果它的值>0表示向上滚动  <0表示向下滚动
        final float dy = downY - moveY;
        if (dy > 0) {               //向上
            movedDis += dy;
            if (movedDis > movedMaxDis) {
                movedDis = movedMaxDis;
            }

            if (movedDis < movedMaxDis) {
                scrollBy(0, (int) dy);
                downY = moveY;
                return true;
            }
        } else {                //向下
            movedDis += dy;
            if (movedDis < 0) {
                movedDis = 0;
            }
            if (movedDis > 0) {
                scrollBy(0, (int)dy);
            }
            downY = moveY;
            return true;
        }
        return false;
    }

    public boolean touchActionDown(float eventY) {
        //记录手指按下的位置
        downY = (int) eventY;

        if (!arriveTop && downY < movedMaxDis) {
            return false;
        } else{
            return true;
        }
    }

    /**
     * slidBottom的显示方法
     */
    public void show() {
        scroll2TopImmediate();
    }

    /**
     * slidBottom的隐藏方法
     */
    public void hide() {
        scroll2BottomImmediate();
    }

    /**
     * arriveTop返回值
     * 判断child是否到达顶部
     */
    public boolean switchVisible() {
        if (arriveTop()) {
            hide();
        } else {
            show();
        }
        return arriveTop();
    }

    public boolean arriveTop() {
        return this.arriveTop;
    }

    public void scroll2TopImmediate() {
        mScroller.startScroll(0, getScrollY(), 0, (movedMaxDis - getScrollY()));
        invalidate();
        movedDis = movedMaxDis;
        arriveTop = true;
        isShow= true;
    }

    public void scroll2BottomImmediate() {
        mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
        postInvalidate();
        movedDis = 0;
        arriveTop = false;
        isShow = false;
    }



    /**
     * 绑定Recyclerview如果你的子布局中含有Recyclerview的话
     * 该方法用于判断是否到达Recyclerview的顶部
     * @param recyclerView
     * @return
     */
    public static boolean isTop(RecyclerView recyclerView){
        if(recyclerView == null){
            return false;
        }
        return !recyclerView.canScrollVertically(-1);
    }

    /**
     * 绑定RecyclerView（可选）
     * 如果子布局有RecyclerView必须绑定否则Recyclerview的滑动不会被拦截
     * @param recyclerView
     */
    public void bindRecyclerView(RecyclerView recyclerView){
        this.recyclerview = recyclerView;
    }

 /*   public void setShortSlideListener(ShortSlideListener listener) {
        this.shortSlideListener = listener;
    }*/

    /**
     * 隐藏比重阈值
     * @param hideWeight
     */
    public void setHideWeight(float hideWeight) {
        if (hideWeight <= 0 || hideWeight > 1) {
            throw new IllegalArgumentException("隐藏的阈值应该在(0f,1f]之间");
        }
        this.hideWeight = hideWeight;
    }

    /**
     * 设置显示高度
     * @param visibilityHeight
     */
    public void setVisibilityHeight(float visibilityHeight) {
        this.visibilityHeight = visibilityHeight;
    }


}