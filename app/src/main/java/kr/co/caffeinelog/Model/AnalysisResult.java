package kr.co.caffeinelog.Model;

public class AnalysisResult {
    private String result;
    private int scope;

    public AnalysisResult() {

    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public String getResult() {
        return result;
    }

    public int getScope() {
        return scope;
    }

    public String toString() {
        return String.valueOf(this);
    }
}
