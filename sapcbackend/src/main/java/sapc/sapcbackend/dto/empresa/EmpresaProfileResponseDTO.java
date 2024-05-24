package sapc.sapcbackend.dto.empresa;

public record EmpresaProfileResponseDTO(
        String nome,
        String razaoSocial,
        String cnpj,
        String logoPequena,
        String logoGrande,
        String endereco,
        String bairro,
        String cidade,
        String uf,
        String dataCriacao,
        String diretor,
        String site,
        String telefone
) {}