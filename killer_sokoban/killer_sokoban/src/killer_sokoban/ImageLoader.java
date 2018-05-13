package killer_sokoban;

/**
 *Ez az osztaly feleleos a megfelelo kepek betolteseert.
 */
public class ImageLoader{
	private String player="player.png";
	private String box="box.png";
	private String wall="wall.png";
	private String field="field.png";
	private String trapdoor_open="trapdoor_open.png";
	private String trapdoor_colse="trapdoor_close.png";
	private String target="target.png";
	private String hole="hole.png";
	private String button="button.png";


	/**
	 *A bevett mezo alapjan eldonti mit ad vissza.
	 *
	 * @param f  Field tipusu valtozo, az a mezo aminek a kepet akarjuk meg kapni.
	 * @return   Egy Stringet ad vssza ami a betoltendo kep eleresi utvonala.
	 */
	public String GetImage(Field f){
		String image="";
		//Amennyiben nincs Moveable a mezon azokat az eseteket kezeli ez a switch case, ilyenkor mindig figyelni kell a keno anyagre is
		if(f.GetmyMoveable()==null){
			switch(""+f){
				case "Field":		//Field esete
					image=field;
					break;
				case "Hole":		//Hole esete
					image=hole;
					break;
				case "Target":		//Target esete
					image=target;
					break;
				case "TrapDoor":	//Trapdoor esete
					TrapDoor td=(TrapDoor) f;	//A filedet TrapDoorra castolom mert meg kell tudnom hogy nyitott e vagy zart
					if(td.GetActive()){
						image=trapdoor_open;
					}else{
						image=trapdoor_colse;
					}
					break;
				case "Button":		//Button esete
					image=button;
					break;
					
			}
			

			String[] separated=image.split("\\.");//szetszedem hogy be tudjam szurni a kenoanyagnek megfelelo modositot.
			
			switch(f.GetFriction()){
			case 1: 
				image=separated[0]+"."+separated[1];
				break;
			case 0:
				image=separated[0]+"_oil."+separated[1];
				break;
			case 2:
				image=separated[0]+"_honey."+separated[1];
				break;
			}

		}else{									//Ha van valaki a Fielden itt azt kezelem.
			switch(""+f.GetmyMoveable()){
				case "Player":					//Player esete
					Player p=(Player) f.GetmyMoveable();	//Playerbe castolom hogy le tudjam kerni a szinet.
					image=p.GetColor()+player;
					break;
				case "Wall":					//Wall esete
					image=wall;
					break;
				case "Box":						//Box esete
					image=box;
					break;
			}
		}

		return image;							//Vissza adom az elkeszult eleresi utat a megfelelo kephez.
	}
}