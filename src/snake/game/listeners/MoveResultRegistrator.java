

package snake.game.listeners;

import java.util.ArrayList;
import java.util.List;
import snake.game.enums.ActionResult;


public abstract class MoveResultRegistrator implements MoveResultNotifier{
    
    ArrayList<MoveResultListener> list = new ArrayList<>();
    
    @Override
    public List<MoveResultListener> getMoveListeners() {
        return list;
    }

    @Override
    public void addMoveListener(MoveResultListener listener) {
        list.add(listener);
    }

    @Override
    public void removeMoveListener(MoveResultListener listener) {
        list.remove(listener);
    }

    @Override
    public void removeAllLMoveisteners() {
        list.clear();
    }

    @Override
    public void notifyMoveListeners(ActionResult res) {
        for(MoveResultListener listener : list){
            listener.doAction(res);
        }
    }

}
