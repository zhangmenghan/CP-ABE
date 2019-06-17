package mainpageuser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sun.misc.Cleaner;
import login_gui.Fault;
import login_gui.MessageBox;
import login_gui.Success;
import cpabe.client.Client;
import cpabe.client.DemoClient;
import cpabe.entity.AttributeSet;
import cpabe.file.FileOperator;
import cpabe.server.DemoServer;

public class Encryption implements ActionListener {
	int count = 0;
	String selectedEncryptFilePath;
	String selectedEncryptFileName;

	String rationality[][] = new String[3][8];
	int index = 0;
	int num = 0;
	int num1 = 0;
	int num2 = 0;
	int num3 = 0;

	String first_piles;
	String second_piles;
	String third_piles;

	String Str;
	String policyStr;

	JPanel pn_panel;

	JTextField tex_show1;
	JTextField tex_select_file;
	JTextField tex_number;
	JTextField tex_show2;

	JButton btnA;
	JButton btnB;
	JButton btnC;
	JButton btnD;
	JButton btnE;
	JButton btnF;
	JButton btnG;
	JButton btnH;
	JButton en_for_sure;
	JButton final_en_for_sure;
	JButton en_select_file;
	JButton en_reset;

	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	JButton btn5;
	JButton btn6;
	JButton en_continue_select;
	JButton en_forsure;

	JLabel labA;
	JLabel labB;
	JLabel labC;
	JLabel labD;
	JLabel labE;
	JLabel labF;
	JLabel labG;
	JLabel labH;
	
	AttributeSet attrSet = null;
	AttributeSet attrAfterSplit = null;
	public Encryption() {
	
		attrSet = FileOperator.loadAttrProp("./fileTemp/attribute.properties");
		attrAfterSplit = FileOperator.splitAttribute(attrSet);	
		
		pn_panel = new JPanel();

		tex_show1 = new JTextField();
		tex_select_file = new JTextField();
		tex_number = new JTextField();
		tex_show2 = new JTextField();

		btn1 = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_1_button.png")));
		btn2 = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_2_button.png")));
		btn3 = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_3_button.png")));
		btn4 = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_4_button.png")));
		btn5 = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_5_button.png")));
		btn6 = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_6_button.png")));
		en_continue_select = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_reselect_btn.png")));
		en_forsure = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_foresure_button.png")));

		
		
		
		btnA = new JButton(attrAfterSplit.getAttr_1());		
		btnA.setForeground(Color.WHITE);
		btnA.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnA.setBackground(new Color(43,86,154));
		btnB = new JButton(attrAfterSplit.getAttr_2());
		btnB.setForeground(Color.WHITE);
		btnB.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnB.setBackground(new Color(43,86,154));
		btnC = new JButton(attrAfterSplit.getAttr_3());
		btnC.setForeground(Color.WHITE);
		btnC.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnC.setBackground(new Color(43,86,154));
		btnD = new JButton(attrAfterSplit.getAttr_4());
		btnD.setForeground(Color.WHITE);
		btnD.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnD.setBackground(new Color(43,86,154));
		btnE = new JButton(attrAfterSplit.getAttr_5());
		btnE.setForeground(Color.WHITE);
		btnE.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnE.setBackground(new Color(43,86,154));
		btnF = new JButton(attrAfterSplit.getAttr_6());
		btnF.setForeground(Color.WHITE);
		btnF.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnF.setBackground(new Color(43,86,154));
		btnG = new JButton(attrAfterSplit.getAttr_7());
		btnG.setForeground(Color.WHITE);
		btnG.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnG.setBackground(new Color(43,86,154));
		btnH = new JButton(attrAfterSplit.getAttr_8());
		btnH.setForeground(Color.WHITE);
		btnH.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnH.setBackground(new Color(43,86,154));
		en_for_sure = new JButton(new ImageIcon(getClass().getResource(
				"/iconforuser/img_foresure_button.png")));
		final_en_for_sure = new JButton(new ImageIcon(getClass().getResource(
				"/iconforuser/img_lock_forsure.png")));
		en_reset = new JButton(new ImageIcon(getClass().getResource(
				"/iconforuser/img_relock_button.png")));
		en_select_file = new JButton(new ImageIcon(getClass().getResource(
				"/iconforuser/img_select_button.png"))); 

		labA = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));
		labB = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));
		labC = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));
		labD = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));
		labE = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));
		labF = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));
		labG = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));
		labH = new JLabel(new ImageIcon(getClass().getResource("/iconforuser/img_already_select_lbl.png")));

		tex_show1.setFont(new Font("宋体", Font.PLAIN, 16));
		tex_show1.setForeground(Color.RED);
		tex_show1.setBackground(Color.WHITE);
		tex_show1.setEnabled(false);
		tex_show1.setHorizontalAlignment(SwingConstants.LEFT);
		tex_show1.setText("请选择第一层加密的属性");
		tex_show1.setBounds(80, 50, 500, 30);

		tex_number = new JTextField();
		tex_number.setEnabled(false);
		tex_number.setFont(new Font("宋体", Font.PLAIN, 16));
		tex_number.setText("请选择本层加密的数量");
		tex_number.setBounds(30, 460, 200, 32);
		tex_number.setVisible(false);

		tex_show2 = new JTextField();
		tex_show2.setEnabled(false);
		tex_show2.setFont(new Font("宋体", Font.PLAIN, 16));
		tex_show2.setBounds(100, 520, 284, 37);
		tex_show2.setVisible(false);

		tex_select_file.setText("请选择需要加密的文件");
		tex_select_file.setBounds(80, 100, 500, 30);
		en_select_file.setBounds(580, 100, 90, 30);

		pn_panel.setBackground(new Color(234, 234, 234));
		pn_panel.setLayout(null);

		btnA.setBounds(220, 200, 90, 30);
		btnB.setBounds(360, 200, 90, 30);
		btnC.setBounds(220, 270, 90, 30);
		btnD.setBounds(360, 270, 90, 30);
		btnE.setBounds(220, 340, 90, 30);
		btnF.setBounds(360, 340, 90, 30);
		btnG.setBounds(220, 410, 90, 30);
		btnH.setBounds(360, 410, 90, 30);
		en_reset.setBounds(520, 200, 90, 30);
		en_for_sure.setBounds(520, 410, 90, 30);
		final_en_for_sure.setBounds(80, 200, 90, 30);

		final_en_for_sure.setVisible(false);

		labA.setBounds(220, 200, 90, 30);
		labB.setBounds(360, 200, 90, 30);
		labC.setBounds(220, 270, 90, 30);
		labD.setBounds(360, 270, 90, 30);
		labE.setBounds(220, 340, 90, 30);
		labF.setBounds(360, 340, 90, 30);
		labG.setBounds(220, 410, 90, 30);
		labH.setBounds(360, 410, 90, 30);

		btn1.setBounds(100, 500, 90, 30);
		btn2.setBounds(240, 500, 90, 30);
		btn3.setBounds(380, 500, 90, 30);
		btn4.setBounds(100, 560, 90, 30);
		btn5.setBounds(240, 560, 90, 30);
		btn6.setBounds(380, 560, 90, 30);
		en_continue_select.setBounds(540, 500, 90, 30);
		en_forsure.setBounds(540, 560, 90, 30);

		labA.setVisible(false);
		labB.setVisible(false);
		labC.setVisible(false);
		labD.setVisible(false);
		labE.setVisible(false);
		labF.setVisible(false);
		labG.setVisible(false);
		labH.setVisible(false);
		btnA.setVisible(false);
		btnB.setVisible(false);
		btnC.setVisible(false);
		btnD.setVisible(false);
		btnE.setVisible(false);
		btnF.setVisible(false);
		btnG.setVisible(false);
		btnH.setVisible(false);
		en_for_sure.setVisible(false);
		en_reset.setVisible(false);
		btn1.setVisible(false);
		btn2.setVisible(false);
		btn3.setVisible(false);
		btn4.setVisible(false);
		btn5.setVisible(false);
		btn6.setVisible(false);
		en_continue_select.setVisible(false);
		en_forsure.setVisible(false);

		pn_panel.add(btnA);
		pn_panel.add(btnB);
		pn_panel.add(btnC);
		pn_panel.add(btnD);
		pn_panel.add(btnE);
		pn_panel.add(btnF);
		pn_panel.add(btnG);
		pn_panel.add(btnH);
		pn_panel.add(btn1);
		pn_panel.add(btn2);
		pn_panel.add(btn3);
		pn_panel.add(btn4);
		pn_panel.add(btn5);
		pn_panel.add(btn6);
		pn_panel.add(en_continue_select);
		pn_panel.add(en_forsure);
		pn_panel.add(en_reset);
		pn_panel.add(en_for_sure);
		pn_panel.add(final_en_for_sure);
		pn_panel.add(tex_show1);
		pn_panel.add(tex_select_file);
		pn_panel.add(en_select_file);
		pn_panel.add(tex_number);
		pn_panel.add(tex_show2);
		pn_panel.add(labA);
		pn_panel.add(labB);
		pn_panel.add(labC);
		pn_panel.add(labD);
		pn_panel.add(labE);
		pn_panel.add(labF);
		pn_panel.add(labG);
		pn_panel.add(labH);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == btn1) {
			en_forsure.setVisible(true);

			if (index == 1) {
				first_piles = "1";
				tex_show2
						.setText("第" + (index) + "已选择" + (first_piles) + "个属性");
			} else if (index == 2) {
				second_piles = "1";
				tex_show2.setText("第" + (index) + "已选择" + (second_piles)
						+ "个属性");
			} else if (index == 3) {
				third_piles = "1";
				tex_show2
						.setText("第" + (index) + "已选择" + (third_piles) + "个属性");
			}

			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			tex_show2.setVisible(true);
		} else if (source == btn2) {
			en_forsure.setVisible(true);

			if (index == 1) {
				first_piles = "2";
				tex_show2.setText("第" + (index) + "层已选择" + (first_piles)
						+ "个属性");
			} else if (index == 2) {
				second_piles = "2";
				tex_show2.setText("第" + (index) + "层已选择" + (second_piles)
						+ "个属性");
			} else if (index == 3) {
				third_piles = "2";
				tex_show2.setText("第" + (index) + "层已选择" + (third_piles)
						+ "个属性");
			}

			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			tex_show2.setVisible(true);
		} else if (source == btn3) {
			en_forsure.setVisible(true);

			if (index == 1) {
				first_piles = "3";
				tex_show2.setText("第" + (index) + "层已选择" + (first_piles)
						+ "个属性");
			} else if (index == 2) {
				second_piles = "3";
				tex_show2.setText("第" + (index) + "层已选择" + (second_piles)
						+ "个属性");
			} else if (index == 3) {
				third_piles = "3";
				tex_show2.setText("第" + (index) + "层已选择" + (third_piles)
						+ "个属性");
			}

			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);

			tex_show2.setVisible(true);
		} else if (source == btn4) {
			en_forsure.setVisible(true);

			if (index == 1) {
				first_piles = "4";
				tex_show2.setText("第" + (index) + "层已选择" + (first_piles)
						+ "个属性");
			} else if (index == 2) {
				second_piles = "4";
				tex_show2.setText("第" + (index) + "层已选择" + (second_piles)
						+ "个属性");
			} else if (index == 3) {
				third_piles = "4";
				tex_show2.setText("第" + (index) + "层已选择" + (third_piles)
						+ "个属性");
			}

			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			tex_show2.setVisible(true);
		} else if (source == btn5) {
			en_forsure.setVisible(true);

			if (index == 1) {
				first_piles = "5";
				tex_show2.setText("第" + (index) + "层已选择" + (first_piles)
						+ "个属性");
			} else if (index == 2) {
				second_piles = "5";
				tex_show2.setText("第" + (index) + "层已选择" + (second_piles)
						+ "个属性");
			} else if (index == 3) {
				third_piles = "5";
				tex_show2.setText("第" + (index) + "层已选择" + (third_piles)
						+ "个属性");
			}

			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			tex_show2.setVisible(true);
		} else if (source == btn6) {
			en_forsure.setVisible(true);

			if (index == 1) {
				first_piles = "6";
				tex_show2.setText("第" + (index) + "层已选择" + (first_piles)
						+ "个属性");
			} else if (index == 2) {
				second_piles = "6";
				tex_show2.setText("第" + (index) + "层已选择" + (second_piles)
						+ "个属性");
			} else if (index == 3) {
				third_piles = "6";
				tex_show2.setText("第" + (index) + "层已选择" + (third_piles)
						+ "个属性");
			}

			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			tex_show2.setVisible(true);
		} else if (source == btnA) {
			if(count == 0){
				rationality[index][num] = attrSet.getAttr_1();
				num++;
				btnA.setVisible(false);
				labA.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == btnB) {
			if(count == 0){
				rationality[index][num] = attrSet.getAttr_2();
				num++;
				btnB.setVisible(false);
				labB.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == btnC) {
			if(count == 0){
				rationality[index][num] = attrSet.getAttr_3();
				num++;
				btnC.setVisible(false);
				labC.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == btnD) {
			if(count == 0){
				rationality[index][num] = attrSet.getAttr_4();
				num++;
				btnD.setVisible(false);
				labD.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == btnE) {
			if(count == 0){
				rationality[index][num] = attrSet.getAttr_5();
				num++;
				btnE.setVisible(false);
				labE.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == btnF) {
			if(count == 0){
				rationality[index][num] = attrSet.getAttr_6();
				num++;
				btnF.setVisible(false);
				labF.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == btnG) {
			if(count == 0){
				rationality[index][num] = attrSet.getAttr_7();
				num++;
				btnG.setVisible(false);
				labG.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == btnH) {
			if(count ==0){
				rationality[index][num] = attrSet.getAttr_8();
				num++;
				btnH.setVisible(false);
				labH.setVisible(true);
				en_for_sure.setVisible(true);
				en_reset.setVisible(true);
				final_en_for_sure.setVisible(false);
			}
			
		} else if (source == en_reset) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					rationality[index][num] = null;
				}
			}
			num = index = num1 = num2 = num3 = 0;
			Str = null;

			
			btnA.setVisible(true);
			btnB.setVisible(true);
			btnC.setVisible(true);
			btnD.setVisible(true);
			btnE.setVisible(true);
			btnF.setVisible(true);
			btnG.setVisible(true);
			btnH.setVisible(true);

			tex_number.setVisible(false);
			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			en_continue_select.setVisible(false);
			en_forsure.setVisible(false);
			final_en_for_sure.setVisible(false);

			tex_show1.setText("请选择第一层加密的属性");
			
			
		} else if (source == en_for_sure) {
			index++;

			if (index == 1) {
				num1 = num;
				for (int i = 0; i < num1; i++) {
					Str = Str + rationality[index - 1][i] + " ";
				}
				tex_show1.setText("第" + (index) + "层已加密，请选择第" + (index + 1)
						+ "的属性");

				if (num1 == 1) {
					btn1.setVisible(true);
				} else if (num1 == 2) {
					btn1.setVisible(true);
					btn2.setVisible(true);
				} else if (num1 == 3) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
				} else if (num1 == 4) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
				} else if (num1 == 5) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
				} else if (num1 == 6) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
					btn6.setVisible(true);
				}

			} else if (index == 2) {
				num2 = num;
				for (int i = 0; i < num2; i++) {
					Str = Str + " " + rationality[index - 1][i];
				}
				tex_show1.setText("第" + (index) + "层已加密，请选择第" + (index + 1)
						+ "的属性");

				if (num2 == 1) {
					btn1.setVisible(true);
					btn2.setVisible(true);
				} else if (num2 == 2) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
				} else if (num2 == 3) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
				} else if (num2 == 4) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
				} else if (num2 == 5) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
					btn6.setVisible(true);
				}

			} else if (index >= 3) {
				num3 = num;
				for (int i = 0; i < num; i++) {
					Str = Str + " " + rationality[index - 1][i];
				}
				tex_show1.setText("第" + (index) + "层加密已完成");

				if (index == 3) {
					if (num3 == 1) {
						btn1.setVisible(true);
						btn2.setVisible(true);
					} else if (num3 == 2) {
						btn1.setVisible(true);
						btn2.setVisible(true);
						btn3.setVisible(true);
					} else if (num3 == 3) {
						btn1.setVisible(true);
						btn2.setVisible(true);
						btn3.setVisible(true);
						btn4.setVisible(true);
					} else if (num3 == 4) {
						btn1.setVisible(true);
						btn2.setVisible(true);
						btn3.setVisible(true);
						btn4.setVisible(true);
						btn5.setVisible(true);
					} else if (num3 == 5) {
						btn1.setVisible(true);
						btn2.setVisible(true);
						btn3.setVisible(true);
						btn4.setVisible(true);
						btn5.setVisible(true);
						btn6.setVisible(true);
					}
				}
				en_for_sure.setVisible(false);
			}

			count = 1;
			num = 0;

			tex_number.setVisible(true);
			en_continue_select.setVisible(true);
			en_for_sure.setVisible(false);

		} else if (source == final_en_for_sure) {
			policyStr = Str.substring(4);
			System.out.println(policyStr);

			String clientFileName = FileOperator.createFileNameInClient(
					selectedEncryptFileName, Client.STAFF_ID);

			try {
				File dir = new File(DemoClient.clientCiphertextDir);
				if(!dir.exists()){
					dir.mkdirs();
				}
				DemoClient.encryptFile(selectedEncryptFilePath,
						DemoClient.clientCiphertextDir + clientFileName,
						policyStr);
				FileOperator.storePolicyToPropFile(
						DemoClient.policyPropertiesFilePath, clientFileName,
						policyStr);
				new Success("文件加密成功！");
			} catch (Exception e1) {
				new Fault("文件加密失败！");
				//Discard
			}
			
			num = index = num1 = num2 = num3 = 0;
			Str = null;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					rationality[i][j] = null;
				}
			}

			btnA.setVisible(true);
			btnB.setVisible(true);
			btnC.setVisible(true);
			btnD.setVisible(true);
			btnE.setVisible(true);
			btnF.setVisible(true);
			btnG.setVisible(true);
			btnH.setVisible(true);

			tex_number.setVisible(false);
			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			en_continue_select.setVisible(false);
			en_forsure.setVisible(false);
			final_en_for_sure.setVisible(false);

			tex_show1.setText("请选择第一层加密的属性");

		} else if (source == en_select_file) {
			JFileChooser fDialog = new JFileChooser();
			fDialog.setDialogTitle("请选择需要加密的文件");
			int returnVal = fDialog.showOpenDialog(null);
			if (JFileChooser.APPROVE_OPTION == returnVal) {

				selectedEncryptFilePath = fDialog.getSelectedFile().getPath();
				DemoClient.storeCiphertextPath = selectedEncryptFilePath
						.replaceAll("\\\\", "/");
				selectedEncryptFileName = fDialog.getSelectedFile().getName();
				tex_select_file.setText(selectedEncryptFilePath);
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					rationality[index][num] = null;
				}
			}
			num = index = num1 = num2 = num3 = 0;
			Str = null;

			btnA.setVisible(true);
			btnB.setVisible(true);
			btnC.setVisible(true);
			btnD.setVisible(true);
			btnE.setVisible(true);
			btnF.setVisible(true);
			btnG.setVisible(true);
			btnH.setVisible(true);
			en_for_sure.setVisible(true);
			en_reset.setVisible(true);

			tex_number.setVisible(false);
			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			en_continue_select.setVisible(false);
			en_forsure.setVisible(false);
			final_en_for_sure.setVisible(false);

			tex_show1.setText("请选择第一层加密的属性");

		} else if (source == en_continue_select) {
			en_forsure.setVisible(false);
			tex_show2.setVisible(false);

			if (index == 1) {
				first_piles = null;

				if (num1 == 1) {
					btn1.setVisible(true);
				} else if (num1 == 2) {
					btn1.setVisible(true);
					btn2.setVisible(true);
				} else if (num1 == 3) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
				} else if (num1 == 4) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
				} else if (num1 == 5) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
				} else if (num1 == 6) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
					btn6.setVisible(true);
				}
			} else if (index == 2) {
				if (num2 == 1) {
					btn1.setVisible(true);
					btn2.setVisible(true);
				} else if (num2 == 2) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
				} else if (num2 == 3) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
				} else if (num2 == 4) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
				} else if (num2 == 5) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
					btn6.setVisible(true);
				}

				second_piles = null;
			} else if (index == 3) {
				if (num3 == 1) {
					btn1.setVisible(true);
					btn2.setVisible(true);
				} else if (num3 == 2) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
				} else if (num3 == 3) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
				} else if (num3 == 4) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
				} else if (num3 == 5) {
					btn1.setVisible(true);
					btn2.setVisible(true);
					btn3.setVisible(true);
					btn4.setVisible(true);
					btn5.setVisible(true);
					btn6.setVisible(true);
				}

				third_piles = null;
			}
		} else if (source == en_forsure) {
			count = 0;
			tex_number.setVisible(false);
			btn1.setVisible(false);
			btn2.setVisible(false);
			btn3.setVisible(false);
			btn4.setVisible(false);
			btn5.setVisible(false);
			btn6.setVisible(false);
			en_continue_select.setVisible(false);
			en_forsure.setVisible(false);
			tex_show2.setVisible(false);

			if (index == 1) {
				en_for_sure.setVisible(true);
				Str = Str + first_piles + "of" + num1;
			} else if (index == 2) {
				en_for_sure.setVisible(true);
				Str = Str + " " + second_piles + "of" + (num2 + 1);
			} else if (index == 3) {
				en_for_sure.setVisible(false);
				Str = Str + " " + third_piles + "of" + (num3 + 1);
			}

			final_en_for_sure.setVisible(true);
		}
	}

	public static void main(String[] args) {
		new Encryption();
	}
}
