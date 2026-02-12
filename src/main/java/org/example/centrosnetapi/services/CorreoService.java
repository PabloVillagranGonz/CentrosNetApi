package org.example.centrosnetapi.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.models.Correo;
import org.example.centrosnetapi.models.UsuarioCorreo;
import org.example.centrosnetapi.repositories.CorreoRepository;
import org.example.centrosnetapi.repositories.UserRepository;
import org.example.centrosnetapi.repositories.UsuarioCorreoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CorreoService {

    private final CorreoRepository correoRepository;
    private final UsuarioCorreoRepository usuarioCorreoRepository;
    private final UserRepository userRepository;

    // 📥 INBOX
    public List<Map<String, Object>> getInbox(Long userId) {
        return usuarioCorreoRepository.findInbox(userId);
    }

    // 📤 ENVIADOS
    public List<Map<String, Object>> getSent(Long userId) {
        return correoRepository.findSentByUser(userId);
    }

    // ✅ MARCAR COMO LEÍDO
    @Transactional
    public void markAsRead(Long correoId, Long userId) {
        usuarioCorreoRepository.markAsRead(correoId, userId);
    }
    // 🗑️ BORRAR
    @Transactional
    public void deleteForUser(Long correoId, Long userId) {
        usuarioCorreoRepository.markAsDeleted(correoId, userId);
    }
    // 🔢 CONTADOR
    public Map<String, Integer> getEmailCount(Long userId) {
        int enviados = correoRepository.countSent(userId);
        int recibidos = correoRepository.countReceived(userId);

        return Map.of(
                "enviados", enviados,
                "recibidos", recibidos
        );
    }

    public void sendCorreo(
            Long emisorId,
            Long destinatarioId,
            String asunto,
            String cuerpo
    ) {
        Correo correo = new Correo();

        correo.setEmisor(
                userRepository.findById(emisorId)
                        .orElseThrow(() -> new RuntimeException("EMISOR_NOT_FOUND"))
        );

        correo.setDestinatario(
                userRepository.findById(destinatarioId)
                        .orElseThrow(() -> new RuntimeException("DESTINATARIO_NOT_FOUND"))
        );

        correo.setAsunto(asunto);
        correo.setCuerpo(cuerpo);

        correoRepository.save(correo);

        UsuarioCorreo uc = new UsuarioCorreo();
        uc.setCorreo(correo);
        uc.setUsuario(correo.getDestinatario());
        uc.setLeido(false);

        usuarioCorreoRepository.save(uc);
    }
}
