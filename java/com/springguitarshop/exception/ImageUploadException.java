package com.springguitarshop.exception;

public class ImageUploadException extends RuntimeException {
	String message;
	ImageUploadException(){
		
	}
	public ImageUploadException(String message){
		this.message=message;
	}

}
