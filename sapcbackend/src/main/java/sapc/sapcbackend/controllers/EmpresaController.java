package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.empresa.Empresa;
import sapc.sapcbackend.dto.empresa.EmpresaExistsResponseDTO;
import sapc.sapcbackend.dto.empresa.EmpresaProfileResponseDTO;
import sapc.sapcbackend.services.EmpresaService;

@CrossOrigin
@RestController
@RequestMapping("api/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/get/profile")
    public ResponseEntity<EmpresaProfileResponseDTO> getEmpresaProfile() {
        return new ResponseEntity<>(this.empresaService.getFirstEmpresa(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id) {
        Empresa empresa = this.empresaService.getEmpresaById(id);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @GetMapping("existe-param")
    public ResponseEntity<EmpresaExistsResponseDTO> haveParam() {
        return new ResponseEntity<>(new EmpresaExistsResponseDTO(this.empresaService.existeEmpresa()), HttpStatus.OK);
    }

    @PostMapping("/parametrizar")
    public ResponseEntity<Object> createEmpresa(@RequestBody Empresa empresa) {
        Empresa newEmpresa = this.empresaService.saveEmpresa(empresa);

        return new ResponseEntity<>(newEmpresa, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Empresa> updateEmpresa(@RequestBody Empresa empresa) {
        Empresa updatedEmpresa = this.empresaService.atualizarEmpresa(empresa);

        return new ResponseEntity<>(updatedEmpresa, HttpStatus.OK);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable String cnpj) {

        if(this.empresaService.shutdownEmpresa(cnpj)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
