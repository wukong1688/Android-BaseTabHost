package com.jack.appnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jack.appnews.R;
import com.jack.appnews.bean.ItemNewsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ItemNewsBean> items;
    private ClickListener clickListener;

    public NewsListRecycleAdapter(Context context, List<ItemNewsBean> items) {
        this.context = context;
        this.items = items;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_news_list_style_1, parent, false);
                viewHolder = new ViewHolderOne(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final XRecyclerView.ViewHolder holder, int position) {
        ItemNewsBean newsBean = items.get(position);
        switch (getItemViewType(position)) {
            case 1: //1张图 情况
                ViewHolderOne ViewHolderOne = (ViewHolderOne) holder;
                ViewHolderOne.tvTitle.setText(newsBean.getNews_title());
                ViewHolderOne.tvCateName.setText(newsBean.getSource_name());

                if (newsBean.getImg_list() != null && newsBean.getImg_list().size() > 0) {
                    Glide.with(context)
                            .load(newsBean.getImg_list().get(0))
                            .error(R.mipmap.ic_article_delete)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ViewHolderOne.ivImage);
                }

                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                clickListener.onItemClick(holder.itemView, pos);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getContent_type();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //1张图
    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_cate_name)
        TextView tvCateName;
        @BindView(R.id.iv_main_img)
        ImageView ivImage;

        public ViewHolderOne(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}