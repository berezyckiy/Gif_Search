package maks.dev.gifsearcher.api.giphy;

import maks.dev.gifsearcher.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by berezyckiy on 4/4/17.
 */

public class GiphyApiFactory {

    private static GiphyAPI service;

    public static GiphyAPI getInstance() {
        if (service == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT_GIPHY)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = client.create(GiphyAPI.class);
            return service;
        } else {
            return service;
        }
    }

}
