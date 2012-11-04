
// Import the basic graphics classes.
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

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
		
		for(Integer point : dataPoints){
			if(firstRun){
				y = (int) (((double)point.intValue() / (double)getMax()) * 280);
				yCoord = yOffset - y;
				xCoord = xOffset + x;
				g.drawOval(xCoord, yCoord, 3, 3);
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
				g.drawOval(xCoord, yCoord, 3, 3);
				yPast = yCoord;
				xPast = xCoord;
				x += dx;
			}
			
		}
	}

	private void setGraph(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 450, 450);
		g.setColor(Color.BLACK);
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
	
	public static void main(String arg[]){
		JFrame frame = new JFrame("BasicPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);

		GraphPanel panel = new GraphPanel();
		frame.setContentPane(panel);          
		frame.setVisible(true);                   
	}
}

