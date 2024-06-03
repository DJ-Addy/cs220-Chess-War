package gameHandlers;
import java.util.*;
import gameHandlers.Board.Builder;

public abstract class Move {
    
    final Board board;
    protected final ChessPiece movedPiece;
    final int destinationCoordinate;

    public static final Move InvalidMover = new InvalidMove();

    private Move(final Board board, final ChessPiece movedPiece, final int destinationCoordinate)
    {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    @Override
    public int hashCode()
    {
        int result = 1;
        result = 31 * result + this.destinationCoordinate;
        result = 31 * result + this.movedPiece.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object other)
    {
        if(this == other)
        {
            return true;
        }
        if(!(other instanceof Move))
        {
            return false;
        }
        final Move otherMove = (Move) other;
        return getDestinationCoordinate() == otherMove.getDestinationCoordinate() && getMovedPiece().equals(otherMove.getMovedPiece());
    }

    public int getDestinationCoordinate()
    {
        return this.destinationCoordinate;
    }

    public int getCurrentCoordinate()
    {
        return this.movedPiece.getPiecePosition();
    }

    public boolean isAttack()
    {
        return false;
    }

    public boolean isCastlingMove()
    {
        return false;
    }

    public ChessPiece getAttackedPiece()
    {
        return null;
    }

    public ChessPiece getMovedPiece()
    {
        return this.movedPiece;
    }

    public Board execute()
    {
        //we arent going to mutuate a existing board but create a whole new board with the new move
        final Builder builder = new Builder();
        //iterate through all the active pieces of the current player
        for(final ChessPiece piece : this.board.currentPlayer().getActivePieces())
        {
            //if the piece is not the moved piece place them on the new board
            if(!this.movedPiece.equals(piece))
            {
                builder.setPiece(piece);
            }
        }
        //iterate through all the active pieces of the opponent and place them on the new board
        for(final ChessPiece piece : this.board.currentPlayer().getOpponent().getActivePieces())
        {
            builder.setPiece(piece);
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getPlayerColor());
        
        return builder.build();
    }

    //consider developing move and attacking move
   public static final class DevelopingMove extends Move
   {
       public DevelopingMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate)
       {
           super(board, movedPiece, destinationCoordinate);
       }
   }

   public static class AttackMove extends Move
   {
       final ChessPiece attackedPiece;

       public AttackMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate, final ChessPiece attackedPiece)
       {
           super(board, movedPiece, destinationCoordinate);
           this.attackedPiece = attackedPiece;
       }
       @Override
         public int hashCode(){
                return this.attackedPiece.hashCode() + super.hashCode();
            }
        @Override
        public boolean equals(final Object other)
        {
            if(this == other)
            {
                return true;
            }
            if(!(other instanceof AttackMove))
            {
                return false;
            }
            final AttackMove otherAttackMove = (AttackMove) other;
            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
        }
           
        @Override
        public Board execute()
        {
            return null;
        }
        @Override
        public boolean isAttack()
        {
            return true;
        }
        @Override
        public ChessPiece getAttackedPiece()
        {
            return this.attackedPiece;
        }
   }

    public static final class PawnMove extends Move
    {
         public PawnMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate)
         {
              super(board, movedPiece, destinationCoordinate);
         }
    }


    public static class PawnAttackMove extends AttackMove
    {

        public PawnAttackMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate, final ChessPiece attackedPiece)
        {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
    }

    public static final class PawnEnPassantAttackMove extends PawnAttackMove
    {
        public PawnEnPassantAttackMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate,final ChessPiece attackedPiece)
        {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
    }

    public static final class PawnJump extends Move
    {
        public PawnJump(final Board board, final ChessPiece movedPiece, final int destinationCoordinate)
        {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute()
        {
            final Builder builder = new Builder();
            for(final ChessPiece piece : this.board.currentPlayer().getActivePieces())
            {
                if(!this.movedPiece.equals(piece))
                {
                    builder.setPiece(piece);
                }
            }
            for(final ChessPiece piece : this.board.currentPlayer().getOpponent().getActivePieces())
            {
                builder.setPiece(piece);
            }
            final PawnHandler movedPawn = (PawnHandler) this.movedPiece.movePiece(this);
            //enpassent pawn can jump which is why we need the setEnPassantPawn method
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getPlayerColor());
            return builder.build();
        }
    }

    public static abstract class CastleMove extends Move
    {
        protected final RookHandler castleRook;
        protected final int castleRookStartPos;
        protected final int castleRookDestinationPos;
        public CastleMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate, 
        final RookHandler castleRook, final int castleRookStartPos, final int castleRookDestinationPos){

            super(board, movedPiece, destinationCoordinate);
            this.castleRook = castleRook;
            this.castleRookStartPos = castleRookStartPos;
            this.castleRookDestinationPos = castleRookDestinationPos;
        }

        public RookHandler getCastleRook()
        {
            return this.castleRook;
        }

        @Override
        public boolean isCastlingMove()
        {
            return true;
        }

        @Override
        public Board execute(){

            final Builder builder = new Builder();
            for(final ChessPiece piece : this.board.currentPlayer().getActivePieces())
            {
                if(!this.movedPiece.equals(piece) && !this.castleRook.equals(piece))
                {
                    builder.setPiece(piece);
                }
            }
            for(final ChessPiece piece : this.board.currentPlayer().getOpponent().getActivePieces())
            {
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            //TODO look into the first move on normal pieces
            builder.setPiece(new RookHandler(this.castleRookDestinationPos, this.castleRook.getPiecePlayer()));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getPlayerColor());
            return builder.build();
        }
    }

    public static final class KingSideCastleMove extends CastleMove
    {
        public KingSideCastleMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate, 
        final RookHandler castleRook, final int castleRookStartPos, final int castleRookDestinationPos)
        {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStartPos, castleRookDestinationPos);
        }

        @Override
        public String toString()
        {
            return "0-0";
        }
    }

    public static final class QueenSideCastleMove extends CastleMove
    {
        public QueenSideCastleMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate, 
        final RookHandler castleRook, final int castleRookStartPos, final int castleRookDestinationPos)
        {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStartPos, castleRookDestinationPos);
        }

        @Override
        public String toString()
        {
            return "0-0-0";
        }
    }

    public static final class InvalidMove extends Move
    {
        public InvalidMove()
        {
            super(null, null, -1);
        }

        @Override
        public Board execute()
        {
            throw new RuntimeException("Cannot execute the invalid move");
        }
    }

    public static class MoveFactory 
    {
        private MoveFactory() {
            throw new RuntimeException("you cannot instantiate this class");
        }

        public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate)
        {
            for(final Move move : board.getAllLegalMoves())
            {
                if(move.getCurrentCoordinate() == currentCoordinate && move.getDestinationCoordinate() == destinationCoordinate)
                {
                    return move;
                }
            }
            return InvalidMover;
        }


    }

}
