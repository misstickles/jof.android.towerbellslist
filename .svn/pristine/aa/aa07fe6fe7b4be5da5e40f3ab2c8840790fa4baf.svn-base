package uk.co.jofaircloth.dovesguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AboutFragment extends Fragment {

	private Button btnBuildDatabase;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) return null;
		
		View vw = inflater.inflate(R.layout.about_layout, container, false);
		
		btnBuildDatabase = (Button) vw.findViewById(R.id.about_build_database_button);
		btnBuildDatabase.setOnClickListener(new onClickListener());

		TextView info = (TextView) vw.findViewById(R.id.about_content);
		info.setText(Html.fromHtml(getString(R.string.about_content)));
		info.setMovementMethod(LinkMovementMethod.getInstance());
		info.setLinksClickable(true);
		return vw;
	}

	private class onClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			Toast.makeText(view.getContext(), "Here I would be updating the database...", Toast.LENGTH_SHORT).show();
//			DbGuide db = new DbGuide(view.getContext());
//	        ReadFile r = new ReadFile("dove.txt");
//	        r.ReadFileIntoGuideDb(view.getContext());
//	    	Toast.makeText(view.getContext(), String.format("%s records added to the database", db.getItemCount()), Toast.LENGTH_LONG).show();	
		}
	}
}
