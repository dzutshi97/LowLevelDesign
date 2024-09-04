package com.SnakeLadderGame.models;

import java.util.*;

public class Game {

    Dice dice;
    Board board;
    List<Player> players;
    List<BoardEntity> boardEntities;
    Player winner;

    public Game(Dice dice, Board board, List<Player> players, List<BoardEntity> boardEntities) {
        this.dice = dice;
        this.board = board;
        this.players = players;
        this.boardEntities = boardEntities;
    }

    public void start(Game game){
        Queue<Player> playerQueue = new LinkedList<>();
        for (int i = 0; i < game.players.size(); i++) {
            playerQueue.offer(game.players.get(i));
        }
   /**     HashMap<Player, Integer> playerPos = new HashMap<>();
        for(int i=0; i<game.players.size(); i++){
            playerPos.put(game.players.get(i), 1);//initialize all players to starting position!
        } */ //Instead- in player constructor have the pos set to 0 by default. no need of using an extra map -> playerPos

        while(true){
            Player player = playerQueue.remove();
            playerQueue.offer(player); //add this player to end
            int currentPos = player.pos;
            int steps  = game.dice.roll(6);
            int nextPos = currentPos + steps;

            if(nextPos > game.board.maxPos){
                continue;
            }

            if(nextPos == game.board.maxPos){
                System.out.println("Winner Found!");
                System.out.println("Winner is! --> "+player.name);
                return;
            }

            if(game.board.boardEntityPos.containsKey(nextPos)){
                if(game.board.boardEntityPos.get(nextPos) instanceof Snake){ //Liskov substituion principle
                    nextPos = game.board.boardEntityPos.get(nextPos).end;
                } else if (game.board.boardEntityPos.get(nextPos) instanceof Ladder) {
                    nextPos = game.board.boardEntityPos.get(nextPos).start;
                }
            }
            player.pos = nextPos;
            playerPos.put(player,nextPos);
        }
    }

}
