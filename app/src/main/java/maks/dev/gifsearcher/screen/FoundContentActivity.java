package maks.dev.gifsearcher.screen;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import maks.dev.gifsearcher.screen.adapter.OnGifClickListener;
import maks.dev.gifsearcher.R;
import maks.dev.gifsearcher.api.giphy.GiphyApiFactory;
import maks.dev.gifsearcher.api.yandex_translate.YandexApiFactory;
import maks.dev.gifsearcher.model.response.giphy.Data;
import maks.dev.gifsearcher.model.content.Gif;
import maks.dev.gifsearcher.model.response.yandex_translate.Language;
import maks.dev.gifsearcher.screen.adapter.RecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by berezyckiy on 4/2/17.
 */

public class FoundContentActivity
        extends AppCompatActivity
        implements OnGifClickListener {

    private RecyclerView recyclerViewFoundContent;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Gif> gifsList;
    private String query;
    private String queryLanguage;
    private Data queryResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_content);
        initItems();
        detectLanguageWithQueryGifs(query);
        setToolbarTitle(query);
        buildRecyclerView();
        addButtonHome();
    }

    private void initItems() {
        gifsList = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getIntent() != null) {
            query = getIntent().getStringExtra("query");
        } else {
            query = "Error";
        }
        recyclerViewFoundContent = (RecyclerView) findViewById(R.id.recyclerViewFoundContent);
        recyclerViewAdapter = new RecyclerViewAdapter(this, gifsList);
    }

    public void detectLanguageWithQueryGifs(String sourceText) {
        YandexApiFactory.getInstance().getQueryLanguage(sourceText).enqueue(new Callback<Language>() {
            @Override
            public void onResponse(Call<Language> call, Response<Language> response) {
                queryLanguage = response.body().getLang();
                queryGifs();
            }

            @Override
            public void onFailure(Call<Language> call, Throwable t) {
                queryLanguage = "en";
            }
        });
    }

    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void addButtonHome() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void buildRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewFoundContent.setLayoutManager(layoutManager);
        recyclerViewFoundContent.setAdapter(recyclerViewAdapter);
    }

    private void queryGifs() {
        GiphyApiFactory.getInstance().getFoundResult(query, queryLanguage).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                queryResponse = response.body();
                showGifsWithRating("all");
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("myLogs", t.getMessage());
            }
        });
    }

    private void showGifsWithRating(String rating) {
        gifsList.clear();
        Integer countOfGifs = Integer.parseInt(queryResponse.getPagination().getCount().toString());
        for (int i = 0; i < countOfGifs; i++) {
            if (rating.equals("all")) {
                String url = queryResponse.getData().get(i).getImages().getFixedHeightSmall().getUrl();
                String bigSizeGifUrl = queryResponse.getData().get(i).getImages().getFixedHeight().getUrl();
                gifsList.add(new Gif(url, bigSizeGifUrl));
                recyclerViewAdapter.notifyDataSetChanged();
            } else if (queryResponse.getData().get(i).getRating().equals(rating)) {
                String url = queryResponse.getData().get(i).getImages().getFixedHeightSmall().getUrl();
                String bigSizeGifUrl = queryResponse.getData().get(i).getImages().getFixedHeight().getUrl();
                gifsList.add(new Gif(url, bigSizeGifUrl));
                recyclerViewAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chosing_rating, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionRatingY:
                showGifsWithRating("y");
                break;
            case R.id.actionRatingG:
                showGifsWithRating("g");
                break;
            case R.id.actionRatingPG:
                showGifsWithRating("pg");
                break;
            case R.id.actionRatingAll:
                showGifsWithRating("all");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showBigSizeGif(String originalUrl) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        GifDialog newFragment = GifDialog.newInstance(originalUrl);
        newFragment.show(ft, "dialog");
    }
}
