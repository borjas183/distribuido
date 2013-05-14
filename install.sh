mkdir nodo_distribuido
cd nodo_distribuido
rm -r scripts*
wget http://$1:7770/scripts.zip
if [ ! -f scripts.zip ]; then
	wget http://10.0.4.2:7770/scripts.zip
fi
unzip scripts.zip
rm scripts.zip
python /home/xubuntu/nodo_distribuido/scripts/first.py $1 $2
crontab -u xubuntu /home/xubuntu/nodo_distribuido/scripts/local_crontab

