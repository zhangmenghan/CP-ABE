package cpabe.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cpabe.entity.FileDownloadInfo;
import cpabe.entity.FileInfo;
import cpabe.entityDAO.FileDownLoadInfoDAO;
import cpabe.entityDAO.FileInfoDAO;
import cpabe.utils.CpabeException;

public class FileService {

	public void createFileInfoRecord(FileInfo fileInfo) throws CpabeException {
		FileInfoDAO dao = new FileInfoDAO();
		if ((dao.getByFile_name(fileInfo.getName())) != null) {
			throw new CpabeException("file is exist!");
		}
		dao.save(fileInfo);
	}

	public void addFileDownloadInfoRec(FileDownloadInfo downloadInfo) {
		FileDownLoadInfoDAO downloadInfoDao = new FileDownLoadInfoDAO();
		downloadInfoDao.save(downloadInfo);
	}

	/**
	 * modify the fileDownloadInfo, and modify the total download_times in fileInfo
	 * 
	 * if has no record in database than save,else update
	 * 
	 * @param download_staff_id
	 * @param serverFileName
	 * @param downloadInfo
	 * */
	public void modifyInfoAboutDownload(String download_staff_id,
			String serverFileName) {

		/**
		 * modify FileDownLoadInfoDAO;
		 * */
		FileDownLoadInfoDAO downLoadInfoDAO = new FileDownLoadInfoDAO();
		FileDownloadInfo info = downLoadInfoDAO.findByStaff_idAndFileName(
				download_staff_id, serverFileName);
		if (info != null) {
			int downloadtimes = info.getDownload_times();
			info.setDownload_times(downloadtimes + 1);
			downLoadInfoDAO.update(info);
		} else {
			String downloadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			//create FileDownloadInfo record
			FileDownloadInfo downloadInfo = new FileDownloadInfo();
			downloadInfo.setDownload_staff_id(download_staff_id);
			downloadInfo.setFile_name(serverFileName);
			downloadInfo.setDownload_times(1);
			downloadInfo.setDownload_time(downloadTime);
			//save FileDownloadInfo record
			downLoadInfoDAO.save(downloadInfo);
		}
		
		/**
		 * modify FileInfoDAO;
		 * */
		FileInfoDAO fileInfoDAO = new FileInfoDAO();
		FileInfo fileInfo = fileInfoDAO.getByFile_name(serverFileName);
		if (fileInfo != null) {
			int times = fileInfo.getDownload_times() + 1;
			fileInfo.setDownload_times(times);
			fileInfoDAO.update(fileInfo);
		}
	}

	public void modifyTotalDownloadTimes(String fileNameInServer) {
		FileInfoDAO dao = new FileInfoDAO();
		FileInfo fileInfo = dao.getByFile_name(fileNameInServer);
		int totalDownloadTimes = fileInfo.getDownload_times() + 1;
		fileInfo.setDownload_times(totalDownloadTimes);
		dao.update(fileInfo);
	}

	public List<FileInfo> getAllFileInfos() {
		FileInfoDAO dao = new FileInfoDAO();
		return dao.getAllFileInfos();
	}
	
	public String findPolicyByFileName(String serverFileName){
//		String policy = null;
		FileInfoDAO dao = new FileInfoDAO();
		FileInfo fileInfo = dao.getByFile_name(serverFileName);
		return fileInfo.getPolicy();
	}
	
	/**
	 * this method used to refresh the file info in ServerPage
	 * */
	public Object[][] refresh(){
		List<FileInfo> fileInfos = this.getAllFileInfos();
		Object[][] data = new Object[fileInfos.size()][3];
		for (int i = 0; i < fileInfos.size(); i++) {
			data[i][0] = fileInfos.get(i).getName();
			data[i][1] = fileInfos.get(i).getFilesize();
			data[i][2] = fileInfos.get(i).getDownload_times();
		}
		return data; 
	}

}
