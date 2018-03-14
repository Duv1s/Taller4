/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uptc.sw2.taller4.servicio;

import co.edu.uptc.sw2.taller4.dto.EstudianteDTO;
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
        for (int i = 0; i < this.obtenerTodosEstudiantes().size(); i++) {
            EstudianteDTO estudianteDTO = this.obtenerTodosEstudiantes().get(i);
            if (estudianteDTO!=null&&( estudianteDTO.getNombres().contains(nombre)||estudianteDTO.getNombres().contains(apellido))) {
                listSearch.add(estudianteDTO);
                //System.out.println(estudianteDTO.getNombres()+"   -------");

            }
//            else{
//                System.out.println(estudianteDTO.getNombres()+"   NO FOUND NOBRE ");
//            }

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
