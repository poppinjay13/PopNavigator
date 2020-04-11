package com.poppinjay13.popnavigator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

public class PopNavigator extends LinearLayout {

    Context context;

    public static int HOME = R.raw.home;
    public static int NOTIFICATIONS = R.raw.home;
    public static int SETTINGS = R.raw.home;
    public static int PROFILE = R.raw.mad;
    public static int SPORT = R.raw.sport;
    public static int PARTY = R.raw.party;
    public static int MOVIE = R.raw.movie;
    public static int FOOD = R.raw.food;
    public static int EXERCISE = R.raw.exercise;

    /*
    Constructors
     */
    public PopNavigator(Context context) {
        super(context);
        this.context = context;
    }

    public PopNavigator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PopNavigator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    /*
    Adds a new menu item to the navigation bar
     */
    public void addMenuItem(int animation) throws Resources.NotFoundException {
        if (context.getResources().getResourceName(animation) != null) {
            LottieAnimationView lottieAnimationView;
            lottieAnimationView = new LottieAnimationView(context);
            lottieAnimationView.setAnimation(animation);
            lottieAnimationView.loop(false);
            lottieAnimationView.pauseAnimation();
            lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play(v);
                }
            });
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            lottieAnimationView.setLayoutParams(param);
            this.addView(lottieAnimationView);
        } else {
            throw new Resources.NotFoundException("Unable to locate the resource specified");
        }
    }

    /*
    Initiates animation
    */
    private void play(View v) {
        final int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = this.getChildAt(i);
            if (view instanceof LottieAnimationView) {
                LottieAnimationView animationView = (LottieAnimationView) view;
                if (v == view) {
                    if (animationView.getTag() == null || !animationView.getTag().equals("selected")) {
                        animationView.playAnimation();
                        animationView.setTag("selected");
                    }
                } else {
                    if (animationView.getTag() != null && animationView.getTag().equals("selected")) {
                        if (animationView.isAnimating()) {
                            animationView.pauseAnimation();
                        }
                        reverse(animationView);
                        animationView.setTag("");
                    }
                }
            }
        }
    }

    /*
    Reverses animation
     */
    private void reverse(final LottieAnimationView animView) {
        float progress = animView.getProgress();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-progress, 0).setDuration((long) (animView.getDuration() * progress));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animView.setProgress(Math.abs((float) animation.getAnimatedValue()));
            }
        });
        valueAnimator.start();
    }

    /*
    Get currently selected navigation bar item
     */
    public int getSelectedItemId() {
        final int childCount = this.getChildCount();
        int item = 0;
        for (int i = 0; i < childCount; i++) {
            View view = this.getChildAt(i);
            if (view instanceof LottieAnimationView) {
                LottieAnimationView animationView = (LottieAnimationView) view;
                if (animationView.getTag() != null && animationView.getTag().equals("selected")) {
                    return animationView.getId();
                }
            }
        }
        return 0;
    }

    /*
    Set navigation bar item as selected
     */
    public void setSelectedItemId(int itemId) {
        final int childCount = this.getChildCount();
        int item = 0;
        for (int i = 0; i < childCount; i++) {
            View view = this.getChildAt(i);
            if (view instanceof LottieAnimationView && item == itemId) {
                view.performClick();
            }
        }
    }

}
