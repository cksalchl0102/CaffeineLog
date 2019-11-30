package kr.co.caffeinelog.ConnectDB;

import android.util.Log;

import java.util.concurrent.ExecutionException;

import kr.co.caffeinelog.Model.AnalysisResult;

public class CaffeineScopeDAO {

    String[] returns;
    private AnalysisResult analysisResult = new AnalysisResult();

    public boolean checkCaffeine(String scope) {

        String result = "";

        try {
            result = new AnalysisTask().execute("getCaffeineScope", scope).get().toString();
            Log.i("chanmi", "result : " + result);
            if (result == null) {
                Log.i("chanmi", "DB값 가져오기 실패" + result);
                return false;
            }
            returns = result.split("\t");
            for (int i = 0; i < returns.length; i++) {
                Log.i("chanmi", "returns[" + i + "] = " + returns[i]);
            }
            if (returns[0].equals("successGetInfo")) {//DB 가져오기 성공홤
                Log.i("chanmi", "DB통신 성공, 카페인 지수 결과 : " + returns[1]);
                //analysisResult.setScope(returns[1]);
                return true;
            } else {//존재하지 않는 카페인 지수 입력
                Log.i("chanmi", "DB 통신 오류");
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return false;
    }
    public void settingResult(int scope){
        analysisResult.setScope(scope);

    }
}
