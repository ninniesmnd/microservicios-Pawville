package pawville.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "*")
public class ImagenController {

    private final Path rootLocation = Paths.get("subir/mascotas");

    public ImagenController() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de almacenamiento", e);
        }
    }

    @PostMapping("/subir")
    public ResponseEntity<String> subirImagen(@RequestParam("archivo") MultipartFile archivo) {
        try {
            if (archivo.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío");
            }

            //nombre unico para la imagen
            String extension = obtenerExtension(archivo.getOriginalFilename());
            String nombreArchivo = UUID.randomUUID().toString() + extension;

            // guardar archvo
            Path destinoArchivo = rootLocation.resolve(nombreArchivo);
            Files.copy(archivo.getInputStream(), destinoArchivo, StandardCopyOption.REPLACE_EXISTING);

            // retorna la URL de la imagen
            String urlImagen = "/api/imagenes/" + nombreArchivo;
            return ResponseEntity.ok(urlImagen);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }
    }

    @GetMapping("/{nombrearchivo:.+}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombrearchivo) {
        try {
            Path archivo = rootLocation.resolve(nombrearchivo);
            Resource recurso = new UrlResource(archivo.toUri());

            if (recurso.exists() && recurso.isReadable()) {
                // ver el tipo de contenido
                String contentType = Files.probeContentType(archivo);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "inline; filename=\"" + recurso.getFilename() + "\"")
                        .body(recurso);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private String obtenerExtension(String nombrearchivo) {
        if (nombrearchivo == null) return "";
        int ultimoPunto = nombrearchivo.lastIndexOf('.');
        return (ultimoPunto == -1) ? "" : nombrearchivo.substring(ultimoPunto);
    }
}