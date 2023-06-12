/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package or.uv.examen_zurisaddai.models;

/**
 *
 * @author zurisaddairj
 */
public class DTOGrupo {
    
    private Long clavealumnos;
    private Long clavemateria;
    private String nombregrupo;

    public Long getClavealumnos() {
        return clavealumnos;
    }

    public void setClavealumnos(Long clavealumnos) {
        this.clavealumnos = clavealumnos;
    }

    public Long getClavemateria() {
        return clavemateria;
    }

    public void setClavemateria(Long clavemateria) {
        this.clavemateria = clavemateria;
    }

    public String getNombregrupo() {
        return nombregrupo;
    }

    public void setNombregrupo(String nombregrupo) {
        this.nombregrupo = nombregrupo;
    }
}
