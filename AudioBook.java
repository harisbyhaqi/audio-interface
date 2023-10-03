
import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent {
	public static final String TYPENAME = "AUDIOBOOK";

	private String author;
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
			String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters) {
		super(title, year, id, type, audioFile, length); // Using super method to inherit
		this.author = author; // initializing author
		this.narrator = narrator; // initializing narrator
		this.chapterTitles = chapterTitles; // initializing chapter titles
		this.chapters = chapters; // initializing chapters
	}

	public String getType() {
		return TYPENAME;
	}

	public void printInfo() {
		super.printInfo(); // using super method to inherit the method and print the information in the
							// superclass
		System.out.println("Author: " + getAuthor() + " Narrated by: " + getNarrator()); // Print the author and
																							// narrator
	}

	public void play() {
		setAudioFile(chapterTitles.get(currentChapter) + "\n" + chapters.get(currentChapter)); // setting the audiofile
																								// to current chapter
																								// from the
																								// chapterTitles list
																								// then from the
																								// chapters list
		super.play(); // Inheriting method and using it to play
	}

	public void printTOC() {
		for (int i = 0; i < chapterTitles.size(); i++) { // loop through chapter titles
			System.out.println("Chapter " + (i + 1) + ". " + chapterTitles.get(i)); // Print out chapter titles
			System.out.println(); // extra line for printing
		}
	}

	public void selectChapter(int chapter) {
		if (chapter >= 1 && chapter <= chapters.size()) {
			currentChapter = chapter - 1;
		}
	}

	public boolean equals(Object other) {
		AudioBook otherBook = (AudioBook) other; // Creating new instance of AudioBook using the parameter 'other' then
													// casting it to Audiobook
		return super.equals(otherBook) && getAuthor().equals(otherBook.getAuthor()) // First checking if the condition
																					// in the super class are met using
																					// the super method then checking if
																					// the author and narrator are equal
				&& getNarrator().equals(otherBook.getNarrator());
	}

	public int getNumberOfChapters() {
		return chapters.size();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNarrator() {
		return narrator;
	}

	public void setNarrator(String narrator) {
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles() {
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles) {
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters() {
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters) {
		this.chapters = chapters;
	}

}
