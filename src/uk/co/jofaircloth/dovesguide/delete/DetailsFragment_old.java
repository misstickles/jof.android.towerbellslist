package uk.co.jofaircloth.dovesguide.delete;
//package uk.co.jofaircloth.dovesguide;
//
//import uk.co.jofaircloth.dovesguide.dal.DbGuide;
//import uk.co.jofaircloth.dovesguide.dal.DbUtils;
//import uk.co.jofaircloth.dovesguide.utils.MyLocation;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.opengl.Visibility;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class DetailsFragment_old extends Fragment {
//
//	private DbGuide db;
//	private View vw;
//	private final String TAG = "DetailsFragment.java"; 
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		if (container == null) return null;
//		
//		vw = inflater.inflate(R.layout.details_layout, container, false);
//		updateData(null);
//
//		return vw; // (LinearLayout) inflater.inflate(R.layout.search_layout, container, false);
//	}
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		
//		// close ...
//	}
//	
//	public void updateData(Cursor c) {
//		if (vw != null) {
//			GuideItem itm = null;
//			if (c != null) {
//				itm = DbUtils.getItem(c);
//			}
//			
//			if (itm != null) {
//			
//				// store the location in a static, final, class so we can access from inline class
//				final MyLocation loc = new MyLocation();
//				loc.lat = itm.LAT;
//				loc.lng = itm.LONG;
//				loc.satLat = itm.SN_LAT;
//				loc.satLng = itm.SN_LONG;
//				
//				ImageView location = (ImageView) vw.findViewById(R.id.details_location_image);
//				location.setClickable(true);
//				location.setOnClickListener(new OnClickListener() {				
//					@Override
//					public void onClick(View v) {
//						String uri = "geo:" + loc.lat + "," + loc.lng;
//						startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
//					}
//				});
//	
//	
//				LinearLayout ll = (LinearLayout) vw.findViewById(R.id.details_satnav_position_layout);
//				if (itm.getSatnavPosition().equals("")) {
//					ll.setVisibility(View.GONE);
//				} else {
//					((TextView) vw.findViewById(R.id.details_satnav_position)).setText(itm.getSatnavPosition());
//					location = (ImageView) vw.findViewById(R.id.details_location_satnav_image);
//					location.setClickable(true);
//					location.setOnClickListener(new OnClickListener() {				
//						@Override
//						public void onClick(View v) {
//							String uri = "geo:" + loc.satLat + "," + loc.satLng;
//							startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
//						}
//					});
//				}
//				
////				((TextView) vw.findViewById(R.id.details_title)).setText(itm.getTitle());
//				((TextView) vw.findViewById(R.id.details_position)).setText(itm.getPosition());
//		
//				TextView additionalLocation = (TextView) vw.findViewById(R.id.details_additional_location_text);
//				if (itm.getAdditionalLocation().equals("")) {
//					additionalLocation.setVisibility(View.GONE);
//				} else {
//					additionalLocation.setText(itm.getAdditionalLocation());
//				}
//				((TextView) vw.findViewById(R.id.details_county_country_text)).setText(itm.getCountyCountry());
//				((TextView) vw.findViewById(R.id.details_diocese_text)).setText(itm.DIOCESE);
//				((TextView) vw.findViewById(R.id.details_affiliation_text)).setText(itm.AFFILIATIONS);
//		
//				TextView aka = (TextView) vw.findViewById(R.id.details_aka);
//				if (itm.getAka().equals("")) {
//					aka.setVisibility(View.GONE);
//				} else {
//					aka.setText(itm.getAka());
//				}
//				((TextView) vw.findViewById(R.id.details_no_bells_text)).setText(itm.getBellNumber());
//				((TextView) vw.findViewById(R.id.details_weight_text)).setText(itm.getBellWeight());
//				((TextView) vw.findViewById(R.id.details_key_text)).setText(itm.getBellKey());
//				
//				TextView gf = (TextView) vw.findViewById(R.id.details_ground_floor_text);
//				TextView ur = (TextView) vw.findViewById(R.id.details_uringable_text);
//				if (itm.getGroundFloor().equals("")) {
//					gf.setVisibility(View.GONE);
//				} else {
//					gf.setText(itm.getGroundFloor());
//				}
//				if (itm.getUnringable().equals("")) {
//					ur.setVisibility(View.GONE);
//				} else {
//					String sUr = itm.getUnringable();
//					if (itm.getGroundFloor() != "") sUr = ", " + sUr;
//					ur.setText(sUr);
//				}
//		
//				TextView pn = (TextView) vw.findViewById(R.id.details_practice_night_text);
//				TextView pt = (TextView) vw.findViewById(R.id.details_practice_time_text);
//				if (itm.getPracticeNight().equals("")) {
//					pn.setVisibility(View.GONE);
//					pt.setVisibility(View.GONE);
//				} else {
//					pn.setText(itm.getPracticeNight());
//					if (itm.getPracticeTime() != "") {
//						pt.setText(String.format(" (%s)", itm.getPracticeTime()));
//					}
//				}
//				
//				TextView extraInfo = (TextView) vw.findViewById(R.id.details_extra_info_text);
//				TextView prxf = (TextView) vw.findViewById(R.id.details_prxf_text);
//				TextView toilet = (TextView) vw.findViewById(R.id.details_toilet_text);
//				TextView towerBase = (TextView) vw.findViewById(R.id.details_tower_base_text);
//				TextView contractor = (TextView) vw.findViewById(R.id.details_contractor_and_date_text);
//				TextView tuned = (TextView) vw.findViewById(R.id.details_date_tuned_text);
//				TextView web = (TextView) vw.findViewById(R.id.details_web_text);
//				web.setClickable(true);
//				final String webAddr = itm.getWebAddress();
//				web.setOnClickListener(new OnClickListener() {				
//					@Override
//					public void onClick(View v) {
//						String uri = webAddr;
//						startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
//					}
//				});
//				
//				if (itm.getExtraInfo().equals("")) extraInfo.setVisibility(View.GONE);
//				else extraInfo.setText(itm.getExtraInfo());
//				if (itm.getPrxf().equals("")) prxf.setVisibility(View.GONE);
//				else prxf.setText(itm.getPrxf());
//				if (itm.getToilet().equals("")) toilet.setVisibility(View.GONE);
//				else toilet.setText(itm.getToilet());
//				if (itm.getTowerBaseId().equals("")) towerBase.setVisibility(View.GONE);
//				else towerBase.setText(itm.getTowerBaseId());
//				if (itm.getContractorAndDate().equals("")) contractor.setVisibility(View.GONE);
//				else contractor.setText(itm.getContractorAndDate());
//				if (itm.getTuned().equals("")) tuned.setVisibility(View.GONE);
//				else tuned.setText(itm.getTuned());
//				if (itm.getWebAddress().equals("")) web.setVisibility(View.GONE);
//				else web.setText(itm.getWebAddress());	
//				
//				((TextView) vw.findViewById(R.id.details_postcode_text)).setText(itm.getPostcode());
//				((TextView) vw.findViewById(R.id.details_grid_ref_text)).setText(itm.getGridRef());
//			} else {
//				// TODO: show no data text
//			}
//		}
//	}
//}
