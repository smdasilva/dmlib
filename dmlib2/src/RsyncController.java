public class RsyncController {
	private String srcPath;
	private String srcUser;
	private String srcHost;

	private String destPath;
	private String destUser;
	private String destHost;
	
	public RsyncController() {
		clearInfos();
	}

	public RsyncController(String srcPath, String srcUser, String srcHost,
			String destPath, String destUser, String destHost) {
		init(srcPath, srcUser, srcHost, destPath, destUser, destHost);
	}

	private void init(String srcPath, String srcUser, String srcHost,
			String destPath, String destUser, String destHost) {
		clearInfos();

		this.srcPath = srcPath;
		this.srcHost = srcHost;
		if((srcHost != null) && (!srcHost.isEmpty()))
			this.srcUser = srcUser;
		else
			this.srcUser = null;

		this.destPath = destPath;
		if((destHost != null) && (!destHost.isEmpty()))
			this.destUser = destUser;
		else
			this.destUser = null;
		this.destHost = destHost;
	}

	public void setInfos(String srcPath, String srcUser, String srcHost,
			String destPath, String destUser, String destHost) {
		this.init(srcPath, srcUser, srcHost, destPath, destUser, destHost);
	}

	public void setInfos(ComputerRepository src, ComputerRepository dest, String libraryName, String filePath) {
		clearInfos();

		this.srcHost = src.getAddress();
		this.srcPath = src.getLibraryPath(libraryName) + filePath;
		
		this.destHost = dest.getAddress();
		this.destPath = dest.getLibraryPath(libraryName) + filePath;
	}

	private void clearInfos() {
		this.srcHost = this.srcPath = this.srcUser = "";
		this.destHost = this.destPath = this.destUser = "";
	}

	public void execute() {
		String tmpsrc = "";
		if((srcUser != null) && (!srcUser.isEmpty()))
			tmpsrc += srcUser + "@";

		if((srcHost != null) && (!srcHost.isEmpty()))
			tmpsrc += srcHost + ":";

		tmpsrc += srcPath;

		String tmpdest = "";
		if((destUser != null) && (!destUser.isEmpty()))
			tmpdest += destUser + "@";

		if((destHost != null) && (!destHost.isEmpty()))
			tmpdest += destHost + ":";

		tmpdest += destPath;

		try {
			RsyncModel.execute(tmpsrc, tmpdest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}