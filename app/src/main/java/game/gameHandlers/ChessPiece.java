package gameHandlers;
import java.util.*;

public abstract class ChessPiece{

    protected final PieceType pieceType;
    final int piecePosition;
    final Player playerColor;
    boolean isFirstMove = true;

    ChessPiece(final int piecePosition, final Player playerColor, boolean isFirstMove, final PieceType pieceType)
    {
        this.piecePosition = piecePosition;
        this.playerColor = playerColor;
        //TODO: more work here!!!
        this.isFirstMove = false;
        this.pieceType = pieceType;
    }

    public int getPiecePosition()
    {
        return this.piecePosition;
    }

    public Player getPiecePlayer()
    {
        return this.playerColor;
    }
    public PieceType getPieceType()
	{
		return this.pieceType;
	}
    public boolean isFirstMove()
    {
        return this.isFirstMove;
    }

    public abstract List<Move> LegalMovesList(final Board board);


    public enum PieceType{
        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

        private String pieceName;

        PieceType(final String pieceName)
        {
            this.pieceName = pieceName;
        }

        @Override
        public String toString()
        {
            return this.pieceName;
        }
    }


}