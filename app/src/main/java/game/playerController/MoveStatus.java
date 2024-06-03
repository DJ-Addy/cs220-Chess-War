package playerController;

public enum MoveStatus {
    
    Done {
        @Override
        public boolean isDone() {
            return true;
        }
    },
    IllegalMove {
        @Override
        public boolean isDone() {
            return false;
        }
    },
    LeavingPlayerInCheck {
        @Override
        public boolean isDone() {
            return false;
        }
    };
   public abstract boolean isDone();
}
