

// CLASE CON PATRÓN SINGLETON QUE CREA DATOS DE PRUEBA EN CASO DE NO EXISTIR AÚN LOS MISMOS

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Usuario;
import com.exception.ServiciosException;
import com.servicios.UsuariosBeanRemote;

public class DatosDePrueba {
	private static DatosDePrueba instancia = new DatosDePrueba(); 
	
	//constructor privado
	private DatosDePrueba() {	
		
		UsuariosBeanRemote usuarioBean=null;
		try {
			usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup("/GEONat/UsuariosBean!com.servicios.UsuariosBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		 try {
			if (usuarioBean.obtenerTodos().size()<1) {
				System.out.println("NO existen datos de prueba: "+ usuarioBean.obtenerTodos().size() );
				for (int i = 0; i < 10; i++) {
					Usuario u = new Usuario();
					u.setNombre("Nombre" + i);
					u.setApellido("Apellido" + i);
					u.setDireccion("Dirección" + i);
					u.setEmail("email" + i + "@email" + 1+ ".com");
					u.setNroDocumento("0000000" + i);
					u.setEstadoActivo(true);
					//u.setId_Ciudad(ciudadBean.read(1));
					//u.setId_Departamento(departamentoBean.read(1));
					//u.setId_Tipo(id_Tipo);
					
					// Llamada al servicio remoto UsuarioBeanRemote para solicitarle que cree el usuario "usuario1"
					try {
						usuarioBean.create(u);
						System.out.println("Usuario creado" + i);
						
					} catch (ServiciosException err) {
						
						err.printStackTrace();
					}
					
				}
			}
			else {
				System.out.println("Ya existen datos de prueba");
			}
				
			
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} ;
		
		
		
	} 
	
	//método estático que obtiene la instancia de la clase
	public static DatosDePrueba getInstance() { 
		return instancia; 
	}

}
