package com.ch12.Thread_Safe_Collections.blockingQueue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE=10;
    private static final int SEARCH_THREADS = 100;
    private static final Path DUMMY =Path.of("");
    //阻塞队列
    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
    public static void main(String[] args) {
        //try-with-resources
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Enter base directory(e.g. /opt/jdk-9-src): ");
            String directory = scanner.nextLine();
            System.out.println("Enter keyword(e.g. volatile): ");
            String keyword = scanner.nextLine();
//            System.out.println(keyword);

            Runnable enumerator = () -> {
                try {
                    enumerate(Path.of(directory));
                    //put() 向队尾添加一个元素，如果队列满，则阻塞
                    queue.put(DUMMY);
                } catch (InterruptedException ignored) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
            };

            new Thread(enumerator).start();

            for (int i = 0; i < SEARCH_THREADS; i++) {

                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            //take() 移除并返回队首元素，如果队列空，则阻塞
                            Path file = queue.take();
                            if (file == DUMMY) {
                                queue.put(file);
//                                System.out.println(file.getFileName());
                                done = true;
                            } else search(file, keyword);
                        }
                    } catch (InterruptedException ignored) {

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                };

                new Thread(searcher).start();
//                System.out.println("searcher启动");
            }

        }

    }

    /**
     * 寻找一个包含关键字的文件，并把所有匹配结果打印出来
     * @param file 搜索的文件
     * @param keyword 搜索的关键字
     */
    private static void search(Path file, String keyword) throws IOException {
        try(Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)){
            int lineNumber = 0;
            while(scanner.hasNextLine()){
                lineNumber++;
                String line = scanner.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n",file,lineNumber,line);
                }
            }
        }
    }

    /**
     * 递归地枚举给定目录和它的子目录中的所有文件
     * @param directory 开始的目录
     */
    private static void enumerate(Path directory) throws IOException, InterruptedException {
        try(Stream<Path> children = Files.list(directory))
        {
            List<Path> collect = children.collect(Collectors.toList());
            for (Path child : collect) {
                //对于收集到的所有的路径,如果是一个目录(子目录)，继续递归
                if(Files.isDirectory(child))
                    enumerate(child);
                //如果不是目录，把这个路径放进阻塞队列中，准备打印
                else
//                    System.out.println(child.getFileName());
                    queue.put(child);
            }
        }
    }
}
