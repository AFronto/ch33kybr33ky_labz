package killer_sokoban;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.lang.Math;

import static killer_sokoban.Game.*;

public class Map {
	
	private Field[][] fields;
	
	private int[][] maze;
	private int width;
	private int height;
	private int corridorLength;
	private double roomChance;

	/**
	 *Konstruktor
	 */
	public Map(){
		printOnConstruct("Map");
		printOnExitConstuctor("Map");
		width=11;
		height=11;
		corridorLength=2;
		roomChance=1;
	}

	/**
	 * Generalja a palyat.
	 *
	 * @param playerCount a jatekosok szama
	 */
	public void CreateMap(int playerCount){
		
		fields=new Field[height][width];
		
		for (int y = 0; y < height; y++)
        {	
            for (int x = 0; x < width; x++)
            {
            	fields[y][x]=new Field();
            	if(x-1>0){
           			fields[y][x-1].SetNeighbour(Direction.RIGHT,fields[y][x]);
           			fields[y][x].SetNeighbour(Direction.RIGHT,fields[y][x-1]);
           		}
           		if(y-1>0){
           			fields[y-1][x].SetNeighbour(Direction.RIGHT,fields[y][x]);
           			fields[y][x].SetNeighbour(Direction.RIGHT,fields[y-1][x]);
           		}
            }
        }
		generateMazeBase();
		for (int y = 0; y < height; y++)
        {	
            for (int x = 0; x < width; x++)
            {
            	if(maze[y][x]==0){
            		fields[y][x].Register(null);
            	}else{
            		fields[y][x].Register(new Wall());
            	}
            }
        }

        setBoxNum(0);

        for (int y = 0; y < height; y++)
        {	
            for (int x = 0; x < width; x++)
            {
            	if(fields[y][x].GetmyMoveable()==null){
            		System.out.print("  ");
            	}else{
            		System.out.print("0 ");
            	}
            }
            System.out.println();
        }
		/*printOnEntry(this,"CreateMap",playerCount+"");
		String[] newFieldStrings = {"1. Field",
		"2. Hole", "3. TrapDoor","4. Button","5. Target","6. Vegeztem az epitessel"};


		boolean buildingMap = true;
		
		while(buildingMap){
			for (String s : newFieldStrings){
				printOption(s);
			}
			int sel = printQuestion("Milyen mezot akarsz rakni?", 1, 6);
			switch (sel) {
				case 1:
					Field f= new Field();
					f.SetNeighbour(Direction.UP,f);
					f.SetNeighbour(Direction.DOWN,f);
					f.SetNeighbour(Direction.LEFT,f);
					f.SetNeighbour(Direction.RIGHT,f);
					MovableOption(f);
					////f.GetNeighbour(Direction.UP);    ////teszt
					break;
				case 2:
					Hole h= new Hole();
					h.SetNeighbour(Direction.UP,h);
					h.SetNeighbour(Direction.DOWN,h);
					h.SetNeighbour(Direction.LEFT,h);
					h.SetNeighbour(Direction.RIGHT,h);
					break;
				case 3:
					TrapDoor t= new TrapDoor();
					t.SetNeighbour(Direction.UP,t);
					t.SetNeighbour(Direction.DOWN,t);
					t.SetNeighbour(Direction.LEFT,t);
					t.SetNeighbour(Direction.RIGHT,t);
					MovableOption(t);
					break;
				case 4:
					Button b= new Button();
					b.SetNeighbour(Direction.UP,b);
					b.SetNeighbour(Direction.DOWN,b);
					b.SetNeighbour(Direction.LEFT,b);
					b.SetNeighbour(Direction.RIGHT,b);
					MovableOption(b);
					break;
				case 5:
					Target ta= new Target();
					ta.SetNeighbour(Direction.UP,ta);
					ta.SetNeighbour(Direction.DOWN,ta);
					ta.SetNeighbour(Direction.LEFT,ta);
					ta.SetNeighbour(Direction.RIGHT,ta);
					MovableOption(ta);
					break;
				case 6:
					buildingMap = false;
					break;
	
			}
			
		}
		printOnExit(this,"CreateMap",null);*/
	}
	
	public Field getByIndex(int i, int j)
	{
		return fields[i][j];
	}
	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////

	/**
	 *A tesztelo valszt hogy mit tesz az adott mezore
	 *
	 *@param f A mezo amire pakol a tesztelo.
	 */

	public void MovableOption(Field f){
		String[] movableStrings = {"1. Player","2. Box","3. Wall","4. Ures"};
		
		for (String s : movableStrings){
			printOption(s);
		}
		int sel = printQuestion("Mi legyen rajta?", 1, 4);
		switch (sel) {
			case 1:
				Player p = new Player(4);
				f.Register(p);
				break;
			case 2:
				Box b = new Box();
				f.Register(b);
				break;
			case 3:
				Wall w = new Wall();
				f.Register(w);
				break;
			case 4:
				f.Register(null);
				break;

		}
	}

	/*
	 * Generalunk egy palyat, a megadott meretekkel.
	 */
	public void generateMazeBase()		
    {
        maze = new int[height][ width];
        // Initialize
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                maze[i][j] = 1;
            }
        }

        int r = ThreadLocalRandom.current().nextInt(0, height);
        while(r%2 == 0)
        {
            r = ThreadLocalRandom.current().nextInt(0, height);
        }

        int c = ThreadLocalRandom.current().nextInt(0, width);
        while (c % 2 == 0)
        {
            c = ThreadLocalRandom.current().nextInt(0, width);
        }

        maze[r][c] = 0;
        
        recursion(r, c);
    }

    public void recursion(int r, int c)
    {
        // 4 random directions
        ArrayList<Integer> randDirs = generateRandomDirections();
        // Examine each direction
        for (int i = 0; i < randDirs.size(); i++)
        {
            switch (randDirs.get(i))
            {
                case 0: // Up
                        //　Whether 2 cells up is out or not
                    if (r-corridorLength <= 0)
                        continue;
                    if (maze[r -corridorLength][c] != 0)
                    {
                       
                        for (int act = 1; act < corridorLength + 1; act++)
                        {
                            maze[r - act][c] = 0;
                            if (act == corridorLength / 2)
                            {
                                GenRoom(r - act, c);
                            }
                        }
                        recursion(r - corridorLength, c);
                    }
                    break;
                case 1: // Right
                        // Whether 2 cells to the right is out or not
                    if (c +corridorLength >= width - 1)
                        continue;
                    if (maze[r][c+corridorLength] != 0)
                    {
                        for (int act = 1; act < corridorLength + 1; act++)
                        {
                            maze[r][c + act] = 0;
                            if (act == corridorLength / 2)
                            {
                                GenRoom(r, c + act);
                            }
                        }
                        recursion(r, c + corridorLength);
                    }
                    break;
                case 2: // Down
                        // Whether 2 cells down is out or not
                    if (r + corridorLength >= height - 1)
                        continue;
                    if (maze[r + corridorLength][c] != 0)
                    {
                        for (int act = 1; act < corridorLength + 1; act++)
                        {
                            maze[r + act][c] = 0;
                            if (act == corridorLength / 2)
                            {
                                GenRoom(r + act, c);
                            }
                        }

                        recursion(r + corridorLength, c);
                    }
                    break;
                case 3: // Left
                        // Whether 2 cells to the left is out or not
                    if (c - corridorLength <= 0)
                        continue;
                    if (maze[r][c - corridorLength] != 0)
                    {   
                        for(int act =1; act< corridorLength+1; act++)
                        {
                            maze[r][c - act] = 0;
                            if (act == corridorLength / 2)
                            {
                                GenRoom(r, c - act);
                            }
                        }
                        recursion(r, c - corridorLength);
                    }
                    break;
            }
        }
    }

    public ArrayList<Integer> generateRandomDirections()
    {
        ArrayList<Integer> MyArray = new ArrayList<Integer>();
        for (int i =0; i < 4; i++)
        {
            int value = ThreadLocalRandom.current().nextInt(0, 4);
            while (MyArray.contains(value))
            {
                value = ThreadLocalRandom.current().nextInt(0, 4);
            }
            MyArray.add(value);
        }

        return MyArray;
    }


    public void GenRoom(int x,int y)
    {
        double chanche = Math.random();
        if (chanche<roomChance)
        {
            for (int i = -corridorLength / 2; i < corridorLength / 2; i++)
            {
                for (int j = -corridorLength / 2; j < corridorLength / 2; j++)
                {	
                	if(x+i>0 && x+i<width-1 && y+j>0 && y+j<height-1){
                    	maze[x + i][y + j] = 0;
                	}
                }
            }
        }
    }
}

