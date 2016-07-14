package uk.co.jofaircloth.dovesguide.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import uk.co.jofaircloth.dovesguide.GuideItemEnum;
import uk.co.jofaircloth.dovesguide.R;
import uk.co.jofaircloth.dovesguide.helper.GuideItem;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class GuideProvider extends ContentProvider {

	private final String TAG = "GuideProvider.java";
	private GuideOpenHelper mDb;
	
	private static final String DB_NAME = "doveguide.db";
	private static final int DB_VERSION = 1;

	public static final String AUTHORITY = "uk.co.jofaircloth.dovesguide.dal.GuideProvider";
	
	//private static final String GUIDE_BASE_PATH = "dove_guide";
	public static final Uri GUIDE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/dove_guide");
	public static final Uri VISITED_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/visited");

	public static final String GUIDE_SINGLE_ITEM_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/dove_guide";
	public static final String GUIDE_MULTIPLE_ITEMS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/dove_guide";
	public static final String VISITED_SINGLE_ITEM_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/visited";
	public static final String VISITED_MULTIPLE_ITEMS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/visited";
	
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		uriMatcher.addURI(AUTHORITY, "dove_guide/", QueryType.GUIDE_FULL);
		uriMatcher.addURI(AUTHORITY, "dove_guide/#", QueryType.GUIDE_ID);
		uriMatcher.addURI(AUTHORITY, "dove_guide/location/*", QueryType.GUIDE_LOCATION);
		uriMatcher.addURI(AUTHORITY, "dove_guide/county", QueryType.GUIDE_COUNTY);
		uriMatcher.addURI(AUTHORITY, "dove_guide/filter/*", QueryType.GUIDE_WHERE);
		uriMatcher.addURI(AUTHORITY, "dove_guide/*", QueryType.GUIDE_WHERE);
	}

	@Override
	public String getType(Uri uri) {
		final int uriType = uriMatcher.match(uri);
		switch (uriType) {
			case QueryType.GUIDE_FULL:
			case QueryType.GUIDE_LOCATION:
			case QueryType.GUIDE_WHERE:
				return GUIDE_MULTIPLE_ITEMS_MIME_TYPE;
			case QueryType.GUIDE_ID:
				return GUIDE_SINGLE_ITEM_MIME_TYPE;
			case QueryType.VISITED_DATA:
				return VISITED_MULTIPLE_ITEMS_MIME_TYPE;
			default:
				return null;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		getContext().getContentResolver().notifyChange(uri, null);
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		throw new UnsupportedOperationException();
//		getContext().getContentResolver().notifyChange(uri, null);
//		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		getContext().getContentResolver().notifyChange(uri, null);
		return 0;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(GuideContract.Guide.TABLE_NAME);

		switch (uriMatcher.match(uri)) {
		case QueryType.GUIDE_FULL:
			Log.d(TAG, "getting FULL_GUIDE");
			Log.d(TAG, sortOrder);
			if (selectionArgs != null) {
				Log.d(TAG, selectionArgs[0]);
			}
			queryBuilder.appendWhere(GuideContract.Guide.PLACE + "!= 'Place'");
			return getGuide(queryBuilder);
			//break;
		case QueryType.GUIDE_LOCATION:
			queryBuilder.setTables(GuideContract.Guide.TABLE_NAME);
			String value = uri.getLastPathSegment();
			Log.d(TAG, "getting LOCATION: " + value);
			String[] coords = value.split(",");
			double lat = Double.parseDouble(coords[0]);
			double lng = Double.parseDouble(coords[1]);
			double latMin = lat - 0.2, latMax = lat + 0.2;
			double lngMin = lng - 0.2, lngMax = lng + 0.2;
//				String sql;
//				SELECT *, 3956 * 2 * ASIN(SQRT(POWER(SIN((51 - Lat) * pi()/180/2), 2) + COS(51 * pi()/180) * COS(Lat * pi()/180) * POWER (SIN (( 0 - Long) * pi() / 180 / 2), 2))) AS Distance
//				FROM dove_guide
//				sql = "SELECT *, ((" + lat + " - CAST(" + GuideItemEnum.LAT + " AS NUMERIC)) * (" + lat + " - CAST(" + GuideItemEnum.LAT + " AS NUMERIC)) + (" + lng + " - CAST(" + GuideItemEnum.LONG + " AS NUMERIC)) * (" + lng + " - CAST(" + GuideItemEnum.LONG + " AS NUMERIC))) AS Distance FROM dove_guide WHERE CAST(" + GuideItemEnum.LAT + " AS NUMERIC) BETWEEN " + latMin + " AND " + latMax + " AND CAST(" + GuideItemEnum.LONG.getColumnName() + " AS NUMERIC) BETWEEN " + lngMin + " AND " + lngMax;
//				Log.d(TAG, "LOC sql: " + sql);
//				return db.getReadableDatabase().rawQuery(sql, null);
			
			queryBuilder.appendWhere("CAST(" + GuideItemEnum.LAT + " AS NUMERIC) BETWEEN " + latMin + " AND " + latMax + " AND CAST(" + GuideItemEnum.LONG.getColumnName() + " AS NUMERIC) BETWEEN " + lngMin + " AND " + lngMax);
			return getLocation();
			//break;
		case QueryType.GUIDE_COUNTY:
			break;
//			queryBuilder.setTables(GuideContract.Guide.TABLE_NAME);
//			return mDb.getReadableDatabase().rawQuery("SELECT 0 AS _id, '' AS Country, '[Any]' AS County, 0 AS ord UNION SELECT DISTINCT 0 AS _id, Country, CASE Country WHEN '' THEN '' ELSE Country || ': ' END || County AS County, 1 AS ord FROM dove_guide WHERE County != '' ORDER BY ord, Country, County", null);
		case QueryType.GUIDE_WHERE:
			queryBuilder.setTables(GuideContract.Guide.TABLE_NAME);
			String value1 = uri.getLastPathSegment();
			queryBuilder.appendWhere(value1);
			break;
		case QueryType.GUIDE_ID:
			queryBuilder.setTables(GuideContract.Guide.TABLE_NAME);
			queryBuilder.appendWhere(GuideContract.Guide._ID + "=" + uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown Uri - GuideProvider.query()");
		}

		Cursor c = queryBuilder.query(mDb.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	private Cursor getGuide(SQLiteQueryBuilder qb) {
		return null;		
	}
	private Cursor getLocation() { 
		return null;
	}

	@Override
	public boolean onCreate() {
		mDb = new GuideOpenHelper(getContext());
		return true;
	}
	
	private static class GuideOpenHelper extends SQLiteOpenHelper {

		private final Context mHelperContext;
		private SQLiteDatabase mHelperDb;

		private static final String GUIDE_TABLE = "guide";
		private static final String GUIDE_CREATE = "CREATE TABLE "
				+ GUIDE_TABLE + " (" + GuideContract.Guide._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ GuideContract.Guide.DOVE_ID + " TEXT NOT NULL, "
				+ GuideContract.Guide.NATIONAL_GRID_REF + " TEXT NOT NULL, "
				+ GuideContract.Guide.SN_LAT + " TEXT NOT NULL, "
				+ GuideContract.Guide.SN_LONG + " TEXT NOT NULL, "
				+ GuideContract.Guide.POSTCODE + " TEXT NOT NULL, "
				+ GuideContract.Guide.TOWER_BASE + " TEXT NOT NULL, "
				+ GuideContract.Guide.COUNTY + " TEXT NOT NULL, "
				+ GuideContract.Guide.COUNTRY + " TEXT NOT NULL, "
				+ GuideContract.Guide.ISO3166_CODE + " TEXT NOT NULL, "
				+ GuideContract.Guide.DIOCESE + " TEXT NOT NULL, "
				+ GuideContract.Guide.PLACE + " TEXT NOT NULL, "
				+ GuideContract.Guide.PLACE_2 + " TEXT NOT NULL, "
				+ GuideContract.Guide.PLACE_CL + " TEXT NOT NULL, "
				+ GuideContract.Guide.DEDICN + " TEXT NOT NULL, "
				+ GuideContract.Guide.BELLS + " TEXT NOT NULL, "
				+ GuideContract.Guide.WEIGHT + " TEXT NOT NULL, "
				+ GuideContract.Guide.APP + " TEXT NOT NULL, "
				+ GuideContract.Guide.NOTE + " TEXT NOT NULL, "
				+ GuideContract.Guide.HZ + " TEXT NOT NULL, "
				+ GuideContract.Guide.DETAILS + " TEXT NOT NULL, "
				+ GuideContract.Guide.GROUND_FLOOR + " TEXT NOT NULL, "
				+ GuideContract.Guide.TOILET + " TEXT NOT NULL, "
				+ GuideContract.Guide.UR + " TEXT NOT NULL, "
				+ GuideContract.Guide.PD_NO + " TEXT NOT NULL, "
				+ GuideContract.Guide.PRACTICE_NIGHT + " TEXT NOT NULL, "
				+ GuideContract.Guide.PST + " TEXT NOT NULL, "
				+ GuideContract.Guide.PRXF + " TEXT NOT NULL, "
				+ GuideContract.Guide.OVERHAUL_YEAR + " TEXT NOT NULL, "
				+ GuideContract.Guide.CONTRACTOR + " TEXT NOT NULL, "
				+ GuideContract.Guide.TUNE_YEAR + " TEXT NOT NULL, "
				+ GuideContract.Guide.EXTRA_INFO + " TEXT NOT NULL, "
				+ GuideContract.Guide.WEB_PAGE + " TEXT NOT NULL, "
				+ GuideContract.Guide.UPDATED + " TEXT NOT NULL, "
				+ GuideContract.Guide.AFFILIATIONS + " TEXT NOT NULL, "
				+ GuideContract.Guide.ALT_NAME + " TEXT NOT NULL, "
				+ GuideContract.Guide.SIMULATOR + " TEXT NOT NULL, "
				+ GuideContract.Guide.LAT + " TEXT NOT NULL, "
				+ GuideContract.Guide.LONG + " TEXT NOT NULL" + ");";

		GuideOpenHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			mHelperContext = context;
			getWritableDatabase();
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			mHelperDb = db;
			mHelperDb.execSQL(GUIDE_CREATE);
			loadGuide();
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("GuideDatabase", "Upgrading database from version "
					+ oldVersion + " to " + newVersion
					+ ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + GUIDE_TABLE);
			onCreate(db);
		}

		private void loadGuide() {
			new Thread(new Runnable() {
				public void run() {
					try {
						loadGuideData();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}).start();
		}

		private void loadGuideData() throws IOException {
			Log.d("GuideDatabase", "Loading guide...");

			GuideItem guide = new GuideItem(mHelperDb);

			long deleted = guide.deleteAll();
			if (deleted < 0) {
				return;
			} else {
				final Resources resources = mHelperContext.getResources();
				InputStream is = resources.openRawResource(R.raw.dove);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				// br = new BufferedReader(new
				// InputStreamReader(c.getAssets().open(fileName)));

				try {
					String line = "";
					String[] data;
					long id;
					while ((line = reader.readLine()) != null) {
						// split the line on \
						data = TextUtils.split(line, "\\\\");
						if (data.length <= 0 || data[0].equals("DoveID"))
							continue;
						id = guide.addItem(data);
						if (id < 0)
							Log.e("GuideDatabase", "Unable to add tower: "
									+ data);
					}
				} catch (IOException e) {
					Log.d("GuideDatabase", "ReadFile error: " + e.getMessage());
					e.printStackTrace();
				} finally {
					reader.close();
				}
			}

			Log.d("GuideDatabase", "DONE loading guide...");

		}

	}
	
}
