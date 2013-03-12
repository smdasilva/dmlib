package mercurial;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


import com.aragost.javahg.*;
import com.aragost.javahg.internals.Server;
import com.aragost.javahg.internals.Utils;
import com.google.common.io.Files;


/**
 * A very basic server implementation.
 * <p>
 * It is not used for anything, but sometimes useful to reproduce
 * bugs.
 */
public class SimpleTestServer {

    private File directory;

    private String hgBin = "hg";

    private InputStream input;
    private InputStream error;
    private OutputStream output;

    private Thread errorReaderThread = new Thread() {
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(SimpleTestServer.this.error));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.err.println("error: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    };

    public SimpleTestServer(File directory) {
        this.directory = directory;
    }

    public void start() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(this.hgBin, "serve", "--cmdserve", "pipe", "--config",
                "ui.interactive=true");
        processBuilder.directory(this.directory);
        Process process = processBuilder.start();

        this.input = process.getInputStream();
        this.error = process.getErrorStream();
        this.output = process.getOutputStream();
        this.errorReaderThread.start();
        readChannel();
        int length = readLength();
        byte[] bytes = new byte[length];
        int n = this.input.read(bytes);
        if (n != length) {
            throw new RuntimeException("Wrong number of bytes read");
        }
        System.out.println(new String(bytes));
    }

    public void stop() throws IOException {
        this.input.close();
        this.error.close();
        this.output.close();
    }

    private void processInput() throws IOException {
        while (true) {
            int channel = readChannel();
            if (channel == -1) {
                return;
            }
            int length = readLength();
            switch (channel) {
            case 'o':
            case 'e':
                System.out.print("" + (char) channel + length + ":");
                byte[] bytes = new byte[length];
                int n = this.input.read(bytes);
                if (n != length) {
                    throw new RuntimeException("Wrong number of bytes read");
                }
                System.out.println(new String(bytes));
                break;
            case 'r':
                int rc = readLength();
                System.out.println("rc=" + rc);
                return;
            default:
                throw new RuntimeException("Unsupported channel: " + channel);
            }
        }
    }

    private int readLength() throws IOException {
        return Utils.readBigEndian(this.input);
    }

    private int readChannel() throws IOException {
        return this.input.read();
    }

    public void run(String... args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(args[0].getBytes());
        for (int i = 1; i < args.length; i++) {
            baos.write(0);
            baos.write(args[i].getBytes());
        }
        this.output.write("runcommand\n".getBytes());
        Utils.writeBigEndian(baos.size(), this.output);
        this.output.write(baos.toByteArray());
        this.output.flush();
        processInput();
    }
    
    
    public static File createMercurialRepository(String path) {
    	File dir = new File(path);
    	if (!dir.exists()) {
    	new File(path).mkdir(); 
        Server server = new Server(RepositoryConfiguration.DEFAULT.getHgBin(),
                                   RepositoryConfiguration.DEFAULT.getEncoding());
        
        server.initMecurialRepository(dir);
    	}
        return dir;
    }

    public static void main(String[] args) throws IOException {
    	File dir = createMercurialRepository("/net/cremi/abndoye/espaces/travail/MYDEARTEST/");
    	
        //Runtime.getRuntime().exec("hg init " + dir.getAbsolutePath());
        SimpleTestServer s = new SimpleTestServer(dir);
        s.start();
        
        File file = new File(dir, "a");
        
        FileOutputStream out = new FileOutputStream(file);
        out.write("a".getBytes());
        out.close();
       
        s.run("add", "--debug");
        s.run("commit", "--debug", "-mm");

        out = new FileOutputStream(file);
        out.write("b".getBytes());
        out.close();

        s.run("add", "--debug");
        s.run("commit", "--debug", "-mm");
        s.run("rollback");
        s.run("log", "--rev", "0", "--debug", "--template", "{node}");
        s.run("status");
        s.run("add", "--debug");
        s.run("commit", "-mm");
      

        s.stop();
        
    }
    
    public static File createTempDirectory()
    	    throws IOException
    	{
    	    final File temp;

    	    temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

    	    if(!(temp.delete()))
    	    {
    	        throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
    	    }

    	    if(!(temp.mkdir()))
    	    {
    	        throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
    	    }

    	    return (temp);
    	}
}