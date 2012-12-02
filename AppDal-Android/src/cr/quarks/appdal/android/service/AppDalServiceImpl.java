package cr.quarks.appdal.android.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cr.quarks.appdal.android.entity.Action;
import cr.quarks.appdal.android.entity.Community;

public class AppDalServiceImpl implements AppDalService {

	private final String BASE_VIEWS_URL = "http://200.122.178.180:8080/appdal/appdal/views/";

	@Override
	public List<Community> retrieveCommunities() {
		final String endPointComunidades = "comunidades";
		List<Community> result = null;

		String response = executeRequest(BASE_VIEWS_URL + endPointComunidades);
		
		result = fromJsonList(response);
		
		return result;
	}

	@Override
	public List<Action> getActions() {
		final String endPointAcciones = "acciones";
		List<Action> result = null;
		
		String response = executeRequest(BASE_VIEWS_URL + endPointAcciones);
		
		result = fromJsonListActions(response);
		
		return result;
	}

	private String executeRequest(String url) {
		// Initialize the resource proxy.
		ClientResource cr = new ClientResource(url);
		// Workaround for GAE servers to prevent chunk encoding
		String response = null;
		try {
			response = cr.get(MediaType.APPLICATION_JSON).getText();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}
	
	public List<Community> fromJsonList(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Date.class,
//                new DateUtil.WCFDateDeserializer());

        Gson parser = gsonBuilder.create();
        Type listType = new TypeToken<List<Community>>() {
        }.getType();

        return parser.fromJson(json, listType);
    }
	
	public List<Action> fromJsonListActions(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson parser = gsonBuilder.create();
        Type listType = new TypeToken<List<Action>>() {
        }.getType();

        return parser.fromJson(json, listType);
    }

}
