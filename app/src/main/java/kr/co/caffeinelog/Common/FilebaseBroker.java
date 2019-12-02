package kr.co.caffeinelog.Common;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;



public class FilebaseBroker extends DatabaseBroker {

    String defaultInfo = "age:20####gender:남자####weight:60";

    //info 초가화(셋팅)==========================================================================
    public void setInfoOnDataBrokerListener(Context context, OnDataBrokerListener onDataBrokerListener){
        infoOnDataBrokerListener = onDataBrokerListener;

        try {
            infoDatabaseStr = defaultInfo;

            String fileName = rootPath+".txt";
            FileInputStream fileInputStream = context.openFileInput(fileName);
            if(fileInputStream.available() > 0) {
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer);
                infoDatabaseStr = new String(buffer);
            }
            fileInputStream.close();
        }catch(IOException e){
        }

        if(infoOnDataBrokerListener != null){
            infoOnDataBrokerListener.onChange(infoDatabaseStr);
        }
    }

    //info 값 불러오기=====================================================
    public Info loadInfoDatabase(Context context){
        Info info = new Info(infoDatabaseStr);
        return info;
    }

    //info 값 저장============================================================
    public void saveInfoDatabase(Context context, Info infoDatabase){
        infoDatabaseStr = infoDatabase.toString(); //새로운 info 값

        //새로운 info 값 내부파일에 저장하기
        try {
            String fileName = rootPath+".txt";
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(infoDatabaseStr.getBytes());
            fileOutputStream.close();
        }catch(IOException e){
        }

        if(infoOnDataBrokerListener != null){
            infoOnDataBrokerListener.onChange(infoDatabaseStr);
        }
    }

    public void setCheckDatabaseRoot(DatabaseBroker.OnDataBrokerListener onDataBrokerListener){

        checkOnDataBrokerListener = onDataBrokerListener;
        if (checkOnDataBrokerListener != null) {
            checkOnDataBrokerListener.onChange("CaffeineLog");
        }
    }

    public void resetDatabase(Context context){

        String[] fileNames = context.fileList();
        for(int i=0;i<fileNames.length;i++) {
            context.deleteFile(fileNames[i]);
        }

    }
}

