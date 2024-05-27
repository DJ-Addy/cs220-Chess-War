package main.java.game.template;

import java.util.List;
import java.util.ArrayList;
import com.google.common.collect.ImmutableList;


public class PawnHandler extends ChessPiece{
    
    private final static int[] CandidateMoveCoordinates = {8, 16, 7, 9};

    PawnHandler(final int piecePosition, final Player playerColor, ImageView pieceImage)
    {
        super(piecePosition, playerColor, pieceImage);
    }

    @Override
    public List<Move> LegalMovesList( final Board board) {
        
        final List<Moves> legalMoves = new ArrayList<>();
        //TODO: HANDLE PAWN PROMOTION
        for (final int currentCandidate : CandidateMoveCoordinates) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.playerColor.getDirection() * currentCandidate);

            if(!BoardUtil.isValidTile(candidateDestinationCoordinate)) {
                continue;
            }
        if(currentCandidate == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new DevelopingMove(board, this, candidateDestinationCoordinate));
        }else if(currentCandidate == 16 && this.isFirstMove() && 
            (BoardUtil.SECOND_ROW(this.piecePosition)&& 
            this.getPiecePlayer().isBlack())|| 
            (BoardUtil.SEVENTH_ROW(this.piecePosition)&& 
            this.getPiecePlayer().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.getPiecePlayer().getDirection() * 8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && 
                   !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new DevelopingMove(board, this, candidateDestinationCoordinate));
                }
        }else if(currentCandidate == 7 && 
                !((BoardUtil.EigthColumn[this.piecePosition] && this.playerColor.isWhite() || 
                (BoardUtil.FirstColumn[this.piecePosition] && this.playerColor.isBlack())))) {
            if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                final ChessPiece pieceAtDestination = board.getTile(candidateDestinationCoordinate).getPiece();
                if(this.playerColor != pieceAtDestination.getPiecePlayer()) {
                    //TODO: handle attacking into a pawn promotion
                    legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                }
            }
        }else if(currentCandidate == 9 &&
            !((BoardUtil.FirstColumn[this.piecePosition] && this.playerColor.isWhite() || 
            (BoardUtil.EigthColumn[this.piecePosition] && this.playerColor.isBlack())))){
            if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final ChessPiece pieceAtDestination = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.playerColor != pieceAtDestination.getPiecePlayer()) {
                        //TODO: handle attacking into a pawn promotion
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
            }
        }
     }
        return ImutableList.copyOf(legalMoves);
    }
}
