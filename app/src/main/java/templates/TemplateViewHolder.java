package templates;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class TemplateViewHolder extends RecyclerView.ViewHolder {
    TextView templateMgsTxt;
    ImageView deleteBtn;

    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        templateMgsTxt = itemView.findViewById(R.id.template_mgs_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
