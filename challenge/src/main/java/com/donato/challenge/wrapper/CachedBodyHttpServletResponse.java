//package com.donato.challenge.wrapper;
//
//
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpServletResponseWrapper;
//import org.springframework.util.StreamUtils;
//
//import java.io.*;
//
//public class CachedBodyHttpServletResponse extends HttpServletResponseWrapper {
//    /**
//     * Constructs a response adaptor wrapping the given response.
//     *
//     * @param response The response to be wrapped
//     * @throws IllegalArgumentException if the response is null
//     */
//    public CachedBodyHttpServletResponse(HttpServletResponse response) {
//        super(response);
//        InputStream requestInputStream = response.getInputStream();
//        this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);
//    }
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        return new CachedBodyServletInputStream(this.cachedBody);
//    }
//    @Override
//    public BufferedReader getReader() throws IOException {
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
//        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
//    }
//}
