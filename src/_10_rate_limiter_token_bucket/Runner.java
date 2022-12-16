package _10_rate_limiter_token_bucket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    void init(){
        int nThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

//        Testcase 1:
//        When: make bucket size to greater than nThreads.
//        Then: all threads will get access

//        Testcase 2:
//        When: make bucket size less than nThreads.
//        Then: not all threads will get access

//        TODO: low-level design can be done better
        UserLevelRateLimiter userLevelRateLimiter = new UserLevelRateLimiter(1, 5, 1);
        for(int i = 0; i < nThreads; i++){
//            note: got confused on using below syntax.
            executorService.execute(() -> {
                userLevelRateLimiter.isAllowed(1);
            });
        }
        executorService.shutdown();
    }
}
