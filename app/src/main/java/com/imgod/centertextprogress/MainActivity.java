package com.imgod.centertextprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imgod.centertextprogress.view.CenterTextProgress;

public class MainActivity extends AppCompatActivity {

    private CenterTextProgress ctp_main;
    private Button btn_add;
    private Button btn_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvent();
    }

    private int progress;//0-100
    private String title;

    private void initEvent() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress += 10;
                calcProgress();
                ctp_main.setTitleText(title);
                ctp_main.setProgress(progress);
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress -= 10;
                calcProgress();
                ctp_main.setTitleText(title);
                ctp_main.setProgress(progress);
            }
        });
    }

    private void initViews() {
        ctp_main = findViewById(R.id.ctp_main);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
    }

    private void calcProgress() {
        if (progress <= 0) {
            progress = 0;
        }

        if (progress >= 100) {
            progress = 100;
        }

        if (progress <= 33) {
            title = "1/3";
        }
        if (progress > 33 && progress <= 66) {
            title = "2/3";
        }

        if (progress > 66) {
            title = "3/3";
        }

    }

}
