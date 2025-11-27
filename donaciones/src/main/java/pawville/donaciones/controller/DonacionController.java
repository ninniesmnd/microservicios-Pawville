package pawville.donaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawville.donaciones.model.Donacion;
import pawville.donaciones.service.DonacionService;

import java.util.List;

@RestController // 1. Indica que esta clase es un controlador REST
@RequestMapping("/api/donaciones")

public class DonacionController {
    @Autowired
    private DonacionService donacionService;

    @PostMapping
    public ResponseEntity<Donacion> CrearDonacion(@RequestBody Donacion donacion) {
        try{
            Donacion nuevaDonacion = donacionService.CrearDonacion(donacion);
            return new ResponseEntity<>(nuevaDonacion, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Donacion>> obtenerTodas() {
        return new ResponseEntity<>(donacionService.obtenerTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> obtenerDonacionPorId(@PathVariable Long id) {
        return donacionService.obtenerPorId(id)

                .map(donacion -> new ResponseEntity<>(donacion, HttpStatus.OK))

                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/buscar/email")
    public List<Donacion> buscarPorEmail(@RequestParam(required = true) String email) {
        return donacionService.buscarPorEmail(email);
    }

    @GetMapping("/buscar/nombre")
    public List<Donacion> buscarPorNombre(@RequestParam(required = true) String nombre) {
        return donacionService.buscarPorNombre(nombre);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Donacion> eliminarDonacionPorId(@PathVariable Long id) {
        donacionService.eliminarDonacion(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
