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

public class MessageBox extends JFrame implements ActionListener {
	private static final long serialVersionUID = -5297780523307869602L;
	JButton btn_close;
	JButton btn_comfirm;
	JLabel lb_imformation;
	ImageIcon head = new ImageIcon(getClass().getResource("/iconforstudent/img_logindefault_head.png"));
	JLabel lb_head;
	ImageIcon bang = new ImageIcon(getClass().getResource("/iconforstudent/img_logindefault_decorate.png"));
	JLabel lb_bang;
	Container c;
	
	public MessageBox (String string) {
		btn_close = new JButton(new ImageIcon(getClass().getResource("/iconforstudent/bg_logindefault_close_button.png")));
		btn_comfirm = new JButton(new ImageIcon(getClass().getResource("/iconforstudent/bg_logindefault_confirm_button.jpg")));
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
		btn_comfirm.setBorder(null);
		btn_comfirm.setContentAreaFilled(false);
		btn_comfirm.setPressedIcon(new ImageIcon(getClass().getResource("/iconforstudent/bg_logindefault_confirm_button_pressed.jpg")));
		lb_imformation.setFont(new Font("微软雅黑",0, 14));
		
		btn_close.setBounds(new Rectangle(355, 4, 24, 24));
		btn_comfirm.setBounds(new Rectangle(170, 130, 65, 19));
		lb_imformation.setBounds(150, 55, 200, 30);
		lb_head.setBounds(0, 0, 381, 24);
		lb_head.setOpaque(false);
		lb_bang.setBounds(50, 50, 50, 50);
		lb_bang.setOpaque(false);
		
		btn_close.addActionListener(this);
		btn_comfirm.addActionListener(this);
		
		this.getLayeredPane().add(btn_close, new Integer(30));
		this.getLayeredPane().add(btn_comfirm, new Integer(30));
		this.getLayeredPane().add(lb_imformation, new Integer(20));
		this.getLayeredPane().add(lb_head, new Integer(10));
		this.getLayeredPane().add(lb_bang, new Integer(10));
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == btn_close) {
			this.dispose();
		} else if (source == btn_comfirm) {
			this.dispose();
		}
	}
	public static void main(String[] args) {
		new MessageBox("登录失败");
	}
}
