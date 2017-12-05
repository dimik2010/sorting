package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.*;
import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class IntKeyObjectSortBench {
  private IntKeyObject<String>[] a;
  private CountingSort<IntKeyObject<String>> countingSort = new CountingSort<>();
  private AbstractSortOnComparisons<IntKeyObject<String>> quickSort = new QuickSort<>();
  private AbstractSortOnComparisons<IntKeyObject<String>> heapSort = new HeapSort<>();
  private AbstractSortOnComparisons<IntKeyObject<String>> quickSortWithTriplePartition = new QuickSortWithTriplePartition<>();


  @Setup(value = Level.Invocation)
  public void setUpInvocation() {
    a = SortUtils.generateIntKeyStringValueObjectArray(1000, 1000000);
  }

  @Benchmark
  public void measureCountingSort(Blackhole bh) {
    countingSort.sort(a);
    bh.consume(a);
  }
  @Benchmark
  public void measureQuickSort(Blackhole bh) {
    quickSort.sort(a);
    bh.consume(a);
  }
  @Benchmark
  public void measureHeapSort(Blackhole bh) {
    heapSort.sort(a);
    bh.consume(a);
  }
  @Benchmark
  public void measureQuickSortWithTriplePartition(Blackhole bh) {
    quickSortWithTriplePartition.sort(a);
    bh.consume(a);
  }


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
            .include(IntKeyObjectSortBench.class.getSimpleName())
            .warmupIterations(5)
            .measurementIterations(5)
            .forks(1)
            .build();

    new Runner(opt).run();
  }
}
