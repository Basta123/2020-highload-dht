package ru.mail.polis.service.asta1234;

import com.google.common.net.HttpHeaders;
import one.nio.http.HttpServer;
import one.nio.http.HttpServerConfig;
import one.nio.http.Param;
import one.nio.http.Path;
import one.nio.http.Request;
import one.nio.http.RequestMethod;
import one.nio.http.Response;
import ru.mail.polis.dao.DAO;
import ru.mail.polis.service.Service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.mail.polis.service.asta1234.Utils.getByteArrayFromByteBuffer;
import static ru.mail.polis.service.asta1234.Utils.getByteBufferFromByteArray;

public class MyHttpServerImpl extends HttpServer implements Service {

    private final DAO dao;

    public MyHttpServerImpl(final HttpServerConfig config, final DAO dao) throws IOException {
        super(config);
        this.dao = dao;
    }

    /**
     * Checking status.
     *
     * @return - return code 200 OK
     */
    @Path("/abracadabra")
    @RequestMethod(Request.METHOD_GET)
    public Response abracadabraCheckMethod() {
        return new Response(Response.BAD_REQUEST, Response.EMPTY);
    }

    @Path("/v0/status")
    @RequestMethod(Request.METHOD_GET)
    public Response statusCheckMethod() {
        return new Response(Response.OK, new byte[0]);
    }

    @Path("/v0/entity")
    @RequestMethod(Request.METHOD_GET)
    public Response getValueByKey(final @Param("id") String id) {
        if (id == null || "".equals(id)) {
            return new Response(Response.BAD_REQUEST, Response.EMPTY);
        }

        final byte[] keyBytes = id.getBytes(UTF_8);
        final ByteBuffer keyByteBuffer = getByteBufferFromByteArray(keyBytes);

        ByteBuffer valueByteBuffer;
        try {
            valueByteBuffer = dao.get(keyByteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting value :", e);

        } catch (NoSuchElementException e) {
            return new Response(Response.NOT_FOUND, Response.EMPTY);
        }

        final byte[] valueBytes = getByteArrayFromByteBuffer(valueByteBuffer);
        final Response responseOk = new Response(Response.OK, valueBytes);
        responseOk.addHeader(HttpHeaders.CONTENT_TYPE + ": " + "text/plain");
        return responseOk;
    }

    @Path("/v0/entity")
    @RequestMethod(Request.METHOD_PUT)
    public Response putValueByKey(final @Param("id") String id, final Request request) throws IOException {
        if ("".equals(id)) {
            return new Response(Response.BAD_REQUEST, Response.EMPTY);
        }

        final byte[] keyBytes = id.getBytes(UTF_8);
        final ByteBuffer keyByteBuffer = getByteBufferFromByteArray(keyBytes);

        final byte[] valueByte = request.getBody();
        final ByteBuffer valueByteBuffer = getByteBufferFromByteArray(valueByte);

        dao.upsert(keyByteBuffer, valueByteBuffer);

        final Response responseCreated = new Response(Response.CREATED, Response.EMPTY);
        responseCreated.addHeader(HttpHeaders.CONTENT_TYPE + ": " + "text/plain");
        return responseCreated;
    }

    @Path("/v0/entity")
    @RequestMethod(Request.METHOD_DELETE)
    public Response deleteValueByKey(final @Param("id") String id) throws IOException {
        if ("".equals(id)) {
            return new Response(Response.BAD_REQUEST, Response.EMPTY);
        }
        final byte[] keyBytes = id.getBytes(UTF_8);
        final ByteBuffer keyByteBuffer = getByteBufferFromByteArray(keyBytes);

        dao.remove(keyByteBuffer);
        return new Response(Response.ACCEPTED, Response.EMPTY);

    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public synchronized void stop() {
        super.stop();
    }
}