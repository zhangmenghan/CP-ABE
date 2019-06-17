package login_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import mainpageuser.MainPageUser;

public class AreaField extends JFrame implements ActionListener {
	JPanel pn_panel;
	JTextArea output;
	JButton btn_close;
	JButton btn_minisize;
	BackGroundPanel bg;

	
	public AreaField(String string) {
		setResizable(false);//窗体不能最大化
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 300, 400, 200);
		pn_panel = new JPanel();
		setContentPane(pn_panel);
		pn_panel.setLayout(null);
		this.setUndecorated(true);
		this.setIconImage(new ImageIcon(getClass().getResource("/iconforuser/title.png")).getImage());

		bg = new BackGroundPanel((new ImageIcon(getClass().getResource("/iconforuser/new_head.png"))).getImage());
		bg.setBounds(0, 0, 400, 30);
		bg.setOpaque(false);
		
		output = new JTextArea(string);
		output.setEnabled(false);
		output.setEditable(false);
		output.setLineWrap(true);
		output.setBounds(0, 30, 400, 170);
		output.setFont(new Font("微软雅黑",0, 22));
		
		btn_close = new JButton();
		btn_minisize = new JButton();
		btn_close.setBorder(null);
		btn_close.setContentAreaFilled(false);
		btn_close.setIcon(new ImageIcon(LogIn.class.getResource("/iconforuser/bg_login_close_button.png")));
		btn_minisize.setContentAreaFilled(false);
		btn_minisize.setBorder(null);
		btn_minisize.setIcon(new ImageIcon(LogIn.class.getResource("/iconforuser/bg_login_minimize_button.png")));
		
		btn_close.setBounds(new Rectangle(370, 0, 30, 30));
		btn_minisize.setBounds(new Rectangle(340, 0, 30, 30));
		
		
		btn_close.addActionListener(this);
		btn_minisize.addActionListener(this);
		
		addIt(btn_minisize,23);
		addIt(btn_close,23);
		addIt(bg,1);
		pn_panel.add(output);
		this.setVisible(true);
		
		
		
	}

	void addIt(Component item, int n) {
		this.getContentPane().add(item, new Integer(n));
	}
		
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == btn_close) {
				this.setVisible(false);
			} else if (source == btn_minisize) {
				this.setExtendedState(ICONIFIED);
			}
		}

		public static void main(String[] args) {
			new AreaField("stringstringstringstringstringstringstringstringstringstringstring");
		}
	}


