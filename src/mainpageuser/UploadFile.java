package mainpageuser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import login_gui.MessageBox;
import login_gui.Success;
import cpabe.client.DemoClient;
import cpabe.systemEntity.ClientBusiness;


public class UploadFile extends JFrame implements ActionListener {
	String project_name;
	String clientUploadDir;
	String name;
	File[] newfiles = null;
	
	JPanel pn_panel;
	static DefaultMutableTreeNode node = new DefaultMutableTreeNode();
	static DefaultMutableTreeNode subNode = new DefaultMutableTreeNode();
	static JTree tree;
	JScrollPane scroll;
	
		
	JTextField ProjectName;
	JTextField tips;
//	JTextField SelectTextField;
	
	JButton btn_upload_file;
//	JButton btn_select;
	
	public UploadFile () {
		pn_panel = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pn_panel = new JPanel();
		pn_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pn_panel);
		pn_panel.setLayout(null);

	    loadingTree(new File(DemoClient.clientCiphertextDir),node);

		tree = new JTree(node);
		tree.setBounds(10, 10, 760, 500);
		tree.setFont(new Font("宋体", Font.PLAIN, 16));


		scroll = new JScrollPane(tree);
		scroll.setBounds(10, 10, 760, 500);
		pn_panel.add(scroll);
		
		
		
//		SelectTextField = new JTextField();
		ProjectName = new JTextField();
		
		btn_upload_file = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_uploadfile_button.png"))); 
//		btn_select = new JButton(new ImageIcon("src//iconforuser//img_select_button.png"));
		pn_panel.setBackground(new Color(234, 234, 234));
		pn_panel.setLayout(null);
		
		
//		SelectTextField.setEditable(true);
//		SelectTextField.setText("请选择文件");
//		SelectTextField.setBounds( 80, 540, 520, 30);
//		btn_select.setBounds(600, 540, 90, 30);
		
		
		JButton refresh = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_refresh_button.png")));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				node.removeAllChildren();

				scroll.setVisible(false);
				tree.setVisible(false);
				loadingTree(new File(DemoClient.clientCiphertextDir),node);
				tree = new JTree(node);
				tree.setBounds(10, 10, 760, 500);
				tree.setFont(new Font("宋体", Font.PLAIN, 16));

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
		refresh.setBounds(600, 520, 90, 30);
		pn_panel.add(refresh);
		
		tips = new JTextField("如果没有显示文件，请先刷新");
//		tips.setText();
		tips.setEditable(false);
		tips.setBounds(80, 520, 520, 30);
		pn_panel.add(tips);
		
		
		ProjectName.setEditable(true);
		ProjectName.setText("Project");
		ProjectName.setBounds( 80, 580, 520, 30);
		btn_upload_file.setBounds(600, 580, 90, 30);
		
		
//		pn_panel.add(btn_select);
		pn_panel.add(ProjectName);
//		pn_panel.add(SelectTextField);
		pn_panel.add(btn_upload_file);
		pn_panel.setOpaque(true);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
	            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

	            if (node == null)
	                return;

	            Object object = node.getUserObject();
	            if (node.isLeaf()) {
	            	name = node.toString();
	            }
	            
//	            for(int i = 0;i<newfiles.length;i++){
//						System.out.println(newfiles[i].getName());
//				}
	        }
	    });
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		
//		if (source == btn_select) {
//			JFileChooser fDialog = new JFileChooser();              
//			fDialog.setDialogTitle("请选择需要上传的文件");                
//			int returnVal = fDialog.showOpenDialog(null);           
//			if(JFileChooser.APPROVE_OPTION == returnVal){           
//				String s = fDialog.getSelectedFile().getPath();
//				SelectTextField.setText(s);
//			}
//		}
		if(source == btn_upload_file){
			System.out.println("OK");
				project_name = ProjectName.getText();
				for(int i = 0;i<newfiles.length;i++){
					if(name.equals(newfiles[i].getName())){
						clientUploadDir = newfiles[i].getPath();
					}
				}
				
				/**
				 * get dirName
				 * */
				String clientUploadDirPath = clientUploadDir.substring(0,
						clientUploadDir.length() - name.length());
				
				ClientBusiness clientBusiness = new ClientBusiness(
						MainPageUser.socket);
				clientBusiness.sendUploadSegment(project_name,clientUploadDirPath, name);
		}
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
