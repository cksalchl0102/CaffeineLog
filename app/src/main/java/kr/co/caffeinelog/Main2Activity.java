package kr.co.caffeinelog;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import kr.co.caffeinelog.Common.DatabaseBroker;
import kr.co.caffeinelog.Common.Info;

public class Main2Activity extends AppCompatActivity {
    DatabaseBroker databaseBroker;
    Info infoDatabase;
    String rootPath;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = this;
        rootPath = "CaffeineLog";

        databaseBroker = DatabaseBroker.createDatabaseObject(rootPath); //createDatabase()
        databaseBroker.setInfoOnDataBrokerListener(context, onInfoListener); //set Database for Settings
        infoDatabase = databaseBroker.loadInfoDatabase(context);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_addview, R.id.navigation_analysis)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    DatabaseBroker.OnDataBrokerListener onInfoListener = new DatabaseBroker.OnDataBrokerListener() {
        @Override
        public void onChange(String databaseStr) {
            infoDatabase = databaseBroker.loadInfoDatabase(context);
        }
    };

}
