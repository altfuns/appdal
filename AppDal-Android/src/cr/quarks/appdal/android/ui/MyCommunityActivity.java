package cr.quarks.appdal.android.ui;

import cr.quarks.appdal.android.R;
import android.app.Activity;
import android.os.Bundle;

public class MyCommunityActivity extends Activity{

	private CommunityBar communityBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycommunity);
		communityBar  = (CommunityBar) findViewById(R.id.mycommunity_community_bar);
	}
	
	@Override
    protected void onResume() {
        if (communityBar != null) {
        	communityBar.updateContent();
        }
        super.onResume();
    }
}
