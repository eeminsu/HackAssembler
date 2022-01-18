package main;

import java.io.*;
import java.util.Scanner;

public class Parser {
    private Scanner sc;
    private String command = "";

    public Parser(File file) throws FileNotFoundException {
        sc = new Scanner(file);
    }

    public boolean hasMoreCommands(){
        return sc.hasNext();
    }

    public void advance() {
        String val = sc.nextLine().trim();

        while (sc.hasNext()){
            if(val != null){
                if(!val.equals("")){
                    if(val.charAt(0) == '/'){
                        val = sc.nextLine().trim();
                    } else {
                        break;
                    }
                } else {
                    val = sc.nextLine().trim();
                }
            } else{
                val = sc.nextLine().trim();
            }
        }
        if(val.contains(" ")){
            command = val.substring(0, val.indexOf(" "));
        } else if(val.contains("/")){
            command = val.substring(0, val.indexOf("/"));
        } else {
            command = val;
        }
    }

    public String commandType(){
        if(command.contains("@")){
            return "A_COMMAND";
        } else if(command.contains("=") | command.contains(";")){
            return "C_COMMAND";
        } else if (command.contains("(") & command.contains(")")){
            return "L_COMMAND";
        }
        return null;
    }

    public String symbol(){
        if(command.contains("@")){
            return command.substring(command.indexOf("@")+1);
        }
        return command.substring(command.indexOf("(")+1, command.indexOf(")"));
    }

    public String dest(){
        if(command.contains("=")){
            return command.substring(0,command.indexOf("="));
        }
        return "null0";
    }

    public String comp(){
        if(command.contains("=") && command.contains(";")){
            return command.substring(command.indexOf("=")+1,command.indexOf(";"));
        } else if(command.contains("=") && !command.contains(";")){
            return command.substring(command.indexOf("=")+1);
        }
        else if(!command.contains("=")){
            return command.substring(0, command.indexOf(";"));
        }

        return "";
    }

    public String jump(){
        if(command.contains(";")){
            return command.substring(command.indexOf(";")+1);
        }
        return "null";
    }
}
