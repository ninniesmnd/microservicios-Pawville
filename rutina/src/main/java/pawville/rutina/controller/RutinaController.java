package pawville.rutina.controller;

import org.springframework.web.bind.annotation.*;
import pawville.rutina.model.Rutina;
import pawville.rutina.service.RutinaService;

import java.util.List;

@RestController
@RequestMapping("/rutina")
public class RutinaController {

    private final RutinaService rutinaService;

    public RutinaController(RutinaService rutinaService) {
        this.rutinaService = rutinaService;
    }

    @PostMapping
    public Rutina crearRutina(@RequestBody Rutina rutina) {
        return rutinaService.crearRutina(rutina);
    }

    @GetMapping
    public List<Rutina> obtenerPorUsuario(@RequestParam String usuarioId) {
        return rutinaService.obtenerRutinasPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public Rutina obtenerRutina(@PathVariable Long id) {
        return rutinaService.obtenerRutina(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada: " + id));
    }

    @PutMapping("/{id}")
    public Rutina actualizarRutina(@PathVariable Long id, @RequestBody Rutina rutina) {
        return rutinaService.actualizarRutina(id, rutina);
    }

    @DeleteMapping("/{id}")
    public void eliminarRutina(@PathVariable Long id) {
        rutinaService.eliminarRutina(id);
    }
}
