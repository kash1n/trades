package ru.kashin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String pathToFile = "/home/kostantin/trades.txt";

        try (Stream<String> stream = Files.lines(Paths.get(pathToFile))) {

            Comparator<List<TradeInfo>> priceGrowCmp = (list1, list2) -> {
                Double diff1 = list1.get(list1.size() - 1).PRICE - list1.get(0).PRICE;
                Double diff2 = list2.get(list2.size() - 1).PRICE - list2.get(0).PRICE;
                return diff1.compareTo(diff2);
            };

            Comparator<TradeInfo> dateCmp = Comparator.comparing(trade -> trade.TRADETIME);

            var groups = stream
                    .skip(1)
                    .map(TradeInfo::fromString)
                    .filter((r) -> r.SECBOARD.equals("TQBR") || r.SECBOARD.equals("FQBR"))
                    .collect(Collectors.groupingBy(TradeInfo::getSECCODE))
                    .values()
                    .stream()
                    .peek((group) -> group.sort(dateCmp))
                    .sorted(priceGrowCmp)
                    .toList();
            var worstGroups = groups.subList(0, 10);
            var bestGroups = groups.subList(groups.size() - 10, groups.size());

            System.out.println("Worst:");
            printResult(worstGroups);
            System.out.println("Best:");
            printResult(bestGroups);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printResult (List<List<TradeInfo>> groups) {
        System.out.println("  SECCODE  | Number of trades | Total volume | Diff in %");
        groups.forEach((group) -> {
            TradeInfo first = group.get(0);
            TradeInfo last = group.get(group.size() - 1);

            Double priceDiffPercent = 100. * (last.PRICE - first.PRICE) / first.PRICE;
            Double totalVolume = group.stream()
                    .map(TradeInfo::getVALUE)
                    .reduce(Double::sum)
                    .get();

            System.out.printf("%10s  %13d %18.2f %+7.2f%%\n", first.SECCODE, group.size(),
                    totalVolume, priceDiffPercent);
        });
    }
}
