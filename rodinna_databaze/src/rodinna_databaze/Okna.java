package rodinna_databaze;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Lukáš Dastych
 */
public class Okna {
    
}

class OknoPolozkaMenuOProgramu extends JFrame {    
    public OknoPolozkaMenuOProgramu() {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("O programu");
        setBounds(400, 400, 250, 125);
        JTextArea ta = new JTextArea("Rodinná databáze. \n" 
                + "Především databáze knih. \n"
                + "Lukáš Dastych - 2013.");
        ta.setEditable(false);
        ta.setFont(new Font("Dialog", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        JPanel p = new JPanel();
        p.add(ta);
        getContentPane().add(p);        
    }
}
