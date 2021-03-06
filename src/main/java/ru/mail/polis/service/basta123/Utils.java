package ru.mail.polis.service.basta123;

import one.nio.http.HttpServerConfig;
import one.nio.server.AcceptorConfig;
import java.nio.ByteBuffer;

public final class Utils {

    private Utils() {
    }

    public static ByteBuffer getByteBufferFromByteArray(final byte[] bytes) {
        return ByteBuffer.wrap(bytes);
    }

    /**
     * ByteArrayFromByteBuffer.
     *
     * @param buffer input buffer
     * @return bytes array
     */
    public static byte[] getByteArrayFromByteBuffer(final ByteBuffer buffer) {
        final byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        buffer.clear();
        return bytes;
    }

    /**
     * config server setup.
     *
     * @param port server port
     * @return serverConfig
     */
    public static HttpServerConfig getHttpServerConfig(final int port) {
        final AcceptorConfig acceptorConfig = new AcceptorConfig();
        acceptorConfig.port = port;
        final HttpServerConfig httpServerConfig = new HttpServerConfig();
        httpServerConfig.acceptors = new AcceptorConfig[1];
        httpServerConfig.acceptors[0] = acceptorConfig;
        acceptorConfig.deferAccept = true;
        acceptorConfig.reusePort = true;
        httpServerConfig.maxWorkers = Runtime.getRuntime().availableProcessors();
        return httpServerConfig;
    }

}
