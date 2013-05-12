/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 
class Carpeta(Base):
    __tablename__ = 'carpeta'
    id = Column(Integer, primary_key=True)
    nombre = Column(String(250))
    espacio = Column(DECIMAL)
    usuario = Column(String(250))
    direccion = Column(String(250))
    reporte_id = Column(Integer, ForeignKey('reporte.id'))
 * 
 * @author miguel
 */
@DatabaseTable(tableName ="carpeta")
public class Carpeta {
    
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String nombre;
        
	@DatabaseField
	private String espacio;
        
	@DatabaseField
	private String usuario;
        
	@DatabaseField
	private String direccion;
        
        
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

        public String getEspacio() {
            return espacio;
        }

        public void setEspacio(String espacio) {
            this.espacio = espacio;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

}
