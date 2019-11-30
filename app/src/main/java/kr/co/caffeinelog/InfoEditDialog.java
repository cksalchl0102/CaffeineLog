package kr.co.caffeinelog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import kr.co.caffeinelog.Common.DatabaseBroker;
import kr.co.caffeinelog.Common.Info;

public class InfoEditDialog extends DialogFragment {

    OnMyDialogResult mDialogResult;

    DatabaseBroker databaseBroker;
    Info infoDatabase;

    int age;
    String gender;
    int weight;

    public static InfoEditDialog newInstance(){
        InfoEditDialog dialog = new InfoEditDialog();
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View layout = inflater.inflate(R.layout.info_edit_dialog, null);
        builder.setView(layout);
        builder.setTitle("정보 입력");

        databaseBroker = ((Main2Activity)getActivity()).databaseBroker;
        infoDatabase = ((Main2Activity)getActivity()).infoDatabase;
        age = infoDatabase.age;
        gender = infoDatabase.gender;
        weight = infoDatabase.weight;

        final EditText newAge = (EditText)layout.findViewById(R.id.newAge);
        final EditText newWeight = (EditText)layout.findViewById(R.id.newWeight);
        final RadioGroup radioGroup = (RadioGroup)layout.findViewById(R.id.radioGroup);
        final RadioButton maleRadioButton = (RadioButton)layout.findViewById(R.id.male);
        final RadioButton femaleRadioButton = (RadioButton)layout.findViewById(R.id.female);

        if(gender.equals("남자"))
            radioGroup.check(R.id.male);
        else
            radioGroup.check(R.id.female);

        newAge.setText(age+"");
        newWeight.setText(weight+"");

        newAge.setInputType(InputType.TYPE_CLASS_NUMBER);
        newWeight.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setPositiveButton("입력", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                infoDatabase.age = Integer.parseInt(newAge.getText().toString());
                infoDatabase.weight = Integer.parseInt(newWeight.getText().toString());
                if(maleRadioButton.isChecked()){
                    infoDatabase.gender = "남자";
                }else if(femaleRadioButton.isChecked()){
                    infoDatabase.gender = "여자";
                }

                mDialogResult.finish(infoDatabase);

                databaseBroker.saveInfoDatabase(((Main2Activity)getActivity()).context, infoDatabase);
            }
        });
        builder.setNegativeButton("취소", null);

        return builder.create();
    }

    public void setDialogResult(OnMyDialogResult dialogResult){

        mDialogResult = dialogResult;

    }



    public interface OnMyDialogResult{

        void finish(Info result);

    }
}
