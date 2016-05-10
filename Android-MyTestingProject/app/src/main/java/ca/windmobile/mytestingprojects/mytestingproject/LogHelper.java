package ca.windmobile.mytestingprojects.mytestingproject;

import android.app.Activity;
import android.util.Log;

/**
 * Created by Tarek on 2016-05-08.
 */
public class LogHelper {

    public static void logCallBack(Activity activity,String callBackName){
        String log = String.format("Activity:%s | CallBack:%s",activity.getClass().getSimpleName(),callBackName);
        Log.d(
                "ACTIVITY_EVENT",log
        );
    }
}
