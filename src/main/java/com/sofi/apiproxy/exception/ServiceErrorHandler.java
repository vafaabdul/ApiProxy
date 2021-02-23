package com.sofi.apiproxy.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ServiceErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series. CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }
    @Override
    @SneakyThrows
    public void handleError(ClientHttpResponse response)  {
        if(response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()){
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()));
            String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
            throw new DownStreamException(httpBodyResponse,response.getStatusCode());
        }
    }
}
