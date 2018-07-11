package org.luis.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;

import org.luis.connection.Connect;
import org.luis.model.Alumnos;
import org.luis.dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controlador() {
		// TODO Auto-generated constructor stub
	}

	private final String PREFIX = "/WEB-INF/vistas/";
	private String NEXT_COMPONENT;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recojo datos del formulario
		String name = request.getParameter("name");
		String apel = request.getParameter("apel");
		String dni1 = request.getParameter("dni");
		int dni = Integer.parseInt(dni1);
		String tel = request.getParameter("tel");

		Alumnos al = new Alumnos();
		al.setNombreA(name);
		al.setApellidosA(apel);
		al.setDni(dni);
		al.setTel(tel);
		AlumnosDao alumnoDao = new AlumnosDao();

		// Datos del form eliminar
		String eliminar = request.getParameter("btnEliminarDB");

		if (eliminar != null) {
			alumnoDao.eliminarAlumno(al.getDni());
		}else
			System.out.println("No se ha eliminado ningún registro");

		Alumnos a2 = new Alumnos();

		Connection conn = null;

		Connect db = new Connect();

		Map<Integer, Alumnos> listaAlumnos = new HashMap<Integer, Alumnos>();

		// Instancio la clase AlumnoDao y llamo a su método INSERTAR
		try {
			alumnoDao.insertar(al);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Dejo la lista en el request

		request.setAttribute("listaAlumnos", listaAlumnos);

		NEXT_COMPONENT = PREFIX + "ShowAlumnos.jsp";

		// Pasar el control del flujo web a otro componente
		RequestDispatcher rd = request.getRequestDispatcher(NEXT_COMPONENT);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		// Recojo datos del formulario
		String name = request.getParameter("name");
		String apel = request.getParameter("apel");
		String dni1 = request.getParameter("dni");
		int dni = Integer.parseInt(dni1);
		String tel = request.getParameter("tel");
		// Connection conn = null;

		// Connect db = new Connect();

		Map<Integer, Alumnos> listaAlumnos = new HashMap<Integer, Alumnos>();
		Alumnos al = null;
		AlumnosDao alumno = new AlumnosDao();

		if (name.isEmpty() || apel.isEmpty() || dni1.isEmpty() || tel.isEmpty()) {
			System.out.println("Datos no válidos. Debe de rellenar todos los campos del formulario");
			
		} else {
			al = new Alumnos();
			try {
				alumno.insertar(al);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		// Añadimos el alumno a la lista
		listaAlumnos.put(0, al);

		// Dejo la lista en el request

		request.setAttribute("listaAlumnos", listaAlumnos);

		NEXT_COMPONENT = PREFIX + "ShowAlumnos.jsp";

		// Pasar el control del flujo web a otro componente
		//RequestDispatcher rd = request.getRequestDispatcher(NEXT_COMPONENT);
		//rd.forward(request, response);
//Prueba de commit	numero 3
	}

	/*
	 * al.setNombreA(name); al.setApellidosA(apel); al.setDni(dni); al.setTel(tel);
	 */

}
