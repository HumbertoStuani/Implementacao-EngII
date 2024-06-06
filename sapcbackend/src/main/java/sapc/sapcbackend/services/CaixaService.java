package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapc.sapcbackend.db.entities.Caixa;
import sapc.sapcbackend.db.entities.Usuarios;
import sapc.sapcbackend.db.repositories.CaixaRepository;
import sapc.sapcbackend.db.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaixaService {
    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Caixa abrirCaixa(Integer usuarioId, float saldoInicial) {  // Alterado para Integer
        Usuarios usuario = usuarioRepository.findOptionalById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Caixa caixa = new Caixa();
        caixa.setUsuario(usuario);
        caixa.setSaldoInicial(saldoInicial);
        caixa.setSaldo(saldoInicial);
        caixa.setDataAbertura(LocalDateTime.now());
        return caixaRepository.save(caixa);
    }

    @Transactional
    public Caixa fecharCaixa(Long id) {
        Caixa caixa = caixaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Caixa não encontrado"));

        caixa.setSaldoFinal(caixa.getSaldo());
        caixa.setDataFechamento(LocalDateTime.now());
        return caixaRepository.save(caixa);
    }

    public List<Caixa> getAllCaixas() {
        return caixaRepository.findAll();
    }

    public Caixa getCaixaById(Long id) {
        return caixaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Caixa não encontrado"));
    }
}
