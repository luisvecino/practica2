package org.luis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.luis.model.*;

import org.luis.model.Alumnos;
import org.luis.connection.*;

public class AlumnosDao {

	private Connect con;
	private Connection connection;

	//
	public AlumnosDao() {

	}

	public AlumnosDao(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Connect();
	}

	// insertar alumnos

	PreparedStatement st = null;
	Statement statement = null;

	public boolean insertar(Alumnos alumno) throws SQLException {
		String sql = "INSERT INTO alumnos (Nombre, Apellidos, Dni, Telefono) VALUES (?, ?, ?,?)";

		connection = Connect.getConnection();
		st = connection.prepareStatement(sql);

		st.setString(1, alumno.getNombreA());
		st.setString(2, alumno.getApellidosA());
		st.setInt(3, alumno.getDni());
		st.setString(4, alumno.getTel());

		boolean rowInserted = st.executeUpdate() > 0;
		st.close();
		con.close(connection);
		return rowInserted;
	}
	/*
	 * //eliminar public boolean eliminar(Alumnos alumno) throws SQLException {
	 * boolean rowEliminar = false; String sql = "DELETE FROM alumnos WHERE ID=?";
	 * connection = con.getConnection();
	 * 
	 * PreparedStatement statement = connection.prepareStatement(sql);
	 * statement.setInt(1, alumno.getId());
	 * 
	 * rowEliminar = statement.executeUpdate() > 0; statement.close();
	 * con.close(connection);
	 * 
	 * return rowEliminar; }
	 */

	// ELIMINAR ALUMNO
	Alumnos alumno = new Alumnos();

	public boolean eliminarAlumno(int dni) {
		boolean respuesta = false;
		try {
			String sql = "delete from alumnos where Dni = 'dni' ";
			System.out.println("Hola" + sql);
			connection = Connect.getConnection();
			statement = connection.createStatement();
			statement.executeQuery(sql);
			respuesta = true;
		} catch (SQLException ex) {
			System.out.println("No se ha borrado nada");
		}

		return respuesta;
	}

	// listar todos los productos
	public List<Alumnos> listarAlumnos() throws SQLException {

		List<Alumnos> listaAlumnos = new ArrayList<Alumnos>();
		String sql = "SELECT * FROM alumnos";
		connection = con.getConnection();

		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			String name = rs.getString("Nombre");
			String ape = rs.getString("Apellidos");
			int dni = rs.getInt("Dni");
			String tel = rs.getString("Telefono");

			Alumnos alumnos = new Alumnos(name, ape, dni, tel);
			listaAlumnos.add(alumnos);
		}
		con.close(connection);
		return listaAlumnos;
	}

	// actualizar
	public boolean actualizar(Alumnos alumnos) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE articulos SET nombre=?,apellidos=?,dni=?,telefono=? WHERE id=?";
		connection = con.getConnection();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, alumnos.getNombreA());
		statement.setString(2, alumnos.getApellidosA());
		statement.setInt(3, alumnos.getDni());
		statement.setString(4, alumnos.getTel());
		statement.setInt(5, alumnos.getId());

		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.close(connection);
		return rowActualizar;
	}

}