package com.disk91.etl;

import com.disk91.etl.data.object.MobileReward;
import com.disk91.etl.data.object.Param;
import com.disk91.etl.data.repository.MobileRewardRepository;
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

import jakarta.annotation.PreDestroy;

@EnableScheduling
@EnableAsync
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

    @Autowired
    private EtlConfig etlConfig;

    @Autowired
    private MobileRewardRepository mobileRewardRepository;

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
		if ( p.getLongValue() == 0 && etlConfig.isEtlMongodbShardingEnable() ) {
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
				Document rewards = adminDB.runCommand(
						new Document("shardCollection", "helium-etl.etl_rewards")
								.append("key", new Document("hotspotId", 1).append("_id", 1)));
				p.setLongValue(1);
				paramRepository.save(p);
				System.out.println(">> V1 - DONE");
			} catch (Exception x) {
				System.out.println(">> Failed "+x.getMessage());
				exit();
			}
		}
		if ( p.getLongValue() == 1 && etlConfig.isEtlMongodbShardingEnable() ) {
			try {
				System.out.println(">> Creating reward sharding");

				MongoDatabase adminDB = mongoTemplate.getMongoDatabaseFactory()
						.getMongoDatabase("admin");
				Document rewards = adminDB.runCommand(
						new Document("shardCollection", "helium-etl.etl_rewards")
								.append("key", new Document("hotspotId", 1).append("_id", 1)));
				p.setLongValue(2);
				paramRepository.save(p);
				System.out.println(">> V2 - DONE");
			} catch (Exception x) {
				System.out.println(">> Failed "+x.getMessage());
				exit();
			}
		}
        if ( p.getLongValue() == 2 && etlConfig.isEtlMongodbShardingEnable() ) {
            try {
                System.out.println(">> Creating mobile reward sharding");
                // make sure collection exists
                MobileReward m = new MobileReward();
                m.setHotspotId("0000");
                m.setOwnerId("0000");
                m = mobileRewardRepository.save(m);

                // Create sharding
                MongoDatabase adminDB = mongoTemplate.getMongoDatabaseFactory()
                    .getMongoDatabase("admin");
                Document rewards = adminDB.runCommand(
                    new Document("shardCollection", "helium-etl.etl_mob_rewards")
                        .append("key", new Document("hotspotId", 1).append("_id", 1)));
                p.setLongValue(3);
                paramRepository.save(p);

                // remove dummy entry
                mobileRewardRepository.delete(m);
                System.out.println(">> V3 - DONE");
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
