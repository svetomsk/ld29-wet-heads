package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Game;

public class StartServerPanel extends JPanel
{
	public StartServerPanel()
	{
    	super();
    	Dimension dim = new Dimension(Game.basicWIDTH, Game.basicHEIGHT);
    	setPreferredSize(dim);
    	
    	int countOfButtons = 3;
        int bheight = (int) (dim.getHeight()/(2*countOfButtons+1));
        int bwidth = (int) (dim.getWidth()/2);
        
        int gap = (int) (dim.getWidth()-bwidth);
        int range = (int) ((dim.getHeight() - countOfButtons * bheight)/(countOfButtons+1));
        
        setLayout(new FlowLayout(FlowLayout.CENTER, gap, range));
        
        Dimension button = new Dimension(bwidth, bheight);
        
        JLabel waiting = new JLabel("Waiting for connections...");
        waiting.setFont(new Font(null, Font.PLAIN, 22));
//        waiting.setPreferredSize(button);
        
    	add(waiting);
	}
	
}
