package cr.quarks.appdal.android.entity;

public enum RankingType {

	UNSECURE(1), MEDIUM(2), SECURE(3);
	
	private long id;
	
	private RankingType(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}
}
