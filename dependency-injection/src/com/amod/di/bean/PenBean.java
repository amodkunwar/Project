package com.amod.di.bean;

import java.io.Serializable;

/**
 * @author Rishikant Mahto
 *
 */
public class PenBean implements Serializable {

	private String brand;
	private InkBean inkBean;

	public PenBean() {
		System.out.println(this.getClass().getSimpleName() + " class object is created");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public InkBean getInkBean() {
		return inkBean;
	}

	public void setInkBean(InkBean inkBean) {
		this.inkBean = inkBean;
	}

	public void write() {
		inkBean.providingInk();
		System.out.println("The brand of the pen is " + brand);
	}

}
