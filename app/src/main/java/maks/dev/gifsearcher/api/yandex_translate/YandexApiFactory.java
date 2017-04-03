package maks.dev.gifsearcher.api.yandex_translate;

import maks.dev.gifsearcher.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by berezyckiy on 4/4/17.
 */

public class YandexApiFactory {

    private static YandexAPI service;

    public static YandexAPI getInstance() {
        if (service == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT_YANDEX)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = client.create(YandexAPI.class);
            return service;
        } else {
            return service;
        }
    }

}
