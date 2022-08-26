package com.training.foodApp.util;

public class ResponseStructure<T> {
	String message;
	int status;
	T data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getT() {
		return data;
	}
	public void setT(T data) {
		this.data = data;
	}
}