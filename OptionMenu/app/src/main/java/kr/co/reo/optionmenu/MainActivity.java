package kr.co.reo.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

import kr.co.reo.optionmenu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }
    //option menu를 구성하는 메서드

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //xml 파일로부터 메뉴를 생성하는 객체
        //MenuInflater menuInflater =  getMenuInflater();
        //menuInflater.inflate(R.menu.main_menu,menu);

        //코드를 통해 메뉴를 구성한다.
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴 1");
        SubMenu subMenu = menu.addSubMenu("코드 메뉴 2");
        subMenu.add(Menu.NONE, Menu.FIRST + 10, Menu.NONE, "하위 메뉴1");
        subMenu.add(Menu.NONE, Menu.FIRST + 20, Menu.NONE, "하위 메뉴2");
        menu.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "코드 메뉴 3");

        return true; //false를 주면 안나타난다(이걸로 메뉴바 설정할 수 있음)
    }
    //사용자가 메뉴를 선택했을 때 호출되는 메서드

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 사용자가 터치한 메뉴의 객체에서 id를 추출한다.
        int id = item.getItemId();
//        switch (id) {
//            case R.id.menu_item1:
//                activityMainBinding.textView.setText("메뉴 항목1을 선택했습니다");
//                break;
//            case R.id.menu_item3:
//                activityMainBinding.textView.setText("메뉴 항목3을 선택했습니다");
//                break;
//            case R.id.sub_item1:
//                activityMainBinding.textView.setText("하위 항목1을 선택했습니다");
//                break;
//            case R.id.sub_item2:
//                activityMainBinding.textView.setText("하위 항목2를 선택했습니다");
//                break;
//        }
        switch(id){
            case Menu.FIRST:
                activityMainBinding.textView.setText("코드 메뉴1을 선택했습니다");
                break;
            case Menu.FIRST+2:
                activityMainBinding.textView.setText("코드 메뉴3을 선택했습니다");
                break;
            case Menu.FIRST+10:
                activityMainBinding.textView.setText("하위 메뉴 1을 선택했습니다");
                break;
            case Menu.FIRST+20:
                activityMainBinding.textView.setText("하위 메뉴 2를 선택했습니다.");
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}