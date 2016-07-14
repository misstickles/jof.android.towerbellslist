package uk.co.jofaircloth.dovesguide.delete;
//package uk.co.jofaircloth.dovesguide.delete;
//
//import uk.co.jofaircloth.dovesguide.GuideItem;
//import uk.co.jofaircloth.dovesguide.GuideItemEnum;
//import android.database.Cursor;
//
//public class DbUtils {
//	
//	public static GuideItem getItem(Cursor c) {
//		GuideItem itm = new GuideItem();
//		if (c.getCount() > 0) {
//			if(c.getColumnIndex(GuideItemEnum.ID.getColumnName()) != -1)
//				itm.DB_ID = c.getString(c.getColumnIndex(GuideItemEnum.ID.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.DOVE_ID.getColumnName()) != -1)
//				itm.DOVE_ID = c.getString(c.getColumnIndex(GuideItemEnum.DOVE_ID.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.NAT_GRID_REF.getColumnName()) != -1)
//				itm.NATIONAL_GRID_REF = c.getString(c.getColumnIndex(GuideItemEnum.NAT_GRID_REF.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.SN_LAT.getColumnName()) != -1)
//				itm.SN_LAT = c.getString(c.getColumnIndex(GuideItemEnum.SN_LAT.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.SN_LONG.getColumnName()) != -1)
//				itm.SN_LONG = c.getString(c.getColumnIndex(GuideItemEnum.SN_LONG.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.POSTCODE.getColumnName()) != -1)
//				itm.POSTCODE = c.getString(c.getColumnIndex(GuideItemEnum.POSTCODE.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.TOWER_BASE.getColumnName()) != -1)
//				itm.TOWER_BASE = c.getString(c.getColumnIndex(GuideItemEnum.TOWER_BASE.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.COUNTY.getColumnName()) != -1)
//				itm.COUNTY = c.getString(c.getColumnIndex(GuideItemEnum.COUNTY.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.COUNTRY.getColumnName()) != -1)
//				itm.COUNTRY = c.getString(c.getColumnIndex(GuideItemEnum.COUNTRY.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.ISO3166_CODE.getColumnName()) != -1)
//				itm.ISO3166_CODE = c.getString(c.getColumnIndex(GuideItemEnum.ISO3166_CODE.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.DIOCESE.getColumnName()) != -1)
//				itm.DIOCESE  = c.getString(c.getColumnIndex(GuideItemEnum.DIOCESE.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.PLACE.getColumnName()) != -1)
//				itm.PLACE = c.getString(c.getColumnIndex(GuideItemEnum.PLACE.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.PLACE_2.getColumnName()) != -1)
//				itm.PLACE_2 = c.getString(c.getColumnIndex(GuideItemEnum.PLACE_2.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.PLACE_CL.getColumnName()) != -1)
//				itm.PLACE_CL = c.getString(c.getColumnIndex(GuideItemEnum.PLACE_CL.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.DEDICN.getColumnName()) != -1)
//				itm.DEDICN = c.getString(c.getColumnIndex(GuideItemEnum.DEDICN.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.BELLS.getColumnName()) != -1)
//				itm.BELLS = c.getString(c.getColumnIndex(GuideItemEnum.BELLS.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.WEIGHT.getColumnName()) != -1)
//				itm.WEIGHT = c.getString(c.getColumnIndex(GuideItemEnum.WEIGHT.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.APP.getColumnName()) != -1)
//				itm.APP = c.getString(c.getColumnIndex(GuideItemEnum.APP.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.NOTE.getColumnName()) != -1)
//				itm.NOTE = c.getString(c.getColumnIndex(GuideItemEnum.NOTE.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.HZ.getColumnName()) != -1)
//				itm.HZ = c.getString(c.getColumnIndex(GuideItemEnum.HZ.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.DETAILS.getColumnName()) != -1)
//				itm.DETAILS = c.getString(c.getColumnIndex(GuideItemEnum.DETAILS.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.GROUND_FLOOR.getColumnName()) != -1)
//				itm.GROUND_FLOOR = c.getString(c.getColumnIndex(GuideItemEnum.GROUND_FLOOR.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.TOILET.getColumnName()) != -1)
//				itm.TOILET = c.getString(c.getColumnIndex(GuideItemEnum.TOILET.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.UR.getColumnName()) != -1)
//				itm.UR = c.getString(c.getColumnIndex(GuideItemEnum.UR.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.PD_NO.getColumnName()) != -1)
//				itm.PD_NO = c.getString(c.getColumnIndex(GuideItemEnum.PD_NO.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.PRACTICE_NIGHT.getColumnName()) != -1)
//				itm.PRACTICE_NIGHT = c.getString(c.getColumnIndex(GuideItemEnum.PRACTICE_NIGHT.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.PST.getColumnName()) != -1)
//				itm.PST = c.getString(c.getColumnIndex(GuideItemEnum.PST.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.PRXF.getColumnName()) != -1)
//				itm.PRXF = c.getString(c.getColumnIndex(GuideItemEnum.PRXF.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.OVERHAUL_YEAR.getColumnName()) != -1)
//				itm.OVERHAUL_YEAR = c.getString(c.getColumnIndex(GuideItemEnum.OVERHAUL_YEAR.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.CONTRACTOR.getColumnName()) != -1)
//				itm.CONTRACTOR = c.getString(c.getColumnIndex(GuideItemEnum.CONTRACTOR.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.TUNE_YEAR.getColumnName()) != -1)
//				itm.TUNE_YEAR = c.getString(c.getColumnIndex(GuideItemEnum.TUNE_YEAR.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.EXTRA_INFO.getColumnName()) != -1)
//				itm.EXTRA_INFO = c.getString(c.getColumnIndex(GuideItemEnum.EXTRA_INFO.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.WEB_PAGE.getColumnName()) != -1)
//				itm.WEB_PAGE = c.getString(c.getColumnIndex(GuideItemEnum.WEB_PAGE.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.UPDATED.getColumnName()) != -1)
//				itm.UPDATED = c.getString(c.getColumnIndex(GuideItemEnum.UPDATED.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.AFFILIATIONS.getColumnName()) != -1)
//				itm.AFFILIATIONS = c.getString(c.getColumnIndex(GuideItemEnum.AFFILIATIONS.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.ALT_NAME.getColumnName()) != -1)
//				itm.ALT_NAME = c.getString(c.getColumnIndex(GuideItemEnum.ALT_NAME.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.SIMULATOR.getColumnName()) != -1)
//				itm.SIMULATOR = c.getString(c.getColumnIndex(GuideItemEnum.SIMULATOR.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.LAT.getColumnName()) != -1)
//				itm.LAT = c.getString(c.getColumnIndex(GuideItemEnum.LAT.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.LONG.getColumnName()) != -1)
//				itm.LONG = c.getString(c.getColumnIndex(GuideItemEnum.LONG.getColumnName()));
//			if(c.getColumnIndex(GuideItemEnum.DISTANCE.getColumnName()) != -1)
//				itm.DISTANCE = c.getString(c.getColumnIndex(GuideItemEnum.DISTANCE.getColumnName()));
//		}
//		return itm;
//	}
//	
//	public static GuideItem getItem(String[] s) {
//		GuideItem itm = new GuideItem();
//		try {
//			itm.DOVE_ID = s[0];
//			itm.NATIONAL_GRID_REF = s[1];
//			itm.SN_LAT = s[2];
//			itm.SN_LONG = s[3];
//			itm.POSTCODE = s[4];
//			itm.TOWER_BASE = s[5];
//			itm.COUNTY = s[6];
//			itm.COUNTRY = s[7];
//			itm.ISO3166_CODE = s[8];
//			itm.DIOCESE  = s[9];
//			itm.PLACE = s[10];
//			itm.PLACE_2 = s[11];
//			itm.PLACE_CL = s[12];
//			itm.DEDICN = s[13];
//			itm.BELLS = s[14];
//			itm.WEIGHT = s[15];
//			itm.APP = s[16];
//			itm.NOTE = s[17];
//			itm.HZ = s[18];
//			itm.DETAILS = s[19];
//			itm.GROUND_FLOOR = s[20];
//			itm.TOILET = s[21];
//			itm.UR = s[22];
//			itm.PD_NO = s[23];
//			itm.PRACTICE_NIGHT = s[24];
//			itm.PST = s[25];
//			itm.PRXF = s[26];
//			itm.OVERHAUL_YEAR = s[27];
//			itm.CONTRACTOR = s[28];
//			itm.TUNE_YEAR = s[29];
//			itm.EXTRA_INFO = s[30];
//			itm.WEB_PAGE = s[31];
//			itm.UPDATED = s[32];
//			itm.AFFILIATIONS = s[33];
//			itm.ALT_NAME = s[34];
//			itm.SIMULATOR = s[35];
//			itm.LAT = s[36];
//			itm.LONG = s[37];
//			itm.DISTANCE = s[38];
//		} catch (Exception ex) {
//			
//		}
//		
//		return itm;
//	}
//
//}
