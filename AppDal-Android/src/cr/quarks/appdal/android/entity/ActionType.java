package cr.quarks.appdal.android.entity;

public enum ActionType {
	
	COMPLAINTS("denuncias"),
	SERVICES("servicios");
	
	private String key;
	
	public String getKey() {
		return key;
	}

	private ActionType(String key) {
		this.key = key;
	}
	
	public static ActionType parse(String key) {
		if (key.equals("denuncias")) {
			return COMPLAINTS;
		} else if (key.equals("servicios")) {
			return SERVICES;
		} else {
			return null;
		}
	}
}