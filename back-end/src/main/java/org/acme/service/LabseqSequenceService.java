package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.response.LabseqSequenceResponseDTO;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class LabseqSequenceService {

    @Inject
    CacheService cacheService;

    private final ConcurrentHashMap<Integer, BigInteger> cache = new ConcurrentHashMap<>(Map.of(0,BigInteger.ZERO, 1, BigInteger.ONE, 2, BigInteger.ZERO, 3, BigInteger.ONE));

    public LabseqSequenceResponseDTO getLabseqSequence(Integer number) {
        return new LabseqSequenceResponseDTO(calculateLabseqIterativeWithCache(number));
    }


    public String calculateLabseqIterativeWithCache(int n) {
        if (cache.containsKey(n)) return cache.get(n).toString();
        int begin = Math.max(4, getBiggestKeyValue());
        for (int i = begin; i <= n; i++) cache.put(i, cache.get(i - 4).add(cache.get(i - 3)));
        return cache.get(n).toString();
    }

    private int getBiggestKeyValue() {
        return cache.keySet().stream()
                .max(Integer::compare)
                .orElse(4);
    }


}
