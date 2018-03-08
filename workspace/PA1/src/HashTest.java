import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/** 
 * Class to test functionality of miscellaneous hash functions
 */
public class HashTest {

	public static void main(String[] args) throws IOException {
		// files
        String shak1 = new String(Files.readAllBytes(Paths.get("shak1")));
        String shak2 = new String(Files.readAllBytes(Paths.get("shak2")));
        
        // tests
        int slen = 8;
        long t0, t1;
        float s;
        
        shak1 = Util.strip(shak1);
        shak2 = Util.strip(shak2);
        
        t0 = System.currentTimeMillis();
        HashCodeSimilarity hcs = new HashCodeSimilarity(shak1, shak2, slen);
        s = hcs.similarity();
        t1 = System.currentTimeMillis();
        System.out.printf("HCS %dms is %f\n", (t1-t0), s);
        
        BruteForceSimilarity bfs = new BruteForceSimilarity(shak1, shak2, slen);
        t0 = System.currentTimeMillis();
        s = bfs.similarity();
        t1 = System.currentTimeMillis();
        System.out.printf("BFS %dms is %f\n", (t1-t0), s);
        
        HashStringSimilarity hss = new HashStringSimilarity(shak1, shak2, slen);
        t0 = System.currentTimeMillis();
        s = hss.similarity();
        t1 = System.currentTimeMillis();
        System.out.printf("HSS %dms is %f\n", (t1-t0), s);
	}

}
