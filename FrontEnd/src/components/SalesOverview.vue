<template>
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Visão Geral das Vendas</h5>
        <canvas id="salesOverviewChart"></canvas>
      </div>
    </div>
  </template>
  
  <script>
  import { Chart } from "chart.js";
  
  export default {
    name: "SalesOverview",
    mounted() {
      this.createChart();
    },
    methods: {
      createChart() {
        const ctx = document.getElementById("salesOverviewChart").getContext("2d");
        new Chart(ctx, {
          type: "line",
          data: {
            labels: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho"],
            datasets: [
              {
                label: "Vendas",
                data: [10, 20, 30, 40, 50, 60],
                borderColor: "rgba(75, 192, 192, 1)",
                backgroundColor: "rgba(75, 192, 192, 0.2)",
                borderWidth: 1,
              },
            ],
          },
          options: {
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          },
        });
      },
    },
  };
  </script>
  
  <style scoped>
  .card {
    margin-bottom: 1rem;
  }
  </style>
  