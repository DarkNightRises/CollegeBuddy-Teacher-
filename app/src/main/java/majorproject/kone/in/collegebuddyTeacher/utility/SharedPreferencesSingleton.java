package majorproject.kone.in.collegebuddyTeacher.utility;

import android.content.Context;
import android.content.SharedPreferences;

import majorproject.kone.in.collegebuddyTeacher.Config;

/**
 * Created by kartikey on 30/12/16.
 */

public class SharedPreferencesSingleton {
    private SharedPreferencesSingleton() {
    }

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static SharedPreferences getSharedPreference() {

        return sharedPreferences;
    }

    public static SharedPreferences.Editor getSharedPreferenceEditor() {
        return editor;
    }

    public static void initSharedPreference(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Config.SharedPreferences, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }
}
