package gameHandlers;
import java.util.*;
import gameHandlers.Move.*;
import gameHandlers.Board.*;
import gameHandlers.BoardUtil.*;
import com.google.common.collect.ImmutableList;


public class KingHandler extends ChessPiece{

    private final static int[] CandidateMoveCoordinates = {-9, -8, -7, -1, 1, 7, 8, 9};
    
    public KingHandler(final int piecePosition, final Player playerColor)
    {
        super(piecePosition, playerColor, true, PieceType.KING);
    }

    @Override
    public List<Move> LegalMovesList(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidate : CandidateMoveCoordinates) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidate;

        if(BoardUtil.isValidTile(candidateDestinationCoordinate)) {
                if(isFirstColumnEdgeCase(this.piecePosition, currentCandidate) || isEighthColumnEdgeCase(this.piecePosition, currentCandidate)){
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new DevelopingMove(board, this, candidateDestinationCoordinate));
                }
                else {
                    
                    final ChessPiece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Player piecePlayer = pieceAtDestination.getPiecePlayer();

                    if(this.playerColor != piecePlayer) {
                        //TODO
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }

        }

        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
    @Override
    public KingHandler movePiece(Move move) {
        return new KingHandler(move.getDestinationCoordinate(), move.getMovedPiece().getPiecePlayer());
    }

    private static boolean isFirstColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.FirstColumn[currentPosition] && (candidatePos == -9 || candidatePos == -1 || candidatePos == 7); 
    }

    private static boolean isEighthColumnEdgeCase(final int currentPosition, final int candidatePos) {
        return BoardUtil.EigthColumn[currentPosition] && (candidatePos == -7 || candidatePos == 1 || candidatePos == 9);
    }

}
