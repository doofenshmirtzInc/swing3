package gui;

public class Utils { //this class is used to find the file extension of a file when a user is trying to save the file

	
	public static String getFileExtension(String filename) {
		
		int periodIndex = filename.lastIndexOf(".");
		
		if(periodIndex == -1) {
			return null;
		}
		
		if(periodIndex == filename.length() - 1) {
			return null;
		}
		
		return filename.substring(periodIndex + 1, filename.length());
	}
}
