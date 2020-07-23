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

    public static class Crear{
        public String rut;
        public String nombre;
        public String apellido;
        public String direccion;
        public String telefono;
        public String fecha_nacimiento;
        public String antecedentes_medicos;
        public int estado;
        public String fecha_ingreso;
        public int diagnostico;
        public String programa_salud;

        public Crear(){

        }
    }

    @PostMapping("/ingresar")
    public boolean ingresarPaciente(@RequestBody @Valid Crear aux){

        Paciente paciente = new Paciente();
        paciente.setRut(aux.rut);
        paciente.setNombre(aux.nombre);
        paciente.setApellido(aux.apellido);
        paciente.setDireccion(aux.direccion);
        paciente.setTelefono(aux.telefono);
        paciente.setFechaNacimiento(Date.valueOf(aux.fecha_nacimiento));
        paciente.setAntecedentesMedicos(aux.antecedentes_medicos);
        paciente.setEstado(aux.estado);
        paciente.setFechaIngreso(Date.valueOf(aux.fecha_ingreso));
        paciente.setDiagnostico(aux.diagnostico);
        paciente.setProgramaSalud(aux.programa_salud);
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

    @GetMapping("/obtenerEstado/{estado}")
    public List<Long> obtenerEstado(@PathVariable("estado") int estado){
        return servicio.obtenerEstado(estado);
    }

    @PutMapping("/actualizarEstado/{id}")
    public boolean actualizarEstado(@PathVariable("id") long id, @RequestParam(name="estado", required=true) int estado){
        return servicio.actualizarEstado(id,estado);
    }

    public static class Datos{
        public String rut;
        public String nombre;
        public String apellido;
        public String direccion;
        public String telefono;
        public String fecha_nacimiento;
        public String antecedentes_medicos;
        public int diagnostico;
        public String programa_salud;

        public Datos(){

        }
    }



    @PutMapping("/actualizarDatos/{id}")
    public boolean actualizarDatos(@PathVariable("id") long id, @RequestBody Datos datos){
        return servicio.actualizarDatos(id, datos.rut, datos.nombre, datos.apellido, datos.direccion, datos.telefono, Date.valueOf(datos.fecha_nacimiento), datos.antecedentes_medicos,datos.diagnostico,datos.programa_salud);
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