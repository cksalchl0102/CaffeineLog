package kr.co.caffeinelog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kr.co.caffeinelog.Common.Info;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    ProgressBar progressBar;
    TextView ageView;
    TextView genderView;
    TextView weightView;
    TextView recommendedView;
    TextView intakeView;
    TextView percentView;
    ImageButton infoEditButton;

    private int age;
    private String gender;
    private int weight;
    private double recommended;
    private int intakeCaffeine;
    private double percent;

    private Info infoDatabase;

    SharedPreferences caffeinePrefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment, container, false);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        ageView = (TextView)rootView.findViewById(R.id.ageView);
        genderView = (TextView)rootView.findViewById(R.id.genderView);
        weightView = (TextView)rootView.findViewById(R.id.weightView);
        recommendedView = (TextView)rootView.findViewById(R.id.recommendedView);
        intakeView = (TextView)rootView.findViewById(R.id.intakeView);
        percentView = (TextView)rootView.findViewById(R.id.percentView);
        infoEditButton = (ImageButton) rootView.findViewById(R.id.infoEditButton);
        infoEditButton.setOnClickListener(onClickListener);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        infoDatabase = ((Main2Activity)getActivity()).infoDatabase; //내부파일에 저장된 info 값 불러오기

        setValueText(infoDatabase);
    }

    //imageview 클릭시****//////////////////////////////////////////////////////////////////
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //정보 입력 다이얼로그 띄우기--------------------------------
            InfoEditDialog dialog = InfoEditDialog.newInstance();
            dialog.show(getFragmentManager(), "info_edit_dialog");


            //다이얼로그 입력 정보 받아서 textview에 표시----------------------
            dialog.setDialogResult(new InfoEditDialog.OnMyDialogResult() {
                @Override
                public void finish(Info result) {
                    infoDatabase = result;

                    setValueText(infoDatabase);
                }
            });

        }
    };

    //값 셋팅하고 TextView 및 Progressbar에 표시
    public void setValueText(Info infoDatabase){

        //info 값 세팅----------------------------
        age = infoDatabase.age;
        gender = infoDatabase.gender;
        weight = infoDatabase.weight;

        //textview에 info 값 표시--------------------------
        ageView.setText(age+"세");
        genderView.setText(gender+"");
        weightView.setText(weight+"kg");


        //사용자 나이가 20세 미만일 경우
        //카페인 권장량은 1kg당 2.5mg ----------------------------
        if(age>=20){
            recommended = 400;
        }else{
            recommended = weight*2.5;
        }
        recommendedView.setText(recommended+"mg");


        //하루 섭취한 카페인--------------------------------------------
        //prefenrence에 저장된 하루 카페인 섭취량을 불러옴.
        caffeinePrefs = this.getActivity().getSharedPreferences("daily_caffeine",0);

        intakeCaffeine = caffeinePrefs.getInt("intake_caffeine",0);
        intakeView.setText(intakeCaffeine+"mg");


        //퍼센트 소수점 둘째자리까지만 출력-----------------------------------
        percent = intakeCaffeine/recommended*100;
        percent = Math.round(percent*100)/100.0;
        percentView.setText(percent+"%");


        //사람 모양 프로그래스바 값 설정---------------------------------
        //하루 카페인 섭취량이 권장량을 넘을 경우 빨간색
        if(intakeCaffeine>recommended) {
            Resources res = getResources();
            Drawable progressdrawble = res.getDrawable(R.drawable.custom_progressbar_warning);
            progressBar.setProgressDrawable(progressdrawble);
        }

        progressBar.setProgress((int)Math.round(percent));

    }

}
