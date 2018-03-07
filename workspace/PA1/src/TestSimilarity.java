import org.junit.Test;

public class TestSimilarity
{
    public static String vectorString1 = "ABFHBFDFAB";
    public static String vectorString2 = "BEAAHHDCH";
    public static String similarityString1 = "aroseisaroseisarose";
    public static String similarityString2 = "aroseisaflowerwhichisarose";

    public static int vectorShingleLength = 1;
    public static int similarityShingleLength = 4;

    public static float vectorAnswer1 = (float) Math.sqrt(24);
    public static float vectorAnswer2 = (float) Math.sqrt(17);
    public static float similarityAnswer = (float) (22 / (Math.sqrt(38) * Math.sqrt(27)));

    @Test
    public void bruteForceVectorLength()
    {
        BruteForceSimilarity bfs = new BruteForceSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        assert (bfs.lengthOfS1() == vectorAnswer1);
        assert (bfs.lengthOfS2() == vectorAnswer2);
    }

    @Test
    public void bruteForceSimilarity()
    {
        BruteForceSimilarity bfs = new BruteForceSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assert (bfs.similarity() == similarityAnswer);
    }

    @Test
    public void hashStringVectorLength()
    {
        HashStringSimilarity bfs = new HashStringSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        assert (bfs.lengthOfS1() == vectorAnswer1);
        assert (bfs.lengthOfS2() == vectorAnswer2);
    }

    @Test
    public void hashStringSimilarity()
    {
        HashStringSimilarity bfs = new HashStringSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assert (bfs.similarity() == similarityAnswer);
    }

    @Test
    public void hashCodeVectorLength()
    {
        HashCodeSimilarity bfs = new HashCodeSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        assert (bfs.lengthOfS1() == vectorAnswer1);
        assert (bfs.lengthOfS2() == vectorAnswer2);
    }

    @Test
    public void hashCodeSimilarity()
    {
        HashCodeSimilarity bfs = new HashCodeSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assert (Util.fuzzyMatch(bfs.similarity(), similarityAnswer, 0.1f));
    }
}
