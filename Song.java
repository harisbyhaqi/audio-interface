
/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable<Song>// Comparable interface
{
	public static final String TYPENAME = "SONG";

	public static enum Genre {
		POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL
	};

	private String artist; // Can be multiple names separated by commas
	private String composer; // Can be multiple names separated by commas
	private Genre genre;
	private String lyrics;

	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics) {
		// Initialize additional Song instance variables.
		super(title, year, id, type, audioFile, length); // Using the super method to inherit
		this.artist = artist; // initializing artist
		this.composer = composer; // initializing composer
		this.genre = genre; // initializing genre
		this.lyrics = lyrics; // initializing lyrics
	}

	public String getType() {
		return TYPENAME;
	}

	// Print information about the song. First print the basic information of the
	// AudioContent
	public void printInfo() {
		super.printInfo(); // printing the information in the AudioContent Class using super
		System.out.println("Artist: " + getArtist() + " Composer: " + getComposer() + " Genre: " + getGenre()); // adding
																												// the
																												// artist,
																												// composer
																												// and
																												// genre
	}

	// Play the song by setting the audioFile to the lyrics string and then calling
	// the play() method of the superclass
	public void play() {
		this.setAudioFile(lyrics); // setting audio file to lyrics
		super.play(); // playing inherited method from superclass

	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	// Two songs are equal if their AudioContent information is equal and both the
	// composer and artists are the same
	public boolean equals(Object other) {
		Song otherSong = (Song) other; // Creating new instance of Song using the parameter 'other' then casting it to
										// Song
		return super.equals(otherSong) && getComposer().equals(otherSong.getComposer())
				&& getArtist().equals(otherSong.getArtist()); // first using the equals method from the super class to
																// check other conditions then checking if artist and
																// composer are equal
	}

	// Comparable interface
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other) {
		return getTitle().compareTo(other.getTitle()); // comparing two songs with respect to their titles
	}
}
