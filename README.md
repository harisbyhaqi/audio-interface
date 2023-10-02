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

### Interfaces

- **Comparable Interface:**
  - Implementing this interface allows for sorting audio content based on a chosen criteria (e.g., by title, artist, duration).

- **Comparator Interface:**
  - This interface can be used to provide custom sorting options for audio content.

### Basic Algorithms

- Basic sorting algorithms like Bubble Sort, Selection Sort are used to filter audio content based on user interest.

## Usage

1. **Adding Audio Content:**
   - Use the `addContent()` method to add audio content to the library.

2. **Viewing Library Content:**
   - Use the `viewLibrary()` method to see the list of audio content in the library.

3. **Playing Audio:**
   - Use the `play()` method to play a selected audio content.


## Contributing

Feel free to fork this repository and submit pull requests with improvements or additional features.

## License

This project is licensed under the MIT License - see the [MIT License](LICENSE.txt) file for details.
