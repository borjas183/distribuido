/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
     
class Dispositivo(Base):
    __tablename__ = 'dispositivo'
    id = Column(Integer, primary_key=True)
    nombre = Column(String(250))
    dev = Column(String(250))
    total = Column(DECIMAL)
    usado = Column(DECIMAL)
    disponible = Column(DECIMAL)
    reporte_id = Column(Integer, ForeignKey('reporte.id'))

 *
 * @author miguel
 */
@DatabaseTable(tableName ="dispositivo")
public class Dispositivo {
    
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String nombre;
        
	@DatabaseField
	private String dev;
        
	@DatabaseField
	private double total;
        
	@DatabaseField
	private double usado;
        
	@DatabaseField
	private double disponible;
        
        
        @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "reporte_id")
	private Reporte reporte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUsado() {
        return usado;
    }

    public void setUsado(double usado) {
        this.usado = usado;
    }

    public double getDisponible() {
        return disponible;
    }

    public void setDisponible(double disponible) {
        this.disponible = disponible;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

        
        
        
}
