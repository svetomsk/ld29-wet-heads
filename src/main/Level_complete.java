package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;

class Level_complete extends JFrame
{
    private int width = 400, height = 400;
    private Image current;
    private int n;
    Level_complete(int n) throws IOException
    {
        this.n = n;
        setSize(width, height);
        setLayout(new FlowLayout());
        setUndecorated(true);
        setLocationRelativeTo(null);
        initializePicture();
        setListeners();
        
        setAlwaysOnTop(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
    	super.paint(g);
    	g.drawImage(current, 0, 0, width, height, this);
    }   
            
    private void initializePicture() throws IOException
    {       
        current = Pictures.complete_level[n-1];      
    }   
    
    void close()
    {
        setVisible(false);
    }
    
    private void setListeners()
    {
        this.addMouseListener(new MouseAdapter() 
        {

            @Override
            public void mouseClicked(MouseEvent e) 
            {
                close();
                Game.nextLevel();
            }            
        }
        );
        
    }
}