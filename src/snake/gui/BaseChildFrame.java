package snake.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;


public class BaseChildFrame extends JFrame {
    
    private JFrame parent = null;

    public BaseChildFrame(){
        this.setCloseOperation();
    }

    public void showFrame(JFrame parent) {
        this.parent = parent;
        parent.setVisible(false);
        super.setLocationRelativeTo(parent);
        super.setVisible(true);
    }
    
    public void closeFrame(){
        if (parent==null) {
            throw new IllegalArgumentException("parent frame must not be null!");
        }
        super.setVisible(false);
        parent.setVisible(true);
    }
    
    private void setCloseOperation(){
        super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        super.addWindowListener(new WindowAdapter() {
            
                @Override
                public void windowClosing(WindowEvent e) {
                    BaseChildFrame.this.closeFrame();
                }
            }
        );
    }

}
