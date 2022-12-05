package templates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        getSupportActionBar().setTitle("Add Template");
        handleBtn();

    }

    private void handleBtn() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText mgsEditTxt = findViewById(R.id.message_edit_txt);
            String message = mgsEditTxt.getText().toString();
            createTemplate(message);
        });
    }

    private void createTemplate(String message) {
        Template template = new Template();
        template.message = message;

        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<Template> call = templateService.create(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddTemplateActivity.this, "Successfully loaded", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                Toast.makeText(AddTemplateActivity.this, "Failed to get loaded", Toast.LENGTH_SHORT).show();

            }
        });

    }
}