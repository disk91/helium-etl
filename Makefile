DOCKER_COMP_CMD=docker-compose

.FORCE:

back: .FORCE
	./gradlew build -x test && docker build -t disk91/etl .

setup: .FORCE
	cp -R ./etl /
	-sudo apt-get install default-jdk
	-sudo apt-get install docker-compose
	$(DOCKER_COMP_CMD) --profile mongo up -d

build: back

install: back setup

start:
	-docker stop etl
	-docker rm etl
	docker run --name etl \
	           --restart always \
	           --net=host \
       	       -d disk91/etl


