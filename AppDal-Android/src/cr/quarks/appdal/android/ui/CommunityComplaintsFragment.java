package cr.quarks.appdal.android.ui;

import java.util.ArrayList;
import java.util.List;

import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.entity.Action;
import cr.quarks.appdal.android.entity.ActionRequest;
import cr.quarks.appdal.android.entity.ActionType;
import cr.quarks.appdal.android.service.AppDalService;
import cr.quarks.appdal.android.service.AppDalServiceImpl;
import cr.quarks.appdal.android.util.BackgroundTask;
import cr.quarks.appdal.android.util.ImageUtil;
import cr.quarks.appdal.android.util.LogIt;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class CommunityComplaintsFragment extends Fragment {

	private List<Action> complaints;

	private ComplaintAdapter adapter;

	private ListView complaintsListView;

	private EditText message;

	private RelativeLayout sendMessage;

	public static CommunityComplaintsFragment newInstance() {
		CommunityComplaintsFragment fragment = new CommunityComplaintsFragment();

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

		View view = inflater.inflate(R.layout.fragment_community_complaint,
				container, false);
		// complaintsListView = (ListView) view
		// .findViewById(R.id.community_complaint_list);
		// adapter = new ComplaintAdapter();
		// complaintsListView.setAdapter(adapter);
		// loadComplaints();
		message = (EditText) view
				.findViewById(R.id.community_complaint_message_text);
		sendMessage = (RelativeLayout) view
				.findViewById(R.id.community_complaint_send_message);
		sendMessage.setOnClickListener(sendMessageClickListener);
		return view;
	}

	private void loadComplaints() {
		new BackgroundTask() {

			@Override
			public void work() {
				AppDalService service = new AppDalServiceImpl();

				complaints = new ArrayList<Action>();
				;

				for (Action action : service.getActions()) {

					if (action.getType() == ActionType.COMPLAINTS) {
						action.setBitmap(ImageUtil.getImage(action.getImage()));
						complaints.add(action);
					}

				}

			}

			@Override
			public void done() {
				adapter.setItems(complaints);

			}
		};
	}

	private OnClickListener sendMessageClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String messageText = message.getText().toString();
			LogIt.d(getActivity(), messageText);

			AppDalServiceImpl service = new AppDalServiceImpl();
			ActionRequest request = new ActionRequest();

			request.setTitle("Robo o asalto");
			request.setType("solicitud_accion");
			request.setBody(messageText);
			request.setActionReference("Robo o asalto");
			request.setLatitud("-9.1241243");
			request.setLongitud("-8.14512");
			service.submitActionRequest(request);

		}
	};
}
