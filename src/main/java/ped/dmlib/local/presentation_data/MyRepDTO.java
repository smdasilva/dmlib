package ped.dmlib.local.presentation_data;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyRepDTO extends DefaultMutableTreeNode
{
	private static final long serialVersionUID = 1L;
	
	private int idRep;
	private String absolutePath;
	
	public MyRepDTO(Object o, int idRep, String absolutePath)
	{
		super(o);
		this.setId(idRep);
		this.setAbsolutePath(absolutePath);
	}

	//Getters and Setters
	public void setId(int idRep) {
		this.idRep = idRep;
	}
	public int getId() {
		return idRep;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
}
