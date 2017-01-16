package majorproject.kone.in.collegebuddyTeacher.model;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import majorproject.kone.in.collegebuddyTeacher.utility.SharedPreferencesSingleton;

/**
 * Created by kartikey on 16/01/17.
 */

public class Teacher {
    private JSONObject teacherInfo;
    public static String id = "id";
    public static String name = "name";
    public static String email = "email";
    public static String mob_no = "mob_no";
    public static String password = "password";
    public static String gcm_id = "gcm_id";
    public static String device_id = "device_id";
    public static String api_token = "api_token";
    public static String college_id = "college_id";
    public Teacher(JSONObject jsonObject){
        this.teacherInfo = jsonObject;
        saveTeacherInfo();
    }

    private void saveTeacherInfo() {
        SharedPreferences.Editor editor = SharedPreferencesSingleton.getSharedPreferenceEditor();
        Log.d("Teacher info","Teacher info "+teacherInfo);
        try {
            editor.putInt(id,teacherInfo.getInt(id));
        editor.putString(name,teacherInfo.getString(name));
        editor.putInt(college_id,teacherInfo.getInt(college_id));
        editor.putString(email,teacherInfo.getString(email));
        editor.putString(mob_no,teacherInfo.getString(mob_no));
        editor.putString(gcm_id,teacherInfo.getString(gcm_id));
        editor.putString(device_id,teacherInfo.getString(device_id));
        editor.putString(api_token,teacherInfo.getString(api_token));
        editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
