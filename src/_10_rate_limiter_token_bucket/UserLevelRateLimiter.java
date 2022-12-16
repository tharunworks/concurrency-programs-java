package _10_rate_limiter_token_bucket;

import java.util.HashMap;
import java.util.Map;

public class UserLevelRateLimiter {

    Map<Integer, TokenBucket> userTokenBuckets;

    public UserLevelRateLimiter(Integer nUsers, Integer bucketSize, Integer refillRate) {
        this.userTokenBuckets = new HashMap<>();
        for(int i = 1; i <= nUsers; i++){
            this.userTokenBuckets.put(i, new TokenBucket(bucketSize, refillRate));
        }
    }

    boolean isAllowed(Integer userId){
        if(userTokenBuckets.get(userId).getAccess()){
            System.out.println(Thread.currentThread().getName() +":: Got access for userId: " + userId);
            return true;
        } else{
            System.out.println(Thread.currentThread().getName() +":: No access for userId: " + userId);
            return false;
        }
    }
}
