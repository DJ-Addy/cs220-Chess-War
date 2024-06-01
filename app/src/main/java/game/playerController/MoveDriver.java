package playerController;

import gameHandlers.Board.*;
import gameHandlers.Move.*;
import gameHandlers.MoveStatus.*;

public class MoveDriver {
    private final Board transitionboard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveDriver(final Board board, final Move move, final MoveStatus moveStatus){
        this.transitionboard = transitionboard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }
}
