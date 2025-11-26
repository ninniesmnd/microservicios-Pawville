package pawville.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pawville.model.Usuario;
import pawville.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con correo: " + correo);
        }
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(Long.valueOf(id));
    }

    public Usuario actualizarUsuario(Integer id, Usuario datosActualizados) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (datosActualizados.getNombre() != null) {
            usuario.setNombre(datosActualizados.getNombre());
        }
        if (datosActualizados.getCorreo() != null) {
            usuario.setCorreo(datosActualizados.getCorreo());
        }
        if (datosActualizados.getContrasena() != null) {
            usuario.setContrasena(datosActualizados.getContrasena());
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String contrasena) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);

        if (usuario.isPresent() && usuario.get().getContrasena().equals(contrasena)) {
            return usuario.get();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
    }


}