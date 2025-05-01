package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.response.LabseqSequenceResponse;

import java.math.BigInteger;

@ApplicationScoped
public class LabseqSequenceService {

    @Inject
    CacheService cacheService;


    public LabseqSequenceResponse getLabseqSequence(Integer number) {
        long startTime = System.currentTimeMillis();
        long result = calculateLabseqInteractive(number);
        long elapsed = System.currentTimeMillis() - startTime;
        return new LabseqSequenceResponse(result, elapsed);
    }

    private in calculateLabseqInteractive(Integer n){
            if (n == 0 || n == 2) return 0;
            if (n == 1 || n == 3) return 1;
        long[] dp = new long[n + 1];
            dp[0] = 0L; dp[1] = 1L; dp[2] = 0L; dp[3] = 1L;
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 4] + dp[i - 3];
            }
            return dp[n];
    };

    private BigInteger calculateLabseqRecursiveWithCache(Integer n){
        if (n == 0 || n == 2) return BigInteger.ZERO;
        if (n == 1 || n == 3) return BigInteger.ONE;
        String key = "labseq:" + n;
        String cachedValue = cacheService.getReactive(key).await().indefinitely();
        if (cachedValue != null) return new BigInteger(cachedValue);
        BigInteger result = calculateLabseqRecursiveWithCache(n - 4).add(calculateLabseqRecursiveWithCache(n - 3));
        cacheService.setReactive(key, String.valueOf(result)).await().indefinitely();
        return result;
    };
}
