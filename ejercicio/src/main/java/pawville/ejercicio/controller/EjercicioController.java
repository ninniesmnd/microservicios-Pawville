package pawville.ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawville.ejercicio.model.Ejercicio;
import pawville.ejercicio.service.EjercicioService;

import java.util.List;

@RestController
@RequestMapping("/api/ejercicios") // Endpoint base más específico
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;

    // GET: Obtener todos los ejercicios
    @GetMapping
    public List<Ejercicio> obtenerTodos() {
        return ejercicioService.obtenerTodos();
    }

    // GET: Obtener un ejercicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ejercicio> obtenerPorId(@PathVariable Long id) {
        return ejercicioService.obtenerPorId(id)
                .map(ResponseEntity::ok) // Si existe, retorna 200 OK con el cuerpo
                .orElse(ResponseEntity.notFound().build()); // Si no existe, retorna 404 NOT FOUND
    }

    // POST: Crear un nuevo ejercicio
    @PostMapping
    public Ejercicio crear(@RequestBody Ejercicio ejercicio) {
        return ejercicioService.crear(ejercicio);
    }

    // PUT: Actualizar un ejercicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Ejercicio> actualizar(@PathVariable Long id, @RequestBody Ejercicio ejercicioDetalles) {
        Ejercicio ejercicioActualizado = ejercicioService.actualizar(id, ejercicioDetalles);

        if (ejercicioActualizado != null) {
            return ResponseEntity.ok(ejercicioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Eliminar un ejercicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ejercicioService.eliminar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 NO CONTENT
    }
}