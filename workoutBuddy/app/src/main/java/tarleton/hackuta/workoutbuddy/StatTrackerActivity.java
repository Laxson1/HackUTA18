package tarleton.hackuta.workoutbuddy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;

import java.util.concurrent.TimeUnit;

import static com.google.android.gms.fitness.Fitness.HISTORY_API;
import static com.google.android.gms.fitness.Fitness.RECORDING_API;

public class StatTrackerActivity extends AppCompatActivity  {

    private static final String TAG = "TAG";
    //Create the Google API Client
    GoogleApiClient mClient = new GoogleApiClient.Builder(this)
            //Calls the Different APIs
            .addApi(RECORDING_API)
            .addApi(HISTORY_API)
            //specifies the scope of access
            .addScope(Fitness.SCOPE_ACTIVITY_READ)
            .addScope(Fitness.SCOPE_BODY_READ_WRITE)
            //Provides Callbacks
            .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
            .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
            .build();
    public long totalDailySteps = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribe();
        readDailyResults();
    }
    public void subscribe() {
        // To create a subscription, invoke the Recording API. As soon as the subscription is
        // active, fitness data will start recording.
        Fitness.RecordingApi.subscribe(mClient, DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .setResultCallback(new ResultCallback<Status>() {

                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            if (status.getStatusCode()
                                    == FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED) {
                                Log.i(TAG, "Existing subscription for activity detected.");
                            } else {
                                Log.i(TAG, "Successfully subscribed!");
                            }
                        } else {
                            Log.w(TAG, "There was a problem subscribing.");
                        }
                    }
                });
    }
    public void readDailyResults(){
         class VerifyDataTask extends AsyncTask<Void, Void, Void> {
            protected Void doInBackground(Void... params) {


                PendingResult<DailyTotalResult> result = Fitness.HistoryApi.readDailyTotal(mClient,
                        DataType.TYPE_STEP_COUNT_DELTA);
                DailyTotalResult totalResult = result.await(30, TimeUnit.SECONDS);
                if (totalResult.getStatus().isSuccess()) {
                    DataSet totalSet = totalResult.getTotal();
                    totalDailySteps = totalSet.isEmpty()
                            ? 0
                            : totalSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                } else {
                    Log.w(TAG, "There was a problem getting the step count.");
                }

                Log.i(TAG, "Total steps: " + totalDailySteps);

                return null;
            }
        }
    }
}
