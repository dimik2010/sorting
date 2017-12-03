package ru.mail.polis.sort.valid;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.SimpleSortOnComparisons;
import ru.mail.polis.structures.*;

/**
 * Created by Nechaev Mikhail
 * Since 14/11/2017.
 */
public class SimpleTest {

    @Test
    public void test01() throws IOException {
        LSDSort<SimpleString> sorter = new LSDSort<>();
        SimpleString[] array = new SimpleString[]{new SimpleString("abc"), new SimpleString("bcd"), new SimpleString("cde"),
                new SimpleString("acd"), new SimpleString("zxy"), new SimpleString("bba")};
        sorter.sort(array);

        Assert.assertArrayEquals(new SimpleString[]{new SimpleString("abc"), new SimpleString("acd"), new SimpleString("bba"),
                new SimpleString("bcd"), new SimpleString("cde"), new SimpleString("zxy")}, array);
    }

    @Test
    public void testLSD() throws IOException {
        LSDSort<SimpleInteger> lsdSort = new LSDSort<>();
        SimpleInteger[] array = new SimpleInteger[]{new SimpleInteger(10), new SimpleInteger(20), new SimpleInteger(30)};
        lsdSort.sort(array);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCountingSort() throws IOException {
        CountingSort<IntKeyObject<String>> countingSort = new CountingSort<>();
        IntKeyObject<String>[] array = new IntKeyStringValueObject[] {new IntKeyStringValueObject(1, "abc"), new IntKeyStringValueObject(2, "bcd")};
        countingSort.sort(array);
    }
}
