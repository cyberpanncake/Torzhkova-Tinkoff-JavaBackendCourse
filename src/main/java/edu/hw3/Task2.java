package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task2 {
    private static final String WRONG_BRACKET_SEQUENCE_MESSAGE = "Неверная скобочная последовательность";

    public String[] clusterize(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Пустая скобочная последовательность");
        }
        List<String> answer = new ArrayList<>();
        Stack<Character> brackets = new Stack<>();
        int prevI = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                brackets.push(ch);
            } else if (ch == ')') {
                if (!brackets.empty() && brackets.peek() == '(') {
                    brackets.pop();
                    if (brackets.empty()) {
                        answer.add(input.substring(prevI, i + 1));
                        prevI = i + 1;
                    }
                } else {
                    throw new IllegalArgumentException(WRONG_BRACKET_SEQUENCE_MESSAGE);
                }
            } else {
                throw new IllegalArgumentException("Скобочная последовательность может содержать только скобки");
            }
        }
        if (!brackets.empty()) {
            throw new IllegalArgumentException(WRONG_BRACKET_SEQUENCE_MESSAGE);
        }
        return answer.toArray(new String[0]);
    }
}
