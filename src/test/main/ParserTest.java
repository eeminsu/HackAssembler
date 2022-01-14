package main;

import main.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    static Parser ps;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        ps = new Parser(new File("C:\\Users\\ADMIN\\Desktop\\test.txt"));

        if(ps.hasMoreCommands()){
            ps.advance();
        }
    }

    @Test
    void commandType() {
        String str = "C_COMMAND";
        assertEquals(ps.commandType(), str);

    }

    @Test
    void symbol() {
        String str = "1234";

        if(ps.commandType().equals("A_COMMAND") | ps.commandType().equals("L_COMMAND")){
            assertEquals(ps.symbol(), str);
        }
    }

    @Test
    void dest() {
        String str = "M";

        if(ps.commandType().equals("C_COMMAND")){
            assertEquals(ps.dest(), str);
        }
    }

    @Test
    void comp() {
        String str = "D";

        if(ps.commandType().equals("C_COMMAND")){
            assertEquals(ps.comp(), str);
        }
    }

    @Test
    void jump() {
        if (ps.commandType().equals("C_COMMAND")){
            assertEquals(ps.jump(), "");
        }
    }
}