import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicTextUI.BasicCaret;

import structures.*;

import java.awt.*;
import java.util.*;

public class GUI {
    public JFrame mainFrame = new JFrame();

    public JTextField tf = new JTextField();
    public JButton saveButton = new JButton("Save");
    public JButton loadButton = new JButton("Load");
    public JButton newGameButton = new JButton("New");

    public JPanel movePanel = new JPanel();
    public JPanel winPanel = new JPanel();
    public JPanel data = new JPanel();
    
    public JLabel gps = new JLabel();
    public JLabel vic = new JLabel("VICTORY");
    public JLabel back;
    /**
     * Sets up the gui
     * @param x X cord
     * @param y Y cord
     */
    public GUI(int x, int y){
        update(7, 0);
        mainFrame.setSize(x,y);
        mainFrame.setBackground(Color.BLACK);
        saveButton.setSize(25,25);
        loadButton.setSize(25,25);
        newGameButton.setSize(25,25);

        movePanel.setBorder(BorderFactory.createEmptyBorder(100, (x/2)-150, 100, (x/2)-150));
        movePanel.setLayout(new GridLayout(3, 3));
        movePanel.setBackground(Color.BLACK);
        
        mainFrame.add(tf);
        mainFrame.add(backHelper(backgroundSet("titleImg.png")));
        movePanel.add(saveButton);
        movePanel.add(loadButton);
        movePanel.add(newGameButton);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Gaem");
        mainFrame.add(movePanel, BorderLayout.SOUTH);
        
    }
    /**
     * Gets an Icon Image for the background
     * @param name The String to the file
     * @return ImageIcon - a file type that JLabel Uses
     */
    public ImageIcon backgroundSet(String name){
        ImageIcon background = new ImageIcon((name));
        Image img=background.getImage();
        Image temp=img.getScaledInstance(640,1080,Image.SCALE_SMOOTH);
        background =new ImageIcon(temp);
        return background;
    }
    /**
     * Helps make the JLabel for the background
     * @param i Image Icon made by the set
     * @return A JLabel, its the background
     */
    public JLabel backHelper(ImageIcon i){
        this.back=new JLabel(i);
        this.back.setLayout(null);
        this.back.setBounds(0,0,640,1080);
        return back;
    }

    /**
     * the initialization of the GPS system
     * @param monsterLoc the monster location
     * @param playerLoc the player location
     */
    public void update(int monsterLoc, int playerLoc){
        gps = new JLabel();
        gps.setFont(new FontUIResource("Comic Sans", 1, 18));
        gps.setText(("Player Location: "+Integer.toString(playerLoc)+"                     Monster Location: "+Integer.toString(monsterLoc)));
        this.data.add(gps);
        mainFrame.add(data,BorderLayout.NORTH);
    }


/**
 * Asks you if you want to play again
 * @param x The outcome of that play session
 * @return quits or keeps going
 */
    public int dispBan(String x){
        String[] options = new String[] {"Yes", "No"};
        int response = JOptionPane.showOptionDialog(null,new JLabel(x + "\n \nPlay Again?",JLabel.CENTER), "Are You Winning Son?",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
        return response;
    }
/**
 * Sets the Gui Visible
 */
    public void guiShow(){
        mainFrame.setVisible(true);
    }
/**
 * Instruction Manuel
 */
    public void instructions(){
        JOptionPane.showMessageDialog(null, "MOST FEATURES ARE A WIP -Save, New Game, and Load- all on the way \n You use the arrow keys to move any direction, it is really hard if you dont know the path. \n you might die :)", "Are You Winning Son?", JOptionPane.WARNING_MESSAGE);
    }
}
