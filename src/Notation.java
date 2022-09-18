// Author: Sai Malkireddy
public class Notation {

    /**
     * Evaluates a postfix expression from a string to a double
     *
     * @throws InvalidNotationFormatException - if the postfix expression format is invalid
     * @parameters postfixExpr - the postfix expression in String format
     * @returns the evaluation of the postfix expression as a double
     */
    public static double evaluatePostfixExpression(java.lang.String postfixExpr) throws InvalidNotationFormatException {
        NotationStack<Double> operatorStack = new NotationStack<Double>();
        try { // try to parse the postfix expression
            for (int i = 0; i < postfixExpr.length(); i++) {
                char c = postfixExpr.charAt(i); // get the current character
                if (Character.isDigit(c)) {
                    operatorStack.push(Character.getNumericValue(c) + 0.0); // push the digit to the stack if it is a digit
                } else if (operatorStack.size() >= 2) { // if the stack has at least 2 elements
                    Double operand2 = operatorStack.pop();
                    Double operand1 = operatorStack.pop();
                    if (c == '+')
                        operatorStack.push(operand1 + operand2); // add the two topmost elements
                    else if (c == '-')
                        operatorStack.push(operand1 - operand2); // subtract the two topmost elements
                    else if (c == '*')
                        operatorStack.push(operand1 * operand2);  // multiply the two topmost elements
                    else if (c == '/')
                        operatorStack.push(operand1 / operand2); // divide the two topmost elements
                    else if (c == '%')
                        operatorStack.push(operand1 % operand2); // modulo the two topmost elements
                } else { // if the stack has less than 2 elements throw an exception
                    throw new InvalidNotationFormatException();
                }
            }
            if (operatorStack.size() == 1)
                return operatorStack.pop(); // if the stack has only one element return it
            else throw new InvalidNotationFormatException(); // if the stack has more than one element throw an exception
        } catch (StackOverflowException e) {
            throw new InvalidNotationFormatException();
        } catch (StackUnderflowException e) {
            throw new InvalidNotationFormatException();
        }
    }

    /**
     * Convert the Postfix expression to the Infix expression. Must include Modulus (%) and support double digits.
     *
     * @throws InvalidNotationFormatException - if the postfix expression format is invalid
     * @parameters postfix - the postfix expression in string format
     * @returns the infix expression in string format
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        NotationStack<String> operatorStack = new NotationStack<String>();
        String postfix = ""; // the postfix expression
        try { // try to parse the infix expression
            for (int i = 0; i < infix.length(); i++) {
                char c = infix.charAt(i); // get the current character
                if (Character.isDigit(c)) {
                    postfix += c; // add the digit to the postfix expression
                } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == ')' || c == '(') { // if the character is an operator
                    if (c == '(') { // if the character is an opening parenthesis
                        operatorStack.push(Character.toString(c)); // push the character to the stack
                    } else if (c == ')') {
                        while (!operatorStack.top().equals("(")) { // while the top of the stack is not an opening parenthesis
                            postfix += operatorStack.pop(); // pop the operator from the stack and add it to the postfix expression
                        }
                        operatorStack.pop();
                    } else {
                        if (operatorStack.isEmpty()) { 
                            operatorStack.push(Character.toString(c)); // if the stack is empty push the operator to the stack
                        } else {
                            if (c == '+' || c == '-') { // if the operator is addition or subtraction
                                if(!operatorStack.top().equals("("))
                                    postfix += operatorStack.pop();
                                operatorStack.push(Character.toString(c));
                            } else if (c == '*' || c == '/' ||c == '%') { // if the character is a multiplication or division operator
                                while (!operatorStack.isEmpty() && !operatorStack.top().equals("(") && (operatorStack.top().equals("*") || operatorStack.top().equals("/")||operatorStack.top().equals("%"))) {
                                    postfix += operatorStack.pop();
                                }
                                operatorStack.push(Character.toString(c));
                            }
                        }
                    }
                } else{ // if the character is not a digit or an operator
                    throw new InvalidNotationFormatException();
                }
            }
            while (!operatorStack.isEmpty()) {
                postfix += operatorStack.pop(); // while the stack is not empty pop the operators from the stack and add them to the postfix expression
            }
        } catch (StackUnderflowException e) {
            throw new InvalidNotationFormatException();
        } catch (StackOverflowException e) {
            throw new InvalidNotationFormatException();
        }
        return postfix;
    }

    /**
     * Convert the postfix expression to the infix expression. Must include Modulus (%) and support double digits.
     *
     * @throws InvalidNotationFormatException - if the postfix expression format is invalid
     * @parameters postfix - the infix expression in string format
     * @returns the infix expression in string format
     */
    public static java.lang.String convertPostfixToInfix(java.lang.String postfix) throws InvalidNotationFormatException {
        NotationQueue<String> queue = new NotationQueue<String>();
        NotationStack<String> operatorStack = new NotationStack<String>();
        String infix = "";
        try {
            for (int i = 0; i < postfix.length(); i++) { // try to parse the postfix expression
                char c = postfix.charAt(i);
                if (Character.isDigit(c)) {
                    queue.enqueue(Character.toString(c)); // enqueue the digit to the queue
                    if(queue.size() == 1){
                        operatorStack.push(queue.dequeue()); // push the digit to the stack if it is the first digit
                    }
                }else if (operatorStack.size() >= 2) {
                    String operand2 = operatorStack.pop();
                    String operand1 = operatorStack.pop();
                    if(operand1.length() > 1) //
                        operand1 = "(" + operand1 + ")"; // if the first operand is a has more than 1 add parentheses around it
                    if(operand2.length() > 1)
                        operand2 = "(" + operand2 + ")"; // if the second operand has more than 1 add parentheses around it
                    if (c == '+')
                        operatorStack.push(operand1  + "+"  + operand2);
                    else if (c == '-')
                        operatorStack.push(operand1 + "-" + operand2);
                    else if (c == '*')
                        operatorStack.push(operand1 + "*" + operand2);
                    else if (c == '/')
                        operatorStack.push(operand1 + "/" + operand2);
                    else if (c == '%')
                        operatorStack.push(operand1 + "%" + operand2);
                } else {
                    throw new InvalidNotationFormatException(); // if the stack has less than 2 elements throw an exception
                }
            }
            if (operatorStack.size() == 1)
                return operatorStack.pop(); // if the stack has only one element return it
            else throw new InvalidNotationFormatException(); // if the stack has more than one element throw an exception
        } catch (QueueUnderflowException e) {
            throw new InvalidNotationFormatException();
        } catch (QueueOverflowException e) {
            throw new InvalidNotationFormatException();
        } catch (StackUnderflowException e) {
            throw new InvalidNotationFormatException();
        } catch (StackOverflowException e) {
            throw new InvalidNotationFormatException();
        }
    }
}

