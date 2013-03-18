public class App 
{
    public static void main( String[] args )
    {     
        RepositoriesList servers = new RepositoriesList();
        
        ComputerRepository r1 = new ComputerRepository("toto", "127.0.0.1");
        r1.addLibrary("music", "/home/music");
        r1.addLibrary("image", "/home/image");
        
        ComputerRepository r2 = new ComputerRepository("titi", "127.127.127.1");
        r2.addLibrary("image", "/home/titi/image");
        r2.addLibrary("music", "/home/titi/music");       
        
        servers.addRepository(r1);
        servers.addRepository(r2);
        
        String tmp = servers.save();
        System.out.println(tmp);
        
        RepositoriesList servers2 = new RepositoriesList();
        servers2.load(tmp);
        
        System.out.println("---------\\\\\\-------------");
        
        System.out.println(servers2.save());
        
        System.out.println("---------\\\\\\-------------");
        
        System.out.println(r1.getAddress() + " -------- " + r1.getURI("image"));
        System.out.println(r2.getAddress() + " -------- " + r2.getURI("music"));
        
        
        /*BaseRepository br;
        File f = new File("/tmp");
        
        try {
        	br = Repository.open(f);
        } catch (IllegalArgumentException iae) {
        	br = Repository.create(f);
        }
    
        br.close();*/
        
    }
}