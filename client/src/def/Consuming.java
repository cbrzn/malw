package def;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Consuming {
	public static String path = System.getProperty("user.dir");
	private static Socket s;
	static int PORT = 9006;
	static String IP = "localhost"; //use your ip

	public static void main(String[] args)  {
	    try {
			s = new Socket(IP ,PORT);	
	        System.err.println("-- Client  --");
			System.out.println("Connecting to Server ->" +  IP  + "/" + PORT);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			DataInputStream dis = new DataInputStream(ois);
			int number_of_files = dis.readInt();	
			int i = 0;
			File[] files = new File[number_of_files];
			while (i < number_of_files) {
				long fileLength = dis.readLong();		
			    String fileName = dis.readUTF();
				if (fileName != "/Serv.jar"){					
				    files[i] = new File(path + "/" + fileName);
					
				    FileOutputStream fos = new FileOutputStream(files[i]);
				    BufferedOutputStream bos = new BufferedOutputStream(fos);
	
				    System.out.println("working");		    	
				    for(int j = 0; j < fileLength; j++) bos.write(ois.read());
	
				    bos.close();
					System.out.println("File #"+i+ " recieved from server.");
					fos.close();				
				}
				i++;
    		}
			dis.close();
				   
	//			System.out.println("Commands: "+" -> Create "+" -> Delete "+" -> Download "+" -> Upload");
	//			System.out.println("C:>");
	//			String inp = input.nextLine();
	//			OutputStream os = s.getOutputStream();
	//			ObjectOutputStream oos = new ObjectOutputStream(os);
	//			oos.writeObject(inp);
	//			String[] cmds = inp.split(" ");
	//				switch (cmds[0]) {
	//						case "upload":
	//							upload(cmds[1]);
	//							System.out.println("File uploaded");
	//							break;
	//						case "download":
	//							download(cmds[1]);	
	//							System.out.println("File downloaded");
	//							break;
	//					}	
	//				 
	//				 ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
	//				 System.out.println((String)ois.readObject());
	//				 os.close();
	//		}
	//				
	//
	//			} catch (Exception e) { 
	//				e.printStackTrace();
	//		}
	//	}
	//	
	//
	//	public   static void upload(String fileName) {
	//	    try {
	//	        File myFile = new File(path + fileName);
	//	        byte[] buffer = new byte[1024];
	//	        InputStream is = new FileInputStream(myFile);
	//	        OutputStream os = s.getOutputStream(); 
	//	        int count;
	//	        while ((count = is.read(buffer)) > 0) {
	//	        	os.write(buffer, 0, count);	
	//	        }		    
	//		    os.close();
	//		    is.close();
	//	        System.out.println("File "+fileName+" sent to Server.");
	//	    } catch (Exception e) {
	//	        System.err.println("File does not exist!");
	//	    }
	//	}
	//
	//	public  static void download(String fileName) {
	//	    try {
	//	    	File f = new File(path + fileName);
	//			byte[] buffer = new byte[1024];
	//	    	InputStream is = s.getInputStream();
	//	    	OutputStream fos = new FileOutputStream(f);
	//	    	int count;
	//	    	while ((count = is.read(buffer)) > 0){
	//	    		fos.write(buffer, 0, count);
	//	    	}
	//	    	fos.close();
	//	    	is.close();
	//	        System.out.println("File "+fileName+" received from Server.");
	    } catch (Exception e) {   
	    	e.printStackTrace();
	    }
	}
}
