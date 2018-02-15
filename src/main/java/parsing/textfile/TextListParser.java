package parsing.textfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextListParser {
	
	public static String readTextFromFile(String filepath) throws IOException{
		return new String(Files.readAllBytes(Paths.get(filepath)));
	}
	
	public static List<String> getListDividedBy(String source, String lineStartRegex,
			String lineEndSymbol)
	{
		String regex = lineStartRegex + "[^" + lineEndSymbol + "]+";
		final Matcher matcher = Pattern.compile(regex).matcher(source);

	    final List<String> matches = new ArrayList<>();
	    while (matcher.find()) {
	       ///// System.out.println(matcher.group());
	        matches.add(matcher.group(0));
	    }
		return matches;
	}
	
	public static void main(String[] args) throws IOException
	{
		String filename = "\\src\\main\\resources\\multiLvlList.txt";
		String filepath = System.getProperty("user.dir") + filename;
		String textSource = readTextFromFile(filepath);
		
		String arabicNumbInBracketsRegex = "\\(\\d+\\)";
		String romanNumbInBracketsRegex = "\\([iv]+\\)";
		String lettersInBracketsRegex = "\\([a-h]\\)";
		String lineEndSymbol = "(";

		List<String> listByArabicNumbers = getListDividedBy(textSource, 
				arabicNumbInBracketsRegex, lineEndSymbol);
		System.out.println("List of lines started by arabic numbers:\n");
		listByArabicNumbers.forEach(System.out::println);
		
		List<String> listByRomanNumbers = getListDividedBy(textSource, 
				romanNumbInBracketsRegex, lineEndSymbol);
		System.out.println("\nList of lines started by roman numbers:\n");
		listByRomanNumbers.forEach(System.out::println);
		
		List<String> listByLetters = getListDividedBy(textSource, 
				lettersInBracketsRegex, lineEndSymbol);
		System.out.println("\nList of lines started by letters from a to h:\n");
		listByLetters.forEach(System.out::println);
	}

}
