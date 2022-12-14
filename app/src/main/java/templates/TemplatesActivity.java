package templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.Constants;
import com.improve10x.crud.api.CurdApi;
import com.improve10x.crud.api.CurdService;
import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {
    private CurdService curdService;
    private ArrayList<Template> templates;
    private RecyclerView templatesRv;
    private TemplatesAdapter templatesAdapter;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        log("onCreate");
        getSupportActionBar().setTitle("Template");
        setupApiService();
        handleAdd();
        setupData();
        setupTemplatesRv();
        setupAdapter();
    }

    private void setupAdapter() {
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setTemplates(templates);
        templatesRv.setAdapter(templatesAdapter);
        templatesAdapter.setOnItemClickListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                Intent intent = new Intent(TemplatesActivity.this, AddTemplateActivity.class);
                intent.putExtra(Constants.KEY_TEMPLATE, template);
                startActivity(intent);
                //showToast("onItemClicked");
            }

            @Override
            public void onItemDelete(Template template) {
                showToast("onItemDelete");
                deleteTemplate(template);
            }

            @Override
            public void onItemEdit(Template template) {
                showToast("onItemEdit");
            }
        });
    }

    private void setupApiService() {
        CurdApi curdApi = new CurdApi();
        curdService = curdApi.createCurdService();
    }

    private void deleteTemplate(Template template) {
        Call<Void> call = curdService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully loaded");
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to get loaded");
            }
        });
    }

    private void handleAdd() {
        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addTemplateIntent = new Intent(this, AddTemplateActivity.class);
            startActivity(addTemplateIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchTemplates();
    }

    private void fetchTemplates() {
        Call<List<Template>> call = curdService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setTemplates(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                showToast("Failed to get loaded");
            }
        });
    }

    private void setupTemplatesRv() {
        templatesRv = findViewById(R.id.template_rcv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupData() {
        templates = new ArrayList<>();
    }
}