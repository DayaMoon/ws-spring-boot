package com.mtt.airline.accessors;

import org.apache.log4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class RestTemplateErrorHandler implements ResponseErrorHandler
{

    private static final Logger LOG = Logger.getLogger(RestTemplateErrorHandler.class);

    private final ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(final ClientHttpResponse response) throws IOException
    {
        return errorHandler.hasError(response);
    }

    @Override
    public void handleError(final ClientHttpResponse response) throws IOException
    {

        final RestClientException exception = new RestClientException(response.getStatusCode().getReasonPhrase());
        LOG.error("HTTP status recieved: " + response.getRawStatusCode());
        LOG.error("HTTP reason phrase: " + response.getStatusCode().getReasonPhrase());
        throw exception;
    }
}