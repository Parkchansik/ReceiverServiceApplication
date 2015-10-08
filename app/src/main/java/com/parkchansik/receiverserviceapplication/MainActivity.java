package com.parkchansik.receiverserviceapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputView;
    TextView resultView;
    private static final int REQUEST_CODE_OTHER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputView = (EditText)findViewById(R.id.edit_input);
        resultView = (TextView)findViewById(R.id.text_result);
        Button btn = (Button)findViewById(R.id.btn_other);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                String text = inputView.getText().toString();
                intent.putExtra(OtherActivity.EXTRA_MESSAGE, text);
                intent.putExtra(OtherActivity.EXTRA_NAME, "ysi");
                intent.putExtra(OtherActivity.EXTRA_AGE, 41);
                Person p = new Person();
                p.name = "ysi";
                p.age = 41;
                p.message = text;
                intent.putExtra(OtherActivity.EXTRA_PERSON, p);
                startActivityForResult(intent, REQUEST_CODE_OTHER);
            }
        });

        btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("count", 100);
                startService(intent);
            }
        });

        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OTHER && resultCode == Activity.RESULT_OK) {
            String message = data.getStringExtra(OtherActivity.RESULT_MESSAGE);
            resultView.setText(message);
        }
    }
}
