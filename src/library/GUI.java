//Daniel Durbin
package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
//class implements the GUI for the library
public class GUI extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	LinkedList<SongNode> songLL; //linked list of song nodes
	JLabel name, artist, album, year, songs, selected; //labels for selected songs
	JTextArea nameText, artistText, albumText, yearText; //text for selected songs
	JList display; //Jlist to list library
	JScrollPane displayScroll;
	DefaultListModel model;
	JButton add, edit, delete; //control buttons
	GridBagLayout gridbag;
	GridBagConstraints c;

	JPanel message; // panel to display error messages
	JTextArea error; //error message

	JPanel addEditSong; //add/edit song panel
	JPanel selectedSong; //panel for selected song
	JTextArea newSongName, newSongArtist, newSongAlbum, newSongYear; //text area to enter new songs and edit songs
	JLabel topLabel, nameNew, artistNew, albumNew, yearNew;

	public GUI(){
		// set up Gridbag layout
		songLL = new LinkedList<SongNode>();
		model = new DefaultListModel();
		display = new JList(model);
		displayScroll = new JScrollPane();
		displayScroll.setPreferredSize(new Dimension(500,200));
		display.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add = new JButton("add");
		edit = new JButton("edit");
		delete = new JButton("delete");
		selectedSong = new JPanel();
		selected = new JLabel("Selected Song:");
		name = new JLabel("Name:");
		artist = new JLabel("Artist:");
		album = new JLabel("Album:");
		year = new JLabel("Year:");
		songs = new JLabel("Library:");
		nameText = new JTextArea(1,10);
		nameText.setEditable(false);
		artistText = new JTextArea(1,10);
		artistText.setEditable(false);
		albumText = new JTextArea(1,10);
		albumText.setEditable(false);
		yearText = new JTextArea(1,10);
		yearText.setEditable(false);
		selectedSong.add(name,c);
		selectedSong.add(nameText,c);
		selectedSong.add(artist,c);
		selectedSong.add(artistText,c);
		selectedSong.add(album,c);
		selectedSong.add(albumText,c);
		selectedSong.add(year,c);
		selectedSong.add(yearText,c);
		addEditSong = new JPanel();
		newSongName = new JTextArea(1,10);
		newSongArtist = new JTextArea(1,10);
		newSongAlbum = new JTextArea(1,10);
		newSongYear = new JTextArea(1,10);
		topLabel = new JLabel("Add or Edit Song:");
		nameNew = new JLabel("Name:");
		artistNew = new JLabel("Artist:");
		albumNew = new JLabel("Album:");
		yearNew = new JLabel("Year:");
		addEditSong.add(nameNew);
		addEditSong.add(newSongName);
		addEditSong.add(artistNew);
		addEditSong.add(newSongArtist);
		addEditSong.add(albumNew);
		addEditSong.add(newSongAlbum);
		addEditSong.add(yearNew);
		addEditSong.add(newSongYear);
		addEditSong.setVisible(true);
		error = new JTextArea("");
		error.setBackground(Color.red);
		message = new JPanel();
		message.add(error);
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		setLayout(gridbag);
		c.insets = new Insets(5,5,10,5);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.WEST;
		add(selected,c);
		add(selectedSong,c);
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 3;
		add(delete,c);
		c.insets = new Insets(5,5,10,5);
		c.gridx = 0;
		c.gridy = 5;
		add(message,c);
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		add(topLabel,c); 
		c.gridx = 0;
		c.gridy = 7;
		c.anchor = GridBagConstraints.CENTER;
		add(addEditSong,c);
		c.gridx = 0;
		c.gridy = 8;
		c.anchor = GridBagConstraints.WEST;
		add(add,c);
		c.gridx = 0;
		c.gridy = 9;
		add(edit,c);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 10;
		add(songs,c);
		c.gridx = 0;
		c.gridy = 11;
		displayScroll.setViewportView(display);
		c.gridwidth = 4;
		add(displayScroll,c);
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 12;
		add(message,c);
		
		// listener to determine which song selected
		MouseListener mouseListener  = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1){
					int index = display.getMinSelectionIndex();
					if(index != -1){
						SongNode selectedSong = (SongNode) songLL.get(index);
						nameText.setText(selectedSong.name);
						artistText.setText(selectedSong.artist);
						albumText.setText(selectedSong.album);
						yearText.setText(selectedSong.year);
					}

				}
			}
		};
		display.addMouseListener(mouseListener); //listener for JList

		//add button action listener
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String newName, newArtist, newAlbum, newYear;
				message.setVisible(false);
				if(newSongName.getText().equals("") || newSongArtist.getText().equals("")){ //no details entered
					error.setText("error: unable to add song!");
					message.setVisible(true);
				}else{ //process details
					newName = newSongName.getText(); //get name
					newArtist = newSongArtist.getText(); //get artist
					if((newSongAlbum.getText() == null) || (newSongAlbum.getText().equals(""))){ //get album
						newAlbum = "";
					}else{ 
						newAlbum = newSongAlbum.getText();
					}
					if((newSongYear.getText() == null) || (newSongYear.getText().equals(""))){ //get year
						newYear = "";
					}else{
						newYear = newSongYear.getText();
					}
					SongNode song = new SongNode(newName, newArtist, newAlbum, newYear); //create songNode
					if(songLL.contains(song)){ //duplicate check
						error.setText("error: duplicate song exists");
						message.setVisible(true);
					}else{ //no duplicate
						songLL.add(song);
						Collections.sort(songLL); //sort song list
						displayList(); //update display of songs
						setSong(song); //display new song info
						display.setSelectedIndex(songLL.indexOf(song)); //set selected song to new song
					}
				}
				newSongName.setText(null); //reset all add/edit text
				newSongAlbum.setText(null);
				newSongArtist.setText(null);
				newSongYear.setText(null);
			}
		});
		//edit song listener
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				message.setVisible(false);
				int index = display.getMinSelectionIndex(); //get selected song
				if(index == -1){ // no song selected
					error.setText("error: no songs to edit");
					message.setVisible(true);
					return;
				}
				SongNode exists = (SongNode)songLL.get(index); // get existing selected song
				SongNode newSong = new SongNode(exists.name,exists.artist,exists.album,exists.year); // create new song to compare
				// no new details entered to edit
				if(newSongName.getText().equals("")  && newSongArtist.getText().equals("") && newSongAlbum.getText().equals("") && newSongYear.getText().equals("")){
					error.setText("error: enter details to change");
					message.setVisible(true);
				}else{
					if(!(newSongName.getText() == null) && !(newSongName.getText().equals(""))){ //get name text if exists

						String text = newSongName.getText();
						newSong.name = text;
						//need to sort!!
					}
					if(!(newSongArtist.getText() == null) && !(newSongArtist.getText().equals(""))){ //get artist text if exists

						String text = newSongArtist.getText();
						newSong.artist = text;
						//need to sort!!
					}
					if(!(newSongAlbum.getText() == null) && !(newSongAlbum.getText().equals(""))){ // get album text if exists

						String text = newSongAlbum.getText();
						newSong.album = text;
					}
					if(!(newSongYear.getText() == null) && !(newSongYear.getText().equals(""))){ // get year text if exists

						String text = newSongYear.getText();
						newSong.year = text;
					}
					if(songLL.contains(newSong)){ //song exists
						//duplicate song no change to album or year
						if(newSong.album.compareToIgnoreCase(exists.album) == 0 && newSong.year.compareToIgnoreCase(exists.year) == 0){
							error.setText("error: song already exists");
							message.setVisible(true);
						}else{ //update album and/or year
							((SongNode)songLL.get(index)).name = newSong.name;
							((SongNode)songLL.get(index)).artist = newSong.artist;
							((SongNode)songLL.get(index)).album = newSong.album;
							((SongNode)songLL.get(index)).year = newSong.year;
							setSong((SongNode)songLL.get(index));
							Collections.sort(songLL);
							displayList();
							display.setSelectedIndex(songLL.indexOf(newSong));
						}
						
					}else{ // name and/or album changed
						((SongNode)songLL.get(index)).name = newSong.name;
						((SongNode)songLL.get(index)).artist = newSong.artist;
						((SongNode)songLL.get(index)).album = newSong.album;
						((SongNode)songLL.get(index)).year = newSong.year;
						setSong((SongNode)songLL.get(index)); // set new edited song details
						Collections.sort(songLL); // sort songs
						displayList(); // refresh JList of songs
						display.setSelectedIndex(songLL.indexOf(newSong)); // set edited song as selected
					}
				}
				
				newSongName.setText(null); // reset text for add/edit songs
				newSongAlbum.setText(null);
				newSongArtist.setText(null);
				newSongYear.setText(null);
			}
		});	
		// delete button listener
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				message.setVisible(false);
				if(display.getMinSelectionIndex() == -1){ // no song selected
					error.setText("error: no song selected to delete");
					message.setVisible(true);
				}else{
					int index = display.getMinSelectionIndex(); // get song
					songLL.remove(index); //delete
					displayList(); //refresh JList
					nameText.setText(null); //reset selected song text boxes
					albumText.setText(null);
					artistText.setText(null);
					yearText.setText(null);
					if(songLL.size() > 0){ //display first song after delete if it exists
						display.setSelectedIndex(0);
						setSong((SongNode)songLL.get(0));
					}
				}


			}
		});	
	}
	//Sets the selected song details to the selected song
	public void setSong(SongNode song){
		nameText.setText(song.name);
		albumText.setText(song.album);
		artistText.setText(song.artist);
		yearText.setText(song.year);
	}
	// refreshes the JList to display new changed list of songs
	public void displayList(){
		model.clear();
		ListIterator<SongNode> x = songLL.listIterator(0);
		Object element;
		while(x.hasNext()){ //iterate through list
			element = x.next();
			model.addElement(((SongNode)element).name); // add song
		}


	}
}
