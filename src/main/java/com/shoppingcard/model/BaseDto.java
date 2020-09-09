package com.shoppingcard.model;

public class BaseDto {

	//category ya da ürün için ortak yönetilebilen alanlar eklenebilir
	protected String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
