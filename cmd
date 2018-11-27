sudo apt-get update
sudo apt-get install postgresql postgresql-contrib

sudo apt-get update
sudo apt-get install pgadmin3

sudo -u postgres psql
ALTER USER postgres PASSWORD 'postgres';
sudo /etc/init.d/postgresql restart
