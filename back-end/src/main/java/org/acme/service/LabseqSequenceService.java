package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.response.LabseqSequenceResponse;

@ApplicationScoped
public class LabseqSequenceService {

    @Inject
    CacheService cacheService;


    public LabseqSequenceResponse getLabseqSequence(Integer number) {
        long startTime = System.currentTimeMillis();
        int result = calculateLabseqRecursiveWithCache(number);
        long elapsed = System.currentTimeMillis() - startTime;
        return new LabseqSequenceResponse(result, null);
    }
    private int calculateLabseqInteractive(Integer n){
            int[] dp = new int[n + 1];
            dp[0] = 0; dp[1] = 1; dp[2] = 0; dp[3] = 1;
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 4] + dp[i - 3];
            }
            return dp[n];
    };

    private int calculateLabseqRecursiveWithCache(Integer n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 0;
        if (n == 3) return 1;
        String key = "labseq:" + n;
        String cachedValue = cacheService.getReactive(key).await().indefinitely();
        if (cachedValue != null) return Integer.parseInt(cachedValue);
        int result = calculateLabseqRecursiveWithCache(n - 4) + calculateLabseqRecursiveWithCache(n - 3);
        cacheService.setReactive(key, String.valueOf(result)).await().indefinitely();
        return result;
    };
}
