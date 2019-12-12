package kr.co.caffeinelog.Common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import kr.co.caffeinelog.ConnectDB.CaffeineScopeDAO;
import kr.co.caffeinelog.R;
import kr.co.caffeinelog.Service.AnalysisResultService;

public class FragmentDialog extends DialogFragment {

    private MyDialogListener myListener;
    private CaffeineScopeDAO caffeineScopeDAO = new CaffeineScopeDAO();
    private AnalysisResultService analysisResultService = new AnalysisResultService();
    private Fragment fragment;

    public interface MyDialogListener {
        public void myCallback(String cityName);
    }

    public FragmentDialog() {
        //caffeineScopeDAO = new CaffeineScopeDAO();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog, null))
                .setPositiveButton("수치를 입력해여", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText edCityName = (EditText) getDialog().findViewById(R.id.city_name);
                        String cityName = edCityName.getText().toString();
                        Log.i("chanmi", cityName);
                        if(caffeineScopeDAO.checkCaffeine(cityName)){
                            Log.i("chanmi","CaffeineDialog : success ");

                        }else{
                            Log.i("chanmi","CaffeineDAO FAIL");
                        }
                    }
                });
        return builder.create();
    }
}
