package main;

import java.util.ArrayList;

public class MP04 {
    private String label;
    private ArrayList<Command> commands;
    private Command start;

    public MP04(String label) {
        this.label = label;
        this.commands = new ArrayList<Command>();
    }
    
    public void addCommand(Command command){
        this.commands.add(command);
    }
    
    public void setStart(Command start){
        this.start = start;
    }
    
    public void process(ArrayList<Integer> inputs){
        Command curr = start;
        int index = 0;
        ArrayList<Integer> inputList = inputs;
        
        System.out.println("start");
        printCursor(inputList, index);
        
        while(curr != null){
            State temp = curr.process(inputList, index);
            
            System.out.print(curr.getCommandName());
            if(curr.getParams() != null){
                if(curr.getParams().size() == 1)
                    System.out.println(", " + curr.getParams().get(0));
                else
                    System.out.println(", " + curr.getParams().get(0) + ", " + curr.getParams().get(1));
            }else System.out.println("");
            
            if(temp == null){
                curr = null;
            } else{
                inputList = temp.getInputList();
                index = temp.getIndex();
                
                for(int i = 0; i < commands.size(); i++)
                    if(temp.commandID == commands.get(i).getCommandID())
                        curr = commands.get(i);
            }
            
            printCursor(inputList, index);
        }
    }
    
    public void printCursor(ArrayList<Integer> inputs, int index){
        String str = "";
        
        for(int i = 0; i < inputs.size(); i++){
                      
            if(i != index)
                str += "#";
             else 
                str += "[#]";
            
            if(inputs.get(i) > 0)
                str += inputs.get(i);
        }

        str += "#";
        System.out.println(str+"\n");
    }
}
