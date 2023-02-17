package kr.co.reo.onactivityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import kr.co.reo.onactivityresult.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding activitySecondBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(activitySecondBinding.getRoot());

        Button3ClickListener listener3 = new Button3ClickListener();
        activitySecondBinding.button3.setOnClickListener(listener3);
    }
    class Button3ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
