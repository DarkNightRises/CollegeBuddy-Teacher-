package majorproject.kone.in.collegebuddyTeacher.listener;

import java.net.MalformedURLException;

/**
 * Created by Kartikey on 7/15/2015.
 */
public interface NetworkResponseListener {
    public void preRequest() throws MalformedURLException;


    public void postRequest(String result) throws MalformedURLException;
}
