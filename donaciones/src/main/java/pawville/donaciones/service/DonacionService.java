package pawville.donaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawville.donaciones.model.Donacion;
import pawville.donaciones.repository.DonacionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    public Donacion CrearDonacion(Donacion donacion) {
        if(donacion.getMonto() == null || donacion.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto de la donación debe ser positivo.");
        }
        return donacionRepository.save(donacion);
    }

    public List<Donacion> obtenerTodas() {
        return donacionRepository.findAll();
    }

    public Optional<Donacion> obtenerPorId(Long id) {
        return donacionRepository.findById(id);
    }

    public void eliminarDonacion(Long id) {
        donacionRepository.deleteById(id);
    }

    public List<Donacion> buscarPorEmail(String email) {
        return donacionRepository.findByEmail(email);
    }

    public List<Donacion> buscarPorNombre(String nombre) {
        return donacionRepository.findByNombre(nombre);
    }
}
