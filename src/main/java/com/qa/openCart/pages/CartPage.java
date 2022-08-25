package com.qa.openCart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

public class CartPage {
	
	By cart=By.id("cart");
	public boolean addCart() {
		System.out.println("add to cart");
		System.out.println("click on cart");
		return true;
	}
}