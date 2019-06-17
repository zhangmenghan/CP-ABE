package cpabe.entity;

public class FileDownloadInfo {
	private String file_name;
	private String download_staff_id;
	private String download_time;
	private int download_times;

	public FileDownloadInfo(){
		
	}
	
	public FileDownloadInfo(String file_name, String download_staff_id,
			String download_time, int download_times) {
		super();
		this.file_name = file_name;
		this.download_staff_id = download_staff_id;
		this.download_time = download_time;
		this.download_times = download_times;
	}

	

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getDownload_staff_id() {
		return download_staff_id;
	}

	public void setDownload_staff_id(String download_staff_id) {
		this.download_staff_id = download_staff_id;
	}

	public String getDownload_time() {
		return download_time;
	}

	public void setDownload_time(String download_time) {
		this.download_time = download_time;
	}

	public int getDownload_times() {
		return download_times;
	}

	public void setDownload_times(int download_times) {
		this.download_times = download_times;
	}
}
