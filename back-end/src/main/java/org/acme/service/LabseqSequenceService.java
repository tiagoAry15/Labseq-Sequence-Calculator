package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.response.LabseqSequenceResponseDTO;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class LabseqSequenceService {

    @Inject
    CacheService cacheService;

    private static final ConcurrentHashMap<Integer, String> cache = new ConcurrentHashMap<>();


    public LabseqSequenceResponseDTO getLabseqSequence(Integer number) {
        return new LabseqSequenceResponseDTO(calculateLabseqInteractive(number));
    }

    public String calculateLabseqWithCache(int n) {
        String cachedResult = cache.get(n);
        if (cachedResult != null) return cachedResult;

        if (n == 0 || n == 2) {
            String result = BigInteger.ZERO.toString();
            cache.put(n, result);
            return result;
        }
        if (n == 1 || n == 3) {
            String result = BigInteger.ONE.toString();
            cache.put(n, result);
            return result;
        }
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ONE;
        dp[2] = BigInteger.ZERO;
        dp[3] = BigInteger.ONE;

        for (int i = 4; i <= n; i++) {
            if(cache.get(i) != null) dp[i] = new BigInteger(cache.get(i));
            else {
                dp[i] = dp[i - 4].add(dp[i - 3]);
                cache.put(i, dp[i].toString());
            }
        }

        String result = dp[n].toString();
        cache.put(n, result);
        return result;
    }

    public String calculateLabseqInteractive(int n) {
        if (n == 0 || n == 2) return BigInteger.ZERO.toString();
        if (n == 1 || n == 3) return  BigInteger.ONE.toString();

        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ONE;
        dp[2] = BigInteger.ZERO;
        dp[3] = BigInteger.ONE;
        for (int i = 4; i <= n; i++) dp[i] = dp[i - 4].add(dp[i - 3]);
        return dp[n].toString();
    }


}
