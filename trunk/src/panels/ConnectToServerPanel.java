package panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectToServerPanel extends JPanel{
    private int width = 512;
    private int height = 64;
    private JTextField ip;
    private JLabel message;
    private JButton ok, cancel;
    
    public ConnectToServerPanel()
    {
        setPreferredSize(new Dimension(width, height));
        setLayout(new FlowLayout());
        setAlignmentX(CENTER_ALIGNMENT);

        createComponents();
        addComponents();
    }
    
    private void createComponents()
    {
        message = new JLabel("Enter your IP-address: ");
        
        ip = new JTextField(10);
        
        ok = new JButton("Connect");
        ok.setPreferredSize(new Dimension(90, 30));
        ok.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = ip.getText();
                ip.setText("");
                //connect action
            }
            
        });
        
        cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(90, 30));
        cancel.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                //cancel action
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
}
