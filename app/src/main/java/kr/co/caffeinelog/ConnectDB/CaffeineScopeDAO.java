package kr.co.caffeinelog.ConnectDB;

import android.util.Log;

import java.util.concurrent.ExecutionException;

public class CaffeineScopeDAO {
    String[] returns;
    public boolean checkCaffeine(String scope){

        String result = "";

        try{
            result = new AnalysisTask().execute("getCaffeineScope",scope).get();
            if(result==null){
                Log.i("chanmi","DB값 가져오기 실패"+result);
                return false;
            }
            returns = result.split("\t");
            if (returns[0].equals("successGetInfo")) {
                Log.i("chanmi","DB 통신 오류");
                return false;
            }else{//DB 가져오기 성공
                Log.i("chanmi","DB통신 성공, 카페인 지수 결과 : "+returns[1]);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return false;
    }
}
