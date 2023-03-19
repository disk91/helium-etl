package com.disk91.etl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableScheduling
@EnableWebMvc
@Configuration
@SpringBootApplication
public class EtlApplication implements CommandLineRunner, ExitCodeGenerator {

	public static ApplicationContext context;
	public static boolean requestingExitForStartupFailure = false;


	public static void main(String[] args) {
		context = SpringApplication.run(EtlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long pid = ProcessHandle.current().pid();
		System.out.println("-------------- GO ("+pid+")--------------");

		if (EtlApplication.requestingExitForStartupFailure) exit();
	}
	public static void exit() {
		int exitCode = SpringApplication.exit(context,new ExitCodeGenerator() {
			@Override
			public int getExitCode() {
				return 0;
			}
		});
		System.out.println("------------- GONE --------------");
	}

	public int getExitCode() {
		return 0;
	}

}
