package kr.co.caffeinelog.Model;

public class AnalysisResult {
    private String result;
    private String scope;

    public AnalysisResult() {

    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getResult() {
        return result;
    }

    public String getScope() {
        return scope;
    }

    public String toString() {
        return String.valueOf(this);
    }
}
