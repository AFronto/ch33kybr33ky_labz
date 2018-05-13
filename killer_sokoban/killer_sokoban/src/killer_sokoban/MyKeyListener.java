package killer_sokoban;

import javax.swing.JFrame;
import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static killer_sokoban.Game.*;

/**
 *A KeyListener interfacet implemetalo sajat Listenerem 4 fele iranyito billentyu plusz a keno anyag felvitel billentyu es a kilepesert felelos 
 *ESC lenyomasat hivatott kezelni. 
 */
public class MyKeyListener implements KeyListener
{
	private Player p;
	private ArrayList<Integer> mykeys;

    private static boolean endflag;

    /**
     * Konstruktor
     *
     * @param p     A Player akit az adott gombok iranyitanak 
     * @param mykeys Az 5 gomb ami iranyitja az adott Playert
     */
	public MyKeyListener(Player p,ArrayList<Integer> mykeys){
        super();
        this.mykeys=new ArrayList<Integer>();
        for(Integer k: mykeys){
        	this.mykeys.add(k);
        }
        this.p=p;
        endflag=false;
    }

    /**
     *A KeyListener gomb lenyomasert felelos fuggvenyenek megvalositasa
     *
     * @param e  Ez a gombnyomas event ami kivakltja az egesz fuggvenyt.
     */
    @Override
	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==mykeys.get(0)){      
            p.Control(null, Direction.UP, 0);      //A felfele mozgast elindito gomb hatasara felfele lep es kirajzolja a mapot ujra
        	getMyMap();
        }else if(e.getKeyCode()==mykeys.get(1)){
        	p.Control(null, Direction.DOWN, 0);    //A lefele mozgast elindito gomb hatasara lefele lep es kirajzolja a mapot ujra
        	getMyMap();
        }else if(e.getKeyCode()==mykeys.get(2)){
        	p.Control(null, Direction.LEFT, 0);    //A balra mozgast elindito gomb hatasara balra lep es kirajzolja a mapot ujra
        	getMyMap();        	
        }else if(e.getKeyCode()==mykeys.get(3)){
        	p.Control(null, Direction.RIGHT, 0);   //A jobra mozgast elindito gomb hatasara jobra lep es kirajzolja a mapot ujra
        	getMyMap();        	
        }else if(e.getKeyCode()==mykeys.get(4)){    
        	p.changeFriction();                    //A kenoanyag valto gomb hatasara kenoanyagot valt es kirajzolja a mapot ujra
        	getMyMap();        	
        }else if(!endflag&&e.getKeyCode()==KeyEvent.VK_ESCAPE){
                endflag=true;                      //Esc gombbal ki lehet lepni a jatek kozben ilyenkor a jatek autiomatikusan befejezodik es a jatek vege kepernyo ugrik fel
                EndGame();
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}