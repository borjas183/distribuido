import subprocess
import time
import os
import re
from model import *
import socket
import sys


if __name__=='__main__':        
    host= str(sys.argv[1])    
    admin = session.query(Administrador).filter_by(host = host).first()
    
    nodo = session.query(Nodo).filter_by(host = socket.gethostname(), admin = admin).first()
    if not nodo:
        nodo=Nodo(host=socket.gethostname(),administrador_id=admin.id)
        session.add(nodo)
    nodo.estado="activo"
    session.commit()
    print "<nodo_id>%s</nodo_id>"%str(nodo.id)