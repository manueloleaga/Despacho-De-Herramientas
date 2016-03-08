
package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

import java.io.Serializable;

@Entity
public class Suplidor implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private String entregadoPor;
	private String entregadoA;
	private String herramientaEnReparacion;
	private int cantidadReparacion;
	private String descripcion;
	
	
	public Suplidor(int id,String entregadoPor,String entregadoA,String herramientaEnReparacion,int cantidadReparacion,String descripcion){
		this.id=id;
		this.entregadoPor=entregadoPor;
		this.entregadoA=entregadoA;
		this.herramientaEnReparacion=herramientaEnReparacion;
		this.cantidadReparacion=cantidadReparacion;
		this.descripcion=descripcion;
	}
	public Suplidor(String entregadoPor,String entregadoA,String herramientaEnReparacion,int cantidadReparacion,String descripcion){
		this.entregadoPor=entregadoPor;
		this.entregadoA=entregadoA;
		this.herramientaEnReparacion=herramientaEnReparacion;
		this.cantidadReparacion=cantidadReparacion;
		this.descripcion=descripcion;
	}
	
	public Suplidor(int id){
		this.id= id;
	}
	
	public Suplidor(){
		
	}
	
	
	
	public int getId(){
		return this.id;
	}
	
	public void setEntregadoPor(String entregadoPor){
		this.entregadoPor= entregadoPor;
	}
	public String getEntregadoPor(){
		return this.entregadoPor;
	}
	
	public void setEntregadoA(String entregadoA){
		this.entregadoA= entregadoA;
	}
	public String getEntregadoA(){
		return this.entregadoA;
	}
	
	public void setHerramientaEnReparacion(String herramientaEnReparacion){
		this.herramientaEnReparacion= herramientaEnReparacion;
	}
	public String getHerramientaEnReparacion(){
		return this.herramientaEnReparacion;
	}
	
	public void setCantidadReparacion(int cantidadReparacion){
		this.cantidadReparacion=cantidadReparacion;
	}
	public int getCantidadReparacion(){
		return this.cantidadReparacion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	public String getDescripcion(){
		return this.descripcion;
	}
}