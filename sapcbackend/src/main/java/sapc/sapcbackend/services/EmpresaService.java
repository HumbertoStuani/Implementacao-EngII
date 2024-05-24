package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.empresa.Empresa;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaAlreadyExistsException;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaNotFoundException;
import sapc.sapcbackend.db.entities.empresa.exceptions.InvalidCnpjException;
import sapc.sapcbackend.db.entities.empresa.exceptions.ParametrizationAlreadyExistsException;
import sapc.sapcbackend.db.repositories.EmpresaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EmpresaNotFoundException("Empresa não existe"));
    }

    public Optional<Empresa> getEmpresaByCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public boolean getFirstEmpresa() {
        return empresaRepository.existsAny();
    }

    public Empresa saveEmpresa(Empresa empresa) {
        if(this.getFirstEmpresa()) {
            throw new ParametrizationAlreadyExistsException("Parametrizacao ja foi feita!");
        }

        if (!Empresa.isCNPJ(empresa.getCnpj())) {
            throw new InvalidCnpjException("CNPJ inválido");
        }

        Optional<Empresa> existeEmpresa = this.getEmpresaByCnpj(empresa.getCnpj());

        if(existeEmpresa.isPresent()) {
            throw new EmpresaAlreadyExistsException("Empresa ja existe com o CNPJ: " + Empresa.imprimeCNPJ(empresa.getCnpj()));
        }

        empresa.setData_criacao(LocalDateTime.now());
        return this.empresaRepository.save(empresa);
    }

    public boolean shutdownEmpresa(Long id) {
        if(!this.empresaRepository.existsById(id)){
            throw new EmpresaNotFoundException("Empresa não existe com o ID: " +id);
        }

        try {
            this.empresaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
