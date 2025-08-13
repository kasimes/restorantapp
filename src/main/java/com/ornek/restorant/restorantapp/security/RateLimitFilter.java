package com.ornek.restorant.restorantapp.security;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
@Component

public class RateLimitFilter extends OncePerRequestFilter {
    //istek limiti kontrolu bucket ile yapılır
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    //Bucket olusturma
    private Bucket createNEwBucket() {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofSeconds(10)));
        return Bucket4j.builder().addLimit(limit).build();

    };
    //filtreleme burda yapılıyor
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //ip adresi allınıyor
        String ip = request.getRemoteAddr();
        //bucket yoksa olusturuluyor
        Bucket bucket = cache.computeIfAbsent(ip, k -> createNEwBucket());

        //token tüketme
        if (bucket.tryConsume(1)){
            filterChain.doFilter(request, response);
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().println("Çok fazla istek .lütfen biraz bekleyin ");
        }

    }
}
