package Assignments.Assignment2;

public class Notation {

    //todo fix
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
        String[] expression = infix.split("");
        NotationStack<String> stack = new NotationStack<>(expression.length);
        NotationQueue<String> queue = new NotationQueue<>(expression.length);
        for(String s : expression){
            switch(s){
                case " ":
                    //do nothing
                    break;
                case "(":
                    stack.push(s);
                    break;
                case ")":
                    String stackPeekParenthesis;
                    do {
                        stackPeekParenthesis = stack.pop();
                    } while (!stackPeekParenthesis.equals("("));
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    String stackPeekOperator;
                    boolean lowPrecedenceFlag = false;
                    do {
                        stackPeekOperator = stack.pop();
                        queue.enqueue(stackPeekOperator);
                    } while (stackPeekOperator.matches("[+*/-]"));
                    stack.push(s);
                    break;
                default:
                    if(s.matches("[0-9]")){
                        queue.enqueue(s);
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
                        String a = stack.pop();
                        String b = stack.pop();
                        buffer.append("(")
                                .append(b)
                                .append(s)
                                .append(a)
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
