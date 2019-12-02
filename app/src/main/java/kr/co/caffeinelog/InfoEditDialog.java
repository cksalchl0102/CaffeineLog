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

    private int age;
    private String gender;
    private int weight;

    public static InfoEditDialog newInstance(){
        InfoEditDialog dialog = new InfoEditDialog();
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //다이얼로그 ui 설정-----------------------------------------
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View layout = inflater.inflate(R.layout.info_edit_dialog, null);
        builder.setView(layout);
        builder.setTitle("정보 입력");
        builder.setIcon(R.drawable.ic_create_black_24dp);

        //기존 info 값 받아오기-----------------------------------------
        databaseBroker = ((Main2Activity)getActivity()).databaseBroker;
        infoDatabase = ((Main2Activity)getActivity()).infoDatabase;
        age = infoDatabase.age;
        gender = infoDatabase.gender;
        weight = infoDatabase.weight;

        //받아온 info 값 다이얼로그에 셋팅하기-----------------------------
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


        //다이얼로그 확인 버튼 눌렀을 때-----------------------------------------------------
        builder.setPositiveButton("입력", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //나이, 몸무게 입력한 값으로 변경
                //입력 값이 없으면 기존 값 유지---------------------------------------
                if(!newAge.getText().toString().equals("")){
                    infoDatabase.age = Integer.parseInt(newAge.getText().toString());
                }
                if(!newWeight.getText().toString().equals("")){
                    infoDatabase.weight = Integer.parseInt(newWeight.getText().toString());
                }

                //선택된 라디오버튼 성별로 정보 변경---------------------------
                if(maleRadioButton.isChecked()){
                    infoDatabase.gender = "남자";
                }else if(femaleRadioButton.isChecked()){
                    infoDatabase.gender = "여자";
                }

                //다이얼로그 입력값 담아서 프레그먼트로 넘겨주기------------------------
                mDialogResult.finish(infoDatabase);

                databaseBroker.saveInfoDatabase(((Main2Activity)getActivity()).context, infoDatabase);
            }
        });

        builder.setNegativeButton("취소", null);

        return builder.create();
    }

    //다이얼로그 입력 값 home 프레그먼트로 넘겨주기=============================================

    public void setDialogResult(OnMyDialogResult dialogResult){

        mDialogResult = dialogResult;

    }

    public interface OnMyDialogResult{

        void finish(Info result);

    }
}
