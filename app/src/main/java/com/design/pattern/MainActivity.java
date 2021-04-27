package com.design.pattern;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.design.srp.SingleResponsibilityPrincipleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSingleResponsibility(View v) {
        startActivity(new Intent(MainActivity.this, SingleResponsibilityPrincipleActivity.class));
    }
}
