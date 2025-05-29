<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps<{
  modelValue: string;
  disabled?: boolean;
  placeholder?: string;
  label?: string;
  rows?: number | string;
  id?: string;
}>();

const emit = defineEmits<{
  (e: 'update:modelValue', value: string): void;
}>();

const value = computed({
  get: () => props.modelValue,
  set: (value: string) => emit('update:modelValue', value)
});
</script>

<template>
  <div class="input-section">
    <label v-if="label" :for="id">{{ label }}</label>
    <textarea
      :id="id"
      v-model="value"
      :disabled="disabled"
      :placeholder="placeholder"
      :rows="rows"
      class="input-field"
    ></textarea>
  </div>
</template>

<style scoped>
.input-section {
  margin-bottom: 20px;
  width: 100%;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: var(--text-color);
}

.input-field {
  width: 100%;
  padding: 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  font-family: inherit;
  font-size: 16px;
  resize: vertical;
  min-height: 150px;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.input-field:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 0 2px rgba(66, 184, 131, 0.2);
}

.input-field:disabled {
  background-color: #f5f5f5;
  cursor: not-allowed;
  opacity: 0.7;
}
</style>
