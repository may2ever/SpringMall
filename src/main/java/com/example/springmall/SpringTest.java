package com.example.springmall;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringTest {
	private int test1;
	private int rest2;
	public SpringTest(@Value(value="10") int test1, @Value(value="20")int test2) {
		this.test1 = test1;
		this.rest2 = test2;
	}
	@Override
	public String toString() {
		return "SpringTest [test1=" + test1 + ", rest2=" + rest2 + "]";
	}
	
}
