package pawville.adopcion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawville.adopcion.model.Adopcion;
import pawville.adopcion.repository.AdopcionRepository;

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

    public Adopcion crearSolicitud(Adopcion solicitud) {
        return adopcionRepository.save(solicitud);
    }

    public void eliminarSolicitud(Long id) {
        adopcionRepository.deleteById(id);
    }
}
