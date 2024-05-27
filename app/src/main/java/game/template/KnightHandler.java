package main.java.game.template;
import java.util.List;
import com.google.common.collect.ImmutableList;
import game.template.ChessPiece;

public class KnightHandler extends ChessPiece {
    
    private final int[] CandidateMoveCoordinates = {-17, -15, -10, -6, 6, 10, 15, 17};

    KnightHandler(final int piecePosition, final Player playerColor, ImageView pieceImage)
    {
        super(piecePosition, playerColor, pieceImage);
    }

    @Override
    public List<Move> LegalMovesList(Board board) {
        
        int candidateDestinationCoordinate;
        final List<Moves> legalMoves = new ArrayList<>();
        for (final int currentCandidate : CandidateMoveCoordinates) {
            candidateDestinationCoordinate = this.piecePosition + currentCandidate;

            if (true /*isValidTileCoordinate(candidateDestinationCoordinate)*/) {
                //TODO
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(candidateDestinationTile.isTileOccupied()) {
                    //TODO
                    legalMoves.add(new Move());
                }
                else {
                    //TODO
                    final ChessPiece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Player piecePlayer = pieceAtDestination.getPiecePlayer();

                    if(this.playerColor != piecePlayer) {
                        //TODO
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        
        return ImutableList.copyOf(legalMoves);
    }
}
