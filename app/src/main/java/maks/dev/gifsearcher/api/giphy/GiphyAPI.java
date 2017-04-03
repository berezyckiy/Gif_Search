package maks.dev.gifsearcher.api.giphy;

import maks.dev.gifsearcher.model.response.giphy.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by berezyckiy on 4/4/17.
 */

public interface GiphyAPI {

    @GET("trending?api_key=dc6zaTOxFJmzC&limit=26")
    Call<Data> getTrending();

    @GET("search?api_key=dc6zaTOxFJmzC")
    Call<Data> getFoundResult(@Query("q") String query, @Query("lang") String lang);

}
