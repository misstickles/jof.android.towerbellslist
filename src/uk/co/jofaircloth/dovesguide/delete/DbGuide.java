//package uk.co.jofaircloth.dovesguide.delete;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//
//public class DbGuide extends DbAdapter implements DbConstants {
//
//	private final Context context;
//	private DbAdapter dbAdapter;
//	private SQLiteDatabase db;
//
//	public static final String LIST_VIEW = "ListView";
//	
//	public DbGuide(Context context) {
//		super(context);
//		this.context = context;
//		open();
//	}
//
//	public DbAdapter open() throws SQLException {
//		dbAdapter = new DbAdapter(context);
//		db = dbAdapter.getWritableDatabase();
//		return this;
//	}
//	public void close() throws SQLException {
//		dbAdapter.close();
//		db.close();
//	}
//
//	public long deleteAll() {
//		return db.delete(DbAdapter.TABLE_NAME, null, null);
//	}
//	
//	public boolean deleteItem(GuideItem itm) {
//		long execResult = db.delete(DbAdapter.TABLE_NAME, DbAdapter.DOVE_ID + "='" + itm + "'", null);
//		if (execResult > -1) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public GuideItem getItemByDoveId(String doveId) {
//		String sql = "SELECT * FROM " +	TABLE_NAME + 
//		" WHERE " + DOVE_ID + " = '" + doveId + "'" +  
//		" ORDER BY " + PLACE;
//		Cursor cursor = db.rawQuery(sql, null);
//		if (cursor != null) 
//			cursor.moveToFirst();
//		
//		return DbUtils.getItem(cursor);
//	}
//	public GuideItem getItemById(int id) {
//		String sql = "SELECT * FROM " +	TABLE_NAME + 
//		" WHERE " + _ID + " = " + id + 
//		" ORDER BY " + PLACE;
//		Cursor cursor = db.rawQuery(sql, null);
//		if (cursor != null) 
//			cursor.moveToFirst();
//		
//		return DbUtils.getItem(cursor);
//	}
//	public Cursor getListItems(int id) {
//		String sql = "SELECT Place + ', ' + County + ', ' + Country AS ListView FROM " + TABLE_NAME + 
//		" ";
//		Cursor cursor = db.rawQuery(sql, null);
//		if (cursor != null) {
//			cursor.moveToNext();
//		}
//		return cursor;
//	}
//	public List<GuideItem> getItemsByCol(String column, String value) {
//		List<GuideItem> lst = new ArrayList<GuideItem>();
//		String sql = "SELECT * FROM " +	TABLE_NAME + 
//		" WHERE " + column + " = " + value + 
//		" ORDER BY " + PLACE + ", " + COUNTRY;
//		Cursor cursor = db.rawQuery(sql, null);
//		if (cursor.moveToFirst()) {
//			do {
//				DbUtils.getItem(cursor);
//			} while (cursor.moveToNext());
//		}
//		
//		return lst;
//	}
//	public int getItemCount() {
//		String count = "SELECT * FROM " + TABLE_NAME;
//		Cursor cursor = db.rawQuery(count, null);
//		cursor.close();
//		
//		return cursor.getCount();
//	}
//	
//	public List<GuideItem> getNearbyLocations(int distance) {
//		// TODO: get lat/lng offset for distance
//		// 1 deg = 69.047 miles = 111.12 km
//		// cos(lat * (pi/180))
//		
//		/*
//		# Distances are measured in miles.
//		# Longitudes and latitudes are measured in degrees.
//		# Earth is assumed to be perfectly spherical.
//
//		earth_radius = 3960.0
//		degrees_to_radians = math.pi/180.0
//		radians_to_degrees = 180.0/math.pi
//
//		def change_in_latitude(miles):
//		    "Given a distance north, return the change in latitude."
//		    return (miles/earth_radius)*radians_to_degrees
//
//		def change_in_longitude(latitude, miles):
//		    "Given a latitude and a distance west, return the change in longitude."
//		    # Find the radius of a circle around the earth at given latitude.
//		    r = earth_radius*math.cos(latitude*degrees_to_radians)
//		    return (miles/r)*radians_to_degrees
//		*/
//
//		float lat = 51.12429f;
//		float lng = -0.00599f;
//		float minLat = lat - 20f, maxLat = lat + 20f;
//		float minLng = lng - 20f, maxLng = lng + 20f;
//		
//		List<GuideItem> lst = new ArrayList<GuideItem>();
//		String sql = "SELECT * FROM " +	TABLE_NAME + 
//		" WHERE " + LAT + " BETWEEN " + maxLat + " AND " + minLat +
//		" AND " + LONG + " BETWEEN " + maxLng + " AND " + minLng;
//
//		Cursor cursor = db.rawQuery(sql, null);
//		if (cursor.moveToFirst()) {
//			do {
//				DbUtils.getItem(cursor);
//			} while (cursor.moveToNext());
//		}
//		
//		return lst;
//	}
//
//	/**
//	 * Insert a new record into the Dove's guide database
//	 */
//	public long addItem(String[] data) {
//		TowerData itm = getGuideItem(data);
//
//		ContentValues values = new ContentValues();
//		values.put(GuideContract.Guide.DOVE_ID, itm.DOVE_ID);
//		values.put(GuideContract.Guide.NATIONAL_GRID_REF, itm.NATIONAL_GRID_REF);
//		values.put(GuideContract.Guide.SN_LAT, itm.SN_LAT);
//		values.put(GuideContract.Guide.SN_LONG, itm.SN_LONG);
//		values.put(GuideContract.Guide.POSTCODE, itm.POSTCODE);
//		values.put(GuideContract.Guide.TOWER_BASE, itm.TOWER_BASE);
//		values.put(GuideContract.Guide.COUNTY, itm.COUNTY);
//		values.put(GuideContract.Guide.COUNTRY, itm.COUNTRY);
//		values.put(GuideContract.Guide.ISO3166_CODE, itm.ISO3166_CODE);
//		values.put(GuideContract.Guide.DIOCESE, itm.DIOCESE); 
//		values.put(GuideContract.Guide.PLACE, itm.PLACE);
//		values.put(GuideContract.Guide.PLACE_2, itm.PLACE_2);
//		values.put(GuideContract.Guide.PLACE_CL, itm.PLACE_CL);
//		values.put(GuideContract.Guide.DEDICN, itm.DEDICN);
//		values.put(GuideContract.Guide.BELLS, itm.BELLS);
//		values.put(GuideContract.Guide.WEIGHT, itm.WEIGHT);
//		values.put(GuideContract.Guide.APP, itm.APP);
//		values.put(GuideContract.Guide.NOTE, itm.NOTE);
//		values.put(GuideContract.Guide.HZ, itm.HZ);
//		values.put(GuideContract.Guide.DETAILS, itm.DETAILS);
//		values.put(GuideContract.Guide.GROUND_FLOOR, itm.GROUND_FLOOR);
//		values.put(GuideContract.Guide.TOILET, itm.TOILET);
//		values.put(GuideContract.Guide.UR, itm.UR);
//		values.put(GuideContract.Guide.PD_NO, itm.PD_NO);
//		values.put(GuideContract.Guide.PRACTICE_NIGHT, itm.PRACTICE_NIGHT);
//		values.put(GuideContract.Guide.PST, itm.PST);
//		values.put(GuideContract.Guide.PRXF, itm.PRXF);
//		values.put(GuideContract.Guide.OVERHAUL_YEAR, itm.OVERHAUL_YEAR);
//		values.put(GuideContract.Guide.CONTRACTOR, itm.CONTRACTOR);
//		values.put(GuideContract.Guide.TUNE_YEAR, itm.TUNE_YEAR);
//		values.put(GuideContract.Guide.EXTRA_INFO, itm.EXTRA_INFO);
//		values.put(GuideContract.Guide.WEB_PAGE, itm.WEB_PAGE);
//		values.put(GuideContract.Guide.UPDATED, itm.UPDATED);
//		values.put(GuideContract.Guide.AFFILIATIONS, itm.AFFILIATIONS);
//		values.put(GuideContract.Guide.ALT_NAME, itm.ALT_NAME);
//		values.put(GuideContract.Guide.SIMULATOR, itm.SIMULATOR);
//		values.put(GuideContract.Guide.LAT, itm.LAT);
//		values.put(GuideContract.Guide.LONG, itm.LONG);
//
//		return db.insert(GuideContract.Guide.TABLE_NAME, null, values);
//	}
//
//
//	/**
//	 * Insert a new record into the Dove's guide database
//	 */
//	public boolean addItem(String[] data) {
//		getg
//		ContentValues values = new ContentValues();
//		values.put(DbAdapter.DOVE_ID, itm.DOVE_ID);
//		values.put(DbAdapter.NATIONAL_GRID_REF, itm.NATIONAL_GRID_REF);
//		values.put(DbAdapter.SN_LAT, itm.SN_LAT);
//		values.put(DbAdapter.SN_LONG, itm.SN_LONG);
//		values.put(DbAdapter.POSTCODE, itm.POSTCODE);
//		values.put(DbAdapter.TOWER_BASE, itm.TOWER_BASE);
//		values.put(DbAdapter.COUNTY, itm.COUNTY);
//		values.put(DbAdapter.COUNTRY, itm.COUNTRY);
//		values.put(DbAdapter.ISO3166_CODE, itm.ISO3166_CODE);
//		values.put(DbAdapter.DIOCESE, itm.DIOCESE); 
//		values.put(DbAdapter.PLACE, itm.PLACE);
//		values.put(DbAdapter.PLACE_2, itm.PLACE_2);
//		values.put(DbAdapter.PLACE_CL, itm.PLACE_CL);
//		values.put(DbAdapter.DEDICN, itm.DEDICN);
//		values.put(DbAdapter.BELLS, itm.BELLS);
//		values.put(DbAdapter.WEIGHT, itm.WEIGHT);
//		values.put(DbAdapter.APP, itm.APP);
//		values.put(DbAdapter.NOTE, itm.NOTE);
//		values.put(DbAdapter.HZ, itm.HZ);
//		values.put(DbAdapter.DETAILS, itm.DETAILS);
//		values.put(DbAdapter.GROUND_FLOOR, itm.GROUND_FLOOR);
//		values.put(DbAdapter.TOILET, itm.TOILET);
//		values.put(DbAdapter.UR, itm.UR);
//		values.put(DbAdapter.PD_NO, itm.PD_NO);
//		values.put(DbAdapter.PRACTICE_NIGHT, itm.PRACTICE_NIGHT);
//		values.put(DbAdapter.PST, itm.PST);
//		values.put(DbAdapter.PRXF, itm.PRXF);
//		values.put(DbAdapter.OVERHAUL_YEAR, itm.OVERHAUL_YEAR);
//		values.put(DbAdapter.CONTRACTOR, itm.CONTRACTOR);
//		values.put(DbAdapter.TUNE_YEAR, itm.TUNE_YEAR);
//		values.put(DbAdapter.EXTRA_INFO, itm.EXTRA_INFO);
//		values.put(DbAdapter.WEB_PAGE, itm.WEB_PAGE);
//		values.put(DbAdapter.UPDATED, itm.UPDATED);
//		values.put(DbAdapter.AFFILIATIONS, itm.AFFILIATIONS);
//		values.put(DbAdapter.ALT_NAME, itm.ALT_NAME);
//		values.put(DbAdapter.SIMULATOR, itm.SIMULATOR);
//		values.put(DbAdapter.LAT, itm.LAT);
//		values.put(DbAdapter.LONG, itm.LONG);
//
//		long execResult = db.insert(TABLE_NAME, null, values);
//		if (execResult > -1) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//}
