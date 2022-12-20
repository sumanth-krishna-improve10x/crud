package templates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.messages.Message;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTemplateActivity extends BaseAddEditTemplatesActivity {
    private Template template;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Message");
            template = (Template) intent.getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
            handleEdit();
            showEditBtn();
        }
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String messageText = messagesEditTxt.getText().toString();
            Template updatedTemplate = createTemplate(messageText);
            updateTemplate(template.id, updatedTemplate);
        });
    }

    private void showData () {
            messagesEditTxt.setText(template.messageText);
        }

    private void showEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }

    private void updateTemplate (String id, Template updatedTemplate){
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

        private void setupViews () {
            editBtn = findViewById(R.id.edit_btn);
        }
    }


