package killer_sokoban;

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

	public String GetImage(Field f){
		String image="";
		if(f.GetmyMoveable()==null){
			switch(""+f){
				case "Field":
					image=field;
					break;
				case "Hole":
					image=hole;
					break;
				case "Target":
					image=target;
					break;
				case "TrapDoor":
					TrapDoor td=(TrapDoor) f;
					if(td.GetActive()){
						image=trapdoor_open;
					}else{
						image=trapdoor_colse;
					}
					break;
				case "Button":
					image=button;
					break;
					
			}
			

			String[] separated=image.split("\\.");
			
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

		}else{
			switch(""+f.GetmyMoveable()){
				case "Player":
					Player p=(Player) f.GetmyMoveable();
					image=p.GetColor()+player;
					break;
				case "Wall":
					image=wall;
					break;
				case "Box":
					image=box;
					break;
			}
		}

		return image;
	}
}