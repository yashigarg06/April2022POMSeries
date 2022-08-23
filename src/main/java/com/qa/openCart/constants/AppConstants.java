package com.qa.openCart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final int SMALL_DEFAULT_TIME_OUT=5;
	public static final int MEDIUM_DEFAULT_TIME_OUT=10;
	public static final int LARGE_DEFAULT_TIME_OUT=15;
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
	public static final String ACCOUNTS_PAGE_TITLE="My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION="route=account/account";
	public static final List<String> EXPECTED_ACCOUNT_HEADERS_LIST=
			                 Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created";
	
	
}
