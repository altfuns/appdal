package cr.quarks.appdal.android;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import cr.quarks.appdal.android.entity.Community;
import cr.quarks.appdal.android.service.AppDalService;
import cr.quarks.appdal.android.service.AppDalServiceImpl;
import cr.quarks.appdal.android.ui.CommunityAdapter;
import cr.quarks.appdal.android.util.BackgroundTask;

public class MainActivity extends Activity {

	private ListView communitiesListView;
	
	private List<Community> communities;
	
	private CommunityAdapter adapter;

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
	}

	private void loadStartData() {
		new BackgroundTask() {

			@Override
			public void work() {
				AppDalService service = new AppDalServiceImpl();
				
				communities = service.retrieveCommunities();
			}

			@Override
			public void done() {
				adapter = new CommunityAdapter();
				adapter.setItems(communities);
				communitiesListView.setAdapter(adapter);
			}
		};
	}
}
