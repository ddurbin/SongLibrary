//Daniel Durbin
package library;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//puts all classes together and creates library. Containing Main method
public class SongLib extends JFrame implements Serializable{

	private static final long serialVersionUID = 1L;
	GUI details; //GUI and LL object
	GridBagLayout gridbag;
	GridBagConstraints c;
	JPanel message;
	JTextArea error; //displays errors
	FileIO file; //file object for File IO

	public SongLib(String title){
		
		super(title);
		details = new GUI();
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gridbag);
		c.gridwidth = GridBagConstraints.REMAINDER;
		add(details,c);
		file = new FileIO(this);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SongLib player = new SongLib("Song Library"); //create library
		player.file.importLibrary(); //get archived songs
		if(player.details.songLL.size() > 0){ //set selected song
			player.details.setSong(player.details.songLL.get(0));
		}
		player.addWindowListener(new WindowAction(player)); //create listener to write songs after close
		player.pack();
		player.setVisible(true);
		player.setLocationRelativeTo(null);
		player.setResizable(true);
		player.setMinimumSize(new Dimension(900,600));
	}
}
