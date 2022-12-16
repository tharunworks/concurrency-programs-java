package _10_rate_limiter_token_bucket;

public class TokenBucket {
    int bucketSize;
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
        int tokensToBeAdded = (int) (((currentTime - this.lastUpdateTime) * this.refillRate) / 1000);
        if (tokensToBeAdded > 0) {
//            (tokensToBeAdded > 0) condition is important. this makes sure we update only if we were able to add new tokens. or else,
//            we may never ever add tokens to bucket due to truncation  but ending up updating only last updated time.
            currentTokens += tokensToBeAdded;
        }
        if (currentTokens > 0) {
            currentTokens--;
            return true;
        } else {
            return false;
        }
    }
}
