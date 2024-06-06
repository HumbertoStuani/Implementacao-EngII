<template>
  <b-container>
    <b-form @submit.prevent="registerSale">
      <b-row>
        <b-col md="8">
          <b-form-group label="Cliente">
            <b-form-select v-model="sale.clienteId" :options="clientesOptions" required></b-form-select>
          </b-form-group>
        </b-col>
        <b-col md="4" class="d-flex align-items-end">
          <b-button @click="openNewClientModal" variant="secondary">Novo Cliente</b-button>
        </b-col>
      </b-row>

      <b-row>
        <b-col md="6">
          <b-form-group label="Produto">
            <b-form-select v-model="selectedProduct" :options="produtosOptions" @change="fetchProductDetails" required></b-form-select>
          </b-form-group>
        </b-col>
        <b-col md="6">
          <b-form-group label="Quantidade">
            <b-form-input type="number" v-model="quantidade" @change="updateTotal" required></b-form-input>
          </b-form-group>
        </b-col>
      </b-row>

      <b-row>
        <b-col md="12" class="text-right">
          <b-button @click="addProduct" variant="primary">Adicionar Produto</b-button>
        </b-col>
      </b-row>

      <b-row class="mt-4">
        <b-col md="12">
          <b-table :items="sale.produtos" :fields="productFields"></b-table>
        </b-col>
      </b-row>

      <b-row>
        <b-col md="6">
          <b-form-group label="Total">
            <b-input-group prepend="R$">
              <b-form-input v-model="formattedTotal" readonly></b-form-input>
            </b-input-group>
          </b-form-group>
        </b-col>
        <b-col md="6" class="text-right align-self-end">
          <b-button type="submit" variant="success">Registrar Venda</b-button>
        </b-col>
      </b-row>

      <!-- Modal para novo cliente -->
      <b-modal v-model="showNewClientModal" title="Novo Cliente" hide-footer size="lg">
        <b-form @submit.prevent="registerClient">
          <b-form-group label="Nome">
            <b-form-input v-model="newClient.nome" required></b-form-input>
          </b-form-group>
          <b-form-group label="Email">
            <b-form-input type="email" v-model="newClient.email" required></b-form-input>
          </b-form-group>
          <b-form-group label="Telefone">
            <b-form-input v-model="newClient.telefone" required></b-form-input>
          </b-form-group>
          <div class="text-right">
            <b-button type="submit" variant="success">Registrar Cliente</b-button>
            <b-button @click="showNewClientModal = false" variant="secondary" class="ml-2">Cancelar</b-button>
          </div>
        </b-form>
      </b-modal>
    </b-form>
  </b-container>
</template>

<script>
import { apiClientClientes, apiClientProdutos } from "@/services/axios.js";

export default {
  name: "NewSale",
  data() {
    return {
      sale: {
        clienteId: "",
        produtos: [],
        total: 0,
      },
      clientesOptions: [],
      produtosOptions: [],
      selectedProduct: "",
      selectedProductDetails: {},
      quantidade: 0,
      showNewClientModal: false,
      newClient: {
        nome: "",
        email: "",
        telefone: "",
      },
      productFields: [
        { key: "nome", label: "Nome" },
        { key: "quantidade", label: "Quantidade" },
        { key: "preco", label: "Preço", formatter: value => (value !== undefined ? `R$ ${value.toFixed(2)}` : '-') },
        { key: "subtotal", label: "Subtotal", formatter: value => (value !== undefined ? `R$ ${value.toFixed(2)}` : '-') },
      ],
    };
  },
  computed: {
    formattedTotal() {
      return this.sale.total.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
    }
  },
  methods: {
    fetchClientes() {
      apiClientClientes.get("/all")
        .then(response => {
          this.clientesOptions = response.data
            .filter(cliente => cliente.active) // Filtra apenas clientes ativos
            .map(cliente => ({
              value: cliente.id,
              text: cliente.nome,
            }));
        })
        .catch(error => {
          console.error("Erro ao buscar clientes:", error);
        });
    },
    fetchProdutos() {
      apiClientProdutos.get("/allprodutos")
        .then(response => {
          this.produtosOptions = response.data.map(produto => ({
            value: produto.idProd,
            text: produto.nomeProd,
          }));
        })
        .catch(error => {
          console.error("Erro ao buscar produtos:", error);
        });
    },
    fetchProductDetails() {
      if (this.selectedProduct) {
        apiClientProdutos.get(`/produto/${this.selectedProduct}`)
          .then(response => {
            this.selectedProductDetails = response.data;
          })
          .catch(error => {
            console.error("Erro ao buscar detalhes do produto:", error);
          });
      }
    },
    addProduct() {
      if (!this.selectedProductDetails || !this.selectedProductDetails.nomeProd) {
        alert("Selecione um produto válido.");
        return;
      }
      const existingProduct = this.sale.produtos.find(product => product.produtoId === this.selectedProduct);
      if (existingProduct) {
        existingProduct.quantidade += parseInt(this.quantidade);
        existingProduct.subtotal = existingProduct.quantidade * existingProduct.preco;
      } else {
        const product = {
          produtoId: this.selectedProduct,
          nome: this.selectedProductDetails.nomeProd,
          quantidade: parseInt(this.quantidade),
          preco: this.selectedProductDetails.valorProd,
          subtotal: parseInt(this.quantidade) * this.selectedProductDetails.valorProd,
        };
        this.sale.produtos.push(product);
      }
      this.updateTotal();
    },
    updateTotal() {
      this.sale.total = this.sale.produtos.reduce((total, product) => total + product.subtotal, 0);
    },
    registerSale() {
      apiClientClientes.post("/vendas", this.sale)
        .then(() => {
          this.$emit("sale-registered");
          this.$bvModal.hide("new-sale-modal");
        })
        .catch(error => {
          console.error("Erro ao registrar venda:", error);
        });
    },
    openNewClientModal() {
      this.showNewClientModal = true;
    },
    registerClient() {
      apiClientClientes.post("/register", this.newClient)
        .then(response => {
          this.clientesOptions.push({
            value: response.data.id,
            text: response.data.nome,
          });
          this.newClient = {
            nome: "",
            email: "",
            telefone: "",
          };
          this.showNewClientModal = false;
        })
        .catch(error => {
          console.error("Erro ao registrar cliente:", error);
        });
    },
  },
  mounted() {
    this.fetchClientes();
    this.fetchProdutos();
  },
};
</script>

<style scoped>
.b-modal {
  max-width: 80% !important;
}
</style>
