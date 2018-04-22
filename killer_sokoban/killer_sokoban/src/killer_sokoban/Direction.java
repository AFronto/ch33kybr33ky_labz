package killer_sokoban;

/**
 *Az iranyokat tarolo enumeracio
 */
public enum Direction {

	UP, DOWN, LEFT, RIGHT;
	
	/**
	 * Ez egy olyan met�dus, amely minden bizonnyal azt a Direction-t fogja visszaadni, mely ellent�te az �ltalunk kor�bban kiv�lasztott ir�nynak.
	 * Ha valamilyen okn�l fogva nem lenne ellent�te a met�dus m�gis null �rt�ket ad vissza. 
	 *
	 * @return Vissza adja az ellenkezo iranyt 
	 */
	public Direction Opposite(){
		switch(this){
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		}
		return null;
	}
}
