package playerController;

import java.util.*;
import gameHandlers.Move.*;
import gameHandlers.Board;
import gameHandlers.Board.*;
import gameHandlers.ChessPiece.*;
import gameHandlers.KingHandler;
import gameHandlers.Move;
import gameHandlers.Player;
import gameHandlers.QueenHandler;
import gameHandlers.RookHandler;
import gameHandlers.Tile.*;

public class WhitePlayer extends PlayerHandler {
    public WhitePlayer(final Board board, final List<Move> whiteStandardLegalMoves, final List<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public List<ChessPiece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Player getPlayerColor() {
        return Player.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected List<Move> calculateKingCastles(List<Move> playerLegals, List<Move> opponentsLegals){
        final List<Move> kingCastles = new ArrayList<>();

        if(this.playerKing.isFirstMove() && !this.isInCheck()){
            //whites king side castle
            if(this.board.getTile(61).isTileOccupied() && this.board.getTile(62).isTileOccupied()){
                final ChessPiece rookTile = this.board.getTile(63);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                        if(PlayerHandler.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                           PlayerHandler.calculateAttacksOnTile(62, opponentsLegals).isEmpty() && 
                           rookTile.getPiece().getPieceType().isRook()){
                            kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing, 62,
                                             (RookHandler)rookTile.getPiece(), rookTile.getTileCoordinate(), 61));
                        }
                    }
                }
            }
            if(!this.board.getTile(59).isTileOccupied() && !this.board.getTile(58).isTileOccupied() && !this.board.getTile(57).isTileOccupied()){
                final ChessPiece rookTile = this.board.getTile(56);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 58,
                                     (RookHandler)rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
                }
        }




        return Collections.unmodifiableList(kingCastles);

        }
}
