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
import kr.co.caffeinelog.ConnectDB.CaffeineScopeDAO;


public class AnalysisFragment extends Fragment implements View.OnClickListener {

    SharedPreferences caffeinePrefs;
    SharedPreferences.Editor editor;
    private Info infoDatabase;
    private double percent;
    private int intakeCaffeine;
    private double recommended;
    private int age;
    private int weight;
    private TextView scope;
    private TextView title1, title2, title3;
    private TextView result1, result2, result3;

    //DB
    private CaffeineScopeDAO caffeineScopeDAO = new CaffeineScopeDAO();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.analysis_fragment, container, false);

        title1 = rootView.findViewById(R.id.title1);
        title2 = rootView.findViewById(R.id.title2);
        title3 = rootView.findViewById(R.id.title3);
        result1 = rootView.findViewById(R.id.result1);
        result2 = rootView.findViewById(R.id.result2);
        result3 = rootView.findViewById(R.id.result3);


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
        int p = (int)percent;
        Log.i("chanmi","in anlysis Class : "+percent);

        String results[] = caffeineScopeDAO.getResult(String.valueOf(p));
        if(results[0].equals("fail")){
            Log.i("chanmi","in analysis Class : Data get Fail");
        }else{
            Log.i("chanmi","in analysis Class : data get Success");
            title1.setText(results[1]);
            result1.setText(results[2]);
            title2.setText(results[3]);
            result2.setText(results[4]);
            title3.setText(results[5]);
            result3.setText(results[6]);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
