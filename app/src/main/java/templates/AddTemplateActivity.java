package templates;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseAddEditTemplatesActivity {
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
        setupViews();
        handleAdd();
        showAddBtn();
    }
    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
    }


    private void showAddBtn() {

        addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String messageText = messagesEditTxt.getText().toString();
            Template template = createTemplate(messageText);
            saveTemplate(template);
        });
    }

    private void saveTemplate(Template template){
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


