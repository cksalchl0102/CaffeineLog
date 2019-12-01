package kr.co.caffeinelog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class DateChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_DATE_CHANGED)) {
            SharedPreferences caffeinePrefs = context.getSharedPreferences("daily_caffeine", 0);
            SharedPreferences.Editor editor = caffeinePrefs.edit();

            editor.putInt("intake_caffeine", 0);
            editor.apply();
        }
    }
}
