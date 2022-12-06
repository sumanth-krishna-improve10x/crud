package templates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TemplatesService {

    @GET("templates")
    Call<List<Template>> fetchData();

    @POST("templates")
    Call<Template> create(@Body Template template);

    @DELETE ("templates/{id}")
    Call<Void> deleteMessage (@Path("id")String id);

}
