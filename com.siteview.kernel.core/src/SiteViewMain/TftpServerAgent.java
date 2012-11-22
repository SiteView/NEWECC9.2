/*
 * tftpServerAgent.java
 *
 * Created on 2007年4月30日, 下午1:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package SiteViewMain;

import java.net.*;
import java.io.*;
import java.util.*;

import COM.dragonflow.StandardMonitor.ConfigCopy;
import COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor;

/**
 * 
 * @author Administrator
 */
public class TftpServerAgent extends Thread {

	/** Creates a new instance of tftpServerAgent */
	public TftpServerAgent() {
		this.m_hashClients = new Hashtable();
	}

	private int n = 0;
	private Hashtable m_hashClients;

	// over the parent's run methord
	public void run() {
		try {
			DatagramSocket tftpd = null;

			tftpd = new DatagramSocket(69);
			// tftp server socket
			byte[] buf = new byte[516]; // a buffer for UDP packet
			DatagramPacket dp = new DatagramPacket(buf, 516); // a UDP packet

			DataInputStream din = null;
			TftpClientAgent newClient = null;
			short tftp_opcode = 0; // opcode: the 2 bytes in the front of tftp
									// packet
			String tftp_filename = null;
			String tftp_mode = null;
			while (true) {

				tftpd.receive(dp); // wait for a client
				buf = dp.getData(); // get the UDP packet data
				din = new DataInputStream(new ByteArrayInputStream(buf));
				tftp_opcode = din.readShort(); // get the opcode
				{ // get the filename
					int fnoffset = 2;
					int fnlen = 0;
					while (din.readByte() != 0) {
						fnlen++; // filename will end with a null char('\0')
					}
//					String ConfigURL=ConfigDownLoadUploadMonitor.ConfigURL;
					tftp_filename = new String(buf, fnoffset, fnlen);
					tftp_filename =ConfigCopy.getTempUrl()+"/"+tftp_filename;// 新下载下来的文件存放位置
//					System.out.println("tftp_filename=="+tftp_filename);
					// get the mode
					int mdnoffset = fnoffset + fnlen + 2;
					int mdnlen = 0;
					while (din.readByte() != 0) {
						mdnlen++; // filename will end with a null char('\0')
					}
					tftp_mode = new String(buf, mdnoffset, mdnlen);
				}

				switch (tftp_opcode) {
				case 1:
					// RRQ
				case 2:
					// WRQ
					newClient = new TftpClientAgent(dp.getAddress(),
							dp.getPort(), tftp_opcode, tftp_filename, tftp_mode);
					newClient.setDaemon(true);
					newClient.start();

					this.m_hashClients.put(
							dp.getAddress().toString() + dp.getPort(),
							newClient);
					// System.out.println("debug: Main.main() --> a RRQ thread start ....");
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
