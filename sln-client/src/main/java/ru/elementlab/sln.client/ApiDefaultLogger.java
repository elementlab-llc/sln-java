/*
 *
 * Module Name:  sln-client
 * Project:      sln
 *
 * Copyright (c) Element Lab LLC
 *
 *  THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 *  EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 */

package ru.elementlab.sln.client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ApiDefaultLogger implements ClientRequestFilter, ClientResponseFilter, WriterInterceptor {

    private static final Logger logger = Logger.getLogger(ApiDefaultLogger.class.getName());
    private static final String ENTITY_STREAM_PROPERTY = "ApiDefaultLogger.entityStream";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private void log(StringBuilder sb) {
        logger.info(sb.toString());
    }

    private InputStream logInboundEntity(final StringBuilder b, InputStream stream, final Charset charset) throws IOException {
        if (!stream.markSupported()) {
            stream = new BufferedInputStream(stream);
        }
        stream.mark(stream.available() + 1);
        final byte[] entity = new byte[stream.available() + 1];
        final int entitySize = stream.read(entity);
        b.append(new String(entity, 0, entitySize, charset));
        stream.reset();
        return stream;
    }

    @Override
    public void filter(ClientRequestContext requestContext) {
        if (requestContext.hasEntity()) {
            final OutputStream stream = new LoggingStream(requestContext.getEntityStream());
            requestContext.setEntityStream(stream);
            requestContext.setProperty(ENTITY_STREAM_PROPERTY, stream);
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext,
                       ClientResponseContext responseContext) throws IOException {
        final StringBuilder sb = new StringBuilder();
        if (responseContext.hasEntity()) {
            responseContext.setEntityStream(logInboundEntity(sb, responseContext.getEntityStream(),
                    DEFAULT_CHARSET));
            log(sb);
        }

    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {
        final LoggingStream stream = (LoggingStream) context.getProperty(ENTITY_STREAM_PROPERTY);
        context.proceed();
        if (stream != null) {
            log(stream.getStringBuilder(DEFAULT_CHARSET));
        }
    }

    private class LoggingStream extends FilterOutputStream {

        private final StringBuilder sb = new StringBuilder();
        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        LoggingStream(OutputStream out) {
            super(out);
        }

        StringBuilder getStringBuilder(Charset charset) {
            final byte[] entity = baos.toByteArray();
            sb.append(new String(entity, 0, entity.length, charset));
            sb.append('\n');
            return sb;
        }

        @Override
        public void write(final int i) throws IOException {
            baos.write(i);
            out.write(i);
        }
    }
}