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
 * Driver of the program
 *  @author leechy9
 */
public class Main {

    /**
     * Main thread of program sets engine refresh rate
     *  @param args the command line arguments
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
	
	//Sets the pmoffscreen to false to fix bug in openjdk rendering
	try{
		System.setProperty("sun.java2d.pmoffscreen", "false");
	}catch(Exception ex){
		ex.printStackTrace();
		System.out.println("Unable to change system properties!");
	}
        
        GameFrame frame = new GameFrame();
        
        Engine engine = new Engine(frame);

        while(true){
            try{
                engine.step();
                Thread.sleep(15);
            }catch(InterruptedException ex){}
        }
    }
}
