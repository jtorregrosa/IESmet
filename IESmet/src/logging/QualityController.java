package logging;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QualityController {
    private ExecutorService m_executor;
    public final int DEFAULT_THREADPOOL_SIZE = 6;
    
    public QualityController(){
        m_executor = Executors.newFixedThreadPool(DEFAULT_THREADPOOL_SIZE);
    }
    
    public QualityController(int nThreads){
        m_executor = Executors.newFixedThreadPool(nThreads);
    }
    
    public void checkFile(String fileName){
        
    }
}
