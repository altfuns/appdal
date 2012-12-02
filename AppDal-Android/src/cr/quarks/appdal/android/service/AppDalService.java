package cr.quarks.appdal.android.service;

import java.util.List;

import cr.quarks.appdal.android.entity.Action;
import cr.quarks.appdal.android.entity.ActionRequest;
import cr.quarks.appdal.android.entity.Community;

public interface AppDalService {

	public List<Community> retrieveCommunities();
	
	public List<Action> getActions();

	public boolean submitActionRequest(ActionRequest request);
}
