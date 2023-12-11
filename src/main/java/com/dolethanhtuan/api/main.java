package com.dolethanhtuan.api;

import java.text.NumberFormat;
import java.util.Locale;

public class main {
	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.UK);
		System.out.println(nf.format(100000.00));
	}
}
