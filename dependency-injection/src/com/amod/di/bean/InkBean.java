package com.amod.di.bean;

import java.io.Serializable;

public class InkBean implements Serializable {

	private String colour;

	public InkBean() {
		System.out.println(this.getClass().getSimpleName() + " classs object is created");
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void providingInk() {
		System.out.println("Colour of the ink is " + colour);
	}

}
