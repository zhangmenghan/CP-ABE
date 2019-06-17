package serverpage;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import login_gui.BackGroundPanel;
import login_gui.Fault;
import login_gui.JTableDemo;
import login_gui.Success;
import mainpageuser.MainPageUser;
import cpabe.client.DemoClient;
import cpabe.entity.FileInfo;
import cpabe.file.FileOperator;
import cpabe.server.DemoServer;
import cpabe.server.GUIServerThread;
import cpabe.server.Server;
import cpabe.service.FileService;

public class ServerPageAdmin extends JFrame implements ActionListener {
	JPanel pn_panel;
	JTableDemo tb_rankListEach; // 单双行颜色不同的表格
	JScrollPane sp_scrollEach;

	String[] columnRankListAll = { "文件名", "文件大小", "下载次数" };

	JTableDemo tb_list;
	JScrollPane sp_scrollAll;
	DefaultTableModel table;
	JLabel startLabel;
	JButton startButton;
	JButton stopButton;
	JButton btn_entering;
	JButton btn_entering_attr;
	JButton btn_delete;
	JButton fresh;
	BackGroundPanel background;

	/**
	 * back end code
	 * */
	Thread mainServer;

	public ServerPageAdmin() {
		FileSystemView fsv = FileSystemView.getFileSystemView();

		DemoServer.FILEDIRPATH = fsv.getHomeDirectory().getAbsolutePath()
				+ "/ServerFiles/";
		File dirServerFiles = new File(DemoServer.FILEDIRPATH);
		if (!dirServerFiles.exists()) {
			dirServerFiles.mkdirs();
		}

		DemoServer.prvfileDir = fsv.getHomeDirectory().getAbsolutePath()
				+ "/prvDir/";
		File dirPrvfileDir = new File(DemoServer.prvfileDir);
		if (!dirPrvfileDir.exists()) {
			dirPrvfileDir.mkdirs();
		}

		setResizable(false);// can not reset the window size
							// :width,height;窗体不能最大化
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 50, 900, 700);
		pn_panel = new JPanel();
		setContentPane(pn_panel);
		pn_panel.setLayout(null);
		this.setTitle("——基于属性及加密的权限认证的文档共享系统");
		this.setIconImage(new ImageIcon(getClass().getResource(
				"/iconforuser/title.png")).getImage());

		background = new BackGroundPanel((new ImageIcon(getClass().getResource(
				"/iconforadmin/img_server_bg.png"))).getImage());
		background.setBounds(0, 0, 900, 150);
		background.setOpaque(false);

		startLabel = new JLabel(new ImageIcon(getClass().getResource(
				"/iconforadmin/img_runed_lbl.png")));
		startLabel.setBounds(350, 100, 90, 30);
		startLabel.setVisible(false);

		startButton = new JButton(new ImageIcon(getClass().getResource(
				"/iconforadmin/img_run_btn.png")));
		startButton.setBounds(350, 100, 90, 30);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainServer = new Thread(new GUIServerThread());
				mainServer.start();
				startButton.setVisible(false);
				startLabel.setVisible(true);
			}
		});

		stopButton = new JButton(new ImageIcon(getClass().getResource(
				"/iconforadmin/img_stop_btn.png")));
		stopButton.setBounds(450, 100, 90, 30);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Server.serverSocket != null) {
						Server.serverSocket.close();
						Server.serverSocket = null;

						startButton.setVisible(true);
						startLabel.setVisible(false);
					}
				} catch (Exception e1) {
					// Discard
				}
			}
		});

		btn_entering_attr = new JButton(new ImageIcon(getClass().getResource(
				"/iconforadmin/img_insert_attr_btn.png")));
		btn_entering_attr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InputAttr();
			}
		});
		btn_entering_attr.setBounds(650, 100, 90, 30);

		btn_entering = new JButton(new ImageIcon(getClass().getResource(
				"/iconforadmin/img_indsert_info_btn.png")));
		btn_entering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfoEntering();
			}
		});
		btn_entering.setBounds(550, 100, 90, 30);

		btn_delete = new JButton(new ImageIcon(getClass().getResource(
				"/iconforadmin/img_delete_prikey_btn.png")));
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOperator.destoryFileSecurely(DemoServer.prvfileDir
						+ "prv_key");
			}
		});
		btn_delete.setBounds(750, 100, 90, 30);

		boolean isNeedInit = true;
		File file = new File(DemoServer.pubfile);
		if (!file.exists()) {
			isNeedInit = false;
			fresh = new JButton(new ImageIcon(getClass().getResource("/iconforadmin/img_fresh_btn.png")));
			fresh.setBounds(50, 100, 90, 30);
			fresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {

						DemoServer.setup();
						fresh.setVisible(false);

						new Success("初始化成功");
					} catch (Exception e1) {
						new Fault("主密钥、公钥初始化失败！");
						//
					}
				}
			});

			pn_panel.add(fresh);

		}

		pn_panel.add(startLabel);
		pn_panel.add(btn_delete);
		pn_panel.add(btn_entering_attr);
		pn_panel.add(btn_entering);
		pn_panel.add(startButton);
		pn_panel.add(stopButton);

		addIt(background, 1);

		// FileService file = new FileService();
		adddata(refresh());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if (Server.serverSocket != null) {
						Server.serverSocket.close();
						Server.serverSocket = null;
					}
				} catch (IOException e1) {
					// Discard
				}
				MainPageUser.socket = null;
				System.exit(0);
			}
		});

	}

	TableModel refreshModel(Object[][] data, String[] column, TableModel model) {
		model = new DefaultTableModel(data, column) {
			private static final long serialVersionUID = 5964221603467963808L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		return model;
	}

	void addIt(Component item, int n) {
		this.getContentPane().add(item, new Integer(n));
	}

	public Object[][] refresh() {
		FileService fileservice = new FileService();
		List<FileInfo> fileInfos = fileservice.getAllFileInfos();
		Object[][] data = new Object[fileInfos.size()][3];
		for (int i = 0; i < fileInfos.size(); i++) {
			data[i][0] = fileInfos.get(i).getName();
			data[i][1] = fileInfos.get(i).getFilesize();
			data[i][2] = fileInfos.get(i).getDownload_times();
		}
		return data;
	}

	void adddata(Object[][] newdata) {
		tb_list = new JTableDemo(table);

		Object[][] data = newdata;

		table = (DefaultTableModel) refreshModel(data, columnRankListAll,
				tb_list.getModel());

		tb_list = new JTableDemo(table);
		tb_list.TableSetColumn(640, 100, 100);
		tb_list.setModel(table);

		sp_scrollAll = new JScrollPane(tb_list);
		sp_scrollAll.setBounds(30, 150, 840, 500);
		sp_scrollAll.setBackground(new Color(255, 255, 255));

		pn_panel.add(sp_scrollAll);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerPageAdmin frame = new ServerPageAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// ServerPageAdmin frame = new ServerPageAdmin();
		// frame.setVisible(true);

		// new ServerPageAdmin();
	}
}
