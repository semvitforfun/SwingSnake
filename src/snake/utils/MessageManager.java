

package snake.utils;

import java.awt.Component;
import javax.swing.JOptionPane;


public class MessageManager {

    public static void showInformMessage(Component comp, String message) {
        JOptionPane.showMessageDialog(comp, message, "Message Dialog", JOptionPane.PLAIN_MESSAGE);
    }
    
    public static int LosingMessage(){
        //default icon, custom title
        return JOptionPane.showConfirmDialog(
           null,
           "Would you like to try again?",
           "You lost!!!",
           JOptionPane.YES_NO_OPTION);
    }
    
    public static int nextLevelMessage(Integer currentLevel){
        //default icon, custom title
        return JOptionPane.showConfirmDialog(
           null,
           "Next Level?",
           ""+currentLevel+" level finished!!!",
           JOptionPane.YES_NO_OPTION);
    }
    
    public static void winMessage() {
        JOptionPane.showMessageDialog(null, "You are the winner!!!", "Congratulations!!!", JOptionPane.PLAIN_MESSAGE);
    }
}
