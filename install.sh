echo off
rm scripts*
echo diga ip del administrread ipserver
rm -R scripts*
wget http://10.0.4.2:7770/scripts.zip
unzip scripts.zip
python scripts/top.py 
#python scripts/cmd.py 
