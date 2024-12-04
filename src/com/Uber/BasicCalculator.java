import java.util.Stack;

class Solution {
    public int calculate(String s) {
        return calculateHelper(s, new int[]{0});
    }
    
    private int calculateHelper(String s, int[] index) {
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char previousOperator = '+';
        
        while (index[0] < s.length()) {
            char currentChar = s.charAt(index[0]);
            if (currentChar == '(') {
                index[0]++; // Skip '('
                currentNumber = calculateHelper(s, index); //recurse
            }
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || index[0] == s.length() - 1) {
                if (previousOperator == '-') {
                    stack.push(-currentNumber);
                } else if (previousOperator == '+') {
                    stack.push(currentNumber);
                } else if (previousOperator == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (previousOperator == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                previousOperator = currentChar;
                currentNumber = 0;
                if (currentChar == ')') {
                    break; 
                }
            }
            index[0]++; //skip ')'
        }
        
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
// ~= Basic Calculator and same sol as above should work for Basic Calculator 3
