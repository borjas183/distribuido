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
class Administrador(Base):
    __tablename__ = 'administrador'

    id = Column(Integer, primary_key=True)
    name = Column(String(250))
    host = Column(String(250))
    estado = Column(String(250))
    info = Column(String(250))
    nodos = relationship("Nodo", backref="admin")
 * 
 * 
 * 
 * @author miguel
 */
@DatabaseTable(tableName ="administrador")
public class Administrador {
    

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
        private String name;
	@DatabaseField
        private String host;
	@DatabaseField
        private String estado;
	@DatabaseField
        private String info;
        @ForeignCollectionField
	private ForeignCollection<Nodo> nodos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
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

        public ForeignCollection<Nodo> getNodos() {
            return nodos;
        }

        public void setNodos(ForeignCollection<Nodo> nodos) {
            this.nodos = nodos;
        }
        
        
}
