package tarleton.hackuta.workoutbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

    public class WorkoutsNearMe extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_workout_buddies_near_me);
        }
        public void toSettings(View view){
            // button clicked
            Intent intent = new Intent(this, settings.class);
            //start next page and go to it
            startActivity(intent);
        }
        public void backToHome(View view){
            Intent intent = new Intent(this, homePage.class);
            startActivity(intent);
        }
        public void toWourkoutBuddies(View view){
            // button clicked
            Intent intent = new Intent(this, WorkoutsNearMe.class);
            //start next page and go to it
            startActivity(intent);
        }
    }
