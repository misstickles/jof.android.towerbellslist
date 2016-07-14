package uk.co.jofaircloth.dovesguide.delete;
//package uk.co.jofaircloth.dovesguide.delete;
//
//import uk.co.jofaircloth.dovesguide.utils.ReadFile;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Bundle;
//import android.util.Log;
//
//public class GuideDatabase extends SQLiteOpenHelper {
//
//	private static final String TAG = "GuideDatabase.java";
//	private static final String DB_NAME = "dovesguide.db";
//	private static final int DB_VERSION = 2;
//	
//	public interface Tables {
//		public static final String GUIDE = "dove_guide";
//		public static final String RUNG_AT = "rung_at";
//	}
//	
//	private Context context;
//
//	public static final String GUIDE_TABLE_NAME = "dove_guide";
//	public static final String VISITED_TABLE_NAME = "visited";
//
//	public static final String ID = "_id";
//	public static final String DOVE_ID = "DoveID";
//	public static final String NATIONAL_GRID_REF = "NG";
//	public static final String SN_LAT = "SNLat";
//	public static final String SN_LONG = "SNLong";
//	public static final String POSTCODE = "Postcode";
//	public static final String TOWER_BASE = "TowerBase";
//	public static final String COUNTY = "County";
//	public static final String COUNTRY = "Country";
//	public static final String ISO3166_CODE = "ISO3166code";
//	public static final String DIOCESE = "Diocese";
//	public static final String PLACE = "Place";
//	public static final String PLACE_2 = "Place2";
//	public static final String PLACE_CL = "PlaceCL";
//	public static final String DEDICN = "Dedicn";
//	public static final String BELLS = "Bells";
//	public static final String WEIGHT = "wt";
//	public static final String APP = "App";
//	public static final String NOTE = "Note";
//	public static final String HZ = "Hz";
//	public static final String DETAILS = "Details";
//	public static final String GROUND_FLOOR = "GF";
//	public static final String TOILET = "Toilet";
//	public static final String UR = "UR";
//	public static final String PD_NO = "PDNo";
//	public static final String PRACTICE_NIGHT = "PracN";
//	public static final String PST = "PSt";
//	public static final String PRXF = "PrXF";
//	public static final String OVERHAUL_YEAR = "OvhaulYr";
//	public static final String CONTRACTOR = "Contractor";
//	public static final String TUNE_YEAR = "TuneYr";
//	public static final String EXTRA_INFO = "ExtraInfo";
//	public static final String WEB_PAGE = "WebPage";
//	public static final String UPDATED = "Updated";
//	public static final String AFFILIATIONS = "Affiliations";
//	public static final String ALT_NAME = "AltName";
//	public static final String SIMULATOR = "Simulator";
//	public static final String LAT = "Lat";
//	public static final String LONG = "Long";
//
//	private static final String CREATE_GUIDE_TABLE = 
//		"CREATE TABLE " + Tables.GUIDE + " (" + 
//		"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//		DOVE_ID + " TEXT NOT NULL, " +
//		NATIONAL_GRID_REF + " TEXT NOT NULL, " +
//		SN_LAT + " TEXT NOT NULL, " +
//		SN_LONG + " TEXT NOT NULL, " +
//		POSTCODE + " TEXT NOT NULL, " +
//		TOWER_BASE + " TEXT NOT NULL, " +
//		COUNTY + " TEXT NOT NULL, " +
//		COUNTRY + " TEXT NOT NULL, " +
//		ISO3166_CODE + " TEXT NOT NULL, " +
//		DIOCESE  + " TEXT NOT NULL, " +
//		PLACE + " TEXT NOT NULL, " +
//		PLACE_2 + " TEXT NOT NULL, " +
//		PLACE_CL + " TEXT NOT NULL, " +
//		DEDICN + " TEXT NOT NULL, " +
//		BELLS + " TEXT NOT NULL, " +
//		WEIGHT + " TEXT NOT NULL, " +
//		APP + " TEXT NOT NULL, " +
//		NOTE + " TEXT NOT NULL, " +
//		HZ + " TEXT NOT NULL, " +
//		DETAILS + " TEXT NOT NULL, " +
//		GROUND_FLOOR + " TEXT NOT NULL, " +
//		TOILET + " TEXT NOT NULL, " +
//		UR + " TEXT NOT NULL, " +
//		PD_NO + " TEXT NOT NULL, " +
//		PRACTICE_NIGHT + " TEXT NOT NULL, " +
//		PST + " TEXT NOT NULL, " +
//		PRXF + " TEXT NOT NULL, " +
//		OVERHAUL_YEAR + " TEXT NOT NULL, " +
//		CONTRACTOR + " TEXT NOT NULL, " +
//		TUNE_YEAR + " TEXT NOT NULL, " +
//		EXTRA_INFO + " TEXT NOT NULL, " +
//		WEB_PAGE + " TEXT NOT NULL, " +
//		UPDATED + " TEXT NOT NULL, " +
//		AFFILIATIONS + " TEXT NOT NULL, " +
//		ALT_NAME + " TEXT NOT NULL, " +
//		SIMULATOR + " TEXT NOT NULL, " +
//		LAT + " TEXT NOT NULL, " +
//		LONG + " TEXT NOT NULL" +
//		");";
//
//	private static final String ALTER_GUIDE_TABLE = 
//		"DROP TABLE IF EXISTS " + GUIDE_TABLE_NAME;
//
//	public GuideDatabase(Context context) {
//		super(context, DB_NAME, null, DB_VERSION);
//		this.context = context; 
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//        Log.i(TAG, "Bootstrapping database version: " + DB_VERSION);
//
//		db.execSQL(CREATE_GUIDE_TABLE);
//		
////        db.execSQL("CREATE INDEX raw_contacts_contact_id_index ON " + Tables.RAW_CONTACTS + " (" +
////                RawContacts.CONTACT_ID +
////        ");");
//
//
//		// TODO: add data...
//		//addData(db);
////        ReadFile r = new ReadFile("dove.txt");
////        r.ReadFileIntoGuideDb(context);
//		
//        ContentResolver.requestSync(null /* all accounts */, GuideProvider.AUTHORITY, new Bundle());
//
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		Log.w(TAG, "Upgrading database. Existing contents will be lost. [" + oldVersion + "]->[" + newVersion + "]");
//		boolean nukeData = false;
//		if (nukeData) {
//		    db.execSQL("DROP TABLE IF EXISTS " + Tables.GUIDE);
//		    db.execSQL("DROP TABLE IF EXISTS " + Tables.RUNG_AT);
//		}
//	    onCreate(db);
//	    
//        if (oldVersion != newVersion) {
//            throw new IllegalStateException(
//                    "error upgrading the database to version " + newVersion);
//        }
//
//	}
//
//	private void addData(SQLiteDatabase db) {
//		//db.insert(table, nullColumnHack, values)
//	}
//}
