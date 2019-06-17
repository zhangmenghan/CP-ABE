package cpabe.systemEntity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import cpabe.entity.FileInfo;
import cpabe.file.FileOperator;
import cpabe.server.DemoServer;
import cpabe.service.AttrSetService;
import cpabe.service.FileService;
import cpabe.service.StaffService;
import cpabe.utils.CpabeException;

public class ServerBusiness {

	final private static int BUFFER_SIZE = 10 * 1024;

	public static final String LOGIN = "login";
	public static final String DOWNLOAD = "download";
	public static final String UPLOAD = "upload";
	public static final String NAMES = "names";
	public static final String SERVICE = "service";

	private static DataInputStream din = null;
	private static DataOutputStream dout = null;

	// private Map<Socket, String> staffsInfoMap;
	public Socket socket = null;

	public ServerBusiness(Socket socket) {
		this.socket = socket;
		/**
		 * initiate dataInputStream and dataOutputStream
		 * */
		try {
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			//Discard
		}
	}

	/**
	 * 
	 * */
	public void handleRequest() {
		String status = null;
		if (socket.isInputShutdown()) {
			return;
		}

		try {
			status = din.readUTF();// 娴犲骸鐡ч懞鍌涚ウ娑擃叀顕伴崣鏉漷atus鐎涙顔�
			din.readChar();// 娑撱垹绱旈崡蹇氼唴娑擃叀顫夌�姘辨畱缁岀儤鐗�

			while (true) {
				if (LOGIN.equals(status)) {
					String staffId = din.readUTF();
					din.readChar();// 娑撱垹绱攕taffId閸滃asswordMD5 娑斿妫块惃鍕敄閿燂拷
					String passwordMD5 = din.readUTF();
					
					System.out.println("staffId:"+staffId);
					System.out.println("passwordMD5:"+passwordMD5);

					if (isValidStaff(staffId, passwordMD5)) {
						// response100(socket);
						dout.writeUTF("100");
						dout.writeChar(' ');
						
						/**
						 * send attibuteSet information to client,so that user can make policy when encrypt
						 * */
						String attrSetString = new AttrSetService().createAttrSetString();
						dout.writeUTF(attrSetString);
						
						dout.writeChar(' ');
						int length = 0;
						byte[] buf = new byte[BUFFER_SIZE];
						File file = new File(DemoServer.pubfile);
						long sendFileLength = file.length();
						
						System.out.println("before dout.writeLong(sendFileLength);");
						dout.writeLong(sendFileLength);
						System.out.println("after dout.writeLong(sendFileLength);");
						long sum = 0;

						BufferedInputStream bis = new BufferedInputStream(
								new FileInputStream(file));
						while ((length = bis.read(buf)) != -1) {
							dout.write(buf, 0, length);// 娑撴槒顩﹂弰顖氬爱闁板秵娓堕崥搴濈濞嗏�绱舵潏鎾舵畱濞翠胶娈戞径褍鐨�

							sum += length;
							if (sum >= sendFileLength) {
								break;
							}
							System.out.println("server break!");
						}
						bis.close();
						
					} else {
						dout.writeUTF("200");
					}

					
					/**
					 * Constitute a circulation
					 * */
					status = din.readUTF();
					din.readChar();
				}

				if (DOWNLOAD.equals(status)) {

					String staff_id = din.readUTF();
					din.readChar();

					String fileName = din.readUTF();

					if (hasFileInDIR(fileName)) {
						response300(socket, fileName, staff_id);
						
						/**
						 * modify FileDownLoadInfoDAO,and FileInfoDAO;
						 * */
						FileService fileService = new FileService();
						fileService.modifyInfoAboutDownload(staff_id, fileName);
					} else {
						dout.writeUTF("400");
					}

					/**
					 * Constitute a circulation
					 * */
					status = din.readUTF();
					din.readChar();
				}

				if (UPLOAD.equals(status)) {
					if (isReceiveFileSuccessful(din)) {// ///////閻ｆ瑦鍓伴敍灞绢劃婢跺嫬褰查懗钘夊毉閻滅檱UG
						dout.writeUTF("500");
					} else {
						dout.writeUTF("600");
					}

					/**
					 * Constitute a circulation
					 * */
					status = din.readUTF();
					din.readChar();
				}

				if (NAMES.equals(status)) {
					
					String allFileNames = FileOperator
							.getDirFileNamesList(DemoServer.FILEDIRPATH);
					dout.writeUTF("700");
					dout.writeChar(' ');
					dout.writeUTF(allFileNames);

					/**
					 * Constitute a circulation
					 * */
					status = din.readUTF();
					din.readChar();
				}

				if (SERVICE.equals(status)) {
					String serverFileName = null;
					
					String serviceControlInfo = din.readUTF();
					din.readChar();
					if("policy".equals(serviceControlInfo)){
						serverFileName = din.readUTF();
					}else {
						serverFileName = din.readUTF();
					}
					
					FileService fileService = new FileService();
					String policy = fileService.findPolicyByFileName(serverFileName);
					if((!"".equals(policy))&&(policy!=null)){
						dout.writeUTF(policy);
					}else {
						dout.writeUTF("900");
					}
					/**
					 * Constitute a circulation
					 * */
					status = din.readUTF();
					din.readChar();
				}

			}
		} catch (IOException e) {
			//Discard
		}
	}

	public boolean isValidStaff(String staffId, String passwordMD5) {

		System.out.println("isValidStaff");
		StaffService service = new StaffService();
		return service.verifyStaff(staffId, passwordMD5);
	}

	public boolean hasFileInDIR(String fileName) {
		File file = new File(DemoServer.FILEDIRPATH + fileName);
		return file.exists();
	}

	public boolean isReceiveFileSuccessful(DataInputStream din) {

		int length = 0;
		long sum = 0;
		byte buff[] = new byte[BUFFER_SIZE];

		try {
			long fileSize = din.readLong();
			String fromClientFileName = din.readUTF();
			
			din.readChar();
			String staff_id = din.readUTF();
			
			din.readChar();
			String policy = din.readUTF();

			Date date = new Date();
			String serverFileName = FileOperator.createFileNameInServer(
					fromClientFileName, date);

			FileOutputStream fout = new FileOutputStream(DemoServer.FILEDIRPATH
					+ serverFileName);
			while ((length = din.read(buff)) != -1) {

				fout.write(buff, 0, length);
				sum += length;
				/**
				 * because datainputstream is always connect,so this sentence
				 * "(length = din.read(buff)) != -1" will be going on all time
				 * so we need to break out of the dead circulation when file has
				 * received successfully
				 * */
				if (sum >= fileSize) {
					break;
				}
			}
			fout.close();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String upload_date = sdf.format(date);

			FileInfo fileInfo = new FileInfo();
			fileInfo.setName(serverFileName);
			fileInfo.setUpload_staff_id(staff_id);
			fileInfo.setUpload_date(upload_date);
			fileInfo.setDownload_times(0);
			fileInfo.setFilesize(fileSize);
			fileInfo.setPolicy(policy);
			FileService service = new FileService();
			try {
				service.createFileInfoRecord(fileInfo);
			} catch (CpabeException e) {
				//Discard
			}

			if (sum == fileSize) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			//Discard
		}
		return false;
	}

	/**
	 * response methods
	 * */

	/**
	 * CMSP閸楀繗顔呯憴鍕暰閿燂拷
	 * 
	 * 100閿涙俺闊╂禒鎴掍繆閹垽鎷�鏉╁洭鐛欑拠渚婄礉閸忎浇顔忔潻娑樺弳缁崵绮�
	 * */
	public void response100(Socket socket) {

	}

	/**
	 * CMSP閸楀繗顔呯憴鍕暰閿燂拷
	 * 
	 * 200閿涙俺闊╂禒鎴掍繆閹垯璐熼柅姘崇箖妤犲矁鐦夐敍灞惧珕缂佹繆绻橀崗銉ч兇閿燂拷
	 * */
	public void resopnse200(Socket socket) {

	}

	/**
	 * CMSP閸楀繗顔呯憴鍕暰閿燂拷
	 * 
	 * 300閿涙erver娴肩媴鎷�client鐠囬攱鐪伴惃鍕瀮娴犺绱漝ata 闂傤喛顕氶弬鍥︽閺佺増宓侀敓锟� *
	 */
	public void response300(Socket socket, String fileName, String staff_id) {
		int length = 0;
		byte[] buf = new byte[BUFFER_SIZE];
		String filePathName = DemoServer.FILEDIRPATH + fileName;
		File file = new File(filePathName);

		try {
			dout.writeUTF("300");

			long sendFileLength = file.length();
			dout.writeLong(sendFileLength);// 閸欐埊鎷�閺傚洣娆㈤惃鍕亣閿燂拷
			dout.writeUTF(file.getName());// 閸欐埊鎷�閺傚洣娆㈤惃鍕倳閿燂拷

			long sum = 0;

			// // 閸欐埊鎷�閺傚洣娆㈤崘鍛啇
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			while ((length = bis.read(buf)) != -1) {
				dout.write(buf, 0, length);// 娑撴槒顩﹂弰顖氬爱闁板秵娓堕崥搴濈濞嗏�绱舵潏鎾舵畱濞翠胶娈戞径褍鐨�

				sum += length;
				if (sum >= sendFileLength) {
					break;
				}
			}
			bis.close();
			/**
			 * modify the file_download_info, and modify the total
			 * download_times
			 */
//			modifyInfoAboutDownload(staff_id, fileName);
		} catch (IOException e) {
			//Discard
		}

	}

	/**
	 * CMSP閸楀繗顔呯憴鍕暰閿燂拷
	 * 
	 * 400閿涙erver閹锋帞绮烽崫宥呯安client
	 * 鐠囬攱鐪伴惃鍕瀮娴犺埖鍨ㄩ弫鐗堝祦鎼存挷鑵戦弬鍥︽瀹歌尙绮＄悮顐㈠灩閿涘畳ata娑撶皠ull
	 * */
	public void response400(Socket socket) {

	}

	/**
	 * CMSP閸楀繗顔呯憴鍕暰閿燂拷
	 * 
	 * 500閿涙erver閹存劕濮涢幒銉︽暪client閸欐埊鎷�閻ㄥ嫭鏋冮敓锟� *
	 */
	public void response500(Socket socket) {

	}

	/**
	 * CMSP閸楀繗顔呯憴鍕暰閿燂拷
	 * 
	 * 600閿涙erver閹恒儲鏁归弬鍥︽閸戞椽鏁婇敍宀冾嚞濮逛竣lient闁插秴褰�
	 * */
	public void response600(Socket socket) {

	}

	/**
	 * CMSP閸楀繗顔呯憴鍕暰閿燂拷
	 * 
	 * 700閿涙艾顕惔鏀攅quest閻ㄥ墕ervice
	 * */
	public void response700(Socket socket) {

	}

	/**
	 * modify the fileDownloadInfo, and modify the total download_times in fileInfo
	 * 
	 */
//	public void modifyInfoAboutDownload(String staff_id, String serverFileName) {
//		FileService fileService = new FileService();
//
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String download_time = sdf.format(date);
//
//		FileDownloadInfo downloadInfo = new FileDownloadInfo();
//		downloadInfo.setFile_name(serverFileName);
//		downloadInfo.setDownload_time(download_time);
//		downloadInfo.setDownload_times(0);
//		downloadInfo.setDownload_staff_id(staff_id);
//
//		fileService.modifyInfoAboutDownload(staff_id, serverFileName);
//	}
}
