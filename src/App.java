import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.Timer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class App extends JPanel{

    private static final String title = "Brute Force Maze Solver";

    //dimensions for the viewer
    public static final int WIDTH = 600, HEIGHT = 600;

    //how many tiles fit from one side to the other of the viewer
    public static final int tileRadius = 30, tileSize = WIDTH/tileRadius;

    //hashmap storing the tiles with their grid location
    private HashMap<Dimension,Tile> tileMap;

    //for the basic search algorithm
    private boolean continueLoop = true;

    //animation delay (milliseconds)
    public static final int delay = 100;

    private int success=3;

    private Controls controls;
    
    public App()
    {
        controls = new Controls();
        setup();
        generateTiles();
        
    }


    public void generateTiles()
    {
        tileMap = new HashMap<Dimension,Tile>();
        //generates hashmap with all the buttons and adds them to the panel
        addTiles(true);
        
    }
    private void addTiles(boolean random)
    {
        for(int i = 0; i<=tileRadius;i++)
        {
            for (int ii=0;ii<tileRadius;ii++)
            {
                Dimension dimension = new Dimension(i,ii);
                Tile tile = new Tile(dimension,random);
                tileMap.put(dimension, tile);
                this.add(tileMap.get(dimension));
            }
        }
        this.updateUI();
        continueLoop = true;
        
    }
    public void removeTiles()
    {
        Collection<Tile> collection= tileMap.values();
        for(Iterator<Tile> iterator = collection.iterator(); iterator.hasNext();)
        {
            this.remove(iterator.next());
        }
        controls.updateIndicator(3);
    }

    private void setup()
    {
        this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);

    }

    public void startSearch()
    {
        success = 0;
        ArrayList<Tile> searching = new ArrayList<Tile>();
        ArrayList<Tile> newList = new ArrayList<Tile>();
        searching.add(tileMap.get(new Dimension(0,0)));
        int animationDelay =0;
        while(continueLoop){
        for(int i =0;i<searching.size();i++)
        {
            
            Tile tile1 = searching.get(i);
            for(int ii=0;ii<4;ii++)
            {
                Tile tile2 = tileMap.get(tile1.getNeighbour(ii));
                if(tile2 !=null) // checks if the neighbor exists then if its a nonsolid tile it sets it to searching
                {
                    TileState type = tile2.getState();
                    if(type == TileState.NONSOLID)
                    {
                        
                    type = tile2.setSearching(animationDelay);
                    newList.add(tile2);
                    } else if(type == TileState.FINISH)
                    { 
                    success = 1;
                    conclusion();
                    
                    }
                }
            }
        }
        animationDelay++;
        if(newList.size() >0)
        {
            searching = newList;
        newList = new ArrayList<Tile>();
        } else if (success == 0) conclusion();
    
        } 
    
}
    public void clearSearchTiles()
    {
        Collection<Tile> collection= tileMap.values();
        for(Iterator<Tile> iterator = collection.iterator(); iterator.hasNext();)
        {
            iterator.next().setNonSolid();
        }
        continueLoop = true;
        controls.updateIndicator(3);
    }

    public Dimension findStartTile()
    {
        Collection<Tile> collection= tileMap.values();
        for(Iterator<Tile> iterator = collection.iterator(); iterator.hasNext();)
        {
            Tile t = iterator.next();
            if(t.getState() == TileState.START)
            {
                return t.getDimension();
            }
        }
        return new Dimension(0,0);
    }
    public void savePreset(int presetNumber)
    {
        PresetReader.savePreset(presetNumber, tileMap);
    }

    private void conclusion()
    {
        continueLoop = false;
        controls.updateIndicator(success);
    }
    
    public void loadPreset(int presetNumber)
    {
        HashMap<Dimension,Tile> tempMap = PresetReader.getPreset(presetNumber);
        removeTiles();
        for(int i = 0; i<=tileRadius;i++)
        {
            for (int ii=0;ii<tileRadius;ii++)
            {
                Dimension dimension = new Dimension(i,ii);
                Tile tile = tempMap.get(dimension);
                tileMap.put(dimension, tile);
                this.add(tileMap.get(dimension));
            }
        }
        this.updateUI();
        continueLoop = true;
    }
    public Controls getControls()
    {
        return controls;
    }
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(title);

        JFrame frame = new JFrame(title);
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        App app = new App();

        main.add(app);
        main.add(app.getControls());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
        frame.add(main);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

    }
}
