package pruebas;

import java.sql.*;
import org.junit.*;
import clases.*;

public class AdministradorTest {

	Administrador a = null;
	Connection con =null;
	@Before
	public void setUp() throws Exception {
		 a  = new Administrador();
		 try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/centreciutat", "root", "");	

			} catch (SQLException e) {
				System.err.println("Error connexión BBDD");
				e.printStackTrace();
				
			}
	}
/*
	@Test
	public void testCrearUsuario() {
		//given
		System.out.println(" ");
		//when 
		try {
			Assert.assertTrue(a.crearUsuario(con));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//then
		System.out.println("Test Crear Usuario correcto!!");
	}
*/
	
	@Test
	public void testEliminarAlquiler() {
		//given
		System.out.println(" ");
		//when 
		try {
			Assert.assertTrue(a.eliminarAlquiler(con));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//then
		System.out.println("Test Eliminar Alquiler correcto!!");
	}
/*
	@Test
	public void testListarAlquiler() {
		//given
				System.out.println(" ");
				//when 
				try {
					Assert.assertEquals("49403993T", a.listarAlquiler(con));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				//then
				System.out.println("Test Listar Alquiler correcto!!");
	}
	*/

/*	
	@Test
	public void testEditarAlquiler() {
		//given
		int id = 2;
				System.out.println(" ");
				//when 
				try {
					Assert.assertTrue(a.editarAlquiler(con, id));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				//then
				System.out.println("Test Editar Alquiler correcto!!");
	}	
*/
}
