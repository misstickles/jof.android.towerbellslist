package uk.co.jofaircloth.dovesguide;

import uk.co.jofaircloth.dovesguide.helper.GuideItem;
import uk.co.jofaircloth.dovesguide.helper.TowerData;
import uk.co.jofaircloth.dovesguide.utils.Utils;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class CustomCursorAdapter extends SimpleCursorAdapter { //CursorAdapter {

	private Location currentLocation;
	private LayoutInflater inflater;
	private Context c;

	public CustomCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags, Location loc) {
		super(context, layout, c, from, to, flags);	
		this.currentLocation = loc;
		this.inflater = LayoutInflater.from(context);
	}

	public CustomCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);	
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
		c = context;
		if (holder == null) {
			holder = new ViewHolder();
			holder.tower = (TextView) view.findViewById(R.id.browse_tower_text);
			holder.distance = (TextView) view.findViewById(R.id.browse_distance_text);
			holder.practiceNight = (TextView) view.findViewById(R.id.browse_practice_night_text);
			holder.noBells = (TextView) view.findViewById(R.id.browse_no_bells_text);
			holder.weight = (TextView) view.findViewById(R.id.browse_weight_text);

			view.setTag(holder);
		}

		TowerData itm = TowerData.getTowerData(cursor);

		holder.tower.setText(itm.toString());		
		holder.noBells.setText(itm.getBellNumber());
		holder.practiceNight.setText(itm.getPracticeDetails());
		holder.weight.setText(itm.getBellWeight());

		if (currentLocation != null) {
			double lng = cursor.getDouble(cursor.getColumnIndex(GuideItemEnum.LONG.getColumnName()));
			double lat = cursor.getDouble(cursor.getColumnIndex(GuideItemEnum.LAT.getColumnName()));
	
			double distance = MyLocation.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(), lat, lng);
			double dist = Utils.roundToDps(distance, 1);
			holder.distance.setText(String.format(" [%s mi]", dist));
		}
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return inflater.inflate(R.layout.browse_item_layout, parent, false);
	}

	private static class ViewHolder {
		TextView tower;
		TextView distance;
		TextView practiceNight;
		TextView noBells;
		TextView weight;
	}
}
