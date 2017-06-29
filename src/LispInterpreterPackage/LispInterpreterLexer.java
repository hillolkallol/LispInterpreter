/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LispInterpreterPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author KD
 */
public class LispInterpreterLexer {
    LispInterpreterOperations lispInterpreterOperations = new LispInterpreterOperations();
    public static ArrayList<ArrayList<String>> twodlist = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> dynamicTwodlist = new ArrayList<ArrayList<String>>();

    LispInterpreterParser lispInterpreterParser = new LispInterpreterParser();
    PrintWriter pw;

    public LispInterpreterLexer() throws FileNotFoundException {
        this.pw = new PrintWriter(new File("results.txt"));
    }
    
    public void addRow(ArrayList<String> row) {
        twodlist.add(row);
    }
    
    public void dynamicAddRow(ArrayList<String> row) {
        dynamicTwodlist.add(row);
    }

    public Stack<String> tokenizer(String string) throws IOException {
        String newString = string;
        Stack<String> stack = new Stack<String>();

        newString = newString.replace("(", " ( ");
        newString = newString.replace(")", " ) ");

        StringTokenizer st = new StringTokenizer(newString, " ");
        
        boolean isQuote = false;
        boolean isLambda = false;
        boolean isDynamicLambda = false;
        boolean isFunction = false;
        boolean isDynamicFunction = false;
        int index = 0;
        int count = 0;
        while (st.hasMoreTokens()) {
            //System.out.println(st.nextToken());
            String nextString = st.nextToken();
            stack.push(nextString);
            if (nextString.equals("quote"))
                isQuote = true;
            if (nextString.equals("lambda"))
                isLambda = true;
            if (nextString.equals("dynamiclambda"))
                isDynamicLambda = true;
            if(count < 3) {
                for(int i=0; i<twodlist.size(); i++) {
                    if(twodlist.get(i).get(0).equals(nextString)) {
                        isFunction = true;
                        index = i;
                    }
                }
            }
            if(count < 3) {
                for(int i=0; i<dynamicTwodlist.size(); i++) {
                    if(dynamicTwodlist.get(i).get(0).equals(nextString)) {
                        isDynamicFunction = true;
                        index = i;
                    }
                }
            }
            if (nextString.equals(")") && !isQuote && !isLambda && !isFunction && !isDynamicLambda && !isDynamicFunction) {
                stack = lispInterpreterParser.findCommand(stack);
            }
            count++;
        }
        
        if(isQuote) {
            stack.pop();
            stack = lispInterpreterParser.findQuoteCommand(stack);
        }
        
        if(isLambda) {
            stack.pop();
            stack = lispInterpreterParser.findLambdaCommand(stack);
        }
        
        if(isDynamicLambda) {
            stack.pop();
            stack = lispInterpreterParser.findDynamicLambdaCommand(stack);
        }
        
        if(isFunction) {
            String numOfVariables = twodlist.get(index).get(1);
            ArrayList<String> variables = new ArrayList<String>();
            for (int i = 0; i < Integer.parseInt(numOfVariables); i++) {
                variables.add(twodlist.get(index).get(i + 2));
            }
            
            Stack<String> functionStack = new Stack<String>();
            while(!stack.isEmpty())
                functionStack.push(stack.pop());
            
            String syntex = twodlist.get(index).get(Integer.parseInt(numOfVariables) + 2);

            functionStack.pop();
            functionStack.pop();
            functionStack.pop();
            for(int y = 0; y < variables.size(); y++) {
                syntex = syntex.replace(variables.get(y), functionStack.pop());
            }
            tokenizer(syntex);
        }
        
        if(isDynamicFunction) {
            String numOfVariables = dynamicTwodlist.get(index).get(1);
            ArrayList<String> variables = new ArrayList<String>();
            for (int i = 0; i < Integer.parseInt(numOfVariables); i++) {
                variables.add(dynamicTwodlist.get(index).get(i + 2));
            }
            
            Stack<String> functionStack = new Stack<String>();
            while(!stack.isEmpty())
                functionStack.push(stack.pop());
            
            String syntex = dynamicTwodlist.get(index).get(Integer.parseInt(numOfVariables) + 2);

            functionStack.pop();
            functionStack.pop();
            functionStack.pop();
            for(int y = 0; y < variables.size(); y++) {
                
                lispInterpreterOperations.putDynamicHm(variables.get(y), Double.parseDouble(functionStack.peek()));
                syntex = syntex.replace(variables.get(y), functionStack.pop());
            }
            tokenizer(syntex);
        }
        
        
        if (stack.size() == 0) {
            
        }
        else if (stack.size() == 1) {
            try {
                if (!isNumeric(stack.peek()) && stack.peek() != null) {
                    System.out.println(stack.peek());
                    
                    pw.append(stack.peek());
                    pw.println();
                    //pw.close();
                } else if (Double.parseDouble(stack.peek()) != -1) {
                    if (Math.ceil(Double.parseDouble(stack.peek())) == Math.floor(Double.parseDouble(stack.peek()))) {
                        System.out.println((int) Double.parseDouble(stack.peek()));
                        
                        pw.append ((int) Double.parseDouble(stack.peek()) + "");
                        pw.println();
                        //pw.close();
                    } else {
                        System.out.println(stack.peek());
                        
                        pw.append (stack.peek());
                        pw.println();
                        //pw.close();
                    }
                } else {
                    System.out.println("Something wrong in your syntax! Enter correct syntax!");
                    
                    pw.append ("Something wrong in your syntax! Enter correct syntax!");
                    pw.println();
                    //pw.close();
                }
            } catch (Exception e) {
                System.out.println("Something wrong in your syntax! Enter correct syntax!");
                
                pw.append ("Something wrong in your syntax! Enter correct syntax!");
                pw.println();
                //pw.close();
            }
        } else {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
            
            pw.write ("Something wrong in your syntax! Enter correct syntax!");
            pw.println();
            //pw.close();
        }

        return stack;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public void closePrintWriter() {
        pw.write ("Bye!");
        pw.println();
        pw.close();
    }
}
