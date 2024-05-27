package game.template;

import javafx.scene.image.ImageView;

public abstract class ChessPiece{

    final int piecePosition;
    final Player playerColor;
    final ImageView pieceImage;
    final boolean isFirstMove = true;

    ChessPiece(final int piecePosition, final Player playerColor, ImageView pieceImage)
    {
        this.piecePosition = piecePosition;
        this.playerColor = playerColor;
        this.pieceImage = pieceImage;
        //TODO: more work here!!!
        this.isFirstMove = false;
    }
    public Player getPiecePlayer()
    {
        return this.playerColor;
    }
    public boolean isFirstMove()
    {
        return this.isFirstMove;
    }

    public abstract List<Move> LegalMovesList(final Board board);




}