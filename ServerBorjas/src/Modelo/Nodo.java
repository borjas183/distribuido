/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
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
 * 
 * 
 * @author miguel
 */
@DatabaseTable(tableName ="nodo")
public class Nodo {
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String usuario;
        
	@DatabaseField
	private String host;
        
	@DatabaseField
	private String password;
        
	@DatabaseField
	private String referencia;
        
	@DatabaseField
	private String estado;
        
	@DatabaseField
	private String info;
        
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "administrador_id")
	private Administrador administrador;

        @ForeignCollectionField
	private ForeignCollection<Reporte> reportes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getReferencia() {
            return referencia;
        }

        public void setReferencia(String referencia) {
            this.referencia = referencia;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Administrador getAdministrador() {
            return administrador;
        }

        public void setAdministrador(Administrador administrador) {
            this.administrador = administrador;
        }

        public ForeignCollection<Reporte> getReportes() {
            return reportes;
        }

        public void setReportes(ForeignCollection<Reporte> reportes) {
            this.reportes = reportes;
        }

        
        
        
        
}
