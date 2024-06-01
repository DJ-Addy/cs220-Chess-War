package gameHandlers;
import java.util.*;

import playerController.PlayerHandler.*;
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
    public PlayerHandler choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
        return whitePlayer;
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
    public PlayerHandler choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
        return blackPlayer;
        }
    };
    public abstract int getDirection();
    public abstract boolean isWhite();
    public abstract boolean isBlack();
    public abstract PlayerHandler choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);
}
