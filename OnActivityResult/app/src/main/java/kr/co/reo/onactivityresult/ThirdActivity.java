package kr.co.reo.onactivityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import kr.co.reo.onactivityresult.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {
    ActivityThirdBinding activityThirdBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityThirdBinding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(activityThirdBinding.getRoot());

        Button4ClickListener listener4 = new Button4ClickListener();
        activityThirdBinding.button4.setOnClickListener(listener4);

        Button5ClickListener listener5 = new Button5ClickListener();
        activityThirdBinding.button5.setOnClickListener(listener5);

        Button6ClickListener listener6 = new Button6ClickListener();
        activityThirdBinding.button6.setOnClickListener(listener6);

        Button7ClickListener listener7 = new Button7ClickListener();
        activityThirdBinding.button7.setOnClickListener(listener7);
    }
    class Button4ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 작업이 잘 완료되었다는 의미
            setResult(RESULT_OK);
            finish();
        }
    }
    class  Button5ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 작업이 취소 되었다는 의미
            setResult(RESULT_CANCELED);
            finish();
        }
    }
    class Button6ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 사용자 정의
            setResult(RESULT_FIRST_USER);
            finish();
        }
    }
    class Button7ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //사용자 정의
            setResult(RESULT_FIRST_USER + 1);
            finish();
        }
    }
}