package cr.quarks.appdal.android.ui;

import java.util.List;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;
import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.R.id;
import cr.quarks.appdal.android.R.layout;
import cr.quarks.appdal.android.R.menu;
import cr.quarks.appdal.android.entity.Community;
import cr.quarks.appdal.android.service.AppDalService;
import cr.quarks.appdal.android.service.AppDalServiceImpl;
import cr.quarks.appdal.android.util.BackgroundTask;
import cr.quarks.appdal.android.util.LogIt;

public class CommunitySelectionActivity extends Activity {

	private ListView communitiesListView;
	
	private List<Community> communities;
	
	private CommunityAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.community_selection_layout);
		initComponents();
		loadStartData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.community_selection, menu);
		
		// Get the SearchView and set the searchable configuration
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		
		return true;
	}
	

	private void initComponents() {

		communitiesListView = (ListView) findViewById(R.id.community_selection_list);
		
		// Get the intent, verify the action and get the query
	    Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      search(query);
	    }
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
	
	private void search(String query){
		LogIt.d(this, query);
	}
}
