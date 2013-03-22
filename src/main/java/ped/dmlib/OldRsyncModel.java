package ped.dmlib;

public class OldRsyncModel {
	
	public static void execute(String src, String dest) throws Exception{
		String cmd[] = new String[]{"rsync", "-az", src, dest};
		ProcessBuilder pb = new ProcessBuilder(cmd);
		Process p = pb.start();
		int val = p.waitFor();

		System.out.println("Val = " + val);
		
		if (val != 0)
			throw new Exception("Exception during RSync; return code = " + val);
	}
}