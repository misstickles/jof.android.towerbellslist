package uk.co.jofaircloth.dovesguide;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import uk.co.jofaircloth.dovesguide.dal.GuideProvider;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SearchFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final int GUIDE_LIST_LOADER = 0x01;
	private static final String TAG = "SearchFragment.java";
	private CustomCursorAdapter adapter;
	private View vw;
	
	private OnSearchButtonClickedListener searchButtonClickedListener;
	private OnTowerLongClickListener towerLongClickListener;
	private OnTowerSelectedListener towerSelectedListener;

	protected Spinner noBellsSpinner;
	protected Spinner practiceNightSpinner;
	protected Spinner countySpinner;
	protected EditText placeText;
	protected InputMethodManager imm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) return null;

		vw = inflater.inflate(R.layout.search_layout, container, false);
		
		placeText = (EditText) vw.findViewById(R.id.search_text);
		placeText.addTextChangedListener(new OnTextChangedListener());

		noBellsSpinner = (Spinner) vw.findViewById(R.id.search_no_bells_spinner);
		ArrayAdapter<CharSequence> noBellsAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.no_bells, R.layout.item_layout);
//		noBellsAdapter.setDropDownViewResource(R.layout.item_layout);
		noBellsSpinner.setAdapter(noBellsAdapter);
		noBellsSpinner.setOnItemSelectedListener(new OnSelectionChangedListener());
		 
		practiceNightSpinner = (Spinner) vw.findViewById(R.id.search_practice_night_spinner);
		ArrayAdapter<CharSequence> practiceNightAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.practice_night, R.layout.item_layout);
//		practiceNightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		practiceNightSpinner.setAdapter(practiceNightAdapter);
		practiceNightSpinner.setOnItemSelectedListener(new OnSelectionChangedListener());
		
		countySpinner = (Spinner) vw.findViewById(R.id.search_county_spinner);
		Cursor c = getActivity().getContentResolver().query(
				Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI, "county"),
						null, null, null, null);
		c.moveToFirst();	
		
		SimpleCursorAdapter countyAdapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), 
				R.layout.item_layout, c, new String[] { "County" }, new int[] { R.id.item_text },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//		countyAdapter.setDropDownViewResource(R.layout.item_text);
		countySpinner.setAdapter(countyAdapter);
		countySpinner.setOnItemSelectedListener(new OnSelectionChangedListener());
		//c.close();

		imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(placeText.getWindowToken(), 0); // InputMethodManager.HIDE_IMPLICIT_ONLY);

		return vw;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] uiBindFrom = { GuideItemEnum.PLACE.getColumnName(), GuideItemEnum.LAT.getColumnName(), GuideItemEnum.LONG.getColumnName() };
		int [] uiBindTo = { R.id.browse_tower_text };

		getLoaderManager().initLoader(GUIDE_LIST_LOADER, null, this);
		adapter = new CustomCursorAdapter(getActivity().getApplicationContext(), 
				R.layout.browse_item_layout, null, uiBindFrom, uiBindTo,
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String[] projection = null; //GuideItemEnum.getColumnList();
		Cursor c = getActivity().getContentResolver().query(
				Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI,
						String.valueOf(id)),
				projection, null, null, null);
		c.moveToFirst();

		towerSelectedListener.onTowerSelected(c);
		c.close();
	}

	private class OnSelectionChangedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			Cursor c = getActivity().getContentResolver().query(
					Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI,
							getWhere()),
							null, null, null, Configuration.sortOrder);
			c.moveToFirst();

			searchButtonClickedListener.onSearchButtonClicked(c);
			c.close();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
		
	}

	private class OnTextChangedListener implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {
			Cursor c = getActivity().getContentResolver().query(
					Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI,
							getWhere()),
							null, null, null, Configuration.sortOrder);
			c.moveToFirst();

			searchButtonClickedListener.onSearchButtonClicked(c);
			c.close();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			towerSelectedListener = (OnTowerSelectedListener) activity;
			towerLongClickListener = (OnTowerLongClickListener) activity;
			searchButtonClickedListener = (OnSearchButtonClickedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnTowerSelectedListener");
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		OnItemLongClickListener longClick = new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				String[] projection = null; //GuideItemEnum.getColumnList();
				Cursor c = getActivity().getContentResolver().query(
						Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI,
								String.valueOf(id)),
						projection, null, null, null);
				c.moveToFirst();

				towerLongClickListener.onTowerLongClick(c);
				c.close();

				Toast.makeText(getActivity().getApplicationContext(), "Long press........", Toast.LENGTH_SHORT).show();
				return false;
			}
		};
		getListView().setOnItemLongClickListener(longClick);
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
				GuideItemEnum.DISTANCE.getColumnName(),
		};

		CursorLoader cursorLoader = new CursorLoader(getActivity(), 
				Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI, 
						getWhere()),
				projection, null, null, Configuration.sortOrder);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
		getListView().setFastScrollEnabled(true);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);
	}
	
	public void update() {
		getLoaderManager().restartLoader(GUIDE_LIST_LOADER, null, this);
	}

	private String getWhere() {
		StringBuilder where = new StringBuilder();
		try {
			String place = placeText.getText().toString();
			String bells = noBellsSpinner.getSelectedItem().toString();
			String practiceNight = practiceNightSpinner.getSelectedItem().toString();
			Cursor c = (Cursor) countySpinner.getSelectedItem();
			String county = "[Any]";
			if (c != null) county = c.getString(c.getColumnIndex("County"));

			if (!place.equals("")) {
				try {
					where.append(" AND (Place LIKE '" + URLEncoder.encode("%" + place + "%", "UTF-8") + "' OR Place2 LIKE '" + URLEncoder.encode("%" + place + "%", "UTF-8") + "' OR PlaceCL LIKE '" + URLEncoder.encode("%" + place + "%", "UTF-8") + "')");
				} catch (UnsupportedEncodingException e) {
					//e.printStackTrace();
				}
			}
			if (!bells.equals("[Any]")) where.append(" AND Bells = '" + bells + "'");
			if (!practiceNight.equals("[Any]")) where.append(" AND PracN = '" + practiceNight.substring(0, 3) + "'");
			if (!county.equals("[Any]")) {
				if (county.contains(":")) {
					county = county.split(":")[1].trim();
				}
				where.append(" AND County='" + county + "'");
			}
			Log.d(TAG, where.toString());
		} catch (NullPointerException np) {
			return "";
		}
		return where.toString().replaceFirst(" AND", "");
	}
} 
