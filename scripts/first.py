import subprocess
import time
import os
import re
from model import *
import socket
import sys
import os

if __name__=='__main__':        
    host= str(sys.argv[1])       
    child_ip= str(sys.argv[2])    
    admin = session.query(Administrador).filter_by(host = host).first()
    
    nodo = session.query(Nodo).filter_by(host = socket.gethostname(), admin = admin).first()
    if not nodo:
        nodo=Nodo(host=child_ip,administrador_id=admin.id)
        session.add(nodo)
    nodo.estado="activo"
    session.commit()
    
    
    nodo = session.query(Nodo).filter_by(host=child_ip).first()
    filename=os.path.join(os.path.dirname(os.path.abspath(__file__)),"nodo_id");
    f=open(filename,"w+")
    f.write(str(nodo.id))
    f.close()
    print "<nodo_id>%s</nodo_id>"%str(nodo.id)