import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
public class Controls extends JPanel{
    private static final int WIDTH = 600, HEIGHT = 90;

    private JButton start=new JButton("Start"), clear = new JButton("Clear"),randomizeMaze = new JButton("Random Maze"),exit = new JButton("Exit");
    private JButton preset1 = new JButton("Preset 1"),preset2 = new JButton("Preset 2"),preset3 = new JButton("Preset 3");
    private JLabel successLabel = new JLabel("Run Inconclusive");

    private JFrame frame;
    private JPanel successIndicator;

    public Controls()
    {
        setup();
        
        System.out.println("imworking");

    }
    private void setup()
    {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        start.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/2-7));
        clear.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/2-7));
        randomizeMaze.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/2-7));
        exit.setPreferredSize(new Dimension(WIDTH/4,HEIGHT/2-7));

        //top part of controls
        JPanel mainControls = new JPanel();

        mainControls.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        mainControls.setPreferredSize(new Dimension(WIDTH,HEIGHT/3));

        mainControls.add(start);
        mainControls.add(clear);
        mainControls.add(randomizeMaze);
        mainControls.add(exit);
        this.add(mainControls);

        //middle part of controls
        JPanel presetSelect = new JPanel();

        presetSelect.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        presetSelect.setPreferredSize(new Dimension(WIDTH,HEIGHT/3));

        preset1.setPreferredSize(new Dimension(WIDTH/3,HEIGHT/2-7));
        preset2.setPreferredSize(new Dimension(WIDTH/3,HEIGHT/2-7));
        preset3.setPreferredSize(new Dimension(WIDTH/3,HEIGHT/2-7));

        presetSelect.add(preset1);
        presetSelect.add(preset2);
        presetSelect.add(preset3);

        this.add(presetSelect);

        //bottom success indicator
        successIndicator = new JPanel();
        successIndicator.setPreferredSize(new Dimension(WIDTH,HEIGHT/3));
        successIndicator.setBackground(Color.DARK_GRAY);

        successLabel.setBackground(Color.black);
        successLabel.setFont(new Font("Montserrat",Font.BOLD,15));
        successIndicator.add(successLabel);
        
        this.add(successIndicator);

        addListener();
    }

    /**Adds listeners to buttons, to save presets simply change the preset buttons from loadPreset(presetNumber) to savePreset(presetNumber)
     * 
     */
    public void addListener()
    {
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                startSearch();
            }
        });
        clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                resetTiles();
            }
        });
        randomizeMaze.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                randomizeMaze();
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                exit();
            }
        });
        //preset listeners 
        preset1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                loadPreset(1);
            }
        });
        preset2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                loadPreset(2);
            }
        });
        preset3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                loadPreset(3);
            }
        });
    }
    public void updateIndicator(int success)
    {
        if (success==1)
        {
            successLabel.setText("Run Successful");
            successIndicator.setBackground(Color.green);
        } else if(success==0)
        {
            successLabel.setText("Run Failed");
            successIndicator.setBackground(Color.red);
        } else {
            successLabel.setText("Run Inconclusive");
            successIndicator.setBackground(Color.darkGray);
        }
    }
    public void startSearch()
    {
        frame = (JFrame) SwingUtilities.windowForComponent(this);
  
        App app = (App) (frame.getContentPane().getComponent(0)).getComponentAt(0, 0);
        
        app.startSearch();
    }
    public void resetTiles()
    {
        
        frame = (JFrame) SwingUtilities.windowForComponent(this);
  
        App app = (App) (frame.getContentPane().getComponent(0)).getComponentAt(0, 0);
        
        app.clearSearchTiles();
    }
    public void randomizeMaze()
    {
        frame = (JFrame) SwingUtilities.windowForComponent(this);
  
        App app = (App) (frame.getContentPane().getComponent(0)).getComponentAt(0, 0);
        
        app.removeTiles();
        app.generateTiles();
    }
    public void exit()
    {
        frame = (JFrame) SwingUtilities.windowForComponent(this);
        frame.dispose();
        System.exit(0);
    }
    public void loadPreset(int presetNumber)
    {
        frame = (JFrame) SwingUtilities.windowForComponent(this);
  
        App app = (App) (frame.getContentPane().getComponent(0)).getComponentAt(0, 0);
        
        app.loadPreset(presetNumber);
    }
    public void savePreset(int presetNumber)
    {
        frame = (JFrame) SwingUtilities.windowForComponent(this);
  
        App app = (App) (frame.getContentPane().getComponent(0)).getComponentAt(0, 0);
        
        app.savePreset(presetNumber);
    }
}
