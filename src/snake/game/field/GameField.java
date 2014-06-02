package snake.game.field;

import snake.game.objects.Coordinate;
import snake.gui.map.DrawableMap;


public class GameField {

    private Boolean[][] gameFieldArray;
    private int rowCount;
    private int columnCount;
    private DrawableMap drawMap;
    
    
    public GameField(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        
        gameFieldArray = new Boolean[rowCount][columnCount];
        reset();
    }
    
    public void reset(){
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                gameFieldArray[i][j] = false;
            }
        }
    }
    
    public void setValueByCoordinate(Coordinate coord, Boolean value){
        gameFieldArray[coord.getX()][coord.getY()] = value;
    }

    public Boolean[][] getGameFieldArray(){
        return gameFieldArray;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
    
    public void setDrawMap(DrawableMap drawMap){
        this.drawMap = drawMap;
    }
    
    public void update(){
        drawMap.drawMap();
    }
    
}
