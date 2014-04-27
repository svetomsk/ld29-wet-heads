package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Game;

public class ConnectToServerPanel extends JPanel{
    private int width = 400;
    private int height = 640;
    private JTextField ip;
    private JLabel message;
    private JButton ok, cancel;
    
    public ConnectToServerPanel()
    {
        setPreferredSize(new Dimension(width, height));        
        setLayout(new FlowLayout());        
        createComponents();
        addComponents();
        addKeyListener(new KeyListener()
		{
			
			@Override
			public void keyTyped(KeyEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == e.VK_ESCAPE)
				{
					quit();
				}
			}
		});
    }
    
    private void createComponents()
    {
        message = new JLabel("Enter server's IP-address: ");
        
        ip = new JTextField(10);
        
        ok = new JButton("Connect");
        ok.setPreferredSize(new Dimension(90, 30));
        ok.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String s = ip.getText();
                ip.setText("");
                
                if(Game.connectToIP(s)) Game.startGameAsClient();
                
                //TODO connect action
                
            }
            
        });
        
        cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(90, 30));
        cancel.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	quit();
            }
            
        });
    }
    
    private void addComponents()
    {
        add(message);
        add(ip);
        add(ok);
        add(cancel);
    }
    private void quit()
    {
    	Game.toMainMenu();
    }
}
