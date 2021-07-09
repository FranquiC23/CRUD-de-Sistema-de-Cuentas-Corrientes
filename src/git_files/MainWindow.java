package git_files;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("DataBase Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Available Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel initial_panel = new JPanel();
		initial_panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		initial_panel.setToolTipText("");
		initial_panel.setBounds(12, 23, 500, 109);
		contentPane.add(initial_panel);
		initial_panel.setLayout(null);
		
		JButton addCentroCosto = new JButton("Centro Costo");
		addCentroCosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// CENTRO COSTO INIT
				
			}
		});
		addCentroCosto.setBounds(5, 12, 150, 25);
		initial_panel.add(addCentroCosto);
		
		JButton estadoRegistro = new JButton("Estado Registro");
		estadoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ESTADO REGISTRO INIT
				EstadoRegistro frame = new EstadoRegistro();
				frame.setVisible(true);
				
			}
		});
		estadoRegistro.setBounds(160, 12, 150, 25);
		initial_panel.add(estadoRegistro);
		
		JButton estadoTrabajador = new JButton("Estado Trabajador");
		estadoTrabajador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ESTADO TRABAJADOR INIT
				EstadoTrabajador frame = new EstadoTrabajador();
				frame.setVisible(true);
			}
		});
		estadoTrabajador.setBounds(314, 12, 150, 25);
		initial_panel.add(estadoTrabajador);
		
		JButton tipoPrestamo = new JButton("Tipo Prestamo");
		tipoPrestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TIPO PRESTAMOS INIT
				TipoPrestamo frame = new TipoPrestamo();
				frame.setVisible(true);
			}
		});
		tipoPrestamo.setBounds(5, 50, 150, 25);
		initial_panel.add(tipoPrestamo);
		
		JButton tipoTrabajador = new JButton("Tipo Trabajador");
		tipoTrabajador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TIPO  TRABAJADOR INIT 
				TipoTrabajador frame = new TipoTrabajador();
				frame.setVisible(true);
			}
		});
		tipoTrabajador.setBounds(160, 50, 150, 25);
		initial_panel.add(tipoTrabajador);
		
		
		
		
	}

}
