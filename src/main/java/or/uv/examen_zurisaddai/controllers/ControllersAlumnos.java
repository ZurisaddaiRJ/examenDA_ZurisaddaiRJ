/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package or.uv.examen_zurisaddai.controllers;

import java.util.List;
import java.util.Optional;
import or.uv.examen_zurisaddai.models.Alumnos;
import or.uv.examen_zurisaddai.models.DTOAlumnos;
import or.uv.examen_zurisaddai.repository.RepositoryAlumno;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/alumno")
public class ControllersAlumnos {

    @Autowired
    private RepositoryAlumno repositoryalumno;

    @GetMapping("/msg")
    public String holamundo() {
        return "Hola mundo";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumnos> obtenerAlumno(@PathVariable("id") long id) {
        Optional<Alumnos> optionalAlumnos = repositoryalumno.findById(id);

        if (!optionalAlumnos.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalAlumnos.get());
    }

    @GetMapping
    public ResponseEntity<List<Alumnos>> obtenerTodosLosalumnos() {
        return ResponseEntity.ok(repositoryalumno.findAll());
    }

    @PostMapping
    public Alumnos crearAlumno(@RequestBody DTOAlumnos alumnoDTO) {
        Alumnos alumno = new Alumnos();
        BeanUtils.copyProperties(alumnoDTO, alumno);
        return repositoryalumno.save(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumnos> actualizarAlumnos(@PathVariable("id") Long id, @RequestBody DTOAlumnos alumnoDTO) {
        Alumnos alumno = new Alumnos();
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setDireccion(alumnoDTO.getDireccion());
        alumno.setTelefono(alumnoDTO.getTelefono());
        Optional<Alumnos> optionalAlumnos = repositoryalumno.findById(id);

        if (!optionalAlumnos.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        alumno.setClavealumnos(optionalAlumnos.get().getClavealumnos());
        repositoryalumno.save(alumno);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void eliminarAlumno(@PathVariable("id") Long id) {
        repositoryalumno.findById(id);
    }
}
