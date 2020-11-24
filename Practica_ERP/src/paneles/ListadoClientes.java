package paneles;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListadoClientes extends JPanel {
	private JTable table;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	/**
	 * Create the panel.
	 */
	public ListadoClientes() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 213, 1218, 363);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla.setColumnIdentifiers(new Object[] { "ID", "Nombre", "Telefono", "DNI", "Domicilio" ,"CP", "Poblacion", "Provincia" });
		table.setModel(modeloTabla);
		
		JLabel labelGestionCliente6 = new JLabel("Lista de clientes");
		labelGestionCliente6.setForeground(SystemColor.textHighlight);
		labelGestionCliente6.setFont(new Font("Tahoma", Font.BOLD, 22));
		labelGestionCliente6.setBounds(610, 162, 214, 40);
		add(labelGestionCliente6);
		
		cargaClientes();

	}
	
	public void cargaClientes() {
		Connection conexion = null;
		Statement sql = null;
		ResultSet rs = null;
		try {
			try {
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/SotecarsBBDD", "TRABAJO", "TRABAJO");
				sql = conexion.createStatement();
				rs = sql.executeQuery("SELECT ID, Nombre, Telefono, DNI FROM Clientes");

				while (rs.next()) {
					modeloTabla.addRow(new Object[] { rs.getObject("ID"), rs.getObject("Nombre"),
							rs.getObject("Telefono"), rs.getObject("DNI") });
				}

				conexion.close();
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
			}
		} finally {
			System.out.println("Ningun error");
		}
	}
}
