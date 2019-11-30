package kr.co.caffeinelog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import kr.co.caffeinelog.Common.FragmentDialog;


public class AnalysisFragment extends Fragment implements View.OnClickListener {
    private TextView textView;
    private Button dBTestButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.analysis_fragment, container, false);

        textView = rootView.findViewById(R.id.analysisCaffeine);
        textView.setText("database....");

        dBTestButton = rootView.findViewById(R.id.dbTestingBtm);
        dBTestButton.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dbTestingBtm:
                Bundle args = new Bundle();
                args.putString("key", "value");
                FragmentDialog dialog = new FragmentDialog();
                dialog.setArguments(args); // 데이터 전달
                dialog.show(getActivity().getSupportFragmentManager(), "tag");
                break;

        }
    }
}
