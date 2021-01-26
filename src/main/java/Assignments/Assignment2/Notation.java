package Assignments.Assignment2;

public class Notation {

    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
        char[] expression = infix.toCharArray();
        NotationStack<Character> stack = new NotationStack<>(expression.length);
        NotationQueue<Character> queue = new NotationQueue<>(expression.length);
        for(char c : expression){
            switch(c){
                case ' ':
                    //do nothing
                    break;
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while(stack.top() != '(') {
                        if(stack.isEmpty()){
                            throw new InvalidNotationFormatException();
                        } else {
                            queue.enqueue(stack.pop());
                        }
                    }
                    stack.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    if (!queue.isEmpty()) {
                        try{
                            char top = stack.top();
                            if ((c == '*' || c == '/') && (top == '+' || top == '-')) {
                                queue.enqueue(stack.pop());
                            }
                        } catch (StackUnderflowException e){
                            throw new InvalidNotationFormatException();
                        }
                    }
                    stack.push(c);
                    break;
                default:
                    if(Character.isDigit(c)){
                        queue.enqueue(c);
                    } else {
                        throw new InvalidNotationFormatException();
                    }
            }
        }
        while(!stack.isEmpty()){
            queue.enqueue(stack.pop());
        }
        return queue.toString();
    }

    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
        String[] expression = postfix.split("");
        NotationStack<String> stack = new NotationStack<>(expression.length);
        StringBuilder buffer = new StringBuilder();
        for(String s : expression){
            switch(s){
                case " ":
                    //do nothing
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    if(stack.size() < 2){
                        throw new InvalidNotationFormatException();
                    } else {
                        String right = stack.pop();
                        String left = stack.pop();
                        buffer.append("(")
                                .append(left).append(s).append(right)
                                .append(")");
                        stack.push(buffer.toString());
                        buffer.setLength(0);
                    }
                    break;
                default:
                    if(s.matches("[0-9]")){
                        stack.push(s);
                    } else {
                        throw new InvalidNotationFormatException();
                    }
            }
        }
        if(stack.size() > 1){
            throw new InvalidNotationFormatException();
        } else {
            return stack.pop();
        }
    }

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
                    if(stack.size() < 2){
                        throw new InvalidNotationFormatException();
                    } else {
                        double right = Double.parseDouble(stack.pop());
                        double left = Double.parseDouble(stack.pop());
                        double result = switch (s) {
                            case "+" -> left + right;
                            case "-" -> left - right;
                            case "*" -> left * right;
                            case "/" -> left / right;
                            default -> 0;
                        };
                        stack.push(Double.toString(result));
                    }
                    break;
                default:
                    if(s.equals("(") || s.matches("[0-9]")){
                        stack.push(s);
                    } else {
                        throw new InvalidNotationFormatException();
                    }
            }
        }
        if(stack.size() > 1){
            throw new InvalidNotationFormatException();
        } else {
            return Double.parseDouble(stack.pop());
        }
    }

}
