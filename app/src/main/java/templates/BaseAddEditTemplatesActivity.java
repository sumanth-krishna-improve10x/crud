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

public class BaseAddEditTemplatesActivity extends BaseActivity {
    protected CurdService curdService;
    protected EditText messagesEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        setupViews();
        setupApiService();
    }

    private void setupViews() {
        messagesEditTxt = findViewById(R.id.message_edit_txt);
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }


    protected Template createTemplate(String message) {
        Template template = new Template();
        template.messageText = message;
        return template;
    }

}



