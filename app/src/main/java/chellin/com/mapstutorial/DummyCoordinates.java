package chellin.com.mapstutorial;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chellin on 21/11/16.
 */

public class DummyCoordinates extends AsyncTask<Object, String, String> {



//    String googlePlacesData;
    GoogleMap mMap;
//    String url;
    Location loc;

    @Override
    protected String doInBackground(Object... params) {
        try {
            Log.d("Dummy Coordinate", "doInBackground entered");
            mMap = (GoogleMap) params[0];
            loc = (Location) params[1];
//            url = (String) params[1];
//            DownloadUrl downloadUrl = new DownloadUrl();
//            googlePlacesData = downloadUrl.readUrl(url);
            Log.d("GooglePlacesReadTask", "doInBackground Exit");
        } catch (Exception e) {
            Log.d("GooglePlacesReadTask", e.toString());
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        ArrayList<Coordinates> x = new ArrayList<>();
//        x.add(new Coordinates("Suryalaya 4",-6.945321,107.623530));
        x.add(new Coordinates("Balai sartika",-6.942530,107.625461));
        x.add(new Coordinates("Bikasoga",-6.943531,107.624710));
        x.add(new Coordinates("The Harvest",-6.944831,107.630074));
        x.add(new Coordinates("Pizza Hut",-6.937013,107.623186));
        x.add(new Coordinates("Sari Sunda",-6.948899,107.623422));
        x.add(new Coordinates("LPKIA",-6.949900,107.624624));
        x.add(new Coordinates("PT INTI",-6.938100,107.607243));
        x.add(new Coordinates("Horison",-6.935501,107.625525));


        Log.d("DummyReadTask", "onPostExecute Entered");
        ShowNearbyPlaces(x);
        Log.d("GooglePlacesReadTask", "onPostExecute Exit");
    }

    private void ShowNearbyPlaces(ArrayList<Coordinates> nearbyPlacesList) {
        for (int i = 0; i < nearbyPlacesList.size(); i++) {
            Log.d("onPostExecute","Entered into showing locations");
            MarkerOptions markerOptions = new MarkerOptions();
            double lat = nearbyPlacesList.get(i).getLatitude();
            double lng = nearbyPlacesList.get(i).getLongitude();
            String placeName = nearbyPlacesList.get(i).getPlace();
            LatLng latLng = new LatLng(lat, lng);
            LatLng current = new LatLng(loc.getLatitude(), loc.getLongitude());
            markerOptions.position(latLng);
            markerOptions.title(placeName);

            Distances pembanding = getNearestDistance(loc.getLatitude(), loc.getLongitude(), nearbyPlacesList);
            System.out.println("lokasi = "+nearbyPlacesList.get(i).getPlace());
            System.out.println("terdekat = "+pembanding.getName());
            if (nearbyPlacesList.get(i).getPlace().equals(pembanding.getName())){
                System.out.println("masuk if");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }else{
                System.out.println("masuk else");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }
            mMap.addMarker(markerOptions);

            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }

    private static ArrayList<Distances> getDistance(double cuLat, double curLong, ArrayList<Coordinates> coord) {
        ArrayList<Distances> distances = new ArrayList<>();

        for (int i=0;i<coord.size();i++){
            distances.add(new Distances(coord.get(i).getPlace(),(Math.sqrt((coord.get(i).getLatitude() - cuLat) * (coord.get(i).getLatitude() - cuLat) + (coord.get(i).getLongitude() - curLong) * (coord.get(i).getLongitude() - curLong)))));
        }


        return distances;
    }

    private static Distances getNearestDistance(double curLat, double curLong, ArrayList<Coordinates> coord) {
        ArrayList<Distances>distances = getDistance(curLat, curLong, coord);
        Distances nearest = new Distances("",Double.MAX_VALUE);
        for(int i=0;i<distances.size();i++) {
            if (nearest.getDistances() > distances.get(i).getDistances()) {
                nearest.setDistances(distances.get(i).getDistances());
                nearest.setName(distances.get(i).getName());
            }
        }

        return nearest;
    }
}