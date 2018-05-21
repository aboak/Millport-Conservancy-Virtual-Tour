package edu.fandm.aboak.android.millportapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import static com.google.android.gms.internal.zzbfq.NULL;

/**
 * Created by boaki on 3/21/2018.
 */

public class EntryActivity extends Activity
{
    private String className;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // launch a different activity
        Intent launchIntent = new Intent();
        Class<?> launchActivity;



        try
        {
            launchActivity = Class.forName(className);
            //Log.d("ENTRY", "Found something!");
        }
        catch (NullPointerException e)
        {
            //Log.d("ENTRY", "Defaulting to Home!");
            launchActivity = Home.class;
        }
        catch (ClassNotFoundException e)
        {
            //Log.d("ENTRY", "Defaulting to Home!");
            launchActivity = Home.class;
        }
        launchIntent.setClass(getApplicationContext(), launchActivity);
        startActivity(launchIntent);

        finish();
    }

    /** return Class name of Activity to show **/
    private String getScreenClassName()
    {
        // NOTE - Place logic here to determine which screen to show next
        // Default is used in this demo code
        String activity = Home.class.getName();
        return activity;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String stateSaved = savedInstanceState.getString("save_state");
        if(stateSaved==null){
            Toast.makeText(EntryActivity.this, "No state saved!", Toast.LENGTH_SHORT).show();
            className = getScreenClassName();
        }
        else{
            Toast.makeText(EntryActivity.this, "Saved state = " + stateSaved, Toast.LENGTH_SHORT).show();
            className = stateSaved;
        }
    }
}