import java.util.Scanner;

public class SortingWords {
    static Scanner scan = new Scanner(System.in);
	static int cellArray = 0, freeCellUniqueArray = 0;
	static int[] indexArray = new int[64];

    public static void main(String[] args) {
		
		String inputString = "";
		int i = 0;
		
		// Input data is accepted here(data is received from the command line)
		if(args.length != 0){
			for (i = 0; i < args.length - 1; i++){
				inputString = inputString + args[i] + " ";
			}
			inputString = inputString + args[args.length - 1];
		}
		else{
			inputString = "the quick brown fox jumps over the lazy dog";
		}
		
		//Method that starts the operation with the received data
		start(inputString);
	}
	
	public static void start(String inputString){
		
		/* The allocation of the necessary memory 
			and the necessary Declaration of arrays */
											
		String[] wordsArray = new String[64];
		String[] sortingArray = new String[64];
		String[] uniqueArray = new String[64];
		
		//Method that divides the resulting string into words
		splittingTextIntoWords(inputString, wordsArray);
		//Method that sorts words alphabetically
		sortingWords(wordsArray, sortingArray);
		//A method that searches for unique words in a sorted array
		searchForUniqueWords(sortingArray, uniqueArray);
		//Method that counts the number of identical words
		countingIdenticalWords(indexArray, uniqueArray, sortingArray);
		//The method sorts the words according to the number
		sortingByQuantity(indexArray, uniqueArray);
		//Output the result to the command line
		outputWords(uniqueArray, indexArray);
	}
	
	public static String checkingEmptyInput(String inputString){
		
		if ( inputString.isEmpty() == true){
			inputString = "the quick brown fox jumps over the lazy dog";
		}
		return inputString;
	}

	public static String[] splittingTextIntoWords(String inputString, String[] wordsArray){
			
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String finalString = "";
		int i = 0, j = 0;
			
		for ( i = 0; i < inputString.length(); i++ ){
			
			if( inputString.charAt(i) ==  ' '){
				if (inputString.charAt(i + 1) ==  ' '){
					break;
				}
				else {
					cellArray++;
					finalString = "";
				}
			} else{
				for ( j = 0; j < alphabet.length(); j++ ){
					if( inputString.charAt(i) == alphabet.charAt(j)){
						finalString += String.valueOf(alphabet.charAt(j));
					}
				}
				wordsArray[cellArray] = finalString;
			}
		}
		
		
		
		return wordsArray;
	}

	public static String[] sortingWords(String[] wordsArray, String[] sortingArray){
		
		String forSorting = "01234567890AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
		int i = 0, j = 0, m = 0, count = 0;

		int counterWords = cellArray + 1;
		int cellSortingArray = 0;
		
		// Stage of sorting by the first letters of the word
		for ( i = 0; i < forSorting.length(); i++ ){

			for ( j = 0; j < counterWords; j++ ){
				if( wordsArray[j].charAt(0) == forSorting.charAt(i)){
					count = 0;
					
					for ( m = 0; m < sortingArray.length; m++ ){
						if(wordsArray[j] == sortingArray[m] ){
							count++;
						}
					}
					if ( count == 0 ){
						sortingArray[cellSortingArray] = wordsArray[j];
						cellSortingArray++;
					}
				}
			}
        }
		
		String saveWord = "";
		int indexFirstWord = 0, indexSecondWord = 0;
		int changeCounter = 0;
		
		/*Stage of sorting by remaining letters 
			with the condition that the first element is larger than the second*/
			
		for ( j = 0; j < ( cellArray - 1 ); j++ ){
				
			if(sortingArray[j].charAt(0) == sortingArray[j + 1].charAt(0)){
					
				if( sortingArray[j].length() > sortingArray[j + 1].length() ){

					for ( i = 0; i < (sortingArray[j + 1].length() - 1); i++){
							
						if (sortingArray[j + 1].charAt(i) != sortingArray[j].charAt(i)){
							for ( m = 0; m < forSorting.length(); m++ ){
								if (forSorting.charAt(m) == sortingArray[j].charAt(i)){
									indexFirstWord = m;
									break;
								}
							}
							for ( m = 0; m < forSorting.length(); m++ ){
								if (forSorting.charAt(m) == sortingArray[j + 1].charAt(i)){
									indexSecondWord = m;
									break;
								}
							}
								
							changeCounter = 0;
								
							if (indexFirstWord > indexSecondWord){
								saveWord = sortingArray[j];
								sortingArray[j] = sortingArray[j + 1];
								sortingArray[j + 1] = saveWord;
									 
								changeCounter++;
								break;
							}
							else{
								break;
							}
						}
					}
						
				}
				
				/*
				Stage of sorting by remaining letters
					with the condition that the second element is larger than the first
				*/
				
				else if( sortingArray[j].length() < sortingArray[j + 1].length() ){
						
					for ( i = 0; i < sortingArray[j].length(); i++){
							
						if (sortingArray[j + 1].charAt(i) != sortingArray[j].charAt(i)){
							for ( m = 0; m < forSorting.length(); m++ ){
								if (forSorting.charAt(m) == sortingArray[j].charAt(i)){
									indexFirstWord = m;
									break;
								}
							}
							for ( m = 0; m < forSorting.length(); m++ ){
								if (forSorting.charAt(m) == sortingArray[j + 1].charAt(i)){
									indexSecondWord = m;
									break;
								}
							}
								
							changeCounter = 0;
								
							if (indexFirstWord > indexSecondWord){
								saveWord = sortingArray[j + 1];
								 sortingArray[j + 1] = sortingArray[j];
								 sortingArray[j] = saveWord;
								 changeCounter++;
									break;
							}
							else{
								break;
							}
						}
					}
				}
				
				/*
				Stage of sorting by the remaining letters
					with the condition that the sorting elements are equal
				*/
				else if( sortingArray[j].length() == sortingArray[j + 1].length() ){
						
					for ( i = 0; i < sortingArray[j].length(); i++){
							
						if (sortingArray[j + 1].charAt(i) != sortingArray[j].charAt(i)){
							for ( m = 0; m < forSorting.length(); m++ ){
								if (forSorting.charAt(m) == sortingArray[j].charAt(i)){
									indexFirstWord = m;
									break;
								}
							}
							for ( m = 0; m < forSorting.length(); m++ ){
								if (forSorting.charAt(m) == sortingArray[j + 1].charAt(i)){
									indexSecondWord = m;
									break;
								}
							}
								
							changeCounter = 0;
							
							if (indexFirstWord > indexSecondWord){
								saveWord = sortingArray[j];
								 sortingArray[j] = sortingArray[j + 1];
								 sortingArray[j + 1] = saveWord;
								 changeCounter++;
							}
						}
					}
				}
			}
		}
		wordsArray = null;
		return sortingArray;
	}
	
	public static String[] searchForUniqueWords(String[] sortingArray, String[] uniqueArray){
		int i = 0, j = 0;
		int similarity = 0;
		
		for (i = 0; i < cellArray + 1; i++){
			
			similarity = 0;
			for (j=0; j < freeCellUniqueArray; j++){
				
				if ( sortingArray[i].equals(uniqueArray[j]) == true){
					similarity++;
				}
			}
			if (similarity == 0){
					uniqueArray[freeCellUniqueArray] = sortingArray[i];
					freeCellUniqueArray++;
				}
		}
		return uniqueArray;
	}
	
	public static int[] countingIdenticalWords(int[] indexArray, String[] uniqueArray, String[] sortingArray){
		
		int i = 0, j = 0;
		int similarity = 0;
		
		for (i = 0; i < freeCellUniqueArray; i++){
			for (j = 0; j < sortingArray.length; j++){
				if(uniqueArray[i].equals(sortingArray[j]) == true){
					indexArray[i]++;
				}
			}
		}
		return indexArray;
	}
	
	public static String[] sortingByQuantity (int[] indexArray, String[] uniqueArray){
		
		int quantityofWords = 1, i = 0, saveIndex = 0;
		String saveWord = "";
		boolean flagOff = false;
		
		while(!flagOff){
			
			flagOff  = true;
			
			for ( i = 0; i < (indexArray.length - 1); i++){
				
				if ( indexArray[i] < indexArray[i + 1] ){
					
					saveIndex = indexArray[i];
					saveWord = uniqueArray[i];
					
					indexArray[i] = indexArray[i + 1];
					uniqueArray[i] = uniqueArray[i + 1];
					indexArray[i + 1] = saveIndex;
					uniqueArray[i + 1] = saveWord;
					flagOff = false;
				}
			}
		}
		return uniqueArray;
	}
	
	public static void outputWords(String[] inputArray, int[] indexArray){
		int j = 0;
		
		System.out.println("\n The result: \n" );
		for ( j = 0; j < freeCellUniqueArray; j++){
				System.out.println( " " + inputArray[j] + " - " + indexArray[j]);
		}
		
		System.out.println("\n End programm!" );
	}
}