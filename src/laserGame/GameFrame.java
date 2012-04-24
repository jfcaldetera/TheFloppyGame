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