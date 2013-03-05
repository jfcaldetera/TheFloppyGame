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
 * A move defining direction, distance, and number of turns to complete.
 * @author leechy9
 */
public class Move {

    private String action;
    private int dist, totalTurns, currentTurn, xDot, yDot;
    
    /**
     * Default constructor for Move
     * For shooting, the distance is the speed of the bullet, and the turns are how many shots to fire
     * @param a
     *  Action to do
     * @param dis
     *  Distance to move
     * @param tu
     *  Amount of turns to complete
     * @param yD 
     *  The y-distance for the bullet to move each turn
     */
    public Move(String a, int tu, int dis, int yD){
        action = a;
        dist = dis;
        totalTurns = tu;
        currentTurn = 0;
        xDot = dis;
        yDot = yD;
    }

    /**
     * Returns the direction
     * @return
     *  The String direction of the move
     */
    public String getDirection(){
        return action;
    }

    /**
     * Returns the distance
     * @return
     *  The int distance to move
     */
    public int getDistance(){
        return dist;
    }
    
    /**
     * Returns the x-distance for a dot
     * @return
     *  The int x-distance to move the dot
     */
    public int getDotXDist(){
        return xDot;
    }
    
    /**
     * Returns the y-distance for a dot
     * @return
     *  The int y-distance to move the dot
     */
    public int getDotYDist(){
        return yDot;
    }

    /**
     * The amount of turns for the move to take
     * @return
     *  The int amount of moves
     */
    public int getNumTurns(){
        return totalTurns;
    }

    /**
     * Returns the current number of the turn
     * @return
     *  int value of the turn number you are currently on
     */
    public int getTurn(){
        return currentTurn;
    }

    /**
     * Increments the currentTurn value by one, or sets it equal to 0 if at last turn
     */
    public void incrementTurn(){
        if(currentTurn<totalTurns)
            currentTurn++;
        else
            currentTurn=0;
    }

    /**
     * Sets the value of currentTurn
     * @param i
     *  The value to set currentTurn to
     */
    public void setTurn(int i){
        currentTurn = i;
    }

    /**
     * resets the turn count to 0
     */
    public void resetTurn(){
        currentTurn = 0;
    }
}
