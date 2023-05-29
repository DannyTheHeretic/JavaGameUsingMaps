import java.awt.event.*;
import java.util.*;
import structures.*;

public class GameLayout{

    private static MapGraph nMap;
    private static Monster nMonster;
    private static Player nPlayer;
    private static GUI nGui;
    private static Random rand;
/**
 * The main, does the Gui, and pushes to the setup
 */
    public static void main(String[] args) {
        nGui = new GUI(640, 1080);
        nGui.guiShow();
        nGui.instructions();
        gamePlay();
    }

/**
 * The main heart of the game, all things are thru here
 */
    public static void gamePlay(){
        nMap = new MapGraph("Map1.map", true);
        rand = new Random();
        nPlayer = new Player(0);
        nMonster = new Monster(7);
        nGui.tf.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_LEFT){
                    monsterCheck();
                    nPlayer.move(1, nMap);
                    System.out.println(nPlayer.getLoc());
                }else if(key == KeyEvent.VK_UP){
                    monsterCheck();
                    nPlayer.move(2, nMap);
                    System.out.println(nPlayer.getLoc());
                }else if(key == KeyEvent.VK_RIGHT){
                    monsterCheck();
                    nPlayer.move(3, nMap);
                    System.out.println(nPlayer.getLoc());
                }else if(key == KeyEvent.VK_DOWN){
                    monsterCheck();
                    nPlayer.move(4, nMap);
                    System.out.println(nPlayer.getLoc());
                }
                if(nPlayer.getLoc() == 10){
                    int r = nGui.dispBan("VICTORY");
                    if(r == 0){
                        KeyListener[] n = nGui.tf.getKeyListeners();
                        nGui.tf.removeKeyListener(n[0]);;
                        gamePlay();
                    }else{
                        KeyListener[] n = nGui.tf.getKeyListeners();
                        nGui.tf.removeKeyListener(n[0]);;
                        System.exit(0);
                    }
                }
                nGui.gps.setText(("Player Location: "+Integer.toString(nPlayer.getLoc())+"                     Monster Location: "+Integer.toString(nMonster.getLoc())));
                String p = "title"+rand.nextInt(5)+".png";
                nGui.back.setIcon(nGui.backgroundSet(p));
                
            }
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            @Override
            public void keyReleased(KeyEvent e) {}});
        
    }

/**
 * The procedural check for the monster
 */
    public static void monsterCheck(){
        //check the tile you are on
        if (nMonster.getLoc() == nPlayer.getLoc()) {
            nPlayer.setDead();
        }
        //move the monster
        nMonster.move(nMonster.nextMove(nMap, nPlayer.getLoc()));
        //check if the monster killed you
        if (nMonster.getLoc() == nPlayer.getLoc()) {
            nPlayer.setDead();
        }
        //the trap space
        if(nPlayer.getLoc() == 4)
            nPlayer.setDead();
        //the check if dead
        if(nPlayer.isDead()){
            int r = nGui.dispBan("YOU DIED");
            if(r == 0){
                KeyListener[] n = nGui.tf.getKeyListeners();
                nGui.tf.removeKeyListener(n[0]);;
                gamePlay();
            }else{
                KeyListener[] n = nGui.tf.getKeyListeners();
                nGui.tf.removeKeyListener(n[0]);;
                System.exit(0);
            }
        }
    }
}
