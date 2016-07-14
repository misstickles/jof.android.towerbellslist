package uk.co.jofaircloth.dovesguide.helper;

import java.io.Serializable;

import android.database.Cursor;
import uk.co.jofaircloth.dovesguide.GuideItemEnum;
import uk.co.jofaircloth.dovesguide.utils.Utils;

public class TowerData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2767865162374528919L;
	public String DB_ID = "";
	public String DOVE_ID = "";
	public String NATIONAL_GRID_REF = "";
	public String SN_LAT = "";
	public String SN_LONG = "";
	public String POSTCODE = "";
	public String TOWER_BASE = "";
	public String COUNTY = "";
	public String COUNTRY = "";			// GB, New Zealand, Canada
	public String ISO3166_CODE = "";	// GB, NZ, CA
	public String DIOCESE  = "";
	public String PLACE = "";
	public String PLACE_2 = "";
	public String PLACE_CL = "";
	public String DEDICN = "";
	public String BELLS = "";
	public String WEIGHT = "";
	public String APP = "";		// ??
	public String NOTE = "";
	public String HZ = "";
	public String DETAILS = "";
	public String GROUND_FLOOR = "";
	public String TOILET = "";
	public String UR = "";
	public String PD_NO = "";
	public String PRACTICE_NIGHT = "";
	public String PST = "";		// practice start time
	public String PRXF = "";
	public String OVERHAUL_YEAR = "";
	public String CONTRACTOR = "";
	public String TUNE_YEAR = "";
	public String EXTRA_INFO = "";
	public String WEB_PAGE = "";
	public String UPDATED = "";
	public String AFFILIATIONS = "";
	public String ALT_NAME = "";
	public String SIMULATOR = "";
	public String LAT = "";
	public String LONG = "";
	public String DISTANCE = "";

	private final double POUNDS_IN_KG = 2.20462262185;
	private final double POUNDS_IN_CWT = 112;
//	private final double KG_IN_CWT = 58.2;

	public String getPosition() {
		return this.LAT + ", " + this.LONG;
	}
	public String getSatnavPosition() {
		if (!(this.SN_LAT.equals("")) && !(this.SN_LONG.equals(""))) {
			return String.format("Suggested satnav position: %s, %s", this.SN_LAT, this.SN_LONG);
		}
		return "";
	}
	
	public String getTitle() {
		return String.format("%s, %s", this.PLACE, this.DEDICN);
	}
	public String getAdditionalLocation() {
		String comma = "";
		if (!this.PLACE_2.trim().isEmpty() && !this.PLACE_CL.trim().isEmpty()) comma = ", "; 
		return this.PLACE_2 + comma + this.PLACE_CL;
	}
	public String getCountyCountry() {
		String comma = "";
		
		if (!this.COUNTRY.trim().isEmpty() && !this.COUNTY.trim().isEmpty()) comma = ", "; 
		return this.COUNTY + comma + this.COUNTRY;
	}	
	public String getBellNumber() {
		return this.BELLS;
	}
	public String getBellWeight() {
		double cwt = 0;
		int qtr = 0, lbs = 0;
		double kgs = 0;
		if (!this.WEIGHT.equals("")) {
			cwt = Integer.parseInt(this.WEIGHT) / POUNDS_IN_CWT;
			double removeCwt = cwt - (int) cwt;  // eg 28.015345 -> 0.015345
			qtr = (int) (removeCwt / 0.25d);
			lbs =  (int) ((removeCwt - (qtr * 0.25d)) * 100) + 1;  // just round it up!!
			kgs = Integer.parseInt(this.WEIGHT) / POUNDS_IN_KG;
		}
		return String.format("%s (%skg)", String.format("%s-%s-%s", (int) cwt, qtr, lbs), Utils.roundToDps(kgs, 0));
	}
	public String getBellKey() {
		String key = "";
		if (!this.NOTE.equals("")) {
			key = String.format(" in %s", this.NOTE);
		}
		if (!this.HZ.equals("")) {
			key += String.format(" (%sHz)", this.HZ);
		}
		return key; 
	}
	public String getPracticeDetails() {
		String time = "";
		if (!this.PST.isEmpty()) time = String.format(" (%s)", this.PST); 
		if (this.PRACTICE_NIGHT.equals("")) this.PRACTICE_NIGHT = "-";
		return this.PRACTICE_NIGHT + time;
	}
	public String getUnringable() {
		if (this.UR.trim().isEmpty()) return "";
		return "Unringable";
	}
	public String getDiocese() {
		return this.DIOCESE;
	}
	public String getAffiliation() {
		return this.AFFILIATIONS;
	}
	public String getAka() {
		if (this.ALT_NAME.trim().isEmpty()) return "";
		return "Also known as " + this.ALT_NAME;
	}
	public String getPostcode() {
		if (this.POSTCODE.trim().isEmpty()) return "";
		return this.POSTCODE;
	}
	public String getGridRef() {
		return this.NATIONAL_GRID_REF;
	}
	public String getGroundFloor() {
		if (this.GROUND_FLOOR.trim().isEmpty()) return "";
		return "Ground Floor";
	}
	public String getPracticeNight() {
		if (this.PRACTICE_NIGHT.trim().isEmpty()) return "";
		return this.PRACTICE_NIGHT;
	}
	public String getPracticeTime() {
		if (this.PST.trim().isEmpty()) return "";
		return this.PST;
	}
	public String getExtraInfo() {
		if (this.EXTRA_INFO.trim().isEmpty()) return "";
		return this.EXTRA_INFO;
	}
	public String getPrxf() {
		if (this.PRXF.trim().isEmpty()) return "";
		return this.PRXF;
	}
	public String getContractorAndDate() {
		if (this.CONTRACTOR.trim().isEmpty()) return "";
		String date = this.OVERHAUL_YEAR;
		if (!date.trim().isEmpty()) date = ", " + date;
		return this.CONTRACTOR + date;
	}
	public String getTuned() {
		if (this.TUNE_YEAR.trim().isEmpty()) return "";
		return "Tuned in " + this.TUNE_YEAR;
	}
	public String getToilet() {
		if (this.TOILET.trim().isEmpty()) return "";
		return "Toilet available";
	}
	public String getTowerBaseId() {
		if (this.TOWER_BASE.trim().isEmpty()) return "";
		return "Tower base id: " + this.TOWER_BASE;
	}
	public String getWebAddress() {
		if (this.WEB_PAGE.trim().isEmpty()) return "";
		if (!this.WEB_PAGE.startsWith("http")) {
			this.WEB_PAGE = "http://" + this.WEB_PAGE;
		}
		return this.WEB_PAGE;
	}

	@Override
	public String toString() {
//		String selectText = "Place || " + 
//		"' (' || Dedicn || " + 
//		"CASE Place2 WHEN '' THEN ')' ELSE ', ' || Place2 || ')' END || " + 
//		"CASE County WHEN '' THEN '' ELSE ', ' || County END || " +
//		"CASE Country WHEN '' THEN '' ELSE ', ' || Country END AS ListItem";
		String place2, county, country;
		if (!this.PLACE_2.equals("")) place2 = ", " + this.PLACE_2 + ")";
		else place2 = ")";
		if (!this.COUNTY.equals("")) county = ", " + this.COUNTY;
		else county = "";
		if (!this.COUNTRY.equals("")) country = ", " + this.COUNTRY;
		else country = "";
		return String.format("%s (%s%s%s%s", this.PLACE, this.DEDICN, place2, county, country);
	}


	/*
	 * calculate the distance between 2 lat/long locations
	 * view-source:http://www.johndcook.com/lat_long_distance.html
	 */
	public double getDistance(TowerData itemTo) {
		double lat1 = Double.parseDouble(this.LAT);
		double lat2 = Double.parseDouble(itemTo.LAT);
		double long1 = Double.parseDouble(this.LONG);
		double long2 = Double.parseDouble(itemTo.LONG);
		
		// Compute spherical coordinates
		double rho = 3960.0; // earth diameter in miles
		
		// convert latitude and longitude to spherical coordinates in radians
		// phi = 90 - latitude
		double phi1 = (90.0 - lat1) * Math.PI / 180.0;
		double phi2 = (90.0 - lat2) * Math.PI / 180.0;

		// theta = longitude
		double theta1 = long1 * Math.PI / 180.0;
		double theta2 = long2 * Math.PI / 180.0;

		// compute spherical distance from spherical coordinates
		// arc length = \arccos(\sin\phi\sin\phi'\cos(\theta-\theta') + \cos\phi\cos\phi')
		// distance = rho times arc length
		double d = rho * Math.acos(Math.sin(phi1) * Math.sin(phi2) * Math.cos(theta1 - theta2) + Math.cos(phi1) * Math.cos(phi2));

		return d; // miles 1.609344 * d // km
	}
	
	public static TowerData getGuideItem(String[] s) {
		TowerData itm = new TowerData();
		try {
			itm.DOVE_ID = s[0];
			itm.NATIONAL_GRID_REF = s[1];
			itm.SN_LAT = s[2];
			itm.SN_LONG = s[3];
			itm.POSTCODE = s[4];
			itm.TOWER_BASE = s[5];
			itm.COUNTY = s[6];
			itm.COUNTRY = s[7];
			itm.ISO3166_CODE = s[8];
			itm.DIOCESE  = s[9];
			itm.PLACE = s[10];
			itm.PLACE_2 = s[11];
			itm.PLACE_CL = s[12];
			itm.DEDICN = s[13];
			itm.BELLS = s[14];
			itm.WEIGHT = s[15];
			itm.APP = s[16];
			itm.NOTE = s[17];
			itm.HZ = s[18];
			itm.DETAILS = s[19];
			itm.GROUND_FLOOR = s[20];
			itm.TOILET = s[21];
			itm.UR = s[22];
			itm.PD_NO = s[23];
			itm.PRACTICE_NIGHT = s[24];
			itm.PST = s[25];
			itm.PRXF = s[26];
			itm.OVERHAUL_YEAR = s[27];
			itm.CONTRACTOR = s[28];
			itm.TUNE_YEAR = s[29];
			itm.EXTRA_INFO = s[30];
			itm.WEB_PAGE = s[31];
			itm.UPDATED = s[32];
			itm.AFFILIATIONS = s[33];
			itm.ALT_NAME = s[34];
			itm.SIMULATOR = s[35];
			itm.LAT = s[36];
			itm.LONG = s[37];
			itm.DISTANCE = s[38];
		} catch (Exception ex) {
			
		}
		
		return itm;
	}

	public static TowerData getTowerData(Cursor c) {
		TowerData itm = new TowerData();
		if (c.getCount() > 0) {
			if(c.getColumnIndex(GuideItemEnum.ID.getColumnName()) != -1)
				itm.DB_ID = c.getString(c.getColumnIndex(GuideItemEnum.ID.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.DOVE_ID.getColumnName()) != -1)
				itm.DOVE_ID = c.getString(c.getColumnIndex(GuideItemEnum.DOVE_ID.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.NAT_GRID_REF.getColumnName()) != -1)
				itm.NATIONAL_GRID_REF = c.getString(c.getColumnIndex(GuideItemEnum.NAT_GRID_REF.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.SN_LAT.getColumnName()) != -1)
				itm.SN_LAT = c.getString(c.getColumnIndex(GuideItemEnum.SN_LAT.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.SN_LONG.getColumnName()) != -1)
				itm.SN_LONG = c.getString(c.getColumnIndex(GuideItemEnum.SN_LONG.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.POSTCODE.getColumnName()) != -1)
				itm.POSTCODE = c.getString(c.getColumnIndex(GuideItemEnum.POSTCODE.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.TOWER_BASE.getColumnName()) != -1)
				itm.TOWER_BASE = c.getString(c.getColumnIndex(GuideItemEnum.TOWER_BASE.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.COUNTY.getColumnName()) != -1)
				itm.COUNTY = c.getString(c.getColumnIndex(GuideItemEnum.COUNTY.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.COUNTRY.getColumnName()) != -1)
				itm.COUNTRY = c.getString(c.getColumnIndex(GuideItemEnum.COUNTRY.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.ISO3166_CODE.getColumnName()) != -1)
				itm.ISO3166_CODE = c.getString(c.getColumnIndex(GuideItemEnum.ISO3166_CODE.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.DIOCESE.getColumnName()) != -1)
				itm.DIOCESE  = c.getString(c.getColumnIndex(GuideItemEnum.DIOCESE.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.PLACE.getColumnName()) != -1)
				itm.PLACE = c.getString(c.getColumnIndex(GuideItemEnum.PLACE.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.PLACE_2.getColumnName()) != -1)
				itm.PLACE_2 = c.getString(c.getColumnIndex(GuideItemEnum.PLACE_2.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.PLACE_CL.getColumnName()) != -1)
				itm.PLACE_CL = c.getString(c.getColumnIndex(GuideItemEnum.PLACE_CL.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.DEDICN.getColumnName()) != -1)
				itm.DEDICN = c.getString(c.getColumnIndex(GuideItemEnum.DEDICN.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.BELLS.getColumnName()) != -1)
				itm.BELLS = c.getString(c.getColumnIndex(GuideItemEnum.BELLS.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.WEIGHT.getColumnName()) != -1)
				itm.WEIGHT = c.getString(c.getColumnIndex(GuideItemEnum.WEIGHT.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.APP.getColumnName()) != -1)
				itm.APP = c.getString(c.getColumnIndex(GuideItemEnum.APP.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.NOTE.getColumnName()) != -1)
				itm.NOTE = c.getString(c.getColumnIndex(GuideItemEnum.NOTE.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.HZ.getColumnName()) != -1)
				itm.HZ = c.getString(c.getColumnIndex(GuideItemEnum.HZ.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.DETAILS.getColumnName()) != -1)
				itm.DETAILS = c.getString(c.getColumnIndex(GuideItemEnum.DETAILS.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.GROUND_FLOOR.getColumnName()) != -1)
				itm.GROUND_FLOOR = c.getString(c.getColumnIndex(GuideItemEnum.GROUND_FLOOR.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.TOILET.getColumnName()) != -1)
				itm.TOILET = c.getString(c.getColumnIndex(GuideItemEnum.TOILET.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.UR.getColumnName()) != -1)
				itm.UR = c.getString(c.getColumnIndex(GuideItemEnum.UR.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.PD_NO.getColumnName()) != -1)
				itm.PD_NO = c.getString(c.getColumnIndex(GuideItemEnum.PD_NO.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.PRACTICE_NIGHT.getColumnName()) != -1)
				itm.PRACTICE_NIGHT = c.getString(c.getColumnIndex(GuideItemEnum.PRACTICE_NIGHT.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.PST.getColumnName()) != -1)
				itm.PST = c.getString(c.getColumnIndex(GuideItemEnum.PST.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.PRXF.getColumnName()) != -1)
				itm.PRXF = c.getString(c.getColumnIndex(GuideItemEnum.PRXF.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.OVERHAUL_YEAR.getColumnName()) != -1)
				itm.OVERHAUL_YEAR = c.getString(c.getColumnIndex(GuideItemEnum.OVERHAUL_YEAR.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.CONTRACTOR.getColumnName()) != -1)
				itm.CONTRACTOR = c.getString(c.getColumnIndex(GuideItemEnum.CONTRACTOR.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.TUNE_YEAR.getColumnName()) != -1)
				itm.TUNE_YEAR = c.getString(c.getColumnIndex(GuideItemEnum.TUNE_YEAR.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.EXTRA_INFO.getColumnName()) != -1)
				itm.EXTRA_INFO = c.getString(c.getColumnIndex(GuideItemEnum.EXTRA_INFO.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.WEB_PAGE.getColumnName()) != -1)
				itm.WEB_PAGE = c.getString(c.getColumnIndex(GuideItemEnum.WEB_PAGE.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.UPDATED.getColumnName()) != -1)
				itm.UPDATED = c.getString(c.getColumnIndex(GuideItemEnum.UPDATED.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.AFFILIATIONS.getColumnName()) != -1)
				itm.AFFILIATIONS = c.getString(c.getColumnIndex(GuideItemEnum.AFFILIATIONS.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.ALT_NAME.getColumnName()) != -1)
				itm.ALT_NAME = c.getString(c.getColumnIndex(GuideItemEnum.ALT_NAME.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.SIMULATOR.getColumnName()) != -1)
				itm.SIMULATOR = c.getString(c.getColumnIndex(GuideItemEnum.SIMULATOR.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.LAT.getColumnName()) != -1)
				itm.LAT = c.getString(c.getColumnIndex(GuideItemEnum.LAT.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.LONG.getColumnName()) != -1)
				itm.LONG = c.getString(c.getColumnIndex(GuideItemEnum.LONG.getColumnName()));
			if(c.getColumnIndex(GuideItemEnum.DISTANCE.getColumnName()) != -1)
				itm.DISTANCE = c.getString(c.getColumnIndex(GuideItemEnum.DISTANCE.getColumnName()));
		}
		return itm;
	}

}
