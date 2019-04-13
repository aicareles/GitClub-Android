package com.i502tech.gitclub.code.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.i502tech.gitclub.R;
import com.i502tech.gitclub.widget.BaseFrameLayout;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;
import com.wangjie.rapidfloatingactionbutton.util.RFABShape;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description $desc$
 * created by jerry on 2019/4/12.
 */
public class FloatingActionLayout1 extends BaseFrameLayout implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    @BindView(R.id.fb)
    RapidFloatingActionButton fb;
    @BindView(R.id.floatinglayout)
    RapidFloatingActionLayout floatinglayout;
    private RapidFloatingActionHelper rfabHelper;
    private Unbinder unbinder;
    private Context mContext;

    public FloatingActionLayout1(@NonNull Context context) {
        super(context);
    }

    public FloatingActionLayout1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionLayout1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void bindLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.layout_floatbutton, this, true);
        this.mContext  = context;
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(mContext);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Github: wangjiegulu")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(0xffd84315)
                .setIconPressedColor(0xffbf360c)
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                        .setLabel("tiantian.china.2@gmail.com")
                        .setResId(R.mipmap.ic_launcher)
//                        .setDrawable(getResources().getDrawable(R.mipmap.ico_test_c))
                        .setIconNormalColor(0xff4e342e)
                        .setIconPressedColor(0xff3e2723)
                        .setLabelColor(Color.WHITE)
                        .setLabelSizeSp(14)
                        .setLabelBackgroundDrawable(RFABShape.generateCornerShapeDrawable(0xaa000000, RFABTextUtil.dip2px(mContext, 4)))
                        .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("WangJie")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(0xff056f00)
                .setIconPressedColor(0xff0d5302)
                .setLabelColor(0xff056f00)
                .setWrapper(2)
        );
        rfaContent
                .setItems(items)
                .setIconShadowRadius(RFABTextUtil.dip2px(mContext, 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(RFABTextUtil.dip2px(mContext, 5))
        ;
        rfabHelper = new RapidFloatingActionHelper(
                mContext,
                floatinglayout,
                fb,
                rfaContent
        ).build();
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
    }
}
