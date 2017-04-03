package maks.dev.gifsearcher.model.content;

/**
 * Created by berezyckiy on 4/2/17.
 */

public class Gif {
    private String url;
    private String bigSizeGifUrl;

    public Gif(String url, String bigSizeGifUrl) {
        this.url = url;
        this.bigSizeGifUrl = bigSizeGifUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getBigSizeGifUrl() {
        return bigSizeGifUrl;
    }

}
