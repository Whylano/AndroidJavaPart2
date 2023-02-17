package kr.co.reo.onactivityresult;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.AccessibleObject;

import kr.co.reo.onactivityresult.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    static final int SECOND_ACTIVITY = 0;
    static final int THIRD_ACTIVITY = 1;

    // 액티비티를 실행할 때 사용할 런처
    ActivityResultLauncher<Intent> secondActivityLauncher;
    ActivityResultLauncher<Intent> thirdActivityLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);
        // 액티비티 실행을 위한 런처 생성
        SecondActivityCallback callback1 = new SecondActivityCallback();
        ActivityResultContracts.StartActivityForResult contracks1
                = new ActivityResultContracts.StartActivityForResult();
        secondActivityLauncher = registerForActivityResult(contracks1,callback1);

        ThirdActivityCallback callback2 = new ThirdActivityCallback();
        ActivityResultContracts.StartActivityForResult contracts2
                = new ActivityResultContracts.StartActivityForResult();
        thirdActivityLauncher = registerForActivityResult(contracts2,callback2);

        Button8ClickListener listener8 = new Button8ClickListener();
        activityMainBinding.button8.setOnClickListener(listener8);

        Button9ClickListener listener9 = new Button9ClickListener();
        activityMainBinding.button9.setOnClickListener(listener9);
    }

    class Button1ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
            //startActivity(secondIntent);
            startActivityForResult(secondIntent, SECOND_ACTIVITY);
        }
    }

    class Button2ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent thirdIntent = new Intent(MainActivity.this, ThirdActivity.class);
            //startActivity(thirdIntent);
            startActivityForResult(thirdIntent, THIRD_ACTIVITY);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SECOND_ACTIVITY:
                activityMainBinding.textView.setText("SecondActivity 에서 돌아옴");
                break;
            case THIRD_ACTIVITY:
                activityMainBinding.textView.setText("ThirdActivity 에서 돌아옴\n");

                switch (resultCode) {
                    case RESULT_OK:
                        activityMainBinding.textView.append("RESULT_OK");
                        break;
                    case RESULT_CANCELED:
                        activityMainBinding.textView.append("RESULT_CANCELED");
                        break;
                    case RESULT_FIRST_USER:
                        activityMainBinding.textView.append("RESULT_FIRST_USER");
                        break;
                    case RESULT_FIRST_USER + 1:
                        activityMainBinding.textView.append("RESULT_FIRST_USER + 1");
                        break;

                }

                break;

        }
    }
    // SecondActivity에서 복귀했을 때 동작할 콜백
    class SecondActivityCallback implements ActivityResultCallback<ActivityResult>{

        @Override
        public void onActivityResult(ActivityResult result) {
            activityMainBinding.textView.setText("");
        }
    }
    //ThirdActivity에서 복귀했을 때 동작할 콜백
    class ThirdActivityCallback implements ActivityResultCallback<ActivityResult>{
        @Override
        public void onActivityResult(ActivityResult result) {
            // result code를 추출한다.
            int resultCode = result.getResultCode();
            activityMainBinding.textView.setText("ThirdActivity 에서 돌아옴\n");

            switch (resultCode) {
                case RESULT_OK:
                    activityMainBinding.textView.append("RESULT_OK");
                    break;
                case RESULT_CANCELED:
                    activityMainBinding.textView.append("RESULT_CANCELED");
                    break;
                case RESULT_FIRST_USER:
                    activityMainBinding.textView.append("RESULT_FIRST_USER");
                    break;
                case RESULT_FIRST_USER + 1:
                    activityMainBinding.textView.append("RESULT_FIRST_USER + 1");
                    break;

            }
        }
    }
    class Button8ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // SecondActivity 실행
            Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
            secondActivityLauncher.launch(secondIntent);
        }
    }
    class Button9ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //ThirdActivity 실행
            Intent thirdIntent = new Intent(MainActivity.this,ThirdActivity.class);
            thirdActivityLauncher.launch(thirdIntent);
        }
    }
}