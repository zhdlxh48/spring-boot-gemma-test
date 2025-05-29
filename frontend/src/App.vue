<script setup lang="ts">
import { ref } from 'vue';
import { summarizeText } from './services/api';
import type { SummaryResponse, ApiError } from './types/api';
import TextInput from './components/TextInput.vue';
import ErrorMessage from './components/ErrorMessage.vue';
import ResultSection from './components/ResultSection.vue';

const inputText = ref<string>('');
const summary = ref<string>('');
const isLoading = ref<boolean>(false);
const error = ref<string>('');

const summarize = async (): Promise<void> => {
  const textToSummarize = inputText.value.trim();
  
  if (!textToSummarize) {
    error.value = 'Please enter some text to summarize';
    return;
  }

  isLoading.value = true;
  error.value = '';
  
  try {
    const response: SummaryResponse = await summarizeText({ text: textToSummarize });
    summary.value = response.summary || response.error || 'No summary available';
  } catch (err) {
    const apiError = err as ApiError;
    console.error('API Error:', apiError);
    error.value = apiError?.message || 'Failed to get summary. Please try again.';
  } finally {
    isLoading.value = false;
  }
};
</script>

<template>
  <div class="container">
    <h1>Text Summarizer</h1>
    
    <TextInput
      v-model="inputText"
      label="Enter text to summarize:"
      placeholder="Paste your text here..."
      :disabled="isLoading"
      id="input-text"
      :rows="10"
    />
    
    <button 
      @click="summarize"
      :disabled="isLoading || !inputText.trim()"
      class="submit-btn"
    >
      {{ isLoading ? 'Summarizing...' : 'Summarize Text' }}
    </button>
    
    <ErrorMessage 
      v-if="error" 
      :message="error" 
    />
    
    <ResultSection 
      v-if="summary" 
      :summary="summary" 
    />
  </div>
</template>

<style>
:root {
  --primary-color: #42b883;
  --primary-hover: #359268;
  --error-color: #ff4444;
  --border-color: #ddd;
  --text-color: #333;
  --bg-color: #f9f9f9;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  line-height: 1.6;
  color: var(--text-color);
  background-color: var(--bg-color);
  padding: 20px;
}

.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: var(--primary-color);
  margin-bottom: 24px;
}

h2 {
  color: var(--primary-color);
  margin: 20px 0 10px;
}

.input-section {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
}

textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  font-family: inherit;
  font-size: 16px;
  resize: vertical;
  min-height: 150px;
}

.submit-btn {
  display: block;
  width: 100%;
  padding: 12px;
  background-color: var(--primary-color);
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-btn:hover:not(:disabled) {
  background-color: var(--primary-hover);
}

.submit-btn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.error-message {
  margin-top: 15px;
  padding: 10px;
  background-color: #ffebee;
  color: var(--error-color);
  border-radius: 4px;
  border-left: 4px solid var(--error-color);
}

.result-section {
  margin-top: 30px;
}

.summary-box {
  background-color: #f5f5f5;
  padding: 15px;
  border-radius: 4px;
  border-left: 4px solid var(--primary-color);
  white-space: pre-wrap;
  line-height: 1.6;
}

@media (max-width: 600px) {
  .container {
    padding: 15px;
  }
  
  textarea {
    min-height: 120px;
  }
}
</style>
