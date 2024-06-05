<template>
    <br><br>
    <div class="card mb-4">
      <div class="card-header pb-0 d-flex justify-content-between align-items-center">
        <h6>Produtos</h6>
        <button class="btn btn-primary" @click="openModal(null)">Novo Produto</button>
      </div>
      <div class="card-body px-0 pt-0 pb-2">
        <div class="table-responsive p-0">
          <div class="p-3">
            <b-row>
              <b-col md="6" lg="4">
                <b-form-input v-model="searchQuery" placeholder="Pesquisar por nome ou descrição"></b-form-input>
              </b-col>
            </b-row>
          </div>
          <table class="table align-items-center mb-0">
            <thead>
              <tr>
                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nome</th>
                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Descrição</th>
                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Quantidade</th>
                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Preço</th>
                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Tipo do Produto</th>
                <th class="text-secondary opacity-7"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="product in filteredProducts" :key="product.idProd">
                <td>
                  <div class="d-flex px-2 py-1">
                    <div class="d-flex flex-column justify-content-center">
                      <h6 class="mb-0 text-sm">{{ product.nomeProd }}</h6>
                    </div>
                  </div>
                </td>
                <td>
                  <p class="text-xs text-secondary mb-0">{{ product.descricaoProd }}</p>
                </td>
                <td>
                  <p class="text-xs text-secondary mb-0">{{ product.quantidadeProd }}</p>
                </td>
                <td>
                  <p class="text-xs text-secondary mb-0">R$ {{ product.valorProd.toFixed(2) }}</p>
                </td>
                <td>
                  <p class="text-xs font-weight-bold mb-0">{{ product.idTipoProd.nomeTipoProduto }}</p>
                </td>
                <td class="align-middle">
                  <a href="javascript:;" @click="openModal(product)" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit product">
                    <i class="fas fa-pencil-alt"></i>
                  </a>
                  <a href="javascript:;" @click="deleteProduct(product.idProd)" class="text-secondary font-weight-bold text-xs ms-2" data-toggle="tooltip" data-original-title="Delete product">
                    <i class="fas fa-trash"></i>
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
  
      <!-- Modal -->
      <b-modal v-model="showModal" :title="modalTitle" hide-footer size="lg">
        <b-form @submit.prevent="submitForm">
          <b-form-group label="Nome do Produto">
            <b-form-input v-model="form.nomeProd" required></b-form-input>
          </b-form-group>
          <b-form-group label="Descrição">
            <b-form-input v-model="form.descricaoProd" required></b-form-input>
          </b-form-group>
          <b-form-group label="Tipo do Produto">
            <b-form-select v-model="form.idTipoProd.idTipoProduto" :options="tiposOptions" required></b-form-select>
          </b-form-group>
          <b-form-group label="Preço">
            <b-input-group prepend="R$">
              <b-form-input v-model="form.valorProd" type="number" step="0.01" required></b-form-input>
            </b-input-group>
          </b-form-group>
          <b-form-group label="Quantidade">
            <b-form-input v-model="form.quantidadeProd" type="number" required></b-form-input>
          </b-form-group>
          <div class="text-right">
            <b-button type="submit" variant="success">Salvar</b-button>
            <b-button @click="showModal = false" variant="secondary" class="ml-2">Cancelar</b-button>
          </div>
        </b-form>
      </b-modal>
    </div>
  </template>
  
  <script>
  import { apiClientProdutos } from "@/services/axios.js";
  
  export default {
    name: "Products",
    data() {
      return {
        products: [],
        showModal: false,
        modalTitle: 'Novo Produto',
        form: {
          idProd: null,
          nomeProd: '',
          descricaoProd: '',
          idTipoProd: {
            idTipoProduto: '',
            nomeTipoProduto: ''
          },
          valorProd: 0,
          quantidadeProd: 0,
        },
        tiposOptions: [],
        searchQuery: '',
        fields: [
          { key: 'nomeProd', label: 'Nome' },
          { key: 'descricaoProd', label: 'Descrição' },
          { key: 'quantidadeProd', label: 'Quantidade' },
          { key: 'valorProd', label: 'Preço', formatter: value => `R$ ${value.toFixed(2)}` },
          { key: 'idTipoProd.nomeTipoProduto', label: 'Tipo do Produto' },
          { key: 'alterar', label: 'Alterar', sortable: false },
          { key: 'excluir', label: 'Excluir', sortable: false },
        ],
      };
    },
    computed: {
      filteredProducts() {
        return this.products.filter(product => {
          const query = this.searchQuery.toLowerCase();
          return (
            product.nomeProd.toLowerCase().includes(query) ||
            product.descricaoProd.toLowerCase().includes(query)
          );
        });
      }
    },
    methods: {
      fetchProducts() {
        apiClientProdutos.get("/allprodutos")
          .then(response => {
            this.products = response.data;
          })
          .catch(error => {
            console.error("Erro ao buscar produtos:", error);
          });
      },
      fetchTipos() {
        apiClientProdutos.get("/allprodtipo")
          .then(response => {
            this.tiposOptions = response.data.map(tipo => ({
              value: tipo.idTipoProduto,
              text: tipo.nomeTipoProduto,
            }));
          })
          .catch(error => {
            console.error("Erro ao buscar tipos de produtos:", error);
          });
      },
      openModal(product) {
        if (product) {
          this.modalTitle = 'Editar Produto';
          this.form = { ...product, idTipoProd: product.idTipoProd || { idTipoProduto: '', nomeTipoProduto: '' } };
        } else {
          this.modalTitle = 'Novo Produto';
          this.form = {
            idProd: null,
            nomeProd: '',
            descricaoProd: '',
            idTipoProd: {
              idTipoProduto: '',
              nomeTipoProduto: ''
            },
            valorProd: 0,
            quantidadeProd: 0,
          };
        }
        this.showModal = true;
      },
      submitForm() {
        const payload = {
          idProd: this.form.idProd,
          nomeProd: this.form.nomeProd,
          descricaoProd: this.form.descricaoProd,
          quantidadeProd: this.form.quantidadeProd,
          valorProd: this.form.valorProd,
          ativo: true,
          idTipoProd: {
            idTipoProduto: this.form.idTipoProd.idTipoProduto
          }
        };
        if (this.form.idProd) {
          apiClientProdutos.patch(`/${this.form.idProd}`, payload)
            .then(() => {
              this.fetchProducts();
              this.showModal = false;
            })
            .catch(error => {
              console.error("Erro ao atualizar produto:", error);
            });
        } else {
          apiClientProdutos.post("/", payload)
            .then(() => {
              this.fetchProducts();
              this.showModal = false;
            })
            .catch(error => {
              console.error("Erro ao criar produto:", error);
            });
        }
      },
      deleteProduct(id) {
        apiClientProdutos.delete(`/${id}`)
          .then(() => {
            this.fetchProducts();
          })
          .catch(error => {
            console.error("Erro ao excluir produto:", error);
          });
      },
    },
    mounted() {
      this.fetchProducts();
      this.fetchTipos();
    },
  };
  </script>
  
  <style scoped>
  .table-responsive {
    padding: 1rem;
  }
  
  .mb-4 {
    margin-left: 25px; 
    margin-right: 80px; 
  }
  
  @media (min-width: 576px) {
    .modal-dialog {
      max-width: 800px !important;
      margin: 1.75rem auto;
    }
  }
  
  .form-control {
    font-size: 14px;
  }
  </style>
  