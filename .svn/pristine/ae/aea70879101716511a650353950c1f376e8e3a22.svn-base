package uk.co.jofaircloth.dovesguide.dal;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines a contract between the Note Pad content provider and its clients. A contract defines the
 * information that a client needs to access the provider as one or more data tables. A contract
 * is a public, non-extendable (final) class that contains constants defining column names and
 * URIs. A well-written client depends only on the constants in the contract.
 */
public final class GuideContract {

	public static final String AUTHORITY = "uk.co.jofaircloth.goringing.guide";
	
    // This class cannot be instantiated
    private GuideContract() {}

    /**
     * Dove guide table contract
     */
    public static final class Guide implements BaseColumns {

        // This class cannot be instantiated
        private Guide() {}

        public static final String TABLE_NAME = "guide";

        private static final String SCHEME = "content://";
        private static final String PATH_GUIDE = "/guide";
        private static final String PATH_GUIDE_ID = "/guideid/";

        /**
         * 0-relative position of a note ID segment in the path part of a note ID URI
         */
        public static final int NOTE_ID_PATH_POSITION = 1;

        /**
         * Path part for the Live Folder URI
         */
        private static final String PATH_LIVE_FOLDER = "/live_folders/notes";

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_GUIDE);

        /**
         * The content URI base for a single note. Callers must
         * append a numeric note id to this Uri to retrieve a note
         */
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_GUIDE_ID);

        /**
         * The content URI match pattern for a single note, specified by its ID. Use this to match
         * incoming URIs or to construct an Intent.
         */
        public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(SCHEME + AUTHORITY + PATH_GUIDE_ID + "/#");

        /**
         * The content Uri pattern for a notes listing for live folders
         */
        public static final Uri LIVE_FOLDER_URI = Uri.parse(SCHEME + AUTHORITY + PATH_LIVE_FOLDER);

         // MIME type definitions

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.note";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * note.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.note";

        public static final String DEFAULT_SORT_ORDER = "modified DESC";

        //  Column definitions
        public static final String ID = "_id";
    	public static final String DOVE_ID = "DoveID";
    	public static final String NATIONAL_GRID_REF = "NG";
    	public static final String SN_LAT = "SNLat";
    	public static final String SN_LONG = "SNLong";
    	public static final String POSTCODE = "Postcode";
    	public static final String TOWER_BASE = "TowerBase";
    	public static final String COUNTY = "County";
    	public static final String COUNTRY = "Country";
    	public static final String ISO3166_CODE = "ISO3166code";
    	public static final String DIOCESE = "Diocese";
    	public static final String PLACE = "Place";
    	public static final String PLACE_2 = "Place2";
    	public static final String PLACE_CL = "PlaceCL";
    	public static final String DEDICN = "Dedicn";
    	public static final String BELLS = "Bells";
    	public static final String WEIGHT = "wt";
    	public static final String APP = "App";
    	public static final String NOTE = "Note";
    	public static final String HZ = "Hz";
    	public static final String DETAILS = "Details";
    	public static final String GROUND_FLOOR = "GF";
    	public static final String TOILET = "Toilet";
    	public static final String UR = "UR";
    	public static final String PD_NO = "PDNo";
    	public static final String PRACTICE_NIGHT = "PracN";
    	public static final String PST = "PSt";
    	public static final String PRXF = "PrXF";
    	public static final String OVERHAUL_YEAR = "OvhaulYr";
    	public static final String CONTRACTOR = "Contractor";
    	public static final String TUNE_YEAR = "TuneYr";
    	public static final String EXTRA_INFO = "ExtraInfo";
    	public static final String WEB_PAGE = "WebPage";
    	public static final String UPDATED = "Updated";
    	public static final String AFFILIATIONS = "Affiliations";
    	public static final String ALT_NAME = "AltName";
    	public static final String SIMULATOR = "Simulator";
    	public static final String LAT = "Lat";
    	public static final String LONG = "Long";

    }

    public static final class RungAt implements BaseColumns {
    	private RungAt() {}
    }
}
