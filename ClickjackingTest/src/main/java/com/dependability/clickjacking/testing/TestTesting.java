package com.dependability.clickjacking.testing;

import java.io.IOException;

public class TestTesting {

	public static void main(String[] args) throws IOException {
		
		boolean[] listAttack= {false,true,false,false,false,false,false};
		TestingClickJacking test = new TestingClickJacking("http://localhost:3000/",listAttack);
		test.creationEvnviroment();
		test.executionTest();
	}

}
