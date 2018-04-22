package killer_sokoban;

import static killer_sokoban.Game.*;

public class Button extends Field{
	
	private TrapDoor myTrap;

	/**
	*Konstruktor
	*/
	public Button(){
		super();
	}
	
	/**
	 * Visszater a buttonhoz csatolt TrapDoor-ral.
	 */
	public TrapDoor GetmyTrap() {
		return myTrap;
	}
	
	/**
	 * Hozzacsatol a buttonhoz egy TrapDoort.
	 *
	 * @param t  a buttonhoz csatolando TrapDoor.
	 */
	public void SetmyTrap(TrapDoor t) {
		myTrap = t;
	}
	
	/**
	 * Ez a fuggveny aktivalja a trapdoort.
	 * 
	 * @param b  Megadja hogy aktivalja-e a gombot ami majd a trapdoort.
	 */
	public void Activate(boolean b){
		myTrap.SetActive(b);
	}
	
	/**
	 * A raallo Moveble megnyomasert felelos fuggvenyet fogja hivni,
	 * ha box akkor ez aktivalashoz vezet ha player akkor nem.
	 */
	public void FieldAction(){
		myMoveable.Press();
		super.FieldAction();    //Beszorulas ellenorzes.
	}

	@Override
	public String toString(){
		return "Button";
	}
}
