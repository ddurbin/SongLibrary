//Daniel Durbin
package library;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowAction extends WindowAdapter{

	public static final String dirName = "dat";
	public static final String fileName = "library.dat";
	SongLib library; //songs
	FileIO file; //FileIO object to read from and write to
	
	public WindowAction(SongLib library){ 
		super();
		this.library = library; //get library
		file = new FileIO(library);
	}
	//upon closing library writes the existing songs to text file
	public void windowClosing(WindowEvent evt) {
		file.writeLibrary();
		System.exit(0);
	}

}
