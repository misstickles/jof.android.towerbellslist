package uk.co.jofaircloth.dovesguide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import uk.co.jofaircloth.dovesguide.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocation {

	/**
	 * loop through each enabled provider (most accurate is last), and get last known location
	 * @param c
	 * @param a
	 * @return
	 */
	public static Location getLocation(Context c, Activity a) {
		final LocationManager locationManager = (LocationManager) a.getSystemService(Context.LOCATION_SERVICE);
		Location loc = null;
		
//		LocationListener locationListener = new LocationListener() {			
//			@Override
//			public void onStatusChanged(String provider, int status, Bundle extras) {
//			}			
//			@Override
//			public void onProviderEnabled(String provider) {
//			}			
//			@Override
//			public void onProviderDisabled(String provider) {
//			}			
//			@Override
//			public void onLocationChanged(Location location) {
				List<String> providers = locationManager.getProviders(true);
				
				for (int i = providers.size() - 1; i >= 0; i--) {
					loc = locationManager.getLastKnownLocation(providers.get(i));
					if (loc != null) break;
				}
				
				SimpleDateFormat fmt = new SimpleDateFormat("HH:mm E dd/MM/yyyy", Locale.UK);
				String date = fmt.format(new Date(loc.getTime()));
				Toast.makeText(c, String.format("Location: (%s, %s) [%s, %s UTC]", Utils.roundToDps(loc.getLatitude(), 3), Utils.roundToDps(loc.getLongitude(), 3), loc.getProvider().toUpperCase(), date), Toast.LENGTH_LONG).show();
//			}
//		};
//		
//		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

		return loc;
	}
	
	public static double getDistance(double lat1, double long1, double lat2, double long2) {

		// Compute spherical coordinates
		double rho = 3960.0; // earth diameter in miles
		
		// convert latitude and longitude to spherical coordinates in radians
		// phi = 90 - latitude
		double phi1 = (90.0 - lat1) * Math.PI / 180.0;
		double phi2 = (90.0 - lat2) * Math.PI / 180.0;

		// theta = longitude
		double theta1 = long1 * Math.PI / 180.0;
		double theta2 = long2 * Math.PI / 180.0;

		// compute spherical distance from spherical coordinates
		// arc length = \arccos(\sin\phi\sin\phi'\cos(\theta-\theta') + \cos\phi\cos\phi')
		// distance = rho times arc length
		double d = rho * Math.acos(Math.sin(phi1) * Math.sin(phi2) * Math.cos(theta1 - theta2) + Math.cos(phi1) * Math.cos(phi2));

		return d; // miles 1.609344 * d // km
	}

}
