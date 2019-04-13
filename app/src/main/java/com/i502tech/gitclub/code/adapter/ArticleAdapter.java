package com.i502tech.gitclub.code.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.i502tech.gitclub.R;
import com.i502tech.gitclub.code.bean.Article;
import com.i502tech.gitclub.utils.GlideUtil;

import java.util.List;

/**
 * description $desc$
 * created by jerry on 2019/3/18.
 */
public class ArticleAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {

    public ArticleAdapter(List<Article> mData, Context context) {
        super(R.layout.item_article, mData);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_name_des, String.format("%s%s", item.getUser().getNick_name(), item.getDes()));
        ImageView ivAvater = helper.getView(R.id.iv_avater);
        ImageView ivCover = helper.getView(R.id.iv_cover);
        GlideUtil.displayCircle(mContext, ivAvater, item.getUser().getAvatar());
        String url = item.getImg_url();
        if (url.endsWith("gif")){
            Glide.with(mContext).asGif().load(url).into(ivCover);
        }else {
//            GlideUtil.display(mContext, ivCover, url);
            Glide.with(mContext).load(url).into(ivCover);
        }
        helper.setText(R.id.tv_view, String.valueOf(item.getViews()));
        helper.setText(R.id.tv_star, String.valueOf(item.getStars()));
    }
}
