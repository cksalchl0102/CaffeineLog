package kr.co.caffeinelog.Common;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;



public abstract class DatabaseBroker {

    public interface OnDataBrokerListener{
        public void onChange(String databaseStr);
    }

    //info====================================================================
    protected String infoDatabaseStr = "";
    protected OnDataBrokerListener infoOnDataBrokerListener = null;

    // database -----------------------------------------------------------------------
    OnDataBrokerListener checkOnDataBrokerListener = null;

    public abstract void setInfoOnDataBrokerListener(Context context, OnDataBrokerListener onDataBrokerListener);
    public abstract Info loadInfoDatabase(Context context);
    public abstract void saveInfoDatabase(Context context, Info settingsDatabase);

    public abstract void setCheckDatabaseRoot(OnDataBrokerListener onDataBrokerListener);
    public abstract void resetDatabase(Context context);


    public String rootPath = "";
    public static DatabaseBroker createDatabaseObject(String rootPath){
        DatabaseBroker  databaseBroker;
        databaseBroker = new FilebaseBroker();
        databaseBroker.rootPath = rootPath;
        return databaseBroker;
    }

    public static DatabaseBroker createFireDatabaseObject(String rootPath){
        DatabaseBroker  databaseBroker;
        databaseBroker = new FirebaseBroker();
        databaseBroker.rootPath = rootPath;
        return databaseBroker;
    }
}
