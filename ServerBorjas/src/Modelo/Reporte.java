/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
class Reporte(Base):    
    __tablename__ = 'reporte'
    id = Column(Integer, primary_key=True)
    time = Column(DATETIME())
    timestamp = Column(Integer)
    procesos = relationship("Proceso", backref="reporte")
    carpetas = relationship("Carpeta", backref="reporte")
    dipositivos = relationship("Dispositivos", backref="reporte")
    nodo_id = Column(Integer, ForeignKey('nodo.id'))
    
 * @author miguel
 */


@DatabaseTable(tableName ="reporte")
public class Reporte {
    
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private Date time;
        
	@DatabaseField
	private int timestamp;
        
        @ForeignCollectionField
	private ForeignCollection<Proceso> procesos;
        
        @ForeignCollectionField
	private ForeignCollection<Carpeta> carpetas;
        
        @ForeignCollectionField
	private ForeignCollection<Dispositivo> dipositivos;
        
        @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "nodo_id")
	private Nodo nodos;
        

    
}
