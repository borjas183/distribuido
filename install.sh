mkdir distribuido
cd distribuido
wget http://$1:7770/scripts.zip
unzip scripts.zip
rm scripts.zip
echo "*/12 * * * * /home/xubuntu/distribuido/scripts/top.sh" >> /etc/crontab

