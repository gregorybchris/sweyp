package sweyp.data;

public class Trie {
	private Node head;
	private static final int ALPHA_LENGTH = 26;

	public Trie() {
		head = new Node();
	}

	public boolean isPrefix(String word) {
		Node current = head;
		for(char c : word.toCharArray()) {
			int ci = c - 'A';
			if(current.children[ci] == null)
				return false;
			current = current.children[ci];
		}
		for(int i = 0; i < ALPHA_LENGTH; i++)
			if(current.children[i] != null)
				return true;
		return false;
	}

	public boolean isWord(String word) {
		Node current = head;
		for(char c : word.toCharArray()) {
			int ci = c - 'A';
			if(current.children[ci] == null)
				return false;
			current = current.children[ci];
		}
		if(current.marker == true)
			return true;
		return false;
	}

	public void addWord(String word) {
		Node current = head;
		word = word.replaceAll("[^A-Za-z0-9]", "");
		for(char c : word.toCharArray()) {
			int ci = (int)(c) - 'A';
			if(current.children[ci] == null)
				current.children[ci] = new Node();
			current = current.children[ci];
		}
		current.marker = true;
	}

	private class Node {
		public Node() {}
		public Node[] children = new Node[26];
		public boolean marker;
	}
}
