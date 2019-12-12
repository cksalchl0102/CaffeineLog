package kr.co.caffeinelog.ConnectDB;

import android.util.Log;

import java.util.concurrent.ExecutionException;

import kr.co.caffeinelog.Model.AddCaffeineBean;
import kr.co.caffeinelog.Model.Analysis;

public class CaffeineScopeDAO {

    String[] returns;
    private Analysis analysisResult = new Analysis();
    private AddCaffeineBean addCaffeineBean = new AddCaffeineBean();

    public boolean checkCaffeine(String scope) {

        String result = "";

        try {
            result = new AnalysisTask().execute("getCaffeineScope", scope, "").get().toString();
            Log.i("chanmi", "getCaffeine Result : " + result);
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
                addCaffeineBean.setValue(returns[1]);
                //analysisResult.setScope(returns[1]);
//                settingResult(Integer.parseInt(returns[1]));
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

    public String addCaffeine(String sort, String name) {
        String result = "";
        try {
            result = new AnalysisTask().execute("addCaffeine", sort, name).get().toString();
            Log.i("chanmi", "addCaffine result : " + result);

            if (result == null) {
                Log.i("chanmi", "DB값 가져오기 실패" + result);
                return "false";
            }

            returns = result.split("\t");
            for (int i = 0; i < returns.length; i++) {
                Log.i("chanmi", "returns[" + i + "] = " + returns[i]);
            }
            if (returns[0].equals("successGetValue")) {//DB 가져오기 성공홤
                Log.i("chanmi", "DB통신 성공, 카페인  : " + returns[1]);
                //analysisResult.setScope(returns[1]);
//                settingResult(Integer.parseInt(returns[1]));
                return returns[1];
            } else {//존재하지 않는 카페인 지수 입력
                Log.i("chanmi", "DB 통신 오류, failGetValue");
                return "false";
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String[] getResult(String percent) {
        String result = "";
        try {
            result = new AnalysisTask().execute("getResult", percent, "").get();
            if (result == null) {
                returns[0] = "fail";
                return returns;
            }
            returns = result.split("\t");
            if (returns[0].equals("successGeAnalysis")) {
                for (int i = 0; i < returns.length; i++) {
                    Log.i("chanmi", "result[" + i + "] : " + returns[i]);
                }
                return returns;
            } else {
                returns[0] = "fail";
                return returns;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        returns[0] = "fail";
        return returns;
    }
/*
    public void settingResult(int scope) {
        analysisResult.setScope(scope);
        String result = "";
        if (scope == 0) {
            result = "카페인을 아무것도 섭취하지 않았군요!";
        } else if (0 < scope && scope <= 50) {
            result = "적당한 양의 카페인입니다. 집중력에 도움이되어요.";
        } else if (50 < scope && scope <= 100) {
            result = "오늘 할 일이 많으신가봐요 카페인 적정량을 거의 다 채우셨어요 이제 그만!";
        } else if (100 < scope && scope < 120) {
            result = "카페인 적정량을 초과하셨습니다. 심장이 두근거리고 소화장애를 일으킬 수 있어요.";
        } else {
            result = "카페인 과다 섭취량입니다. 맥박이 빨라지며, 심장이 많이 두군거릴 수 있어요.";
        }
        analysisResult.setResult(result);
    }*/
}
