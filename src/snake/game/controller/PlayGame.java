package snake.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import snake.game.enums.ActionResult;
import snake.game.field.GameField;
import snake.game.listeners.MoveResultListener;
import snake.game.objects.Coordinate;
import snake.game.objects.Snake;
import snake.game.objects.Targets;
import snake.game.objects.sound.WavPlayer;
import snake.gui.GameFrame;
import snake.gui.GameOptionsFrame;
import snake.gui.map.JtableMap;
import snake.utils.MessageManager;


public class PlayGame implements ActionListener, MoveResultListener{
    
    //timer
    private Timer timer;
    private int moveDelay;
//    private int movesToNewTargetGenerate;
//    private int moveCount = 0;
//    
    private int winSize;
    private int currentLevel;
    private int levelSpeadIncrease;
    
    
    private JFrame parent;
    
    private GameOptionsFrame gameOptionsFrame;
    private GameFrame gameFrame;
    
    private GameField gameField;
    private Targets targets;
    private Snake snake;
    private WavPlayer player;



    public PlayGame(JFrame parent) {
        this.parent = parent;
        
        gameOptionsFrame = new GameOptionsFrame();
        
        moveDelay = 250;
//        movesToNewTargetGenerate = 30;
        winSize = 25;
        currentLevel = 1;
        levelSpeadIncrease = 20;
        
        timer = new Timer(moveDelay, this);
        timer.setInitialDelay(500);
    }
    
    public void start(){

        if (gameOptionsFrame.isBackgraundSoundSelected()){
            player.startBackgroundMusic();
        }
        timer.start();
    }
    
    public void stop(){
        if (gameOptionsFrame.isBackgraundSoundSelected()){
            player.stopBackgoundMusic();
        }
        timer.stop();
    }
    
    public void setDelay(int i){
        moveDelay = i;
        timer.setDelay(moveDelay);
    }
    
    public Boolean isRunning(){
        return timer.isRunning();
    }
    
    public GameOptionsFrame getGameOptionsFrame() {
        return gameOptionsFrame;
    }
    
    public void startGame(){
        int n = gameOptionsFrame.getHeightOption();
        int m = gameOptionsFrame.getWidthOption();
        
        switch(gameOptionsFrame.getDifficultyLevel()){

            case 2:
                setDelay(165);
                levelSpeadIncrease = 12;
                break;
            case 3:
                setDelay(100);
                levelSpeadIncrease = 7;
                break;
            case 1:
            default:
                setDelay(250);
                levelSpeadIncrease = 20;
                break;
        }
        
        
        
        player = new WavPlayer();
        gameField = new GameField(m, n);
        targets = new Targets(gameField);
        snake = new Snake(gameField, targets);
        snake.addMoveListener(this);
        if ( gameOptionsFrame.isGameSoundSelected() ){
            snake.addMoveListener(player);
        }
        gameFrame = new GameFrame(m, n, this);
        gameFrame.setMap(new JtableMap(gameField));
        gameFrame.showFrame(parent);

        if ( gameOptionsFrame.isGameSoundSelected() ){
            player.playStartSound();
        }
        start();
    }
    
    private void nextLevel(){
        currentLevel +=1;
        setDelay(moveDelay-levelSpeadIncrease);
    }
    
    private void resetGame(){

        gameField.reset();
        gameField.update();
        targets.generateNewTarget();
//        moveCount = 0;
        snake.initSnake();
        if ( gameOptionsFrame.isGameSoundSelected() ){
            player.playStartSound();
        }
        this.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
    }
    
    public Snake getSnake() {
        return snake;
    }
    
    @Override
    public void doAction(ActionResult res) {
        
        switch(res){
            case BAD:
                this.stop();
                if ( gameOptionsFrame.isGameSoundSelected() ){
                    player.playLoseSound();
                }
                if( MessageManager.LosingMessage() == 0 ){
                    //if start again
                    resetGame();
                } else {
                    //if finished
                    gameFrame.closeFrame();
                }
                break;
            case TARGET:
                if (snake.getSize() == winSize){
                    this.stop();
                    if ( gameOptionsFrame.isGameSoundSelected() ){
                        player.playWinSound();
                    }
                    
                    if( currentLevel == 11 ) {

                        MessageManager.winMessage();
                        gameFrame.closeFrame();
                    } else {
                        if (MessageManager.nextLevelMessage(currentLevel) == 0){
                            nextLevel();
                            resetGame();
                        } else {
                            gameFrame.closeFrame();
                        }
                    }
                } else {
                    Coordinate tmp = new Coordinate(targets.getCoord());
                    targets.generateNewTarget();
//                    moveCount = 0;
                    gameField.setValueByCoordinate(tmp, true);
                }
                
                break;
                
            case GOOD:
            default:
                break;
        }
        
//        if( moveCount >  movesToNewTargetGenerate) {
//            targets.generateNewTarget();
//            moveCount = 0;
//        }      
//        moveCount++;
        
        gameField.update();
    }
}
