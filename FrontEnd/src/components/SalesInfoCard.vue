<template>
    <div class="card">
      <div class="card-body">
        <div class="d-flex align-items-center">
          <div class="icon-shape icon-sm bg-gradient-primary shadow-primary text-center rounded-circle">
            <i :class="icon"></i>
          </div>
          <div class="ms-3">
            <h5 class="font-weight-bold mb-0">{{ title }}</h5>
            <p class="text-sm mb-0">{{ description }}</p>
            <span class="h5 font-weight-bold">{{ value }}</span>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "SalesInfoCard",
    props: {
      icon: {
        type: String,
        required: true,
      },
      title: {
        type: String,
        required: true,
      },
      description: {
        type: String,
        required: true,
      },
      value: {
        type: String,
        required: true,
      },
    },
  };
  </script>
  
  <style scoped>
  .card {
    margin-bottom: 1rem;
  }
  .icon-shape {
    width: 48px;
    height: 48px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
  }
  </style>
  