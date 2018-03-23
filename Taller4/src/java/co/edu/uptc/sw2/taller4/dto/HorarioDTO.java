package co.edu.uptc.sw2.taller4.dto;

import java.util.List;
import java.util.ArrayList;

public class HorarioDTO {

    public HorarioDTO(){
        //constructor base
    }
    
    public HorarioDTO(Long id){
        this.id=id;
    }

	private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
    
    /**
    * @generated
    */
    private String diaSemana;
    
    /**
    * @generated
    */
    private String horaInicio;
    
    /**
    * @generated
    */
    private String horaFin;

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    
    
	
}
