/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		for(int i = -1; i <= s.length(); ++i){
			for(String insertion : getInsertionsWithCharacterAt(s, i, wordsOnly)){
				if(!currentList.contains(insertion)){
					currentList.add(insertion);
				}
			}
		}
	}
	
	private List<String> getInsertionsWithCharacterAt(String s, int k, boolean wordsOnly) {
		Set<String> insertions = new HashSet<>();

		char[] chars = s.toCharArray();
		int length = s.length();
		char[] result = new char[length + 1];

		if (k == length) {
			
			for (int i = 0; i < length; ++i) {
				result[i] = chars[i];
			}
			
			for (char charCode = 'a'; charCode <= 'z'; ++charCode) {
				result[length] = charCode;
				addIfWordsOnly(insertions, new String(result), wordsOnly);
			}
			
		} else if (k == -1) {
			
			for (int i = 1; i < length + 1; ++i) {
				result[i] = chars[i - 1];
			}
			
			for (char charCode = 'a'; charCode <= 'z'; ++charCode) {
				result[0] = charCode;
				addIfWordsOnly(insertions, new String(result), wordsOnly);
			}
			
		} else {
			
			for(int i = 0;  i < length+1; ++i){
				if(i < k){
					result[i] = chars[i];
				}else if (i > k){
					result[i] = chars[i-1];
				}
			}
			
			for (char charCode = 'a'; charCode <= 'z'; ++charCode) {
				result[k] = charCode;
				addIfWordsOnly(insertions, new String(result), wordsOnly);
			}
			
		}

		return new ArrayList<String>(insertions);

	}
	
	private void addIfWordsOnly(Collection<String> words, String word, boolean wordsOnly) {
		if (wordsOnly) {
			if (dict.isWord(word)) {
				words.add(word);
			}
		} else {
			words.add(word);
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		Set<String> deletions = new HashSet<>();

		for (int i = 0; i < s.length(); ++i) {
			String deletion = getStringWithoutCharAt(s, i);
			addIfWordsOnly(deletions, deletion, wordsOnly);
		}

		currentList.addAll(deletions);
	}

	private String getStringWithoutCharAt(String s, int k) {
		if ("".equals(s)) {
			return s;
		}

		StringBuilder result = new StringBuilder(s.length() - 1);
		int i = 0;

		for (Character c : s.toCharArray()) {
			if (i++ != k) {
				result.append(c);
			}
		}

		return result.toString();
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		Queue<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same  
														   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		 
		
		// insert first node
		queue.add(word);
		visited.add(word);
					
		// TODO: Implement the remainder of this method, see assignment for algorithm
		while (!queue.isEmpty() && numSuggestions>0) {
			for(String n:  distanceOne(queue.poll(), false)){
				if(!visited.contains(n)){
					visited.add(n);
					queue.offer(n);
					if(dict.isWord(n)){
						retList.add(n);
						--numSuggestions;
					}
				}
				
				if(visited.size() == THRESHOLD && retList.size() > 0){
					break;
				}
				
			}
			
		}
		
		return retList;

	}	

   public static void main(String[] args) {
	   /* basic testing code to get started
	   String word = "i";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
	   */
   }

}
