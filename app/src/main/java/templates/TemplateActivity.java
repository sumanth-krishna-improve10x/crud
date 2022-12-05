package templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplateActivity extends AppCompatActivity {
    public ArrayList<Template> templates;
    public RecyclerView templateRcv;
    public TemplateAdapter templateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        getSupportActionBar().setTitle("Template");
        handleAddBtn();
        setTemplate();
        setRecyclerview();
    }

    public void deleteMessage(Template template) {
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<Void> call = templateService.deleteMessage(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplateActivity.this, "Successfully done", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplateActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void handleAddBtn() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent addTemplateIntent = new Intent(this,AddTemplateActivity.class);
            startActivity(addTemplateIntent);
        });
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        fetchData();
    }

    public void fetchData(){
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<List<Template>> call = templateService.fetchData();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templateAdapter.setTemplates(templates);

            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplateActivity.this, "Failed to get loaded", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void setRecyclerview() {
        templateRcv = findViewById(R.id.template_rcv);
        templateRcv.setLayoutManager(new LinearLayoutManager(this));
        templateAdapter = new TemplateAdapter();
        templateAdapter.setTemplates(templates);
        templateRcv.setAdapter(templateAdapter);
        templateAdapter.setOnItemClickListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                Toast.makeText(TemplateActivity.this, "on Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Template template) {
                Toast.makeText(TemplateActivity.this, "on Delete", Toast.LENGTH_SHORT).show();
                deleteMessage(template);

            }

            @Override
            public void onItemEdit(Template template) {
                Toast.makeText(TemplateActivity.this, "on Edit", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void setTemplate() {
        templates  = new ArrayList<>();
    }

}