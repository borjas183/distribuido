/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 
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
    
 *
 * @author borjas
 */
@DatabaseTable(tableName ="proceso")
public class Proceso {
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String pid;
        
	@DatabaseField
	private String user;
        
	@DatabaseField
	private double cpu;
        
	@DatabaseField
	private double mem;
        
        
	@DatabaseField
	private String time;
        
	@DatabaseField
	private String command;
        
        
        @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "reporte_id")
	private Reporte reporte;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public double getCpu() {
            return cpu;
        }

        public void setCpu(double cpu) {
            this.cpu = cpu;
        }

        public double getMem() {
            return mem;
        }

        public void setMem(double mem) {
            this.mem = mem;
        }


        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public Reporte getReporte() {
            return reporte;
        }

        public void setReporte(Reporte reporte) {
            this.reporte = reporte;
        }
    
        
        
}
