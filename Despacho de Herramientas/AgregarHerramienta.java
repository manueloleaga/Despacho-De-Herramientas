
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class AgregarHerramienta extends JDialog{

	private JTextField txtNumItem, txtNombre, txtDescripcion, txtCantidadTotal;
	private JLabel lblNumItem, lblNombre, lblDescripcion, lblCantidadTotal;
	private JButton btnAgregar, btnCancelar;
	private JPanel panelArriba, panelAbajo, panelCentro, panelBack;
	private JFrame ventana;
	private JTable tblHerramienta;
	private DefaultTableModel modeloHerramienta;
	
	private static final Color AZUL_OSCURO = new Color(11,54,75);
	private static final Color COLOR_NARANJA = new Color(223,138,40);
	
	public AgregarHerramienta(JFrame ventana, String titulo, boolean comportamiento, JTable tblHerramienta,DefaultTableModel modeloHerramienta){
	super(ventana, titulo, comportamiento);
	this.ventana=ventana;
	this.tblHerramienta = tblHerramienta;
	this.modeloHerramienta=modeloHerramienta;
	gui();
	ini();

	}
	
	public void ini(){
		setSize(450,210);
		setLocationRelativeTo(null);
		setResizable(false);
	
	}
	
	public void gui(){
		Font letrabotones = new Font("Arial", 1, 15);
		panelArriba = new JPanel();
			panelArriba.setBackground(AZUL_OSCURO);
			JLabel lblEncabezado = new JLabel("Agregar Herramienta", JLabel.CENTER);
			lblEncabezado.setFont(new Font("Arial",1,20));
			lblEncabezado.setForeground(Color.WHITE);
			panelArriba.add(lblEncabezado);
		
		panelCentro = new JPanel();
			panelCentro.setBackground(Color.WHITE);
			
			lblNumItem = new JLabel("Numero:        ");			lblNumItem.setFont(letrabotones);
			lblNombre = new JLabel("Nombre:        ");				lblNombre.setFont(letrabotones);
			lblDescripcion = new JLabel("Descripcion: ");	lblDescripcion.setFont(letrabotones);
			lblCantidadTotal = new JLabel("Cantidad: ");	lblCantidadTotal.setFont(letrabotones);
			
			txtNumItem = new JTextField(10);				txtNumItem.setFont(letrabotones);
			txtNombre = new JTextField(25);					txtNombre.setFont(letrabotones);
			txtDescripcion = new JTextField(25);			txtDescripcion.setFont(letrabotones);
			txtCantidadTotal = new JTextField(7);			txtCantidadTotal.setFont(letrabotones);
			
			panelCentro.setLayout(new FlowLayout(FlowLayout.LEFT));
			panelCentro.add(lblNumItem); panelCentro.add(txtNumItem);
			panelCentro.add(lblCantidadTotal); panelCentro.add(txtCantidadTotal);
			panelCentro.add(lblNombre); panelCentro.add(txtNombre);
			panelCentro.add(lblDescripcion); panelCentro.add(txtDescripcion);
			
		panelAbajo = new JPanel();
			panelAbajo.setBackground(AZUL_OSCURO);
			
			btnAgregar = new JButton("Agregar");
				btnAgregar.setBackground(Color.WHITE);
				btnAgregar.setForeground(AZUL_OSCURO);
			
			btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setForeground(AZUL_OSCURO);
			
			panelAbajo.add(btnAgregar);
			panelAbajo.add(btnCancelar);
			
		panelBack = new JPanel();
			panelBack.setBackground(Color.WHITE);
			panelBack.setLayout(new BorderLayout());
			panelBack.add(panelArriba, BorderLayout.NORTH);
			panelBack.add(panelCentro, BorderLayout.CENTER);
			panelBack.add(panelAbajo, BorderLayout.SOUTH);
			
			add(panelBack);
			
	ManejoEvento evento = new ManejoEvento(this,tblHerramienta,modeloHerramienta,txtNumItem, txtNombre, txtCantidadTotal, txtDescripcion);
	btnAgregar.addActionListener(evento);
	btnCancelar.addActionListener(evento);

	}


}