/*
 * This file is part of TheFloppyGame.
 *
 * TheFloppyGame is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TheFloppyGame is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TheFloppyGame.  If not, see <http://www.gnu.org/licenses/>.
 */

package laserGame;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Paints the game scenario
 *  @author leechy9
 */
public class GamePanel extends JPanel{

    private int x, y, limitX, limitY, playerX, playerY, bgSlide, energyContainerNum, heatContainerNum, enemyContainerNum, enemyContainerNumTotal, heat, energy, hp;
    private boolean isShielded,isVenting, win, lose;
    private Image background, ship, gDot, bDot, rDot, shield, venting;
    private MediaTracker tracker;
    private Level lvl;
    private LinkedList<Dot> dotList;
    private ArrayList<Enemy> enemies;
    

    /**
     * Default Constructor
     */
    public GamePanel(Level ll) {
        super();
        win = false;
        lose = false;
        lvl = ll;
        background = lvl.getBackground();

        //Make below dynamic in future
        ship = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("images/blueShip.png"));

        gDot = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("images/greenDot.png"));

        bDot = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("images/blueDot.png"));

        rDot = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("images/redDot.png"));

        shield = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("images/roundShield.png"));

        venting = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("images/venting.png"));
                
        dotList = new LinkedList<Dot>();

        energyContainerNum=2000;
        heatContainerNum=0;

        tracker = new MediaTracker(this);
        tracker.addImage(background,0);
        tracker.addImage(ship,1);
        tracker.addImage(shield,2);
        tracker.addImage(venting,3);
        tracker.addImage(gDot,4);
        tracker.addImage(bDot,5);
        tracker.addImage(rDot,6);

        try {
            tracker.waitForAll();
        } catch (InterruptedException ex) {}
        //End of content to be dynamic

        this.setSize(800,750);

    }

    /**
     * Tells the program how to paint the background
     * @param g
     * takes a graphics object
     */
    @Override
    public void paint(Graphics g){

        //Turns on AntiAliasing
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(new Color(0,0,0));
        g.fillRect(0,0,800,750);
        //BACKGROUND, HP MONITOR, & SHIP
        g.drawImage(background,0,0+bgSlide,this);
        g.drawImage(background,x,y+bgSlide-1200,this);

        g.setColor(new Color(30,62,100));
        g.fillArc(32, 615, 70, 70, 0, (int)((hp/999.0)*360));
        g.setFont(new Font("Monospace", 20, 20));
        g.setColor(new Color(255,255,255));
        g.drawString(""+hp,50,655);
        g.setFont(new Font("Monospace", 10, 10));
        
        g.drawImage(ship, playerX, playerY, this);

        //ENEMIES
        for(int i=0;i<enemies.size();i++){
            Character c = enemies.get(i);
            g.drawImage(c.getImage(), c.getX(), c.getY(), this);
        }
            
        
        //ATTACKS
        try{
            Iterator<Dot> it = dotList.iterator();
            while(it.hasNext()){
                Dot tmpDot = it.next();
                g.drawImage(this.getDotImage(tmpDot.getColor()), tmpDot.getX(), tmpDot.getY(), this);
            }
        }catch(ConcurrentModificationException ex){}
            
        
        g.drawImage(ship, playerX, playerY, this);

        //VENTING
        if(isVenting){
            g.drawImage(venting,playerX, playerY, this);
        }
        //SHIELD
        if(isShielded){
            g.drawImage(shield,playerX, playerY, this);
        }

        //INTERFACE
        g.setColor(new Color(114,0,255));
        for(int i=0;i<20;i++){
            g.setColor(new Color(114,0,255));
            if(energyContainerNum<=i)
                g.drawRect(10,150+(i*15),30,10);
            else
                g.fillRect(10,150+(i*15),30,10);
            g.setColor(new Color(255,114,0));
            if(heatContainerNum<=i)
                g.drawRect(50,150+(i*15),20,10);
            else
                g.fillRect(50,150+(i*15),20,10);
        }


        if(enemyContainerNumTotal>0){
            g.setColor(new Color(30,225,0));
            g.drawRect(767, 150, 25, ((enemyContainerNumTotal*300)/enemyContainerNumTotal)+2);
            if(enemyContainerNum>0){
                g.fillRect(770, 152, 20, (enemyContainerNum*300)/enemyContainerNumTotal);
            }
        }
            
        g.setColor(new Color(100,100,100));
        g.drawRect(5,5,90,75);
        g.setColor(new Color(0,112,255));
        g.drawString("X-Coord: "+playerX,10,20);
        g.drawString("Y-Coord: "+playerY,10,30);
        g.setColor(new Color(0,200,0));
        g.drawString("Dots: "+dotList.size(),10,40);
        g.setColor(new Color(194,0,255));
        g.drawString("Energy: "+energy,10,50);
        g.setColor(new Color(255,114,0));
        g.drawString("Heat: "+heat,10,60);
        g.setColor(new Color(255,255,255));
        g.drawString("HP:"+hp,10,70);


        if(win){
            g.setColor(new Color(255,255,255));
            g.setFont(new Font("Monospace", 50, 50));
            g.drawString("You Win!",300,300);
        }
        if(lose){
            g.setColor(new Color(0,0,0));
            g.fillRect(0, 0, 800, 750);
            g.setColor(new Color(255,255,255));
            g.setFont(new Font("Monospace", 50, 50));
            g.drawString("You Lose!",290,350);
        }
    }

    /**
     * Sets the characters coordinates
     * @param newX
     *  takes in the X coordinate
     * @param newY
     *  takes in the X coordinate
     */
    public void setCoordinates(int newX, int newY){
        x=newX;
        y=newY;
    }

    /**
     * Sets the characters coordinates
     * @param newX
     *  takes in the X coordinate
     * @param newY
     *  takes in the X coordinate
     */
    public void setPlayerCoordinates(int newX, int newY){
        playerX=newX;
        playerY=newY;
    }

    /**
     * Sets if the shield should be drawn
     * @param b
     *  What the shield status should be set to
     */
    public void setShield(boolean b){
        isShielded=b;
    }

    /**
     * Sets if the vent should be drawn
     * @param b
     *  What the vent status should be set to
     */
    public void setVenting(boolean b){
        isVenting=b;
    }

    /**
     * Sets the list of Dots to be drawn.
     * @param d
     *  What the playerDotList should be set to.
     */
    public void setDots(LinkedList<Dot> d){
        dotList=d;
    }

    /**
     * Sets the player's energy.
     * @param i
     *  What the energy should be set to.
     */
    public void setEnergy(int i){
        energy=i;
    }
    
    /**
     * Sets the player's energy.
     * @param i
     *  What the energy should be set to.
     */
    public void setHeat(int i){
        heat=i;
    }

    /**
     * Sets the panel's hp monitor value
     * @param i
     *  The value to set the panel's hp monitor to
     */
    public void setHP(int i){
        hp=i;
    }
    /**
     * Sets the player's energy.
     * @param i
     *  What the energy should be set to.
     */
    public void setEnergyContainer(int i){
        energyContainerNum=i;
    }

    /**
     * Sets the player's energy.
     * @param i
     *  What the energy should be set to.
     */
    public void setHeatContainer(int i){
        heatContainerNum=i;
    }

    /**
     * Returns the boundaries of the picture
     * @return
     *  Returns X amt of pixels
     */
    public int getLimitX(){
        limitX = background.getWidth(this);
        return limitX;
    }

    /**
     * Returns the boundaries of the picture
     * @return
     *  Returns Y amt of pixels
     */
    public int getLimitY(){
        limitY = background.getHeight(this);
        return limitY;
    }

    /**
     * Manages the background's scrolling.
     */
    public void setSlide(){
    	if(bgSlide>=800)
    		bgSlide=0;
    	else
    		bgSlide++;
    }

    /**
     * Sets the enemies to be drawn.
     * @param a
     *  What the enemy list should hold.
     */
    public void setEnemies(ArrayList<Enemy> a){
        enemies = a;
    }

    /**
     * Takes in a string to determine the color of the dot
     * @param s
     *  The color to change it to
     * @return
     *  The image in the color you specified
     */
    public Image getDotImage(String s){

        if(s.equalsIgnoreCase("blue"))
            return bDot;
        else if(s.equalsIgnoreCase("red"))
            return rDot;
        else if(s.equalsIgnoreCase("green"))
            return gDot;
        else
            return gDot;
    }
    
    /**
     * Sets the background of the game
     * @param ll
     *  The Level to pass to the panel
     */
    public void setLevel(Level ll){
        lvl = ll;
        background = lvl.getBackground();
    }

    /**
     * Sets the value to display of the last enemy's hp meter
     * @param i
     *  The int value of the enemy's hp
     * @param tot
     *  The int value of the enemy's total hp
     */
    public void setEnemyHPMeter(int i, int tot){
        enemyContainerNum = i;
        enemyContainerNumTotal = tot;
    }

    /**
     * Sets the panel to the win status or not
     * @param b
     *  The boolean value saying if the player has won
     */
    public void setWin(boolean b) {
        win = b;
    }

    /**
     * Sets the panel to the lose status or not
     * @param b
     *  The boolean value saying if the player has lost
     */
    public void setLose(boolean b) {
        lose = b;
    }
}
