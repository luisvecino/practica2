<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.luis.dao.AlumnosDao"%>
<%@page import="java.util.*"%>

<%@page import="org.luis.model.Alumnos"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- CSS ----------------------------->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="
https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css
"></script>

<!-- JS ------------------------------------------------->
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>

<!-- MODAL  -->

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jsp recibidor de lista de alumnos</title>
</head>
<body>
	<h1>Lista alumnos</h1>
	<!-- Trigger the modal with a button -->
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
		data-target="#myModal">A�adir Alumno</button>
	<form action="Controlador" name="form1" method="post">
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">A�adir Alumno</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body">
						<div class="modal-body mx-3">
							<div class="md-form mb-5">
								<label data-error="wrong" data-success="right" for="name">Nombre:
								</label> <i class="fa fa-user prefix grey-text"></i> <input type="text"
									name="name" class="form-control validate">
							</div>

							<div class="md-form mb-5">
								<label data-error="wrong" data-success="right" for="apel">Apellidos:</label>
								<i class="fa fa-envelope prefix grey-text"></i> <input
									type="text" name="apel" class="form-control validate">
							</div>

							<div class="md-form mb-5">
								<label data-error="wrong" data-success="right" for="dni">dni:</label>
								<i class="fa fa-tag prefix grey-text"></i> <input type="text"
									name="dni" class="form-control validate">
							</div>

							<div class="md-form">
								<label data-error="wrong" data-success="right" for="tel">telefono:</label>
								<i class="fa fa-pencil prefix grey-text"></i> <input type="text"
									name="tel" class="md-textarea form-control"></input>
							</div>
						</div>


					</div>
					<div class="modal-footer">
						<div class="d-flex justify-content-center">
							<button class="btn btn-primary">
								Send <i class="fa fa-paper-plane-o ml-1"></i>
							</button>
						</div>
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
	</form>

	<table id="tableList" class="table table-striped table-bordered"
		style="width: 100%">

		<thead>
			<tr>
				<th>id</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>DNI</th>
				<th>Tel�fono</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			<%
				AlumnosDao alumnosDao = new AlumnosDao();
				List<Alumnos> listAlum = alumnosDao.listarAlumnos();

				for (Alumnos alum : listAlum) {
			%>
			<tr>
				<td><%=alum.getId()%></td>
				<td><%=alum.getNombreA()%></td>
				<td><%=alum.getApellidosA()%></td>
				<td><%=alum.getDni()%></td>
				<td><%=alum.getTel()%></td>
				<td>
					<form action ="Controlador" method ="get" name ="formEliminar">
						<input type ="hidden" name ="btnHidden">
						<input type="submit" name ="btnEliminarDB" class="borrar" value="Eliminar fila y registro" onclick="confirmar();">
					</form>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>


	</table>


	<script type="text/javascript">
		// Cuando el documento este cargado

		$(document).ready(function() {
			$('#tableList').DataTable();
		});

		// POR AQU� HAY UN ERROR YA QUE BORRA LA FILA SIEMPRE
		// Funci�n para confirmar si de verdad quiere eliminar la fila
		
		function confirmar() {
			if (confirm('�Estas seguro de eliminar este alumno?')) {
				//C�digo JS(Jquery) para borrar la fila de la tabla en la que se haga CLICK

				$(document).on('click', '.borrar', function(event) {
					event.preventDefault();
					$(this).closest('tr').remove();
				});
				return true;
			}

			else
				return false;

		}
	</script>

</body>
</html>