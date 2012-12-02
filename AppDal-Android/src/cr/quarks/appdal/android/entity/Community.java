package cr.quarks.appdal.android.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Community
 * 
 * @author altfuns
 * 
 */
public class Community implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SerializedName("id")
	private long id;

	@SerializedName("node_title")
	private String name;

	@SerializedName("ranking")
	private int rankingId;

	private RankingType rankingType;
	@SerializedName("calificacion")
	private float score;

	private long regionId;
	
	private String location;

	public Community(long id, String name, int rankingId, float score,
			long regionId) {
		super();
		this.id = id;
		this.name = name;
		this.rankingId = rankingId;
		this.score = score;
		this.regionId = regionId;
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

	public int getRankingId() {
		return rankingId;
	}

	public void setRankingId(int rankingId) {
		this.rankingId = rankingId;
	}

	public RankingType getRankingType() {
		return rankingType;
	}

	public void setRankingType(RankingType ranking) {
		this.rankingType = ranking;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
