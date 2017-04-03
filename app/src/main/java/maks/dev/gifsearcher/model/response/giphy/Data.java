
package maks.dev.gifsearcher.model.response.giphy;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("data")
    private List<Datum> data = null;
    @SerializedName("pagination")
    private Pagination pagination;

    public List<Datum> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

}
