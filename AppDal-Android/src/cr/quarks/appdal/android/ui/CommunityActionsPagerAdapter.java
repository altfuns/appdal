package cr.quarks.appdal.android.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cr.quarks.appdal.android.AppDalApp;
import cr.quarks.appdal.android.R;

public class CommunityActionsPagerAdapter extends FragmentPagerAdapter {

	public CommunityActionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment result = null;
		switch (position) {
		case 0:
			result = new CommunityServiceFragment();
			break;

		case 1:
			result = new CommunityComplaintsFragment();
			break;

		default:
			break;
		}
		return result;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String result = null;
		switch (position) {
		case 0:
			result = AppDalApp.getInstance().getString(R.string.services);
			break;
		case 1:
			result = AppDalApp.getInstance().getString(R.string.complaints);
			break;
		default:
			break;
		}

		return result;
	}

}
