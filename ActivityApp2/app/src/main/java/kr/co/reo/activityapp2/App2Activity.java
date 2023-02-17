package kr.co.reo.activityapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.co.reo.activityapp2.databinding.ActivityApp2Binding;

public class App2Activity extends AppCompatActivity {
    ActivityApp2Binding activityApp2Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityApp2Binding = ActivityApp2Binding.inflate(getLayoutInflater());
        setContentView(activityApp2Binding.getRoot());

        Button1ClickListener listener1 = new Button1ClickListener();
        activityApp2Binding.button.setOnClickListener(listener1);

        Intent testIntent = getIntent();
        int data1 = testIntent.getIntExtra("data1",0);
        String data2 = testIntent.getStringExtra("data2");
        activityApp2Binding.textView.setText("data1 : "+ data1 +"\n");
        activityApp2Binding.textView.append("data2 : "+ data2);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent resultIntent = new Intent();
            resultIntent.putExtra("value1",200);
            resultIntent.putExtra("value2","문자열2");

            setResult(RESULT_OK,resultIntent);

            finish();
        }
    }
}