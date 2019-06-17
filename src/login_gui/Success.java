package login_gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Success extends JFrame implements ActionListener {
	private static final long serialVersionUID = -5297780523307869602L;
	JButton btn_close;
	JLabel lb_imformation;
	ImageIcon head = new ImageIcon(getClass().getResource("/iconSuccess/img_logindefault_head.png"));
	JLabel lb_head;
	ImageIcon bang = new ImageIcon(getClass().getResource("/iconSuccess/img_sucess_button.png"));
	JLabel lb_bang;
	Container c;
	
	public Success () {
		btn_close = new JButton(new ImageIcon(getClass().getResource("/iconSuccess/bg_logindefault_close_button.png")));
		lb_imformation = new JLabel("文件已加密成功");
		lb_head = new JLabel(head);
		lb_bang = new JLabel(bang);
		c = this.getContentPane();
		
		this.setSize(381, 168);
		c.setBackground(new Color(253,253,253));
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
		btn_close.setContentAreaFilled(false);
		btn_close.setBorder(null);
		lb_imformation.setFont(new Font("微软雅黑",0, 16));
		
		btn_close.setBounds(new Rectangle(355, 4, 24, 24));
		lb_imformation.setBounds(0, 126, 381, 30);
		lb_imformation.setHorizontalAlignment(SwingConstants.CENTER);
		lb_head.setBounds(0, 0, 381, 24);
		lb_head.setOpaque(false);
		lb_bang.setBounds(165, 50, 50, 50);
		lb_bang.setOpaque(false);
		
		btn_close.addActionListener(this);
		
		this.getLayeredPane().add(btn_close, new Integer(30));
		this.getLayeredPane().add(lb_imformation, new Integer(20));
		this.getLayeredPane().add(lb_head, new Integer(10));
		this.getLayeredPane().add(lb_bang, new Integer(10));
		this.setVisible(true);
	}
	
	public Success (String string) {
		btn_close = new JButton(new ImageIcon(getClass().getResource("/iconSuccess/bg_logindefault_close_button.png")));
		lb_imformation = new JLabel(string);
		lb_head = new JLabel(head);
		lb_bang = new JLabel(bang);
		c = this.getContentPane();
		
		this.setSize(381, 168);
		c.setBackground(new Color(253,253,253));
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
		btn_close.setContentAreaFilled(false);
		btn_close.setBorder(null);
		lb_imformation.setFont(new Font("微软雅黑",0, 16));
		
		btn_close.setBounds(new Rectangle(355, 4, 24, 24));
		lb_imformation.setBounds(132, 126, 200, 30);
		lb_head.setBounds(0, 0, 381, 24);
		lb_head.setOpaque(false);
		lb_bang.setBounds(165, 50, 50, 50);
		lb_bang.setOpaque(false);
		
		btn_close.addActionListener(this);
		
		this.getLayeredPane().add(btn_close, new Integer(30));
		this.getLayeredPane().add(lb_imformation, new Integer(20));
		this.getLayeredPane().add(lb_head, new Integer(10));
		this.getLayeredPane().add(lb_bang, new Integer(10));
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == btn_close) {
			this.dispose();
		} 
	}
	
	public static void main(String[] args) {
		
	}
}
