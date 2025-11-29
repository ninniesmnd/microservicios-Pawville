package pawville.rutina.service;

import org.springframework.stereotype.Service;
import pawville.rutina.model.Rutina;
import pawville.rutina.repository.RutinaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaService {

    private final RutinaRepository rutinaRepository;

    public RutinaService(RutinaRepository rutinaRepository) {
        this.rutinaRepository = rutinaRepository;
    }

    public Rutina crearRutina(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    public List<Rutina> obtenerRutinasPorUsuario(String usuarioId) {
        return rutinaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Rutina> obtenerRutina(Long id) {
        return rutinaRepository.findById(id);
    }

    public Rutina actualizarRutina(Long id, Rutina datosActualizados) {
        return rutinaRepository.findById(id)
                .map(rutina -> {
                    if (datosActualizados.getNombre() != null)
                        rutina.setNombre(datosActualizados.getNombre());

                    if (datosActualizados.getDescripcion() != null)
                        rutina.setDescripcion(datosActualizados.getDescripcion());

                    if (datosActualizados.getEjercicioIds() != null)
                        rutina.setEjercicioIds(datosActualizados.getEjercicioIds());

                    return rutinaRepository.save(rutina);
                })
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada: " + id));
    }

    public void eliminarRutina(Long id) {
        rutinaRepository.deleteById(id);
    }
}
