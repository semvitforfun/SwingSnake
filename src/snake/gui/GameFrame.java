package snake.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import snake.game.controller.PlayGame;
import snake.game.enums.Direction;
import snake.game.objects.options.DBOptions;
import snake.gui.map.DrawableMap;


public class GameFrame extends BaseChildFrame implements KeyListener{
    private JPanel jPanelMap = new JPanel();
    
    private PlayGame game;
    
    public GameFrame(int x,  int y, PlayGame game) {
        int height = 26*x+10;
        int width = 26*y;
        this.game = game;
        
        setTitle("Snake");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuFrame.class.getResource("/snake/images/snake.png") ));
        
        if ( System.getProperty("os.name").startsWith("Windows")) {
            height += 25;
            width += 10;
        }
        
        setSize(new Dimension(width, height));
        setResizable(false);
        
        jPanelMap.setSize(new Dimension(width, height));
        this.add(jPanelMap);
        addKeyListener(this);    
    }
    
    public void setMap(DrawableMap drawMap) {
        jPanelMap.removeAll();
        jPanelMap.add(drawMap.getMapComponent());
    }

    @Override
    public void closeFrame() {
        super.closeFrame(); 
        game.stop();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case VK_LEFT:
                game.getSnake().setDirection(Direction.LEFT);
                break;
            case VK_RIGHT:
                game.getSnake().setDirection(Direction.RIGHT);
                break;
            case VK_UP:
                game.getSnake().setDirection(Direction.UP);
                break;
            case VK_DOWN:
                game.getSnake().setDirection(Direction.DOWN);
                break;
            case VK_SPACE:
                if (game.isRunning()){
                    game.stop();
                } else {
                    game.start();    
                }
                break;                
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}