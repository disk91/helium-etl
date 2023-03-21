package com.disk91.etl;

import com.disk91.etl.data.object.Param;
import com.disk91.etl.data.repository.ParamRepository;
import com.disk91.etl.service.ExitService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PreDestroy;

//@EnableAsync
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

	@Autowired
	private ParamRepository paramRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void run(String... args) throws Exception {
		long pid = ProcessHandle.current().pid();
		System.out.println("-------------- GO ("+pid+")--------------");

		// Test is parameter is here ( not on first start, immediately exit we just want to create the schema )
		// This is because the table are not automatically shared and we need to make it manually
		Param p = paramRepository.findOneParamByParamName("shard_init");
		if ( p == null ) {
			p = new Param();
			p.setParamName("shard_init");
			p.setLongValue(0);
			paramRepository.save(p);
		}
		if ( p.getLongValue() == 0 ) {
			try {
				System.out.println(">> Creating sharding");

				MongoDatabase adminDB = mongoTemplate.getMongoDatabaseFactory()
						.getMongoDatabase("admin");
				Document beacons = adminDB.runCommand(
						new Document("shardCollection", "helium-etl.etl_beacons")
								.append("key", new Document("hotspotId", 1).append("_id", 1)));
				Document hotspots = adminDB.runCommand(
						new Document("shardCollection", "helium-etl.etl_hotspots")
								.append("key", new Document("hotspotId", 1).append("_id", 1)));
				Document witnesses = adminDB.runCommand(
						new Document("shardCollection", "helium-etl.etl_witnesses")
								.append("key", new Document("hotspotId", 1).append("_id", 1)));
				p.setLongValue(1);
				paramRepository.save(p);
				System.out.println(">> V1 - DONE");
			} catch (Exception x) {
				System.out.println(">> Failed "+x.getMessage());
				exit();
			}
		}

		if (EtlApplication.requestingExitForStartupFailure) exit();
	}

	public static void exit() {
		System.out.println("---------- SHUTDOWN WAIT------------");
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
