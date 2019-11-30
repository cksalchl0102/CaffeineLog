package kr.co.caffeinelog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kr.co.caffeinelog.Common.Info;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

    int age;
    String gender;
    int weight;
    double recommended;

    Info infoDatabase;

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

        infoDatabase = ((Main2Activity)getActivity()).infoDatabase;

        age = infoDatabase.age;
        gender = infoDatabase.gender;
        weight = infoDatabase.weight;

        if(age>=20){
            recommended = 400;
        }else{
            recommended = weight*2.5;
        }

        ageView.setText(age+"세");
        genderView.setText(gender+"");
        weightView.setText(weight+"kg");
        recommendedView.setText(recommended+"mg");

        Resources res = getResources();
        Drawable progressdrawble = res.getDrawable(R.drawable.custom_progressbar_warning);
        progressBar.setProgressDrawable(progressdrawble);
        progressBar.setProgress(50);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            InfoEditDialog dialog = InfoEditDialog.newInstance();
            dialog.show(getFragmentManager(), "info_edit_dialog");

            dialog.setDialogResult(new InfoEditDialog.OnMyDialogResult() {
                @Override
                public void finish(Info result) {
                    infoDatabase = result;

                    age = infoDatabase.age;
                    gender = infoDatabase.gender;
                    weight = infoDatabase.weight;

                    if(age>=20){
                        recommended = 400;
                    }else{
                        recommended = weight*2.5;
                    }

                    ageView.setText(age+"세");
                    genderView.setText(gender+"");
                    weightView.setText(weight+"kg");
                    recommendedView.setText(recommended+"mg");
                }
            });

        }
    };
}
