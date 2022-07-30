import java.util.*;

public class Trie2 {
	
	private class TrieNode {
		Map<Character, TrieNode> children = new TreeMap<>();//TreeMap is java build-in structure, 
		boolean aword = false;		//Basically it acts like a Hashtable or Hashmap, establishing a mapping between Key and Value
		                                //Unlike hash table, keys in TreeMap are sorted!
	}
	
	private TrieNode root;
	public Trie2() {
		this.root = new TrieNode();
	}

	public void insertString(String s) {
		insertString(root, s);
	}
	
	private void insertString(TrieNode root, String s) {
		TrieNode cur = root;
		for (char ch : s.toCharArray()) {
			TrieNode next = cur.children.get(ch);
			if (next == null)
				cur.children.put(ch, next = new TrieNode());
			cur = next;
		}
		cur.aword = true;
	}
	
	public void printSorted() {
		printSorted(root, "");
	}

	private void printSorted(TrieNode node, String s) {
		if (node.aword) {
			System.out.println(s);
		}
		for (Character ch : node.children.keySet()) {
			printSorted(node.children.get(ch), s + ch);
		}
	}
	
	public boolean findWord(String s) {
		return findWord(root, s);
	}
	
	private boolean findWord(TrieNode node, String s) {
		//Simplified function, in terms of findSubtree()
		//If the subtree for the prefix "s" exists, then the string is contained.
		return findSubtree(s) != null;
	}

	private TrieNode findSubtree(String prefix) {
		if(prefix == null) return this.root;

		TrieNode cur = this.root;

		//For each character in the string, follow cur one level lower.
		for(char c : prefix.toCharArray()) {
			//Break if the current node is not found.
			if(cur == null) break;

			//Step to next level down
			cur = cur.children.get(c);
		}

		return cur;
	}

	public LinkedList<String> wordsPrefixedBy(String prefix) {
		return this.wordsPrefixedBy(this.root, prefix);
	}
	private LinkedList<String> wordsPrefixedBy(TrieNode node, String prefix) {
		LinkedList<String> strings = new LinkedList<>();

		//Find the node which all words with this prefix are children of
		TrieNode prefixTree = findSubtree(prefix);

		//Collect all of the words
		collectSubtree(prefix, prefixTree, strings);

		return strings;
	}
	void collectSubtree(String prefixText, TrieNode tree, Collection<String> collection) {
		if(tree == null) return;

		//If current node is a word, add it to the list.
		if(tree.aword) collection.add(prefixText);

		//Recursively search all the children, depth-first.
		tree.children.forEach(
			(character, subtree) -> collectSubtree(prefixText + character, subtree, collection)
		);
	}

	// Usage example
	public static void main(String[] args) {
		
		Trie2 tr = new Trie2();
		
		tr.insertString("hello");
		tr.insertString("world");
		tr.insertString("hi");
		tr.insertString("ant");
		tr.insertString("an");
		
		System.out.println(tr.findWord("ant"));
		System.out.println(tr.findWord("an"));
		System.out.println(tr.findWord("hello"));
		System.out.println(tr.findWord("cant"));
		System.out.println(tr.findWord("hig"));
		System.out.println(tr.findWord("he"));

		tr.printSorted();
	}
}
