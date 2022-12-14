package templates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseActivity {
    private CurdService curdService;
    private Template template;
    private Button addBtn;
    private EditText messagesEditTxt;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        Intent intent = getIntent();
        setupViews();
        setupApiService();
        if (intent.hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) intent.getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
            handleEdit();
            showEditBtn();

        } else {
            getSupportActionBar().setTitle("Add Template");
            handleAdd();
            showAddBtn();
        }
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String messageText = messagesEditTxt.getText().toString();
            Template updatedTemplate = createTemplate(messageText);
            updateTemplate(template.id , updatedTemplate);
        });
    }

    private void updateTemplate(String id, Template updatedTemplate) {
        Call<Void> call = curdService.updatedTemplate(id, updatedTemplate);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Success");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get loaded");

            }
        });
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);
        messagesEditTxt = findViewById(R.id.message_edit_txt);
    }

    private void showData() {
        messagesEditTxt.setText(template.messageText);
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String messageText = messagesEditTxt.getText().toString();
            Template template = createTemplate(messageText);
            saveTemplate(template);
        });
    }

    private Template createTemplate(String message) {
        Template template = new Template();
        template.messageText = message;
        return template;
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



