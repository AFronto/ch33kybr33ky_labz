package killer_sokoban;

/**
 *Az iranyokat tarolo enumeracio
 */
public enum Direction {

	UP, DOWN, LEFT, RIGHT;
	
	/**
	 * Visszaadja az ellenkezo iranyt.
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
