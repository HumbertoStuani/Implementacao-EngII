<template>
    <div class="container-fluid mt-4">
      <!-- Título e Botão de Nova Venda -->
      <div class="row align-items-center mb-4">
        <div class="col-lg-6">
          <h2>Vendas</h2>
        </div>
        <div class="col-lg-6 text-right">
          <b-button @click="showNewSaleModal" variant="primary">Registrar Nova Venda</b-button>
        </div>
      </div>
  
      <!-- Linha com Visão Geral das Vendas e os outros três cards empilhados -->
      <div class="row">
        <div class="col-xl-6">
          <sales-overview />
        </div>
        <div class="col-xl-6">
          <div class="row">
            <div class="col-md-12">
              <sales-info-card icon="text-white fas fa-cash-register" title="Total Vendas"
                description="Último Mês" value="R$ 10,000" />
            </div>
            <div class="col-md-12">
              <sales-info-card icon="text-white fas fa-shopping-cart" title="Produtos Vendidos"
                description="Último Mês" value="200" />
            </div>
          </div>
        </div>
      </div>
  
      <!-- Outros Cards e Informações -->
      <div class="row">
        <div class="col-md-12 mb-4">
          <top-products-card />
        </div>
      </div>
  
      <div class="row mt-4">
        <div class="col-md-7">
          <billing-card title="Informações de Cobrança" :bills="[
            {
              name: 'Cliente 1',
              company: 'Empresa 1',
              email: 'cliente1@empresa1.com',
              id: '123456',
            },
            {
              name: 'Cliente 2',
              company: 'Empresa 2',
              email: 'cliente2@empresa2.com',
              id: '123457',
            },
            {
              name: 'Cliente 3',
              company: 'Empresa 3',
              email: 'cliente3@empresa3.com',
              id: '123458',
            },
          ]" />
        </div>
        <div class="col-md-5 mt-4">
          <ranking-list-card :horizontal-break="false" :card="{
            title: 'Transações',
            date: 'Última Semana',
            subtitle: 'Mais Recentes',
          }" :item="[
            {
              title: 'Venda 1',
              date: 'Ontem, às 14:30',
              amount: '- R$ 2,500',
              icon: 'fa-arrow-down',
              color: 'danger',
            },
            {
              title: 'Venda 2',
              date: 'Hoje, às 08:00',
              amount: '+ R$ 3,000',
              icon: 'fa-arrow-up',
              color: 'success',
            },
          ]">
            <ranking-list title="Ontem" :item="[
              {
                title: 'Venda 3',
                date: 'Ontem, às 15:00',
                amount: '+ R$ 750',
                icon: 'fa-arrow-up',
                color: 'success',
              },
              {
                title: 'Venda 4',
                date: 'Ontem, às 12:30',
                amount: '+ R$ 1,000',
                icon: 'fa-arrow-up',
                color: 'success',
              },
            ]" :horizontal-break="false" />
          </ranking-list-card>
        </div>
      </div>
  
      <!-- Modal de Nova Venda -->
      <b-modal v-model="isNewSaleModalVisible" title="Registrar Nova Venda" hide-footer size="lg">
        <NewSale @sale-registered="handleSaleRegistered" />
      </b-modal>
    </div>
  </template>
  
  <script>
  import setTooltip from "@/assets/js/tooltip.js";
  import SalesOverview from "@/components/SalesOverview.vue";
  import SalesInfoCard from "@/components/SalesInfoCard.vue";
  import TopProductsCard from "@/components/TopProductsCard.vue";
  import BillingCard from "@/components/BillingCard.vue";
  import RankingList from "@/examples/Cards/RankingList.vue";
  import RankingListCard from "@/examples/Cards/RankingListCard.vue";
  import NewSale from "@/components/NewSale.vue";
  
  export default {
    name: "Sales",
    components: {
      SalesOverview,
      SalesInfoCard,
      TopProductsCard,
      BillingCard,
      RankingList,
      RankingListCard,
      NewSale,
    },
    data() {
      return {
        isNewSaleModalVisible: false,
      };
    },
    methods: {
      showNewSaleModal() {
        this.isNewSaleModalVisible = true;
      },
      handleSaleRegistered() {
        this.isNewSaleModalVisible = false;
        // Atualize a lista de vendas ou execute qualquer ação necessária após registrar uma nova venda
      },
    },
    mounted() {
      setTooltip();
    },
  };
  </script>
  
  <style scoped>
  .container {
    padding: 20px;
  }
  
  .text-right {
    text-align: right;
  }
  
  .mb-4,
  .row {
    margin-bottom: 1.5rem !important;
    margin-right: 60px !important;
    margin-left: 20px !important;
  }
  </style>
  