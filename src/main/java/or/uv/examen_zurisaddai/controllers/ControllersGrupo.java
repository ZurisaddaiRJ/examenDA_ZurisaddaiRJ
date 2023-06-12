/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package or.uv.examen_zurisaddai.controllers;

import java.util.List;
import java.util.Optional;
import or.uv.examen_zurisaddai.models.Alumnos;
import or.uv.examen_zurisaddai.models.DTOGrupo;
import or.uv.examen_zurisaddai.models.Grupo;
import or.uv.examen_zurisaddai.models.Materia;
import or.uv.examen_zurisaddai.repository.RepositoryAlumno;
import or.uv.examen_zurisaddai.repository.RepositoryGrupo;
import or.uv.examen_zurisaddai.repository.RepositoryMateria;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zurisaddairj
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/grupo")
public class ControllersGrupo {

    @Autowired
    private RepositoryGrupo repositorygrupo;

    @Autowired
    private RepositoryAlumno repositoryalumno;

    @Autowired
    private RepositoryMateria repositorymateria;

    @GetMapping("/msg")
    public String holamundo() {
        return "Hola mundo";
    }

    @GetMapping("/{id}")
    public Grupo obtenergrupo(@PathVariable("id") long id) {
        Optional<Grupo> optionalGrupo = repositorygrupo.findById(id);
        if (optionalGrupo.isPresent()) {
            Grupo grupo = optionalGrupo.get();
            return grupo;
        } else {
            return null;
        }
    }

    @GetMapping
    public List<Grupo> obtenerTodosLasmaterias() {
        List<Grupo> grupo = (List<Grupo>) repositorygrupo.findAll();

        return grupo;
    }

    @PostMapping
    public Grupo crearGrupo(@RequestBody DTOGrupo grupoDTO) {
        Grupo grupo = new Grupo();

        BeanUtils.copyProperties(grupoDTO, grupo, "clavemateria", "clavealumnos"); // Excluir los campos claveMateria y claveAlumno de la copia
        // Asignar manualmente los valores de claveMateria y claveAlumno
        Materia materia = repositorymateria.findById(grupoDTO.getClavemateria())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + grupoDTO.getClavemateria()));
        Alumnos alumno = repositoryalumno.findById(grupoDTO.getClavealumnos())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + grupoDTO.getClavealumnos()));

        grupo.setMateria(materia);
        grupo.setAlumnos(alumno);
        grupo.setNombregrupo(grupoDTO.getNombregrupo());

        return repositorygrupo.save(grupo);
    }

    @PutMapping("/{id}")
    public Grupo actualizarGrupo(@PathVariable("id") Long id, @RequestBody DTOGrupo grupoDTO) {
        Optional<Grupo> optionalGrupo = repositorygrupo.findById(id);
        if (optionalGrupo.isPresent()) {
            Grupo grupo = optionalGrupo.get();
            grupo.setNombregrupo(grupoDTO.getNombregrupo());

            Grupo grupoActualizado = repositorygrupo.save(grupo);

            return grupoActualizado;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarGrupo(@PathVariable("id") Long id) {
        repositorygrupo.deleteById(id);
    }
}
