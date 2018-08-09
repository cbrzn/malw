package core;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


	public class SocketServer extends Thread{
		private static Socket s = null;
	    public static String path = System.getProperty("user.dir");
	    static Scanner input = new Scanner (System.in);
			
	    
	    SocketServer(Socket s) {
	    	SocketServer.s = s;
	    }
	    
	    public void run(){
				menu();	
			}
	    
	    
	    public static void menu() {
				try {
					System.out.println(path);
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					DataOutputStream dos = new DataOutputStream(oos);

					File folder = new File(path);
					File[] listOfFiles = folder.listFiles();
					dos.writeInt(listOfFiles.length);
					    for (File file: listOfFiles) {
					      if (file.isFile()) {
					    	  
					    	dos.writeLong(file.length());
					    	dos.writeUTF(file.getName());
					    	
					        FileInputStream fis = new FileInputStream(file);
					        BufferedInputStream bis = new BufferedInputStream(fis);
					        
					        int counter = 0;
					        while ((counter = bis.read()) != -1) oos.writeByte(counter);
					        System.out.println("File #"+file+" sent to client.");
					        bis.close();
					      
					      } else if (file.isDirectory()) {
					        System.out.println("Directory " + file.getName());
					      }
					    }
					    oos.close();
//					while(true){
//					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//					String msg = (String) ois.readObject();
//					String[] cmds = msg.split(" ");
//					switch(cmds[0]){
//					case "create":
//					File f = new File(cmds[1]);
//						if(!f.exists()){
//							create(cmds[1]);
//							oos.writeObject(">> File Created");	}
//						else{
//							create(cmds[1]);
//							oos.writeObject(">> File not created");	}
//						break;
//						
//					case "delete":
//						File f1 = new File(cmds[1]);
//							delete(cmds[1]);
//							if(!f1.exists()){
//								oos.writeObject(" File Deleted ");	}	
//							else{
//								oos.writeObject(" File not found");	}
//						break;
//
//					case "download":
//						download(cmds[1]);
//						oos.writeObject("Success");
//						break;
//						
//					case "upload":
//						upload(cmds[1]);
//						oos.writeObject("Success");
//						break;
//						
//					default:
//						System.out.println("Undefined Operation");
//						oos.writeObject("Undefined Operation");
//						break;
//					}//case
//				oos.close();
//				ois.close();
//					}
				} catch (Exception e) {
					 e.printStackTrace();	
				}
			}
				  
//			
//			public static void download (String filename){
//				 try {
//				       File myFile = new File(path + filename);
//				       byte[] buffer = new byte[(int)myFile.length()];
//				       InputStream is = new FileInputStream(myFile);
//				       BufferedInputStream bis = new BufferedInputStream(is);
//				       bis.read(buffer, 0, buffer.length);
//				       OutputStream os = s.getOutputStream();
//				       os.write(buffer, 0 ,buffer.length);
//				       os.flush();
//				       System.out.println("File "+ filename +" sent to client.");
//				   } catch (Exception e) {
//				       System.err.println("File does not exist!");
//				   }	
//			}
	}

