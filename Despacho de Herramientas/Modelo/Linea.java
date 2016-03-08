
package Modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

import java.io.Serializable;

@Entity
public class Linea implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private String entregadoPor;
	private String entregadoA;
	private String herramientaPrestada;
	private int cantPrestada;
	private String descripcion;
	
	
	public Linea(int id,String entregadoPor,String entregadoA,String herramientaPrestada,int cantPrestada,String descripcion){
		this.id=id;
		this.entregadoPor=entregadoPor;
		this.entregadoA=entregadoA;
		this.herramientaPrestada=herramientaPrestada;
		this.cantPrestada=cantPrestada;
		this.descripcion=descripcion;
	}
	public Linea(String entregadoPor,String entregadoA,String herramientaPrestada,int cantPrestada,String descripcion){
		this.entregadoPor=entregadoPor;
		this.entregadoA=entregadoA;
		this.herramientaPrestada=herramientaPrestada;
		this.cantPrestada=cantPrestada;
		this.descripcion=descripcion;
	}
	
	public Linea(int id){
		this.id= id;
	}
	
	public Linea(){
		
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
	
	public void setHerramientaPrestada(String herramientaPrestada){
		this.herramientaPrestada= herramientaPrestada;
	}
	public String getHerramientaPrestada(){
		return this.herramientaPrestada;
	}
	
	public void setCantidadPrestada(int cantPrestada){
		this.cantPrestada=cantPrestada;
	}
	public int getCantidadPrestada(){
		return this.cantPrestada;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	public String getDescripcion(){
		return this.descripcion;
	}
}