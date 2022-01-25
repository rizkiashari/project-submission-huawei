package com.rizkiashari.restoapp.model;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("pictureFood")
	private String pictureFood;

	@SerializedName("price")
	private String price;

	@SerializedName("nameFood")
	private String nameFood;

	@SerializedName("id")
	private int id;

	@SerializedName("restoId")
	private int restoId;

	public String getPictureFood(){
		return pictureFood;
	}

	public String getPrice(){
		return price;
	}

	public String getNameFood(){
		return nameFood;
	}

	public int getId(){
		return id;
	}

	public int getRestoId(){
		return restoId;
	}
}