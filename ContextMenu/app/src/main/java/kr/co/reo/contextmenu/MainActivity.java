package kr.co.reo.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import kr.co.reo.contextmenu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    String[] data1 = {
            "항목1", "항목2", "항목3", "항목4", "항목5",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, data1);
        activityMainBinding.list1.setAdapter(adapter);

        ListItemClickListener1 listener1 = new ListItemClickListener1();
        activityMainBinding.list1.setOnItemClickListener(listener1);

        // 뷰에 컨텍스트 메뉴를 등록한다.
        registerForContextMenu(activityMainBinding.textView);
        registerForContextMenu(activityMainBinding.list1);
    }

    class ListItemClickListener1 implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            activityMainBinding.textView.setText("리스트뷰의 항목 클릭 : " + data1[position]);
        }
    }

    // 컨텍스트 메뉴를 구성하는 메서드
    // 두 번째 : 메뉴를 띄우기 위해 사용자가 길게 누른 뷰 객체가 전달된다.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //사용자가 길게 누른 뷰의 아이디를 추출한다.
        int id = v.getId();
        // xml을 통해 메뉴를 구상하기 위한 객체
        MenuInflater menuInflater = getMenuInflater();

        //뷰의 아이디로 분기한다.
        switch (id) {
            //텍스트뷰라면..
            case R.id.textView:
                //제목
                menu.setHeaderTitle("텍스트뷰의 메뉴");
                menuInflater.inflate(R.menu.menu1, menu);
                break;
            case R.id.list1: //리스트 뷰라면...
                //사용자가 길게 누른 항목의 순서값을 파악하기 위해..
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                menu.setHeaderTitle("리스트뷰의 메뉴 : " + data1[info.position]);
                menuInflater.inflate(R.menu.menu2, menu);
                break;
        }
    }
    // 메뉴 항목을 터치했을 때 호출되는 메서드

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        // 사용자가 선택한 메뉴 항목의 아이디를 추출한다.
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.text_item1:
                activityMainBinding.textView.setText("텍스트뷰의 메뉴1을 선택했습니다");
                break;
            case R.id.text_item2:
                activityMainBinding.textView.setText("텍스트뷰의 메뉴2를 선택했습니다");
                break;
            case R.id.list_item1:
                AdapterView.AdapterContextMenuInfo info1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                activityMainBinding.textView.setText("리스트뷰의 메뉴1을 눌렀습니다 : " + data1[info1.position]);
                break;
            case R.id.list_item2:
                AdapterView.AdapterContextMenuInfo info2 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                activityMainBinding.textView.setText("리스트뷰의 메뉴2를 눌렀습니다 : " + data1[info2.position]);
                break;
        }

        return super.onContextItemSelected(item);
    }
}