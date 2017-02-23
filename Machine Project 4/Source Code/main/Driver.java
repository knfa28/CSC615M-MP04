package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        MP04 program = readProgram("src/main/program.txt");
        ArrayList<Integer> inputs = readInput("src/main/input.txt");
        
        System.out.print("Input String: ");
        for(int i = 0; i < inputs.size(); i++){
            if(i != inputs.size() - 1)
                System.out.print(inputs.get(i) + ", ");
            else
                System.out.println(inputs.get(i)); 
        }
        
        if(!inputs.isEmpty()){
            program.process(inputs);
        } else
            System.out.println("Input file is empty!");
    }
    
    public static MP04 readProgram(String filename){        
        Path path = Paths.get(filename);
        Charset cs = StandardCharsets.ISO_8859_1;
        boolean isStart = true;
        String line;

        MP04 program = new MP04(filename);
        
        try(BufferedReader reader = Files.newBufferedReader(path, cs)){
            while((line = reader.readLine()) != null){
                String[] readLine = line.split(",");
                int commandID = Integer.parseInt(readLine[0]);
                String commandName = readLine[1];
                ArrayList<Integer> params = new ArrayList<Integer>();
                
                if(readLine.length > 2){
                    for(int i = 2; i < readLine.length; i++)
                        params.add(Integer.parseInt(readLine[i]));
                } else params = null;
                
                Command command = new Command(commandID, commandName, params);
                
                program.addCommand(command);
                    
                if(isStart){
                    program.setStart(command);
                    isStart = false;
                }
            }			
      	} catch(IOException x){
            System.err.println(x);
      	}
        
        return program;
    }
    
    public static ArrayList<Integer> readInput(String filename){        
        ArrayList<Integer> input = new ArrayList<Integer>();
        
        Path path = Paths.get(filename);
        Charset cs = StandardCharsets.ISO_8859_1;
        String line;
		
        try(BufferedReader reader = Files.newBufferedReader(path, cs)){
            if((line = reader.readLine()) != null){
                String[] readLine = line.split(",");
                
                for(int i = 0; i < readLine.length; i++)
                    input.add(Integer.parseInt(readLine[i]));
            }			
      	} catch(IOException x){
            System.err.println(x);
      	}
        
        return input;
    }
}
