package playerController;

import java.util.*;
import gameHandlers.Player;
import gameHandlers.Board;
import gameHandlers.Move;
import gameHandlers.ChessPiece;
import gameHandlers.RookHandler;
import gameHandlers.Tile;

public class BlackPlayer extends PlayerHandler {
    public BlackPlayer(final Board board, final List<Move> whiteStandardLegalMoves, final List<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves ,whiteStandardLegalMoves);
    }

    @Override
    public List<ChessPiece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Player getPlayerColor() {
        return Player.BLACK;
    }

    @Override
    public PlayerHandler getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected List<Move> calculateKingCastles(List<Move> playerLegals, List<Move> opponentsLegals){
        final List<Move> kingCastles = new ArrayList<>();
        //black king side castle
        if(this.playerKing.isFirstMove() && !this.isInCheck()){
            //whites king side castle
            if(this.board.getTile(5).isTileOccupied() && this.board.getTile(6).isTileOccupied()){
                final Tile rookTile = this.board.getTile(7);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                        if(PlayerHandler.calculateAttacksOnTile(5, opponentsLegals).isEmpty() &&
                           PlayerHandler.calculateAttacksOnTile(6, opponentsLegals).isEmpty() && 
                           rookTile.getPiece().getPieceType().isRook()){
                            kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 6,
                                         (RookHandler)rookTile.getPiece(), rookTile.getTileCoordinate(), 5));
                        }
                    }
                }
            }
            if(!this.board.getTile(1).isTileOccupied() && !this.board.getTile(2).isTileOccupied() && !this.board.getTile(3).isTileOccupied()){
                final Tile rookTile = this.board.getTile(0);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() && 
                PlayerHandler.calculateAttacksOnTile(2, opponentsLegals).isEmpty() &&
                PlayerHandler.calculateAttacksOnTile(3, opponentsLegals).isEmpty() &&
                rookTile.getPiece().getPieceType().isRook()){
                    kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 2,
                                     (RookHandler)rookTile.getPiece(), rookTile.getTileCoordinate(), 3));
                }
        }

        return kingCastles;

    }
}