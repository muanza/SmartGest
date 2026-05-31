package com.smartgest.config;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterEncodingFilterTest {

    @Test
    void shouldForceUtf8EncodingForRequestAndResponse() throws IOException, ServletException {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        AtomicReference<String> requestEncoding = new AtomicReference<>();
        AtomicReference<String> responseEncoding = new AtomicReference<>();
        ServletRequest request = createProxy(ServletRequest.class, requestEncoding);
        ServletResponse response = createProxy(ServletResponse.class, responseEncoding);
        CapturingFilterChain chain = new CapturingFilterChain();

        filter.doFilter(request, response, chain);

        Assertions.assertEquals("UTF-8", requestEncoding.get());
        Assertions.assertEquals("UTF-8", responseEncoding.get());
        Assertions.assertSame(request, chain.request);
        Assertions.assertSame(response, chain.response);
    }

    private <T> T createProxy(Class<T> type, AtomicReference<String> encoding) {
        return type.cast(Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                (proxy, method, args) -> {
                    if ("setCharacterEncoding".equals(method.getName())) {
                        encoding.set((String) args[0]);
                        return null;
                    }
                    if ("getCharacterEncoding".equals(method.getName())) {
                        return encoding.get();
                    }
                    return defaultValue(method.getReturnType());
                }));
    }

    private Object defaultValue(Class<?> returnType) {
        if (!returnType.isPrimitive()) {
            return null;
        }
        if (boolean.class.equals(returnType)) {
            return false;
        }
        if (char.class.equals(returnType)) {
            return '\0';
        }
        if (byte.class.equals(returnType)) {
            return (byte) 0;
        }
        if (short.class.equals(returnType)) {
            return (short) 0;
        }
        if (int.class.equals(returnType)) {
            return 0;
        }
        if (long.class.equals(returnType)) {
            return 0L;
        }
        if (float.class.equals(returnType)) {
            return 0F;
        }
        if (double.class.equals(returnType)) {
            return 0D;
        }
        return null;
    }

    private static final class CapturingFilterChain implements FilterChain {

        private ServletRequest request;
        private ServletResponse response;

        @Override
        public void doFilter(ServletRequest request, ServletResponse response) {
            this.request = request;
            this.response = response;
        }
    }
}
