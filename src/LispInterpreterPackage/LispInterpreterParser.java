/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LispInterpreterPackage;

import java.io.FileNotFoundException;
import java.util.Stack;

/**
 *
 * @author KD
 */
public class LispInterpreterParser {
    Stack<String> stack;
    LispInterpreterOperations lispInterpreterOperations = new LispInterpreterOperations();
    
    public Stack<String> findCommand(Stack<String> s) throws FileNotFoundException {
        stack = s;
        String newcommand;
        Stack<String> newStack = new Stack<String>();
        newcommand = stack.pop();
        try {
            while (!(newcommand = stack.pop()).equals("(")) {
                newStack.push(newcommand);
            }
        } catch (Exception e) {
            System.out.println("Something wrong in your syntax! Enter correct syntax!");
        }
        findOperation(newStack);
        
        return stack;
    }
    
    public Stack<String> findQuoteCommand(Stack<String> s) throws FileNotFoundException {
        stack = s;
        Stack<String> quoteStack = new Stack<String>();
        
        while(!stack.isEmpty())
            quoteStack.push(stack.pop());
        quoteStack.pop();
        findOperation(quoteStack);
        return stack;
    }
    
    public Stack<String> findLambdaCommand(Stack<String> s) throws FileNotFoundException {
        stack = s;
        Stack<String> quoteStack = new Stack<String>();
        
        while(!stack.isEmpty())
            quoteStack.push(stack.pop());
        quoteStack.pop();
        findOperation(quoteStack);
        return stack;
    }
    
    public Stack<String> findDynamicLambdaCommand(Stack<String> s) throws FileNotFoundException {
        stack = s;
        Stack<String> quoteStack = new Stack<String>();
        
        while(!stack.isEmpty())
            quoteStack.push(stack.pop());
        quoteStack.pop();
        findOperation(quoteStack);
        return stack;
    }
    
    public void findOperation(Stack<String> newStack) throws FileNotFoundException {
        String operation = newStack.pop();

        switch (operation.toLowerCase()) {
            case "+": 
                double plusResult = lispInterpreterOperations.plus(newStack);
                stack.push(String.valueOf(plusResult));
                break;
                
            case "-": 
                double minusResult = lispInterpreterOperations.minus(newStack);
                stack.push(String.valueOf(minusResult));
                break;
                
            case "*": 
                double multiplyResult = lispInterpreterOperations.multiply(newStack);
                stack.push(String.valueOf(multiplyResult));
                break;
                
            case "/": 
                double divideResult = lispInterpreterOperations.divide(newStack);
                stack.push(String.valueOf(divideResult));
                break;
                
            case "define": 
                String defineResult = lispInterpreterOperations.define(newStack);
                stack.push(defineResult);
                break;
                
            case "<": 
                int lessThanResult = lispInterpreterOperations.lessThan(newStack);
                stack.push(String.valueOf(lessThanResult));
                break;
                
            case ">": 
                int greaterThanResult = lispInterpreterOperations.greaterThan(newStack);
                stack.push(String.valueOf(greaterThanResult));
                break;
                
            case "=": 
                int equalToResult = lispInterpreterOperations.equalTo(newStack);
                stack.push(String.valueOf(equalToResult));
                break;
                
            case "<=": 
                int lessThanEqualToResult = lispInterpreterOperations.lessThanEqualTo(newStack);
                stack.push(String.valueOf(lessThanEqualToResult));
                break;
                
            case ">=": 
                int greaterThanEqualToResult = lispInterpreterOperations.greaterThanEqualTo(newStack);
                stack.push(String.valueOf(greaterThanEqualToResult));
                break;
                
            case "if": 
                double ifConditionResult = lispInterpreterOperations.ifCondition(newStack);
                stack.push(String.valueOf(ifConditionResult));
                break;
                
            case "set!": 
                double setFunctionResult = lispInterpreterOperations.setFunction(newStack);
                stack.push(String.valueOf(setFunctionResult));
                break;
                
            case "sqrt": 
                double sqrtFunctionResult = lispInterpreterOperations.sqrtFunction(newStack);
                stack.push(String.valueOf(sqrtFunctionResult));
                break;
                
            case "sin": 
                double sinFunctionResult = lispInterpreterOperations.sinFunction(newStack);
                stack.push(String.valueOf(sinFunctionResult));
                break;
                
            case "cos": 
                double cosFunctionResult = lispInterpreterOperations.cosFunction(newStack);
                stack.push(String.valueOf(cosFunctionResult));
                break;
                
            case "tan": 
                double tanFunctionResult = lispInterpreterOperations.tanFunction(newStack);
                stack.push(String.valueOf(tanFunctionResult));
                break;
                
            case "quote": 
                String quoteResult = lispInterpreterOperations.quote(newStack);
                stack.push(String.valueOf(quoteResult));
                break;
                
            case "lambda": 
                String lambdaResult = lispInterpreterOperations.lambda(newStack);
                stack.push(String.valueOf(lambdaResult));
                break;
                
            case "dynamiclambda": 
                String dynamicLambdaResult = lispInterpreterOperations.dynamicLambda(newStack);
                stack.push(String.valueOf(dynamicLambdaResult));
                break;
                
            default:
                String singleResult = lispInterpreterOperations.single(newStack, operation);
                stack.push(singleResult);
                break;
        }        
    }
}
