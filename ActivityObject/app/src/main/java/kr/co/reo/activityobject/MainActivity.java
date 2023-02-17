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

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    ActivityResultLauncher<Intent> secondActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        SecondActivityCallback callback1 = new SecondActivityCallback();
        ActivityResultContracts.StartActivityForResult contracts1 = new ActivityResultContracts.StartActivityForResult();
        secondActivityLauncher = registerForActivityResult(contracts1,callback1);

        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);
    }
    class SecondActivityCallback implements ActivityResultCallback<ActivityResult> {
        @Override
        public void onActivityResult(ActivityResult result) {
        //result code를 추출한다.
            int resultCode = result.getResultCode();
            if(resultCode==RESULT_OK){
                // Intent를 추출한다.
                Intent data = result.getData();
                // 객체를 추출한다
                TestClass t1 = data.getParcelableExtra("t1");
                activityMainBinding.textView.setText("data1 : " + t1.getData1() + "\n");
                activityMainBinding.textView.append("data2 : "+t1.getData2()+"\n");
            }
        }
    }
    class Button1ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);

            //전달할 객체를 생성한다.
            TestClass t1 = new TestClass(100,"문자열");
            secondIntent.putExtra("t1",t1);

            secondActivityLauncher.launch(secondIntent);
        }
    }
}