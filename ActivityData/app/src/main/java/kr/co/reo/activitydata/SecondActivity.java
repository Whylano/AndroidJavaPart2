package kr.co.reo.activitydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.co.reo.activitydata.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding activitySecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(activitySecondBinding.getRoot());

        Button3ClickListener listener3 = new Button3ClickListener();
        activitySecondBinding.button3.setOnClickListener(listener3);

        // SecondActivity를 실행하기 위해 사용한 Intent를 추출한다.
        Intent secondIntent= getIntent();
        // 데이터를 추출한다.
        int data1 = secondIntent.getIntExtra("data1",0);
        double data2 = secondIntent.getDoubleExtra("data2",0.0);
        boolean data3 = secondIntent.getBooleanExtra("data3",false);
        String data4 = secondIntent.getStringExtra("data4");

        activitySecondBinding.textView2.setText("data1 : "+ data1 +"\n");
        activitySecondBinding.textView2.setText("data2 : "+ data2 +"\n");
        activitySecondBinding.textView2.setText("data3 : "+ data3 +"\n");
        activitySecondBinding.textView2.setText("data4 : "+ data4);

    }
    class Button3ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 이전 Activity로 전달할 Intent를 설정한다.
            Intent resultIntent = new Intent();
            resultIntent.putExtra("value1",300);
            resultIntent.putExtra("value2",33.33);
            resultIntent.putExtra("value3",true);
            resultIntent.putExtra("value4","문자열3");

            setResult(RESULT_OK,resultIntent);
            finish();
        }
    }
}