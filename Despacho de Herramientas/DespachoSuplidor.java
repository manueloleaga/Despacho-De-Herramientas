import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import javax.swing.table.*;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class DespachoSuplidor extends JDialog{

	private JLabel lblEncabezado,lblHerramienta, lblCantidad, lblEntregadoA, lblEntregadoPor, lblDescripcion;
	private JComboBox cbbNumItem, cbbCantidad;
	private DefaultComboBoxModel modeloNumItem, modeloCantidad;
	private JTextField txtEntregadA, txtEntregadoPor;
	private JTextArea tatDescripcion;
	private JPanel panelArriba, panelAbajo, panelCentro, panelBack;
	private JButton btnAceptar, btnCancelar;
	private Color verde= new Color(11,54,75);
	private Color marron= new Color(223,138,40);
	private JFrame ventana;
	private JTable tblHerramienta;
	private DefaultTableModel modeloHerramienta;
	
	// private static final Color AZUL_OSCURO = new Color(11,54,75);
	// private static final Color COLOR_NARANJA = new Color(223,138,40);



	public DespachoSuplidor( JFrame ventana, String titulo,boolean comportamiento, JTable tblHerramienta,DefaultTableModel modeloHerramienta){
	super(ventana, titulo, comportamiento);
	this.ventana=ventana;
	this.tblHerramienta = tblHerramienta;
	this.modeloHerramienta=modeloHerramienta;
	gui();
	eventoDespacho();
	ini();
	}
	
	public void ini(){
	setStatusEnabled(false);
	setSize(480,310);
	setLocationRelativeTo(ventana);
	setResizable(false);
	
	}
	public void eventoDespacho(){
	ManejoEvento evento = new ManejoEvento(this, cbbNumItem,modeloNumItem, cbbCantidad,modeloCantidad, tblHerramienta, modeloHerramienta, txtEntregadA, txtEntregadoPor, tatDescripcion);
		btnAceptar.addActionListener(evento);
		btnCancelar.addActionListener(evento);
		cbbNumItem.addActionListener(evento);
	}
	
	
	
	public void gui(){
		panelArriba = new JPanel();
			panelArriba.setBackground(verde);
			lblEncabezado = new JLabel("Reparar Herramienta", JLabel.CENTER);
				lblEncabezado.setFont(new Font("Ebrima",1,18));
				lblEncabezado.setForeground(Color.WHITE);
			panelArriba.add(lblEncabezado);
			Font letraLabel = new Font ("Ebrima",1,15);
		panelCentro = new JPanel();	
			panelCentro.setLayout(new BorderLayout());
			panelCentro.setBackground(Color.WHITE);
			lblHerramienta= new JLabel("Herramienta:");
				lblHerramienta.setFont(letraLabel);
			//	lblHerramienta.setForeground(verde);
			BaseDatos gestor = new BaseDatos();
			cbbNumItem = new JComboBox();
				modeloNumItem = new DefaultComboBoxModel(gestor.arregloHerramientasDisponibles());
				cbbNumItem.setModel(modeloNumItem);
				cbbNumItem.setFont(letraLabel);
				//AutoCompleteDecorator.decorate(cbbNumItem);
			Dimension d = new Dimension(200,30);
				cbbNumItem.setPreferredSize(d);
				cbbNumItem.setBackground(Color.WHITE);
				cbbNumItem.setForeground(marron);
			
			lblCantidad= new JLabel("Cantidad:  ");		cbbCantidad = new JComboBox();
				Dimension c = new Dimension(80,30);		modeloCantidad = new DefaultComboBoxModel(arregloCantidadSeleccionado());
				lblCantidad.setFont(letraLabel);		cbbCantidad.setModel(modeloCantidad);
														cbbCantidad.setFont(letraLabel); cbbCantidad.setBackground(Color.WHITE);
														cbbCantidad.setPreferredSize(c);
				//AutoCompleteDecorator.decorate(cbbLocalidad);	
				
				
			JPanel panelCentroNorte = new JPanel(); panelCentroNorte.setBackground(Color.WHITE);
				panelCentroNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
				panelCentroNorte.add(lblHerramienta);panelCentroNorte.add(cbbNumItem);
				panelCentroNorte.add(lblCantidad);panelCentroNorte.add(cbbCantidad);
				
			JPanel panelCentroCentro = new JPanel();panelCentroCentro.setBackground(Color.WHITE); 
			FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
				flow.setHgap(7); flow.setVgap(3);
			panelCentroCentro.setLayout(flow);
				lblEntregadoPor= new JLabel("Entregado Por:"); txtEntregadoPor = new JTextField(21); 
					lblEntregadoPor.setFont(letraLabel);	txtEntregadoPor.setFont(letraLabel);
				//	lblEntregadoPor.setForeground(verde); 	
				lblEntregadoA= new JLabel("Entregado a:    ");		txtEntregadA = new JTextField(21);
					lblEntregadoA.setFont(letraLabel);		 txtEntregadA.setFont(letraLabel);
					//lblEntregadoA.setForeground(verde); 	
				lblDescripcion= new JLabel("Descripcion:     "); tatDescripcion = new JTextArea(3,21);
					lblDescripcion.setFont(letraLabel);			tatDescripcion.setFont(letraLabel);
															tatDescripcion.setEditable(false);
					
				TitledBorder bordePanel = new TitledBorder("");
					panelCentroCentro.setBorder(bordePanel);
					
				panelCentroCentro.add(lblEntregadoPor); panelCentroCentro.add(txtEntregadoPor);		
				panelCentroCentro.add(lblEntregadoA); panelCentroCentro.add(txtEntregadA);
				panelCentroCentro.add(lblDescripcion); panelCentroCentro.add(tatDescripcion);		
			
			panelCentro.add(panelCentroNorte, BorderLayout.NORTH);
			panelCentro.add(panelCentroCentro, BorderLayout.CENTER);
			
	
		panelAbajo = new JPanel();
			panelAbajo.setBackground(verde);
			panelAbajo.setLayout(new FlowLayout(FlowLayout.CENTER));
				btnAceptar = new JButton("Aceptar"); btnAceptar.setActionCommand("Reparar");
					btnAceptar.setBackground(Color.WHITE);
					btnAceptar.setForeground(verde);
				//	btnAceptar.setFont(letraLabel);
				btnCancelar = new JButton("Cancelar");
					btnCancelar.setBackground(Color.WHITE);
					btnCancelar.setForeground(verde);
				//	btnCancelar.setFont(letraLabel);
			panelAbajo.add(btnAceptar);panelAbajo.add(btnCancelar);
			
		
		panelBack = new JPanel();	
			panelBack.setLayout(new BorderLayout());
			panelBack.add(panelArriba, BorderLayout.NORTH);
			panelBack.add(panelCentro, BorderLayout.CENTER);
			panelBack.add(panelAbajo, BorderLayout.SOUTH);
		add(panelBack);
		
		//EVENTO COMBO HERRAMIENTA
		cbbNumItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if (ae.getSource() instanceof JComboBox){
					JComboBox cb = (JComboBox) ae.getSource();
					String seleccionado = cb.getSelectedItem().toString();
					if (!seleccionado.equals("")){
						setStatusEnabled(true);
					}else{
						setStatusEnabled(false);
					}
				}
			}		
		});
		
		
	}
	
	public String [] arregloCantidadSeleccionado(){
		BaseDatos gestor = new BaseDatos();
		String arregloCantidadSeleccionado [] = new String [gestor.cantidadHerramientaSeleccionada((String)cbbNumItem.getSelectedItem())];
		for (int i=0; i<arregloCantidadSeleccionado.length; i++){
			arregloCantidadSeleccionado[i] =String.valueOf(i+1);
		}
		return arregloCantidadSeleccionado;
	}
	
	
	
	public void setStatusEnabled(boolean comportamiento){
			txtEntregadA.setEnabled(comportamiento);
			txtEntregadoPor.setEnabled(comportamiento);
			tatDescripcion.setEnabled(comportamiento);
			cbbCantidad.setEnabled(comportamiento);
	}
	
}