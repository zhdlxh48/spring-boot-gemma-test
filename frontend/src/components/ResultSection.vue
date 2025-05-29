<script setup lang="ts">

defineProps<{
  summary?: string;
}>();
</script>

<template>
  <div v-if="summary" class="result-section">
    <h2>Summary:</h2>
    <div class="summary-box">
      {{ summary }}
    </div>
  </div>
</template>

<style scoped>
.result-section {
  margin-top: 30px;
  animation: fadeIn 0.3s ease-in-out;
}

h2 {
  color: var(--primary-color);
  margin: 0 0 10px 0;
  font-size: 1.5rem;
}

.summary-box {
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 4px;
  border-left: 4px solid var(--primary-color);
  white-space: pre-wrap;
  line-height: 1.6;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
