package cpabe.systemEntity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import login_gui.Fault;
import login_gui.Success;
import cpabe.client.Client;
import cpabe.client.DemoClient;
import cpabe.file.FileOperator;

public class ClientBusiness {
	final private static int BUFFER_SIZE = 10 * 1024;
	final private static String DEFAULTFILEDIR = "./file_dir/";

	private static DataInputStream din = null;
	private static DataOutputStream dout = null;
	
	public Socket socket=null;
	
	public ClientBusiness(Socket socket){
		this.socket = socket;
		//initiate dataInputStream and dataOutputStream
		try {
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			//Discard
		}
		
	}
	
	
	/**
	 * client send
	 * */
//	public void sendLoginSegment(Socket socket, String staffId, String password) {
	public boolean sendLoginSegment(String staffId, String password) {
		if (socket.isOutputShutdown()) {
			return false;
		}

		/**
		 * 瀵瑰瘑鐮佺敤MD5鍔犲瘑涔嬪悗鍦ㄨ繘琛屼紶锟�
		 * */
		String passwordMD5 = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] passwordBytes = md.digest();
			passwordMD5 = new String(passwordBytes);
		} catch (NoSuchAlgorithmException e) {
			//Discard
		}

		try {
			dout.writeUTF("login");
			dout.writeChar(' ');// 鎸夌収CMSP鍗忚瑙勫畾锛屼腑闂寸暀绌烘牸
			dout.writeUTF(staffId);
			dout.writeChar(' ');// 鍖哄垎staffId涓巔asswordMD5
			dout.writeUTF(passwordMD5);
			
			/**
			 * 闃诲绛夊緟鎺ユ敹鍝嶅簲淇℃伅
			 * */
			String code = din.readUTF();
			
			if ("100".equals(code)) {
				din.readChar();
				/**
				 * get attibuteSet information from server,and store the information into the properties file
				 * 			--attribute.properties
				 * */
				String attrSetString = din.readUTF();
				FileOperator.storeAttrSetToPropFile(DemoClient.attributePropertiesFilePath, attrSetString);
		
				din.readChar();
				long fileSize = din.readLong();
				int length = 0;
				long sum = 0;
				byte buff[] = new byte[BUFFER_SIZE];

				FileOutputStream fout = new FileOutputStream(DemoClient.pubfile);
				while ((length = din.read(buff)) != -1) {
					
					fout.write(buff, 0, length);
					sum += length;
					
					if(sum >= fileSize){
						break;
					}
				}
				fout.close();

				
				
				/**
				 * because the first page need to show all files' names,
				 * so we should get the all_file_names information right after login 
				 * */
				FileOperator.deleteRootDir(DemoClient.ServerRootDir);
				
				sendNamesSegment();
				return true;
			}
			if ("200".equals(code)) {
				return false;
			}
		} catch (IOException e) {
			//Discard
		}
		return false;
	}
	
	public void sendDownloadSegment(String fileName) {
		if (socket.isOutputShutdown()) {
			return;
		}
		try {
			dout.writeUTF("download");
			dout.writeChar(' ');// 鎸夌収CMSP鍗忚瑙勫畾锛屼腑闂寸暀绌烘牸
			dout.writeUTF(Client.STAFF_ID);
			dout.writeChar(' ');
			dout.writeUTF(fileName);

			String code = din.readUTF();
			
			if ("300".equals(code)) {
//				din.readChar();// 涓㈠純鍗忚涓殑绌烘牸(瑙勮寖)

				/**
				 * 浠庢暟鎹祦涓鍙栨暟鎹紝骞跺垏瀹炰繚瀛樺埌鎸囧畾鐨勬枃浠朵腑锟�
				 * */

				int length = 0;
				long sum = 0;
				byte buff[] = new byte[BUFFER_SIZE];
				long fileSize = din.readLong();

				
				
//				din.readChar();
				String serverFileName = din.readUTF();
				
				File dir = new File(DemoClient.serverCiphertextDir);
				if(!dir.exists()){
					dir.mkdirs();
				}
				FileOutputStream fout = new FileOutputStream(DemoClient.serverCiphertextDir
						+ serverFileName);
				while ((length = din.read(buff)) != -1) {
					
					fout.write(buff, 0, length);
					sum += length;
					
					if(sum >= fileSize){
						break;
					}
				}
				fout.close();
				
				if (sum == fileSize) {
					new Success("文件下载成功！");
				} else {
					new Fault("文件下载失败！");
				}

			}
			if ("400".equals(code)) {
				new Fault("文件下载失败！");
			}
		} catch (IOException e) {
			//Discard
		}

	}

	/**
	 * 涓婁紶鏂囦欢鐨勬姤鏂囩殑鏍煎紡涓猴細
	 * 
	 * upload size+filename+data
	 * 
	 * 鍙湁锟�锟斤拷绌烘牸
	 * 
	 * */
	public void sendUploadSegment(String project,String fileDir,String filename) {
		if (socket.isOutputShutdown()) {
			return;
		}

		int length = 0;
		byte[] buf = new byte[BUFFER_SIZE];
		File file = new File(fileDir+filename);

		try {
			dout.writeUTF("upload");
			dout.writeChar(' ');
			dout.writeLong(file.length());// 鍙戯拷?鏂囦欢鐨勫ぇ锟�
			dout.writeUTF(project+"-"+file.getName());// 鍙戯拷?鏂囦欢鐨勫悕锟�
			
			dout.writeChar(' ');
			dout.writeUTF(Client.STAFF_ID);
			
			String policy = FileOperator.getCiphertextPolicy(filename);
			dout.writeChar(' ');
			if(policy!=null){
				dout.writeUTF(policy);
			}else {
				dout.writeUTF("empty");
			}

			// 鍙戯拷?鏂囦欢鍐呭
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			while ((length = bis.read(buf)) != -1) {
				dout.write(buf, 0, length);// 涓昏鏄尮閰嶆渶鍚庝竴娆′紶杈撶殑娴佺殑澶у皬
			}

			/**
			 * 鎺ュ彈鏈嶅姟鍣ㄧ鐨勭‘璁や俊锟�
			 * */
			
			String code = din.readUTF();
			
			if ("500".equals(code)) {
				new Success("文件上传成功！");
			}
			if ("600".equals(code)) {
				new Success("文件上传失败！请重新上传！");
			}
		} catch (IOException e) {
			//Discard
		}
	}

	/**
	 * 璇锋眰鏈嶅姟鍣ㄦ墍鏈夊瘑鏂囨枃浠跺悕
	 * 
	 * names
	 * 
	 * 娉細璇egment鍙湁names锛屾病鏈夌┖鏍硷紝娌℃湁data
	 * */
	public void sendNamesSegment() {
		if (socket.isOutputShutdown()) {
			return;
		}
		try {
			dout.writeUTF("names");
			dout.writeChar(' ');

			/**
			 * 闃诲绛夊緟鎺ユ敹鍝嶅簲淇℃伅
			 * */
			String code = din.readUTF();
			
			if ("700".equals(code)) {

				/**
				 * 浠庢暟鎹祦涓鍙栨暟鎹紝骞跺垏瀹炰繚瀛樺埌鎸囧畾鐨勬枃浠朵腑锟�
				 * */
				din.readChar();// 涓㈠純鍗忚涓殑绌烘牸(瑙勮寖)

				String allFileNames = din.readUTF();
	
				Map<String, List<String>> dirFileMap = FileOperator
						.parseStringToDirFile(allFileNames);
				/**
				 * 閬嶅巻map涓殑锟�
				 * 
				 * 瀹為檯搴旂敤鏃讹紝灏嗭拷?浼狅拷?缁欏墠绔樉锟�
				 * */
				Iterator<Map.Entry<String, List<String>>> entries = dirFileMap
						.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry<String, List<String>> entry = entries.next();
					String projectDirPath = DemoClient.ServerRootDir+entry.getKey();
					File projectDir = new File(projectDirPath);
					if(!projectDir.exists()){
						projectDir.mkdirs();
					}
					List<String> filenameList = entry.getValue();
					for (int i = 0; i < filenameList.size(); i++) {
						String specialFilePath = projectDirPath +"/"+filenameList.get(i);
						File file = new File(specialFilePath);
						if(!file.exists()){
							file.createNewFile();
						}
					}
				}
			}
			if ("800".equals(code)) {
				new Fault("Error!Failed to check all server's files' name!");
			}
		} catch (IOException e) {
			new Fault("Error!Failed to check all server's files' name!");
		}
	}

	/**
	 * 涓氬姟鎺у埗淇℃伅鐨勬姤鏂囩殑鏍煎紡
	 * 
	 * service ?key:value?key:value?鈥︼拷?
	 * 
	 * 娉ㄥ悓涓嶅悓鐨勯敭鍊煎涔嬮棿锟�?'鍒嗗壊锛屼笖寮哄埗瑕佹眰key鍜寁alue鐨勫唴瀹逛笉鍖呭惈'?'
	 * */
	public void sendServiceSegment(String serverFileName) {
		if (socket.isOutputShutdown()) {
			return;
		}

		try {
			dout.writeUTF("service");
			dout.writeChar(' ');
			
			dout.writeUTF("policy");
			dout.writeChar(' ');
			dout.writeUTF(serverFileName);
			
			String policy = din.readUTF();
			if("900".equals(policy)){
				new Fault("ERROR! policy information check failed!");
			}
			DemoClient.policy = policy;
		} catch (IOException e) {
			//Discard
		}
	}

	/**
	 * client recevie
	 * */
}
