/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LispInterpreterPackage;

import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author KD
 */
public class LispInterpreterOperations {

    HashMap<String, Double> hm = new HashMap<String, Double>();
    public static HashMap<String, Double> dynamicHm = new HashMap<String, Double>();
    
    public void putDynamicHm(String s, Double d) {
        dynamicHm.put(s, d);
    }

    public double plus(Stack<String> newStack) {
        double answer = 0;
        try {
            while (!newStack.isEmpty()) {
                if (isNumeric(newStack.peek())) {
                    double variable = Double.parseDouble(newStack.pop());
                    answer = answer + variable;
                } else {
                    if(dynamicHm.isEmpty()) {
                        double variable = hm.get(newStack.pop());
                        answer = answer + variable;
                    }
                    else {
                        double variable = dynamicHm.get(newStack.peek());
                        answer = answer + variable;
                        dynamicHm.remove(newStack.pop());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");                                
        }

        //System.out.println(answer);
        return answer;
    }

    public double minus(Stack<String> newStack) {
        double answer = 0;
        try {
            if (isNumeric(newStack.peek())) {
                answer = Double.parseDouble(newStack.pop());
            } else {
                if(dynamicHm.isEmpty()) {
                    answer = hm.get(newStack.pop());
                }
                else {
                    answer = dynamicHm.get(newStack.peek());
                    dynamicHm.remove(newStack.pop());
                }
            }

            if (newStack.isEmpty()) {
                answer = -answer;
            }

            while (!newStack.isEmpty()) {
                if (isNumeric(newStack.peek())) {
                    double variable = Double.parseDouble(newStack.pop());
                    answer = answer - variable;
                } else {
                    if(dynamicHm.isEmpty()) {
                        double variable = hm.get(newStack.pop());
                        answer = answer - variable;
                    }
                    else {
                        double variable = dynamicHm.get(newStack.peek());
                        answer = answer - variable;
                        dynamicHm.remove(newStack.pop());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }

    public double multiply(Stack<String> newStack) {
        double answer = 1;
        try {
            if (isNumeric(newStack.peek())) {
                answer = Double.parseDouble(newStack.pop());
            } else {
                if(dynamicHm.isEmpty()) {
                    answer = hm.get(newStack.pop());
                }
                else {
                    answer = dynamicHm.get(newStack.peek());
                    dynamicHm.remove(newStack.pop());
                }
            }

            while (!newStack.isEmpty()) {
                if (isNumeric(newStack.peek())) {
                    double variable = Double.parseDouble(newStack.pop());
                    answer = answer * variable;
                } else {
                    if(dynamicHm.isEmpty()) {
                        double variable = hm.get(newStack.pop());
                        answer = answer * variable;
                    }
                    else {
                        double variable = dynamicHm.get(newStack.peek());
                        answer = answer * variable;
                        dynamicHm.remove(newStack.pop());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }

    public double divide(Stack<String> newStack) {
//        String answerString = null;
        double answer = 1;
        try {
            if (isNumeric(newStack.peek())) {
                answer = Double.parseDouble(newStack.pop());
            } else {
                if(dynamicHm.isEmpty()) {
                    answer = hm.get(newStack.pop());
                }
                else {
                    answer = dynamicHm.get(newStack.peek());
                    dynamicHm.remove(newStack.pop());
                }
            }

            while (!newStack.isEmpty()) {
                if (isNumeric(newStack.peek())) {
                    double variable = Double.parseDouble(newStack.pop());
                    answer = answer / variable;
                } else {
                    if(dynamicHm.isEmpty()) {
                        double variable = hm.get(newStack.pop());
                        answer = answer / variable;
                    }
                    else {
                        double variable = dynamicHm.get(newStack.peek());
                        answer = answer / variable;
                        dynamicHm.remove(newStack.pop());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }

    public String quote(Stack<String> newStack) {
        String stringQuote = "";
        
        while(!newStack.isEmpty()) {
            if(newStack.peek().equals("(") && newStack.get(newStack.size() - 2).equals("quote")) {
                stringQuote = stringQuote + "' ";
                newStack.pop();
                newStack.pop();
                newStack.remove(0);
            }
            else
                stringQuote = stringQuote + newStack.pop() + " ";
        }
        
        stringQuote = stringQuote.replace(" ( ", "(");
        stringQuote = stringQuote.replace(" ) ", ")");
        stringQuote = stringQuote.replace("( ", "(");
        stringQuote = stringQuote.replace(") ", ")");

        //System.out.println(answer);
        //(quote (quote (quote (quote (quote (quote (quote (quote (quote (+ 1 2))))))))))                          
        return stringQuote;
    }
    
    public String lambda(Stack<String> newStack) throws FileNotFoundException {
        String stringQuote = "";
        
        ArrayList<String> row = new ArrayList<String>();
        stringQuote = newStack.peek();
        row.add(newStack.pop());
        newStack.pop();
        
        int count = 0;
        String variables = "";
        while(!newStack.peek().equals(")")) {
            variables = variables + newStack.pop() + " ";
            count++;
        }
        row.add(String.valueOf(count));
        
        StringTokenizer st = new StringTokenizer(variables, " ");
        while (st.hasMoreTokens()) {
            String nextString = st.nextToken();
            row.add(nextString);
        }
        
        newStack.pop();
        
        String lastString = "";
        while(newStack.size() != 0) { 
            lastString = lastString + newStack.pop() + " ";
        }
        
        row.add(lastString);
        
//        System.out.println(row);
        
        LispInterpreterLexer lispInterpreterLexer = new LispInterpreterLexer();
        lispInterpreterLexer.addRow(row);
        
        return stringQuote;
    }
    
    public String dynamicLambda(Stack<String> newStack) throws FileNotFoundException {
        String stringQuote = "";
        
        ArrayList<String> row = new ArrayList<String>();
        stringQuote = newStack.peek();
        row.add(newStack.pop());
        newStack.pop();
        
        int count = 0;
        String variables = "";
        while(!newStack.peek().equals(")")) {
            variables = variables + newStack.pop() + " ";
            count++;
        }
        row.add(String.valueOf(count));
        
        StringTokenizer st = new StringTokenizer(variables, " ");
        while (st.hasMoreTokens()) {
            String nextString = st.nextToken();
            row.add(nextString);
        }
        
        newStack.pop();
        
        String lastString = "";
        while(newStack.size() != 0) {
            lastString = lastString + newStack.pop() + " ";
        }
        
        row.add(lastString);
        
//        System.out.println(row);
        
        LispInterpreterLexer lispInterpreterLexer = new LispInterpreterLexer();
        lispInterpreterLexer.dynamicAddRow(row);
        
        return stringQuote;
    }

    public String single(Stack<String> newStack, String operation) {
        String answer = null;
        try {
            if (newStack.isEmpty()) {
                if (isNumeric(operation)) {
                    answer = operation;
                } else {
                    answer = String.valueOf(hm.get(operation));
                }
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }

    public String define(Stack<String> newStack) {
        String answer = null;
        try {
            answer = newStack.pop();
            if (newStack.size() == 1) {
                double variable = Double.parseDouble(newStack.pop());
                hm.put(answer, variable);
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }

    public int lessThan(Stack<String> newStack) {
        int answer = -1;
        double firstValue, secondValue;
        try {
            if (isNumeric(newStack.peek())) {
                firstValue = Double.parseDouble(newStack.pop());
            } else {
                firstValue = hm.get(newStack.pop());
            }

            if (isNumeric(newStack.peek())) {
                secondValue = Double.parseDouble(newStack.pop());
            } else {
                secondValue = hm.get(newStack.pop());
            }

            if (firstValue < secondValue) {
                answer = 1;
            } else {
                answer = 0;
            }

        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public int greaterThan(Stack<String> newStack) {
        int answer = -1;
        double firstValue, secondValue;
        try {
            if (isNumeric(newStack.peek())) {
                firstValue = Double.parseDouble(newStack.pop());
            } else {
                firstValue = hm.get(newStack.pop());
            }

            if (isNumeric(newStack.peek())) {
                secondValue = Double.parseDouble(newStack.pop());
            } else {
                secondValue = hm.get(newStack.pop());
            }

            if (firstValue > secondValue) {
                answer = 1;
            } else {
                answer = 0;
            }

        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public int equalTo(Stack<String> newStack) {
        int answer = -1;
        double firstValue, secondValue;
        try {
            if (isNumeric(newStack.peek())) {
                firstValue = Double.parseDouble(newStack.pop());
            } else {
                firstValue = hm.get(newStack.pop());
            }

            if (isNumeric(newStack.peek())) {
                secondValue = Double.parseDouble(newStack.pop());
            } else {
                secondValue = hm.get(newStack.pop());
            }

            if (firstValue == secondValue) {
                answer = 1;
            } else {
                answer = 0;
            }

        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public int lessThanEqualTo(Stack<String> newStack) {
        int answer = -1;
        double firstValue, secondValue;
        try {
            if (isNumeric(newStack.peek())) {
                firstValue = Double.parseDouble(newStack.pop());
            } else {
                firstValue = hm.get(newStack.pop());
            }

            if (isNumeric(newStack.peek())) {
                secondValue = Double.parseDouble(newStack.pop());
            } else {
                secondValue = hm.get(newStack.pop());
            }

            if (firstValue <= secondValue) {
                answer = 1;
            } else {
                answer = 0;
            }

        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public int greaterThanEqualTo(Stack<String> newStack) {
        int answer = -1;
        double firstValue, secondValue;
        try {
            if (isNumeric(newStack.peek())) {
                firstValue = Double.parseDouble(newStack.pop());
            } else {
                firstValue = hm.get(newStack.pop());
            }

            if (isNumeric(newStack.peek())) {
                secondValue = Double.parseDouble(newStack.pop());
            } else {
                secondValue = hm.get(newStack.pop());
            }

            if (firstValue >= secondValue) {
                answer = 1;
            } else {
                answer = 0;
            }

        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public double ifCondition(Stack<String> newStack) {
        double answer = -1;
        double firstValue, secondValue, thirdValue;
        try {
            firstValue = Double.parseDouble(newStack.pop());

            if (isNumeric(newStack.peek())) {
                secondValue = Double.parseDouble(newStack.pop());
            } else {
                secondValue = hm.get(newStack.pop());
            }
            
            if (isNumeric(newStack.peek())) {
                thirdValue = Double.parseDouble(newStack.pop());
            } else {
                thirdValue = hm.get(newStack.pop());
            }

            if (firstValue == 1) {
                answer = secondValue;
            } else {
                answer = thirdValue;
            }

        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public double setFunction(Stack<String> newStack) {
        double variable = -1;
        try {
            String keyString = newStack.pop();
            if (newStack.size() == 1) {
                variable = Double.parseDouble(newStack.pop());
                hm.put(keyString, variable);
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return variable;
    }
    
    public double sqrtFunction(Stack<String> newStack) {
        double answer = -1;
        double value;
        try {
            if (isNumeric(newStack.peek())) {
                value = Double.parseDouble(newStack.pop());
            } else {
                value = hm.get(newStack.pop());
            }
            answer = Math.sqrt(value);
            
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public double sinFunction(Stack<String> newStack) {
        double answer = -1;
        double value;
        try {
            if (isNumeric(newStack.peek())) {
                value = Double.parseDouble(newStack.pop());
            } else {
                value = hm.get(newStack.pop());
            }
            answer = Math.sin(value);
            
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public double cosFunction(Stack<String> newStack) {
        double answer = -1;
        double value;
        try {
            if (isNumeric(newStack.peek())) {
                value = Double.parseDouble(newStack.pop());
            } else {
                value = hm.get(newStack.pop());
            }
            answer = Math.cos(value);
            
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }
    
    public double tanFunction(Stack<String> newStack) {
        double answer = -1;
        double value;
        try {
            if (isNumeric(newStack.peek())) {
                value = Double.parseDouble(newStack.pop());
            } else {
                value = hm.get(newStack.pop());
            }
            answer = Math.tan(value);
            
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }

        //System.out.println(answer);
        return answer;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
