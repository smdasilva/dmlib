package ped.dmlib.connexion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ped.dmlib.Util;

public class Config {
	private static final String HOOK = "[hooks]\n" +
			"changegroup.update = print(\"UPDATE\")";
	
	private static final String ALLOW_PUSH = "[web]\n" +
			"push_ssl = false\n" +
			"allow_push = *\n";
	
	private String installationDir;
	private String localConfigDir;
	private String repositoryDir;
	private String remoteConfigDir;
	private String metaDir;
	private String hashDir;
	private String hgPath;
	private String hgrcPath;

	public Config(String installationDir) {
		this.installationDir = installationDir;
		
		localConfigDir = installationDir + "/.config/";
		repositoryDir = installationDir + "/hg/";
		remoteConfigDir = repositoryDir + ".config/";
		metaDir = repositoryDir + "Meta/";
		hashDir = repositoryDir + "Hash/";
		hgPath = repositoryDir + ".hg/";
		hgrcPath = hgPath + "hgrc";
	}
	
	public void initAll() {
		initDirTree();
		initHgrc();
	}

	public void initDirTree() {
		createDirTree(localConfigDir);
		createDirTree(repositoryDir);
		createDirTree(remoteConfigDir);
		createDirTree(metaDir);
		createDirTree(hashDir);
		createDirTree(hgPath);
	}
	
	private void createDirTree(String dirPath) {
		File f = new File(dirPath);
		f.mkdirs();
	}
	
	public void initHgrc() {
		try {
			Util.clearFile(hgrcPath);
			Util.addLineIntoFile(hgrcPath, ALLOW_PUSH);
			Util.addLineIntoFile(hgrcPath, HOOK);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
