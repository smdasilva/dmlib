package ped.dmlib.temp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class App 
{
    @SuppressWarnings("restriction")
	public static void main( String[] args )
    {
        Repo r1 = new Repo("toto", "127.0.0.1", 8000);
        Repo r2 = new Repo("titi", "254.56.35.12", 8080);
        
        /*RepoList rl = new RepoList();
        rl.addRepo(r1);
        rl.addRepo(r2);
        
        r1.addLibrary("image", "c:/images");
        
        rl.save(".");
        
        JAXBContext context;
		try {
			context = JAXBContext.newInstance(Repo.class);
			Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
	        m.marshal(r1, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		FactoryRepo fr = new FactoryRepo("./");
		fr.addRemoteRepo(r2);
		fr.setLocalRepo(r1);
		
		fr.saveRepositories();
		
		FactoryRepo fr2 = new FactoryRepo("./");
		fr2.loadRepositories();
		
		System.out.println(fr2.getLocalRepo());
		System.out.println(fr.getRemoteRepositories());
		
//        Constructor constructor = new Constructor(Repo.class);//Car.class is root
//
//        Yaml yaml = new Yaml(constructor);
//        File f = new File("toto.txt");
//        try {
//			yaml.dump(r1,new FileWriter(f));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}
