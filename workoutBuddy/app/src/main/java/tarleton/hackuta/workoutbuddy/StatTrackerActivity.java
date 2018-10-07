package tarleton.hackuta.workoutbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class StatTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_tracker);
    }
    public void createProfile(View view) {
        // button clicked
        //not working
        Intent intent = new Intent(StatTrackerActivity.this, homePage.class);
        //save profile name and email
        //EditText profile_Name = findViewById(R.id.profile_name);
        //EditText profile_Email = findViewById(R.id.profile_email);
        //String profileName = profile_Name.getText().toString();
        //String profileEmail = profile_Email.getText().toString();
        //Spinner spin_sex = findViewById(R.id.spinner_sex);
        //String sex = spin_sex.getSelectedItem().toString();
        //profile buddy = new profile(profileName, profileEmail, sex);
        //start next page and go to it
        startActivity(intent);
    }
}
