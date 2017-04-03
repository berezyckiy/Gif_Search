package maks.dev.gifsearcher.screen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import maks.dev.gifsearcher.R;

/**
 * Created by berezyckiy on 4/2/17.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView imgGif;
    FrameLayout containerImgGif;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        imgGif = (ImageView) itemView.findViewById(R.id.imgGif);
        containerImgGif = (FrameLayout) itemView.findViewById(R.id.containerImgGif);
    }
}
