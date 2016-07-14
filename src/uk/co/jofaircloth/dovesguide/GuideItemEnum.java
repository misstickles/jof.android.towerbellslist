package uk.co.jofaircloth.dovesguide;

public enum GuideItemEnum {

	ID				("_id", "", "_id"),
	DOVE_ID			("DoveID", "", ""),
	NAT_GRID_REF	("NG", "", ""),
	SN_LAT			("SNLat", "", ""),
	SN_LONG			("SNLong", "", ""),
	POSTCODE 		("Postcode", "", ""),
	TOWER_BASE 		("TowerBase", "", ""),
	COUNTY 			("County", "", ""),
	COUNTRY 		("Country", "", ""),
	ISO3166_CODE	("ISO3166code", "", ""),
	DIOCESE 		("Diocese", "", ""),
	PLACE 			("Place", "Place", "Place, Dedicn"),
	PLACE_2 		("Place2", "", ""),
	PLACE_CL 		("PlaceCL", "", ""),
	DEDICN 			("Dedicn", "", ""),
	BELLS 			("Bells", "Number of Bells", "CAST(Bells AS NUMERIC), Place, Dedicn"),
	WEIGHT 			("wt", "Weight", "CAST(Wt AS NUMERIC), Place, Dedicn"),
	APP 			("App", "", ""),
	NOTE			("Note", "", ""),
	HZ				("Hz", "", ""),
	DETAILS 		("Details", "", ""),
	GROUND_FLOOR	("GF", "", ""),
	TOILET 			("Toilet", "", ""),
	UR 				("UR", "", ""),
	PD_NO 			("PDNo", "", ""),
	PRACTICE_NIGHT	("PracN", "Practice Night", "CASE PracN WHEN '-' THEN 0 WHEN 'Mon' THEN 1 WHEN 'Tue' THEN 2 WHEN 'Wed' THEN 3 WHEN 'Thu' THEN 4 WHEN 'Fri' THEN 5 WHEN 'Sat' THEN 6 WHEN 'Sun' THEN 7 END, Place, Dedicn"),
	PST 			("PSt", "", ""),
	PRXF 			("PrXF", "", ""),
	OVERHAUL_YEAR 	("OvhaulYr", "", ""),
	CONTRACTOR 		("Contractor", "", ""),
	TUNE_YEAR 		("TuneYr", "", ""),
	EXTRA_INFO 		("ExtraInfo", "", ""),
	WEB_PAGE 		("WebPage", "", ""),
	UPDATED 		("Updated", "", ""),
	AFFILIATIONS 	("Affiliations", "", ""),
	ALT_NAME 		("AltName", "", ""),
	SIMULATOR 		("Simulator", "", ""),
	LAT 			("Lat", "", "CAST(Lat AS NUMERIC)"),
	LONG 			("Long", "", "CAST(Long AS NUMERIC)"),
	DISTANCE		("'' AS Distance", "Distance", "Distance, Place, Dedicn");

	private final String columnName;
	private final String columnDescription;
	private final String sortOrder;

	GuideItemEnum(String n, String d, String s) {
		this.columnName = n;
		this.columnDescription = d;
		this.sortOrder = s;
	}

	public static String[] getColumnList() {
		int i = 0;
		String[] s = new String[GuideItemEnum.values().length];

		for (GuideItemEnum g : GuideItemEnum.values()) {
			s[i] = g.columnName;
			i++;
		}
		return s;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public String getDescription() {
		if (columnDescription.equals(""))
			return columnName;
		return columnDescription;
	}
	public String getSortOrder() {
		if (sortOrder.equals(""))
			return columnName;
		return sortOrder;
	}
	
	public static String getSortFromString(String desc) {
		for (GuideItemEnum e : GuideItemEnum.values()) {
			if (e.getDescription().equals(desc)) {
				return e.getSortOrder();
			}
		}
		return "";
	}
	@Override
	public String toString() {
		if (columnDescription.equals(""))
			return columnName;
		return columnDescription;
	}

}
