package majorproject.kone.in.collegebuddyTeacher.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import majorproject.kone.in.collegebuddyTeacher.Config;
import majorproject.kone.in.collegebuddyTeacher.R;
import majorproject.kone.in.collegebuddyTeacher.adapter.UploadSubjectAdapter;
import majorproject.kone.in.collegebuddyTeacher.listener.NetworkResponseListener;
import majorproject.kone.in.collegebuddyTeacher.listener.OnSubmit;
import majorproject.kone.in.collegebuddyTeacher.model.Branch;
import majorproject.kone.in.collegebuddyTeacher.model.Teacher;
import majorproject.kone.in.collegebuddyTeacher.network.FetchData;
import majorproject.kone.in.collegebuddyTeacher.utility.SharedPreferencesSingleton;

/**
 * Created by kartikey on 16/01/17.
 */

public class UploadSubject extends Activity implements NetworkResponseListener,OnSubmit{
    private RecyclerView uploadsubjectList;
    private UploadSubjectAdapter uploadSubjectAdapter;
    private FetchData fetchData;
    private Branch branch;
    private int numberOfSubjects;
    private int dataflow =0;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_subjects);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        uploadsubjectList = (RecyclerView) findViewById(R.id.upload_subject_list);


        Bundle bundle = getIntent().getExtras();
        try {
            JSONArray jsonArray = new JSONObject(bundle.getString(EnterNumberOfSubjects.branchesJSON)).getJSONArray("data");
            branch = new Branch(jsonArray);
            numberOfSubjects=bundle.getInt(EnterNumberOfSubjects.numberOfSubject);
            uploadSubjectAdapter = new UploadSubjectAdapter(this,numberOfSubjects,
                    branch.getBranchesNames(),this);
            RecyclerView.LayoutManager mLayoutManager =new LinearLayoutManager(getApplicationContext());
            uploadsubjectList.setLayoutManager(mLayoutManager);
            uploadsubjectList.setItemAnimator(new DefaultItemAnimator());
            uploadsubjectList.setAdapter(uploadSubjectAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void preRequest() throws MalformedURLException {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postRequest(String result) throws MalformedURLException {
        progressBar.setVisibility(View.GONE);
        if(result != null){
            Log.d("Results are ",result);
        }
    }

    @Override
    public void submit() {
        JSONArray sectionArray = new JSONArray();
           dataflow = 0;
            try {
                for(int i=0;i< numberOfSubjects;i++) {
                    UploadSubjectAdapter.USViewHolder usViewHolder = (UploadSubjectAdapter.USViewHolder) uploadsubjectList.findViewHolderForAdapterPosition(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("section", usViewHolder.section.getText().toString());
                    int position = branch.getId(usViewHolder.branchesList.getSelectedItemPosition());
                    jsonObject.put("branch_id", position);
                    jsonObject.put("year",Integer.parseInt(usViewHolder.year.getText().toString()));
                    jsonObject.put("section",Integer.parseInt( usViewHolder.section.getText().toString()));
                    sectionArray.put(jsonObject);
                }
                JSONObject entireJSONObject = new JSONObject();
                entireJSONObject.put("id", SharedPreferencesSingleton.getSharedPreference().getInt(Teacher.id,0));
                entireJSONObject.put("sections",sectionArray);
                Log.d("Entire JSON","Entire json"+entireJSONObject);
                fetchData = new FetchData(UploadSubject.this,UploadSubject.this);
                fetchData.setHeader(SharedPreferencesSingleton.getSharedPreference().getString(Teacher.api_token,""));
                fetchData.setUrl(Config.BASE_URL+Config.GET_SECTION_ID);
                fetchData.setType_of_request(Config.POST);
                fetchData.setData(entireJSONObject);
                fetchData.execute();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

    }
}
