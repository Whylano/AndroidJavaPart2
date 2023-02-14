package kr.co.reo.popupmenu;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import kr.co.reo.popupmenu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        ButtonClickListener1 listener1 = new ButtonClickListener1();
        activityMainBinding.button.setOnClickListener(listener1);
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // PopupMenu 객체를 생성한다.
            PopupMenu pop = new PopupMenu(MainActivity.this, activityMainBinding.textView);

            // 메뉴를 구성한다.
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu1, pop.getMenu());

            // 팝업메뉴를 띄운다.
            pop.show();
            //메뉴 항목을 선택했을 때 반응하는 리스너를 등록한다
            PopupMenuItemClickListener1 listener1 = new PopupMenuItemClickListener1();
            pop.setOnMenuItemClickListener(listener1);
        }
    }

    // 팝업 메뉴의 항목을 선택했을 때...
    class PopupMenuItemClickListener1 implements PopupMenu.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            // 사용자가 선택한 메뉴항목의 아이디를 추출한다.
            int id = item.getItemId();

            switch (id) {
                case R.id.item1:
                    activityMainBinding.textView.setText("메뉴 1을 눌렸습니다");
                    break;
                case R.id.item2:
                    activityMainBinding.textView.setText("메뉴 2를 눌렀습니다");
                    break;
                case R.id.item3:
                    activityMainBinding.textView.setText("메뉴 3을 눌렀습니다");
                    break;
            }

            return false;
        }
    }
}