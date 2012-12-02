package cr.quarks.appdal.android.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3664020243334409992L;

	@SerializedName("id")
	private long id;

	@SerializedName("nombre")
	private String name;

	@SerializedName("tipo")
	private String tipo;

	private ActionType type;

	@SerializedName("imagen")
	private String image;

	/**
	 * @param id
	 * @param name
	 * @param type
	 * @param image
	 */
	public Action(long id, String name, String type, String image) {
		super();
		this.id = id;
		this.name = name;
		this.type = ActionType.parse(type);
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionType getType() {
		if(type == null){
			type = ActionType.parse(tipo); 
		}
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
