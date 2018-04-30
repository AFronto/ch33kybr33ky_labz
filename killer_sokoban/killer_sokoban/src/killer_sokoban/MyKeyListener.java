package killer_sokoban;

import javax.swing.JFrame;
import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static killer_sokoban.Game.*;

public class MyKeyListener implements KeyListener
{
	private Player p;
	private ArrayList<Integer> mykeys;

	public MyKeyListener(Player p,ArrayList<Integer> mykeys){
        super();
        this.mykeys=new ArrayList<Integer>();
        for(Integer k: mykeys){
        	this.mykeys.add(k);
        }
        this.p=p;
    }

    @Override
	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==mykeys.get(0)){
            p.Control(null, Direction.UP, 0);
        	getMyMap();
        }else if(e.getKeyCode()==mykeys.get(1)){
        	p.Control(null, Direction.DOWN, 0);
        	getMyMap();
        }else if(e.getKeyCode()==mykeys.get(2)){
        	p.Control(null, Direction.LEFT, 0);
        	getMyMap();        	
        }else if(e.getKeyCode()==mykeys.get(3)){
        	p.Control(null, Direction.RIGHT, 0);
        	getMyMap();        	
        }else if(e.getKeyCode()==mykeys.get(4)){
        	p.changeFriction();
        	getMyMap();        	
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}