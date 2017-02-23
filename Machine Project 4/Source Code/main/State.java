package main;

import java.util.ArrayList;

public class State {
    ArrayList<Integer> inputList;
    int index;
    int commandID;

    public State(ArrayList<Integer> inputList, int index, int commandID) {
        this.inputList = inputList;
        this.index = index;
        this.commandID = commandID;
    }

    public ArrayList<Integer> getInputList() {
        return inputList;
    }

    public void setInputList(ArrayList<Integer> inputList) {
        this.inputList = inputList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStateNum() {
        return commandID;
    }

    public void setStateNum(int stateNum) {
        this.commandID = stateNum;
    }
}
