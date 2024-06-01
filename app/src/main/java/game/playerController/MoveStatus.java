package playerController;

public enum MoveStatus {
    
    Done {
        @Override
        boolean isDone() {
            return true;
        }
    },
    IllegalMove {
        @Override
        boolean isDone() {
            return false;
        }
    },
    LeavingPlayerInCheck {
        @Override
        boolean isDone() {
            return false;
        }
    };
    abstract boolean isDone();
}
