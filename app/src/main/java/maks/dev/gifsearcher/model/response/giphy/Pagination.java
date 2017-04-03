
package maks.dev.gifsearcher.model.response.giphy;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("count")
    private Integer count;

    public Integer getCount() {
        return count;
    }

}
