package uk.co.jofaircloth.dovesguide;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VisitedDialogFragment extends DialogFragment {

	private View vw;
	
	// required zero-arg constructor
	public VisitedDialogFragment() {}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		vw = inflater.inflate(R.layout.visited_layout, container, false);
		
		updateData();
		
		getDialog().setTitle("Yep.  Rung Here...");		
		getDialog().setCanceledOnTouchOutside(true);
		setStyle(R.style.DescTitle, R.style.AppTheme);
		return vw;
	}
	
	public void updateData() {
		
	}

}
