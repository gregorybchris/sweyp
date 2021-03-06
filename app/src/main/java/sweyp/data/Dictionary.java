package sweyp.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import sweyp.constants.UC;

public class Dictionary {
	private Trie dictionary;

	public Dictionary() {
		dictionary = new Trie();
		fillDictionary();
	}

	private void fillDictionary() {
		try {
			/* When running in command line */
			//	BufferedReader reader = new BufferedReader(new FileReader(
			//	System.getProperty("user.dir") + UC.DICTIONARY_FILE_NAME));

			/* When creating a JAR */
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					Dictionary.class.getResourceAsStream(UC.DICTIONARY_FILE_NAME)));
			String word;
			while((word = reader.readLine()) != null)
				dictionary.addWord(word.toUpperCase());
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isWord(String word) {
		return dictionary.isWord(word);
	}

	public boolean isPrefix(String word) {
		return dictionary.isPrefix(word);
	}
}
