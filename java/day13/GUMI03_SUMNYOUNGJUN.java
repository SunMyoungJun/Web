import java.net.*;
import java.io.*;

public class GUMI03_SUNMYOUNGJUN {

	// User and Launcher Information
	static final String NICKNAME = "GUMI03_SUNMYOUNGJUN";
	static final String HOST = "127.0.0.1";

	// Static Value(Do not modify)
	static final int PORT = 1447;
	static final int CODE_SEND = 9901;
	static final int CODE_REQUEST = 9902;
	static final int SIGNAL_ORDER = 9908;
	static final int SIGNAL_CLOSE = 9909;

	// Predefined Variables(Do not modify)
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 127;
	static final int NUMBER_OF_BALLS = 6;
	static final int[][] HOLES = { { 0, 0 }, { 127, 0 }, { 254, 0 }, { 0, 127 }, { 127, 127 }, { 254, 127 } };

	public static void main(String[] args) {

		Socket socket = null;
		String recv_data = null;
		byte[] bytes = new byte[1024];
		int[][] balls = new int[NUMBER_OF_BALLS][2];
		int order = 0;
		int cnt=0;
		int cnt2=0;
		try {
			socket = new Socket();
			System.out.println("Trying Connect: " + HOST + ":" + PORT);
			socket.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("Connected: " + HOST + ":" + PORT);

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String send_data = CODE_SEND + "/" + NICKNAME + "/";
			bytes = send_data.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("Ready to play!\n--------------------");

			while (socket != null) {

				// Receive Data
				bytes = new byte[1024];
				int count_byte = is.read(bytes);
				recv_data = new String(bytes, 0, count_byte, "UTF-8");
				System.out.println("Data Received: " + recv_data);

				// Read Game Data
				String[] split_data = recv_data.split("/");
				int idx = 0;
				try {
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						for (int j = 0; j < 2; j++) {
							balls[i][j] = Integer.parseInt(split_data[idx++]);
						}
					}
				} catch (Exception e) {
					bytes = (CODE_REQUEST + "/" + CODE_REQUEST).getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println("Received Data has been currupted, Resend Requested.");
					continue;
				}

				// Check Signal for Player Order or Close Connection
				if (balls[0][0] == SIGNAL_ORDER) {
					order = balls[0][1];
					System.out.println("\n* You will be the " + (order == 1 ? "first" : "second") + " player. *\n");
					continue;
				} else if (balls[0][0] == SIGNAL_CLOSE) {
					break;
				}

				// Show Balls' Position
				for (int i = 0; i < NUMBER_OF_BALLS; i++) {
					System.out.println("Ball " + i + ": " + balls[i][0] + ", " + balls[i][1]);
				}

				float angle = 0.0f;
				float power = 0.0f;

				//////////////////////////////
				// Beginning of Your Code
				// Put your code here to set angle and power values.
				// angle(float) must be between 0.0 and 360.0
				// power(float) must be between 0.0 and 100.0
				int whiteBall_x = balls[0][0];
				int whiteBall_y = balls[0][1];

				int tx=1,ty=1;
				while(true) {
					if(balls[tx][0] >=0 || balls[ty][0]>=0) {
						break;
					}
					tx++;
					ty++;
				}
				
				System.out.println(tx+" "+ty+"?????????.");
				int targetBall_x = balls[tx][0];
				int targetBall_y = balls[ty][1];

				int width = Math.abs(targetBall_x - whiteBall_x);
				int height = Math.abs(targetBall_y - whiteBall_y);
				double radian =0.0;
				if(cnt ==0) { //??????????????????.
					 radian = Math.atan(width / height);
				}
				angle = (float) ((180.0 / Math.PI) * radian);

				if (targetBall_y < whiteBall_y)
				{
					angle = (float) ((float)Math.toDegrees(Math.atan2(height, width)) +90); // ????????????2
				}
				
				

				
			
				
				if (targetBall_y < whiteBall_y  && targetBall_x > whiteBall_x)
				{
					angle = 89+ (float)((float)Math.toDegrees(Math.atan2(height, width))); // ????????????2
				}
				if(targetBall_y < whiteBall_y && targetBall_x < whiteBall_x) {
					angle = 179+ (90-(float)((float)Math.toDegrees(Math.atan2(height, width)))); // ????????????2
				}
				
				
				if(targetBall_y > whiteBall_y && targetBall_x >whiteBall_x) { 
					angle = (float) (91 - ((float)Math.toDegrees(Math.atan2(height, width))));
					
				}
				if(targetBall_y > whiteBall_y && targetBall_x <whiteBall_x) {
					angle = (float)270 +((float)Math.toDegrees(Math.atan2(height, width)));
				}
				
				if(tx ==5) { //????????? ??????
					System.out.println("?????????");
					if(cnt2 ==1) {
						angle +=-1;
					}
					else {
						angle -=2;

					}
					cnt2++;
				}
				
				double distance = Math.sqrt((width * width) + (height * height));

				power = (float) distance;
				// You can clear Stage 1 with the pre-written code above.
				// Those will help you to figure out how to clear other Stages.
				// Good luck!!
				// ENd of Your Code
				//////////////////////////////

				
				if(distance<50) { 
					power = 30;
				}
				else if(distance <100) {
					power = 70;
				}
				else if(distance <150) {
					power = 110;
				}
				else {
					power =140;
				}
				
//				if(cnt ==5) {
//					power -= 50;
//				}
				// Play(Send Data)
				String merged_data = angle + "/" + power + "/";
				bytes = merged_data.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("Data Sent: " + merged_data);
				cnt++;
				System.out.println("cnt  : "+ cnt);
			}

			os.close();
			is.close();
			socket.close();
			System.out.println("Connection Closed.\n--------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
