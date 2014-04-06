//Daniel Durbin
package library;

import java.io.Serializable;

public class SongNode implements Comparable<SongNode>, Serializable{

	private static final long serialVersionUID = 1L;
	String name, artist, album, year; //song fields
	SongNode next;
	
	public SongNode(String name, String artist, String album, String year){
		//create song
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String toString(){
		
		return name + " " + artist + " " + album + " " + year;
	}
	// overrides Object compareTo. Compares name and artist only
	public int compareTo(SongNode song){
		
		int compare = name.compareToIgnoreCase(song.name);
		if(compare != 0){ //different name
			return compare;
		}else{ // same name, check artist
			return artist.compareToIgnoreCase(song.artist);
		}
	}
	//overrides Object equals.
	public boolean equals(Object o){
		
		return ((SongNode)o).name.equals(name) && ((SongNode)o).artist.equals(artist);
	}

}
