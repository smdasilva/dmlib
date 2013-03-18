package src;

public class RsyncModel {

public static void execute(String src, String dest) throws Exception{
String cmd[] = new String[]{"rsync", "-az", src, dest};
ProcessBuilder pb = new ProcessBuilder(cmd);
Process p = pb.start();
int val = p.waitFor();

if (val != 0)
throw new Exception("Exception during RSync; return code = " + val);
}
}