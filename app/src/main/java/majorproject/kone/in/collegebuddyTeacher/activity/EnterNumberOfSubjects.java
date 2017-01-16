package majorproject.kone.in.collegebuddyTeacher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import majorproject.kone.in.collegebuddyTeacher.Config;
import majorproject.kone.in.collegebuddyTeacher.R;
import majorproject.kone.in.collegebuddyTeacher.listener.NetworkResponseListener;
import majorproject.kone.in.collegebuddyTeacher.network.FetchData;

/**
 * Created by kartikey on 16/01/17.
 */

public class EnterNumberOfSubjects extends Activity implements NetworkResponseListener{
 public static String numberOfSubject = "numberOfSubject";
    public static String branchesJSON = "CollegeJSON";
    private ProgressBar progressBar;
    private FetchData fetchData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entersubjectnumber);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        findViewById(R.id.continueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getBranches();
            }
        });
    }
    private void getBranches(){
        fetchData = new FetchData(EnterNumberOfSubjects.this,EnterNumberOfSubjects.this);
        fetchData.setType_of_request(Config.GET);
        fetchData.setUrl(Config.BASE_URL+Config.GET_BRANCH);
        fetchData.execute();
    }

    @Override
    public void preRequest() throws MalformedURLException {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postRequest(String result) throws MalformedURLException {
        progressBar.setVisibility(View.GONE);
        if(result != null){
            try {
                JSONObject resultObject = new JSONObject(result);
                if(resultObject.getBoolean("success")==true){
                    int numberOfSubjects = Integer.parseInt(((EditText)findViewById(R.id.numberOfSubjects)).getText().toString());
                    Intent intent = new Intent(EnterNumberOfSubjects.this,UploadSubject.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(numberOfSubject,numberOfSubjects);
                    bundle.putString(branchesJSON,result);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }
    }
}
