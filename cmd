sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

sudo apt-get update
sudo apt-get install pgadmin3

sudo -u postgres psql
ALTER USER postgres PASSWORD 'postgres';
sudo /etc/init.d/postgresql restart
create database ms;
\c ms;
\dt;
\i ubuntu1.sql



https://github.com/naveter/ms.git

/home/bass/Downloads/idea-IC-183.4284.148/bin/idea.sh

sudo add-apt-repository -y ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer -y
sudo java -version

netstat -ant | grep :2181
sudo kafka_2.12-2.1.0/bin/kafka-server-start.sh kafka_2.12-2.1.0/config/server.properties
sudo nohup kafka_2.12-2.1.0/bin/kafka-server-start.sh kafka_2.12-2.1.0/config/server.properties /tmp/kafka.log 2>&1 &
sudo kafka_2.12-2.1.0/bin/kafka-topics.sh --create --zookeeper 192.168.0.103:2181 --replication-factor 1  --partitions 1 --topic testing
sudo kafka_2.12-2.1.0/bin/kafka-topics.sh --list --zookeeper 192.168.0.103:2181
sudo kafka_2.12-2.1.0/bin/kafka-console-producer.sh --broker-list 192.168.0.103:9092 --topic testing
sudo kafka_2.12-2.1.0/bin/kafka-console-consumer.sh --bootstrap-server 192.168.0.103:9092 --topic testing --from-beginning


sudo nano /etc/default/grub
To disable GUI on boot, run:
sudo systemctl set-default multi-user.target
To enable GUI again issue the command:
sudo systemctl set-default graphical.target
To start Gnome session on a system without a current GUI just execute:
sudo systemctl start gdm3.service

http://localhost:8080/greetings?message=Hello,%20World!



