package templates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TemplatesService {

    @GET("sumanthTemplates")
    Call<List<Template>> fetchTemplates();

    @POST("sumanthTemplates")
    Call<Template> createTemplate(@Body Template template);

    @DELETE ("sumanthTemplates/{id}")
    Call<Void> deleteTemplate(@Path("id")String id);

}
