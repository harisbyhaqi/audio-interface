
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist {
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture

	public Playlist(String title) {
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addContent(AudioContent content) {
		contents.add(content);
	}

	public ArrayList<AudioContent> getContent() {
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents) {
		this.contents = contents;
	}

	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list.
	 */
	public void printContents() {
		for (int i = 1; i <= contents.size(); i++) { // going through the contents, to print out all the Contents
			System.out.print(i + ". "); // print value of i along with .
			contents.get(i - 1).printInfo(); // i-1 because started i at 1
			System.out.println(); // new line for printing
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll() {
		for (int i = 0; i < contents.size(); i++) { // go through contents
			contents.get(i).play(); // play the content at i
			System.out.println(); // new line
		}
	}

	// Play the specific AudioContent from the contents array list.
	public void play(int index) {
		if (index >= 1 && index <= contents.size()) { // if the index is greater than or equal to 1 and less than or
														// equal to content size then do the following
			contents.get(index - 1).play(); // index - 1 because the arraylists are 0 indexed then use play method
			System.out.println(); // new line
		}
	}

	public boolean contains(int index) {
		return index >= 1 && index <= contents.size(); // checking if index is valid
	}

	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other) {
		Playlist otherPlaylist = (Playlist) other; // create new instance of playlist and casting the parameter other to
													// Playlist
		return getTitle().equals(otherPlaylist.getTitle()); // return true if the title of the two playlists are the
															// same
	}

	public void deleteContent(int index) {
		if (!contains(index))
			return;
		contents.remove(index - 1);
	}

}
