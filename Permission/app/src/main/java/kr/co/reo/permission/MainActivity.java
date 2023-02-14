package kr.co.reo.permission;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import java.util.Map;
import java.util.Set;

import kr.co.reo.permission.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    //권한을 허용하지않은 것만 뜨니 기억이 잘 안난다면 다 허용하기
    //****테스트시 Android 실행앱에서 권한을 허용하지않고  다시 실행했을때 안될때는 에뮬레이터 버그다****.
    // 확인 받은 모든 권한 목록
    String[] permissionList = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    // 권한 확인 창을 띄우는 요소
    //권한을 확인할때는 String[]제네릭
    ActivityResultLauncher<String[]> locationLauncher;
    ActivityResultLauncher<String[]> contactsLauncher;
    ActivityResultLauncher<String[]> storageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        ButtonClickListener1 listener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(listener1);

        // 권한 확인 용도로 지정하기 위한 객체
        ActivityResultContracts.RequestMultiplePermissions r1 = new ActivityResultContracts.RequestMultiplePermissions();
        ActivityResultContracts.RequestMultiplePermissions r2 = new ActivityResultContracts.RequestMultiplePermissions();
        ActivityResultContracts.RequestMultiplePermissions r3 = new ActivityResultContracts.RequestMultiplePermissions();
        
        
        //요청 확인이 끝나면 동작할 콜백
        LocationPermissionCallback callback1 = new LocationPermissionCallback();
        ContactsPermissionCallback callback2 = new ContactsPermissionCallback();
        StoragePermissionCallback callback3 = new StoragePermissionCallback();

        //런처를 생성한다.
        locationLauncher = registerForActivityResult(r1,callback1);
        contactsLauncher = registerForActivityResult(r2,callback2);
        storageLauncher = registerForActivityResult(r3,callback3);

        ButtonClickListener2 listener2 = new ButtonClickListener2();
        ButtonClickListener3 listener3 = new ButtonClickListener3();
        ButtonClickListener4 listener4 = new ButtonClickListener4();

        activityMainBinding.button2.setOnClickListener(listener2);
        activityMainBinding.button3.setOnClickListener(listener3);
        activityMainBinding.button4.setOnClickListener(listener4);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //거부되어 있는 권한들을 사용자에게 확인한다.
            requestPermissions(permissionList, 0);
        }
    }
    //권한 확인이 끝나면 자동으로 호출되는 메서드
    //onRequest 치고 Tap키 누르면 @Override 구문이 나옴
    //모든 권한을 허용할때 사용**
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        activityMainBinding.resultTextView.setText("사용자 확인 요청\n");

        for (int i = 0; i < permissions.length; i++) {
            //현재 권한의 이름을 가져온다.
            String permission = permissions[i];
            int chk = grantResults[i];
            if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (chk == PackageManager.PERMISSION_GRANTED) {
                    activityMainBinding.resultTextView.append("위치1: 허용\n");
                } else if (chk == PackageManager.PERMISSION_DENIED) {
                    activityMainBinding.resultTextView.append("위치1 : 거부\n");
                }
            } else if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                if (chk == PackageManager.PERMISSION_GRANTED) {
                    activityMainBinding.resultTextView.append("위치2 : 허용\n");
                } else if (chk == PackageManager.PERMISSION_DENIED) {
                    activityMainBinding.resultTextView.append("위치2 : 거부\n");
                }
            } else if (permission.equals(Manifest.permission.READ_CONTACTS)) {
                if (chk == PackageManager.PERMISSION_GRANTED) {
                    activityMainBinding.resultTextView.append("연락처 1 : 허용\n");
                } else if (chk == PackageManager.PERMISSION_DENIED) {
                    activityMainBinding.resultTextView.append("연락처 1 : 거부\n");
                }
            } else if (permission.equals(Manifest.permission.WRITE_CONTACTS)) {
                if (chk == PackageManager.PERMISSION_GRANTED) {
                    activityMainBinding.resultTextView.append("연락처 2 : 허용\n");
                } else if (chk == PackageManager.PERMISSION_DENIED) {
                    activityMainBinding.resultTextView.append("연락처 2 : 거부\n");
                }
            } else if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                if (chk == PackageManager.PERMISSION_GRANTED) {
                    activityMainBinding.resultTextView.append("저장소 1 : 허용\n");
                } else if (chk == PackageManager.PERMISSION_DENIED) {
                    activityMainBinding.resultTextView.append("저장소 1 : 거부\n");
                }

            } else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (chk == PackageManager.PERMISSION_GRANTED) {
                    activityMainBinding.resultTextView.append("저장소 2 : 허용\n");
                } else if (chk == PackageManager.PERMISSION_DENIED) {
                    activityMainBinding.resultTextView.append("저장소 2 : 거부\n");
                }
            }
        }
    }
    class LocationPermissionCallback implements ActivityResultCallback<Map<String,Boolean>>{

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            activityMainBinding.resultTextView.setText("위치 권한 확인 \n");
            //확인한 권한 이름 목록을 가져온다.
            Set<String> a1 = result.keySet();

            for(String permission : a1){
                //해당 권한의 허용 여부값을 가져온다.
                boolean chk = result.get(permission);
                if(permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)){
                    if(chk == true){
                        activityMainBinding.resultTextView.append("위치 1 : 허용\n");
                    }else{
                        activityMainBinding.resultTextView.append("위치 1 : 거부\n");
                    }
                }else if(permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)){
                    if(chk == true){
                        activityMainBinding.resultTextView.append("위치 2 : 허용\n");
                    }else{
                        activityMainBinding.resultTextView.append("위치 2 : 거부\n");
                    }
                }
            }
        }
    }

    class ContactsPermissionCallback implements ActivityResultCallback<Map<String,Boolean>>{

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            activityMainBinding.resultTextView.setText("연락처 권한 확인 \n");
            //확인한 권한 이름 목록을 가져온다.
            Set<String> a1 = result.keySet();

            for(String permission : a1){
                //해당 권한의 허용 여부값을 가져온다.
                boolean chk = result.get(permission);
                if(permission.equals(Manifest.permission.READ_CONTACTS)){
                    if(chk == true){
                        activityMainBinding.resultTextView.append("연락처 1 : 허용\n");
                    }else{
                        activityMainBinding.resultTextView.append("연락처 1 : 거부\n");
                    }
                }else if(permission.equals(Manifest.permission.WRITE_CONTACTS)){
                    if(chk == true){
                        activityMainBinding.resultTextView.append("연락처 2 : 허용\n");
                    }else{
                        activityMainBinding.resultTextView.append("연락처 2 : 거부\n");
                    }
                }
            }
        }
    }
    class StoragePermissionCallback implements ActivityResultCallback<Map<String,Boolean>>{

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            activityMainBinding.resultTextView.setText("저장소 권한 확인 \n");
            //확인한 권한 이름 목록을 가져온다.
            Set<String> a1 = result.keySet();

            for(String permission : a1){
                //해당 권한의 허용 여부값을 가져온다.
                boolean chk = result.get(permission);
                if(permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    if(chk == true){
                        activityMainBinding.resultTextView.append("저장소 1 : 허용\n");
                    }else{
                        activityMainBinding.resultTextView.append("저장소 1 : 거부\n");
                    }
                }else if(permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    if(chk == true){
                        activityMainBinding.resultTextView.append("저장소 2 : 허용\n");
                    }else{
                        activityMainBinding.resultTextView.append("저장소 2 : 거부\n");
                    }
                }
            }
        }
    }
    class ButtonClickListener2 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // 확인할 권한
            String [] a1 ={
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
            locationLauncher.launch(a1);
        }
    }
    class ButtonClickListener3 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // 확인할 권한
            String [] a1 ={
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS
            };
            contactsLauncher.launch(a1);
        }
    }
    class ButtonClickListener4 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // 확인할 권한
            String [] a1 ={
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            storageLauncher.launch(a1);
        }
    }
}