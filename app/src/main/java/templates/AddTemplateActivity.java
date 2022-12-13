package templates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseActivity {
    private CurdService curdService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        getSupportActionBar().setTitle("Add Template");
        setupApiService();
        handleAdd();
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText mgsEditTxt = findViewById(R.id.message_edit_txt);
            String message = mgsEditTxt.getText().toString();
            createTemplate(message);
        });
    }

    private void createTemplate(String message) {
        Template template = new Template();
        template.messageText = message;

        Call<Template> call = curdService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("Successfully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
               showToast("Failed to get loaded");
            }
        });
    }
}