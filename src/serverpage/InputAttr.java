package serverpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cpabe.entity.AttributeSet;
import cpabe.entityDAO.AttributeSetDAO;
import login_gui.MessageBox;

public class InputAttr extends JFrame implements ActionListener {
	String get_Attribute_1 = null;
	String get_Attribute_2 = null;
	String get_Attribute_3 = null;
	String get_Attribute_4 = null;
	String get_Attribute_5 = null;
	String get_Attribute_6 = null;
	String get_Attribute_7 = null;
	String get_Attribute_8 = null;
	
	
	JTextField Attribute_1;
	JTextField Attribute_2;
	JTextField Attribute_3;
	JTextField Attribute_4;
	JTextField Attribute_5;
	JTextField Attribute_6;
	JTextField Attribute_7;
	JTextField Attribute_8;
	
	JButton btn_Input_Attr;
	JFrame frame;
	
	JLabel tips;
	JLabel lbl_title;
	JLabel lbl_1;
	JLabel lbl_2;
	JLabel lbl_3;
	JLabel lbl_4;
	JLabel lbl_5;
	JLabel lbl_6;
	JLabel lbl_7;
	JLabel lbl_8;
	
	public InputAttr(){
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240,240,240));
		frame.getContentPane().setFont(new Font("微软雅黑", Font.BOLD, 30));
		frame.setBounds(500, 80, 400, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("ABE文档加密系统");
		frame.setIconImage(new ImageIcon(getClass().getResource("/iconforuser/title.png")).getImage());

		
		lbl_title = new JLabel("录入属性");
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(100, 0, 200, 50);
		frame.getContentPane().add(lbl_title);
		
		tips = new JLabel("请按次序 录入属性");
		tips.setFont(new Font("宋体 ", Font.PLAIN, 20));
		tips.setHorizontalAlignment(SwingConstants.LEFT);
		tips.setBounds(10, 60, 300, 40);
		frame.getContentPane().add(tips);
		
		
		
		lbl_1 = new JLabel("属性一");
		lbl_1.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_1.setBounds(0, 100, 100, 40);
		frame.getContentPane().add(lbl_1);
		
		Attribute_1 = new JTextField();
		Attribute_1.setForeground(Color.BLACK);
		Attribute_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_1.setBounds(100,103,280,34);
		Attribute_1.setBorder(null);
		frame.getContentPane().add(Attribute_1);
		
	
		
		 
		lbl_2 = new JLabel("属性二");
		lbl_2.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_2.setBounds(0, 140, 100, 32);
		frame.getContentPane().add(lbl_2);
		
		Attribute_2 = new JTextField();
		Attribute_2.setForeground(Color.BLACK);
		Attribute_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_2.setBounds(100,143,280,34);
		Attribute_2.setBorder(null);
		frame.getContentPane().add(Attribute_2);
		
		
		
		
		
		lbl_3 = new JLabel("属性三");
		lbl_3.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_3.setBounds(0, 180, 100, 40);
		frame.getContentPane().add(lbl_3);
		
		Attribute_3 = new JTextField();
		Attribute_3.setForeground(Color.BLACK);
		Attribute_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_3.setBounds(100,183,280,34);
		Attribute_3.setBorder(null);
		frame.getContentPane().add(Attribute_3);
		
		
		
		
		lbl_4 = new JLabel("属性四");
		lbl_4.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_4.setBounds(0, 220, 100, 40);
		frame.getContentPane().add(lbl_4);
		
		Attribute_4 = new JTextField();
		Attribute_4.setForeground(Color.BLACK);
		Attribute_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_4.setBounds(100,223,280,34);
		Attribute_4.setBorder(null);
		frame.getContentPane().add(Attribute_4);
		
		
		
		
		lbl_5 = new JLabel("属性五");
		lbl_5.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_5.setBounds(0, 260, 100, 40);
		frame.getContentPane().add(lbl_5);
		
		Attribute_5 = new JTextField();
		Attribute_5.setForeground(Color.BLACK);
		Attribute_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_5.setBounds(100,263,280,34);
		Attribute_5.setBorder(null);
		frame.getContentPane().add(Attribute_5);
		
		
		
		
		lbl_6 = new JLabel("属性六");
		lbl_6.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_6.setBounds(0, 300, 100, 40);
		frame.getContentPane().add(lbl_6);
		
		Attribute_6 = new JTextField();
		Attribute_6.setForeground(Color.BLACK);
		Attribute_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_6.setBounds(100,303,280,34);
		Attribute_6.setBorder(null);
		frame.getContentPane().add(Attribute_6);
		
		
		
		
		lbl_7 = new JLabel("属性七");
		lbl_7.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_7.setBounds(0, 340, 100, 40);
		frame.getContentPane().add(lbl_7);
		
		Attribute_7 = new JTextField();
		Attribute_7.setForeground(Color.BLACK);
		Attribute_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_7.setBounds(100,343,280,34);
		Attribute_7.setBorder(null);
		frame.getContentPane().add(Attribute_7);
		
		
		
		
		lbl_8 = new JLabel("属性八");
		lbl_8.setFont(new Font("宋体 ", Font.PLAIN, 20));
		lbl_8.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_8.setBounds(0, 380, 100, 40);
		frame.getContentPane().add(lbl_8);
		
		Attribute_8 = new JTextField();
		Attribute_8.setForeground(Color.BLACK);
		Attribute_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_8.setBounds(100,383,280,34);
		Attribute_8.setBorder(null);
		frame.getContentPane().add(Attribute_8);
		
		btn_Input_Attr = new JButton("确定录入");
		btn_Input_Attr.setForeground(Color.BLUE);
		btn_Input_Attr.setBackground(Color.WHITE);
		btn_Input_Attr.setFont(new Font("微软雅黑", Font.BOLD, 32));
		btn_Input_Attr.setBounds(100, 450, 200, 50);
		frame.getContentPane().add(btn_Input_Attr);
		
		btn_Input_Attr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				get_Attribute_1 = Attribute_1.getText();
				get_Attribute_2 = Attribute_2.getText();
				get_Attribute_3 = Attribute_3.getText();
				get_Attribute_4 = Attribute_4.getText();
				get_Attribute_5 = Attribute_5.getText();
				get_Attribute_6 = Attribute_6.getText();
				get_Attribute_7 = Attribute_7.getText();
				get_Attribute_8 = Attribute_8.getText();
				
				
				AttributeSet attrSet = new AttributeSet();
				attrSet.setAttr_1(get_Attribute_1);
				attrSet.setAttr_2(get_Attribute_2);
				attrSet.setAttr_3(get_Attribute_3);
				attrSet.setAttr_4(get_Attribute_4);
				attrSet.setAttr_5(get_Attribute_5);
				attrSet.setAttr_6(get_Attribute_6);
				attrSet.setAttr_7(get_Attribute_7);
				attrSet.setAttr_8(get_Attribute_8);
				
				AttributeSetDAO dao = new AttributeSetDAO();
				dao.save(attrSet);
				frame.setVisible(false);
			}
		});
		
		
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		
	}
	
//	
//	public static void main(String[] args) {
//		new InputAttr();
//	}

}
