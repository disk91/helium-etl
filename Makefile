DOCKER_COMP_CMD=docker compose

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
	docker-compose exec mongo-config-01 sh -c "mongosh < /scripts/config-server"
	docker-compose exec shard-01-node-a sh -c "mongosh < /scripts/shard-01-server"
	docker-compose exec shard-02-node-a sh -c "mongosh < /scripts/shard-02-server"
	docker-compose exec shard-03-node-a sh -c "mongosh < /scripts/shard-03-server"
	sleep 10
	docker-compose exec mongo-router-01 sh -c "mongosh < /scripts/router-server"

build: back

install: back setup

start:
	-docker stop etl
	-docker rm etl
	docker run --name etl \
	           --restart always \
	           --net=host \
       	       -d disk91/etl


