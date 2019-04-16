package com.i502tech.gitclub.code.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

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
public class ArticleTypeAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {


    public ArticleTypeAdapter(List<Article> mData, Context context) {
        super(R.layout.item_article_type, mData);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article article) {
        helper.setText(R.id.tv_title, article.getTitle());
        helper.setText(R.id.tv_name, article.getUser().getNick_name());
        ImageView ivAvater = helper.getView(R.id.iv_avater);
        ImageView ivCover = helper.getView(R.id.iv_cover);
        GlideUtil.displayCircle(mContext, ivAvater, article.getUser().getAvatar());
        String url = article.getImg_url();
        if (url.endsWith("gif")){
            Glide.with(mContext).asGif().load(url).into(ivCover);
        }else {
            Glide.with(mContext).load(url).into(ivCover);
        }
        helper.setText(R.id.tv_des, article.getDes());
        helper.setText(R.id.tv_date, article.getDate());

    }
}
