package tests;

import ros.RosBridge;
import ros.RosListenDelegate;

import java.util.HashMap;
import java.util.Map;

/**
 * Example of connecting to rosbridge with publish/subscribe messages.
 * @author James MacGlashan.
 */
public class RosTest {

	public static void main(String[] args) {
		RosBridge bridge = RosBridge.createConnection("ws://chelone:9090");
		bridge.waitForConnection();

		/*
		bridge.subsribe("/burlap_chatter", "burlap_msgs/burlap_state",
				new RosListenDelegate() {
					@Override
					public void receive(Map<String, Object> data, String stringRep) {
						System.out.println("I received: " + stringRep);
					}
				});
		*/

		final Map<String, String> strData = new HashMap<String, String>();
		strData.put("data", "hello from java");
		for(int i = 0; i < 100; i++) {
			System.out.println("sending...");
			bridge.publish("/bridge", "std_msgs/String", strData);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}