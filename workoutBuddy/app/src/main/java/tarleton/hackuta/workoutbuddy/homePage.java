package tarleton.hackuta.workoutbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
    public void toSettings(View view){
        // button clicked
        Intent intent = new Intent(this, settings.class);
        //start next page and go to it
        startActivity(intent);
    }
    public void toGymsNearMe(View view){
        // button clicked
        Intent intent = new Intent(this, gymsNearMe.class);
        startActivity(intent);
    }
    public void toWorkoutBuddiesNearMe(View view){
        // button clicked
        Intent intent = new Intent(this, workoutBuddiesNearMe.class);
        //start next page and go to it
        startActivity(intent);
    }
}
