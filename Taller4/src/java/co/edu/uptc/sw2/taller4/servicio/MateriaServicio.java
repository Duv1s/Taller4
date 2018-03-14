/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uptc.sw2.taller4.servicio;

import co.edu.uptc.sw2.taller4.dto.MateriaDTO;
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
@Path("/Materia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MateriaServicio {

      @GET
    public List<MateriaDTO> obtenerTodosMaterias() {
        return ProveedorInformacion.instance().obtenerTodos(MateriaDTO.class);
    }
    
    @GET
    @Path("/{id}")
    public MateriaDTO obtenerMateria(@PathParam("id") Long id) {
        return (MateriaDTO) ProveedorInformacion.instance().obtener(MateriaDTO.class, id);
    }
    
    @POST
    public MateriaDTO guardarMateria(MateriaDTO dto) {
        return (MateriaDTO) ProveedorInformacion.instance().guardar(dto);
    }
    
    @DELETE
    @Path("/{id}")
    public void borrarMateria(@PathParam("id") Long id) {
        ProveedorInformacion.instance().eliminar(MateriaDTO.class, id);
    }
}
