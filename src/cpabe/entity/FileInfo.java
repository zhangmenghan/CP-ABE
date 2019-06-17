package cpabe.entity;

public class FileInfo {
	private int id;
	private String name;
	private String upload_staff_id;
	private String upload_date;
	private String policy;
	private String profile;
	private int download_times;
	private long filesize;

	public FileInfo() {
	}

	public FileInfo(int id, String name, String upload_staff_id,
			String upload_date, String policy, String profile,
			int download_times, long filesize) {
		super();
		this.id = id;
		this.name = name;
		this.upload_staff_id = upload_staff_id;
		this.upload_date = upload_date;
		this.policy = policy;
		this.profile = profile;
		this.download_times = download_times;
		this.filesize = filesize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpload_staff_id() {
		return upload_staff_id;
	}

	public void setUpload_staff_id(String upload_staff_id) {
		this.upload_staff_id = upload_staff_id;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public int getDownload_times() {
		return download_times;
	}

	public void setDownload_times(int download_times) {
		this.download_times = download_times;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

}
