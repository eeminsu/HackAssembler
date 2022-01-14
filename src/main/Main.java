package main;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[0].substring(0, args[0].lastIndexOf(".asm")) + ".hack");
        Parser parser = new Parser(inputFile);
        Code code = new Code();


        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        while(parser.hasMoreCommands()) {
            parser.advance();

            String dest = "";
            String comp = "";
            String jump = "";

            switch (parser.commandType()){
                case "A_COMMAND" :
                case "L_COMMAND" :
                    bw.write(parser.symbol());
                    bw.newLine();
                    break;
                case "C_COMMAND" :
                    String machineCode = "111";
                    comp = parser.comp();
                    dest = parser.dest();
                    jump = parser.jump();

                    machineCode += code.comp(comp);
                    machineCode += code.dest(dest);
                    machineCode += code.jump(jump);
                    bw.write(machineCode);
                    bw.newLine();
                    break;
            }
        }
        bw.close();
    }

}
