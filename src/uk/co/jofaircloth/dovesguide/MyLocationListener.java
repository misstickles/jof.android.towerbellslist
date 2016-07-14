package uk.co.jofaircloth.dovesguide;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {

	private final static String TAG = "MyLocationListener.java";

	private double lat, lon;
	private String locationName, locationCoords;
	private Context context;

	public MyLocationListener(Context c) {
		context = c;
		Toast.makeText(context, "MyLocationListener constructor", Toast.LENGTH_SHORT).show();		
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
			Log.d(TAG, location.toString());
			Geocoder geo = new Geocoder(context, Locale.getDefault());
			List<Address> addresses = null;
			try {
				if (location != null)
					addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			} catch (IOException e) {
				Log.e(TAG, "Geocoder IOException: " + e.getMessage());
			} catch (Exception e) {
				Log.e(TAG, "Geocoder Exception: " + e.getMessage());
			}
			
			if (addresses != null && addresses.size() > 0) {
				Address a = addresses.get(0);
				StringBuilder name = new StringBuilder();
				StringBuilder coords = new StringBuilder();
				if (a.getLocality() != null) name.append(a.getLocality());
				if (a.getAdminArea() != null) name.append(", " + a.getAdminArea());
				try {
					coords.append("(");
					coords.append(a.getLatitude());
					coords.append(", ");
					coords.append(a.getLongitude());
					coords.append(")");
				} catch (IllegalStateException e) {
					Log.e(TAG, "lat/long error: " + e.getMessage());
				}
				locationName = name.toString();
				locationCoords = coords.toString();
			} else {
				locationName = "...";
				locationCoords = "(?, ?)";
			}
			Log.d(TAG, "changed: " + lat + "," + lon);
			Toast.makeText(context, "Location changed " + locationName, Toast.LENGTH_LONG).show();
		} else {}
	}

	@Override
	public void onProviderDisabled(String provider) {
	}
	@Override
	public void onProviderEnabled(String provider) {
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

//	private void subscribeToLocationUpdates(Context context) {
//		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//
//		Criteria criteria = new Criteria();
//		provider = lm.getBestProvider(criteria, false);
//		loc = lm.getLastKnownLocation(provider);
//
//		Geocoder geo = new Geocoder(context.getApplicationContext(), Locale.getDefault());
//		List<Address> addresses = null;
//		try {
//			if (loc != null)
//				addresses = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
//		} catch (IOException e) {
//			Log.e(TAG, "Geocoder IOException: " + e.getMessage());
//		} catch (Exception e) {
//			Log.e(TAG, "Geocoder Exception: " + e.getMessage());
//		}
//		
//		if (addresses != null && addresses.size() > 0) {
//			Address a = addresses.get(0);
//			StringBuilder name = new StringBuilder();
//			StringBuilder coords = new StringBuilder();
//			if (a.getLocality() != null) name.append(a.getLocality());
//			if (a.getAdminArea() != null) name.append(", " + a.getAdminArea());
//			try {
//				coords.append("(");
//				coords.append(a.getLatitude());
//				coords.append(", ");
//				coords.append(a.getLongitude());
//				coords.append(")");
//			} catch (IllegalStateException e) {
//				Log.e(TAG, "lat/long error: " + e.getMessage());
//			}
//			locationName = name.toString();
//			locationCoords = coords.toString();
//		} else {
//			locationName = "...";
//			locationCoords = "(?, ?)";
//		}
//	}

	public String getLocationCoords() {
		return locationCoords;
	}
	public String getLocationName() {
		return locationName;
	}
	
}
