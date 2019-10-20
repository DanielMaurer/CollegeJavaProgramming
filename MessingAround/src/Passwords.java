class Solution {
    private int currentCount; // this variable will keep track of the length of the substring
    private int charVal; // this variable will hold the ascii representation of the character for comparison
    private int capitalCount; // this variable will keep track of the amount of capital letters in a substring
    private boolean valid; // this variable will determine if the count shoud be iterated. Will be false if a restriction is met
    private int maxSub; // this variable will keep track of the longest substring length
    
    
    
    public int solution(String S) {
        currentCount = 0;
        capitalCount = 0;
        valid = false;
        maxSub = 0;
        for(char c : S.toCharArray()){ // for each character in the string
            charVal = (int) c; // convert the character to its ASCII code
            if(charVal >= 65 && charVal <= 90){ // if the character is upper case
                capitalCount += 1;
                if(capitalCount > 0 && capitalCount <= 1){ // if there is only one capital letter
                    currentCount += 1;
                    valid = true; // then the password is valid
                }else{ // if there is more than one capital letter
                    if(currentCount > maxSub){ // check to see if the substring is the longest
                        maxSub = currentCount;
                    }
                    // then reset the values
                    currentCount = 0;
                    capitalCount = 0;
                }
            } else if(charVal >= 97 && charVal <= 122){ // if the character is lower case
                currentCount += 1;
            } else{ // if the string has a number or anything else
                if(currentCount > maxSub){
                    maxSub = currentCount;
                }
                currentCount = 0;
                capitalCount = 0;
            }
        }
       
        if(valid == false){ // if there are no capital letters
            maxSub = -1;
        } else {
        		if(currentCount > maxSub) {
        			maxSub = currentCount;
        		}
        }
        return maxSub;
    }
}
public class Passwords{
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution("a0bb"));
	}
}
