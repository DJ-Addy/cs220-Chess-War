package main.java.game.template;

import java.util.List;
import java.util.ArrayList;
import com.google.common.collect.ImmutableList;

public class QueenHandler {
 
    private final static int[] CandidateMoveCoordinates = {-9,-8,-7,-1,1,7,8, 9};

    QueenHandler(final int piecePosition, final Player playerColor, ImageView pieceImage)
    {
        super(piecePosition, playerColor, pieceImage);
    }

    @Override
    public List<Move> LegalMovesList(Board board) {
        
        final List<Moves> legalMoves = new ArrayList<>();
        //loop through the candidate vectors
        for (final int currentCandidate : CandidateMoveCoordinates) {
            int candidateDestinationCoordinate = this.piecePosition;
            
            //while the diagonal,horizonal,vertical move is valid
            while(BoardUtil.isValidTile(candidateDestinationCoordinate)) {
                if(isFirstColumnEdgeCase(candidateDestinationCoordinate, currentCandidate) || 
                isEighthColumnEdgeCase(candidateDestinationCoordinate, currentCandidate)){
                    break;
                }
                //allows the Queen to move diagonally,horizonally,vertically using the candidate vectors
                candidateDestinationCoordinate += currentCandidate;
                if (BoardUtil.isValidTile(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied()) {
                    //TODO
                    legalMoves.add(new DevelopingMove(board, this, candidateDestinationCoordinate));
                    }else {
                    //TODO
                        final ChessPiece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Player piecePlayer = pieceAtDestination.getPiecePlayer();

                    if(this.playerColor != piecePlayer) {
                        //TODO
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        //breaks when either white or black piece is encountered on the diagonal,horizonal,vertical
                        break;
                    }
                }
            }
        }
        return ImutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.FIRST_COLUMN[currentPosition] && (candidatePos == -9 ||candidatePos == -1 ||candidatePos == 7);
    }

    private static boolean isEighthColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.EIGHTH_COLUMN[currentPosition] && (candidatePos == -7 || candidatePos == 9 || candidatePos == 1);
    }
}

