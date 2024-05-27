package game.template;

import javafx.scene.image.ImageView;

public enum ChessPiece
{
    PAWN,
    KNIGHT,
    BISHOP,
    ROOK,
    QUEEN,
    KING
}

public abstract class ChessPiece{

    final int piecePosition;
    final Player playerColor;
    final ImageView pieceImage;

    ChessPiece(final int piecePosition, final Player playerColor, ImageView pieceImage)
    {
        this.piecePosition = piecePosition;
        this.playerColor = playerColor;
        this.pieceImage = pieceImage;
    }
    public Player getPiecePlayer()
    {
        return this.playerColor;
    }

    public abstract List<Move> LegalMovesList(final Board board);




}