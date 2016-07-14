package uk.co.jofaircloth.dovesguide;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import uk.co.jofaircloth.dovesguide.dal.GuideProvider;
import uk.co.jofaircloth.dovesguide.utils.Utils;
import android.app.Activity;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class NearbyFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final int GUIDE_LIST_LOADER = 0x01;
	private static final String TAG = "NewabyFragment.java";
	private CustomCursorAdapter adapter;
	private OnTowerSelectedListener towerSelectedListener;
	private Location loc;
	private View vw;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) return null;

		vw = inflater.inflate(R.layout.nearby_layout, container, false);
		return vw;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loc = MyLocation.getLocation(getActivity().getApplicationContext(), getActivity());

		String[] uiBindFrom = { GuideItemEnum.PLACE.getColumnName(), GuideItemEnum.LAT.getColumnName(), GuideItemEnum.LONG.getColumnName() };
		int [] uiBindTo = { R.id.browse_tower_text };

		getLoaderManager().initLoader(GUIDE_LIST_LOADER, null, this);
		adapter = new CustomCursorAdapter(getActivity().getApplicationContext(), 
				R.layout.browse_item_layout, null, uiBindFrom, uiBindTo,
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER, loc);

		setListAdapter(adapter);
		
		//setHasOptionsMenu(true);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String[] projection = null; //GuideItemEnum.getColumnList();
		Cursor cursor = getActivity().getContentResolver().query(
				Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI,
						String.valueOf(id)),
				projection, null, null, null);
		cursor.moveToFirst();

		towerSelectedListener.onTowerSelected(cursor);
		cursor.close();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			towerSelectedListener = (OnTowerSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnTowerSelectedListener");
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = { 
				GuideItemEnum.ID.getColumnName(),
				GuideItemEnum.PLACE.getColumnName(),
				GuideItemEnum.PLACE_2.getColumnName(),
				GuideItemEnum.COUNTY.getColumnName(),
				GuideItemEnum.COUNTRY.getColumnName(),
				GuideItemEnum.LAT.getColumnName(),
				GuideItemEnum.LONG.getColumnName(),
				GuideItemEnum.BELLS.getColumnName(),
				GuideItemEnum.WEIGHT.getColumnName(),
				GuideItemEnum.PRACTICE_NIGHT.getColumnName(),
				GuideItemEnum.PST.getColumnName(),
				GuideItemEnum.DEDICN.getColumnName(),
				"(((" + loc.getLatitude() + " - Lat) * (" + loc.getLatitude() + " - Lat)) + ((" + loc.getLongitude() + " - Long) * (" + loc.getLongitude() + " - Long))) AS Distance",
				//"((" + loc.getLatitude() + " - CAST(" + GuideItemEnum.LAT + " AS NUMERIC)) * (" + loc.getLatitude() + " - CAST(" + GuideItemEnum.LAT + " AS NUMERIC)) + (" + loc.getLongitude() + " - CAST(" + GuideItemEnum.LONG + " AS NUMERIC)) * (" + loc.getLongitude() + " - CAST(" + GuideItemEnum.LONG + " AS NUMERIC))) AS Distance",
		};

		CursorLoader cursorLoader = new CursorLoader(getActivity(),
				Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI,
						"location/" + loc.getLatitude() + "," + loc.getLongitude()),
						projection, null, null, Configuration.sortOrder);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// TODO: see where this is SUPPOSED to go!!
//		if (vw != null) {
//			StringBuilder name = new StringBuilder();
//			// get town
//			Geocoder geo = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
//			List<Address> addresses = null;
//			try {
//				if (loc != null) {
//					addresses = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
//				}
//			} catch (IOException e) {
//				Log.e(TAG, "Geocoder IOException: " + e.getMessage());
//			} catch (Exception e) {
//				Log.e(TAG, "Geocoder Exception: " + e.getMessage());
//			}
//			
//			if (addresses != null && addresses.size() > 0) {
//				Address a = addresses.get(0);
//				if (a.getLocality() != null) name.append(a.getLocality());
//				if (a.getAdminArea() != null) name.append(", " + a.getAdminArea());
//			}
//
//			Locale uk = Locale.UK;
//			SimpleDateFormat fmt = new SimpleDateFormat("HH:mm E dd/MM/yyyy", uk);
//			String date = fmt.format(new Date(loc.getTime()));
//			String info = String.format("Location: (%s, %s) %s\n[%s, %s UTC]", 
//					Utils.roundToDps(loc.getLatitude(), 3), 
//					Utils.roundToDps(loc.getLongitude(), 3), 
//					name, 
//					loc.getProvider().toUpperCase(uk), date);
//			((TextView)  vw.findViewById(R.id.nearby_last_location)).setText(info);
//		}

		adapter.swapCursor(cursor);
		getListView().setFastScrollEnabled(true);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}
	
	public void update() {
		getLoaderManager().restartLoader(GUIDE_LIST_LOADER, null, this);
	}

}
