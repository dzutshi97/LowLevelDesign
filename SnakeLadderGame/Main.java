package com.SnakeLadderGame;

import com.SnakeLadderGame.models.*;
import com.lld3.SnakeLadderGame.models.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dice dice = new Dice();
        Board board = new Board(16);
        Player p1 = new Player(1,"deeps");
        Player p2 = new Player(2,"shady");
        Player p3 = new Player(3,"abc");
        Player p4 = new Player(4,"xyz");
        List<Player> playersList = new ArrayList<>();
        playersList.add(p1);
        playersList.add(p2);
        playersList.add(p3);
        playersList.add(p4);
        BoardEntity boardEntityLadder = new Ladder();
        boardEntityLadder.setStart(4);
        boardEntityLadder.setEnd(10);

        BoardEntity boardEntitySnake = new Snake();
        boardEntitySnake.setStart(12);
        boardEntitySnake.setEnd(4);
        board.addEntity(boardEntitySnake);

        List<BoardEntity> boardEntities = new ArrayList<>();
        boardEntities.add(boardEntitySnake);
        boardEntities.add(boardEntityLadder);
        board.addEntity(boardEntityLadder);

        Game game = new Game(dice,board,playersList,boardEntities);
        game.start(game);
    }

}
