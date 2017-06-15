package Endpoints;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/example")
public class chatBox {
	static Queue<Session> sessions = new ConcurrentLinkedQueue();
	
// It is called when user connects to web socket endpoints
	@OnOpen
	public void openSession(Session session) {
		sessions.add(session);
		System.out.println("I am connected as "+ session.getQueryString());

	}
// This method is called when web socket receives a message 
	@OnMessage
	public String onMessageReceived(String message, Session userSession) { 
		System.out.println(message);
		String userName = userSession.getQueryString().replace("=", "");
		System.out.println(userName + " username");
		
		for (Session session : sessions) {
			if (session.isOpen()) {				
				UserMessage userMessage = new UserMessage();
				userMessage.setUserName(userName);
				userMessage.setMessage(message);
				session.getAsyncRemote().sendText(userMessage.toString());

			}
		}
		return null;

	}
	
	class UserMessage {
		String userName;
		String message;
		
		public void setUserName(String userName){
			this.userName = userName;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String userName(){
			return userName;
		}
		
		public String getMessage() {
			return message;
		}
		
		public String toString(){
			return "username: "+ userName() + ": message:"+ getMessage();
		}
		
	}

	@OnError
	public void onErrorReceive(Throwable error) {
		System.out.println("Error");
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose");

	}
}
