package kr.co.reo.startactivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import kr.co.reo.startactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding activitySecondBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());

        setContentView(activitySecondBinding.getRoot());

        FinishSecondBtnClickListener listener1 = new FinishSecondBtnClickListener();
        activitySecondBinding.finishSecondBtn.setOnClickListener(listener1);
    }
    class FinishSecondBtnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // 현재 Activity를 종료한다.
            finish();
        }
    }
}