public class UseLewisList {
    public static void main(String[] args) {
        LewisList list = new LewisList();
        list.append(7);
        list.append(5);
        list.append(13);
        list.append(9);
        try {
        		System.out.println("The value at location 8 is " + list.get(8)); // if this is invalid
        } catch (Exception ex) { // then we will catch it with the exception
        		System.out.println("That is not a valid position.");
        }
        System.out.println("Here are all the values: ");
        System.out.println(list); // calls toString
    }
}