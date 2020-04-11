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

    //available animations
    public static int ECOM_BAG = R.raw.bag;
    public static int ECOM_BANK = R.raw.bank;
    public static int ECOM_CARD = R.raw.card;
    public static int ECOM_CALENDAR = R.raw.calendar;
    public static int ECOM_CHART = R.raw.chart;
    public static int ECOM_DIAMOND = R.raw.diamond;
    public static int ECOM_EXCHANGE = R.raw.exchange;
    public static int ECOM_GAVEL = R.raw.gavel;
    public static int ECOM_MOBILE = R.raw.mobile;
    public static int ECOM_SETTING = R.raw.setting;
    public static int ECOM_TAGS = R.raw.tags;
    public static int ECOM_WALLET = R.raw.wallet;
    //
    public static int BASE_EXPLORE = R.raw.explore;
    public static int BASE_USER = R.raw.user;
    public static int BASE_NOTIFY = R.raw.notify;
    //
    public static int BLACK_UPDATE = R.raw.update;
    public static int BLACK_STAR = R.raw.star;
    public static int BLACK_EXPAND = R.raw.expand;
    public static int BLACK_CLIP = R.raw.clip;
    public static int BLACK_ATTACHMENT = R.raw.attachment;
    public static int BLACK_OPTIONS = R.raw.options;
    public static int BLACK_SLIDERS = R.raw.sliders;
    public static int BLACK_BOOKMARK = R.raw.bookmark;
    public static int BLACK_SCREENS = R.raw.screens;
    public static int BLACK_EDIT = R.raw.edit;
    public static int BLACK_FOLDER = R.raw.folder;
    public static int BLACK_LIKE = R.raw.like;

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
