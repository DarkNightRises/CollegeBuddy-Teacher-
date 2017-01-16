package majorproject.kone.in.collegebuddyTeacher.utility;

import android.app.Application;

/**
 * Created by kartikey on 16/01/17.
 */

public class CollegeBuddyTeacherApplicaiotn extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initialiseSingletons();
    }

    private void initialiseSingletons() {
        SharedPreferencesSingleton.initSharedPreference(this);
    }
}
