package RubberDuck.ClientApi.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

@Table(name="paciente")
@Entity
public class Paciente{
    @Id
    @GenericGenerator(name="incrementPaciente",strategy="increment")
    @GeneratedValue(generator="imcrementPaciente")
    
    @Column(name="id")
    long id;

    @Column(name="rut")
    String rut;

    @Column(name="nombre")
    String nombre;

    @Column(name="apellido")
    String apellido;

    @Column(name="direccion")
    String direccion;

    @Column(name="telefono")
    String telefono;

    @Column(name="fecha_nacimiento")
    Date fecha_nacimiento;

    @Column(name="antecedentes_medicos")
    String antecedentes_medicos;

    @Column(name="estado")
    int estado;

    @Column(name="fecha_ingreso")
    Date fecha_ingreso;

    @Column(name="diagnostico")
    int diagnostico;

    @Column(name="programa_salud")
    String programa_salud;

    public Paciente(){

    }

    public Paciente(long id, String rut, String nombre, String direccion, String telefono, Date fecha_nacimiento, String antecedentes_medicos, int estado, Date fecha_ingreso, int diagnostico, String programa_salud){
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.antecedentes_medicos = antecedentes_medicos;
        this.estado = estado;
        this.fecha_ingreso = fecha_ingreso;
        this.diagnostico = diagnostico;
        this.programa_salud = programa_salud;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getRut(){
        return this.rut;
    }

    public void setRut(String rut){
        this.rut = rut;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public Date getFechaNacimiento(){
        return this.fecha_nacimiento;
    }

    public void setFechaNacimiento(Date fecha_nacimiento){
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getAntecedentesMedicos(){
        return this.antecedentes_medicos;
    }

    public void setAntecedentesMedicos(String antecedentes_medicos){
        this.antecedentes_medicos = antecedentes_medicos;
    }

    public int getEstado(){
        return this.estado;
    }

    public void setEstado(int estado){
        this.estado = estado;
    }

    public Date getFechaIngreso(){
        return this.fecha_ingreso;
    }

    public void setFechaIngreso(Date fecha_ingreso){
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getDiagnostico(){
        return this.diagnostico;
    }

    public void setDiagnostico(int diagnostico){
        this.diagnostico = diagnostico;
    }

    public String getProgramaSalud(){
        return this.programa_salud;
    }

    public void setProgramaSalud(String programa_salud){
        this.programa_salud = programa_salud;
    }
}