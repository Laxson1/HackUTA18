package tarleton.hackuta.workoutbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class settings extends AppCompatActivity {
profile buddy = new profile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void backToHome(View view){
        Intent intent = new Intent(this, homePage.class);
        startActivity(intent);
    }
    public void changeProfileSettings(View view){
        EditText profile_Name = findViewById(R.id.change_name);
        EditText profile_Email = findViewById(R.id.change_email);
        if(profile_Name != null){
            String profileName = profile_Name.getText().toString();
            buddy.setName(profileName);
        }
        if(profile_Name != null){
            String profileEmail = profile_Email.getText().toString();
            buddy.setName(profileEmail);
        }
        Toast.makeText(getApplicationContext(), "Changed!",
                Toast.LENGTH_LONG).show();

    }
}
