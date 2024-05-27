package main.java.game.template;

public abstract class Move {
    
    final Board board;
    final ChessPiece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board, final ChessPiece movedPiece, final int destinationCoordinate)
    {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }
    //consider developing move and attacking move
   public static final class DevelopingMove extends Move
   {
       public DevelopingMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate)
       {
           super(board, movedPiece, destinationCoordinate);
       }
   }

   public static final class AttackMove extends Move
   {
       final ChessPiece attackedPiece;

       public AttackMove(final Board board, final ChessPiece movedPiece, final int destinationCoordinate, final ChessPiece attackedPiece)
       {
           super(board, movedPiece, destinationCoordinate);
           this.attackedPiece = attackedPiece;
       }
   }

}
