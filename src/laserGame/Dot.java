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

/**
 * Dot or "Default Bullet"
 * @author leechy9
 */
public class Dot {
    private double x, y;
    private String color;
    private double xChange, yChange;
    private boolean isBad, isDead;

    /**
     * Constructs a Dot at (X,Y)
     * @param b
     *  The boolean value determining whether the dot is going evil or not.
     * @param xx
     *  The X Coordinate for the Dot
     * @param yy
     *  The Y Coordinate for the Dot
     * @param xc
     *  The amount the X Coordinate changes by each frame.
     * @param yc
     *  The amount the Y Coordinate changes by each frame.
     * @param s
     *  The color of the dot to fire (red, green blue).
     */
    public Dot(boolean b, int xx, int yy, int xc, int yc, String s){
        isBad=b;
        x=xx;
        y=yy;
        xChange=xc*0.1;
        yChange=yc*0.1;
        color=s;
    }

    /**
     * Moves the bullet along it's path
     */
    public void step(){
            y+=yChange;
            x+=xChange;
    }
    /**
     * Sets Dot's X Coordinate
     * @param xx
     *  What X should be set to.
     */
    public void setX(int xx){
        x=xx;
    }

    /**
     * Sets Dot's Y Coordinate
     * @param yy
     *  What Y should be set to.
     */
    public void setY(int yy){
        y=yy;
    }

    /**
     * Returns the Dot's X coordinate.
     * @return
     *  The Dot's X coordinate.
     */
    public int getX(){
        return (int)x;
    }

    /**
     * Returns the Dot's Y coordinate.
     * @return
     *  The Dot's Y coordinate.
     */
    public int getY(){
        return (int)y;
    }

    /**
     * Returns the dot's current color
     * @return
     *  The dot's color
     */
    public String getColor(){
        return color;
    }

    /**
     * Tells if the dot is bad or not
     * @return
     *  The boolean value saying if the dot is bad or not
     */
    public boolean isBad(){
        return isBad;
    }

    /**
     * Tells if the dot is dead or not
     * @return
     *  The boolean value saying if the dot is dead or not
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets the dot to dead or not
     * @param b
     *  The boolean value indicating if the dot is dead
     */
    public void setDead(boolean b){
        isDead = b;
    }
}
