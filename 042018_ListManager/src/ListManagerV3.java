// 04-20-18
// Create a full suite of functions for working with data
 
import java.util.Random;
import java.util.Scanner;
public class ListManagerV3 {
    private static int capacity;
    private static int[] numbers;
    private static int count;
     
    // this function returns true if the list is full
    // and false if it isn't
    public static boolean isFull() {
        return (count == capacity);
    }
     
    // this function returns true if the list is empty
    // and false if it isn't
    public static boolean isEmpty() {
        return (count == 0);
    }
     
    // this resize() function doubles the capacity of
    // the list.
    public static void resize() {
        capacity = 2 * capacity;
        int[] newNumbers = new int[capacity];
        for (int i = 0; i < count; i++) {
            newNumbers[i] = numbers[i];
        }
        numbers = newNumbers;
    }
     
    // this function inserts val at location pos
    public static void insert(int val, int pos) {
        if (isFull()) {
            resize();
        }
        if (pos >= count) {
            append(val);
        } else {
            for (int i = count; i > pos; i--) {
                numbers[i] = numbers[i-1];
            }
            numbers[pos] = val;
            count = count + 1;
        }
    }
     
    // this function adds a value to the end of the list
    // if the list is full, it resizes it. Specifically,
    // it doubles its capacity before appending the new value.
    public static void append(int num) {
        if (isFull()) {
            resize();
        }
        numbers[count] = num;
        count = count + 1;
    }
    public static void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(numbers[i]);
        }
    }
    // this function removes the last number
    public static void removeLast() {
        if (!isEmpty()) {
            count = count - 1;
        }
    }
    // this function removes all the numbers
    public static void clear() {
        count = 0;
    }
    //precondition: the pos value is positive or zero
    public static void removeAt(int pos) {
        if (pos < count) {
            for (int i = pos; i < count-1; i++) {
                numbers[i] = numbers[i+1];
            }
            count = count - 1;
        }
    }
     
    // this function finds and returns the maximum value
    // in the list. precondition: the list is not empty.
    public static int findMax() {
        int max = numbers[0];
        for (int i = 1; i < count; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
     
    // this function finds and returns the minimum value
    // in the list. precondition: the list is not empty.
    public static int findMin() {
        int min = numbers[0];
        for (int i = 1; i < count; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;     
    }
     
    // this function finds the target in the list
    // it returns -1 if not found; otherwise, it returns the position
    // where target was found.
    public static int linearSearch(int target) {
        for (int i = 0; i < count; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return -1;  // did not find it
    }
     
    // this function finds the range of the values in a list.
    // precondition: the list is not empty
    public static int findRange() {
        return findMax() - findMin();
    }
    // this function tries to remove the first instance of
    // target. If target isn't in the list, this returns false.
    // Otherwise, it returns true.
    public static boolean remove(int target) {
        int pos = linearSearch(target);
        if (pos >= 0) {
            removeAt(pos);
            return true;
        } else {
            return false;
        }
    }
    // this function removes all occurrences of the target value 
    // returns the number of instances of target that were removed
    public static int removeAll(int target) {
        int result = 0;
        while (remove(target)) {
            result = result + 1;
        }
        return result;
    }
    // this sorts the list using O(n^2) algorithm
    public static void selectionSort() {
        int min, minPos;
        int temp;
        for (int i = 0; i < count; i++) {
            min = numbers[i];
            minPos = i;
            for (int j = i+1; j < count; j++) {
                if (numbers[j] < min) {
                    min = numbers[j];
                    minPos = j;
                }
            }
            temp = numbers[i];
            numbers[i] = min;
            numbers[minPos] = temp;
        }
    }
    public static void main(String[] args) {
        // initialize
        Scanner sc = new Scanner(System.in);
        capacity = 5;
        numbers = new int[capacity];
        count = 0;
        // add values to the numbers array
        append(7);
        append(13);
        append(5);
        append(4);
        append(13);
        append(9);
        append(17);   // this will cause a problem cuz I have only 5 slots
        print();
        System.out.println("Count is " + count);
        System.out.println("Capacity is " + capacity);
        System.out.println("Now going to insert a 17 at location 1");
        System.out.println("and a 24 at location 4.");
        insert(17,1);
        insert(24,4);
        insert(96,12);
        print();
        System.out.println("The range of these values is " + findRange());
        int searchFor;
        int foundAt;
        System.out.print("What do you want to search for? ");
        searchFor = sc.nextInt();
        foundAt = linearSearch(searchFor);
        if (foundAt >= 0) {
            System.out.println("Found at " + foundAt);
        } else {
            System.out.println("Not found.");
        }
        int numToRemove;
        System.out.print("What value should I remove? ");
        numToRemove = sc.nextInt();
        if (remove(numToRemove)) {
            System.out.println("The value was removed. Here is the list.");
            print();
        } else {
            System.out.println("The value wasn't there.");
        }
        System.out.println("Let's get rid of bad luck ...");
        int numRemoved = removeAll(13);
        if (numRemoved == 0) {
            System.out.println("Nothing removed.");
        } else {
            System.out.println("Got rid of " + numRemoved + " values.");
            print();
        }
        System.out.println("Now will sort ...");
        selectionSort();
        print();
    }
}