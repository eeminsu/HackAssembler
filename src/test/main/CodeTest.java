package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {
    static Code code;

    @BeforeEach
    void setUp(){
        code = new Code();
    }

    @Test
    void dest() {
        String actual = "001";

        assertEquals(code.dest("M"), actual);
    }

    @Test
    void comp() {
        String  actual = "0110111";

        assertEquals(code.comp("A+1"), actual);
    }

    @Test
    void jump() {
        String actual = "010";

        assertEquals(code.jump("JEQ"), actual);
    }
}