///**
// * Usage
// * getWritableDatabase() / getReadableDatabase() -> return SQLiteDatabase obj
// * SQLiteDatabase.query() / SQLiteQueryBuilder for complex queries -> return Cursor
// */
//package uk.co.jofaircloth.dovesguide.dal;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//
//import com.example.android.notepad.NotePad;
//
//import uk.co.jofaircloth.dovesguide.GuideItemEnum;
//import uk.co.jofaircloth.dovesguide.R;
//import uk.co.jofaircloth.dovesguide.helper.GuideItem;
//import android.content.Context;
//import android.content.res.Resources;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteQueryBuilder;
//import android.net.Uri;
//import android.text.TextUtils;
//import android.util.Log;
//
//public class GuideDatabase {
//
//	private static final String TAG = "GuideDatabase.java";
//
//	private final GuideOpenHelper mDb;
//	private final Context mContext;
//	private static final HashMap<String, String> mColumnMap = buildColumnMap();
//	
//	public GuideDatabase(Context context) {
//		mDb = new GuideOpenHelper(context);
//		mContext = context;
//	}
//
//	/**
//	 * Builds a map for all columns that may be requested, which will be given
//	 * to the SQLiteQueryBuilder. This is a good way to define aliases for
//	 * column names, but must include all columns, even if the value is the key.
//	 * This allows the ContentProvider to request columns w/o the need to know
//	 * real column names and create the alias itself.
//	 */
//	private static HashMap<String, String> buildColumnMap() {
//		HashMap<String, String> map = new HashMap<String, String>();
////        // Maps "title" to "title"
////        sNotesProjectionMap.put(NotePad.Notes.COLUMN_NAME_TITLE, NotePad.Notes.COLUMN_NAME_TITLE);
////
////        // Maps "note" to "note"
////        sNotesProjectionMap.put(NotePad.Notes.COLUMN_NAME_NOTE, NotePad.Notes.COLUMN_NAME_NOTE);
//
//		return map;
//	}
//
//	// public Cursor getMatches() { return new Cursor; }
//
//	public Cursor getByDoveId(String doveId, String[] columns) {
//		String selection = "SELECT * FROM " + GuideContract.Guide.TABLE_NAME +
//				" WHERE " + GuideContract.Guide.DOVE_ID + " = '" + doveId + "'" +
//				" ORDER BY " + GuideContract.Guide.PLACE;
//		return query(selection, null, columns);
//		
////		String sql = "SELECT * FROM " +	TABLE_NAME + 
////		" WHERE " + DOVE_ID + " = '" + doveId + "'" +  
////		" ORDER BY " + PLACE;
////		Cursor cursor = db.rawQuery(sql, null);
////		if (cursor != null) 
////			cursor.moveToFirst();
////		
////		return DbUtils.getItem(cursor);
//
//	}
//	
//
//	private Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
//		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//
//		int uriType = uriMatcher.match(uri);
//		switch (uriType) {
//		case QueryType.GUIDE_FULL:
//			Log.d(TAG, "getting FULL_GUIDE");
//			Log.d(TAG, sortOrder);
//			if (selectionArgs != null) {
//				Log.d(TAG, selectionArgs[0]);
//			}
//			queryBuilder.appendWhere(GuideItemEnum.PLACE + "!= 'Place'");
//			break;
//		case QueryType.GUIDE_LOCATION:
//			String value = uri.getLastPathSegment();
//			Log.d(TAG, "getting LOCATION: " + value);
//			String[] coords = value.split(",");
//			double lat = Double.parseDouble(coords[0]);
//			double lng = Double.parseDouble(coords[1]);
//			double latMin = lat - 0.2, latMax = lat + 0.2;
//			double lngMin = lng - 0.2, lngMax = lng + 0.2;
////				String sql;
////				SELECT *, 3956 * 2 * ASIN(SQRT(POWER(SIN((51 - Lat) * pi()/180/2), 2) + COS(51 * pi()/180) * COS(Lat * pi()/180) * POWER (SIN (( 0 - Long) * pi() / 180 / 2), 2))) AS Distance
////				FROM dove_guide
////				sql = "SELECT *, ((" + lat + " - CAST(" + GuideItemEnum.LAT + " AS NUMERIC)) * (" + lat + " - CAST(" + GuideItemEnum.LAT + " AS NUMERIC)) + (" + lng + " - CAST(" + GuideItemEnum.LONG + " AS NUMERIC)) * (" + lng + " - CAST(" + GuideItemEnum.LONG + " AS NUMERIC))) AS Distance FROM dove_guide WHERE CAST(" + GuideItemEnum.LAT + " AS NUMERIC) BETWEEN " + latMin + " AND " + latMax + " AND CAST(" + GuideItemEnum.LONG.getColumnName() + " AS NUMERIC) BETWEEN " + lngMin + " AND " + lngMax;
////				Log.d(TAG, "LOC sql: " + sql);
////				return db.getReadableDatabase().rawQuery(sql, null);
//			
//			queryBuilder.appendWhere("CAST(" + GuideItemEnum.LAT + " AS NUMERIC) BETWEEN " + latMin + " AND " + latMax + " AND CAST(" + GuideItemEnum.LONG.getColumnName() + " AS NUMERIC) BETWEEN " + lngMin + " AND " + lngMax);
//			break;
//		case QueryType.GUIDE_COUNTY:
//			return mDb.getReadableDatabase().rawQuery("SELECT 0 AS _id, '' AS Country, '[Any]' AS County, 0 AS ord UNION SELECT DISTINCT 0 AS _id, Country, CASE Country WHEN '' THEN '' ELSE Country || ': ' END || County AS County, 1 AS ord FROM dove_guide WHERE County != '' ORDER BY ord, Country, County", null);
//		case QueryType.GUIDE_WHERE:
//			String value1 = uri.getLastPathSegment();
//			queryBuilder.appendWhere(value1);
//			break;
//		case QueryType.GUIDE_ID:
//			queryBuilder.appendWhere(GuideContract.Guide.DOVE_ID + "=" + uri.getLastPathSegment());
//			break;
//		default:
//			throw new IllegalArgumentException("Unknown Uri - GuideDatabase.query()");
//		}
//
//		queryBuilder.setTables(GuideContract.Guide.TABLE_NAME);
//		queryBuilder.setProjectionMap(mColumnMap);
//
//		Cursor c = queryBuilder.query(mDb.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
//		// Tells the Cursor what URI to watch, so it knows when its source data changes
//		c.setNotificationUri(mContext.getContentResolver(), uri);
//
//		if (c == null)
//			return null;
//		else if (!c.moveToFirst()) {
//			c.close();
//			return null;
//		}
//		return c;
//
//	}
//
//}
