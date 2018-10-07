package tarleton.hackuta.workoutbuddy;

import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.search.Location;

public class StatTrackerActivity extends AppCompatActivity {

    // map embedded in the map fragment
    private Map map = null;
    PositioningManager.LocationMethod locationMethod = PositioningManager.LocationMethod.GPS;
    GeoPosition geo;
    GeoCoordinate coord = geo.getCoordinate();

    // map fragment embedded in this activity
    private MapFragment mapFragment = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        setContentView(R.layout.activity_stat_tracker);

        // Search for the map fragment to finish setup by calling init().
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);

        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    // retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();
                    // Set the zoom level to the average between min and max
                    onPositionUpdated(coord);
                    map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                } else {
                    System.out.println("ERROR: Cannot initialize Map Fragment");
                }
            }
        });
    }

    public void onPositionUpdated(GeoCoordinate coord) {
        map.setCenter(coord, Map.Animation.LINEAR);
    }
}
