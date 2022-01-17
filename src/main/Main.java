package main;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[0].substring(0, args[0].lastIndexOf(".asm")) + ".hack");
        Parser parser = new Parser(inputFile);
        Code code = new Code();
        SymbolTable st = new SymbolTable();

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        int romCnt = 0;
        int ramCnt = 16;

        while(parser.hasMoreCommands()){
            parser.advance();
//            System.out.println(parser.commandType());
            if(parser.commandType().equals("L_COMMAND")){
                st.addEntry(parser.symbol(),romCnt);
            } else{
                romCnt++;
            }
        }

        parser = new Parser(inputFile);

        while(parser.hasMoreCommands()) {
            parser.advance();

            if(parser.commandType() == "C_COMMAND"){
                String dest = "";
                String comp = "";
                String jump = "";

                String machineCode = "111";
                comp = parser.comp();
                dest = parser.dest();
                jump = parser.jump();

                machineCode += code.comp(comp);
                machineCode += code.dest(dest);
                machineCode += code.jump(jump);
                bw.write(machineCode);
                bw.newLine();
            }
            if(parser.commandType() == "A_COMMAND"){
                String tmp = parser.symbol();
                boolean numeric = tmp.matches("-?\\d+(\\.\\d+)?");

                if(numeric){
                    int addr = Integer.parseInt(tmp);
                    String addrStr = Integer.toBinaryString(addr);

                    while(addrStr.length() < 16){
                        addrStr = '0'+addrStr;
                    }

                    bw.write(addrStr);
                    bw.newLine();
                } else{
                    if(!st.contains(parser.symbol())){
                        st.addEntry(parser.symbol(), ramCnt++);
                    }
                    if(st.contains(parser.symbol())){
                        int addr = st.getAddress(parser.symbol());
                        String addrStr = Integer.toBinaryString(addr);

                        while(addrStr.length() < 16){
                            addrStr = '0'+addrStr;
                        }

                        bw.write(addrStr);
                        bw.newLine();
                    }
                }

            }


        }
        bw.close();
    }

}
