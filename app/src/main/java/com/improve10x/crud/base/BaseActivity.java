package com.improve10x.crud.base;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.improve10x.crud.messages.MessagesActivity;

public class BaseActivity extends AppCompatActivity {

    protected void  showToast(String message){
        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
    }

   protected void log(String message){
        Log.i(this.getClass().getSimpleName(),message);
    }
}
