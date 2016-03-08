package Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
public class Herramienta implements Serializable {
	
	@Id 
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;

	private String numeroItem;
	private String nombreItem;
	private String descripcion;
	private int cantidadTotal;
	private int cantidadDisponible=0;
	private int cantidadReparacion=0;
	private int cantidadPrestada=0;
	
	public Herramienta(int id,String numeroItem, String nombreItem, String descripcion, int cantidadTotal, int cantidadDisponible){
		this.id = id;
		this.numeroItem = numeroItem;
		this.nombreItem = nombreItem;
		this.descripcion = descripcion;
		this.cantidadTotal = cantidadTotal;
		this.cantidadDisponible = cantidadDisponible;
		
		
	}
	
	public Herramienta(String numeroItem, String nombreItem, String descripcion, int cantidadTotal, int cantidadDisponible, int cantidadReparacion,int cantidadPrestada){
		this.numeroItem = numeroItem;
		this.nombreItem = nombreItem;
		this.descripcion = descripcion;
		this.cantidadTotal = cantidadTotal;
		this.cantidadDisponible = cantidadDisponible;
		this.cantidadReparacion = cantidadReparacion;
		this.cantidadPrestada = cantidadPrestada;
		
	}
	
	public Herramienta(String numeroItem, String nombreItem, String descripcion, int cantidadTotal){
		this.numeroItem = numeroItem;
		this.nombreItem = nombreItem;
		this.descripcion = descripcion;
		this.cantidadTotal = cantidadTotal;

		
	}
	
	public Herramienta(int id){
		this.id= id;
	}
	
	public Herramienta(){
		
	}
	
	
	public int getId(){
		return this.id;
	}
	
	public void setNumeroItem(String numeroItem){
		this.numeroItem=numeroItem;
	}
	public String getNumeroItem(){
		return this.numeroItem;
	}
	
	public void setNombreItem(String nombreItem){
		this.nombreItem=nombreItem;
	}
	public String getNombreItem(){
		return this.nombreItem;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion= descripcion;
	}
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setCantidadTotal(int cantidadTotal){
		this.cantidadTotal= cantidadTotal;
	}
	public int getCantidadTotal(){
		return this.cantidadTotal;
	}
	
	public void setCantidadDisponible(int cantidadDisponible){
		this.cantidadDisponible= cantidadDisponible;
	}
	public int getCantidadDisponible(){
		return this.cantidadDisponible;
	}
	
	public void setCantidadReparacion(int cantidadReparacion){
		this.cantidadReparacion= cantidadReparacion;
	}
	public int getCantidadReparacion(){
		return this.cantidadReparacion;
	}
	
	public void setCantidadPrestada(int cantidadPrestada){
		this.cantidadPrestada= cantidadPrestada;
	}
	public int getCantidadPrestada(){
		return this.cantidadPrestada;
	}

}