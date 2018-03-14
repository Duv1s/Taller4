package co.edu.uptc.sw2.taller4.servicio;

import co.edu.uptc.sw2.taller4.dto.CarreraDTO;
import co.edu.uptc.sw2.taller4.dto.FacultadDTO;
import co.edu.uptc.sw2.taller4.dto.MunicipioDTO;
import co.edu.uptc.sw2.taller4.dto.ProfesorDTO;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProveedorInformacion<T> {

    private static ProveedorInformacion proveedorInformacion;

    private ProveedorInformacion() {

    }

    public static ProveedorInformacion instance() {
        if (proveedorInformacion == null) {
            proveedorInformacion = new ProveedorInformacion();
            MunicipioDTO municipioDTO = new MunicipioDTO();
            municipioDTO.setNombre("Pacho");
            proveedorInformacion.guardar(municipioDTO);
            
            FacultadDTO facultadDTO = new FacultadDTO();
            facultadDTO.setNombre("Ingenieria");
            proveedorInformacion.guardar(facultadDTO);
            
            CarreraDTO carreraDTO = new CarreraDTO();
            carreraDTO.setFacultad(facultadDTO);
            carreraDTO.setNombre("Sistemas");
            proveedorInformacion.guardar(carreraDTO);
            
            ProfesorDTO profesorDTO = new ProfesorDTO();
            profesorDTO.setNombres("Profe 1");
            profesorDTO.setApellidos("....");
            profesorDTO.setDocumento("123");
            proveedorInformacion.guardar(profesorDTO);
        }
        return proveedorInformacion;
    }

    private Map<String, Map<Long, Object>> almacenamiento = new HashMap<>();

    private Map<String, Long> secuencia = new HashMap<>();

    public List<T> obtenerTodos(Class<T> tabla) {
        if (almacenamiento.get(tabla.getName()) == null) {
            return new ArrayList<>();
        }
        return (List<T>) new ArrayList<>(almacenamiento.get(tabla.getName()).values());
    }

    public T obtener(Class<T> tabla, Long id) {
        if (almacenamiento.get(tabla.getName()) == null) {
            return null;
        }
        return (T) almacenamiento.get(tabla.getName()).get(id);
    }

    public T guardar(T object) {
        try {
            Method m = object.getClass().getMethod("getId");
            if (m.invoke(object) == null) {
                //guardar
                if (secuencia.get(object.getClass().getName()) == null) {
                    secuencia.put(object.getClass().getName(), 1L);
                }
                
                
                
                if (almacenamiento.get(object.getClass().getName()) == null) {
                    almacenamiento.put(object.getClass().getName(), new HashMap<>());

                }
                Long id = secuencia.get(object.getClass().getName());
                almacenamiento.get(object.getClass().getName()).put(id, object);
                m = object.getClass().getMethod("setId", Long.class);
                m.invoke(object, id);
                secuencia.put(object.getClass().getName(), id + 1);
            } else {
                //actualizar
                almacenamiento.get(object.getClass().getName()).replace((Long) m.invoke(object), object);
            }
            return (T) almacenamiento.get(object.getClass().getName()).get(secuencia.get(object.getClass().getName()));
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ProveedorInformacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ProveedorInformacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProveedorInformacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ProveedorInformacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ProveedorInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void eliminar(Class tabla, Long id) {
        almacenamiento.get(tabla.getName()).remove(id);
    }

}
