/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Example;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kateryna Nosenko
 */
public class CountLinesFileTest {
    

 
    @Test
    public void testDeliteComment() {
        
        System.out.println("deliteComment");
        String s = "/*gtt */";
        String expResult = "";
                
        
        String result = CountLinesFile.deliteComment(s);
        assertEquals(expResult, result);
        
        result = CountLinesFile.deliteComment("nnnn");
        assertEquals("nnnn", result);
        
        s="// This file contains 3 lines of code\n" +
                  "i=2";
        result = CountLinesFile.deliteComment(s);
        assertEquals("\ni=2", result);
        
        s = "System./*wait*/out./*for*/println/*it*/(\"Hello/*\");";
        result = CountLinesFile.deliteComment(s);
        assertEquals("System.out.println(\"Hello/*\");", result); 
        
        s = "/*****\n" +
            "    * This is a test program with 5 lines of code\n" +
            "    *  \\/* no nesting allowed!\n" +
            "    //*****//***/// Slightly pathological comment ending...";
        result = CountLinesFile.deliteComment(s);
        assertEquals("", result);  
    }

    @Test
    public void testDeliteSpace() {
        System.out.println("deliteSpace");
        String s = "  \n";
        String expResult = "";
        String result = CountLinesFile.deliteSpace(s);
        assertEquals(expResult, result);
        
        s = "     \n";
        expResult = "";
        result = CountLinesFile.deliteSpace(s);
        assertEquals(expResult, result);
        
        
        s = "int i=2;\n" +"        \n" +
            "int j=3;\n";
        expResult = "int i=2;\n" +
                "int j=3;\n";
        result = CountLinesFile.deliteSpace(s);
        assertEquals(expResult, result);
 
    }
        /**
     * Test of countLines method, of class CountLinesFile.
     */
    @Test
    public void testCountLines() {
        System.out.println("countLines");
        String s = "hhhh\n" +
                   "        jjjj\n" +
                   "        jj";
        int expResult = 3;
        int result = CountLinesFile.countLines(s);
        assertEquals(expResult, result);
 
        s = "       hhj\n" +
            "        jjk\n" +
            "        hjk\n" +
            "        fghh";
        expResult = 4;
        result = CountLinesFile.countLines(s);
        assertEquals(expResult, result);
   
    }

    
    /**
     * Test of main method, of class CountLinesFile.
     */
    @Test
    public void testLeftPad() throws Exception {
        System.out.println("testLeftPad");
        
        int n = 5;
        String expResult = "     ";
        String result = CountLinesFile.leftPad(n);
        assertEquals(expResult, result);
        
        n=2;
        expResult = "  ";
        result = CountLinesFile.leftPad(n);
        assertEquals(expResult, result);
        
        n=0;
        expResult = "";
        result = CountLinesFile.leftPad(n);
        assertEquals(expResult, result);
    }

    
}
