DOCKER_COMP_CMD=docker compose
DOCKER_CMD=docker

.FORCE:

back: .FORCE
	./gradlew build -x test && docker build -t disk91/etl .

# If you have a problem running setup with missing X11 ...
# this is related to docker login
# run gpg2 --full-generate-key
setup: .FORCE
	-mkdir /etl
	cp -R ./etl/* /etl/
	chown nobody:nogroup /etl/prometheus
	chown 472:root /etl/grafana
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
	-sleep 10
	$(DOCKER_COMP_CMD) --profile mongo stop

clear-setup: stop
	echo "Are you sure, this will delete all mongodb data ?"
	read response
	rm -rf /etl/mongo
	rm -rf /etl/configuration/mongo

build: back

install: setup back

start:
	$(DOCKER_COMP_CMD) --profile mongo --profile etl --profile monitoring up -d

stop-etl: .FORCE
	$(DOCKER_CMD) stop -t 300 etl

stop: stop-etl
	$(DOCKER_COMP_CMD) --profile mongo --profile etl --profile monitoring stop

