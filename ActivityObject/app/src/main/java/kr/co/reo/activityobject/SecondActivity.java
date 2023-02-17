package kr.co.reo.activityobject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.co.reo.activityobject.databinding.ActivityMainBinding;
import kr.co.reo.activityobject.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding activitySecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(activitySecondBinding.getRoot());

        Button2ClickListener listener2 = new Button2ClickListener();
        activitySecondBinding.button2.setOnClickListener(listener2);

        //Intent 추출한다
        Intent secondIntent = getIntent();
        //객체를 복원한다
        TestClass t1 = secondIntent.getParcelableExtra("t1");

        activitySecondBinding.textView2.setText("data 1: "+t1.getData1() +"\n");
        activitySecondBinding.textView2.append("data2 : "+t1.getData1());
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent resultIntent = new Intent();
            //객체를 생성한다.
            TestClass t2 = new TestClass(200,"문자열");
            resultIntent.putExtra("t2",t2);

            setResult(RESULT_OK,resultIntent);

            finish();
        }
    }
}