package cr.quarks.appdal.android.resource;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import cr.quarks.appdal.android.entity.Community;

public interface CommunityResource {

	@Get
	public Community retrieve();
	
	@Put
	public void store(Community community);
	
	@Delete
	public void remove();
}
