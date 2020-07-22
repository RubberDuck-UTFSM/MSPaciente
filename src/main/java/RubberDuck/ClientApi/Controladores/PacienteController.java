package RubberDuck.ClientApi.Controladores;

import javax.validation.*;

import java.util.List;
import java.sql.Date;

import RubberDuck.ClientApi.Entidades.Paciente;
import RubberDuck.ClientApi.Servicios.PacienteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController{
    @Autowired
    @Qualifier("serviciopaciente")
    PacienteServicio servicio;

    @PostMapping("/ingresar")
    public boolean ingresarPaciente(@RequestBody @Valid Paciente paciente){
        return servicio.crear(paciente);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminarPaciente(@PathVariable("id") long id){
        return servicio.borrar(id);
    }

    @GetMapping("/obtenerId/{id}")
    public Paciente obtenerId(@PathVariable("id") long id){
        return servicio.obtenerId(id);
    }

    @GetMapping("/obtenerEstado/{estado")
    public List<Long> obtenerEstado(@PathVariable("estado") int estado){
        return servicio.obtenerEstado(estado);
    }

    @PutMapping("/actualizarEstado/{id}")
    public boolean actualizarEstado(@PathVariable("id") long id, @RequestParam(name="estado", required=true) int estado){
        return servicio.actualizarEstado(id,estado);
    }

    public class Datos{
        String rut;
        String nombre;
        String apellido;
        String direccion;
        String telefono;
        Date fecha_nacimiento;
        String antecedentes_medicos;
        int diagnostico;
        String programa_salud;
    }

    @PutMapping("/actualizarDatos/{id}")
    public boolean actualizarDatos(@PathVariable("id") long id, @RequestBody Datos datos){
        return servicio.actualizarDatos(id, datos.rut, datos.nombre, datos.apellido, datos.direccion, datos.telefono, datos.fecha_nacimiento, datos.antecedentes_medicos,datos.diagnostico,datos.programa_salud);
    }

    @GetMapping("/obtenerDiagnostico/{diagnostico}")
    public List<Long> obtenerDiagnostico(@PathVariable("diagnostico") int diagnostico){
        return servicio.obtenerDiagnostico(diagnostico);
    }

    @GetMapping("/obtenerEstadoDiagnostico")
    public List<Long> obtenerEstadoDiagnostico( @RequestParam(name="estado", required=true) int estado, @RequestParam(name="diagnostico", required=true) int diagnostico){
        return servicio.obtenerEstadoDiagnostico(estado,diagnostico);
    }
}