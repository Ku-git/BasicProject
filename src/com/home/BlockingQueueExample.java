package com.home;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class BlockingQueueExample {
	
	private static final Logger logger = Logger.getLogger(BlockingQueueExample.class.getCanonicalName());
	AtomicInteger counter = new AtomicInteger(0);
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
	

	public static void main(String[] args) {
		BlockingQueueExample impl = new BlockingQueueExample();
		
		for(int i = 0; i < 3; i++) {
			Thread thread = new Thread(impl::produce);
			thread.start();
		}
		
		for(int i = 0; i < 3; i++) {
			Thread thread = new Thread(impl::consume);
			thread.start();
		}
	}
	
	private void produce() {
		while(true) {
			int val = generateValue();
			try {
				int count = counter.incrementAndGet();
				if(count >= 5) {
					break;
				}
				queue.put(val);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			System.out.println(String.format("[%s] Value produced: %d%n", Thread.currentThread().getName(), val));
		}
	}
	
	private void consume() {
		while(true) {
			int val;
			try {
				val = queue.take();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			System.out.println(String.format("[%s] Value consumed: %d%n", Thread.currentThread().getName(), val));
		}
	}
	
	private int generateValue() {
		return new Random().nextInt(10);
	}
	
	
}
