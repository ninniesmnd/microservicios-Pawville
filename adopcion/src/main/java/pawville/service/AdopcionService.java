package pawville.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawville.model.Adopcion;
import pawville.repository.AdopcionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    public List<Adopcion> listarSolicitudes() {
        return adopcionRepository.findAll();
    }

    public Optional<Adopcion> buscarSolicitudPorId(Long id) {
        return adopcionRepository.findById(id);
    }

    public Optional<Adopcion> buscarSolicitudPorIdMascota(Long id) {
        return adopcionRepository.findByIdMascota(id);
    }
    public Adopcion crearSolicitud(Adopcion solicitud) {
        return adopcionRepository.save(solicitud);
    }

    public void eliminarSolicitud(Long id) {
        adopcionRepository.deleteById(id);
    }
}
