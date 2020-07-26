package RubberDuck.ClientApi.Repositorios;

import java.io.Serializable;
import java.util.List;

import java.sql.Date;

import RubberDuck.ClientApi.Entidades.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository("repositoriopaciente")
public interface PacienteRepositorio extends JpaRepository<Paciente, Serializable>{

    public abstract Paciente findByRut(String rut);

    public abstract Paciente findById(long id);

    @Query(value="select id from public.paciente where estado = :estado",nativeQuery=true) 
    public abstract List<Long> obtenerEstado(@Param("estado") int estado);

    @Query(value="select id from public.paciente where diagnostico = :diagnostico",nativeQuery=true)
    public abstract List<Long> obtenerDiagnostico(@Param("diagnostico") int diagnostico);

    @Query(value="select id from public.paciente where estado = :estado and diagnostico = :diagnostico",nativeQuery=true)
    public abstract List<Long> obtenerEstadoDiagnostico(@Param("estado") int estado,@Param("diagnostico") int diagnostico);

}