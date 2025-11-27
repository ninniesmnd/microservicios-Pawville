package pawville.adopcion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawville.adopcion.model.Adopcion;
import pawville.adopcion.service.AdopcionService;
import java.util.List;

@RestController
@RequestMapping("/api/adopcion")
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
