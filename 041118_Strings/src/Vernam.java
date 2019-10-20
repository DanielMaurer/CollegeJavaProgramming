// Computes the vernam cipher

import java.util.Scanner;

public class Vernam {
	public static String vernam(String plain, String key) {
		String result = "";
		char plainChar, keyChar, encChar;
		int code;
		for (int i = 0; i < plain.length(); i++) {
			// combine the key and plain characters at i using xor
			// then translate to a character that I will return
			plainChar = plain.charAt(i);
			keyChar = key.charAt(i);
			code = (int)plainChar ^ (int)keyChar;
			encChar = (char)code;
			result = result + encChar;
		}
		return result;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String plain, key, encrypted;
		System.out.print("Enter the plain text: ");
		plain = sc.nextLine();
		System.out.print("Enter a key that is just as long: ");
		key = sc.nextLine();
		if (plain.length() == key.length()) {
			encrypted = vernam(plain,key);
			System.out.println("Encrypted = " +encrypted);
		} else {
			System.out.print("Follow the instructions.");
		}
	}
}
