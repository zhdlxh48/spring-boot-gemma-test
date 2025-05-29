/**
 * Sanitizes text by normalizing line endings and removing control characters
 * @param text Input text to sanitize
 * @returns Sanitized text with normalized line endings
 */
export const sanitizeText = (text: string): string => {
  if (!text) return '';
  
  // Normalize line endings to LF (\n)
  return text
    .replace(/\r\n|\r/g, '\n')  // Convert CRLF or CR to LF
    .replace(/[\u0000-\u001F\u007F-\u009F]/g, '') // Remove control characters
    .trim();
};

/**
 * Prepares text for JSON serialization
 * @param text Input text to prepare
 * @returns Text ready for JSON stringification
 */
export const prepareTextForJson = (text: string): string => {
  return sanitizeText(text)
    .replace(/\\/g, '\\\\') // Escape backslashes
    .replace(/\n/g, '\\n')     // Escape newlines
    .replace(/\t/g, '\\t')     // Escape tabs
    .replace(/\r/g, '\\r');    // Escape carriage returns
};
