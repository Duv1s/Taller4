/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uptc.sw2.taller4.servicio;

import co.edu.uptc.sw2.taller4.dto.ProfesorDTO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ASUS-PC
 */
@Path("/Profesor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfesorServicio {

      @GET
    public List<ProfesorDTO> obtenerTodosProfesores() {
        return ProveedorInformacion.instance().obtenerTodos(ProfesorDTO.class);
    }
    
    @GET
    @Path("/{id}")
    public ProfesorDTO obtenerProfesor(@PathParam("id") Long id) {
        return (ProfesorDTO) ProveedorInformacion.instance().obtener(ProfesorDTO.class, id);
    }
    
    @POST
    public ProfesorDTO guardarProfesor(ProfesorDTO dto) {
        return (ProfesorDTO) ProveedorInformacion.instance().guardar(dto);
    }
    
    @DELETE
    @Path("/{id}")
    public void borrarProfesor(@PathParam("id") Long id) {
        ProveedorInformacion.instance().eliminar(ProfesorDTO.class, id);
    }
}
