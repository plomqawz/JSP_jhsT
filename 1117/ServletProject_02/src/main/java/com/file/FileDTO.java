package com.file;

public class FileDTO {
	private String name;
	private String title;
	private String image;
	//»ý¼ºÀÚ

	public FileDTO(String name, String title, String image) {
		super();
		this.name = name;
		this.title = title;
		this.image = image;
	}
	//getter
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public String getTitle() {
		return title == null ? "" : title.trim();
	}
	public String getImage() {
		return image == null ? "" : image.trim();
	}
	
	
	

}
