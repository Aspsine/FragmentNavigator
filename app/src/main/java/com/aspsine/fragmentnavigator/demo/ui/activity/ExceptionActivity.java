package com.aspsine.fragmentnavigator.demo.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aspsine.fragmentnavigator.demo.R;

public class ExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);

        final Button button = (Button) findViewById(R.id.btnException);

        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    throw new RuntimeException("This will crash the app");
                }
            });
        }

    }
}
