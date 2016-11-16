public class WordCorrector{
	public static String wordFix(String word){
		String mostLikelyWord="No location found"; 
		int minDistance = 99;		//arbitary large number.
		int tempDistance;
		
		for (String wordtest : wordList){
			tempDistance = levenshteinDistance(word, wordtest);
			if (tempDistance < minDistance){		//compare if the next word matches the input better than the previous word
				minDistance = tempDistance;
				mostLikelyWord = wordtest;			//if yes, the next word is now the most-likely candidate
				}
		}
		return mostLikelyWord;
	}

	//Levenshtein calculates the word distance between the user input, and the actual word. The smaller (up to 0), the closer the word. Lifted shamelessly off the net
	public static int levenshteinDistance(CharSequence lhs, CharSequence rhs){
		int len0 = lhs.length() +1;
		int len1 = rhs.length() +1;

		int[] cost = new int[len0];
		int[] newcost = new int[len0];

		for (int i = 0; i < len0; i++) cost[i]=i;

		for (int j = 1; j < len1; j++) {
			newcost[0]=j;
			for (int i = 1; i<len0; i++){
				int match = (lhs.charAt(i-1)==rhs.charAt(j-1)) ? 0:1;

				int cost_replace = cost[i-1] +match;
				int cost_insert=cost[i]+1;
				int cost_delete = newcost[i-1]+1;

				newcost[i] = Math.min(Math.min(cost_insert, cost_delete),cost_replace);

			}
			int [] swap = cost; cost = newcost; newcost = swap;
		}
		return cost[len0 -1];
	}

	//Change this to represent the real string[] of the real size, and remember to initialise the values!
	static String [] wordList = new String[3];
	
	public static void main (String [] args){
		//automatically run test case
		wordList[0] = "Sentosa";
		wordList[1] = "Chijmes";
		wordList[2] = "East Coast Park";
		System.out.println("Current word array:");
		for (String i : wordList){
				System.out.println(i);
			}

		String word;
		word = "Sentosa";
		System.out.println("Testing " + word + ", got "+wordFix(word));
		word = "sentosa";
		System.out.println("Testing " + word + ", got "+wordFix(word));
		word = "zentoza";
		System.out.println("Testing " + word + ", got "+wordFix(word));
		word = "Fast Boast Wark";
		System.out.println("Testing " + word + ", got "+wordFix(word));
		}
}

