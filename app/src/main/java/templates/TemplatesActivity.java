package templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.CurdApi;
import com.improve10x.crud.CurdService;
import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {
    private ArrayList<Template> templates;
    private RecyclerView templatesRv;
    private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        Log.i("TemplateActivity", "onCreate called");
        getSupportActionBar().setTitle("Template");
        handleAdd();
        setupData();
        setupTemplatesRv();
    }

    private void deleteTemplate(Template template) {
        CurdApi curdApi = new CurdApi();
        CurdService curdService = curdApi.createCurdService();
        Call<Void> call = curdService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully done", Toast.LENGTH_SHORT).show();
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addTemplateIntent = new Intent(this,AddTemplateActivity.class);
            startActivity(addTemplateIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TemplateActivity", "onResume called");
        fetchTemplates();
    }

    private void fetchTemplates(){
        CurdApi curdApi = new CurdApi();
        CurdService curdService = curdApi.createCurdService();
        Call<List<Template>> call = curdService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setTemplates(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to get loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupTemplatesRv() {
        templatesRv = findViewById(R.id.template_rcv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setTemplates(templates);
        templatesRv.setAdapter(templatesAdapter);
        templatesAdapter.setOnItemClickListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                Toast.makeText(TemplatesActivity.this, "on Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Template template) {
                Toast.makeText(TemplatesActivity.this, "on Delete", Toast.LENGTH_SHORT).show();
                deleteTemplate(template);
            }

            @Override
            public void onItemEdit(Template template) {
                Toast.makeText(TemplatesActivity.this, "on Edit", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupData() {
        templates  = new ArrayList<>();
    }

}