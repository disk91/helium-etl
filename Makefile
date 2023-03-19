.FORCE:

back: .FORCE
	./gradlew build -x test && docker build -t disk91/etl .

build: back

install: back
	cp -R ./etl /etl
	-sudo apt-get install default-jdk
	docker compose start mongo-all

start:
	-docker stop etl
	-docker rm etl
	docker run --name etl \
	           --restart always \
	           --net=host \
       	       -d disk91/etl


