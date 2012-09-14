package COM.dragonflow.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import COM.dragonflow.Properties.StringProperty;
import COM.dragonflow.SiteView.Machine;

public class MachineUtil {
	
//	public static void saveMachine(Machine machine){
//		System.out.println("¥Ê¥¢Mahcine");
//		ObjectOutputStream oout = null;
//		try {
//			oout = new ObjectOutputStream(new FileOutputStream("D:/123.obj"));
//			oout.writeObject(machine);
//			oout.flush();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				oout.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public static Machine getMachine(String fileName){
//		System.out.println("∂¡»°Mahcine");
//		fileName += ".obj";
//		ObjectInputStream oin = null;
//		Machine machine = null;
//		try {
//			oin = new ObjectInputStream(new FileInputStream("D:/123.obj"));
//			machine = (Machine)oin.readObject();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				oin.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(machine);
//		return machine;
//	}

}
