package ped.dmlib.local.model;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.jheader.JpegFormatException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;

import ped.dmlib.Util;
import ped.dmlib.local.controller.LocalController;
import ped.dmlib.local.laucher.MainAppLauncher;

public class MyFileWatcher extends Thread
{
	private WatchService watcher;
	private Map<WatchKey,Path> keys;
	private boolean recursive;
	private boolean trace = false;
	
	//Constructor : Creates a WatchService and registers the given directory
	public MyFileWatcher(Path dir, boolean recursive) throws IOException
	{
		this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.setRecursive(recursive);

        if (recursive) {
            System.out.format("Scanning %s ...\n", dir);
            registerAll(dir);
            System.out.println("Done.");
        } 
        else {
            register(dir);
        }

        // enable trace after initial registration
        this.trace = true;
	}
	
	//Register the given directory with the WatchService
    private void register(Path dir) throws IOException 
    {
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		if (trace) {
		    Path prev = keys.get(key);
		    if (prev == null) {
		        System.out.format("register: %s\n", dir);
		    } 
		    else {
		    	if (!dir.equals(prev)) {
		    		System.out.format("update: %s -> %s\n", prev, dir);
		        }
		    }
		}
		keys.put(key, dir);
    }
    
    //Register the given directory, and all its sub-directories, with the WatchService.
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() 
		{
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
			{
				register(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}
    
    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() {
        for (;;) {
        	
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                @SuppressWarnings("rawtypes")
				WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path name = ev.context();
                Path child = dir.resolve(name);

                // print out event
                System.out.format("%s: %s\n", event.kind().name(), child);
                
                if(kind == ENTRY_CREATE)
                {
                	File f = new File(child.toString());
                	if(f.isDirectory() && !f.getParentFile().getName().equals("Meta"))
                	{
                		MainAppLauncher.getMainPanel().getTreePanel().refreshTreePanel(f);
                	}
                	else if(!f.isDirectory() && (Util.getExt(f.getAbsolutePath()).equals(".mp3") || Util.getExt(f.getAbsolutePath()).equals(".jpg")))
                	{
                		MainAppLauncher.getMainPanel().getServerController().addFile(f);
                	}
                }
                if(kind == ENTRY_MODIFY)
                {
                	File f = new File(child.toString());
                	if(!f.isDirectory())
                	{
                		if(Util.getExt(f.getAbsolutePath()).equals(".mp3") || Util.getExt(f.getAbsolutePath()).equals(".jpg")) {
                			MainAppLauncher.getMainPanel().getServerController().updateFile(f);
                		}
                		
                		else if(Util.getExt(f.getAbsolutePath()).equals(".mp3meta")) {
                			String repName = f.getParentFile().getName();
                			File filePath = new File(MainAppLauncher.getMainPanel().getServerController().getFileName(repName)+"/"+f.getName().replace(".mp3meta", ".mp3"));
                			System.out.println("POUET : ---------------------------"+filePath.getAbsolutePath());
                			System.out.println("POUET2 : ---------------------------"+f.getAbsolutePath());
                			
                			try {
								Mp3Meta mp3 = new Mp3Meta(filePath);
								mp3.saveTagFromFile(f.getAbsolutePath());
							} catch (CannotReadException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace(); 
							} catch (TagException e) {
								e.printStackTrace();
							} catch (ReadOnlyFileException e) {
								e.printStackTrace();
							} catch (InvalidAudioFrameException e) {
								e.printStackTrace();
							} catch (KeyNotFoundException e) {
								e.printStackTrace();
							} catch (CannotWriteException e) {
								e.printStackTrace();
							}
                		} else if(Util.getExt(f.getAbsolutePath()).equals(".jpgmeta")) {
                			String repName = f.getParentFile().getName();
                			File filePath = new File(MainAppLauncher.getMainPanel().getServerController().getFileName(repName)+"/"+f.getName().replace(".mp3meta", ".mp3"));

							try {
								JPGMeta2 jpg = new JPGMeta2(f.getAbsolutePath());
								jpg.saveTagFromFile(f.getAbsolutePath());
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (JpegFormatException e) {
								e.printStackTrace();
							} catch (FieldDataInvalidException e) {
								e.printStackTrace();
							} catch (CannotWriteException e) {
								e.printStackTrace();
							}
                	
                			
                		}
                	}
                }

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
    
    public void run() {
		processEvents();		
	}

    //Getters & Setters
	public boolean isRecursive() {
		return recursive;
	}
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}    
}
