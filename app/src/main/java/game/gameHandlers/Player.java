package gameHandlers;
import java.util.*;

import playerController.PlayerHandler;
import playerController.WhitePlayer;
import playerController.BlackPlayer;

public enum Player
{
    WHITE 
    {
    @Override
    public int getDirection()
        {
        return -1;
        }
        @Override
    public boolean isWhite()
        {
        return true;
        }
    @Override
    public boolean isBlack()
        {
        return false;
        }
    @Override
    public WhitePlayer choosePlayer(WhitePlayer whitePlayerController, BlackPlayer blackPlayerController) {
        return whitePlayerController;
        }
    },
    BLACK {
    @Override
    public int getDirection()
        {
        return 1;
        }
        @Override
    public boolean isWhite()
        {
        return false;
        }
    @Override
    public boolean isBlack()
        {
        return true;
        }
    @Override
    public BlackPlayer choosePlayer(WhitePlayer whitePlayerController, BlackPlayer blackPlayerController) {
        return blackPlayerController;
        }
    };
    public abstract int getDirection();
    public abstract boolean isWhite();
    public abstract boolean isBlack();
    public abstract PlayerHandler choosePlayer(final WhitePlayer whitePlayerController, final BlackPlayer blackPlayerController);
}
