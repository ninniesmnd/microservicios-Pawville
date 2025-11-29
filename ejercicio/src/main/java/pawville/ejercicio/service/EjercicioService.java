package pawville.ejercicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawville.ejercicio.model.Ejercicio;
import pawville.ejercicio.repository.EjercicioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    /**
     * Obtiene todos los ejercicios.
     */
    public List<Ejercicio> obtenerTodos() {
        return ejercicioRepository.findAll();
    }

    /**
     * Obtiene un ejercicio por su ID.
     */
    public Optional<Ejercicio> obtenerPorId(Long id) {
        return ejercicioRepository.findById(id);
    }

    /**
     * Crea un nuevo ejercicio.
     */
    public Ejercicio crear(Ejercicio ejercicio) {
        // Al guardar, el ID se genera automáticamente
        return ejercicioRepository.save(ejercicio);
    }

    /**
     * Actualiza un ejercicio existente.
     */
    public Ejercicio actualizar(Long id, Ejercicio ejercicioDetalles) {
        return ejercicioRepository.findById(id).map(ejercicioExistente -> {
            ejercicioExistente.setNombre(ejercicioDetalles.getNombre());
            ejercicioExistente.setDescripcion(ejercicioDetalles.getDescripcion());
            ejercicioExistente.setDuracionDefault(ejercicioDetalles.getDuracionDefault());
            ejercicioExistente.setCaloriasDefault(ejercicioDetalles.getCaloriasDefault());
            return ejercicioRepository.save(ejercicioExistente);
        }).orElse(null); // O puedes lanzar una excepción si prefieres
    }

    /**
     * Elimina un ejercicio por su ID.
     */
    public void eliminar(Long id) {
        ejercicioRepository.deleteById(id);
    }
}