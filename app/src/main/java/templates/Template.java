package templates;

import com.google.gson.annotations.SerializedName;

public class Template {
    @SerializedName("_id")
    public String id;
    @SerializedName("messageText")
    public String messageText;
}
