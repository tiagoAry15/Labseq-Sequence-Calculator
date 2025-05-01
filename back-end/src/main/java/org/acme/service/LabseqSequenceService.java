package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.response.LabseqSequenceResponseDTO;

import java.math.BigInteger;

@ApplicationScoped
public class LabseqSequenceService {

    @Inject
    CacheService cacheService;


    public LabseqSequenceResponseDTO getLabseqSequence(Integer number) {
        return new LabseqSequenceResponseDTO(calculateLabseqInteractive(number));
    }

    private int calculateLabseqInteractive(Integer n){
            if (n == 0 || n == 2) return 0;
            if (n == 1 || n == 3) return 1;
        int[] dp = new int[n + 1];
            dp[0] = 0; dp[1] = 1; dp[2] = 0; dp[3] = 1;
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
