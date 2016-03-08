import org.hibernate.*;
import Modelo.*;
import javax.swing.*;
import java.util.List;
import javax.swing.table.*;
import java.util.Iterator;

public class BaseDatos{
	
	private Session sesion;
	private Transaction transacion;
	
	public BaseDatos(){
		
	}
	
	
	public void iniciarSesion(){
		try{
			
			sesion = HibernateUtil.getSessionFactory().openSession();
			transacion = sesion.beginTransaction();
			
		}catch(HibernateException ex){
			ex.printStackTrace();
		}
		
	}

	public void manejarExcepcion (HibernateException ex) throws HibernateException{
		transacion.rollback();
		throw new HibernateException("Error en la capa de acceso a la creacion de una sesion", ex);
	}
	
	public void agregarHerramienta(String numeroItem, String nombreItem, String descripcion,int cantidadTotal){
		iniciarSesion();
		try{
			
			Herramienta h = new Herramienta();
			

			h.setNumeroItem(numeroItem);
			h.setNombreItem(nombreItem);
			h.setDescripcion(descripcion);
			h.setCantidadDisponible(cantidadTotal);
			h.setCantidadTotal(cantidadTotal);
			
			sesion.save(h);
			transacion.commit();
			
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e){
		e.printStackTrace();	
		}finally{
			sesion.disconnect();
		}
		
	}
	 
	public void agregarHerramientaSuplidor(String entregadoPor,String entregadoA,String herramientaEnReparacion,int cantidadReparacion,String descripcion){
		try{
			iniciarSesion();
			Suplidor s = new Suplidor(entregadoPor, entregadoA, herramientaEnReparacion, cantidadReparacion, descripcion);
			sesion.save(s);
			transacion.commit();
			
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void llenarTabla(JTable tblHerramienta, DefaultTableModel modeloHerramienta){
		try{
		iniciarSesion();
		Query sentencia = sesion.createQuery("from Herramienta");
		List<Herramienta> listaHerramienta = sentencia.list();
		
		String[] fila = new String [7];
		for(Herramienta e: listaHerramienta){
			fila[0] = e.getNumeroItem();
			fila[1] = e.getNombreItem();
			fila[2] = e.getDescripcion();
			fila[3] = (String.valueOf(e.getCantidadDisponible()));
			fila[4] = (String.valueOf(e.getCantidadReparacion()));
			fila[5] = (String.valueOf(e.getCantidadPrestada()));
			fila[6] = (String.valueOf(e.getCantidadTotal()));
			modeloHerramienta.addRow(fila);
		}
		tblHerramienta.setModel(modeloHerramienta);
		transacion.commit();
		
		
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			sesion.disconnect();
		}
	}
	
	public void cantidadReparadas(String numeroItem,int reparando){
		try{
		iniciarSesion();
		Query sentencia = sesion.createQuery("from Herramienta where numeroItem = '"+ numeroItem+"'");
		List<Herramienta> listaHerramienta = sentencia.list();
		
		for(Herramienta e: listaHerramienta){
			  
			  int disponible = e.getCantidadDisponible();
			  
			  int x = (disponible - reparando);
			  e.setCantidadDisponible(x);
			  
			  e.setCantidadReparacion(e.getCantidadReparacion() + reparando);
			  
			  sesion.save(e);
			
		}
		transacion.commit();
		
		
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			sesion.disconnect();
		}
	}
	
	public void cantidadPrestada(String numeroItem,int prestado){
		try{
		iniciarSesion();
		Query sentencia = sesion.createQuery("from Herramienta where numeroItem = '"+ numeroItem+"'");
		List<Herramienta> listaHerramienta = sentencia.list();
		
		for(Herramienta e: listaHerramienta){
			  
			  int disponible = e.getCantidadDisponible();
			  
			  int x = (disponible - prestado);
			  e.setCantidadDisponible(x);
			  
			  e.setCantidadPrestada(e.getCantidadPrestada() + prestado);
			  
			  sesion.save(e);			
		}
		transacion.commit();
		
		
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			sesion.disconnect();
		}
	}
	
	public int cantidadHerramientaDisponible(){
		int cantidadHerramienta = 0;
		try{
			iniciarSesion();
			Query sentencia = sesion.createQuery("select count (numeroItem) from Herramienta where cantidadDisponible>0");
			Iterator it = sentencia.iterate();
			cantidadHerramienta = Integer.valueOf(it.next().toString());
			transacion.commit();
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sesion.disconnect();
		}
		return cantidadHerramienta;
	}
	
	public void llenarDescripcion(String numItemSeleccionadoComboBox, JTextArea tatDescripcion ){
		try{
			iniciarSesion();
			Query sentencia = sesion.createQuery("from Herramienta where numeroItem = '"+numItemSeleccionadoComboBox+"'");
			List<Herramienta> lista = sentencia.list();
			for (Herramienta h: lista){
				tatDescripcion.setText(h.getDescripcion());
			}	
			transacion.commit();
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sesion.disconnect();
		}
	}
	
	public String [] arregloHerramientasDisponibles(){
		String arregloH [] = new String [cantidadHerramientaDisponible()];
		int i=0;
		try{
			iniciarSesion();
			Query sentencia = sesion.createQuery("Select numeroItem from Herramienta where cantidadDisponible>0");
			List<String> listaH = sentencia.list();
			for(String herramienta: listaH){
				arregloH[i] = herramienta;
				i++;
			}
			transacion.commit();
			
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sesion.disconnect();
		}
		
		return arregloH;
	}
	
	public int cantidadHerramientaSeleccionada(String numItemSeleccionado){
		int cantidadHerramientaSeleccionada = 0;
		try{
			iniciarSesion();
			Query sentencia = sesion.createQuery("Select cantidadDisponible from Herramienta where numeroItem ='"+numItemSeleccionado+"'");
			Iterator it = sentencia.iterate();
			cantidadHerramientaSeleccionada = Integer.valueOf(it.next().toString());
			transacion.commit();
		}catch(HibernateException ex){
			manejarExcepcion(ex);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sesion.disconnect();
		}
		return cantidadHerramientaSeleccionada;
	}
	
}