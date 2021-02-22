package Assignments.Assignment2;

/**
 * Utility class used to take basic four operation mathematical expressions and convert them for easier
 * processing, also able to parse postfix expressions and evaluate them to return a result.
 *
 * @author James Pham
 */
public class Notation {

    /**
     * Utility method to convert a given infix expression into a postfix expression for later processing
     * @param infix Given infix expression to be processed
     * @return infix rearranged as postfix
     * @throws InvalidNotationFormatException If the provided infix expression is incomplete and cannot be parsed properly
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
        //create char array from infix expression for later processing
        char[] expression = infix.toCharArray();
        //create stack and queue for storage and parsing
        NotationStack<Character> stack = new NotationStack<>(expression.length);
        NotationQueue<Character> queue = new NotationQueue<>(expression.length);
        //for every character in the expression
        for(char c : expression){
            switch(c){
                case ' ':
                    //do nothing
                    break;
                case '(':
                    //notify that an expression in the parenthesis has started
                    stack.push(c);
                    break;
                case ')':
                    //if there is a closing parenthesis implying that an expression is finished
                    while(stack.top() != '(') {
                        //take all operations in the stack and queue them
                        if(stack.isEmpty()){
                            throw new InvalidNotationFormatException();
                        } else {
                            queue.enqueue(stack.pop());
                        }
                    }
                    //pop the last element to get rid of the parenthesis
                    stack.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    //if operator present
                    if (!queue.isEmpty()) {
                        try{
                            //only add the operator if it conforms to PEMDAS
                            char top = stack.top();
                            if ((c == '*' || c == '/') && (top == '+' || top == '-')) {
                                queue.enqueue(stack.pop());
                            }
                        } catch (StackUnderflowException e){
                            throw new InvalidNotationFormatException();
                        }
                    }
                    //add operation to stack
                    stack.push(c);
                    break;
                default:
                    //add any digits to queue
                    if(Character.isDigit(c)){
                        queue.enqueue(c);
                    } else {
                        throw new InvalidNotationFormatException();
                    }
            }
        }
        //for all remaining elements in the stack just add them to queue for processing
        while(!stack.isEmpty()){
            queue.enqueue(stack.pop());
        }
        //assume everything in the queue is the complete postfix expression
        return queue.toString();
    }

    /**
     * A utility method used to convert a given postfix expression to infix for later processing
     * @param postfix Given postfix expression for processing
     * @return postfix rearranged as infix
     * @throws InvalidNotationFormatException If the provided postfix expression is incomplete and cannot be parsed properly
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
        //create string array for processing
        String[] expression = postfix.split("");
        //create stack for storage
        NotationStack<String> stack = new NotationStack<>(expression.length);
        //create StringBuilder buffer for expression creation
        StringBuilder buffer = new StringBuilder();
        //for every character in the expression
        for(String s : expression){
            switch(s){
                case " ":
                    //do nothing
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    //if there is an operator, get the numbers in the stack append parenthesis around them
                    if(stack.size() < 2){
                        throw new InvalidNotationFormatException();
                    } else {
                        String right = stack.pop();
                        String left = stack.pop();
                        buffer.append("(")
                                .append(left).append(s).append(right)
                                .append(")");
                        stack.push(buffer.toString());
                        //reset buffer
                        buffer.setLength(0);
                    }
                    break;
                default:
                    //if its not an operator, check if we have a number and push it to the stack for later processing
                    if(s.matches("[0-9]")){
                        stack.push(s);
                    } else {
                        throw new InvalidNotationFormatException();
                    }
            }
        }
        //return the final expression stored on the stack
        if(stack.size() > 1){
            throw new InvalidNotationFormatException();
        } else {
            return stack.pop();
        }
    }

    /**
     * A utility method for evaluating a given postfix expression and calculating a value
     * @param postfixExpr Given postfix expression for processing
     * @return The output of the given postfix expression
     * @throws InvalidNotationFormatException If the provided postfix expression is incomplete and cannot be parsed properly
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
        String[] expression = postfixExpr.split("");
        NotationStack<String> stack = new NotationStack<>(expression.length);
        for(String s : expression){
            switch(s){
                case " ":
                    //do nothing
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    //if operator present
                    if(stack.size() < 2){
                        throw new InvalidNotationFormatException();
                    } else {
                        //grab the numbers that the op is being applied to (first on the stack) and perform operation
                        double right = Double.parseDouble(stack.pop());
                        double left = Double.parseDouble(stack.pop());
                        double result = switch (s) {
                            case "+" -> left + right;
                            case "-" -> left - right;
                            case "*" -> left * right;
                            case "/" -> left / right;
                            default -> 0;
                        };
                        //add result back to stack
                        stack.push(Double.toString(result));
                    }
                    break;
                default:
                    //if an open parenthesis is present or a number is present, push to stack for later processing
                    if(s.equals("(") || s.matches("[0-9]")){
                        stack.push(s);
                    } else {
                        throw new InvalidNotationFormatException();
                    }
            }
        }
        //return last element on the stack as the resultant
        if(stack.size() > 1){
            throw new InvalidNotationFormatException();
        } else {
            return Double.parseDouble(stack.pop());
        }
    }

}
