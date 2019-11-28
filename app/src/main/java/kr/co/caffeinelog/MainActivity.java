package kr.co.caffeinelog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private AddViewFragment addViewFragment;
    private AnalysisFragment analysisFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        addViewFragment = new AddViewFragment();
        analysisFragment = new AnalysisFragment();

        transaction = fragmentManager.beginTransaction();
        //초기 프래그먼트는 home으로
        transaction.replace(R.id.frameLayout, homeFragment).commitAllowingStateLoss();
    }
    public void clickHandler(View view){
        transaction = fragmentManager.beginTransaction();

        switch (view.getId()){
            case R.id.btn_home_fragment :
                transaction.replace(R.id.frameLayout,homeFragment).commitAllowingStateLoss();
                break;

            case R.id.btn_add_fragment:
                transaction.replace(R.id.frameLayout,addViewFragment).commitAllowingStateLoss();
                break;

            case R.id.btn_analysis_fragment:
                transaction.replace(R.id.frameLayout,analysisFragment).commitAllowingStateLoss();
        }
    }
}
