
package maks.dev.gifsearcher.model.response.giphy;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("rating")
    private String rating;
    @SerializedName("images")
    private Images images;

    public String getRating() {
        return rating;
    }

    public Images getImages() {
        return images;
    }

}
