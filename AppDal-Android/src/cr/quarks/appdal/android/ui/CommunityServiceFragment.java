package cr.quarks.appdal.android.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.entity.Action;
import cr.quarks.appdal.android.entity.ActionType;
import cr.quarks.appdal.android.service.AppDalService;
import cr.quarks.appdal.android.service.AppDalServiceImpl;
import cr.quarks.appdal.android.util.BackgroundTask;
import cr.quarks.appdal.android.util.ImageUtil;

public class CommunityServiceFragment extends Fragment {

	private List<Action> services;

	private ServiceAdapter adapter;

	private ListView servicesListView;

	public static CommunityServiceFragment newInstance() {
		CommunityServiceFragment fragment = new CommunityServiceFragment();

		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

		View view = inflater.inflate(R.layout.fragment_community_service,
				container, false);
		servicesListView = (ListView) view
				.findViewById(R.id.community_service_list);
		adapter = new ServiceAdapter();
		servicesListView.setAdapter(adapter);
		loadServices();
		return view;
	}

	private void loadServices() {
		new BackgroundTask() {

			@Override
			public void work() {
				AppDalService service = new AppDalServiceImpl();

				services = new ArrayList<Action>();;
				
				for (Action action : service.getActions()) {
					
					if(action.getType() == ActionType.SERVICES){
						action.setBitmap(ImageUtil.getImage(action.getImage()));
						services.add(action);	
					}
					
				}

			}

			@Override
			public void done() {
				adapter.setItems(services);

			}
		};
	}

}
