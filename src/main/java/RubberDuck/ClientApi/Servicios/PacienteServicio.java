package RubberDuck.ClientApi.Servicios;

import java.util.List;
import java.sql.Date;

import RubberDuck.ClientApi.Entidades.Paciente;
import RubberDuck.ClientApi.Repositorios.PacienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("serviciopaciente")
public class PacienteServicio{

    @Autowired
    @Qualifier("repositoriopaciente")
    private PacienteRepositorio repositorio;

    public boolean crear(Paciente paciente){
        try{
            repositorio.save(paciente);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public Paciente obtenerId(long id){
        return repositorio.findById(id);
    }

    public List<Long> obtenerEstado(int estado){
        return repositorio.obtenerEstado(estado);
    }

    public List<Long> obtenerDiagnostico(int diagnostico){
        return repositorio.obtenerDiagnostico(diagnostico);
    }

    public List<Long> obtenerEstadoDiagnostico(int estado, int diagnostico){
        return repositorio.obtenerEstadoDiagnostico(estado, diagnostico);
    }

    public boolean actualizar(Paciente paciente){
        try{
            repositorio.save(paciente);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean actualizarEstado(long id, int estado){
        try{
            Paciente paciente = repositorio.findById(id);
            paciente.setEstado(estado);
            repositorio.save(paciente);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean actualizarDatos(long id, String rut, String nombre, String apellido, String direccion, String telefono, Date fecha_nacimiento, String antecedentes_medicos, int diagnostico, String programa_salud){
        try{
            Paciente paciente = repositorio.findById(id);
            paciente.setRut(rut);
            paciente.setNombre(nombre);
            paciente.setApellido(apellido);
            paciente.setDireccion(direccion);
            paciente.setTelefono(telefono);
            paciente.setFechaNacimiento(fecha_nacimiento);
            paciente.setAntecedentesMedicos(antecedentes_medicos);
            paciente.setDiagnostico(diagnostico);
            paciente.setProgramaSalud(programa_salud);
            repositorio.save(paciente);
            return true;
        } catch (Exception e){
            return false;
        }

    }


    public boolean borrar(long id){
        try{
            Paciente paciente = repositorio.findById(id);
            repositorio.delete(paciente);
            return true;
        } catch (Exception e){
            return false;
        }
    }



}