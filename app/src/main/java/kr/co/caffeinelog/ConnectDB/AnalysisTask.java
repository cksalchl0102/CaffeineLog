package kr.co.caffeinelog.ConnectDB;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.net.*;

public class AnalysisTask extends AsyncTask<String, Void, String> {
    //mySQL DB에서 카페인 섭취 분석 결과 데이터 값과 연동하는 클래스입니다.

    private String sendMsg, recieveMsg;

    @Override
    protected String doInBackground(String... strings) {

        //카페인 분석 결과를 갖고있는 DB와 연결
        String jspUrl = "http://13.125.33.50:8080/ServerOfCaffeineLog/LogDB.jsp";
        //http://localhost:8080/Caffenine/
        ///ServerOfCaffeineLog/LogDB.jsp"
        //기숙사 내꺼 : 192.168.1.199
        //기숙사 방꺼 : 192.168.0.6
        //집 192.168.219.100
        //독서실 172.16.9.157
        //강의실 : 223.194.134.219
        //aws ; 52.78.234.193
        //aws 2 : 13.125.33.50
        HttpURLConnection conn = null;

        try {
            URL url = new URL(jspUrl);
            String tmp;

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");//POST

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            // 원하는 DB의 종류(type)과 조건(value)를 저장
            sendMsg = "type=" + strings[0] + "&scope=" + strings[1] + "&name=" + strings[2];
            Log.i("chanmi", sendMsg);

            osw.write(sendMsg);
            Log.i("chanmi", "osw.write(sendMsg) 성공");

            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuffer buffer = new StringBuffer();

                while ((tmp = reader.readLine()) != null) {
                    buffer.append(tmp);
                }
                recieveMsg = buffer.toString();
                Log.i("chanmi", "recieveMsg : " + recieveMsg);
            } else {
                Log.i("chanmi", "통신 에러입니당");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.disconnect();
            Log.e("conn_disconnect", "disconnect");
        }
        return recieveMsg;
    }
}
