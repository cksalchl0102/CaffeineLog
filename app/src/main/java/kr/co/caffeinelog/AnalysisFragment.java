package kr.co.caffeinelog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;


public class AnalysisFragment extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.analysis_fragment, container, false);

        textView = rootView.findViewById(R.id.analysisCaffeine);
        textView.setText("database....");

        return rootView;

    }
}
