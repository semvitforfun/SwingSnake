package snake.game.objects;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.Collections;
import java.util.LinkedList;
import snake.game.enums.ActionResult;
import snake.game.enums.Direction;
import snake.game.field.GameField;
import snake.game.listeners.MoveResultRegistrator;


public class Snake extends MoveResultRegistrator{
    private Boolean[][] tableContent;
    private GameField gameField;
    private Targets targets;
    private Direction direction = Direction.RIGHT;

    private LinkedList<Coordinate> snakeList = new LinkedList<>();

    
    public Snake(GameField gameField, Targets targets) {
        tableContent = gameField.getGameFieldArray();
        this.gameField = gameField;
        this.targets = targets;

        initSnake();
    }
    
    public void initSnake(){
        direction = Direction.RIGHT;
        snakeList.clear();
        snakeList.push(new Coordinate(2, 2));
        snakeList.push(new Coordinate(2, 3));
        snakeList.push(new Coordinate(2, 4));
        snakeList.push(new Coordinate(2, 5));
        
        for(Coordinate value: snakeList){
            gameField.setValueByCoordinate(value, true);
        }
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void move(){
        Coordinate newHeadCoord;
        
        if (isReversNeeded()){
            reverse();
        }
        
        switch(direction){
            case LEFT:
                newHeadCoord = new Coordinate(snakeList.getFirst().getX(), snakeList.getFirst().getY()-1);
                
                break;
            case RIGHT:
                newHeadCoord = new Coordinate(snakeList.getFirst().getX(), snakeList.getFirst().getY()+1);
                
                break;
            case UP:
                newHeadCoord = new Coordinate(snakeList.getFirst().getX()-1, snakeList.getFirst().getY());
                break;
            case DOWN:
            default:
                newHeadCoord = new Coordinate(snakeList.getFirst().getX()+1, snakeList.getFirst().getY());
        }
        
        if (checkBorder(newHeadCoord) || checkMyself(newHeadCoord) ){
            notifyMoveListeners(ActionResult.BAD);
            return;
        }

        snakeList.push(newHeadCoord);
        gameField.setValueByCoordinate(newHeadCoord, true);
        
        if (!checkTarget(newHeadCoord)){
            gameField.setValueByCoordinate(snakeList.pollLast(), false);
        } else {
            notifyMoveListeners(ActionResult.TARGET);
            return;
        }
        
        notifyMoveListeners(ActionResult.GOOD);
        return;
    }
    
    private Boolean checkBorder(Coordinate coord){
        if(coord.getX() < 0 || coord.getX() > gameField.getRowCount()-1 ||
           coord.getY() < 0 || coord.getY() > gameField.getColumnCount()-1 ){
            return true;
        }
        return false;
    }
    
    private Boolean checkMyself(Coordinate coord){
        for (Coordinate value: snakeList){
            if(value.getX() == coord.getX() && value.getY() == coord.getY() ){
                return true;
            }
        }
        return false;
    }
    
    private Boolean checkTarget(Coordinate coord){
        if ( targets.getCoord().equals(coord) ){
            return true;
        }
        
        return false;
    }
    
    private Direction currentDirection(){
        Coordinate first = snakeList.get(0);
        Coordinate second = snakeList.get(1);
        
        if( first.getX() == second.getX() ) {
            if( first.getY() > second.getY() ){
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        } else {
            if( first.getX() > second.getX() ){
                return Direction.DOWN;
            }else {
                return Direction.UP;
            }
        }
    }
    
    private void newAfterReversDirectionSet(){
        Coordinate ultimate = snakeList.get(snakeList.size()-1);
        Coordinate penUltimate = snakeList.get(snakeList.size()-2);
        
        if( ultimate.getX() == penUltimate.getX() ) {
            if( ultimate.getY() > penUltimate.getY() ){
                direction = Direction.RIGHT;
            } else {
                direction = Direction.LEFT;
            }
        } else {
            if( ultimate.getX() > penUltimate.getX() ){
                direction = Direction.DOWN;
            }else {
                direction = Direction.UP;
            }
        }
    }
    
    private Boolean isReversNeeded(){
        Direction currentDirection = currentDirection();
        
        if ( (currentDirection == Direction.RIGHT && direction == Direction.LEFT) ||
             (currentDirection == Direction.LEFT && direction == Direction.RIGHT) ||
             (currentDirection == Direction.UP && direction == Direction.DOWN)  ||
             (currentDirection == Direction.DOWN && direction == Direction.UP)    )  {
            return TRUE;
        } else {
            return FALSE;
        }
    }
    
    private void reverse(){
        newAfterReversDirectionSet();
        Collections.reverse(snakeList);
    }
    
    public int getSize(){
        return snakeList.size();
    }
}
