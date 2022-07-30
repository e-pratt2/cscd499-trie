import java.util.LinkedList;

public class Tester {
    public static void main(String[] args) {
        Trie2 myTrie = new Trie2();

        //List of strings to test with
        String[] words = new String[]{
                "apple", "bike", "bake", "pen", "did", "ape", "child", "cat", "file", "hello", "he", "hell"
        };

        //Add each string
        for(String s : words){
            myTrie.insertString(s);
        }

        //Test our two prefixes
        printList(myTrie.wordsPrefixedBy("ap"));
        printList(myTrie.wordsPrefixedBy("he"));

    }
    //Simple helper to print out a full list.
    public static void printList(LinkedList<?> list) {
        System.out.print("LinkedList{");
        for(Object o : list) {
            System.out.print(o);
            System.out.print(", ");
        }
        System.out.println("}");
    }
}
