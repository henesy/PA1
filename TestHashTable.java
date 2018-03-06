import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

public class TestHashTable
{
    public static final String testString1 = "FlexboxFlexboxFlexboxFlexbox";
    public static final String testString2 = "is a flexbox toungue twister";
    public static final String testString3 = "fitnessgram pacer test";

    public static HashTable allSameHashTable()
    {
        HashTable ht = new HashTable(1024);
        ht.add(new Tuple(23, testString1));
        ht.add(new Tuple(23, testString2));
        return ht;
    }

    public static HashTable allUniqueHashTable()
    {
        HashTable ht = new HashTable(1024);
        ht.add(new Tuple(23, testString1));
        ht.add(new Tuple(25, testString2));
        return ht;
    }

    public static HashTable mixedHashTable()
    {
        HashTable ht = new HashTable(1024);
        ht.add(new Tuple(23, testString1));
        ht.add(new Tuple(23, testString2));
        ht.add(new Tuple(25, testString3));
        return ht;
    }

    @Test
    public void addAndLookup()
    {
        HashTable ht = new HashTable(1024);
        Tuple a = new Tuple(23, testString1);
        Tuple b = new Tuple(2435, testString2);
        ht.add(a);
        ht.add(b);
        assert (ht.search(a.getKey()).contains(a));
        assert (ht.search(a.getKey()).get(0).getValue() == a.getValue());
        assert (ht.search(b.getKey()).contains(b));
        assert (ht.search(b.getKey()).get(0).getValue() == b.getValue());
    }

    @Test
    public void addCollisions()
    {
        HashTable ht = new HashTable(1024);
        Tuple a = new Tuple(23, testString1);
        Tuple b = new Tuple(23, testString2);
        ht.add(a);
        ht.add(b);
        ArrayList<Tuple> list = ht.search(a.getKey());
        assert (list.contains(a));
        assert (list.contains(b));
    }

    @Test
    public void allSameNumLoaded()
    {
        assert (allSameHashTable().maxLoad() == 2);
    }

    @Test
    public void allUniqueNumLoaded()
    {
        assert (allUniqueHashTable().maxLoad() == 1);
    }

    @Test
    public void mixedNumLoaded()
    {
        assert (mixedHashTable().maxLoad() == 2);
    }

    @Test
    public void allSameAverageLoad()
    {
        assert (allSameHashTable().averageLoad() == 2);
    }

    @Test
    public void allSameAverageLoadRemove()
    {
        HashTable ht = allSameHashTable();
        ht.remove(new Tuple(23, testString1));
        assert (ht.averageLoad() == 1.0);
    }

    @Test
    public void allUniqueAverageLoad()
    {
        assert (allUniqueHashTable().averageLoad() == 1);
    }

    @Test
    public void allUniqueAverageLoadRemove()
    {
        HashTable ht = allUniqueHashTable();
        ht.remove(new Tuple(23, testString1));
        assert (ht.averageLoad() == 1);
    }

    @Test
    public void mixedAverageLoad()
    {
        assert (mixedHashTable().averageLoad() == 1.5);
    }

    @Test
    public void mixedAverageLoadRemove()
    {
        HashTable ht = mixedHashTable();
        ht.remove(new Tuple(23, testString1));
        assert (ht.averageLoad() == 1);

    }

    @Test
    public void mixedAverageLoadRemove2()
    {
        HashTable ht = mixedHashTable();
        ht.remove(new Tuple(25, testString3));
        assert (ht.averageLoad() == 2);
    }

    @Test
    public void allSameLoadFactor()
    {
        assert (allSameHashTable().loadFactor() == (float) 2 / 1031);
    }

    @Test
    public void allUniqueLoadFactor()
    {
        assert (allUniqueHashTable().loadFactor() == (float) 2 / 1031);
    }

    @Test
    public void mixedLoadFactor()
    {
        assert (mixedHashTable().loadFactor() == (float) 3 / 1031);
    }

    @Test
    public void emptyAverageLoad()
    {
        assert (new HashTable(1024).averageLoad() == 0);
    }

    @Test
    public void emptyMaxLoad()
    {
        assert (new HashTable(1024).maxLoad() == 0);
    }

    @Test
    // due to spooky maths, small chance of false flags
    public void resizes()
    {
        HashTable ht = new HashTable(11);
        for (int i = 0; i < 15; i++)
        {
            ht.add(new Tuple(i, testString1));
        }
        assert (ht.size() > 11);
    }

    @Test
    public void sizeDoubles()
    {
        int size = 23;
        HashTable ht = new HashTable(size);
        for (int i = 0; ht.size() == size; i++)
        {
            ht.add(new Tuple(i, testString1));
        }
        assert (ht.size() == 47);
    }

    @Test
    public void resizeLosesNothing()
    {
        int size = 20;
        HashTable ht = new HashTable(size);
        LinkedList<Tuple> tuples = new LinkedList<Tuple>();
        for (int i = 0; ht.size() == 23 && i < 1000; i++)
        {
            Tuple t = new Tuple(i, testString1);
            ht.add(t);
            tuples.add(t);
        }
        for (Tuple t : tuples)
        {
            assert (ht.search(t.key).contains(t));
        }
    }

    @Test
    public void sizeIsPrime()
    {
        HashTable ht = new HashTable(1024);
        assert (ht.size() == 1031);
    }
}
