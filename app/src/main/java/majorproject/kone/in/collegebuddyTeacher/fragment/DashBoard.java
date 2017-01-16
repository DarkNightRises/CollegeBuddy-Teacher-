package majorproject.kone.in.collegebuddyTeacher.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import majorproject.kone.in.collegebuddyTeacher.R;

/**
 * Created by kartikey on 23/12/16.
 */

public class DashBoard extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard,container,false);

        return rootView;
    }

}
