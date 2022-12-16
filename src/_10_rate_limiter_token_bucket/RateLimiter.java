package _10_rate_limiter_token_bucket;

public interface RateLimiter {

    boolean isAllowed(Integer userId);
}
