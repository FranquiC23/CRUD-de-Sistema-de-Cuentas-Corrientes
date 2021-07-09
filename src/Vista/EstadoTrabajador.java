package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import config.Connect;

import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;

// Import java sql
import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EstadoTrabajador extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtDescripcion;
	private JTextField txtEstadoRegistro;
	private JTable dataTable;

	// Create a Connect Object from config/Connect
	Connect con = new Connect();
	
	
	Connection cn;
	Statement st;
	ResultSet rs;
	DefaultTableModel model;
	int id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstadoTrabajador frame = new EstadoTrabajador();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * LISTAR TABLA
	 */
	void listar() {
		
		String sql = "SELECT * FROM ESTADO_TRABAJADOR";
		try {
			cn = con.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			Object[] EtadoTrabajador = new Object[3];
			model = (DefaultTableModel)dataTable.getModel();
			
			while (rs.next()) {
				EtadoTrabajador[0] = rs.getInt("EstTraCod");
				EtadoTrabajador[1] = rs.getString("EstTraNom");
				EtadoTrabajador[2] = rs.getString("EstTraEstReg");
				model.addRow(EtadoTrabajador);
			}
			dataTable.setModel(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * AGREGAR
	 */
	void agregar() {
		String codigo = (txtCodigo.getText());
	
		String descripcion = txtDescripcion.getText();
		String estadoRegistro = txtEstadoRegistro.getText();
		if (descripcion.equals("") || estadoRegistro.equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
		}else {
			int c = Integer.parseInt(codigo);
			String sql = "INSERT INTO ESTADO_TRABAJADOR(EstTraCod, EstTraNom, EstTraEstReg)VALUES("+c+", '"+descripcion+"', '"+estadoRegistro+"')";
			try {
				cn = con.getConnection();
				st = cn.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
				limpiarTabla();
				limpiarDatos();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * MODIFICAR
	 */
	void modificar() {
		int cod = Integer.parseInt(txtCodigo.getText());
		String desc = txtDescripcion.getText();
		String estReg = txtEstadoRegistro.getText();
		
		String sql = "UPDATE ESTADO_TRABAJADOR SET EstTraNom='"+desc+"', EstTraEstReg='"+estReg+"' WHERE EstTraCod="+cod;
		if (desc.equals("") || estReg.equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese datos correctos");
		} else {
			try {
				cn = con.getConnection();
				st = cn.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Datos Actualizados");
				limpiarTabla();
				limpiarDatos();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ELIMINAR
	 */
	
	void eliminar() {
		int cod = Integer.parseInt(txtCodigo.getText());
		int filaSeleccionada = dataTable.getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una fila");
			
		}else {
			String sql = "DELETE FROM ESTADO_TRABAJADOR WHERE EstTraCod="+cod;
			try {
				cn = con.getConnection();
				st = cn.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Datos Borrados con Exito");
				limpiarTabla();
				limpiarDatos();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	}
	
	/**
	 * INACTIVAR
	 */
	void inactivar() {
		txtCodigo.setEditable(false);
		int cod = Integer.parseInt(txtCodigo.getText());
		txtDescripcion.setEditable(false);
		txtEstadoRegistro.setEditable(false);
		
		int filaSeleccionada = dataTable.getSelectedRow();
		
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una fila");
		}else {
			String sql = "UPDATE ESTADO_TRANAJADOR SET EstTraEstReg='I' WHERE EstTraCod="+cod;
			try {
				cn = con.getConnection();
				st = cn.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Datos Inactivados");
				limpiarTabla();
				limpiarDatos();
				txtCodigo.setEditable(true);
				txtDescripcion.setEditable(true);
				txtEstadoRegistro.setEditable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * REACTIVAR
	 */
	void reactivar() {
		txtCodigo.setEditable(false);
		int cod = Integer.parseInt(txtCodigo.getText());
		txtDescripcion.setEditable(false);
		txtEstadoRegistro.setEditable(false);
		
		int filaSeleccionada = dataTable.getSelectedRow();
		
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una fila");
		}else {
			String sql = "UPDATE ESTADO_TRABAJADOR SET EstTraEstReg='A' WHERE EstTraCod="+cod;
			try {
				cn = con.getConnection();
				st = cn.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Datos Activados");
				limpiarTabla();
				limpiarDatos();
				txtCodigo.setEditable(true);
				txtDescripcion.setEditable(true);
				txtEstadoRegistro.setEditable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * LIMPIAR TABLA
	 */
	void limpiarTabla() {
		for (int i = 0; i < dataTable.getRowCount(); i++) {
			model.removeRow(i);
			i--;
		}
	}
	
	/**
	 * LIMPIAR DATOS
	 */
	
	void limpiarDatos() {
		txtCodigo.setText("");
		txtDescripcion.setText("");
		txtEstadoRegistro.setText("");
	}
	/**
	 * Create the frame.
	 */
	public EstadoTrabajador() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "ESTADO TRABAJADOR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_registro = new JPanel();
		panel_registro.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_registro.setToolTipText("");
		panel_registro.setBounds(12, 23, 536, 109);
		contentPane.add(panel_registro);
		panel_registro.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(12, 22, 57, 15);
		panel_registro.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(179, 20, 114, 19);
		panel_registro.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción");
		lblNewLabel_1.setBounds(12, 49, 92, 15);
		panel_registro.add(lblNewLabel_1);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(179, 47, 254, 19);
		panel_registro.add(txtDescripcion);
		
		JLabel lblNewLabel_1_1 = new JLabel("Estado Registro");
		lblNewLabel_1_1.setBounds(12, 79, 114, 15);
		panel_registro.add(lblNewLabel_1_1);
		
		txtEstadoRegistro = new JTextField();
		txtEstadoRegistro.setBounds(179, 77, 31, 19);
		panel_registro.add(txtEstadoRegistro);
		txtEstadoRegistro.setColumns(10);
		
		JPanel panel_tabla = new JPanel();
		panel_tabla.setToolTipText("");
		panel_tabla.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Tabla Estado Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_tabla.setBounds(12, 144, 536, 192);
		contentPane.add(panel_tabla);
		
		JScrollPane scrollPane = new JScrollPane();
	
		
		GroupLayout gl_panel_tabla = new GroupLayout(panel_tabla);
		gl_panel_tabla.setHorizontalGroup(
			gl_panel_tabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_tabla.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane))
		);
		gl_panel_tabla.setVerticalGroup(
			gl_panel_tabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_tabla.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane)
					.addContainerGap())
		);
		
		dataTable = new JTable();
		
		/**
		 * 	SELECCIONAR FILA
		 */
		dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = dataTable.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "Datos no seleccionados");
				}else {
					int cod = Integer.parseInt((String)dataTable.getValueAt(fila, 0).toString());
					String desc = (String)dataTable.getValueAt(fila, 1);
					String estaReg = (String)dataTable.getValueAt(fila, 2);
					
					txtCodigo.setText(""+cod);
					txtDescripcion.setText(desc);
					txtEstadoRegistro.setText(estaReg);
				}
			}
		});
		
		dataTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Descripcion", "Estado Registro"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		dataTable.getColumnModel().getColumn(0).setResizable(false);
		dataTable.getColumnModel().getColumn(0).setPreferredWidth(58);
		dataTable.getColumnModel().getColumn(1).setResizable(false);
		dataTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		dataTable.getColumnModel().getColumn(1).setMinWidth(200);
		dataTable.getColumnModel().getColumn(2).setResizable(false);
		dataTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		dataTable.getColumnModel().getColumn(2).setMinWidth(120);
		scrollPane.setViewportView(dataTable);
		panel_tabla.setLayout(gl_panel_tabla);
		
		JPanel panel_operaciones = new JPanel();
		panel_operaciones.setLayout(null);
		panel_operaciones.setToolTipText("");
		panel_operaciones.setBounds(12, 348, 536, 95);
		contentPane.add(panel_operaciones);
		
		/**
		 * Boton Adicionar
		 */
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				agregar();
				listar();
			}
		});
		
		btnAdicionar.setBounds(23, 12, 117, 25);
		panel_operaciones.add(btnAdicionar);
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
				listar();
			}
		});
		btnModificar.setBounds(152, 12, 117, 25);
		panel_operaciones.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
				listar();
			}
		});
		btnEliminar.setBounds(278, 12, 117, 25);
		panel_operaciones.add(btnEliminar);
		
		JButton btnInactivar = new JButton("Inactivar");
		btnInactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inactivar();
				listar();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarDatos();
			}
		});
		btnCancelar.setBounds(407, 12, 117, 25);
		panel_operaciones.add(btnCancelar);
		btnInactivar.setBounds(23, 58, 117, 25);
		panel_operaciones.add(btnInactivar);
		
		JButton btnReactivar = new JButton("Reactivar");
		btnReactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reactivar();
				listar();
			}
		});
		btnReactivar.setBounds(152, 58, 117, 25);
		panel_operaciones.add(btnReactivar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listar();
			}
		});
		btnActualizar.setBounds(278, 58, 117, 25);
		panel_operaciones.add(btnActualizar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(WIDTH);
			}
		});
		btnSalir.setBounds(407, 58, 117, 25);
		panel_operaciones.add(btnSalir);
		
		listar();
	}
	
}
