package killer_sokoban;

/**
 *Az iranyokat tarolo enumeracio
 */
public enum Direction {

	UP, DOWN, LEFT, RIGHT;
	
	/**
	 * Ez egy olyan metódus, amely minden bizonnyal azt a Direction-t fogja visszaadni, mely ellentéte az általunk korábban kiválasztott iránynak.
	 * Ha valamilyen oknál fogva nem lenne ellentéte a metódus mégis null értéket ad vissza. 
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
