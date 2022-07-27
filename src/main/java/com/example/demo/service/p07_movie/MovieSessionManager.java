package com.example.demo.service.p07_movie;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.WebSocketSession;

public class MovieSessionManager {

	private Map<String, Set<WebSocketSession>> sessions = new ConcurrentHashMap<String, Set<WebSocketSession>>();
    private Map<String, Lock> locks = new ConcurrentHashMap<>();

    public void addSession(String room, WebSocketSession session) {
        synchronized (this.getLock(room)) {
        	this.sessions.putIfAbsent(room, new CopyOnWriteArraySet<>());
        	this.sessions.get(room).add(session);
        }
    }
    
    public void removeSession(String room, WebSocketSession session) {
        synchronized (this.getLock(room)) {
            this.sessions.get(room).remove(session);
            if (this.sessions.get(room).size() == 0) {
            	this.sessions.remove(room);
            	this.locks.remove(room);
            }
        }
    }

    public Set<WebSocketSession> getSessions(String room) {
        return this.sessions.get(room);
    }

    private Lock getLock(String room) {
        Lock newLock = new Lock();
        Lock alreadyLock = this.locks.putIfAbsent(room, newLock);
        return alreadyLock == null ? newLock : alreadyLock;
    }

    private class Lock {}

}
