package com.example.cash_ratio_analyzer_test.application.service;

import com.example.cash_ratio_analyzer_test.application.service.enums.FetchDocumentType;
import com.example.cash_ratio_analyzer_test.application.service.validation.ApiResponseValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EdinetDataFetchService {

    /** 書類取得API */
    @Value("${edinet.api.document.retrieval.url:}")
    private String edinetDocumentRetrievalApiUrl;

    @Value("${edinet.api.subscriptionKey:}")
    private String subscriptionKey;

    private final ApiResponseValidator apiResponseValidator;

    public EdinetDataFetchService(ApiResponseValidator apiResponseValidator) {
        this.apiResponseValidator = apiResponseValidator;
    }

    /**
     * 書類取得APIからデータを取得します。
     *
     * @param type 取得する書類の種類
     * @param documentId 取得する書類の書類番号
     * @return 取得したデータ
     */
    public byte[] fetchFinancialData(FetchDocumentType type, String documentId) {
        var restTemplate = new RestTemplate();
        var response = restTemplate.exchange(
                edinetDocumentRetrievalApiUrl, HttpMethod.GET, null,
                byte[].class, documentId, type.code(), subscriptionKey);

        apiResponseValidator.validateStatusCode(response.getStatusCode());
        apiResponseValidator.validateContentType(response.getHeaders().getContentType(),
                MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_PDF);
        apiResponseValidator.validateResponseBody(response.getBody());

        return response.getBody();
    }
}
