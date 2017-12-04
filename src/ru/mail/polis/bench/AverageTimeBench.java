package ru.mail.polis.bench;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import ru.mail.polis.sort.*;

/**
 * Created by Nechaev Mikhail
 * Since 20/11/16.
 */

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class AverageTimeBench {

  int[][] data;
  Integer[][] dataInteger;
  int[] curr;
  Integer[] currentIntger;
  int index;
  AbstractSortOnComparisons<Integer>[] sorters = new AbstractSortOnComparisons[]{new QuickSort<Integer>(), new HeapSort(), new MergeSort(), new QuickSortWithTriplePartition()};

  @Setup(value = Level.Trial)
  public void setUpTrial() {
    data = new int[10][100];
    dataInteger = new Integer[10][100];
    for (int i = 0; i < 10; i++) {
      //define arrays here
      data[i] = SortUtils.generateArray(100);
      for (int j = 10; j < 100; j++) {
        data[i][j] = 100;
      }
      dataInteger[i] = IntStream.of(data[i]).boxed().toArray(Integer[]::new);
    }
  }

  @Setup(value = Level.Invocation)
  public void setUpInvocation() {
    curr = Arrays.copyOf(data[index], data[index].length);
    currentIntger = Arrays.copyOf(dataInteger[index], dataInteger[index].length);
    index = (index + 1) % 10;
  }

  @Benchmark
  public void measureBubbleSort() {
    BubbleSort.sort(curr);
  }

  @Benchmark
  public void measureQuickSort() {
    sorters[0].sort(currentIntger);
  }

  @Benchmark
  public void measureHeapSort() {
    sorters[1].sort(currentIntger);
  }

  @Benchmark
  public void measureMergeSort() {
    sorters[2].sort(currentIntger);
  }

  @Benchmark
  public void measureQuickSortWithTriplePartition() {
    sorters[3].sort(currentIntger);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
            .include(AverageTimeBench.class.getSimpleName())
            .build();

    new Runner(opt).run();
  }


}
