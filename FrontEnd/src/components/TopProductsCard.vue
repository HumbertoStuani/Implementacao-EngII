<template>
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Produtos Mais Vendidos</h5>
        <canvas id="topProductsChart"></canvas>
      </div>
    </div>
  </template>
  
  <script>
  import { Chart } from "chart.js";
  
  export default {
    name: "TopProductsCard",
    mounted() {
      this.createChart();
    },
    methods: {
      createChart() {
        const ctx = document.getElementById("topProductsChart").getContext("2d");
        new Chart(ctx, {
          type: "bar",
          data: {
            labels: ["Produto 1", "Produto 2", "Produto 3", "Produto 4"],
            datasets: [
              {
                label: "Vendas",
                data: [50, 100, 150, 200],
                backgroundColor: [
                  "rgba(255, 99, 132, 0.2)",
                  "rgba(54, 162, 235, 0.2)",
                  "rgba(255, 206, 86, 0.2)",
                  "rgba(75, 192, 192, 0.2)",
                ],
                borderColor: [
                  "rgba(255, 99, 132, 1)",
                  "rgba(54, 162, 235, 1)",
                  "rgba(255, 206, 86, 1)",
                  "rgba(75, 192, 192, 1)",
                ],
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
  