package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.empresa.Empresa;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaAlreadyExistsException;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaNotFoundException;
import sapc.sapcbackend.db.entities.empresa.exceptions.InvalidCnpjException;
import sapc.sapcbackend.db.entities.empresa.exceptions.ParametrizationAlreadyExistsException;
import sapc.sapcbackend.db.repositories.EmpresaRepository;
import sapc.sapcbackend.dto.empresa.EmpresaProfileResponseDTO;

import java.time.LocalDateTime;
import java.util.Optional;

//Ultimo commit
@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaProfileResponseDTO getFirstEmpresa() {
        Empresa firstEmpresa = empresaRepository.findFirstByOrderByIdDesc();

        if(firstEmpresa == null) {
            throw new EmpresaNotFoundException("Não existe empresa.");
        }

        return new EmpresaProfileResponseDTO(
                firstEmpresa.getId(),
                firstEmpresa.getNome(),
                firstEmpresa.getRazaoSocial(),
                firstEmpresa.getCnpj(),
                firstEmpresa.getLogoPequena(),
                firstEmpresa.getLogoGrande(),
                firstEmpresa.getEndereco(),
                firstEmpresa.getBairro(),
                firstEmpresa.getCidade(),
                firstEmpresa.getUf(),
                firstEmpresa.getData_criacao().toString(),
                firstEmpresa.getDiretor(),
                firstEmpresa.getSite(),
                firstEmpresa.getTelefone()
        );
    }

    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EmpresaNotFoundException("Empresa não existe"));
    }

    public Optional<Empresa> getEmpresaByCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public boolean existeEmpresa() {
        return empresaRepository.existsAny();
    }

    public Empresa saveEmpresa(Empresa empresa) {
        if (this.existeEmpresa()) {
            throw new ParametrizationAlreadyExistsException("Parametrizacao ja foi feita!");
        }

        if (!Empresa.isCNPJ(empresa.getCnpj())) {
            throw new InvalidCnpjException("CNPJ inválido");
        }

        Optional<Empresa> existeEmpresa = this.getEmpresaByCnpj(empresa.getCnpj());

        if (existeEmpresa.isPresent()) {
            throw new EmpresaAlreadyExistsException("Empresa ja existe com o CNPJ: " + Empresa.imprimeCNPJ(empresa.getCnpj()));
        }

        empresa.setData_criacao(LocalDateTime.now());
        return this.empresaRepository.save(empresa);
    }

    public Empresa atualizarEmpresa(Empresa empresa) {
        if (!Empresa.isCNPJ(empresa.getCnpj())) {
            throw new InvalidCnpjException("CNPJ inválido");
        }

        Optional<Empresa> existingEmpresa = this.getEmpresaByCnpj(empresa.getCnpj());
        if (existingEmpresa.isEmpty()) {
            throw new EmpresaNotFoundException("Empresa não existe");
        }

        existingEmpresa.get().setRazaoSocial(empresa.getRazaoSocial());
        existingEmpresa.get().setNome(empresa.getNome());
        existingEmpresa.get().setCnpj(empresa.getCnpj());
        existingEmpresa.get().setLogoPequena(empresa.getLogoPequena());
        existingEmpresa.get().setLogoGrande(empresa.getLogoGrande());
        existingEmpresa.get().setEndereco(empresa.getEndereco());
        existingEmpresa.get().setBairro(empresa.getBairro());
        existingEmpresa.get().setCidade(empresa.getCidade());
        existingEmpresa.get().setUf(empresa.getUf());
        existingEmpresa.get().setDiretor(empresa.getDiretor());
        existingEmpresa.get().setTelefone(empresa.getTelefone());
        existingEmpresa.get().setSite(empresa.getSite());

        return this.empresaRepository.save(existingEmpresa.get());
    }

    public boolean shutdownEmpresa(String cnpj) {
        Optional<Empresa> empresa = this.getEmpresaByCnpj(cnpj);

        if (empresa.isEmpty()) {
            throw new EmpresaNotFoundException("Empresa não existe com o CNPJ: " + cnpj);
        }

        try {
            this.empresaRepository.deleteById(empresa.get().getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
