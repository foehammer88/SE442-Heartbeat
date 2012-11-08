
// Import the basic graphics classes.
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GraphPanel extends JPanel{

	private ArrayList<Integer> dataPoints;
	private String graphType;
	// Create a constructor method
	public GraphPanel(ArrayList<Integer> data, String type){
		super();
		dataPoints = data;
		graphType = type;
	}
	
	public GraphPanel(){
		super();
	}

	public void paintComponent(Graphics g){
		setGraph(g);
		if(dataPoints != null){
			drawGraph(g);
		}
	}

	public void setGraph(ArrayList<Integer> data, String type){
		dataPoints = data;
		graphType = type;
	}
	
	private void drawGraph(Graphics g) {
		// Origin (25,320)
		// Top (25, 40) 280
		// Right (425, 320)
		int xOffset = 25, yOffset = 320;
		int totalPoints = dataPoints.size();
		int dx = 400 / totalPoints;
		int x = 0, xPast = 0, xCoord = 0;
		int y, yPast = 0, yCoord;
		boolean firstRun = true;
		Graphics2D g2D = (Graphics2D)g;
		// Create a rotation transformation for the font.
		AffineTransform fontAT = new AffineTransform();
		// get the current font
		Font theFont = g2D.getFont();
		// Derive a new font using a rotatation transform
		fontAT.rotate(270 * java.lang.Math.PI/180);
		Font theDerivedFont = theFont.deriveFont(fontAT);
		// set the derived font in the Graphics2D context
		g2D.setFont(theDerivedFont);

		// Render a string using the derived font
		g2D.drawString(getYAxis(), 15, 200);

		// put the original font back
		g2D.setFont(theFont);
		g.drawString("time", 225, 332);
		
		for(Integer point : dataPoints){
			if(firstRun){
				y = (int) (((double)point.intValue() / (double)getMax()) * 280);
				yCoord = yOffset - y;
				xCoord = xOffset + x;
				g.drawOval(xCoord-2, yCoord-2, 4, 4);
				yPast = yCoord;
				xPast = xCoord;
				x += dx;
				firstRun = false;
			} else{
				y = (int) (((double)point.intValue() / (double)getMax()) * 280);
				yCoord = yOffset - y;
				xCoord = xOffset + x;
				//System.out.println("("+xPast+","+yPast+","+xCoord+","+yCoord+")");
				g.drawLine(xPast,yPast,xCoord,yCoord);
				g.drawOval(xCoord-2, yCoord-2, 4, 4);
				yPast = yCoord;
				xPast = xCoord;
				x += dx;
			}
			
		}
	}

	private void setGraph(Graphics g){
		//g.setColor(Color.WHITE);
		g.fillRect(0, 0, 450, 450);
		g.setColor(Color.WHITE);
		g.drawLine(25,40,25,320);
		g.drawLine(25,320,425,320);		
	}
	
	private int getMax(){
		if(graphType.equals("BP")){
			return 200;
		}else if(graphType.equals("HR")){
			return 200;
		}else if(graphType.equals("Temp")){
			return 120;
		}else{
			return 150;
		}
	}
	
	private String getYAxis(){
		if(graphType.equals("BP")){
			return "mmHg";
		}else if(graphType.equals("HR")){
			return "bpm";
		}else if(graphType.equals("Temp")){
			return "F";
		}else{
			return "bpm";
		}
	}
	public static void main(String arg[]){
		JFrame frame = new JFrame("BasicPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);

		GraphPanel panel = new GraphPanel();
		frame.setContentPane(panel);          
		frame.setVisible(true);                   
	}
}

