package kr.co.caffeinelog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import kr.co.caffeinelog.Common.FragmentDialog;
import kr.co.caffeinelog.Common.Info;


public class AnalysisFragment extends Fragment implements View.OnClickListener {
    private TextView textView;
    private Button dBTestButton;

    SharedPreferences caffeinePrefs;
    SharedPreferences.Editor editor;
    private Info infoDatabase;
    private double percent;
    private int intakeCaffeine;
    private double recommended;
    private int age;
    private int weight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.analysis_fragment, container, false);

       // textView = rootView.findViewById(R.id.analysisCaffeine);
       // textView.setText("database....");


        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

        infoDatabase = ((Main2Activity) getActivity()).infoDatabase; //저장된 info 값 불러오기

        //하루 섭취한 카페인-----------------------------
        caffeinePrefs = this.getActivity().getSharedPreferences("daily_caffeine", 0);
        intakeCaffeine = caffeinePrefs.getInt("intake_caffeine", 0);
        age = infoDatabase.age;
        weight = infoDatabase.weight;

        if (age >= 20) {
            recommended = 400;
        } else {
            recommended = weight * 2.5;
        }
        percent = intakeCaffeine / recommended * 100;
        Log.i("chanmi","in anlysis Class : "+percent);
    }

    @Override
    public void onClick(View view) {

    }
}
