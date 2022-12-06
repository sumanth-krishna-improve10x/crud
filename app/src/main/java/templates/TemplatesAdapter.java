package templates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {

    OnItemActionListener onItemActionListener;

    public void setOnItemClickListener(OnItemActionListener listener){
        onItemActionListener = listener;
    }


    public List<Template> templatesList;

    public void setTemplates (List<Template> templateArrayList){
        templatesList = templateArrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item,parent,false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(view);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templatesList.get(position);
        holder.templateMgsTxt.setText(template.messageText);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(template);
        });

        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(template);
        });
    }

    @Override
    public int getItemCount() {
        return templatesList.size();
    }
}
