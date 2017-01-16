package majorproject.kone.in.collegebuddyTeacher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import majorproject.kone.in.collegebuddyTeacher.Config;
import majorproject.kone.in.collegebuddyTeacher.R;
import majorproject.kone.in.collegebuddyTeacher.listener.NetworkResponseListener;
import majorproject.kone.in.collegebuddyTeacher.model.Teacher;
import majorproject.kone.in.collegebuddyTeacher.network.FetchData;

/**
 * Created by kartikey on 15/01/17.
 */

public class SignUp extends Activity implements View.OnClickListener,NetworkResponseListener{
    private EditText name,college_name,mobile_number,email,password,confirm_password;
    private Button submitButton;
    private FetchData fetchData;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        initialiseView();
        submitButton.setOnClickListener(this);
    }
    private void initialiseView(){
        name = (EditText) findViewById(R.id.name);
        college_name = (EditText) findViewById(R.id.collegeName);
        mobile_number = (EditText) findViewById(R.id.mobileNumber);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirmPassword);
        submitButton = (Button) findViewById(R.id.submit);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                signUp();
                break;
        }
    }

    private void signUp() {
        if(validate()){
            try {
                JSONObject jsonObject = getPostData();
                fetchData = new FetchData(SignUp.this,SignUp.this);
                fetchData.setData(jsonObject);
                fetchData.setType_of_request(Config.POST);
                fetchData.setUrl(Config.BASE_URL+Config.SIGNUP);
                fetchData.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean validate() {
        /***
         * KARAN implement validations here
         */
        return true;
    }

    public JSONObject getPostData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",name.getText().toString());
            jsonObject.put("email",email.getText().toString());
            jsonObject.put("mobile_no",mobile_number.getText().toString());
            jsonObject.put("password",password.getText().toString());
            jsonObject.put("college_name",college_name.getText().toString());

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void preRequest() throws MalformedURLException {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postRequest(String result) throws MalformedURLException {
        progressBar.setVisibility(View.GONE);
        if(result!=null){
            try {
                JSONObject resultObject = new JSONObject(result);
                if(resultObject.getBoolean("sucess")==true){
                    JSONObject dataObject = (JSONObject) resultObject.getJSONArray("data").get(0);
                    //Saving student Info
                    Teacher teacher = new Teacher(dataObject);
                    Intent intent = new Intent(SignUp.this,EnterNumberOfSubjects.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
