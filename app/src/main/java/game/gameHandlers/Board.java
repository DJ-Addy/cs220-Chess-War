package gameHandlers;
import java.util.*;
import com.google.common.collect.ImmutableList;


public class Board {
    
    private final List<Tile> gameBoard;
    private final List<ChessPiece> whitePieces;
    private final List<ChessPiece> blackPieces;

    //implement players classes 
    // private final Player whitePlayer;
    // private final Player blackPlayer;

    private Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Player.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Player.BLACK);
        final List<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final List<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
    }

    @Override
    public String toString() {
        final StringBuilder stringBoard = new StringBuilder();
        for (int i = 0; i < BoardUtil.NumTiles; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            stringBoard.append(String.format("%3s", tileText));
            if((i+1) % BoardUtil.NumTilesPerRow == 0) {
                stringBoard.append("\n");
            }
        }
        return stringBoard.toString();
    }



    private List<Move> calculateLegalMoves(final List<ChessPiece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final ChessPiece piece : pieces) {
            legalMoves.addAll(piece.LegalMovesList(this));
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static List<ChessPiece> calculateActivePieces(final List<Tile> gameBoard, final Player playerColor) {
        final List<ChessPiece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {
            if(tile.isTileOccupied()) {
                final ChessPiece piece = tile.getPiece();
                if(piece.getPiecePlayer()==playerColor) {
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    public Tile getTile(final int tileCoordinate) {
        return gameBoard.get(tileCoordinate);
    }

    public static Board createStandardBoard(){

        final Builder builder = new Builder();
        //Black Layout
        builder.setPiece(new RookHandler(0, Player.BLACK));
        builder.setPiece(new KnightHandler(1, Player.BLACK));
        builder.setPiece(new BishopHandler(2, Player.BLACK));
        builder.setPiece(new QueenHandler(3, Player.BLACK));
        builder.setPiece(new KingHandler(4, Player.BLACK));
        builder.setPiece(new BishopHandler(5, Player.BLACK));
        builder.setPiece(new KnightHandler(6, Player.BLACK));
        builder.setPiece(new RookHandler(7, Player.BLACK));
        builder.setPiece(new PawnHandler(8, Player.BLACK));
        builder.setPiece(new PawnHandler(9, Player.BLACK));
        builder.setPiece(new PawnHandler(10, Player.BLACK));
        builder.setPiece(new PawnHandler(11, Player.BLACK));
        builder.setPiece(new PawnHandler(12, Player.BLACK));
        builder.setPiece(new PawnHandler(13, Player.BLACK));
        builder.setPiece(new PawnHandler(14, Player.BLACK));
        builder.setPiece(new PawnHandler(15, Player.BLACK));
        //White Layout
        builder.setPiece(new RookHandler(63, Player.WHITE));
        builder.setPiece(new KnightHandler(62, Player.WHITE));
        builder.setPiece(new BishopHandler(61, Player.WHITE));
        builder.setPiece(new QueenHandler(60, Player.WHITE));
        builder.setPiece(new KingHandler(59, Player.WHITE));
        builder.setPiece(new BishopHandler(58, Player.WHITE));
        builder.setPiece(new KnightHandler(57, Player.WHITE));
        builder.setPiece(new RookHandler(56, Player.WHITE));
        builder.setPiece(new PawnHandler(55, Player.WHITE));
        builder.setPiece(new PawnHandler(54, Player.WHITE));
        builder.setPiece(new PawnHandler(53, Player.WHITE));
        builder.setPiece(new PawnHandler(52, Player.WHITE));
        builder.setPiece(new PawnHandler(51, Player.WHITE));
        builder.setPiece(new PawnHandler(50, Player.WHITE));
        builder.setPiece(new PawnHandler(49, Player.WHITE));
        builder.setPiece(new PawnHandler(48, Player.WHITE));
        //white to move
        builder.setMoveMaker(Player.WHITE);
        //build the board
        return builder.build();
    }

    private static List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtil.NumTiles];
        for (int i = 0; i < BoardUtil.NumTiles; i++) {

            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static class Builder {
        Map<Integer, ChessPiece> boardConfig;
        
        Player nextMoveMaker;
        public Builder(){
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece(final ChessPiece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }
        //Setting the next move maker
        public Builder setMoveMaker(final Player nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }
        
        public Board build() {
            return new Board(this);
        }

    }


}