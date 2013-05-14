import subprocess
import time
import os
import re
from model import *
import socket
import datetime

PID =0
USER =2
PR =4
NI =6
VIRT =8
RES =10
SHR = 12
S =14
CPU =16
MEM  =18
TIME  =20
COMMAND =22


DEV=0
TOTAL=2
USADO=4
DISPONIBLE=6
USO=8
NOMBRE=10


OCUPA=0
DIRECCION=2

REG_TOP="([\d]+)([\s]+)([a-zA-Z]+)([\s]+)([a-zA-Z]*[\d]*)([\s]+)([-\d]+)([\s]+)([\d]+[a-z]*)([\s]+)([\d]+[a-z]*)([\s]+)([\d]+[a-z]*)([\s]+)([a-zA-Z]+)([\s]+)([\d]+,[\d])([\s]+)([\d]+,[\d])([\s]+)([\d]+:[\d]+.[\d]+)([\s]+)(.*)";
REG_FILESYSTEM="(/dev/[a-zA-Z0-9]*)([\s]+)([\d]+)([\s]+)([\d]+)([\s]+)([\d]+)([\s]+)([\d]+%)([\s]+)(.*)"
REG_LS="([\d]+)([\s]+)([\w/]+)"
SECONDS=300

def run_command(command):    
    return os.popen(command).read()


def carpetas_home(reporte_id):
        str = run_command("du -s /home/xubuntu/*");
        match = re.findall(REG_LS, str)
        i=1
        for m in match:
			espacio=m[OCUPA]
			direccion=m[DIRECCION]
            print "carpeta:  %d  pid: %s user: %s " %     (i,   espacio, direccion    )
			i+=1
            model_user=Carpeta(espacio=espacio, direccion=direccion,reporte_id=reporte_id)
            session.add(model_user)

# commando top -n 1 -b
def uso_cpu(reporte_id):
        str = run_command("top -n 1 -b");
        match = re.findall(REG_TOP, str)
        i=1
        for m in match:
            pid = m[  PID] 
            user = m[  USER] 
            pr = m[ PR] 
            ni = m[ NI] 
            virt = m[ VIRT] 
            res = m[ RES] 
            shr = m[  SHR] 
            s = m[  S] 
            cpu =  float(m[  CPU].replace(',','.')) 
            mem = float(m[  MEM].replace(',','.')) 
            time = m[  TIME] 
            command = m[  COMMAND] 
            print "top:  %d  pid: %s user: %s cpu: %s mem: %s time: %s command: %s " %     (i,   pid, user, cpu, mem,  time,   command,    )
     
            model_user=Proceso(pid=pid, user=user, cpu=cpu,mem=mem,time=time,command=command,reporte_id=reporte_id)
            session.add(model_user)
            
            i+=1

# commando df -k | sort -n
def uso_filesystem(reporte_id):
        str = run_command("df -k | sort -n");
        match = re.findall(REG_FILESYSTEM, str)
        i=1
        for m in match:
            dev = m[DEV] 
            total = m[TOTAL] 
            usado = m[USADO] 
            disponible = m[DISPONIBLE] 
            nombre = m[NOMBRE] 
            print "dispositivo:  %d  dev: %s total: %s usado: %s disponible: %s nombre: %s " %     (i,   dev, total, usado, disponible,  nombre )
            
            model_fsys=Dispositivo(nombre=nombre, dev=dev, total=total,usado=usado,disponible=disponible,reporte_id=reporte_id)
            session.add(model_fsys)
            
            i+=1

    
def top():
    
    

    filename=os.path.join(os.path.dirname(os.path.abspath(__file__)),"nodo_id");
    f=open(filename,"r+")
    nodo_id=f.readline()
    f.close()
    
    nodo = session.query(Nodo).filter_by(id = nodo_id).first()

    report=Reporte(nodo_id=nodo.id,timestamp=int(time.time()))
    session.add(report)
    session.commit()
    uso_cpu(report.id)
    uso_filesystem(report.id)
    carpetas_home(report.id)
        


if __name__ == '__main__':
    top()
    session.commit()
