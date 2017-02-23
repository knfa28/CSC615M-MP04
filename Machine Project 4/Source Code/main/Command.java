package main;

import java.util.ArrayList;

public class Command {
    private int commandID;
    private String commandName;
    private ArrayList<Integer> params;

    public Command(int commandID, String commandName, ArrayList<Integer> params) {
        this.commandID = commandID;
        this.commandName = commandName;
        
        if(params != null)
            this.params = params;
        else this.params = null;
    }    
    
    public State process(ArrayList<Integer> inputs, int index){
        State state = null;
       
        switch (commandName) {
            case "shR":
                for(int i = 0; i < params.get(0); i++){
                    if(index == inputs.size())
                        inputs.add(0);
                    index += 1;
                }
                return new State(inputs, index, commandID+1);
            case "shL":
                for(int i = 0; i < params.get(0); i++){
                    if(index == 0){
                        inputs.add(0);
                        index = 1;
                    }
                    index -= 1;
                }
                return new State(inputs, index, commandID+1);
            case "copy":
                if(index - params.get(0) >= 0){
                    if(inputs.size() < index + 1)
                        inputs.add(0);
                    
                    inputs.set(index, inputs.get(index - params.get(0)));
                    index += 1;
                    
                    return new State(inputs, index, commandID+1);
                }else return null;
            case "const":
                inputs.add(params.get(0));            
                return new State(inputs, index, commandID+1);
            case "move":
                int count = 0;
                
                while(count != params.get(0)){
                    inputs.remove(0);
                    //inputs.remove(index-1);
                    count++;
                    index--;
                }
                
                return new State(inputs, index, commandID+1);
            case "pushL":
                inputs.remove(0);
                return new State(inputs, index-1, commandID+1);
            case "inc":
                inputs.add(inputs.get(index) + 1);
                index += 1;   
                //inputs.set(index, inputs.get(index) + 1);
                return new State(inputs, index, commandID+1);
            case "dec":
                inputs.add(inputs.get(index) - 1);
                index += 1;
                //inputs.set(index, inputs.get(index) - 1);
                return new State(inputs, index, commandID+1);
            case "add":
                inputs.set(index, inputs.get(index) + inputs.get(index + 1));
                inputs.set(index + 1, 0);
                return new State(inputs, index, commandID+1);
            case "mult":
                inputs.set(index, inputs.get(index) * inputs.get(index + 1));
                inputs.set(index + 1, 0);
                return new State(inputs, index, commandID+1);
            case "monus":
            {
                int temp = inputs.get(index) - inputs.get(index + 1);
                if(temp  >= 0)
                    inputs.set(index, temp);
                else inputs.set(index, 0);
                inputs.set(index + 1, 0);
                return new State(inputs, index, commandID+1);
                
            }
            case "swap":
            {
                int temp = inputs.get(index);
                inputs.set(index, inputs.get(index + 1));
                inputs.set(index + 1, temp);
                return new State(inputs, index, commandID+1);
                
            }
            case "goto":
                return new State(inputs, index, params.get(0));
            case "gotoEQ":
            {
                int num1 = inputs.get(index);
                int num2 = 0;
                if(index+1 != inputs.size())
                    num2 = inputs.get(index+1);
                
                inputs = new ArrayList<Integer>(inputs.subList(0,index));
                
                if(num1 == num2)
                    return new State(inputs, index, params.get(0));
                else
                    return new State(inputs, index, commandID+1);
                
            }
            case "gotoNE":
            {
                int num1 = inputs.get(index);
                int num2 = 0; 
                if(index+1 != inputs.size())
                    num2 = inputs.get(index+1);
                
                inputs = new ArrayList<Integer>(inputs.subList(0,index));
                
                if(num1 != num2)
                    return new State(inputs, index, params.get(0));
                else
                    return new State(inputs, index, commandID+1);
            }
            case "gotoGE":
            {
                int num1 = inputs.get(index);
                int num2 = 0;
                if(index+1 != inputs.size())
                    num2 = inputs.get(index+1);
                
                inputs = new ArrayList<Integer>(inputs.subList(0,index));
                
                if(num1 >= num2)
                    return new State(inputs, index, params.get(0));
                else
                    return new State(inputs, index, commandID+1);
            }
            case "gotoGT":
            {
                int num1 = inputs.get(index);
                int num2 = 0;
                if(index+1 != inputs.size())
                    num2 = inputs.get(index+1);
                
                inputs = new ArrayList<Integer>(inputs.subList(0,index));
                
                if(num1 > num2)
                    return new State(inputs, index, params.get(0));
                else
                    return new State(inputs, index, commandID+1);
            }
            case "gotoLE":
            {
                int num1 = inputs.get(index);
                int num2 = 0;
                if(index+1 != inputs.size())
                    num2 = inputs.get(index+1);
                
                inputs = new ArrayList<Integer>(inputs.subList(0,index));
                
                if(num1 <= num2)
                    return new State(inputs, index, params.get(0));
                else
                    return new State(inputs, index, commandID+1);
            }
            case "gotoLT":
            {
                int num1 = inputs.get(index);
                int num2 = 0;
                if(index+1 != inputs.size())
                    num2 = inputs.get(index+1); 
                       
            inputs = new ArrayList<Integer>(inputs.subList(0,index));
            
            if(num1 < num2)
                return new State(inputs, index, params.get(0));
            else
                return new State(inputs, index, commandID+1);
        }
            case "halt":
                return null;
        }
        
        return state;
    }

    public int getCommandID() {
        return commandID;
    }

    public void setCommandID(int commandID) {
        this.commandID = commandID;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public void setParams(ArrayList<Integer> params) {
        this.params = params;
    }
}
