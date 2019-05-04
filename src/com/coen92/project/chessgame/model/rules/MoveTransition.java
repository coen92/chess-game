package com.coen92.project.chessgame.model.rules;

import com.coen92.project.chessgame.model.board.Board;

//Todo: fill up the class below - transitioning for one Board to another after Move is done
public class MoveTransition {

    private final Board transitionBoard;    //refers to a Board after Move is done
    private final Move move;                //this is the Move that is going to be done
    private final MoveStatus moveStatus;    //tells us if we are able to do the Move

    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }
}
