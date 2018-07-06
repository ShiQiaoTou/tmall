package com.aopioc.tmall;

import com.aopioc.tmall.coupon.task.TokenTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmallApplication.class, args);
		TokenTask.init("aaa","bbb");

	}
}
