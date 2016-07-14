//package uk.co.jofaircloth.dovesguide;
//
//import uk.co.jofaircloth.dovesguide.dal.DbGuide;
//import uk.co.jofaircloth.dovesguide.dal.GuideDatabase;
//import uk.co.jofaircloth.dovesguide.dal.GuideProvider;
//import android.app.Activity;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.ListFragment;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.CursorLoader;
//import android.support.v4.content.Loader;
//import android.support.v4.widget.CursorAdapter;
//import android.support.v4.widget.SimpleCursorAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//
//public class AzBrowseFragment2 extends ListFragment {
//
//	//private static final int GUIDE_LIST_LOADER = 0x01;
//	private SimpleCursorAdapter adapter;
//	private DbGuide db;
//	
//	public interface OnTowerSelectedListener {
//		public void onTowerSelected(Cursor selected);
//	}
//	private OnTowerSelectedListener towerSelectedListener;
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		db = new DbGuide(getActivity().getApplicationContext());
//		db.open();
//		Cursor c = db.getListItems(9);
//		getActivity().startManagingCursor(c);
//		
//		String[] uiBindFrom = { "ListView", GuideDatabase.PLACE, GuideDatabase.COUNTY, GuideDatabase.COUNTRY, GuideDatabase.ID };
//		int [] uiBindTo = { R.id.az_browse_text, R.id.az_place_name_text, R.id.az_county_text, R.id.az_country_text };
//
////		getLoaderManager().initLoader(GUIDE_LIST_LOADER, null, this);
//		adapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), 
//				R.layout.az_browse_item_layout, c, uiBindFrom, uiBindTo, 0);
//
//		setListAdapter(adapter);
//		getListView().setFastScrollEnabled(true);
//
//		//setHasOptionsMenu(true);
//	}
//	
//	@Override
//	public void onListItemClick(ListView l, View v, int position, long id) {
//		String[] projection = GuideItemEnum.getColumnList(); // { GuideDatabase.ID, GuideDatabase.DOVE_ID, GuideDatabase.PLACE, GuideDatabase.WEIGHT };
//		Cursor cursor = getActivity().getContentResolver().query(
//				Uri.withAppendedPath(GuideProvider.CONTENT_URI, String.valueOf(id)),
//				projection, null, null, null);
//		cursor.moveToFirst();
//
//		towerSelectedListener.onTowerSelected(cursor);
//		cursor.close();
//	}
//	
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		try {
//			towerSelectedListener = (OnTowerSelectedListener) activity;
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString() + " must implement OnTowerSelectedListener");
//		}
//	}
//
////	@Override
////	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////		if (container == null) return null;
////		
////		return (LinearLayout) inflater.inflate(R.layout.az_browse_layout, container, false);
////	}
//
//}
