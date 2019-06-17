package login_gui;

import javax.swing.*;

import java.awt.*;

public class BackGroundPanel extends JPanel {  
	private static final long serialVersionUID = -5784465937954637463L;
	Image image;  
    public BackGroundPanel(Image image)  
    {  
        this.image=image;  
        this.setOpaque(true);  
    }  
    //Draw the back ground.  
    public void paintComponent(Graphics g)  
    {  
        super.paintComponents(g);  
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
    }  
} 
