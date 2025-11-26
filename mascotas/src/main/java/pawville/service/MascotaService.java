package pawville.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawville.model.Mascota;
import pawville.repository.MascotaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> buscarMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Transactional
    public Mascota actualizarMascota(Long id, Mascota mascotaActualizada) {
        return mascotaRepository.findById(id)
                .map(mascota -> {
                    if (mascotaActualizada.getNombre() != null) {
                        mascota.setNombre(mascotaActualizada.getNombre());
                    }
                    if (mascotaActualizada.getTipo() != null) {
                        mascota.setTipo(mascotaActualizada.getTipo());
                    }
                    if (mascotaActualizada.getEdad() != null) {
                        mascota.setEdad(mascotaActualizada.getEdad());
                    }
                    if (mascotaActualizada.getDescripcion() != null) {
                        mascota.setDescripcion(mascotaActualizada.getDescripcion());
                    }
                    if (mascotaActualizada.getLocalidad() != null) {
                        mascota.setLocalidad(mascotaActualizada.getLocalidad());
                    }
                    if (mascotaActualizada.getGenero() != null) {
                        mascota.setGenero(mascotaActualizada.getGenero());
                    }
                    if (mascotaActualizada.getTamano() != null) {
                        mascota.setTamano(mascotaActualizada.getTamano());
                    }
                    if (mascotaActualizada.getRaza() != null) {
                        mascota.setRaza(mascotaActualizada.getRaza());
                    }
                    // url
                    if (mascotaActualizada.getNombreimagen() != null) {
                        mascota.setNombreimagen(mascotaActualizada.getNombreimagen());
                    }
                    return mascotaRepository.save(mascota);
                }).orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));
    }
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    //FILTROS
    public List<Mascota> obtenerMascotasPorTipo(String tipo) {
        return mascotaRepository.findByTipo(tipo);
    }

    public List<Mascota> obtenerMascotasPorLocalidad(String localidad) {
        return mascotaRepository.findByLocalidad(localidad);
    }

    public List<Mascota> obtenerMascotasPorGenero(String genero) {
        return mascotaRepository.findByGenero(genero);
    }

    public List<Mascota> obtenerMascotasPorTamano(String tamano) {
        return mascotaRepository.findByTamano(tamano);
    }

    // ELIMINADO: obtenerImagenMascota (ya que su lógica está en ImagenController)
}