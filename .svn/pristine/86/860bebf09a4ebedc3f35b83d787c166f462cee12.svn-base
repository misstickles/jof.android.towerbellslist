package uk.co.jofaircloth.dovesguide;

import uk.co.jofaircloth.dovesguide.dal.GuideProvider;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;

public class AzBrowseFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final int GUIDE_LIST_LOADER = 0x01;
	//private SimpleCursorAdapter adapter;
	private CustomCursorAdapter adapter;
	private OnTowerSelectedListener towerSelectedListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] uiBindFrom = { GuideItemEnum.PLACE.getColumnName() };
		int [] uiBindTo = { R.id.browse_tower_text };

		getLoaderManager().initLoader(GUIDE_LIST_LOADER, null, this);
		adapter = new CustomCursorAdapter(getActivity().getApplicationContext(),
				R.layout.browse_item_layout, null, uiBindFrom, uiBindTo,
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

		setListAdapter(adapter);
		//setHasOptionsMenu(true);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String[] projection = GuideItemEnum.getColumnList();
		Cursor cursor = getActivity().getContentResolver().query(
				Uri.withAppendedPath(GuideProvider.GUIDE_CONTENT_URI,
						String.valueOf(id)),
				projection, null, null, Configuration.sortOrder);
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
				"'' AS Distance",
		};

		CursorLoader cursorLoader = new CursorLoader(getActivity(), GuideProvider.GUIDE_CONTENT_URI,
				projection, null, null, Configuration.sortOrder);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
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

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		if (container == null) return null;
//		
//		return (LinearLayout) inflater.inflate(R.layout.az_browse_layout, container, false);
//	}

}
