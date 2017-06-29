/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LispInterpreterPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KD
 */
public class LispInterpreterMain {
    public static void main(String args[]) throws FileNotFoundException {
        LispInterpreterLexer lispInterpreterLexer = new LispInterpreterLexer();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the fancy new Prompt LISP INTERPRETER, type in LISP commands!");

        while(true){
            try {
                System.out.print("Prompt> ");
                String commandLine = br.readLine();
                if (commandLine.toLowerCase().equals("(quit)"))
                        break;
                
                lispInterpreterLexer.tokenizer(commandLine);
                
            } catch (IOException ex) {
                Logger.getLogger(LispInterpreterMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Bye!");
        lispInterpreterLexer.closePrintWriter();
    }
}
