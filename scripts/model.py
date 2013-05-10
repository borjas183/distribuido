import sqlalchemy
from sqlalchemy import Table, Column, Integer, ForeignKey
from sqlalchemy.orm import relationship, backref
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine

engine = create_engine('mysql://root:1234@10.0.4.2/distribuido')


Base = declarative_base()


    
class Administrador(Base):
    __tablename__ = 'administrador'

    id = Column(Integer, primary_key=True)
    name = Column(String(250))
    host = Column(String(250))
    nodos = relationship("Nodo", backref="admin")

class Nodo(Base):
    __tablename__ = 'nodo'
    id = Column(Integer, primary_key=True)
    host = Column(String(250))
    usuario = Column(String(250))
    password = Column(String(250))
    referencia = Column(String(250))
    administrador_id = Column(Integer, ForeignKey('administrador.id'))
    procesos = relationship("Proceso", backref="nodo")
    carpetas = relationship("Carpeta", backref="nodo")
    
class Proceso(Base):
    __tablename__ = 'proceso'
    id = Column(Integer, primary_key=True)
    nombre = Column(String(250))
    uso_cpu = Column(String(250))
    uso_ram = Column(String(250))
    uso_filesystem = Column(String(250))
    usuario = Column(String(250))
    pid = Column(String(250))
    nodo_id = Column(Integer, ForeignKey('nodo.id'))
    
class Carpeta(Base):
    __tablename__ = 'proceso'
    id = Column(Integer, primary_key=True)
    nombre = Column(String(250))
    espacio = Column(String(250))
    usuario = Column(String(250))
    direccion = Column(String(250))
    nodo_id = Column(Integer, ForeignKey('nodo.id'))




if __name__=='__main__':
    pass