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
import java.awt.event.*;

/**
 * Holds the Game Panel
 *  @author leechy9
 */
public class GameFrame extends JFrame implements ActionListener{

    /**
     * Default Constructor
     */
    @SuppressWarnings("static-access")
    public GameFrame() {
        super("The Floppy Game");
        this.setSize(800,750);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    /**
     * Listens for button presses
     * @param e
     *  takes in action event
     */
    @Override
    public void actionPerformed(ActionEvent e){

    }
}
