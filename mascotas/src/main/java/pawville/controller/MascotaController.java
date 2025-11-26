package pawville.controller;

import pawville.model.Mascota;
import pawville.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*") // peticiones desde React
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerTodasLasMascotas() {
        List<Mascota> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        return mascotaService.buscarMascotaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mascota> crearMascota(
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam("edad") String edad,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("localidad") String localidad,
            @RequestParam("genero") String genero,
            @RequestParam("tamano") String tamano,
            @RequestParam("raza") String raza,
            @RequestParam(value = "urlImagen", required = false) String urlImagen) {
        try {
            Mascota mascota = new Mascota();
            mascota.setNombre(nombre);
            mascota.setTipo(tipo);
            mascota.setEdad(edad);
            mascota.setDescripcion(descripcion);
            mascota.setLocalidad(localidad);
            mascota.setGenero(genero);
            mascota.setTamano(tamano);
            mascota.setRaza(raza);
            mascota.setNombreimagen(urlImagen);

            Mascota nuevaMascota = mascotaService.crearMascota(mascota);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascotaActualizada) {
        try {
            Mascota mascota = mascotaService.actualizarMascota(id, mascotaActualizada);
            return ResponseEntity.ok(mascota);
        } catch (RuntimeException e) {
            // runtime exception si no se encuentra
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }


    //FILTROS
    //tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Mascota>> obtenerPorTipo(@PathVariable String tipo) {
        List<Mascota> mascotas = mascotaService.obtenerMascotasPorTipo(tipo);
        return ResponseEntity.ok(mascotas);
    }

    //localidad
    @GetMapping("/localidad/{localidad}")
    public ResponseEntity<List<Mascota>> obtenerPorLocalidad(@PathVariable String localidad) {
        List<Mascota> mascotas = mascotaService.obtenerMascotasPorLocalidad(localidad);
        return ResponseEntity.ok(mascotas);
    }

    //género
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Mascota>> obtenerPorGenero(@PathVariable String genero) {
        List<Mascota> mascotas = mascotaService.obtenerMascotasPorGenero(genero);
        return ResponseEntity.ok(mascotas);
    }

    //tamaño
    @GetMapping("/tamano/{tamano}")
    public ResponseEntity<List<Mascota>> obtenerPorTamano(@PathVariable String tamano) {
        List<Mascota> mascotas = mascotaService.obtenerMascotasPorTamano(tamano);
        return ResponseEntity.ok(mascotas);
    }
}