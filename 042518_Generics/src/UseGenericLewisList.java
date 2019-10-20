public class UseGenericLewisList {
    public static void main(String[] args) {
        GenericLewisList<Integer> list = new GenericLewisList<Integer>();
        list.append(7);
        list.append(5);
        list.append(13);
        list.append(9);
        try {
            System.out.println("The value at location 1 is " + list.get(1));
        } catch (Exception ex) {
            System.out.println("That is not a valid position.");
        }
        System.out.println("Here are all the values: ");
        System.out.println(list); // calls toString
        System.out.println("Now deal with words.");
        GenericLewisList<String> words = new GenericLewisList<String>();
        words.append("fig");
        words.append("apple");
        words.append("carrot");
        words.append("eggplant");
        System.out.println(words); // calls toString
    }
}