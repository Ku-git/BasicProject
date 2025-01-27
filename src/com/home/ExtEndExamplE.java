package com.home;

import java.util.ArrayList;

class Animal {
	public void move() {
		System.out.println("test");
	}
}

class Bird extends Animal {
	@Override
	public void move() {
		System.out.println("test11");
	}
}

class Cat extends Animal {
	@Override
	public void move() {
		System.out.println("test2");
	}
}

class Tiger extends Cat {
	@Override
	public void move() {
		System.err.println("test3");
	}
}

public class ExtEndExamplE {

	public static void main(String[] args) {
		Animal animal = new Tiger();
		if(animal instanceof Cat) {
			System.out.println("right");
		} else {
			System.out.println("false");
		}
		
	}
}
