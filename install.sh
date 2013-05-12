mkdir distribuido
cd distribuido
wget http://$1:7770/scripts.zip
if [ ! -f scripts.zip ]; then
	wget http://10.0.4.2:7770/scripts.zip
fi
unzip scripts.zip
rm scripts.zip
python /home/xubuntu/distribuido/scripts/first.py $1
crontab -u xubuntu /home/xubuntu/distribuido/scripts/local_crontab

