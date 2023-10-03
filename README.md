# Audio Interface Application

## Introduction

The MyAudio application is a comprehensive audio management program designed to handle various types of audio content, including Songs, Audio Books, and Podcasts. It provides functionalities to store, manage, and play these items from a library. Users can also create playlists that can contain a mix of different types of audio content.

## Features

1. **Library Management:**
   - Users can add, remove, and view audio content in the library.
   - Audio content can be of different types: Songs, Audio Books, and Podcasts.

2. **Store:**
   - Simulated store for downloading additional audio content.
   - Utilizes a HashMap to track downloaded content.

3. **Playback:**
   - Playing a song means printing its lyrics.
   - Utilizes polymorphism to handle different content types.

4. **Playlists:**
   - Users can create playlists with a mix of audio content types.
   - Playlists can be edited (add, remove).

5. **Sorting and Filtering:**
   - Implements basic sorting algorithms (Bubble Sort, Selection Sort) to filter audio content based on user interest.

6. **Search:**
   - Allows users to search for audio content by title, artist, or genre.

## Implementation Details

### Object-Oriented Principles

- **Classes and Objects:**
  - The program utilizes classes for different types of audio content (e.g., `Song`, `AudioBook`, `Podcast`).
  - Each audio content item is an object of its respective class.

- **Inheritance:**
  - Common attributes/methods are inherited from a base class `AudioContent`.

- **Polymorphism:**
  - The `play()` method is overridden in each audio content class to display the specific type of content (e.g., lyrics for a song).

### Data Structures

- **ArrayLists:**
  - The library and playlists are implemented using ArrayLists.

- **HashMaps:**
  - Utilized for tracking downloaded content.
 
- **HashSets:**
  - Utilized for efficiently adding new content to playlist. 

### Interfaces

- **Comparable Interface:**
  - Implementing this interface allows for sorting audio content based on a chosen criteria (e.g., by title, artist, duration).

- **Comparator Interface:**
  - This interface can be used to provide custom sorting options for audio content.

### Basic Algorithms

- Basic sorting algorithms like Bubble Sort, Selection Sort are used to filter audio content based on user interest.

## Usage

1. **Run the `MyAudioUI.java` file**

2. **Use any of the following commands to test the programs functionality**
     - **store:** lists all the audio content available, with the title, author name, content type and genre.
     - **download:** allows you to download audio content based on the range you provide, start range and end range. Content number is shown in the store, downloads all song in the range.
     - **songs:** Lists only the songs from the store
     - **Artists:** lists only the artists from the store
     - **sortbyname:** sorts songs alphabetically by first name
     - **sortbyyear:** sorts song numerically by year
     - **playsong:** play lyrics of a song, you will be asked to input a song number from the store
     - **books:** Lists only the books from the store
     - **booktoc:** lists the table of content for book number that you will input
     - **playbook:** input a number for the book and a valid chapter number, it will then play that chapter of the book
     - **makepl:** create a new playlist, input a name for your playlist
     - **addtopl:** input a playlist name (one that you've created) then add songs, books to the playlist with the store content #
     - **printpl:** lists all the content in your playlist
     - **playallpl:** play all the contents in the playlist
     - **playpl:** input the # of item in your playlist to play that specific content
     - **delsong:** delete a song from the store
     - **quit**


## Contributing

Feel free to fork this repository and submit pull requests with improvements or additional features.

## License

This project is licensed under the MIT License - see the [MIT License](LICENSE.txt) file for details.
