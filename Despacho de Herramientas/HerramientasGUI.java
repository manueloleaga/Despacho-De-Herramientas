import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.Date;
import java.text.SimpleDateFormat;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;//Importando un JAR que me provee la clase Abstracta "AutoCompleteDecorator", para auto completar el ComboBox
import javax.swing.table.*;
import javax.swing.UIManager;

public class HerramientasGUI extends JFrame{
	
	private String titulo;
	private JPanel panelAbajo, panelArriba, panelBack, panelCentro;
	private JTable tblHerramienta;
	private DefaultTableModel modeloHerramienta;
	private static final Color AZUL_OSCURO = new Color(11,54,75);
	private static final Color COLOR_NARANJA = new Color(223,138,40);
	private JScrollPane scrollTabla;
	
	private JMenuBar barraMenu ;
	private JMenu menuAcciones, menuDespacho;
	private JMenuItem itemSuplidor, itemLinea, itemAgregar ;

	public HerramientasGUI(String titulo){
		super(titulo); this.titulo=titulo;
		gui();
		iniciarTabla();
		ini();
		
		eventoPrincipal();
	}
	public HerramientasGUI(){
		
	}

	public void ini(){
		setSize(750,500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}


	public static void main(String args[] ){
		new HerramientasGUI("Herramientas VMETA");
	}
	
	public void iniciarTabla(){
		BaseDatos gestor = new BaseDatos();
		gestor.llenarTabla(tblHerramienta,modeloHerramienta);
	}
	
	public void eventoPrincipal(){
		ManejoEvento evento = new ManejoEvento(this,tblHerramienta, modeloHerramienta);
			itemAgregar.addActionListener(evento);
			itemSuplidor.addActionListener(evento);
			itemLinea.addActionListener(evento);
	}

	
	
	
	

public void gui(){
	
		try{
		setDefaultLookAndFeelDecorated(true);
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	
	//---------------------------------------------PANEL ARRIBA-------------------------------------------------------------
		 panelArriba = new JPanel();
			panelArriba.setBackground(AZUL_OSCURO);
				JLabel lblEncabezado = new JLabel("Herramientas");
				lblEncabezado.setFont(new Font("Arial",1,20));
				lblEncabezado.setForeground(Color.WHITE);
			panelArriba.add(lblEncabezado);

	//---------------------------------------------PANEL CENTRO-------------------------------------------------------------
		Font letraPanelCentro = new Font("Arial", 1, 18);
		Font letrabotones = new Font("Arial", 1, 15);
		
		 panelCentro = new JPanel();
			panelCentro.setLayout(new BorderLayout());
			panelCentro.setBackground(Color.WHITE);
			
			String columnas [] = {"Numero ITEM", "Nombre","Descripcion","Disponibles","Reparando","Prestadas","Total"};
			 modeloHerramienta = new DefaultTableModel(null,columnas);
			 
			 tblHerramienta = new JTable(modeloHerramienta);
			// tblHerramienta.setEditable(false);
			 TableColumnModel columnModel = tblHerramienta.getColumnModel();
			 columnModel.getColumn(0).setPreferredWidth(100);
			 columnModel.getColumn(1).setPreferredWidth(120);
			 columnModel.getColumn(2).setPreferredWidth(200);
			 columnModel.getColumn(3).setPreferredWidth(80);
			 columnModel.getColumn(5).setPreferredWidth(70);
			 columnModel.getColumn(6).setPreferredWidth(60);
			 
			scrollTabla = new JScrollPane(tblHerramienta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelCentro.add(scrollTabla);
			
			
			
	//---------------------------------------------PANEL ABAJO-------------------------------------------------------------
		panelAbajo = new JPanel();
			panelAbajo.setBackground(AZUL_OSCURO);
			panelAbajo.setLayout(new FlowLayout(FlowLayout.CENTER));
			
	//--------------------------------------------FIN PANEL ABAJO-------------------------------------------------------------
				
	//---------------------------------------------PANEL CONTENEDOR-------------------------------------------------------------	
		panelBack = new JPanel();
			panelBack.setBackground(Color.WHITE);
			panelBack.setLayout(new BorderLayout(0,5));
			panelBack.add(panelArriba, BorderLayout.NORTH);
			panelBack.add(panelCentro, BorderLayout.CENTER);
			panelBack.add(panelAbajo, BorderLayout.SOUTH);
			
			
	//---------------------------------------------JDialog-------------------------------------------------------------
	
		//------------------------------------------JMENU--------------------------------------------------------------------
			barraMenu = new JMenuBar();
				menuAcciones = new JMenu("Acciones");
						menuDespacho = new JMenu("Despachar");
							 itemSuplidor = new JMenuItem("Suplidor");
							 itemLinea = new JMenuItem("Linea");
						menuDespacho.add(itemSuplidor);
						menuDespacho.add(itemLinea);
					itemAgregar = new JMenuItem("Agregar Herramienta");
					menuAcciones.add(itemAgregar);
					menuAcciones.addSeparator();
					menuAcciones.add(menuDespacho);
				barraMenu.add(menuAcciones);


		add(panelBack, BorderLayout.CENTER);
		setJMenuBar(barraMenu);
		
		
}



}