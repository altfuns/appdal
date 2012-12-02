package cr.quarks.appdal.android.service;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

	private final String BASE_URL = "http://200.122.178.70:8080/appdal/appdal/views/comunidades";

	@Override
	public List<Community> retrieveCommunities() {
		List<Community> result = null;

		String response = executeRequest(BASE_URL);
		
		result = fromJsonList(response);
		
		return result;
	}

	@Override
	public List<Action> getActions() {
		// TODO Auto-generated method stub
		return null;
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

}
