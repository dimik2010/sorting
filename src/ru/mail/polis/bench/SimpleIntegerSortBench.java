package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.*;
import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SimpleIntegerSortBench {
  private SimpleInteger[] a;
  private LSDSort<SimpleInteger> lsdSort = new LSDSort<>();
  private AbstractSortOnComparisons<SimpleInteger> quickSort = new QuickSort<>();
  private AbstractSortOnComparisons<SimpleInteger> heapSort = new HeapSort<>();


  @Setup(value = Level.Invocation)
  public void setUpInvocation() {
    a = SortUtils.generateSimpleIntegerArray(1000);
  }

  @Benchmark
  public void measureLSDSort(Blackhole bh) {
    lsdSort.sort(a);
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


  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
            .include(SimpleIntegerSortBench.class.getSimpleName())
            .warmupIterations(5)
            .measurementIterations(5)
            .forks(1)
            .build();

    new Runner(opt).run();
  }
}

