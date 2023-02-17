package kr.co.reo.activityaction;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import kr.co.reo.activityaction.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // 확인 받을 권한
    String [] permissionList ={
            Manifest.permission.CALL_PHONE
    };
    ActivityResultLauncher<String[]> permissionLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

//        ActivityResultContracts.RequestMultiplePermissions r1
//                = new ActivityResultContracts.RequestMultiplePermissions();
//        permissionLauncher = registerForActivityResult(r1,null);
//        permissionLauncher.launch(permissionList);

        requestPermissions(permissionList,0);


        Button1ClickListener listener1 = new Button1ClickListener();
        activityMainBinding.button.setOnClickListener(listener1);

        Button2ClickListener listener2 = new Button2ClickListener();
        activityMainBinding.button2.setOnClickListener(listener2);

        Buttton3ClickListener listener3 = new Buttton3ClickListener();
        activityMainBinding.button3.setOnClickListener(listener3);

        Button4ClickListener listener4 = new Button4ClickListener();
        activityMainBinding.button4.setOnClickListener(listener4);
    }
    class Button1ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 위도와 경도를 가지고 있는 URI 객체를 생성한다
            Uri uri = Uri.parse("geo:37.243243,131.861601");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }
    class Button2ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //요청할 페이지의 주소를 가지고 있는 Uri 객체를 생성한다.
            Uri uri = Uri.parse("https://developer.android.com");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }
    class Buttton3ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 전화 번호를 가지고 있는 Uri 객체를 생성한다.
            Uri uri = Uri.parse("tel:12341234");
            Intent intent = new Intent(Intent.ACTION_DIAL,uri);
            startActivity(intent);
        }
    }

    class Button4ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //전화 번호를 가지고 있는 Uri 객체를 생성한다.
            Uri uri = Uri.parse("tel:12341234");
            Intent intent = new Intent(Intent.ACTION_CALL,uri);
            startActivity(intent);
        }
    }
}