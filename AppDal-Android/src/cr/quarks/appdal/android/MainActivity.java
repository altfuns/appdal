package cr.quarks.appdal.android;

import java.util.List;

import org.restlet.resource.Post;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cr.quarks.appdal.android.entity.Community;
import cr.quarks.appdal.android.service.AppDalService;
import cr.quarks.appdal.android.service.AppDalServiceImpl;
import cr.quarks.appdal.android.ui.CommunityAdapter;
import cr.quarks.appdal.android.util.BackgroundTask;

public class MainActivity extends Activity {

	private ListView communitiesListView;

	private List<Community> communities;

	private CommunityAdapter adapter;

	private TextView communityPositionTextView;

	private TextView communityNameTextView;
	
	private View separator;
	
	private ImageView linkImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();
		loadStartData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void initComponents() {

		communitiesListView = (ListView) findViewById(R.id.main_communities);
		communityPositionTextView = (TextView) findViewById(R.id.main_community_position);
		communityNameTextView = (TextView) findViewById(R.id.main_community_name);
		separator = (View) findViewById(R.id.main_separator);
		linkImageView = (ImageView) findViewById(R.id.main_community_link);
	}

	private void loadStartData() {

		new BackgroundTask() {

			@Override
			public void work() {
				AppDalService service = new AppDalServiceImpl();
				communities = service.retrieveCommunities();
				long communityId = AppDalApp.getInstance().getUserCommunityId();
				if (AppDalApp.getInstance().getCommunity() == null) {
					int i = 1;
					for (Community community : communities) {
						community.setPosition(i);
						if (community.getId() == communityId) {
							AppDalApp.getInstance().setCommunity(community);
							break;
						}

						i++;
					}
				}
			}

			@Override
			public void done() {
				Community community = AppDalApp.getInstance().getCommunity();
				if (community != null) {
					communityPositionTextView.setText(String.valueOf(community
							.getPosition()));
					communityNameTextView.setText(community.getName());
					int color = getResources().getColor(community.getRankingType().getColorId());
					communityPositionTextView.setTextColor(color);
					separator.setBackgroundColor(color);
					linkImageView.setBackgroundColor(color);
					
				}

				adapter = new CommunityAdapter();
				adapter.setItems(communities);
				communitiesListView.setAdapter(adapter);
				
				communitiesListView.setSelection(community.getPosition());
			}
		};
	}
}
