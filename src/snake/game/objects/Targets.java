package snake.game.objects;

import java.util.Random;
import snake.game.field.GameField;


public class Targets{
    
    private Boolean[][] tableContent;
    private GameField gameField;
    private Coordinate coord = new Coordinate(0, 0);


    public Targets( GameField gameField) {
        tableContent = gameField.getGameFieldArray();
        
        this.gameField = gameField;
        generateNewTarget();
    }
    
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        generateNewTarget();
//    }
    
    public void generateNewTarget(){
        
        int xCoord;
        int yCoord;
        
        while(true){
            //calc random x, y value
            xCoord = new Random().nextInt(gameField.getRowCount());
            yCoord = new Random().nextInt(gameField.getColumnCount());
            
            if(tableContent[xCoord][yCoord] == false){
                tableContent[coord.getX()][coord.getY()] = false;
                tableContent[xCoord][yCoord] = true;
                coord.setX(xCoord);
                coord.setY(yCoord);
                break;
            }
        }
//        gameField.update();
    }
    
    public Coordinate getCoord() {
        return coord;
    }
}
