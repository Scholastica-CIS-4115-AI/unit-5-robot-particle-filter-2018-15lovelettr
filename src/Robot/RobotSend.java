package Robot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;


public class RobotSend {
	// This class sends data to the robot in a basic protocol.
	//  All data is sent in integer pairs.  The first integer is the data type, on of the constants below.  The second integer is the value 
	// constants for types of data
	final int KINECT_SERVO = -1001;
	final int TILT_SERVO = -1002;
	final int MOTOR_LEFT = -1003;
	final int MOTOR_RIGHT = -1004;
	final int MOTOR_PWM = -1005;
	final int MOTOR_PWMSTOP = -1006;
	final int QUIT = -1099;
	
	NXTConnector conn = new NXTConnector();
	DataInputStream inDat;
	DataOutputStream outDat;
	

	public RobotSend () {
		System.out.println("RobotSend - 1");
		conn.addLogListener(new NXTCommLogListener(){

			public void logEvent(String message) {
				System.out.println("USBSend Log.listener: "+message);
				
			}

			public void logEvent(Throwable throwable) {
				System.out.println("USBSend Log.listener - stack trace: ");
				 throwable.printStackTrace();
				
			}
			
		} 
		);
		System.out.println("RobotSend - 2");
		if (!conn.connectTo("usb://")){
			System.err.println("No NXT found using USB");
			System.exit(1);
		}
		System.out.println("RobotSend - 3");
		inDat = new DataInputStream(conn.getInputStream());
		outDat = new DataOutputStream(conn.getOutputStream());
		System.out.println("RobotSend - 4");
	}
	
	public void send_kinect_angle(int ang) {
		
		try {
				System.out.println("send_kinect_angle 1 - " + KINECT_SERVO);   
				outDat.writeInt(KINECT_SERVO);
				outDat.flush();
				outDat.writeInt(ang);
				outDat.flush();
				System.out.println("send_kinect_angle 2 - " + ang);  
			} catch (IOException ioe) {
				System.err.println("IO Exception writing bytes");
			}
		System.out.println("send_kinect_angle - 5");
	}
	
	public void send_tilt_angle(int ang) {
		
		try {
				System.out.println("send_tilt_angle 1 - " + TILT_SERVO); 
				outDat.writeInt(TILT_SERVO);
				outDat.flush();
				outDat.writeInt(ang);
				outDat.flush();
				System.out.println("send_tilt_angle 2 - " + ang); 
	
			} catch (IOException ioe) {
				System.err.println("IO Exception writing bytes");
			}

	}
	
	public void send_mot_left(int pow) {
		
		try {
				System.out.println("send_mot_left - 1");
				outDat.writeInt(MOTOR_LEFT);
				outDat.flush();
				System.out.println("send_mot_left - 2");
				outDat.writeInt(pow);
				outDat.flush();
				System.out.println("send_mot_left: pow = "+pow);
	
			} catch (IOException ioe) {
				System.err.println("IO Exception writing bytes");
			}

	}

	public void send_mot_right(int pow) {
		
		try {
				System.out.println("send_mot_right - 1");
				outDat.writeInt(MOTOR_RIGHT);
				outDat.flush();
				System.out.println("send_mot_right - 2");
				outDat.writeInt(pow);
				outDat.flush();
				System.out.println("send_mot_right: pow = "+pow);
	
			} catch (IOException ioe) {
				System.err.println("IO Exception writing bytes");
			}

	}
	
	public void send_PWM(int pow, int speed) {
		
		try {
				outDat.writeInt(MOTOR_PWM);
				outDat.flush();
				outDat.writeInt(pow);
				outDat.flush();
				outDat.writeInt(speed);
				outDat.flush();
			} catch (IOException ioe) {
				System.err.println("IO Exception writing bytes");
			}

	}
	
	public void  send_PWMstop() {
		try {
			outDat.writeInt(MOTOR_PWMSTOP);
			outDat.flush();
		} catch (IOException ioe) {
			System.err.println("IO Exception writing bytes");
		}
	}
	
	public void  send_Quit() {
		try {
			outDat.writeInt(QUIT);
			outDat.flush();
		} catch (IOException ioe) {
			System.err.println("IO Exception writing bytes");
		}
	}

	public void close () {
		try {
			inDat.close();
			outDat.close();
			System.out.println("Closed data streams");
		} catch (IOException ioe) {
			System.err.println("IO Exception Closing connection");
		}
		
		try {
			conn.close();
			System.out.println("Closed connection");
		} catch (IOException ioe) {
			System.err.println("IO Exception Closing connection");
		}
	}

}


