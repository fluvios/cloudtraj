package UserInterface;

import processing.core.PApplet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import bdss.preprocessing.Parse;
import bdss.trajdb.Database;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap.OpenStreetMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import g4p_controls.*;

public class CTGui extends PApplet {
	UnfoldingMap map;
	String fileName;
	String path;
	String fileSelected;
	Parse parser = new Parse();
	Database db = new Database();
	
	// Serial ID is optional and added by Eclipse
	@Override
	public void setup() {
		size(1000, 600, P3D);
		if (frame != null) {
			frame.setResizable(true);
		}
		createGUI();
		customGUI();
		// Place your setup code here

	}

	@Override
	public void draw() {
		background(230);
		map.draw();
		db.connect();
	}
	
	public static void main(String args[]) {
		PApplet.main(new String[] { CTGui.class.getName() });
	}

	// Use this method to add additional statements
	// to customise the GUI controls
	public void customGUI() {
		map = new UnfoldingMap(this, 20, 86, 570, 400, new OpenStreetMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
	}
	
	public void fileSelected(File selection) {
		if (selection == null) {
			println("Window was closed or the user hit cancel.");
		} else {
			println("User selected " + selection.getAbsolutePath());
			path = selection.getAbsolutePath();
			if (fileSelected.equals("trip")) {
				fileLocation.setText(path);
				try {
					parser.read(path);
					db.insert(parser.getTempTrajectory());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				map = new UnfoldingMap(this, 20, 86, 570, 400, new OpenStreetMapProvider());
				map.zoomAndPanTo(12, new Location(41.1491,-8.6107));
				MapUtils.createDefaultEventDispatcher(this, map);
			}
		}
	}

	/* =========================================================
	 * ====                   WARNING                        ===
	 * =========================================================
	 * The code in this tab has been generated from the GUI form
	 * designer and care should be taken when editing this file.
	 * Only add/edit code inside the event handlers i.e. only
	 * use lines between the matching comment tags. e.g.

	 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
	     // It is safe to enter your event code here  
	 } //_CODE_:button1:12356:
	 
	 * Do not rename this tab!
	 * =========================================================
	 */

	public void textfield1_change1(GTextField source, GEvent event) { //_CODE_:fileLocation:941412:
	  println("textfield1 - GTextField >> GEvent." + event + " @ " + millis());
	} //_CODE_:fileLocation:941412:

	public void button1_click1(GButton source, GEvent event) { //_CODE_:loadButton:288552:
	  println("loadButton - GButton >> GEvent." + event + " @ " + millis());
	  selectInput("Select a file to process:", "fileSelected");
	  fileSelected = "trip";
	} //_CODE_:loadButton:288552:

	public void button2_click1(GButton source, GEvent event) { //_CODE_:clearButton:472670:
	  println("clearButton - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:clearButton:472670:

	public void dropList1_click1(GDropList source, GEvent event) { //_CODE_:taxiList:640156:
	  println("taxiList - GDropList >> GEvent." + event + " @ " + millis());
	} //_CODE_:taxiList:640156:

	public void textfield2_change1(GTextField source, GEvent event) { //_CODE_:pathStart:294212:
	  println("textfield2 - GTextField >> GEvent." + event + " @ " + millis());
	} //_CODE_:pathStart:294212:

	public void textfield3_change1(GTextField source, GEvent event) { //_CODE_:pathEnd:354994:
	  println("textfield3 - GTextField >> GEvent." + event + " @ " + millis());
	} //_CODE_:pathEnd:354994:

	public void button1_click2(GButton source, GEvent event) { //_CODE_:pathSearch:662089:
	  println("button1 - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:pathSearch:662089:

	public void button2_click2(GButton source, GEvent event) { //_CODE_:regionSelect:563953:
	  println("button2 - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:regionSelect:563953:

	public void button3_click1(GButton source, GEvent event) { //_CODE_:regionClear:624658:
	  println("button3 - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:regionClear:624658:

	public void textfield4_change1(GTextField source, GEvent event) { //_CODE_:stStart:277840:
	  println("textfield4 - GTextField >> GEvent." + event + " @ " + millis());
	} //_CODE_:stStart:277840:

	public void textfield5_change1(GTextField source, GEvent event) { //_CODE_:stEnd:723072:
	  println("textfield5 - GTextField >> GEvent." + event + " @ " + millis());
	} //_CODE_:stEnd:723072:

	public void button4_click1(GButton source, GEvent event) { //_CODE_:stSearch:818899:
	  println("button4 - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:stSearch:818899:

	public void textarea1_change1(GTextArea source, GEvent event) { //_CODE_:trajectoryList:490883:
	  println("trajectoryList - GTextArea >> GEvent." + event + " @ " + millis());
	} //_CODE_:trajectoryList:490883:

	public void button1_click3(GButton source, GEvent event) { //_CODE_:pickPathStart:960893:
	  println("pickPathStart - GButton >> GEvent." + event + " @ " + millis());
	  new DatePickerSample();
	} //_CODE_:pickPathStart:960893:

	public void button2_click3(GButton source, GEvent event) { //_CODE_:pickPathEnd:503254:
	  println("pickPathEnd - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:pickPathEnd:503254:

	public void button3_click2(GButton source, GEvent event) { //_CODE_:pckStStart:276985:
	  println("pckStStart - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:pckStStart:276985:

	public void button4_click2(GButton source, GEvent event) { //_CODE_:pckStEnd:578735:
	  println("pckStEnd - GButton >> GEvent." + event + " @ " + millis());
	} //_CODE_:pckStEnd:578735:



	// Create all the GUI controls. 
	// autogenerated do not edit
	public void createGUI(){
	  G4P.messagesEnabled(false);
	  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
	  G4P.setCursor(ARROW);
	  if(frame != null)
	    frame.setTitle("Sketch Window");
	  fileLabel = new GLabel(this, 29, 30, 80, 20);
	  fileLabel.setText("GPS Record");
	  fileLabel.setOpaque(false);
	  fileLocation = new GTextField(this, 128, 27, 224, 30, G4P.SCROLLBARS_NONE);
	  fileLocation.setOpaque(true);
	  fileLocation.addEventHandler(this, "textfield1_change1");
	  loadButton = new GButton(this, 373, 26, 80, 30);
	  loadButton.setText("Load");
	  loadButton.addEventHandler(this, "button1_click1");
	  clearButton = new GButton(this, 465, 26, 80, 30);
	  clearButton.setText("Clear");
	  clearButton.addEventHandler(this, "button2_click1");
	  label1 = new GLabel(this, 605, 86, 80, 20);
	  label1.setText("Path Query");
	  label1.setOpaque(false);
//	  taxiList = new GDropList(this, 714, 126, 90, 80, 3);
//	  taxiList.setItems(loadStrings("list_640156"), 0);
//	  taxiList.addEventHandler(this, "dropList1_click1");
	  label2 = new GLabel(this, 606, 126, 80, 20);
	  label2.setText("Taxi Id");
	  label2.setOpaque(false);
	  label3 = new GLabel(this, 607, 166, 80, 20);
	  label3.setText("Start Date");
	  label3.setOpaque(false);
	  label4 = new GLabel(this, 605, 212, 80, 20);
	  label4.setText("End Date");
	  label4.setOpaque(false);
	  pathStart = new GTextField(this, 713, 162, 160, 30, G4P.SCROLLBARS_NONE);
	  pathStart.setOpaque(true);
	  pathStart.addEventHandler(this, "textfield2_change1");
	  pathEnd = new GTextField(this, 714, 206, 160, 30, G4P.SCROLLBARS_NONE);
	  pathEnd.setOpaque(true);
	  pathEnd.addEventHandler(this, "textfield3_change1");
	  pathSearch = new GButton(this, 795, 258, 80, 30);
	  pathSearch.setText("Search");
	  pathSearch.addEventHandler(this, "button1_click2");
	  label5 = new GLabel(this, 601, 317, 127, 24);
	  label5.setText("Spatio-Temporal Query");
	  label5.setOpaque(false);
	  regionSelect = new GButton(this, 603, 359, 80, 30);
	  regionSelect.setText("Region");
	  regionSelect.addEventHandler(this, "button2_click2");
	  regionClear = new GButton(this, 708, 360, 80, 30);
	  regionClear.setText("Clear");
	  regionClear.addEventHandler(this, "button3_click1");
	  label6 = new GLabel(this, 604, 415, 80, 20);
	  label6.setText("Start Date");
	  label6.setOpaque(false);
	  stStart = new GTextField(this, 710, 411, 160, 30, G4P.SCROLLBARS_NONE);
	  stStart.setOpaque(true);
	  stStart.addEventHandler(this, "textfield4_change1");
	  label7 = new GLabel(this, 603, 462, 80, 20);
	  label7.setText("End Date");
	  label7.setOpaque(false);
	  stEnd = new GTextField(this, 710, 458, 160, 30, G4P.SCROLLBARS_NONE);
	  stEnd.setOpaque(true);
	  stEnd.addEventHandler(this, "textfield5_change1");
	  stSearch = new GButton(this, 792, 504, 80, 30);
	  stSearch.setText("Search");
	  stSearch.addEventHandler(this, "button4_click1");
	  trajectoryList = new GTextArea(this, 8, 507, 589, 80, G4P.SCROLLBARS_NONE);
	  trajectoryList.setOpaque(true);
	  trajectoryList.addEventHandler(this, "textarea1_change1");
	  pickPathStart = new GButton(this, 886, 160, 80, 30);
	  pickPathStart.setText("Pick Date");
	  pickPathStart.addEventHandler(this, "button1_click3");
	  pickPathEnd = new GButton(this, 886, 206, 80, 30);
	  pickPathEnd.setText("Pick Date");
	  pickPathEnd.addEventHandler(this, "button2_click3");
	  pckStStart = new GButton(this, 888, 410, 80, 30);
	  pckStStart.setText("Pick Date");
	  pckStStart.addEventHandler(this, "button3_click2");
	  pckStEnd = new GButton(this, 888, 460, 80, 30);
	  pckStEnd.setText("Pick Date");
	  pckStEnd.addEventHandler(this, "button4_click2");
	}

	// Variable declarations 
	// autogenerated do not edit
	GLabel fileLabel; 
	GTextField fileLocation; 
	GButton loadButton; 
	GButton clearButton; 
	GLabel label1; 
	GDropList taxiList; 
	GLabel label2; 
	GLabel label3; 
	GLabel label4; 
	GTextField pathStart; 
	GTextField pathEnd; 
	GButton pathSearch; 
	GLabel label5; 
	GButton regionSelect; 
	GButton regionClear; 
	GLabel label6; 
	GTextField stStart; 
	GLabel label7; 
	GTextField stEnd; 
	GButton stSearch; 
	GTextArea trajectoryList; 
	GButton pickPathStart; 
	GButton pickPathEnd; 
	GButton pckStStart; 
	GButton pckStEnd; 
}
