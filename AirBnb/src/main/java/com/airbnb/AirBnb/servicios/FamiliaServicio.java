package com.airbnb.AirBnb.servicios;

import com.airbnb.AirBnb.entidades.Casa;
import com.airbnb.AirBnb.entidades.Comentario;
import com.airbnb.AirBnb.entidades.Familia;
import com.airbnb.AirBnb.entidades.Usuario;
import com.airbnb.AirBnb.enumeraciones.Rol;
import com.airbnb.AirBnb.excepciones.MiException;
import com.airbnb.AirBnb.repositorios.CasaRepositorio;
import com.airbnb.AirBnb.repositorios.FamiliaRepositorio;
import com.airbnb.AirBnb.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamiliaServicio  extends UsuarioServicio{

    @Autowired
    private FamiliaRepositorio familiaRepositorio;
    
    public void crearFamilia(String alias, String email, String clave, String clave2, Integer edadMin, Integer edadMax, Integer numHijos) throws MiException {

        validar(alias, email, clave, clave2, edadMin, edadMax, numHijos);

        Familia familia = new Familia();

        familia.setAlias(alias);
        familia.setEmail(email);
        familia.setClave(new BCryptPasswordEncoder().encode(clave));
        familia.setEdadMin(edadMin);
        familia.setEdadMax(edadMax);
        familia.setNumHijos(numHijos);
        familia.setFechaAlta(new Date());
        familia.setRol(Rol.FAMILIA);
        familia.setEstado(true);
        familia.setCasas(new ArrayList());
        
        familiaRepositorio.save(familia);

    }

    @Transactional
    public void modificarFamilia(String id, String alias, String email, String clave, String clave2, Integer edadMin, Integer edadMax, Integer numHijos) throws MiException {

        Optional<Familia> respuesta = familiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            validar(alias, email, clave, clave2, edadMin, edadMax, numHijos);

            Familia familia = respuesta.get();

            familia.setAlias(alias);
            familia.setEmail(email);
            familia.setClave(new BCryptPasswordEncoder().encode(clave));
            familia.setEdadMin(edadMin);
            familia.setEdadMax(edadMax);
            familia.setNumHijos(numHijos);
            familia.setFechaAlta(new Date());
            familia.setEstado(true);

            familiaRepositorio.save(familia);

        }
    }

    public List<Familia> listarFamilias() {
        List<Familia> familias = new ArrayList();
        familias = familiaRepositorio.findAll();
        return familias;
    }

  

    @Transactional(readOnly = true)
    public Familia getOne(String id) {
        return familiaRepositorio.getOne(id);
    }

    @Transactional
      public void eliminarFamilia(String id) {

        Optional<Familia> respuesta = familiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Familia familia = respuesta.get();

            familiaRepositorio.delete(familia);

        }
    }
      
       public void darBajaFamilia(String id) {

        Optional<Familia> respuesta = familiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Familia familia = respuesta.get();

            familia.setFechaBaja(new Date());
            familia.setEstado(false);

            familiaRepositorio.save(familia);
        }

    }
       
          public void darAltaFamilia(String id) {

        Optional<Familia> respuesta = familiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

             Familia familia = respuesta.get();

            familia.setFechaBaja(null);
            familia.setEstado(true);
            familia.setFechaAlta(new Date());

            familiaRepositorio.save(familia);
        }

    }

        private void validar(String alias, String email, String clave, String clave2, Integer edadMin, Integer edadMax, Integer numHijos) throws MiException {

        super.validar(alias, email, clave, clave2);

        if (numHijos == null) {
            throw new MiException("El número de hijos no puede ser nulo.");
        }
        
        if (numHijos < 0) {
            throw new MiException("El número de hijos debe ser positivo o cero.");
        }
        
        if (edadMin == null) {
            throw new MiException("La edad mínima no puede ser nula.");
        }
        
        if (edadMax == null) {
            throw new MiException("La edad maxima no puede ser nula.");
        }
        
        if (edadMin < 0 || edadMax < 0) {
            throw new MiException("La edad debe ser positiva o cero.");
        }
        
        if (edadMin > edadMax) {
            throw new MiException("La edad mínima debe ser menor o igual que la edad máxima");
        }

    }

}
