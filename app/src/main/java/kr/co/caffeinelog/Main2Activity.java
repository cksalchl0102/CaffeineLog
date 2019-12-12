package kr.co.caffeinelog;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.HashMap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import kr.co.caffeinelog.Common.DatabaseBroker;
import kr.co.caffeinelog.Common.Info;

public class Main2Activity extends AppCompatActivity {
    DatabaseBroker databaseBroker;
    //DatabaseBroker fireDatabaseBroker;
    Info infoDatabase;
    String rootPath;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //가로화면 안됨.

        //액션바 설정-----------------------------------------------------------
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFffffff));

        checkDate();

        context = this;
        rootPath = "CaffeineLog";

        //하단 메뉴바(add, home, log탭)-----------------------------------------------------------
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_addview, R.id.navigation_analysis)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //info 데이터베이스------------------------------------------------
        databaseBroker = DatabaseBroker.createDatabaseObject(rootPath); //데이터베이스 생성
        databaseBroker.setInfoOnDataBrokerListener(context, onInfoListener); //info 데이터베이스 셋팅
        infoDatabase = databaseBroker.loadInfoDatabase(context); //info 데이터 불러오기

        //fireDatabaseBroker = DatabaseBroker.createFireDatabaseObject(rootPath);

    }


    DatabaseBroker.OnDataBrokerListener onInfoListener = new DatabaseBroker.OnDataBrokerListener() {
        @Override
        public void onChange(String databaseStr) {
            infoDatabase = databaseBroker.loadInfoDatabase(context);
        }
    };


    //현재 날짜와 저장된 날짜가 다르면 하루 카페인 섭취량 초기화=====================================
    public void checkDate(){

        Calendar calendar = Calendar.getInstance(); //현재 날짜와 시간 정보를 객체에 저장
        int date = calendar.get(Calendar.DATE); //현재 날짜

        SharedPreferences caffeinePrefs = getSharedPreferences("daily_caffeine",0);

        if(date != caffeinePrefs.getInt("date",0)){
            SharedPreferences.Editor editor = caffeinePrefs.edit();
            editor.putInt("date", date);
            editor.putInt("intake_caffeine", 0);
            editor.apply();
        }

    }
}
