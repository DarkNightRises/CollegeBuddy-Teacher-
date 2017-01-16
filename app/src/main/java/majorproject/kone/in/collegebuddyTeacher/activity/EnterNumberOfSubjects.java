package majorproject.kone.in.collegebuddyTeacher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.widget.EditText;

import majorproject.kone.in.collegebuddyTeacher.R;

/**
 * Created by kartikey on 16/01/17.
 */

public class EnterNumberOfSubjects extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entersubjectnumber);
        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfSubjects = Integer.parseInt(((EditText)findViewById(R.id.numberOfSubjects)).getText().toString());
                Intent intent = new Intent(EnterNumberOfSubjects.this,UploadSubject.class);
                Bundle bundle = new Bundle();
                bundle.putInt("numberOfSubject",numberOfSubjects);
                intent.putExtras(bundle);
            }
        });
    }
}
