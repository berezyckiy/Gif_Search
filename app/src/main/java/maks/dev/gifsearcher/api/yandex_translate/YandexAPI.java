package maks.dev.gifsearcher.api.yandex_translate;

import maks.dev.gifsearcher.model.response.yandex_translate.Language;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by berezyckiy on 4/4/17.
 */

public interface YandexAPI {

    @GET("api/v1.5/tr.json/detect?key=trnsl.1.1.20170403T144646Z.b1676b15671f50ca.c07146039a3388f7852a5a550db6d65599a050e3&hint=en")
    Call<Language> getQueryLanguage(@Query("text") String sourceText);

}
