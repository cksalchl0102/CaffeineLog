package kr.co.caffeinelog.Common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import kr.co.caffeinelog.R;


public class addViewDialogFragment extends DialogFragment {
    private EditText mCaffeine;
    private CaffeineInputListener listener;

    public static addViewDialogFragment newInstance(CaffeineInputListener listener) {
       addViewDialogFragment fragment = new addViewDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    public interface CaffeineInputListener
    {
        void onCaffeineInputComplete(String caffeine);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.addviewdialog, null);
        mCaffeine = (EditText)view.findViewById(R.id.id_txt_input);
        builder.setView(view)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                listener.onCaffeineInputComplete(mCaffeine
                                        .getText().toString());
                            }
                        }).setNegativeButton("취소", null);
        return builder.create();
    }
}
