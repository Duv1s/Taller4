/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uptc.sw2.taller4.servicio;

import co.edu.uptc.sw2.taller4.dto.EstudianteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ASUS-PC
 */
@Path("/Estudiante")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteServicio {

      @GET
    public List<EstudianteDTO> obtenerTodosEstudiantes() {
        return ProveedorInformacion.instance().obtenerTodos(EstudianteDTO.class);
    }
    @GET
    @Path("buscar")//constante
    public List<EstudianteDTO> buscarEstudiante(

        @QueryParam("nombre") String nombre,
         @QueryParam("apellido") String apellido){

       ArrayList listSearch=new ArrayList<EstudianteDTO>();
       List<EstudianteDTO> aux = this.obtenerTodosEstudiantes();
        for (int i = 0; i < aux.size(); i++) {
            EstudianteDTO estudianteDTO = aux.get(i);
            if (estudianteDTO!=null&&( estudianteDTO.getNombres().contains(nombre)||estudianteDTO.getApellidos().contains(apellido))) {
                listSearch.add(estudianteDTO);
            }
        }
        return (List<EstudianteDTO>) listSearch;
    }

    @GET
    @Path("/{id}")
    public EstudianteDTO obtenerEstudiante(@PathParam("id") Long id) {
        return (EstudianteDTO) ProveedorInformacion.instance().obtener(EstudianteDTO.class, id);
    }

    @POST
    public EstudianteDTO guardarEstudiante(EstudianteDTO dto) {
        return (EstudianteDTO) ProveedorInformacion.instance().guardar(dto);
    }

    @DELETE
    @Path("/{id}")
    public void borrarEstudiante(@PathParam("id") Long id) {
        ProveedorInformacion.instance().eliminar(EstudianteDTO.class, id);
    }
}
