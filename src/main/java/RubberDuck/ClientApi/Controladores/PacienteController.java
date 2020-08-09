package RubberDuck.ClientApi.Controladores;

import javax.validation.*;

import java.util.List;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

import RubberDuck.ClientApi.Entidades.Paciente;
import RubberDuck.ClientApi.Entidades.Respuesta1;
import RubberDuck.ClientApi.Entidades.Respuesta2;
import RubberDuck.ClientApi.Servicios.PacienteServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
public class PacienteController {
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
        public int diagnostico;
        public String programa_salud;
        public String formato_fecha;

        public Crear(){

        }
    }


    
    @PostMapping("/ingresar")
    public ResponseEntity<String> ingresarPaciente(@RequestBody @Valid Crear aux){

        try{ 
            if(aux.estado < 0 || aux.estado > 4){
            return new ResponseEntity<>(
                "Datos inválidos", HttpStatus.BAD_REQUEST);
            
            }

            if(aux.diagnostico < 0 || aux.diagnostico > 1){
            return new ResponseEntity<>(
                "Datos inválidos", HttpStatus.BAD_REQUEST);
        
            }
            DateFormat df = new SimpleDateFormat(aux.formato_fecha);
            java.util.Date FN = (java.util.Date) df.parse(aux.fecha_nacimiento);

            Date fn = new Date(FN.getTime());
            Paciente  p = servicio.obtenerRut(aux.rut);
            if(p != null){
                return new ResponseEntity<>(
                    "Paciente ya se encuentra en el sistema", HttpStatus.OK);
            }
            Paciente paciente = new Paciente();
            paciente.setRut(aux.rut);
            paciente.setNombre(aux.nombre);
            paciente.setApellido(aux.apellido);
            paciente.setDireccion(aux.direccion);
            paciente.setTelefono(aux.telefono);
            paciente.setFechaNacimiento(fn);
            paciente.setAntecedentesMedicos(aux.antecedentes_medicos);
            paciente.setEstado(aux.estado);

            java.util.Date date = new Date(System.currentTimeMillis());

            paciente.setFechaIngreso(new Date(date.getTime()));
            paciente.setDiagnostico(aux.diagnostico);
            paciente.setProgramaSalud(aux.programa_salud);
               
            servicio.crear(paciente);
            return new ResponseEntity<>(
                "Ingreso exitoso", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(
                "Datos inválidos", HttpStatus.BAD_REQUEST);
        }
    }

    

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable("id") long id) {
        if(id < 0){
            return new ResponseEntity<>(
                    "Identificador de paciente inválido", HttpStatus.BAD_REQUEST);
        }

        try{
            if(!servicio.borrar(id)){
                return new ResponseEntity<>(
                    "Paciente inexistente", HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    "Eliminación exitosa", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "Identificador de paciente inválido", HttpStatus.BAD_REQUEST);
            }
    }

    
    @GetMapping("/obtenerId/{id}")
    public Respuesta1 obtenerId(@PathVariable("id") long id){
        Respuesta1 respuesta = new Respuesta1();
        if(id<0){
            respuesta.res = null;
            respuesta.status = "Identificador de paciente no válido";
            return respuesta;
        }
        try{
            Paciente paciente = servicio.obtenerId(id);
            respuesta.res = paciente;
            respuesta.status = "ok";
            return respuesta;
        }
        catch (Exception e){
            respuesta.status = "Identificador de paciente no válido";
            respuesta.res = null;
            return respuesta;
        }
    }
    


    @GetMapping("/obtenerEstado/{estado}")
    public Respuesta2 obtenerEstado(@PathVariable("estado") int estado){

        Respuesta2 respuesta = new Respuesta2();

        if(estado < 0 || estado > 4){
            respuesta.status = "Estado no válido";
            return respuesta;
        }
        try{
            List<Long> pacientes = servicio.obtenerEstado(estado);
            respuesta.res = pacientes;
            respuesta.status = "ok";
            return respuesta;
        }
        catch (Exception e){
            respuesta.status = "Estado no válido";
            respuesta.res = null;
            return respuesta;
        }
    }

    @PutMapping("/actualizarEstado/{id}")
    public ResponseEntity<String> actualizarEstado(@PathVariable("id") long id, @RequestParam(name="estado", required=true) int estado){
        if(id < 0 || estado < 0 || estado > 5 ){
            return new ResponseEntity<>(
                    "Datos inválidos", HttpStatus.BAD_REQUEST);
        }
        try{
            if(!servicio.actualizarEstado(id,estado)){
                return new ResponseEntity<>(
                    "Paciente inexistente", HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    "Actualización exitosa", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "Datos inválidos", HttpStatus.BAD_REQUEST);
            }
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
        public String formato_fecha;

        public Datos(){

        }
    }

    @PutMapping("/actualizarDatos/{id}")
    public ResponseEntity<String> actualizarDatos(@PathVariable("id") long id, @RequestBody Datos datos){
        if(id < 0 || datos.diagnostico < 0 || datos.diagnostico > 1){
            return new ResponseEntity<>(
                    "Datos inválidos", HttpStatus.BAD_REQUEST);
        }
        try{
            DateFormat df = new SimpleDateFormat(datos.formato_fecha);
            java.util.Date FN = (java.util.Date) df.parse(datos.fecha_nacimiento);

            Date fn = new Date(FN.getTime());
            if(!servicio.actualizarDatos(id, datos.rut, datos.nombre, datos.apellido, datos.direccion, datos.telefono, fn, datos.antecedentes_medicos,datos.diagnostico,datos.programa_salud)){
                return new ResponseEntity<>(
                    "Paciente inexistente", HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    "Actualización exitosa", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "Datos inválidos", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/obtenerDiagnostico/{diagnostico}")
    public Respuesta2 obtenerDiagnostico(@PathVariable("diagnostico") int diagnostico){
        
        Respuesta2 respuesta = new Respuesta2();

        if(diagnostico < 0 || diagnostico > 1){
            respuesta.status = "Diagnóstico no válido";
            return respuesta;
        }
        try{
            List<Long> pacientes = servicio.obtenerDiagnostico(diagnostico);
            respuesta.res = pacientes;
            respuesta.status = "ok";
            return respuesta;
        }
        catch (Exception e){
            respuesta.status = "Diagnóstico no válido";
            respuesta.res = null;
            return respuesta;
        }
    }

    @GetMapping("/obtenerEstadoDiagnostico")
    public Respuesta2 obtenerEstadoDiagnostico( @RequestParam(name="estado", required=true) int estado, @RequestParam(name="diagnostico", required=true) int diagnostico){

        Respuesta2 respuesta = new Respuesta2();

        if(diagnostico < 0 || diagnostico > 1 || estado < 0 || estado > 4){
            respuesta.status = "Estado y/o Diagnóstico inválido";
            return respuesta;
        }
        try{
            List<Long> pacientes = servicio.obtenerEstadoDiagnostico(estado,diagnostico);
            respuesta.res = pacientes;
            respuesta.status = "ok";
            return respuesta;
        }
        catch (Exception e){
            respuesta.status = "Estado y/o Diagnóstico inválido";
            respuesta.res = null;
            return respuesta;
        }
    }

    @GetMapping("/obtenerTodo")
    public List<Paciente> obtenerTodo(){
        return servicio.obtenerTodo();
    }

}