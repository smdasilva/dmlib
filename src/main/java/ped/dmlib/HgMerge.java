package ped.dmlib;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Assert;

import com.aragost.javahg.BaseRepository;
import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import com.aragost.javahg.commands.AddCommand;
import com.aragost.javahg.commands.CommitCommand;
import com.aragost.javahg.commands.PullCommand;
import com.aragost.javahg.commands.PushCommand;
import com.aragost.javahg.commands.ResolveCommand;
import com.aragost.javahg.commands.UpdateCommand;
import com.aragost.javahg.internals.Utils;
import com.aragost.javahg.merge.ConflictResolvingContext;
import com.aragost.javahg.merge.MergeConflict;
import com.google.common.io.Files;

public class HgMerge {
	
	private BaseRepository testRepository;
	private static int count = 0;
	
	public static void main( String[] args )
    {
        HgMerge hm = new HgMerge();
        try {
			hm.testMergeConflict();
			hm.closeTestRepository();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void testMergeConflict() throws IOException{
		Repository repo = getTestRepository();
	    // Merge conflict in a can not be resolved with internal:merge
	    // in b the conflict can
	    writeFile("a");
	    writeFile("b", "a\na\na\na\na\n");
	    Changeset base = commit();
	    writeFile("a", "XX");
	    writeFile("b", "X\na\na\na\na\n");
	    Changeset parent2 = commit();
	    update(base);
	    writeFile("a", "YY");
	    writeFile("b", "a\na\na\nY\na\n");
	    commit();
	
	    ConflictResolvingContext mergeState = repo.workingCopy().merge(parent2);
	    List<MergeConflict> mergeConflicts = mergeState.getMergeConflicts();
//	    Assert.assertEquals(2, mergeConflicts.size());
	    MergeConflict mca = mergeConflicts.get(0);
	    MergeConflict mcb = mergeConflicts.get(1);
//	    Assert.assertEquals("a", mca.getFilename());
//	    Assert.assertEquals("b", mcb.getFilename());
//	
//	    Assert.assertFalse(mca.isResolved());
//	    Assert.assertFalse(mcb.isResolved());
	
	    mca.resolveWithInternalMerge();
	    mcb.resolveWithInternalMerge();
	    	    
//	    Assert.assertFalse(mca.isResolved());
//	    Assert.assertTrue(mcb.isResolved());
	    
	    
	}
	
	public List<Changeset> localMerge() throws IOException{
		PushCommand pc = PushCommand.on(testRepository);
		pc.force();
		return pc.execute();
	}
	
	public List<Changeset> serverMerge() throws IOException {
		PullCommand pl = PullCommand.on(testRepository);
		pl.force();
		return pl.execute();
    }
	
	public boolean internalMerge(MergeConflict mc) {
        return mc.resolveWith("internal:merge");
    }
	
	public BaseRepository getTestRepository() {
        if (this.testRepository == null) {
            File dir = Files.createTempDir();
            this.testRepository = Repository.create(dir);
        }
        return this.testRepository;
    }
	
	public Changeset commit() throws IOException {
        Repository repo = getTestRepository();
        AddCommand.on(repo).execute();
        CommitCommand cmd = CommitCommand.on(repo).user("testcase").message("testcase: " + getClass().getName());
        return cmd.execute();
    }
	
	public void update(Changeset cs) throws IOException {
        UpdateCommand.on(getTestRepository()).clean().rev(cs.getNode()).execute();
    }
	
	public void closeTestRepository() throws IOException {
        if (this.testRepository != null) {
            this.testRepository.close();
            deleteTempDir(this.testRepository.getDirectory());
            this.testRepository = null;
        }
    }
	
	public static void deleteTempDir(File file) throws IOException {
        Utils.deleteTempDir(file);
    }
	
	public void writeFile(String name, String content) throws IOException {
        writeFile(getTestRepository(), name, content);
    }

    public void writeFile(BaseRepository repo, String name, String content) throws IOException {
        File file = new File(repo.getDirectory(), name);
        Files.write(content, file, utf8());
    }
    
    public void writeFile(String name) throws IOException {
        writeFile(name, String.valueOf(count++) + "\n");
    }
    
    public Charset utf8() {
        return Charset.forName("UTF-8");
    }
}
