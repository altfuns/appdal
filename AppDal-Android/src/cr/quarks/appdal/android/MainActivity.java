package cr.quarks.appdal.android;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import cr.quarks.appdal.android.entity.Community;
import cr.quarks.appdal.android.resource.CommunityResource;
import cr.quarks.appdal.android.service.AppDalService;
import cr.quarks.appdal.android.service.AppDalServiceImpl;
import cr.quarks.appdal.android.ui.CommunityAdapter;
import cr.quarks.appdal.android.util.BackgroundTask;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView communitiesListView;
	
	private List<Community> communities;
	
	private CommunityAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadStartData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void initComponents() {

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
