package uk.co.jofaircloth.dovesguide;

import uk.co.jofaircloth.dovesguide.helper.TowerData;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity 
	implements ActionBar.TabListener, OnTowerSelectedListener, OnTowerLongClickListener, OnSearchButtonClickedListener {

	private final String TAG = "MainActivity.java";
	private final int NUMBER_TABS = 4;
	
	private static final int DIALOG_DETAILS = 1;
	private static final int DIALOG_VISITED = 2;
	
	private AzBrowseFragment azBrowseFragment;
	private NearbyFragment nearbyFragment;
	private SearchFragment searchFragment;
	
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding tab.
        // We can also use ActionBar.Tab#select() to do this if we have a reference to the
        // Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menu_settings:
        	Toast.makeText(this, "clicked " + item.getItemId(), Toast.LENGTH_LONG).show();
    		break;
    	case R.id.menu_sort:
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setTitle("Sort Order...");
    		
    		// TODO: - we don't want to be calling this all the time!!
    		builder.setItems(Constants.items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "Sorting by " + Constants.items[which], Toast.LENGTH_LONG).show();
					try {
						Configuration.sortOrder = GuideItemEnum.getSortFromString((String) Constants.items[which]);
						if (nearbyFragment != null) nearbyFragment.update();
						if (searchFragment != null) searchFragment.update();
						if (azBrowseFragment != null) azBrowseFragment.update();
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
						Log.d(TAG, "ln113:" + e.getMessage());
					}
				}
			});

    		AlertDialog alert = builder.create();
    		alert.show();
    		
    		break;
   		default:
   	    	Toast.makeText(getApplicationContext(), "oops..." , Toast.LENGTH_LONG).show();
   	    	break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	switch (position) {
        	case 0:
        		searchFragment = new SearchFragment();
        		return searchFragment;
        	case 1:
        		azBrowseFragment = new AzBrowseFragment();
        		return azBrowseFragment;
        	case 2:
        		nearbyFragment = new NearbyFragment();
        		return nearbyFragment;
        	case 3:
        		return new AboutFragment();
        	default:
        		searchFragment = new SearchFragment();
        		return searchFragment;
        	}
        }

        @Override
        public int getCount() {
            return NUMBER_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
	            case 0: return getString(R.string.title_search).toUpperCase();
	            case 1: return getString(R.string.title_browse).toUpperCase();
	            case 2: return getString(R.string.title_nearby).toUpperCase();
	            case 3: return getString(R.string.title_about).toUpperCase();
            }
            return null;
        }
    }

	@Override
	public void onTowerSelected(Cursor selected) {
		showDetailsDialog(selected);
	}
	@Override
	public void onTowerLongClick(Cursor cursor) {
		showRungThereDialog(cursor);
	}
	@Override
	public void onSearchButtonClicked(Cursor cursor) {
		searchFragment.update();		
	}	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch(id) {
		case DIALOG_DETAILS:
			return getInstanceDialog(R.layout.details_layout, "");
		case DIALOG_VISITED:
			return getInstanceDialog(R.layout.visited_layout, "");
		default:
			dialog = null;
			break;
		}
		return dialog;
	}
	
	private Dialog getInstanceDialog(int layout, String title) {
		final Dialog d = new Dialog(this, R.style.PopupDialog);
		d.setContentView(layout);
		d.setTitle(title);
		return d;		
	}
	
	private void showDetailsDialog(Cursor cursor) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		DetailsDialogFragment detailsDialog = new DetailsDialogFragment();
		Bundle b = new Bundle();
		b.putSerializable("data", TowerData.getTowerData(cursor));		
		detailsDialog.setArguments(b);
		detailsDialog.show(fragmentManager, "test");
	}
	
	private void showRungThereDialog(Cursor cursor) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		VisitedDialogFragment rungThereDialog = new VisitedDialogFragment();
		Bundle b = new Bundle();
		b.putSerializable("data", TowerData.getTowerData(cursor));
		rungThereDialog.setArguments(b);
		rungThereDialog.show(fragmentManager, "test");
	}

}
