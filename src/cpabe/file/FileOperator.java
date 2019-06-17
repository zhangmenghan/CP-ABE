package cpabe.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cpabe.client.DemoClient;
import cpabe.entity.AttributeSet;

public class FileOperator {
	/*
	 * Java文件操作 获取文件扩展�?
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/*
	 * Java文件操作 获取不带扩展名的文件�?
	 */
	public static String getFileNameNoLastEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	/*
	 * Java文件操作 修改文件的文件名—�?>转换成系统规定的文件命名格式规范
	 * 
	 * 文件的内容组成由以下部分组成�?
	 * 
	 * 1.上传者定义的文件�? 2.日期+ 3.原文件的扩展�? 4.系统规定的扩展名 ".ABE"
	 */
	public static String createFileNameInServer(String filename) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateString = sdf.format(date);
		String serverFileName = null;
		if (filename == null) {
			return null;
		}
		String baldFileName = getFileNameNoLastEx(filename);
		String preExtension = getExtensionName(filename);
		serverFileName = baldFileName + "-" + dateString + "." + preExtension
				+ ".ABE";
		return serverFileName;
	}

	/*
	 * Java文件操作 修改文件的文件名—�?>转换成系统规定的文件命名格式规范
	 * 
	 * 文件的内容组成由以下部分组成�?
	 * 
	 * 1.上传者定义的文件�? 2.日期+ 3.原文件的扩展�? 4.系统规定的扩展名 ".ABE"
	 */
	public static String createFileNameInServer(String filename, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		String dateString = sdf.format(date);

		String serverFileName = null;
		if (filename == null) {
			return null;
		}
		String preFileName = getFileNameNoLastEx(filename);
		String baldFileName = getFileNameNoLastEx(preFileName);
		String preExtension = getExtensionName(preFileName);
		serverFileName = baldFileName + "-" + dateString + "." + preExtension
				+ ".ABE";
		return serverFileName;
	}

	/**
	 * fileName in client baldFileName +"-" + staff_id + "." +
	 * extentionName+".ABE"
	 * */
	public static String createFileNameInClient(String fileName, String staffId) {
		String clientFileName = null;
		String baldFileName = getFileNameNoLastEx(fileName);
		String extentionName = getExtensionName(fileName);
		clientFileName = baldFileName + "-" + staffId + "." + extentionName
				+ ".ABE";
		return clientFileName;
	}

	// public static String createFileNameInClientButUpload(String fileName,
	// String projectName) {
	// String clientFileName = null;
	// String baldFileName = getFileNameNoLastEx(fileName);
	// String extentionName = getExtensionName(fileName);
	// clientFileName = baldFileName + staffId + "." + extentionName + ".ABE";
	// return clientFileName;
	// }

	/*
	 * Java文件操作 恢复原来的文件的的文件名
	 */
	public static String recoverPreFileName(String serverFileName) {
		String preFileName = null;
		int firstIndex = serverFileName.indexOf("-");
		String filenameWithoutProject = serverFileName
				.substring(firstIndex + 1);

		int seconfIndex = filenameWithoutProject.indexOf("-");
		String baldFileName = filenameWithoutProject.substring(0, seconfIndex);
		String preFileNameEx = getExtensionName(getFileNameNoLastEx(serverFileName));
		preFileName = baldFileName + "." + preFileNameEx;
		return preFileName;
	}

	public static String makeFileProfile(String serverFileName, String policy,
			String description) {
		String fileProfile = "To decrypt the ciphertext you should be up to "
				+ "this access policy's requirement which goes like this:"
				+ policy + "-" + description;
		storeProperties(serverFileName, fileProfile);
		return fileProfile;
	}

	/*
	 * Java文件操作 安全地删除文�?
	 * 
	 * 为了安全 先将文件的内容置为空
	 * 
	 * 然后在执行删�?
	 */
	public static void destoryFileSecurely(String pathName) {
		File file = new File(pathName);
		if (file.exists()) {
			int length = (int) file.length();
			try {
				FileOutputStream fos = new FileOutputStream(file);
				byte[] nullContent = new byte[length - 1];
				fos.write(nullContent); // 将文件的内容置为NULL�?
				fos.close();
				file.delete(); // 删除文件
			} catch (IOException e) {
				// Discard
			}
		}
	}

	/*
	 * Java文件操作 --创建临时文件夹，以便存一些临时文�?
	 */
	public static void makeDirectory() {
		String destDirName = "./fileTemp";
		File dir = new File(destDirName);
		if (dir.exists()) {
			return;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		dir.mkdir();
	}

	/*
	 * Java文件操作 --创建临时文件，以便存�?��临时的过渡信�?
	 */
	public static void makeTemplePropertiesFile() {
		makeDirectory();
		String pathname = "./fileTemp/info.properties";
		File file = new File(pathname);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// Discard
			}
		}
	}

	public static void makeTemplePropertiesFile(String pathname) {
		File file = new File(pathname);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// Discard
			}
		}
	}

	/*
	 * Java文件操作 --读取properties文件中的信息
	 * 
	 * 如：用户设置的访问控制结构policy
	 */
	public static String getPropertiesValue(String key) {
		String value = null;
		Properties prop = new Properties();
		// 读取属�?文件a.properties

		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					"./filetemp/info.properties"));
			prop.load(in);
			Iterator<String> it = prop.stringPropertyNames().iterator();
			value = prop.getProperty(key);
			in.close();
		} catch (IOException e) {
			// Discard
		}
		return value;
	}

	public static String getCiphertextPolicy(String key) {
		String value = null;
		Properties prop = new Properties();

		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					DemoClient.policyPropertiesFilePath));
			prop.load(in);
			value = prop.getProperty(key);
			in.close();
		} catch (IOException e) {
			// Discard
		}
		return value;
	}

	public static void storeAttrSetToPropFile(String pathname,
			String attrSetString) {
		List<String> attributeList = FileOperator
				.parseStringToAttributeList(attrSetString);

		Properties prop = new Properties();
		FileOutputStream oFile = null;
		try {
			oFile = new FileOutputStream(pathname, false);// false表示追加关闭
			if (!"*".equals(attributeList.get(0))) {
				prop.setProperty("attr_1", attributeList.get(0));
			}
			if (!"*".equals(attributeList.get(1))) {
				prop.setProperty("attr_2", attributeList.get(1));
			}
			if (!"*".equals(attributeList.get(2))) {
				prop.setProperty("attr_3", attributeList.get(2));
			}
			if (!"*".equals(attributeList.get(3))) {
				prop.setProperty("attr_4", attributeList.get(3));
			}
			if (!"*".equals(attributeList.get(4))) {
				prop.setProperty("attr_5", attributeList.get(4));
			}
			if (!"*".equals(attributeList.get(5))) {
				prop.setProperty("attr_6", attributeList.get(5));
			}
			if (!"*".equals(attributeList.get(6))) {
				prop.setProperty("attr_7", attributeList.get(6));
			}
			if (!"*".equals(attributeList.get(7))) {
				prop.setProperty("attr_8", attributeList.get(7));
			}
			prop.store(oFile, "The New properties file");
			oFile.close();
		} catch (IOException e) {
			// Discard
		}
	}

	public static void storePolicyToPropFile(String pathname,
			String clientFileName, String policy) {
		Properties prop = new Properties();
		FileOutputStream oFile = null;
		try {
			oFile = new FileOutputStream(pathname, true);// true表示追加打开
			prop.setProperty(clientFileName, policy);
			prop.store(oFile, "The New properties file");
			oFile.close();
		} catch (IOException e) {
			// Discard
		}
	}

	public static void loadProperties(String resources) {
		Properties prop = new Properties();
		// 读取属性文件a.properties
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(resources));
			prop.load(in); // /加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				System.out.println(key + ":" + prop.getProperty(key));
			}
		} catch (Exception e) {
			// Discard
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// Discard
			}
		}
	}

	public static AttributeSet loadAttrProp(String resources) {
		AttributeSet attrSet = new AttributeSet();
		Properties prop = new Properties();
		// 读取属性文件a.properties
		InputStream in = null;
		Map<String, String> attrMap = new HashMap<String, String>();
		try {
			in = new BufferedInputStream(new FileInputStream(resources));
			prop.load(in); // /加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = prop.getProperty(key);
				attrMap.put(key, value);
			}
			attrSet.setAttr_1(attrMap.get("attr_1"));
			attrSet.setAttr_2(attrMap.get("attr_2"));
			attrSet.setAttr_3(attrMap.get("attr_3"));
			attrSet.setAttr_4(attrMap.get("attr_4"));
			attrSet.setAttr_5(attrMap.get("attr_5"));
			attrSet.setAttr_6(attrMap.get("attr_6"));
			attrSet.setAttr_7(attrMap.get("attr_7"));
			attrSet.setAttr_8(attrMap.get("attr_8"));
			return attrSet;
		} catch (Exception e) {
			// Discard
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// Discard
			}
		}
		return null;
	}

	public static AttributeSet splitAttribute(AttributeSet attrSet){
		AttributeSet attrSetAfterSpilt = new AttributeSet();
		if((attrSet.getAttr_1()!=null)&&(!"".equals(attrSet.getAttr_1()))){
			int index = attrSet.getAttr_1().indexOf(":");
			attrSetAfterSpilt.setAttr_1(attrSet.getAttr_1().substring(0, index));
		}
		if((attrSet.getAttr_2()!=null)&&(!"".equals(attrSet.getAttr_2()))){
			int index = attrSet.getAttr_2().indexOf(":");
			attrSetAfterSpilt.setAttr_2(attrSet.getAttr_2().substring(0, index));
		}
		if((attrSet.getAttr_3()!=null)&&(!"".equals(attrSet.getAttr_3()))){
			int index = attrSet.getAttr_3().indexOf(":");
			attrSetAfterSpilt.setAttr_3(attrSet.getAttr_3().substring(0, index));
		}
		if((attrSet.getAttr_4()!=null)&&(!"".equals(attrSet.getAttr_4()))){
			int index = attrSet.getAttr_4().indexOf(":");
			attrSetAfterSpilt.setAttr_4(attrSet.getAttr_4().substring(0, index));
		}
		if((attrSet.getAttr_5()!=null)&&(!"".equals(attrSet.getAttr_5()))){
			int index = attrSet.getAttr_5().indexOf(":");
			attrSetAfterSpilt.setAttr_5(attrSet.getAttr_5().substring(0, index));
		}
		if((attrSet.getAttr_6()!=null)&&(!"".equals(attrSet.getAttr_6()))){
			int index = attrSet.getAttr_6().indexOf(":");
			attrSetAfterSpilt.setAttr_6(attrSet.getAttr_6().substring(0, index));
		}
		if((attrSet.getAttr_7()!=null)&&(!"".equals(attrSet.getAttr_7()))){
			int index = attrSet.getAttr_7().indexOf(":");
			attrSetAfterSpilt.setAttr_7(attrSet.getAttr_7().substring(0, index));
		}
		if((attrSet.getAttr_8()!=null)&&(!"".equals(attrSet.getAttr_8()))){
			int index = attrSet.getAttr_8().indexOf(":");
			attrSetAfterSpilt.setAttr_8(attrSet.getAttr_8().substring(0, index));
		}
		return attrSetAfterSpilt;
	}
	/*
	 * Java文件操作 --存储重要的信息至properties文件
	 * 
	 * 如：用户设置的访问控制结构policy
	 */
	public static void storeProperties(String key, String value) {
		Properties prop = new Properties();
		// /保存属�?到b.properties文件
		FileOutputStream oFile;
		try {
			File infoPropDir = new File("./filetemp/");
			if (!infoPropDir.exists()) {
				infoPropDir.mkdirs();
			}
			// File infoPropFile = new File("./filetemp/info.properties");
			// if(){
			//
			// }
			oFile = new FileOutputStream("./filetemp/info.properties", true);// true表示追加打开
			prop.setProperty(key, value);
			prop.store(oFile, "The New properties file");
			oFile.close();
		} catch (IOException e) {
			// Discard
		}
	}

	/**
	 * 将指定文件夹下的�?��文件的文件名(不含路径�?按规定的格式组装成一个字符串
	 * 
	 * 格式�?filenamestring1?filenamestring2?filenamestring3?…�?
	 * */
	public static String getDirFileNamesList(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String allFileNames = new String();
		File dir = new File(dirPath);
		File[] fileArray = dir.listFiles();
		if (fileArray == null) {
			return "?";
		}
		for (int i = 0; i < fileArray.length; i++) {
			String filename = null;
			if (fileArray[i].isFile()) {
				filename = "?" + fileArray[i].getName();
				allFileNames += filename;
			}
		}
		return allFileNames;
	}

	public static List<String> parseStringToAttributeList(String attrSetString) {
		List<String> attributeList = new ArrayList<String>();
		String[] allAttrStr = attrSetString.split("\\?");// �?分割字符串生成数�?
		for (int i = 0; i < allAttrStr.length; i++) {
			if (allAttrStr[i].length() > 0) {
				attributeList.add(allAttrStr[i]);
			}
		}
		return attributeList;
	}

	/**
	 * 将由拂去其发送过来的�?��文件名组成的字符串解析成 directoryName—�?fileNames（一对多�?
	 * 
	 * 返回结果用Map<String, List<String>>
	 * 
	 * @author
	 * @param allFileNames
	 * @return dirFileMap
	 * */
	public static Map<String, List<String>> parseStringToDirFile(
			String allFileNames) {
		List<String> nameList = new ArrayList<String>();
		String[] allFileNameses = allFileNames.split("\\?");// �?分割字符串生成数�?
		for (int i = 0; i < allFileNameses.length; i++) {
			if (allFileNameses[i].length() > 0) {
				nameList.add(allFileNameses[i]);
			}
		}

		/**
		 * 将projectName与baldfileName 分别存进projectList、baldNameList
		 * */
		List<String> projectList = new ArrayList<String>();
		List<String> serverFileNameList = new ArrayList<String>();
		for (int i = 0; i < nameList.size(); i++) {
			int index = nameList.get(i).indexOf("-");
			projectList.add(nameList.get(i).substring(0, index));
			serverFileNameList.add(nameList.get(i));
			// String substring1 = nameList.get(i).substring(index + 1);
			// int index2 = substring1.indexOf("-");
			// baldNameList.add((String) substring1.substring(0, index2));
		}

		/**
		 * 按照dirName-->fileName 存进map�?
		 * */

		Map<String, List<String>> dirFileMap = new HashMap<String, List<String>>();
		for (int i = 0; i < projectList.size(); i++) {
			List<String> dirFileNameList = new ArrayList<String>();
			String projectName = projectList.get(i);
			if (dirFileMap.containsKey(projectName)) {
				List<String> strings = dirFileMap.get(projectName);
				strings.add(serverFileNameList.get(i));
				dirFileMap.put(projectName, strings);
				;
			} else {
				dirFileNameList.add(serverFileNameList.get(i));
				dirFileMap.put(projectName, dirFileNameList);
			}
		}
		return dirFileMap;
	}

	/**
	 * 将来自客户端发�?过来的服务信�?键�?对，如：?policy:content?profile:content?…�?)
	 * 解析存进Map<String, String>中，以待下一步处�?
	 * 
	 * 注：强制限制只有两个键�?对，policy，和profile�?
	 * 
	 * @author
	 * @param serviceContent
	 * @return serviceInfoMap
	 * */
	public static Map<String, String> splitServiceContentToKeyValue(
			String serviceContent) {

		Map<String, String> serviceInfoMap = new HashMap<String, String>();
		List<String> serviceInfoList = new ArrayList<String>();
		String[] serviceInfos = serviceContent.split("\\?");
		/**
		 * 将string[]转换成List<string>，并且去掉由第一�??"造成的空字符�?
		 * */
		for (int i = 0; i < serviceInfos.length; i++) {
			if (serviceInfos[i].length() > 0) {
				serviceInfoList.add(serviceInfos[i]);
			}
		}

		for (int i = 0; i < serviceInfoList.size(); i++) {
			int index = serviceInfoList.get(i).indexOf(":");
			String key = serviceInfoList.get(i).substring(0, index);
			String value = serviceInfoList.get(i).substring(index + 1);
			serviceInfoMap.put(key, value);
		}
		return serviceInfoMap;
	}

	public static void recurDelete(File f) {
		for (File fi : f.listFiles()) {
			if (fi.isDirectory()) {
				recurDelete(fi);
			} else {
				fi.delete();
			}
		}
		f.delete();
	}

	public static void deleteRootDir(String pathName) {
		File f = new File(pathName);
		if (f.exists()) {
			// System.out.println(f.getCanonicalPath());
			recurDelete(f);
		}
		File file = new File(pathName);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static void main(String[] args) {
//		storeProperties("a", "kalsdfaksld");
//		loadProperties("./fileTemp/attribute.properties");
		AttributeSet attributeSet = loadAttrProp("./fileTemp/attribute.properties");
		System.out.println(splitAttribute(attributeSet).getAttr_4());
	}
}
