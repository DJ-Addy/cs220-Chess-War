package gameHandlers;
import java.util.*;

public abstract class ChessPiece{

    protected final PieceType pieceType;
    final int piecePosition;
    final Player playerColor;
    boolean isFirstMove = true;
    private final int cachedHashCode;

    ChessPiece(final int piecePosition, final Player playerColor, boolean isFirstMove, final PieceType pieceType)
    {
        this.piecePosition = piecePosition;
        this.playerColor = playerColor;
        //TODO: more work here!!!
        this.isFirstMove = false;
        this.pieceType = pieceType;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode()
    {
        int result = pieceType.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + playerColor.hashCode();
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }


    //We override the equals method because we dont want to compare referrence equality but Object equality!
    @Override
    public boolean equals(final Object other)
    {
        if(this == other)
        {
            return true;
        }
        if(!(other instanceof ChessPiece))
        {
            return false;
        }
        final ChessPiece otherPiece = (ChessPiece) other;
        return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() && playerColor == otherPiece.getPiecePlayer() && isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode()
    {
        return this.cachedHashCode;
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
    public abstract ChessPiece movePiece(Move move);
    public abstract List<Move> LegalMovesList(final Board board);


    public enum PieceType{
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

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

        public abstract boolean isKing();
        public abstract boolean isRook();
    }


}