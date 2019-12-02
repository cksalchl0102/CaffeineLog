package kr.co.caffeinelog.Service;

import java.util.ArrayList;
import java.util.List;

import kr.co.caffeinelog.Model.AnalysisResult;

public class AnalysisResultService {
    private AnalysisResult analysisResult = new AnalysisResult();

    public AnalysisResultService(){
    }

    public String getAnalysisResultResult(){
        return analysisResult.getResult();
    }


}
