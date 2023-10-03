/*
 * Haris Byhaqi
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App 

public class MyAudioUI {
	public static void main(String[] args) {

		AudioContentStore store = new AudioContentStore();

		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()) {
			String action = scanner.nextLine();

			if (action == null || action.equals("")) {
				System.out.print("\n>");
				continue;
			} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("STORE")) // List all content in store
			{
				store.listAll();
			} else if (action.equalsIgnoreCase("SONGS")) // List all songs
			{
				mylibrary.listAllSongs();
			} else if (action.equalsIgnoreCase("BOOKS")) // List all books
			{
				mylibrary.listAllAudioBooks();
			} else if (action.equalsIgnoreCase("PODCASTS")) // List all podcasts
			{
				mylibrary.listAllPodcasts();
			} else if (action.equalsIgnoreCase("ARTISTS")) // List all artists
			{
				mylibrary.listAllArtists();
			} else if (action.equalsIgnoreCase("PLAYLISTS")) // List all play lists
			{
				mylibrary.listAllPlaylists();
			}
			// Download audiocontent (song/audiobook/podcast) from the store
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) {
				try { // try the following code
					int fromIndex = 0, toIndex = 0; // initialize from and to index
					// below collect input for fromIndex
					System.out.print("From Store Content #: ");
					if (scanner.hasNext()) {
						fromIndex = scanner.nextInt();
						scanner.nextLine();
					}
					// Below collect input for toIndex
					System.out.print("To Store Content #: ");
					if (scanner.hasNext()) {
						toIndex = scanner.nextInt();
						scanner.nextLine();
					}
					// loop using fromIndex, toIndex
					for (int i = fromIndex; i <= toIndex; i++) {
						try {
							mylibrary.download(store.getContent(i)); // try to download the content at i which starts at
																		// fromIndex to toIndex
						} catch (AudioContentAlreadyDownloadedException e) { // catch the exception 'content already
																				// downloaded' exception
							System.out.println(e.getMessage());
						}
					}
				} catch (NotFoundException e) { // for the main try/catch catch if content is not Found
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("DOWNLOADA")) {
				try { // try to download based on user input for artist
						// below get input for artist
					String artist = "";
					System.out.print("Artist Name: ");
					if (scanner.hasNext()) {
						artist = scanner.nextLine();
					}
					// loop through artistDownload which is a method in the store class
					for (AudioContent content : store.artistDownload(artist)) {
						try { // try downloading the content
							mylibrary.download(content);
						} catch (AudioContentAlreadyDownloadedException e) { // catch Already Downloaded exception
							System.out.println(e.getMessage());
						}
					}
				} catch (NotFoundException e) { // if no artist found catch exception
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("DOWNLOADG")) {
				try { // try to download based on user inp for genre
						// below get input for genre
					String genre = "";
					System.out.print("Genre: ");
					if (scanner.hasNext()) {
						genre = scanner.nextLine();
					}
					// loop through genreDownload which is a method in store class
					for (AudioContent content : store.genreDownload(genre)) {
						try { // try downloading the content
							mylibrary.download(content);
						} catch (AudioContentAlreadyDownloadedException e) { // catch already downloaded exception
							System.out.println(e.getMessage());
						}
					}
				} catch (NotFoundException e) { // if no genre found catch exception
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("SEARCH")) {
				try { // try searching for the title of the song
						// below get user inp for the title of the song
					String title = "";
					System.out.print("Title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}
					store.search(title); // search in the store class using the search method with the inputted title
				} catch (NotFoundException e) { // catch exception not Found
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("SEARCHA")) {
				try { // try searching songs based on artist
						// get user input
					String artist = "";
					System.out.print("Artist/Author: ");
					if (scanner.hasNext()) {
						artist = scanner.nextLine();
					}
					store.artistSearch(artist); // search in store class with artistSearch method given the user input
												// 'artist'
				} catch (NotFoundException e) { // catch the not found exception
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("SEARCHG")) {
				try { // try searching songs based on genre
						// get input for genre
					String genre = "";
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					if (scanner.hasNext()) {
						genre = scanner.nextLine();
					}
					store.genreSearch(genre); // search based on genre using the genreSearch method in the store class
				} catch (NotFoundException e) { // catch not Found exception
					System.out.println(e.getMessage());
				}
			}

			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song
			else if (action.equalsIgnoreCase("PLAYSONG")) {
				// Print error message if the song doesn't exist in the library
				try { // try playing the song with the user input for index from library
					int index = 0;
					System.out.print("Song Number: ");
					if (scanner.hasNext()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playSong(index);
				} catch (IndexOutOfBoundsException e) { // catch if index is out of bounds
					System.out.println(e.getMessage());
				}

			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) {
				// Print error message if the book doesn't exist in the library
				try { // try printing book table of content using index
					int index = 0;
					System.out.print("Audio Book Number: ");
					if (scanner.hasNext()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.printAudioBookTOC(index);
				} catch (IndexOutOfBoundsException e) { // catch if index is out of bounds
					System.out.println(e.getMessage());
				}
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) {
				try { // try playing book using index
					int index = 0;
					int chapter = 0;
					System.out.print("Audio Book Number: ");
					if (scanner.hasNext()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					System.out.print("Chapter: ");
					if (scanner.hasNext()) {
						chapter = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playAudioBook(index, chapter);
				} catch (IndexOutOfBoundsException e) { // catch out of bounds exception
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("PLAYALLPL")) {
				try { // try playing all content in playlist
					String title = "";
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}
					mylibrary.playPlaylist(title);
				} catch (PlaylistNotFoundException e) { // catch playlistnot found exception
					System.out.println(e.getMessage());
				}
			}
			// Specify a playlist title (string)
			// Read the index of a song/audiobook/podcast in the playist from the keyboard
			// Play all the audio content
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) {
				try { // play specific content from playlist with title and name of playlist
					String title = "";
					int index = 0;
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}
					System.out.print("Content Number: ");
					if (scanner.hasNext()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playPlaylist(title, index);
				} catch (IndexOutOfBoundsException e) { // catch if index of the content is not in playlist
					System.out.println(e.getMessage());
				} catch (PlaylistNotFoundException e) { // catch if playlist title is not found
					System.out.println(e.getMessage());
				}
			}
			// Delete a song from the list of songs in mylibrary and any play lists it
			// belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) {
				try { // try deleting song based on index
					int index = 0;
					System.out.print("Library Song #: ");
					if (scanner.hasNext()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.deleteSong(index);
				} catch (IndexOutOfBoundsException e) { // catch if index is out of bounds
					System.out.println(e.getMessage());
				}
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) {
				try { // try making playlist
					String title = "";
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}
					mylibrary.makePlaylist(title);
				} catch (PlaylistAlreadyExistsException e) { // if created with name catch Already exists
					System.out.println(e.getMessage());
				}
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL")) // print playlist content
			{
				try { // print content of playlist
					String title = "";
					System.out.print("Playlist title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}
					mylibrary.printPlaylist(title);
				} catch (PlaylistNotFoundException e) { // catch if the playlist isnt found
					System.out.println(e.getMessage());
				}
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a
			// playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from
			// the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) {
				try { // add to playlist
					String title = "";
					String type = "";
					int index = 0;
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					if (scanner.hasNext()) {
						type = scanner.nextLine();
					}
					System.out.print("Library Content #: ");
					if (scanner.hasNext()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.addContentToPlaylist(type, index, title);
				} catch (PlaylistNotFoundException e) { // catch if playlist doesnt exist
					System.out.println(e.getMessage());
				}
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) {
				try { // try deleting from playlist
					String title = "";
					int index = 0;
					System.out.print("Playlist Title: ");
					if (scanner.hasNext()) {
						title = scanner.nextLine();
					}
					System.out.print("Playlist Content #: ");
					if (scanner.hasNext()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.delContentFromPlaylist(index, title);
				} catch (AudioContentNotFoundException e) { // catch if content not found
					System.out.println(e.getMessage());
				} catch (IndexOutOfBoundsException e) { // catch index out of bounds
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			} else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			} else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}
			System.out.print("\n>");
		}
	}
}
