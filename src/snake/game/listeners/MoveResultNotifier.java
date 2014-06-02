
package snake.game.listeners;

import java.util.List;
import snake.game.enums.ActionResult;


public interface MoveResultNotifier {
    List<MoveResultListener> getMoveListeners();

    void addMoveListener(MoveResultListener listener);

    public void removeMoveListener(MoveResultListener listener);

    public void removeAllLMoveisteners();

    public void notifyMoveListeners(ActionResult res);

}
