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
import cpabe.client.DemoClient;
import cpabe.utils.OpenLocalFile;

public class AlreadyEncrypted extends JFrame {
	String name;
	static File[] newfiles = null;

	JPanel pn_panel;
	static DefaultMutableTreeNode node = new DefaultMutableTreeNode();
	static DefaultMutableTreeNode subNode = new DefaultMutableTreeNode();
	static JTree tree;
	JScrollPane scroll;

	public AlreadyEncrypted() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pn_panel = new JPanel();
		pn_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pn_panel);
		pn_panel.setLayout(null);

		loadingTree(new File(DemoClient.storeHasDecFileDir), node);

		tree = new JTree(node);
		tree.setBounds(10, 10, 760, 500);
		tree.setFont(new Font("宋体", Font.PLAIN, 16));

		scroll = new JScrollPane(tree);
		scroll.setBounds(10, 10, 760, 500);
		pn_panel.add(scroll);

		JButton show = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_openfile_button.png")));
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = null;
				
				for (int i = 0; i < newfiles.length; i++) {
					if (name.equals(newfiles[i].getName())) {
						filePath = newfiles[i].getPath();
					}
				}
				if (filePath != null) {
					OpenLocalFile olf = new OpenLocalFile();
					olf.useCMDCommand(filePath);
				} else {
					new Fault("文件打开失败！");
//					System.out.println("file is not exist");
				}
			}
		});

		show.setBounds(500, 540, 90, 30);
		pn_panel.add(show);

		JButton refresh = new JButton(new ImageIcon(getClass().getResource("/iconforuser/img_refresh_button.png")));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				node.removeAllChildren();

				scroll.setVisible(false);
				tree.setVisible(false);
				loadingTree(new File(DemoClient.storeHasDecFileDir),node);
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
		refresh.setBounds(200, 540, 90, 30);
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
