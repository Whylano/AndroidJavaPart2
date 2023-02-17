package kr.co.reo.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.co.reo.startactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        ToSecondBtnClickListener listener1 = new ToSecondBtnClickListener();
        activityMainBinding.toSecondBtn.setOnClickListener(listener1);
    }

    class ToSecondBtnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //SecondActivity 실행을 위한 정보를 담고 있는 Intent를 생성한다.
            Intent secondActivityIntent = new Intent(MainActivity.this,SecondActivity.class);
            // Activity를 실행한다.
            startActivity(secondActivityIntent);
        }
    }
}