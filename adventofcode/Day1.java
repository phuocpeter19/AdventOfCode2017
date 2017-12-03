/* --- PART ONE ---
 * The captcha requires you to review a sequence of digits (your puzzle input)
 * and find the sum of all digits that match the next digit in the list. 
 * The list is circular, so the digit after the last digit is the first digit in 
 * the list.
 * For example:
 *  - 1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the 
 *      second digit and the third digit (2) matches the fourth digit.
 *  - 1111 produces 4 because each digit (all 1) matches the next.
 *  - 1234 produces 0 because no digit matches the next.
 *  - 91212129 produces 9 because the only digit that matches the next one is
 *      the last digit, 9.
 * Now, instead of considering the next digit, it wants you to consider the 
 * digit halfway around the circular list. That is, if your list contains 10 
 * items, only include a digit in your sum if the digit 10/2 = 5 steps forward 
 * matches it. Fortunately, your list has an even number of elements.

For example:

1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.
1221 produces 0, because every comparison is between a 1 and a 2.
123425 produces 4, because both 2s match each other, but no other digit has a match.
123123 produces 12.
12131415 produces 4.
*/
package adventofcode;

import java.util.ArrayList;

/**
 *
 * @author phuoc
 */
public class Day1 {
    
    public Day1(String input) {
        ArrayList<String> strings = partitionInput(input);
        int count = 0;
        for (int i = 0; i < strings.size(); i++) {
            if (i == 0) {
                count += count(strings.get(i));
            } else {
                count += count(strings.get(i), strings.get(i - 1));
            }
        }
        if (strings.get(0).charAt(0) == strings.get(strings.size() - 1).charAt(strings.get(strings.size() - 1).length() - 1)) {
            count += Integer.parseInt(strings.get(0).substring(0, 1));
        }
        System.out.println("Result: " + count);
    }
    
    private ArrayList<String> partitionInput(String input) {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < input.length(); i += 9) {
            if (input.length() - i <= 9) {
                temp.add(input.substring(i));
            }
            if (i + 9 < input.length()) {
                temp.add(input.substring(i, i + 9));
            }
        }
        System.out.printf("String authenticity: %b\n", checkAuthenticity(input, temp));
        System.out.printf("InLen: %d\nArrLen: %d\n", input.length(), temp.size());
        return temp;
    }
    
    private boolean checkAuthenticity(String string, ArrayList<String> temp) {
        StringBuilder sb = new StringBuilder();
        for (String s: temp) {
            sb.append(s);
        }
        return sb.toString().equals(string);
    }
    
    private int count(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (i + 1 >= string.length()) { break; }
            if (string.charAt(i) == string.charAt(i + 1)) {
                count += Integer.parseInt(string.substring(i, i + 1));
            }
        }
        System.out.printf("String: %s, Count: %d\n", string, count);
        return count;
    }
    
    private int count(String string, String prevString) {
        int count = count(string);
        if (prevString.charAt(prevString.length() - 1) == string.charAt(0)) {
            count += Integer.parseInt(string.substring(0, 1));
        }
        return count;
    }
    
}