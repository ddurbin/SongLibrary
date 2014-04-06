//Daniel Durbin
package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO {

	SongLib list;
	File library = new File("library.txt");
	FileWriter fstream;
	BufferedWriter out;

	BufferedReader in;

	public FileIO(SongLib list){
		this.list = list;
		try{
			library.createNewFile(); // create file if it doesn't already exist
			in = null;
			fstream = new FileWriter(library, true);
			out = new BufferedWriter(fstream);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	// write songs to text file
	public void writeLibrary(){
		String text;
		try{
			for(SongNode x : list.details.songLL){ // iterate through songs
				text = x.toString(); //get node toString
				out.write(text + "\n"); //write song details
			}
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//  import songs from text file
	public void importLibrary(){
		String delim = " "; //Delimiter = space
		String[] tokens;
		try{
			in = new BufferedReader(new FileReader(library)); // set up reader
			for(String line; (line = in.readLine()) != null; ){ //iterate through list
				SongNode add = new SongNode("","","",""); //new SongNode
				tokens = line.split(delim); // Tokens song line
				int length = tokens.length;
				switch(length){
					case 2: length = 2; // song only has name and artist
						add.name = tokens[0];
						add.artist = tokens[1];
						break;

					case 3: length = 3; // song contains name, artist, album
						add.name = tokens[0];
						add.artist = tokens[1];
						add.album = tokens[2];
						break;
					case 4: length = 4; // song contains name, artist, album, year
						add.name = tokens[0];
						add.artist = tokens[1];
						add.album = tokens[2];
						add.year = tokens[3];
						break;	
					default: 
						break;
				}
				list.details.songLL.add(add); //add song
			}
			list.details.displayList(); // update JList
			PrintWriter writer = new PrintWriter(library); // reset text file
			writer.print("");
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}
}
