package majorproject.kone.in.collegebuddyTeacher.activity;

/**
 * Created by kartikey on 02/09/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import majorproject.kone.in.collegebuddyTeacher.R;
import majorproject.kone.in.collegebuddyTeacher.model.Teacher;
import majorproject.kone.in.collegebuddyTeacher.utility.SharedPreferencesSingleton;


/**
 * Created by koneracks on 12/21/15.
 */
public class Splash extends Activity {
    private int DELAY_TIME = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Log.d("Splash", "Starts");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent pageIntent;
                if(!SharedPreferencesSingleton.getSharedPreference().getString(Teacher.name,"").equals("")){
                    pageIntent = new Intent(Splash.this,
                            EnterNumberOfSubjects.class);

                }
                else {
                    pageIntent = new Intent(Splash.this, LoginActivity.class);
                }
                startActivity(pageIntent);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                finish();

            }
        },1000);
    }

}