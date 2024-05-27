package main.java.game.template;
import java.util.List;
import java.util.ArrayList;
import com.google.common.collect.ImmutableList;

public class KnightHandler extends ChessPiece {
    
    private final int[] CandidateMoveCoordinates = {-17, -15, -10, -6, 6, 10, 15, 17};

    KnightHandler(final int piecePosition, final Player playerColor, ImageView pieceImage)
    {
        super(piecePosition, playerColor, pieceImage);
    }

    @Override
    public List<Move> LegalMovesList(Board board) {
        
        final List<Moves> legalMoves = new ArrayList<>();

        for (final int currentCandidate : CandidateMoveCoordinates) {
            int candidateDestinationCoordinate = this.piecePosition + currentCandidate;

            if (BoardUtil.isValidTile(candidateDestinationCoordinate)) {

                if(isFirstColumnEdgeCase(this.piecePosition, currentCandidate) || 
                isSecondColumnEdgeCase(this.piecePosition, currentCandidate) || 
                isSeventhColumnEdgeCase(this.piecePosition, currentCandidate) || 
                isEighthColumnEdgeCase(this.piecePosition, currentCandidate)){
                    continue;
                }
                //TODO
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()) {
                    //TODO
                    legalMoves.add(new DevelopingMove(board, this, candidateDestinationCoordinate));
                }
                else {
                    //TODO
                    final ChessPiece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Player piecePlayer = pieceAtDestination.getPiecePlayer();

                    if(this.playerColor != piecePlayer) {
                        //TODO
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }

        
        return ImutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.FIRST_COLUMN[currentPosition] && (candidatePos == -17 || candidatePos == -10 || candidatePos == 6 || candidatePos == 15);
    }

    private static boolean isSecondColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.SECOND_COLUMN[currentPosition] && (candidatePos == -10 || candidatePos == 6);
    }

    private static boolean isSeventhColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.SEVENTH_COLUMN[currentPosition] && (candidatePos == -6 || candidatePos == 10);
    }

    private static boolean isEighthColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.EIGHTH_COLUMN[currentPosition] && (candidatePos == -15 || candidatePos == -6 || candidatePos == 10 || candidatePos == 17);
    }

}
