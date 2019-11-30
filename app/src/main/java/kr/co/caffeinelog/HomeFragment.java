package kr.co.caffeinelog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
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

        ageView.setOnLongClickListener(onLongClickListener);
        genderView.setOnLongClickListener(onLongClickListener);
        weightView.setOnLongClickListener(onLongClickListener);

        Resources res = getResources();
        Drawable progressdrawble = res.getDrawable(R.drawable.custom_progressbar_warning);
        progressBar.setProgressDrawable(progressdrawble);
        progressBar.setProgress(50);


        return rootView;
    }

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            if(view == ageView){

            }else if(view == genderView){

            }else if(view == weightView){

            }
            return false;
        }
    };
}
