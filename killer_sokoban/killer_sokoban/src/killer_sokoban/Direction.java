package killer_sokoban;

/**
 *Az iranyokat tarolo enumeracio
 */
public enum Direction {

	UP, DOWN, LEFT, RIGHT;
	
	/**
	 * Ez egy olyan metodus, amely minden bizonnyal azt a Direction-t fogja visszaadni, mely ellentete az altalunk korabban kivalasztott iranynak.
	 * Ha valamilyen oknal fogva nem lenne ellentete a metodus megis null erteket ad vissza. 
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
