
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library {
	private ArrayList<Song> songs;
	private ArrayList<AudioBook> audiobooks;
	private ArrayList<Playlist> playlists;

	String errorMsg = "";

	public String getErrorMessage() {
		return errorMsg;
	}

	public Library() {
		songs = new ArrayList<Song>();
		audiobooks = new ArrayList<AudioBook>();
		playlists = new ArrayList<Playlist>();
	}

	public void download(AudioContent content) {

		if (content.getType().equals(Song.TYPENAME)) { // checking for type
			Song songContent = (Song) content; // casting to song
			if (songs.contains(songContent)) { // if songs list contains it already
				errorMsg = "SONG " + songContent.getTitle() + " already downloaded";
				throw new AudioContentAlreadyDownloadedException(errorMsg);
			} else {
				songs.add(songContent); // else you add it to list
				System.out.println("SONG " + songContent.getTitle() + " added to library");
			}
		} else if (content.getType().equals(AudioBook.TYPENAME)) { // check type
			AudioBook bookContent = (AudioBook) content; // cast
			if (audiobooks.contains(bookContent)) { // check if already downloaded
				errorMsg = "AUDIOBOOK " + bookContent.getTitle() + "already downloaded";
				throw new AudioContentAlreadyDownloadedException(errorMsg);
			} else {
				audiobooks.add(bookContent); // else add to audibooks list
				System.out.println("AUDIOBOOK " + bookContent.getTitle() + " added to library");
			}
		}
	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() {
		for (int i = 0; i < songs.size(); i++) { // going through songs list and then printing with number value
			int index = i + 1;
			System.out.print(index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() {
		for (int i = 0; i < audiobooks.size(); i++) { // going through audiobooks list and printing with number value
			int index = i + 1;
			System.out.print(index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts() {

	}

	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists() {
		for (int i = 0; i < playlists.size(); i++) { // going through playlists and printing them with number value
			System.out.println(i + 1 + ". " + playlists.get(i).getTitle());

		}
	}

	// Print the name of all artists.
	public void listAllArtists() {
		// First create a new (empty) array list of string
		// Go through the songs array list and add the artist name to the new arraylist
		// only if it is
		// not already there. Once the artist array list is complete, print the artists
		// names
		ArrayList<String> listArtist = new ArrayList<>(); // creating a list to store artists
		for (int i = 0; i < songs.size(); i++) { // going through artists
			if (!listArtist.contains(songs.get(i).getArtist())) { // if the artists list doesnt contain the current
																	// index artist
				listArtist.add(songs.get(i).getArtist()); // then add it to list of artists
			}
		}

		for (int i = 0; i < listArtist.size(); i++) {
			System.out.println((i + 1) + ". " + listArtist.get(i)); // printing the artists
		}
	}

	// Delete a song from the library (i.e. the songs list) -
	// also go through all playlists and remove it from any playlist as well if it
	// is part of the playlist
	public void deleteSong(int index) {
		boolean flag = false; // flag to see if song is deleted

		if (index >= 1 || index <= songs.size()) { // validating index
			songs.remove(index - 1); // removing -1 cuz 0 indexed
			flag = true; // setting flag true
		}
		if (!flag) { // if it hasnt been deleted then
			errorMsg = "Index Out of Bounds";
			throw new IndexOutOfBoundsException(errorMsg);
		} else { // otherwise do the following
			for (int i = 0; i < playlists.size(); i++) { // go through playlists
				for (int j = 0; j < playlists.get(i).getContent().size(); j++) { // go through the content of playlists
																					// so the ith index of playlist
					ArrayList<AudioContent> toBeDeleted = playlists.get(i).getContent(); // then we get the song to be
																							// deleted
					toBeDeleted.remove(j);
					playlists.get(i).setContent(toBeDeleted); // after removing it from tobedeleted we then update the
																// playlists
				}
			}
			System.out.println("SONG deleted");
		}
	}

	// Sort songs in library by year
	public void sortSongsByYear() {
		// Use Collections.sort()
		Collections.sort(songs, new SongYearComparator()); // sorting by year

	}

	// SongYearComparator that implements
	// the Comparator interface and compares two songs based on year
	private class SongYearComparator implements Comparator<Song> {
		public int compare(Song one, Song two) {
			return one.getYear() - two.getYear(); // return the lower
		}
	}

	// Sort songs by length
	public void sortSongsByLength() {
		// Use Collections.sort()
		Collections.sort(songs, new SongLengthComparator()); // sort by length
	}

	// SongLengthComparator that implements
	// the Comparator interface and compares two songs based on length
	private class SongLengthComparator implements Comparator<Song> {
		public int compare(Song one, Song two) {
			return one.getLength() - two.getLength(); // return lower
		}
	}

	// Sort songs by title
	public void sortSongsByName() {

		Collections.sort(songs); // sort by name using comparable interface
	}

	/*
	 * Play Content
	 */

	// Play song from songs list
	public void playSong(int index) {
		if (index < 1 || index > songs.size()) { // if invalid index
			errorMsg = "Index out of bounds";
			throw new IndexOutOfBoundsException(errorMsg);
		}
		songs.get(index - 1).play(); // else play
	}

	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) {
		if (index < 1 || index > audiobooks.size()) { // if invalid index
			errorMsg = "Index out of bounds";
			throw new IndexOutOfBoundsException(errorMsg);
		}
		audiobooks.get(index - 1).selectChapter(chapter); // else, set the audiobook then chapter then play
		audiobooks.get(index - 1).play();
	}

	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) {
		if (index < 1 || index > audiobooks.size()) { // if invalid index
			errorMsg = "Index out of bounds"; // not found
			throw new IndexOutOfBoundsException(errorMsg);
		}
		audiobooks.get(index - 1).printTOC(); // else print table of content
	}

	/*
	 * Playlist Related Methods
	 */

	public void makePlaylist(String title) {
		Playlist listPlaylist = new Playlist(title); // create new instance
		if (!playlists.contains(listPlaylist)) { // if it doesnt contain
			playlists.add(listPlaylist); // add the new instance to the playlists
		} else {
			errorMsg = "Playlist \"" + title + "\" already exists"; // else mention that it exists
			throw new PlaylistAlreadyExistsException(errorMsg);
		}
	}

	// Print list of content information (songs, audiobooks etc) in playlist named
	// title from list of playlists
	public void printPlaylist(String title) {
		boolean found = false;
		for (int i = 0; i < playlists.size(); i++) { // go through playlists
			if (playlists.get(i).getTitle().equals(title)) { // if title is the same
				playlists.get(i).printContents(); // print its contents
				found = true;
			}
		}
		if (!found) {
			errorMsg = title + " Not Found.";
			throw new PlaylistNotFoundException(errorMsg);
		}
	}

	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) {
		boolean found = false;
		for (int i = 0; i < playlists.size(); i++) { // go through playlists
			if (playlists.get(i).getTitle().equals(playlistTitle)) { // check if title is same
				playlists.get(i).playAll(); // play all
				found = true;
			}
		}
		if (!found) {
			errorMsg = playlistTitle + " Not Found.";
			throw new PlaylistNotFoundException(errorMsg);
		}
	}

	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) {
		boolean found = false;
		if (indexInPL < 1 || indexInPL > playlists.size()) { // if not valid index
			errorMsg = "Index is out of bounds!";
			throw new IndexOutOfBoundsException(errorMsg);
		}
		for (int i = 0; i < playlists.size(); i++) { // else go through playlist list
			if (playlists.get(i).getTitle().equals(playlistTitle)) { // if title equals
				playlists.get(i).getContent().get(indexInPL - 1).play(); // only play the index
				found = true;
			}
		}
		if (!found) {
			errorMsg = playlistTitle + " not found";
			throw new PlaylistNotFoundException(errorMsg);
		}

	}

	public void addContentToPlaylist(String type, int index, String playlistTitle) {
		for (int i = 0; i < playlists.size(); i++) { // go through playlist
			if (type.equalsIgnoreCase(Song.TYPENAME)) { // check type
				if (playlists.get(i).getTitle().equals(playlistTitle)) { // get title
					playlists.get(i).addContent(songs.get(index - 1)); // add the content and index -1
				} else {
					errorMsg = playlistTitle + " not found";
					throw new PlaylistNotFoundException(errorMsg);
				}
			} else if (type.equalsIgnoreCase(AudioBook.TYPENAME)) { // check type
				if (playlists.get(i).getTitle().equals(playlistTitle)) { // check title
					playlists.get(i).addContent(audiobooks.get(index - 1)); // add content -1
				} else {
					errorMsg = playlistTitle + " not found";
					throw new PlaylistNotFoundException(errorMsg);
				}
			}
		}

	}

	public void delContentFromPlaylist(int index, String title) {
		boolean found = false;
		if (index < 1 || index > playlists.size()) { // invalid index
			errorMsg = "Index is out of bounds";
			throw new IndexOutOfBoundsException(errorMsg);

		}
		for (int i = 0; i < playlists.size(); i++) { // go through playlist
			if (playlists.get(i).getTitle().equals(title)) { // check title
				ArrayList<AudioContent> toBeDeleted = playlists.get(i).getContent(); // create new instance
				toBeDeleted.remove(index - 1); // remove that specific index
				playlists.get(i).setContent(toBeDeleted); // update playlists
				System.out.println("SONG " + playlists.get(i).getTitle() + " Deleted");
				found = true;
			}
		}

		if (!found) {
			errorMsg = title + " was not found";
			throw new AudioContentNotFoundException(errorMsg);
		}

	}
}

class AudioContentNotFoundException extends RuntimeException {
	public AudioContentNotFoundException(String error) {
		super(error);
	}
}

class AudioBookNotFoundException extends RuntimeException {
	public AudioBookNotFoundException(String error) {
		super(error);
	}
}

class SongNotFoundException extends RuntimeException {
	public SongNotFoundException(String error) {
		super(error);
	}
}

class PlaylistNotFoundException extends RuntimeException {
	public PlaylistNotFoundException(String error) {
		super(error);
	}
}

class AudioContentAlreadyDownloadedException extends RuntimeException {
	public AudioContentAlreadyDownloadedException(String error) {
		super(error);
	}
}

class PlaylistAlreadyExistsException extends RuntimeException {
	public PlaylistAlreadyExistsException(String error) {
		super(error);
	}
}

class NotFoundException extends RuntimeException {
	public NotFoundException(String error) {
		super(error);
	}
}