<script setup lang="ts">

defineProps<{
  message?: string;
}>();
</script>

<template>
  <div v-if="message" class="error-message">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="error-icon">
      <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z"/>
    </svg>
    <span>{{ message }}</span>
  </div>
</template>

<style scoped>
.error-message {
  margin: 15px 0;
  padding: 12px 16px;
  background-color: #ffebee;
  color: #d32f2f;
  border-radius: 4px;
  border-left: 4px solid #d32f2f;
  display: flex;
  align-items: center;
  gap: 8px;
  animation: slideIn 0.3s ease-out;
}

.error-icon {
  width: 20px;
  height: 20px;
  flex-shrink: 0;
}

@keyframes slideIn {
  from {
    opacity: 0;
    transform: translateX(-10px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}
</style>
