import axios from 'axios';
import type { AxiosError, AxiosResponse } from 'axios';
import type { SummaryRequest, SummaryResponse, ApiError } from '../types/api';
import { sanitizeText } from '../utils/textUtils';

const API_BASE_URL = 'http://localhost:8080/api/v1';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const summarizeText = async (request: SummaryRequest): Promise<SummaryResponse> => {
  try {
    // Sanitize the text before sending
    const sanitizedRequest = {
      ...request,
      text: sanitizeText(request.text)
    };
    
    const response: AxiosResponse<SummaryResponse> = await api.post('/gemini/summarize', sanitizedRequest);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      const axiosError = error as AxiosError<{ error?: string }>;
      const apiError: ApiError = {
        message: axiosError.response?.data?.error || 'An unexpected error occurred',
        status: axiosError.response?.status,
        data: axiosError.response?.data,
      };
      throw apiError;
    }
    throw { message: 'An unexpected error occurred' };
  }
};

export default api;
