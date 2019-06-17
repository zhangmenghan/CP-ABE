package serverpage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cpabe.entity.AttributeSet;
import cpabe.entity.Staff;
import cpabe.entityDAO.AttributeSetDAO;
import cpabe.server.DemoServer;
import cpabe.service.AttrSetService;
import cpabe.service.StaffService;
import login_gui.Fault;
import login_gui.MessageBox;
import login_gui.Success;

public class InfoEntering extends JFrame implements ActionListener {
	String get_name = null;
	String get_staff_id = null;
	String get_jobage = null;
	String get_age = null;
	String get_password = null;
	String get_department = null;
	String get_job = null;

	String get_Attribute_1 = null;
	String get_Attribute_2 = null;
	String get_Attribute_3 = null;
	String get_Attribute_4 = null;
	String get_Attribute_5 = null;
	String get_Attribute_6 = null;
	String get_Attribute_7 = null;
	String get_Attribute_8 = null;

	JFrame frame;
	JTextField tex_name;
	JTextField tex_staff_id;
	JTextField tex_jobage;
	JTextField tex_age;
	JTextField tex_password;
	JTextField tex_department;
	JTextField tex_job;

	JButton btn_create_key;

	public InfoEntering() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setFont(new Font("微软雅黑", Font.BOLD, 30));
		frame.setBounds(400, 50, 500, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("ABE文档加密系统");
		frame.setIconImage(new ImageIcon(getClass().getResource("/iconforuser/title.png")).getImage());

		JLabel lbl_title = new JLabel("录入成员");
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 30));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(125, 0, 262, 49);
		frame.getContentPane().add(lbl_title);

		JLabel lbl_name = new JLabel("姓名");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_name.setBounds(0, 60, 80, 40);
		frame.getContentPane().add(lbl_name);

		tex_name = new JTextField();
		tex_name.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tex_name.setForeground(Color.BLACK);
		tex_name.setBounds(80, 60, 400, 40);
		frame.getContentPane().add(tex_name);
		tex_name.setColumns(10);

		JLabel lbl_account = new JLabel("工号");
		lbl_account.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_account.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_account.setBounds(0, 100, 80, 40);
		frame.getContentPane().add(lbl_account);

		tex_staff_id = new JTextField();
		tex_staff_id.setForeground(Color.BLACK);
		tex_staff_id.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tex_staff_id.setColumns(10);
		tex_staff_id.setBounds(80, 100, 400, 40);
		frame.getContentPane().add(tex_staff_id);

		JLabel lbl_jobage = new JLabel("工龄");
		lbl_jobage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_jobage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_jobage.setBounds(0, 140, 80, 40);
		frame.getContentPane().add(lbl_jobage);

		tex_jobage = new JTextField();
		tex_jobage.setForeground(Color.BLACK);
		tex_jobage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tex_jobage.setColumns(10);
		tex_jobage.setBounds(80, 140, 400, 40);
		frame.getContentPane().add(tex_jobage);

		JLabel lbl_birth = new JLabel("年龄");
		lbl_birth.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_birth.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_birth.setBounds(0, 180, 80, 40);
		frame.getContentPane().add(lbl_birth);

		tex_age = new JTextField();
		tex_age.setForeground(Color.BLACK);
		tex_age.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tex_age.setColumns(10);
		tex_age.setBounds(80, 180, 400, 40);
		frame.getContentPane().add(tex_age);

		JLabel lbl_sex = new JLabel("密码");
		lbl_sex.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sex.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_sex.setBounds(0, 220, 80, 40);
		frame.getContentPane().add(lbl_sex);

		tex_password = new JTextField();
		tex_password.setForeground(Color.BLACK);
		tex_password.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tex_password.setColumns(10);
		tex_password.setBounds(80, 220, 400, 40);
		frame.getContentPane().add(tex_password);

		JLabel lbl_department = new JLabel("部门");
		lbl_department.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_department.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_department.setBounds(0, 260, 80, 40);
		frame.getContentPane().add(lbl_department);

		tex_department = new JTextField();
		tex_department.setForeground(Color.BLACK);
		tex_department.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tex_department.setColumns(10);
		tex_department.setBounds(80, 260, 400, 40);
		frame.getContentPane().add(tex_department);

		JLabel lbl_job = new JLabel("职务");
		lbl_job.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_job.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbl_job.setBounds(0, 300, 80, 40);
		frame.getContentPane().add(lbl_job);

		tex_job = new JTextField();
		tex_job.setForeground(Color.BLACK);
		tex_job.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tex_job.setColumns(10);
		tex_job.setBounds(80, 300, 400, 40);
		frame.getContentPane().add(tex_job);

		//
		AttributeSetDAO dao = new AttributeSetDAO();
		AttributeSet attrSet = dao.getAttributeSet();

		final JCheckBox Attribute_1 = new JCheckBox(attrSet.getAttr_1());
		Attribute_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_1.setBackground(Color.WHITE);
		Attribute_1.setForeground(Color.BLACK);
		Attribute_1.setBounds(30, 360, 240, 40);
		frame.getContentPane().add(Attribute_1);

		final JCheckBox Attribute_2 = new JCheckBox(attrSet.getAttr_2());
		Attribute_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_2.setForeground(Color.BLACK);
		Attribute_2.setBackground(Color.WHITE);
		Attribute_2.setBounds(290, 360, 240, 40);
		frame.getContentPane().add(Attribute_2);

		final JCheckBox Attribute_3 = new JCheckBox(attrSet.getAttr_3());
		Attribute_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_3.setForeground(Color.BLACK);
		Attribute_3.setBackground(Color.WHITE);
		Attribute_3.setBounds(30, 400, 240, 40);
		frame.getContentPane().add(Attribute_3);

		final JCheckBox Attribute_4 = new JCheckBox(attrSet.getAttr_4());
		Attribute_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_4.setForeground(Color.BLACK);
		Attribute_4.setBackground(Color.WHITE);
		Attribute_4.setBounds(290, 400, 240, 40);
		frame.getContentPane().add(Attribute_4);

		final JCheckBox Attribute_5 = new JCheckBox(attrSet.getAttr_5());
		Attribute_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_5.setForeground(Color.BLACK);
		Attribute_5.setBackground(Color.WHITE);
		Attribute_5.setBounds(30, 440, 240, 40);
		frame.getContentPane().add(Attribute_5);

		final JCheckBox Attribute_6 = new JCheckBox(attrSet.getAttr_6());
		Attribute_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_6.setForeground(Color.BLACK);
		Attribute_6.setBackground(Color.WHITE);
		Attribute_6.setBounds(290, 440, 240, 40);
		frame.getContentPane().add(Attribute_6);

		final JCheckBox Attribute_7 = new JCheckBox(attrSet.getAttr_7());
		Attribute_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_7.setForeground(Color.BLACK);
		Attribute_7.setBackground(Color.WHITE);
		Attribute_7.setBounds(30, 480, 240, 40);
		frame.getContentPane().add(Attribute_7);

		final JCheckBox Attribute_8 = new JCheckBox(attrSet.getAttr_8());
		Attribute_8.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Attribute_8.setForeground(Color.BLACK);
		Attribute_8.setBackground(Color.WHITE);
		Attribute_8.setBounds(290, 480, 240, 40);
		frame.getContentPane().add(Attribute_8);

		btn_create_key = new JButton(new ImageIcon(getClass().getResource(
				"/iconforadmin/img_create_key_button.png")));
		btn_create_key.setForeground(Color.BLUE);
		btn_create_key.setBackground(Color.WHITE);
		btn_create_key.setFont(new Font("微软雅黑", Font.BOLD, 32));
		btn_create_key.setBounds(117, 550, 285, 52);
		frame.getContentPane().add(btn_create_key);

		frame.setVisible(true);

		btn_create_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				get_name = tex_name.getText();
				get_staff_id = tex_staff_id.getText();
				get_jobage = tex_jobage.getText();
				get_age = tex_age.getText();
				get_password = tex_password.getText();
				get_department = tex_department.getText();
				get_job = tex_job.getText();

				if (Attribute_1.isSelected()) {
					get_Attribute_1 = Attribute_1.getText();
				}
				if (Attribute_2.isSelected()) {
					get_Attribute_2 = Attribute_2.getText();
					if ((get_Attribute_1 == null)
							|| ("".equals(get_Attribute_1))) {
						get_Attribute_1 = get_Attribute_2;
						get_Attribute_2 = "";
					}
				}
				if (Attribute_3.isSelected()) {
					get_Attribute_3 = Attribute_3.getText();
					if ((get_Attribute_1 == null)
							|| ("".equals(get_Attribute_1))) {
						get_Attribute_1 = get_Attribute_3;
						get_Attribute_3 = "";
					} else if ((get_Attribute_2 == null)
							|| ("".equals(get_Attribute_2))) {
						get_Attribute_2 = get_Attribute_3;
						get_Attribute_3 = "";
					}
				}
				if (Attribute_4.isSelected()) {
					get_Attribute_4 = Attribute_4.getText();
					if ((get_Attribute_1 == null)
							|| ("".equals(get_Attribute_1))) {
						get_Attribute_1 = get_Attribute_4;
					} else if ((get_Attribute_2 == null)
							|| ("".equals(get_Attribute_2))) {
						get_Attribute_2 = get_Attribute_4;
					} else if ((get_Attribute_3 == null)
							|| ("".equals(get_Attribute_3))) {
						get_Attribute_3 = get_Attribute_4;
					} else {
						new Fault("职工属性数量不能超过三个！");
					}
				}
				if (Attribute_5.isSelected()) {
					get_Attribute_5 = Attribute_5.getText();
					if ((get_Attribute_1 == null)
							|| ("".equals(get_Attribute_1))) {
						get_Attribute_1 = get_Attribute_5;
					} else if ((get_Attribute_2 == null)
							|| ("".equals(get_Attribute_2))) {
						get_Attribute_2 = get_Attribute_5;
					} else if ((get_Attribute_3 == null)
							|| ("".equals(get_Attribute_3))) {
						get_Attribute_3 = get_Attribute_5;
					} else {
						new Fault("职工属性数量不能超过三个！");
					}
				}
				if (Attribute_6.isSelected()) {
					get_Attribute_6 = Attribute_6.getText();
					if ((get_Attribute_1 == null)
							|| ("".equals(get_Attribute_1))) {
						get_Attribute_1 = get_Attribute_6;
					} else if ((get_Attribute_2 == null)
							|| ("".equals(get_Attribute_2))) {
						get_Attribute_2 = get_Attribute_6;
					} else if ((get_Attribute_3 == null)
							|| ("".equals(get_Attribute_3))) {
						get_Attribute_3 = get_Attribute_6;
					} else {
						new Fault("职工属性数量不能超过三个！");
					}
				}
				if (Attribute_7.isSelected()) {
					get_Attribute_7 = Attribute_7.getText();
					if ((get_Attribute_1 == null)
							|| ("".equals(get_Attribute_1))) {
						get_Attribute_1 = get_Attribute_7;
					} else if ((get_Attribute_2 == null)
							|| ("".equals(get_Attribute_2))) {
						get_Attribute_2 = get_Attribute_7;
					} else if ((get_Attribute_3 == null)
							|| ("".equals(get_Attribute_3))) {
						get_Attribute_3 = get_Attribute_7;
					} else {
						new Fault("职工属性数量不能超过三个！");
					}
				}

				if (Attribute_8.isSelected()) {
					get_Attribute_8 = Attribute_8.getText();
					if ((get_Attribute_1 == null) || ("".equals(Attribute_1))) {
						get_Attribute_1 = get_Attribute_8;
					} else if ((get_Attribute_2 == null)
							|| ("".equals(Attribute_2))) {
						get_Attribute_2 = get_Attribute_8;
					} else if ((get_Attribute_3 == null)
							|| ("".equals(Attribute_3))) {
						get_Attribute_3 = get_Attribute_8;
					} else {
						new Fault("职工属性数量不能超过三个！");
					}
				}

				if (get_name.isEmpty()) {
					new MessageBox("请填写姓名");
				} else if (get_staff_id.isEmpty()) {
					new MessageBox("请填写工号");
				} else if (get_jobage.isEmpty()) {
					new MessageBox("请填写工龄");
				} else if (get_age.isEmpty()) {
					new MessageBox("请填写年龄");
				} else if (get_password.isEmpty()) {
					new MessageBox("请填写密码");
				} else if (get_department.isEmpty()) {
					new MessageBox("请填写部门");
				} else if (get_job.isEmpty()) {
					new MessageBox("请填写职务");
				} else {
					tex_name.setText(null);
					tex_staff_id.setText(null);
					tex_jobage.setText(null);
					tex_age.setText(null);
					tex_password.setText(null);
					tex_department.setText(null);
					tex_job.setText(null);

					Staff staff = new Staff();
					staff.setAttr_1(get_Attribute_1);
					staff.setAttr_2(get_Attribute_2);
					staff.setAttr_3(get_Attribute_3);
					staff.setDuty(get_job);
					staff.setDepartment(get_department);
					staff.setStaff_id(get_staff_id);
					staff.setWork_age(Integer.parseInt(get_jobage));
					staff.setAge(Integer.parseInt(get_age));
					staff.setName(get_name);
					try {
						get_password = new String(get_password.getBytes(),"UTF-8");
					} catch (UnsupportedEncodingException e2) {
						//Discard
					}
					staff.setPassword(get_password);

					StaffService service = new StaffService();
					try {
						service.addStaff(staff);
					} catch (Exception e1) {
						new Fault("职工信息录入失败！");
					}

					/**
					 * generate Private Key
					 * */
					String attr_str = "";
					if ((get_Attribute_1 != null)
							&& (!"".equals(get_Attribute_1))) {
						attr_str = attr_str + get_Attribute_1;
					}
					if ((get_Attribute_2 != null)
							&& (!"".equals(get_Attribute_2))) {
						attr_str = attr_str + " " + get_Attribute_2;
					}
					if ((get_Attribute_3 != null)
							&& (!"".equals(get_Attribute_3))) {
						attr_str = attr_str + " " + get_Attribute_3;
					}
					if (((get_Attribute_1 == null) || ("".equals(Attribute_1)))
							&& ((get_Attribute_2 == null) || (""
									.equals(Attribute_2)))
							&& ((get_Attribute_3 == null) || (""
									.equals(Attribute_3)))) {
						new Fault("未录入职工属性！请选择职工属性！");
					}

					System.out.println(attr_str);

					try {
						DemoServer.keygen(attr_str);
						new Success("请取走您的私钥，并删除将其！");
					} catch (Exception e1) {
						new Fault("职工私钥生成失败！");
					}
					frame.setVisible(false);
				}
			}
		});
	}

//	public static void main(String[] args) {
//		new InfoEntering();
//	}

	public void actionPerformed(ActionEvent arg0) {

	}
}
