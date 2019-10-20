import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
 
public class UsingStacksAndQueues {
    public static Queue<Character> que = null;
    public static Stack<Character> stk = null;
    public static void loadStackAndQueue(String str) {
        que = new LinkedList<Character>();
        stk = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            que.add(str.charAt(i));
            stk.push(str.charAt(i));
        }
    }
    public static boolean isPalindrome() {
        char ch1, ch2;
        while (!que.isEmpty() && !stk.isEmpty()) {
            ch1 = que.remove();
            ch2 = stk.pop();
            if (ch1 != ch2) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String doAgain;
        String str;
        do {
            System.out.print("Enter string: ");
            str = sc.nextLine();
            loadStackAndQueue(str);
            if (isPalindrome()) {
                System.out.println("It's a palindrome.");
            } else {
                System.out.println("It is not a palindrome.");
            }
            System.out.print("Again? ");
            doAgain = sc.nextLine();
        } while (doAgain.equalsIgnoreCase("y"));
    }
}