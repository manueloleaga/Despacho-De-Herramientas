
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;

public class ManejoEvento implements ActionListener{
	private JTable tblHerramienta;
	private DefaultTableModel modeloHerramienta;
	private JFrame ventana;
	private JTextField txtNumItem, txtNombre, txtDescripcion, txtCantidadTotal;
	private JDialog dialogo;
	private JComboBox cbbNumItem, cbbCantidad;
	private DefaultComboBoxModel modeloCantidad, modeloNumItem ;
	private JTextField txtEntregadA, txtEntregadoPor;
	private JTextArea tatDescripcion;
	private BaseDatos gestor = new BaseDatos();

	public ManejoEvento(JFrame ventana, JTable tblHerramienta, DefaultTableModel modeloHerramienta){
		this.ventana=ventana;
		this.tblHerramienta=tblHerramienta;
		this.modeloHerramienta=modeloHerramienta;
	}
	
	
	
	public ManejoEvento(JDialog dialogo, JTable tblHerramienta, DefaultTableModel modeloHerramienta,JTextField txtNumItem, JTextField txtNombre, JTextField txtCantidadTotal, JTextField txtDescripcion){
		this.dialogo = dialogo;
		this.tblHerramienta = tblHerramienta;
		this.modeloHerramienta = modeloHerramienta;
		this.txtNumItem = txtNumItem;
		this.txtNombre = txtNombre;
		this.txtCantidadTotal = txtCantidadTotal;
		this.txtDescripcion = txtDescripcion;
		
		
	}
	
	public ManejoEvento(JDialog dialogo, JComboBox cbbNumItem,DefaultComboBoxModel modeloNumItem, JComboBox cbbCantidad,DefaultComboBoxModel modeloCantidad, JTable tblHerramienta, DefaultTableModel modeloHerramienta, JTextField txtEntregadA, JTextField txtEntregadoPor, JTextArea tatDescripcion){
		this.dialogo=dialogo;
		this.cbbNumItem=cbbNumItem;
		this.modeloNumItem=modeloNumItem;
		this.cbbCantidad=cbbCantidad;
		this.modeloCantidad=modeloCantidad;
		this.tblHerramienta=tblHerramienta;
		this.modeloHerramienta=modeloHerramienta;
		this.txtEntregadA=txtEntregadA;
		this.txtEntregadoPor=txtEntregadoPor;
		this.tatDescripcion=tatDescripcion;
	}
	
	public ManejoEvento(){
		
	}

	public void actionPerformed(ActionEvent ev){
		
		String label = ev.getActionCommand();
		if (ev.getSource() instanceof JComboBox){
			JComboBox combo =(JComboBox)ev.getSource();
			String arregloCantidadDisponibleSeleccionado [] = new String [gestor.cantidadHerramientaSeleccionada((String)combo.getSelectedItem())];
			for (int i =0; i<arregloCantidadDisponibleSeleccionado.length; i++){
				arregloCantidadDisponibleSeleccionado[i]=String.valueOf(i+1);	
			}
				modeloCantidad = new DefaultComboBoxModel(arregloCantidadDisponibleSeleccionado);
			cbbCantidad.setModel(modeloCantidad);
			gestor.llenarDescripcion((String)cbbNumItem.getSelectedItem(), tatDescripcion);
			
			//String entregadoPor,String entregadoA,String herramientaEnReparacion,int cantidadReparacion,String descripcion
		}
		
		
		if (label.equals("Agregar Herramienta")){	
			AgregarHerramienta agregar = new AgregarHerramienta(ventana, "Herramientas VMETA", true, tblHerramienta, modeloHerramienta);
			agregar.show();
		}
		
		if(label.equals("Agregar")){
			BaseDatos gestor = new BaseDatos();
			gestor.agregarHerramienta(txtNumItem.getText(), txtNombre.getText(),txtDescripcion.getText(), Integer.parseInt(txtCantidadTotal.getText()));
			rellenarTabla();	
			dialogo.hide();
		} 
		
		if(label.equals("Suplidor")){
			DespachoSuplidor suplidor = new DespachoSuplidor(ventana, "Despachar a Suplidor", true, tblHerramienta, modeloHerramienta);
			suplidor.show();
		}
		if(label.equals("Linea")){
			DespachoLinea linea = new DespachoLinea(ventana, "Despachar Linea", true, tblHerramienta, modeloHerramienta);
			linea.show();
		}
		if(label.equals("Reparar")){
		BaseDatos gestor = new BaseDatos();
		gestor.agregarHerramientaSuplidor(txtEntregadoPor.getText(), txtEntregadA.getText(),(String)cbbNumItem.getSelectedItem(),Integer.valueOf(cbbCantidad.getSelectedItem().toString()),tatDescripcion.getText());
		gestor.cantidadReparadas((String)cbbNumItem.getSelectedItem(),Integer.valueOf(cbbCantidad.getSelectedItem().toString()) );
		rellenarTabla();
		dialogo.hide();
		}
		
		if (label.equals("Prestar")){
			
			BaseDatos gestor = new BaseDatos();
			gestor.agregarHerramientaSuplidor(txtEntregadoPor.getText(), txtEntregadA.getText(),(String)cbbNumItem.getSelectedItem(),Integer.valueOf(cbbCantidad.getSelectedItem().toString()),tatDescripcion.getText());
			gestor.cantidadPrestada((String)cbbNumItem.getSelectedItem(),Integer.valueOf(cbbCantidad.getSelectedItem().toString()) );
			rellenarTabla();
			dialogo.hide();
		}
		if (label.equals("Cancelar")){
			dialogo.hide();
		}
		

		
	}
	
	public void rellenarTabla(){
		BaseDatos gestor = new BaseDatos();
		gestor.llenarTabla(tblHerramienta,modeloHerramienta);
		for(int i = 0; i<tblHerramienta.getRowCount(); i++){
							((DefaultTableModel)tblHerramienta.getModel()).removeRow(i);
						}
		
		
			
			
		
	}

}