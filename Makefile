DOCKER_COMP_CMD=docker compose
DOCKER_CMD=docker

.FORCE:

back: .FORCE
	./gradlew build -x test && docker build -t disk91/etl .

# If you have a problem running setup with missing X11 ...
# this is related to docker login
# run gpg2 --full-generate-key
setup: .FORCE
	cp -R ./etl /
	-sudo apt-get install default-jdk
	-sudo apt-get install docker-compose
	-sudo apt install gnupg2 pass
	$(DOCKER_COMP_CMD) --profile mongo up -d
	$(DOCKER_CMD) exec mongo-config-01 sh -c "mongosh < /scripts/config-server"
	$(DOCKER_CMD) exec shard-01-node-a sh -c "mongosh < /scripts/shard-01-server"
	$(DOCKER_CMD) exec shard-02-node-a sh -c "mongosh < /scripts/shard-02-server"
	$(DOCKER_CMD) exec shard-03-node-a sh -c "mongosh < /scripts/shard-03-server"
	-sleep 10
	$(DOCKER_CMD) exec mongo-router-01 sh -c "mongosh < /scripts/router-server"
	-sleep 5
	$(DOCKER_CMD) exec mongo-router-01 sh -c "mongosh < /scripts/create-db"
	$(DOCKER_COMP_CMD) --profile mongo stop

clear-setup: .FORCE
	echo "Are you sure, this will delete all mongodb data ?"
	read response
	$(DOCKER_COMP_CMD) --profile mongo stop
	rm -rf /etl/mongo
	rm -rf /etl/configuration/mongo

build: back

install: back setup

start:
	$(DOCKER_COMP_CMD) --profile mongo --profile etl --profile monitoring up -d


