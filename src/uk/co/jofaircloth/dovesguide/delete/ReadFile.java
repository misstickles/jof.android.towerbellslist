//package uk.co.jofaircloth.dovesguide.delete;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import uk.co.jofaircloth.dovesguide.dal.DbGuide;
//import uk.co.jofaircloth.dovesguide.dal.DbUtils;
//import uk.co.jofaircloth.dovesguide.helper.GuideItem;
//import android.content.Context;
//import android.content.res.Resources;
//import android.text.TextUtils;
//import android.util.Log;
//
//public class ReadFile {
//	private static String TAG = "ReadFile";
//
//	public ReadFile() {}
//	
//	public void ReadFileIntoGuideDb(Context c) {
//		
//		Log.d("GuideDatabase", "Loading guide...");
//
//		GuideItem guide = new GuideItem(db);
//		long id = guide.deleteAll();
//		if (id < 0) return;
//		else {
//
//		long deleted = guide.deleteAll();
//		if (deleted < 0) {
//			return;
//		} else {
//			final Resources resources = c.getResources();
//			InputStream is = resources.openRawResource(R.raw.dove);
//			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//			// br = new BufferedReader(new
//			// InputStreamReader(c.getAssets().open(fileName)));
//
//			try {
//				String line = "";
//				String[] data;
//				while ((line = reader.readLine()) != null) {
//					// split the line on \
//					data = TextUtils.split(line, "\\\\");
//					if (data.length <= 0 || data[0].equals("DoveID"))
//						continue;
//					id = guide.addItem(data);
//					if (id < 0)
//						Log.e("GuideDatabase", "Unable to add tower: " + data);
//				}
//			} catch (IOException e) {
//				Log.d("GuideDatabase", "ReadFile error: " + e.getMessage());
//				e.printStackTrace();
//			} finally {
//				reader.close();
//			}
//		}
//
//		Log.d("GuideDatabase", "DONE loading guide...");
//
//	}	
//
//	}
//}
