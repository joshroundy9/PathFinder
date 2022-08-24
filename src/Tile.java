import java.awt.Color;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Tile extends JButton{


    //state of this tile
    private TileState state = TileState.NONSOLID;


    //private Color solid = Color.BLACK, nonsolid = Color.white;
    private ImageIcon start = new ImageIcon("startIcon.png"),
     finish = new ImageIcon("finishIcon.png"), 
     searching = new ImageIcon("searchIcon.png"),
     solid =  new ImageIcon("solidIcon.png"),
     nonsolid = new ImageIcon("nonsolidIcon.png");

    private int gridx,gridy,tileNumber;

    //timer for animation
    private Timer timer;

    public Tile(Dimension d,boolean random)
    {
        this.setHorizontalAlignment(SwingConstants.CENTER);
        gridx = (int)d.getWidth();
        gridy = (int)d.getHeight();
        tileNumber = (gridx*21)+gridy+1;
        //this.setText((tileNumber)+"");
        this.setPreferredSize(new Dimension(App.tileSize,App.tileSize));
        Random r = new Random();
        this.setIcon(nonsolid);

        if(random)
        {
            if(tileNumber ==1)
            {
               setStart();
            } else if(r.nextInt(3)==0){
                this.setIcon(solid);
                state = TileState.SOLID;
            }
            if(tileNumber == 639)
            {
                setFinish();
            }
        }


        this.setMargin(new Insets(0,0,0,0));
        this.setBorder(null);

        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                flipState();
            }
        });
    }
    public int getTileNumber()
    {
        return tileNumber;
    }
    public TileState getState()
    {
        return state;
    }
    public void flipState()
    {
        switch (state)
        {
            case NONSOLID:
            state = TileState.SOLID;
            this.setIcon(solid);
            break;
            case SOLID:
            state = TileState.FINISH;
            this.setIcon(finish);
            break;
            case FINISH:

            state = TileState.NONSOLID;
            this.setIcon(nonsolid);
            break;
        }
    }
    public void setNonSolid()
    {
        if(state == TileState.SEARCHING){
        this.setIcon(nonsolid);
        state = TileState.NONSOLID;
        timer.stop();
        }
    }
    public void setSolid()
    {
        this.setIcon(solid);
            state = TileState.SOLID;
    }
    public void setStart()
    {
        this.setIcon(start);
        state = TileState.START;
    }
    public void setFinish()
    {
        this.setIcon(finish);
        state = TileState.FINISH;
    }
    public TileState setSearching(int animationOrder)
    {
        if(state == TileState.NONSOLID)
        {
            state = TileState.SEARCHING;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    setSearchingIcon();
                }
            };
            timer = new Timer(animationOrder*App.delay,taskPerformer);
            timer.start();
            
        } 
        return state;
    }
    private void setSearchingIcon()
    {
        this.setIcon(searching);
    }
    public Dimension getDimension()
    {
        return new Dimension(gridx,gridy);
    }
    /**
     * Finds the coordinates of the tiles neighbors
     * @param number
     * @return
     * 
     */
    public Dimension getNeighbour(int number)
    {
        //neighbor 0 is left, 1 up, 2 right, and 3 down
        switch(number)
        {
            case 0: 
            if(gridx-1>=0)
            {
                return new Dimension(gridx-1,gridy);
            }
            break;
            case 1: 
            if(gridy+1<App.tileRadius)
            {
                return new Dimension(gridx,gridy+1);
            }
            break;
            case 2: 
            if(gridx+1<App.tileRadius)
            {
                return new Dimension(gridx+1,gridy);
            }
            break;
            case 3: 
            if(gridy-1>=0)
            {
                return new Dimension(gridx,gridy-1);
            }
            break;
        }
        return null;
    }
    
}
