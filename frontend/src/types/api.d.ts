export interface SummaryRequest {
  text: string;
}

export interface SummaryResponse {
  summary?: string;
  error?: string;
}

export interface ApiError {
  message: string;
  status?: number;
  data?: any;
}
