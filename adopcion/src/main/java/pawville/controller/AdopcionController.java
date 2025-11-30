package pawville.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawville.model.Adopcion;
import pawville.service.AdopcionService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adopcion")
@CrossOrigin(origins = "*")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;


    @GetMapping
    public ResponseEntity<List<Adopcion>> listarSolicitudes() {
        return ResponseEntity.ok(adopcionService.listarSolicitudes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> obtenerPorId(@PathVariable Long id) {
        return adopcionService.buscarSolicitudPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/mascota/{id}")
    public Optional<Adopcion> obtenerPorIdMascota(@PathVariable Long id) {
        return adopcionService.buscarSolicitudPorIdMascota(id);
    }
    
    @PostMapping
    public ResponseEntity<Adopcion> crearSolicitud(@RequestBody Adopcion solicitud) {
        return ResponseEntity.ok(adopcionService.crearSolicitud(solicitud));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        adopcionService.eliminarSolicitud(id);
        return ResponseEntity.ok().build();
    }

}
