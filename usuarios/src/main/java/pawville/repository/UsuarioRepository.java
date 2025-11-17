package pawville.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawville.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //@Override ?
    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByCorreo(String correo);
}
