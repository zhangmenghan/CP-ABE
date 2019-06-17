package cpabe.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import login_gui.Fault;

public class OpenLocalFile {
	/**
	 * 鍊熷姪java.awt.Desktop鎵撳紑
	 * 
	 * @see 鎵撳紑鐨勭洰褰曟垨鏂囦欢鍚嶄腑鍏佽鍖呭惈绌烘牸
	 */
	@SuppressWarnings("unused")
	public static void useAWTDesktop(String localFilePath) {
		File file = new File(localFilePath);
		if (!file.exists()) {
			return;
		}
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			new Fault("本地文件打开失败！");
			//Discard
		}
	}

	/**
	 * 鍊熷姪cmd鍛戒护鎵撳紑
	 */
	public static void useCMDCommand(String localFilePath) {
//		if (!(new File(localFilePath).exists())) {
//			Client.message = "file is not exist";
//			return;
//		}
		try {
			Runtime.getRuntime().exec(
					new String[] { "cmd.exe", "/c", localFilePath });
		} catch (IOException e) {
			new Fault("本地文件打开失败！");
			//Discard
		}
	}
}
