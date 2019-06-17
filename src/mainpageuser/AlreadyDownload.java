package mainpageuser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import login_gui.Fault;
import login_gui.Success;
import cpabe.client.Client;
import cpabe.client.DemoClient;
import cpabe.file.FileOperator;
import cpabe.server.DemoServer;
import login_gui.*;

public class AlreadyDownload extends JFrame {
	String name;
	File root;
	String PATH;
	static File[] newfiles = null;
	
	JPanel pn_panel;
	static DefaultMutableTreeNode node = new DefaultMutableTreeNode();
	static DefaultMutableTreeNode subNode = new DefaultMutableTreeNode();
	static JTree tree;
	JScrollPane scroll;
	JButton unlock;
	JButton refresh;
	JButton Select_prikey;
	JTextField showprikey;

	public AlreadyDownload () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pn_panel = new JPanel();
		pn_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pn_panel);
		pn_panel.setLayout(null);

		loadingTree(new File(DemoClient.serverCiphertextDir),node);

		tree = new JTree(node);
		tree.setBounds(10, 10, 760, 500);
		tree.setFont(new Font("宋体", Font.PLAIN, 16));


		scroll = new JScrollPane(tree);
		scroll.setBounds(10, 10, 760, 500);
		pn_panel.add(scroll);
		
		Select_prikey = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_select_prikey_btn.png")));
		Select_prikey.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fDialog = new JFileChooser();              
				fDialog.setDialogTitle("请选择私钥");               
				int returnVal = fDialog.showOpenDialog(null);           
				if(JFileChooser.APPROVE_OPTION == returnVal){          
					PATH = fDialog.getSelectedFile().getPath();
					showprikey.setText(PATH);
					DemoClient.prvfile=PATH;
				}
			}
		});
		
		Select_prikey.setBounds(600, 540, 90, 30);
		pn_panel.add(Select_prikey);
		
		showprikey = new JTextField("请选择私钥");
		showprikey.setEditable(true);
//		showprikey.setText("请选择私钥");
		showprikey.setBounds( 80, 540, 520, 30);
		pn_panel.add(showprikey);
		
		unlock = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_unlock_button.png")));
		unlock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String shoe = showprikey.getText();
				if(shoe.equals("请选择私钥")){
					new MessageBox("请先选择私钥");
				}else{
					File dir = new File(DemoClient.serverCiphertextDir);
					if(!dir.exists()){
						dir.mkdirs();
					}
					String encfile = DemoClient.serverCiphertextDir+name;
					DemoClient.encfile = encfile;
					String baldFileName = FileOperator.recoverPreFileName(name);
					String decfile = DemoClient.storeHasDecFileDir+baldFileName;
					File dir2 = new File(DemoClient.storeHasDecFileDir);
					if(!dir2.exists()){
						dir2.mkdirs();
					}
					try {
						DemoClient.decryptCiphertext(DemoClient.prvfile, encfile, decfile);
					} catch (Exception e1) {
						//Discard
					}
				}
			}
		});
		
		unlock.setBounds(500, 580, 90, 30);
		pn_panel.add(unlock);
		

		refresh = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_refresh_button.png")));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				node.removeAllChildren();
				
				
				scroll.setVisible(false);	
				tree.setVisible(false);	
				loadingTree(new File(DemoClient.serverCiphertextDir),node);
				tree = new JTree(node);
				tree.setBounds(10, 10, 760, 500);
				tree.setFont(new Font("宋体", Font.PLAIN, 16));// ��������.

				scroll = new JScrollPane(tree);
				scroll.setBounds(10, 10, 760, 500);
				pn_panel.add(scroll);
				pn_panel.revalidate();
				pn_panel.repaint();

				tree.addTreeSelectionListener(new TreeSelectionListener() {
					public void valueChanged(TreeSelectionEvent e) {
			            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

			            if (node == null)
			                return;
			            Object object = node.getUserObject();
			            if (node.isLeaf()) {
			            	name = node.toString();
			            }
					}
			    });
			}
		});
		refresh.setBounds(200, 580, 90, 30);
		pn_panel.add(refresh);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			 
			public void valueChanged(TreeSelectionEvent e) {
	            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	            if (node == null)
	                return;

	            Object object = node.getUserObject();
	            if (node.isLeaf()) {
	            	name = node.toString();
	            }
	        }
	    });
	}

	private void loadingTree(File root, DefaultMutableTreeNode node) {
	    File[] files = root.listFiles();	    
	    if (files == null) {
	        return;
	    }
	    for (int i = 0; i < files.length; i++) {
	    	
	        subNode = new DefaultMutableTreeNode(files[i].getName());
	        node.add(subNode);
	        if (files[i].isDirectory()) {
	        	loadingTree(files[i], subNode);
	        }
	    }
	    newfiles = files;
	}
}
