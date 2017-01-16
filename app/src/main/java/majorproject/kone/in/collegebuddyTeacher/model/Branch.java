package majorproject.kone.in.collegebuddyTeacher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kartikey on 15/01/17.
 */

public class Branch {
    public ArrayList<String> branchList;
    public ArrayList<Integer> branchId;
    public JSONArray branches;
    public Branch(JSONArray jsonArray){
        this.branches = jsonArray;
        initialiseBranches();
    }

    private void initialiseBranches() {
        branchList = new ArrayList<>();
        branchId = new ArrayList<>();
        for(int i=0;i<branches.length();i++){
            try {
                JSONObject branchObject = (JSONObject) branches.get(i);
                branchList.add(branchObject.getString("name"));
                branchId.add(branchObject.getInt("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }
    }
    public ArrayList<String> getBranchesNames(){
        return this.branchList;
    }
    public ArrayList<Integer> getBranchId(){
        return this.branchId;
    }
    public  int getId(int pos){
        return branchId.get(pos);
    }

}
