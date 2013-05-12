import sqlalchemy
from sqlalchemy import Table, Column, Integer, ForeignKey, String,DATETIME,DECIMAL
from sqlalchemy.orm import relationship, backref
from sqlalchemy.ext.declarative import declarative_base    
from sqlalchemy import event, create_engine
from sqlalchemy.orm import sessionmaker


import sys


Base = declarative_base()

engine = create_engine('mysql://root:1234@10.0.4.2/distribuido')
session = sessionmaker(bind=engine)
    
class Administrador(Base):
    __tablename__ = 'administrador'

    id = Column(Integer, primary_key=True)
    name = Column(String(250))
    host = Column(String(250))
    estado = Column(String(250))
    info = Column(String(250))
    nodos = relationship("Nodo", backref="admin")

class Nodo(Base):
    __tablename__ = 'nodo'
    id = Column(Integer, primary_key=True)
    host = Column(String(250))
    usuario = Column(String(250))
    password = Column(String(250))
    referencia = Column(String(250))
    estado = Column(String(250))
    info = Column(String(250))
    administrador_id = Column(Integer, ForeignKey('administrador.id'))
    reportes = relationship("Reporte", backref="nodo")
    
class Reporte(Base):    
    __tablename__ = 'reporte'
    id = Column(Integer, primary_key=True)
    time = Column(DATETIME())
    timestamp = Column(Integer)
    procesos = relationship("Proceso", backref="reporte")
    carpetas = relationship("Carpeta", backref="reporte")
    dipositivos = relationship("Dispositivos", backref="reporte")
    nodo_id = Column(Integer, ForeignKey('nodo.id'))
    
class Proceso(Base):
    __tablename__ = 'proceso'
    id = Column(Integer, primary_key=True)
    pid = Column(String(250))
    user = Column(String(250))
    cpu = Column(DECIMAL)
    mem = Column(DECIMAL)
    time = Column(String(250))
    command = Column(String(250))
    reporte_id = Column(Integer, ForeignKey('reporte.id'))
    
class Carpeta(Base):
    __tablename__ = 'carpeta'
    id = Column(Integer, primary_key=True)
    nombre = Column(String(250))
    espacio = Column(DECIMAL)
    usuario = Column(String(250))
    direccion = Column(String(250))
    reporte_id = Column(Integer, ForeignKey('reporte.id'))
    
class Dispositivo(Base):
    __tablename__ = 'dispositivo'
    id = Column(Integer, primary_key=True)
    nombre = Column(String(250))
    dev = Column(String(250))
    total = Column(DECIMAL)
    usado = Column(DECIMAL)
    disponible = Column(DECIMAL)
    reporte_id = Column(Integer, ForeignKey('reporte.id'))




if __name__=='__main__':    
    engine = create_engine('mysql://root@10.0.4.2/distribuido')
    Base.metadata.create_all(engine);