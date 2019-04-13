package com.i502tech.gitclub.code.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.github.clans.fab.FloatingActionMenu;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.utils.ToastUtil;
import com.i502tech.gitclub.widget.BaseFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * description $desc$
 * created by jerry on 2019/4/12.
 */
public class FloatingActionLayout extends BaseFrameLayout {

    @BindView(R.id.fb_menu)
    FloatingActionMenu fbMenu;
    private Unbinder unbinder;

    public FloatingActionLayout(@NonNull Context context) {
        super(context);
    }

    public FloatingActionLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.layout_fb, this, true);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fbMenu.setClosedOnTouchOutside(true);
        fbMenu.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fab_in));
        fbMenu.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fab_out));
    }

    @OnClick({R.id.fb_person, R.id.fb_submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fb_person:
                ToastUtil.show("点击了我的");
                break;
            case R.id.fb_submission:
                ToastUtil.show("点击了投稿");
                break;
        }
    }

    public void show(){
        fbMenu.showMenu(true);
    }

    public void hide(){
        fbMenu.hideMenu(true);
    }
}
