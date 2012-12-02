package cr.quarks.appdal.android.entity;

public enum RankingType {

	UNSECURE(1), MEDIUM(2), SECURE(3);
	
	private long id;
	
	private long colorId;
	
	private RankingType(long id){
		this.id = id;
		this.colorId = colorId;
	}
	
	public long getId(){
		return id;
	}

	public long getColorId() {
		return colorId;
	}
	
	
}
