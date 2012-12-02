package cr.quarks.appdal.android.ui;

import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import cr.quarks.appdal.android.R;

public class MyCommunityActivity extends FragmentActivity{

	private CommunityBar communityBar;
	
	private ViewPager pager;

    private TabPageIndicator titleIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycommunity);
		communityBar  = (CommunityBar) findViewById(R.id.mycommunity_community_bar);
		
		pager = (ViewPager) findViewById(R.id.mycommunity_pager);
        pager.setAdapter(new CommunityActionsPagerAdapter(
                getSupportFragmentManager()));

        titleIndicator = (TabPageIndicator) findViewById(R.id.mycommunity_indicator);
        titleIndicator.setViewPager(pager);
	}
	
	@Override
    protected void onResume() {
        if (communityBar != null) {
        	communityBar.updateContent();
        }
        super.onResume();
    }
}
