package maks.dev.gifsearcher.screen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import maks.dev.gifsearcher.R;

/**
 * Created by berezyckiy on 4/3/17.
 */

public class GifDialog extends DialogFragment {
    private String url;

    static GifDialog newInstance(String url) {
        GifDialog gifDialog = new GifDialog();
        Bundle args = new Bundle();
        args.putString("url", url);
        gifDialog.setArguments(args);
        return gifDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        builder.setView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewGif);
        Glide.with(getActivity())
                .load(url)
                .thumbnail(0.1f)
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }
                });
        return builder.create();
    }
}
