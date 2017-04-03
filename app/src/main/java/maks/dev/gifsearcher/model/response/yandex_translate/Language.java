package maks.dev.gifsearcher.model.response.yandex_translate;

/**
 * Created by berezyckiy on 4/3/17.
 */

import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("lang")
    private String lang;

    public String getLang() {
        return lang;
    }

}
