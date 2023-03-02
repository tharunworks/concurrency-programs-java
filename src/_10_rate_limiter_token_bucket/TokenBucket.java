package _10_rate_limiter_token_bucket;

public class TokenBucket {
    int bucketSize; // capacity/max burst/burstCapacity
    int refillRate;
    int currentTokens;
    long lastUpdateTime;

    public TokenBucket(int bucketSize, int refillRate) {
        this.bucketSize = bucketSize;
        this.refillRate = refillRate;
        this.currentTokens = bucketSize;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public synchronized boolean getAccess() {
        long currentTime = System.currentTimeMillis();
//        note: first calculate the no.of time-units elapsed and then multiple it with refill rate
        int tokensToBeAdded = (int) (((currentTime - this.lastUpdateTime)  / 1000) * this.refillRate);
        currentTokens += tokensToBeAdded;
        currentTokens = Math.min(currentTokens, this.bucketSize);
        if (currentTokens > 0) {
            currentTokens--;
            return true;
        } else {
            return false;
        }
    }
}
