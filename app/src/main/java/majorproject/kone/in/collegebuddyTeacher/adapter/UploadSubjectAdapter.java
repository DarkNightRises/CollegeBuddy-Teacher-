package majorproject.kone.in.collegebuddyTeacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import majorproject.kone.in.collegebuddyTeacher.R;
import majorproject.kone.in.collegebuddyTeacher.listener.OnSubmit;

/**
 * Created by kartikey on 16/01/17.
 */

public class UploadSubjectAdapter extends RecyclerView.Adapter<UploadSubjectAdapter.USViewHolder> {
    Context context;
    int numberOfCards;
    ArrayList<String> branches;
    OnSubmit onSubmit;

    public UploadSubjectAdapter(Context context, int number, ArrayList<String> branches,  OnSubmit onSubmit) {
        this.context = context;
        this.numberOfCards = number;
        this.branches = branches;

        this.onSubmit = onSubmit;
    }

    @Override
    public USViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemVIew = LayoutInflater.from(context).inflate(R.layout.upload_subject_card, parent, false);
        USViewHolder viewHolder = new USViewHolder(itemVIew);
        return new USViewHolder(itemVIew);
    }

    @Override
    public void onBindViewHolder(USViewHolder holder, int position) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,branches);
        holder.branchesList.setAdapter(arrayAdapter);

        if (position == numberOfCards -1) {
            holder.submitSubjects.setVisibility(View.VISIBLE);
        }
        holder.submitSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit.submit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberOfCards ;
    }

    public class USViewHolder extends RecyclerView.ViewHolder {
        public Spinner branchesList;
        public EditText section, subject_name, year, subject_code;
        public Button submitSubjects;

        public USViewHolder(View itemView) {
            super(itemView);
            branchesList = (Spinner) itemView.findViewById(R.id.branchesList);
            subject_code = (EditText) itemView.findViewById(R.id.subject_code);
            subject_name = (EditText) itemView.findViewById(R.id.subject_name);
            section = (EditText) itemView.findViewById(R.id.section);
            year = (EditText) itemView.findViewById(R.id.year);
            submitSubjects = (Button) itemView.findViewById(R.id.submit_subjects);
        }
    }
}
