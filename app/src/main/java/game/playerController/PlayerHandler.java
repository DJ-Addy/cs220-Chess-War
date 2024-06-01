package playerController;
import java.util.*;
import gameHandlers.Move.*;
import gameHandlers.Board;
import gameHandlers.Board.*;
import gameHandlers.ChessPiece;
import gameHandlers.KingHandler;
import gameHandlers.Move;
import gameHandlers.Player;

public abstract class PlayerHandler {
    final Board board;
    final KingHandler playerKing;
    final List<Move> legalMoves;
    private final boolean isInCheck;


    PlayerHandler(final Board board, final List<Move> legalMoves, final List<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        legalMoves.addAll(calculateKingCastles(legalMoves, opponentMoves));
        this.legalMoves = Collections.unmodifiableList(legalMoves);
        //calc attacks on opponent
        this.isInCheck = !PlayerHandler.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }

    public KingHandler establishKing(){
        for(final ChessPiece piece : getActivePieces()){
            if(piece.getPieceType().isKing()){
                return (KingHandler) piece;
            }
        }
        throw new RuntimeException("Not a valid board");
    }

    public KingHandler getPlayerKing(){
        return this.playerKing;
    }

    public List<Move> getLegalMoves(){
        return this.legalMoves;
    }

    protected static List<Move> calculateAttacksOnTile(int piecePosition, List<Move> moves){
        final List<Move> attackMoves = new ArrayList<>();
        for(final Move move : moves){
            if(piecePosition == move.getDestinationCoordinate()){
                attackMoves.add(move);
            }
        }
        return Collections.unmodifiableList(attackMoves);
    }

    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }
    //implement escape moves
    //checkmate is when the king has no escape moves and is in check
    public boolean isInCheckMate(){
        return this.isInCheck() && !hasEscapeMoves();
    }
    //StaleMate is when the king is not in check and has no escape moves
    public boolean isInStaleMate(){
        return !this.isInCheck() && !hasEscapeMoves();
    }

    public boolean isCastled(){
        return false;
    }
     //protected because we want to use this method to be changed in the subclasses
     protected boolean hasEscapeMoves(){
        for(final Move move : this.legalMoves){
            final MoveDriver moveDriver = makeMove(move);
            if(moveDriver.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }

    public MoveDriver makeMove(final Move move){
        if(!isMoveLegal(move)){
            return new MoveDriver(this.board, move, MoveStatus.IllegalMove);
        }
        
        final Board transitionBoard = move.execute();
        //This Long line is basically checking if the king is in check after the move
        final List<Move> kingAttacks = PlayerHandler.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(), 
        transitionBoard.currentPlayer().getLegalMoves());
        
        //if the king can be attacked after the move, then the move is illegal, ei bishop pinned a knight to the king the knight moves and the king is in check which is illegal
        if(!kingAttacks.isEmpty()){
            return new MoveDriver(this.board, move, MoveStatus.LeavesPlayerInCheck);
        }
        return new MoveDriver(this.board, move, MoveStatus.Done);
    }

    public abstract List<ChessPiece> getActivePieces();
    public abstract Player getPlayerColor();
    public abstract Player getOpponent();
    protected abstract List<Move> calculateKingCastles(List<Move> playerLegalMoves, List<Move> opponentLegalMoves);
}
