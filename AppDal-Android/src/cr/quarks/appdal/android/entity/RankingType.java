package cr.quarks.appdal.android.entity;

import android.database.CursorJoiner.Result;
import cr.quarks.appdal.android.R;

public enum RankingType {

	VERDE1(1, R.color.verde_1),VERDE2(2, R.color.verde_2),VERDE3(3, R.color.verde_3)
	, AMARILLO1(4, R.color.amarillo_1), AMARILLO2(5, R.color.amarillo_2), AMARILLO3(6, R.color.amarillo_3)
	, ANARANJADO1(7, R.color.anaranjado_1), ANARANJADO2(8, R.color.anaranjado_2)
	, ROJO1(9, R.color.rojo_1), ROJO2(10, R.color.rojo_2), ROJO3(11, R.color.rojo_3);
	
	private long id;
	
	private int colorId;
	
	private RankingType(long id, int colorId){
		this.id = id;
		this.colorId = colorId;
	}
	
	public long getId(){
		return id;
	}

	public int getColorId() {
		return colorId;
	}
	
	public static RankingType parse(long id){
		RankingType result = VERDE1;
		switch ((int) id) {
		case 1:
			result = VERDE1;
			break;
		case 2:
			result = VERDE2;
			break;
		case 3:
			result = VERDE3;
			break;
		case 4:
			result = AMARILLO1;
			break;
		case 5:
			result = AMARILLO2;
			break;
		case 6:
			result = AMARILLO3;
			break;
		case 7:
			result = ANARANJADO1;
			break;
		case 8:
			result = ANARANJADO2;
			break;
		case 9:
			result = ROJO1;
			break;
		case 10:
			result = ROJO2;
			break;
		case 11:
			result = ROJO3;
			break;

		default:
			break;
		}
		return result;
	}
	
	
}
