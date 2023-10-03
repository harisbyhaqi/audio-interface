
import java.io.IOException;
import java.util.*;
import java.io.*;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
	// declaring maps and arraylist
	private ArrayList<AudioContent> contents;
	private Map<String, Integer> mapTitle;
	private Map<String, ArrayList<Integer>> mapArtist;
	private Map<String, ArrayList<Integer>> mapGenre;

	String errorMsg = ""; // create class variable

	public AudioContentStore() {
		// initializing maps and arraylist in the constructor
		contents = new ArrayList<>();
		mapTitle = new HashMap<>();
		mapArtist = new HashMap<>();
		mapGenre = new HashMap<>();

		try { // try reading the file
			ArrayList<AudioContent> audioOjects = readFile("store.txt");
			for (AudioContent aContent : audioOjects) { // loop through audioObjects got from reading and add to
														// contents
				contents.add(aContent);
			}
		} catch (IOException e) { // catch IOException
			e.printStackTrace();
			System.exit(1);
		}

		for (int i = 0; i < contents.size(); i++) { // loop through contents
			AudioContent newContent = contents.get(i);
			mapTitle.put(newContent.getTitle(), i); // put Title of newContents i.e contents.get(i)
			if (newContent.getType() == Song.TYPENAME) { // if the type fo the newContents is a song
				Song songContent = (Song) newContent; // cast to song
				String stringSongContent = songContent.getGenre().toString(); // get the genre of the songContent
				if (mapArtist.containsKey(songContent.getArtist())) { // if the artist is in map
					ArrayList<Integer> values = mapArtist.get(songContent.getArtist()); // get the artist of the song
					values.add(i); // add i to the values arraylist
					mapArtist.put(songContent.getArtist(), values); // put existing artist and its value
				} else {// else if it isnt in the map
					ArrayList<Integer> values = new ArrayList<>(); // re-initialize the current ArrayList
					values.add(i); // add i value to arraylist
					mapArtist.put(songContent.getArtist(), values); // put NEW artist and its value
				}

				if (mapGenre.containsKey(stringSongContent)) { // checks if genre of current song is already in the map
					ArrayList<Integer> values = mapGenre.get(stringSongContent); // get the existing genre of the song
					values.add(i); // add i to the values arraylist
					mapGenre.put(stringSongContent, values); // put the existing genre and its value
				} else { // else if it isnt in the map
					ArrayList<Integer> values = new ArrayList<Integer>(); // reinitialize values as its not in the
																			// genremap
					values.add(i); // add i to arraylist
					mapGenre.put(stringSongContent, values); // put NEW genre and its value to map
				}
			} else if (newContent.getType() == AudioBook.TYPENAME) { // check if the type is AudioBOOK
				AudioBook bookContent = (AudioBook) newContent; // cast to audiobook
				if (mapArtist.containsKey(bookContent.getAuthor())) { // check if author exists in map
					ArrayList<Integer> values = mapArtist.get(bookContent.getAuthor()); // get the existing author of
																						// the book
					values.add(i); // add i to arraylist
					mapArtist.put(bookContent.getAuthor(), values); // put the existing author with value
				} else {
					ArrayList<Integer> values = new ArrayList<>(); // reinitialize values as not in artist Map
					values.add(i); // add i to arraylist
					mapArtist.put(bookContent.getAuthor(), values); // put new author and its value to map
				}
			}
		}
	}

	private ArrayList<AudioContent> readFile(String filename) throws IOException { // reading file method
		ArrayList<AudioContent> audioList = new ArrayList<>(); // initialize new arrayList
		File file = new File(filename); // new instance of file
		Scanner sc = new Scanner(file); // new scanner instance sc

		while (sc.hasNext()) { // check if theres input in the file
			String line = sc.nextLine();

			if (line.equals(Song.TYPENAME)) { // if its a song get the following pieces of information and store it
				System.out.println("Loading " + Song.TYPENAME);
				String id = sc.nextLine();
				String title = sc.nextLine();
				int year = sc.nextInt();
				sc.nextLine();
				int length = sc.nextInt();
				sc.nextLine();
				String artist = sc.nextLine();
				String composer = sc.nextLine();
				Song.Genre genre = Song.Genre.valueOf(sc.nextLine());
				String lyrics = "";
				int currentLength = sc.nextInt();
				sc.nextLine();

				for (int i = 0; i < currentLength; i++) {
					lyrics += sc.nextLine() + "\n";
				}
				audioList.add(
						new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, genre, lyrics));

			} else if (line.equals(AudioBook.TYPENAME)) { // if its an audiobook get the following information and store
				System.out.println("Loading " + AudioBook.TYPENAME);
				String id = sc.nextLine();
				String title = sc.nextLine();
				int year = sc.nextInt();
				sc.nextLine();
				int length = sc.nextInt();
				sc.nextLine();
				String author = sc.nextLine();
				String narrator = sc.nextLine();
				int chapterTitlesLength = sc.nextInt();
				sc.nextLine();
				ArrayList<String> chapterTitles = new ArrayList<>();
				ArrayList<String> chapters = new ArrayList<>();
				String current = "";

				for (int i = 0; i < chapterTitlesLength; i++) {
					chapterTitles.add(sc.nextLine());
				}
				for (int i = 0; i < chapterTitlesLength; i++) {
					int currentLength = sc.nextInt();
					sc.nextLine();
					for (int j = 0; j < currentLength; j++) {
						current += sc.nextLine();
					}
					chapters.add(current);
				}
				audioList.add(new AudioBook(title, year, id, AudioBook.TYPENAME, current, length, author, narrator,
						chapterTitles, chapters));
			}
		}
		sc.close();
		return audioList;

	}

	public AudioContent getContent(int index) {
		if (index < 1 || index > contents.size()) {
			return null;
		}
		return contents.get(index - 1);
	}

	public void listAll() {
		for (int i = 0; i < contents.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	/**
	 * Search method, using the parameter title. Loop through the set
	 * to check if the title of the song to be found exists
	 * then print all the info about that song.
	 * 
	 * @param title
	 */
	public void search(String title) {
		Set<String> set = mapTitle.keySet(); // creating a set with the key values of the map
		boolean found = false;
		for (String key : set) { // loop through the set
			if (key.equals(title)) { // if current iteration equals title
				int value = mapTitle.get(key); // get the value
				System.out.print((value + 1) + ". "); // print value + 1 for formatting
				contents.get(value).printInfo(); // print all the info about the song
				System.out.println();
				found = true;
			}
		}
		if (!found) { // throw error message if no matches
			errorMsg = "No matches for " + title;
			throw new NotFoundException(errorMsg);
		}
	}

	/**
	 * Artist search, using the parameter artist.
	 * Loop through the set to check if the artist exists
	 * if so, print all the content of that artist
	 * 
	 * @param artist
	 */
	public void artistSearch(String artist) {
		Set<String> set = mapArtist.keySet(); // create a set with key values of the mpa
		boolean found = false;
		for (String key : set) { // loop through the set
			if (key.equals(artist)) { // if the current iteration equals artist
				for (int index : mapArtist.get(key)) { // loop through the mapArtist of the current Iteration
					System.out.print((index + 1) + ". "); // print value + 1 for formatting
					contents.get(index).printInfo(); // print all info
					System.out.println();
					found = true;
				}
			}
		}
		if (!found) { // if not found throw error message
			errorMsg = "No matches for " + artist;
			throw new NotFoundException(errorMsg);
		}
	}

	/**
	 * Genre search method, using the parameter genre.
	 * Loop through the set to check if the genre exists
	 * if so, print all the content of that genre
	 * 
	 * @param genre
	 */
	public void genreSearch(String genre) {
		Set<String> set = mapGenre.keySet(); // create a set with key values of the map
		boolean found = false;
		for (String key : set) { // loop through set
			if (key.equals(genre)) { // if the current iteration is the genre to be found
				for (int index : mapGenre.get(key)) { // loop through genre artist of the current iteration
					System.out.print((index + 1) + ". "); // value + 1 for formatting
					contents.get(index).printInfo(); // print all info
					System.out.println();
					found = true;
				}
			}
		}
		if (!found) { // if not found throw error message
			errorMsg = "No matches for " + genre;
			throw new NotFoundException(errorMsg);
		}
	}

	/**
	 * Artist download, download all songs for that artist
	 * using the parameter. Loop through the set to see if
	 * artist exists if so download all songs/audiobooks for
	 * artist/author
	 * 
	 * @param artist
	 * @return
	 */
	public ArrayList<AudioContent> artistDownload(String artist) {
		Set<String> set = mapArtist.keySet(); // set for key of map artist
		ArrayList<AudioContent> downloads = new ArrayList<>(); // array list to return the downloads
		boolean found = false;
		for (String key : set) { // loop through set
			if (key.equals(artist)) { // if artist is located
				found = true;
				for (int index : mapArtist.get(key)) { // loop through the map of the current iteration
					downloads.add(contents.get(index)); // add to the array list of the current index
				}
			}
		}
		if (!found) { // if not found throw error message
			errorMsg = "No content found for: " + artist;
			throw new NotFoundException(artist);
		}
		return downloads;
	}

	/**
	 * Genre download, download all songs for that genre
	 * using the parameter. Loop through the set to see
	 * if the genre exists, if so, download all songs for that genre
	 * 
	 * @param genre
	 * @return
	 */
	public ArrayList<AudioContent> genreDownload(String genre) {
		Set<String> set = mapGenre.keySet(); // set for key of mapgenre
		ArrayList<AudioContent> downloads = new ArrayList<>(); // array list to keep track of downloads
		boolean found = false;
		for (String key : set) { // loop through set
			if (key.equals(genre)) { // if genre is found
				found = true;
				for (int index : mapGenre.get(key)) { // loop through map genre of the current iteration
					downloads.add(contents.get(index)); // add to the downloads list of the current index
				}
			}
		}
		if (!found) { // if not found throw error message
			errorMsg = "No content found for: " + genre;
			throw new NotFoundException(errorMsg);
		}
		return downloads;
	}

}
