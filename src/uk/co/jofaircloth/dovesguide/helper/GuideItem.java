package uk.co.jofaircloth.dovesguide.helper;

import uk.co.jofaircloth.dovesguide.dal.GuideContract;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class GuideItem {
	
	private SQLiteDatabase mDb;
	
	public GuideItem(SQLiteDatabase db) {
		mDb = db;
	}

	public long deleteAll() {
		return mDb.delete(GuideContract.Guide.TABLE_NAME, null, null);
	}

	/**
	 * Insert a new record into the Dove's guide database
	 */
	public long addItem(String[] data) {
		TowerData itm = TowerData.getGuideItem(data);

		ContentValues values = new ContentValues();
		values.put(GuideContract.Guide.DOVE_ID, itm.DOVE_ID);
		values.put(GuideContract.Guide.NATIONAL_GRID_REF, itm.NATIONAL_GRID_REF);
		values.put(GuideContract.Guide.SN_LAT, itm.SN_LAT);
		values.put(GuideContract.Guide.SN_LONG, itm.SN_LONG);
		values.put(GuideContract.Guide.POSTCODE, itm.POSTCODE);
		values.put(GuideContract.Guide.TOWER_BASE, itm.TOWER_BASE);
		values.put(GuideContract.Guide.COUNTY, itm.COUNTY);
		values.put(GuideContract.Guide.COUNTRY, itm.COUNTRY);
		values.put(GuideContract.Guide.ISO3166_CODE, itm.ISO3166_CODE);
		values.put(GuideContract.Guide.DIOCESE, itm.DIOCESE); 
		values.put(GuideContract.Guide.PLACE, itm.PLACE);
		values.put(GuideContract.Guide.PLACE_2, itm.PLACE_2);
		values.put(GuideContract.Guide.PLACE_CL, itm.PLACE_CL);
		values.put(GuideContract.Guide.DEDICN, itm.DEDICN);
		values.put(GuideContract.Guide.BELLS, itm.BELLS);
		values.put(GuideContract.Guide.WEIGHT, itm.WEIGHT);
		values.put(GuideContract.Guide.APP, itm.APP);
		values.put(GuideContract.Guide.NOTE, itm.NOTE);
		values.put(GuideContract.Guide.HZ, itm.HZ);
		values.put(GuideContract.Guide.DETAILS, itm.DETAILS);
		values.put(GuideContract.Guide.GROUND_FLOOR, itm.GROUND_FLOOR);
		values.put(GuideContract.Guide.TOILET, itm.TOILET);
		values.put(GuideContract.Guide.UR, itm.UR);
		values.put(GuideContract.Guide.PD_NO, itm.PD_NO);
		values.put(GuideContract.Guide.PRACTICE_NIGHT, itm.PRACTICE_NIGHT);
		values.put(GuideContract.Guide.PST, itm.PST);
		values.put(GuideContract.Guide.PRXF, itm.PRXF);
		values.put(GuideContract.Guide.OVERHAUL_YEAR, itm.OVERHAUL_YEAR);
		values.put(GuideContract.Guide.CONTRACTOR, itm.CONTRACTOR);
		values.put(GuideContract.Guide.TUNE_YEAR, itm.TUNE_YEAR);
		values.put(GuideContract.Guide.EXTRA_INFO, itm.EXTRA_INFO);
		values.put(GuideContract.Guide.WEB_PAGE, itm.WEB_PAGE);
		values.put(GuideContract.Guide.UPDATED, itm.UPDATED);
		values.put(GuideContract.Guide.AFFILIATIONS, itm.AFFILIATIONS);
		values.put(GuideContract.Guide.ALT_NAME, itm.ALT_NAME);
		values.put(GuideContract.Guide.SIMULATOR, itm.SIMULATOR);
		values.put(GuideContract.Guide.LAT, itm.LAT);
		values.put(GuideContract.Guide.LONG, itm.LONG);

		return mDb.insert(GuideContract.Guide.TABLE_NAME, null, values);
	}


}
