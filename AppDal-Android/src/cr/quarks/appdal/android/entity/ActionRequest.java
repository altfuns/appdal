package cr.quarks.appdal.android.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ActionRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6308527606700042983L;

	@SerializedName("title")
	private String title;
	
	@SerializedName("type")
	private String type;
	
	@SerializedName("body")
	private String body;
	
	@SerializedName("field_referencia_nodo")
	private String actionReference;
	
	@SerializedName("field_latitud")
	private String latitud;
	
	@SerializedName("field_longitud")
	private String longitud;

	
	public ActionRequest() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param title
	 * @param type
	 * @param body
	 * @param actionReference
	 * @param latitud
	 * @param longitud
	 */
	public ActionRequest(String title, String type, String body,
			String actionReference, String latitud, String longitud) {
		super();
		this.title = title;
		this.type = type;
		this.body = body;
		this.actionReference = actionReference;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	private String parseFieldValue(String value) {
		return  value ;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = parseFieldValue(latitud);
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = parseFieldValue(longitud);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getActionReference() {
		return actionReference;
	}

	public void setActionReference(String actionReference) {
		this.actionReference = parseFieldValue(actionReference);
	}
	
	public String getJson() {
		String result = "{";
		
		result += "\"field_referencia_nodo\":{\"und\":[{\"nid\":\"" + actionReference + "\"}]}," + 
				"\"body\":\"" + body + "\", \"field_latitud\":{\"und\":[{\"nid\":\"-9.1241243\"}]}," +
				"\"field_longitud\":{\"und\":[{\"nid\":\"-8.14512\"}]}," +
				"\"title\":\"" + title + "\", \"type\":\"" + type + "\"";
		
		result += "}";
		return result;
	}
}