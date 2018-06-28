import java.util.Deque;
import java.util.LinkedList;

public class Calculater {
    public int calculate(String s) {
        // TWO stack for similar RPN processing evaluation.
        // Basci rules for eval:
        // 1. When we see operands, we write it down --> in an operands stack
        // 2. When we see operators, we continuily check the operators stack to make sure the precedence
        //    in the operator stack is in an increasing order from bottom to the top.
        //    [new operator pop out other operators in the stack which has higher or equal precedence for the new operator.]
        // 3. When pop out an operator, immediately calculate the current result using top 2 operands in the operands stack
        //    (first pop one should be the "right" one in the infix sequence), and push the result back to the operands stack
        //    [This step is similar as the evaluating RPN, but doing in the RPN generation process]
        // 4. After process all the expression, use the 2 stack(combined as a RPN expression) to evaluate rest of those operands.
        // 5. See line 41 comment

        Deque<Integer> operands = new LinkedList<>();
        Deque<Character> operators = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ' ') continue; // ignore the space

            if (Character.isDigit(cur)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + Integer.valueOf(s.charAt(i) - '0');
                    i++;
                }

                // here we can also add logic to deal with negtive num
                operands.push(num); // just "write down" the operand
                // !!! You i pointer has been moved, UPDATE THE cur variable !!! And watch the boundary !!!
                if (i < s.length()) cur = s.charAt(i);
            }
            // deal with operands and brackets
            if (cur == '(') {
                // left bracket is the lowest priority operator, just push it to the operators stack
                operators.push(cur);
            }

            if (cur == ')') {
                // 5. When see a right bracket, just pop all operators out until see the right bracket, no priority check needed
                // and then follow the rules 3, see comment in line 9
                while (!operators.isEmpty() && operators.peek() != '(') {
                    operands.push(evaluate(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.pop(); // pop '('
            }

            if (cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                // rule 2, see comment in line 6 and then rule 3, see comment in line 9
                while (!operators.isEmpty() && precedence(cur) <= precedence(operators.peek())) {
                    operands.push(evaluate(operators.pop(), operands.pop(), operands.pop()));
                }
                operators.push(cur);
            }
        }

        // finish the remain RPN in the stack
        while (!operators.isEmpty()) {
            operands.push(evaluate(operators.pop(), operands.pop(), operands.pop()));
        }

        return operands.pop();
    }

    private int precedence(Character op) {
        switch (op) {
            case '+' :
            case '-' :
                return 1;
            case '*' :
            case '/' :
                return 2;
            default:
                return -1;
        }
    }

    private int evaluate(Character op, int b, int a) {
        switch (op) {
            case '+' :
                return a + b;
            case '-' :
                return a - b;
            case '*' :
                return a * b;
            case '/' :
                return a / b;
        }
        return -1;
    }
}
