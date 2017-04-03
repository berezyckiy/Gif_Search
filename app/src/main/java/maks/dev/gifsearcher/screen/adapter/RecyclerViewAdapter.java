package maks.dev.gifsearcher.screen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.List;

import maks.dev.gifsearcher.R;
import maks.dev.gifsearcher.model.content.Gif;

/**
 * Created by berezyckiy on 4/2/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Gif> mGifsList;
    private Context mContext;
    private OnGifClickListener mListener;

    public RecyclerViewAdapter(@NonNull Context context, List<Gif> gifsList) {
        this.mGifsList = gifsList;
        this.mContext = context;
        mListener = (OnGifClickListener) context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Gif gif = mGifsList.get(position);
        final String gifBigSizeImageUrl = gif.getBigSizeGifUrl();
        Glide.with(mContext)
                .load(gif.getUrl())
                .thumbnail(0.1f)
                .placeholder(R.drawable.loading_small)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(holder.imgGif) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }
                });
        holder.containerImgGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showBigSizeGif(gifBigSizeImageUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGifsList.size();
    }
}
