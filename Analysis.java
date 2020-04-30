import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Scanner;

/**
 *
 * @author cute
 *
 *
 * 实现从文件中读入英文文章，统计单词个数,并按值从大到小输出
 */
public class wf {

    public static void main(String[] args) {
        //主程序逻辑入口
        while (true) {
            System.out.println("本程序有三种模式：1.单行语言处理；2.单个文件处理；3.批量处理;0.退出程序\n请键入1 2 3选择您需要的模式，模式2指定具体路径为d盘根目录");
            Scanner readerScanner = new Scanner(System.in);
            int flag = readerScanner.nextInt();
            if (flag == 0) {
                break;
            } else if (flag == 1) {
                try {
                    System.out.println("当前为当行语言处理模式，请输入您要评测的语句");
                    BufferedReader bf =new BufferedReader(new InputStreamReader(System.in)); //读取命令行中一行
                    String s=bf.readLine();
                    LineCode(s);
                } catch (IOException ex) {
                    System.out.println("请按单行输入句子");
                }
            } else if (flag == 2) {
                System.out.println("当前为单个文件处理模式，请输入您要输入的文件名，格式：aaa.txt");
                String s = readerScanner.next();
                try {
                    TxtCode(s);
                } catch (Exception ex) {
                    System.out.println("请输入正确的文件名称，确定后文件存在以及文件是否放在d:根目录下");
                }
            } else if(flag==3){
              System.out.println("当前为批量文件处理模式,请输入文件具体路径，格式：d:/ljr");
               String path=readerScanner.next();              
               File file =new File(path);
                if (file.isDirectory()) {
                    File[] filelist =file.listFiles();
                    for(File file1:filelist){
                        try {
                            String s=file1.getPath();//地址回溯
                            System.out.println(s);
                            FileCode(s);
                        } catch (Exception ex) {
                            System.out.println("请输入正确的路径，若程序无法结束请重新运行程序");
                        }
                    }
                }
            }
        }
        System.out.println("程序结束");
    }

    //统计单个文件
    public static void TxtCode(String txtname) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("F:/LIXIN/duanluo.txt" ));
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");  //过滤出只含有字母的  
            for (String word : wordsArr1) {
                if (word.length() != 0) {  //去除长度为0的行  
                    lists.add(word);
                }
            }
        }
        br.close();
        StatisticalCode(lists);       
    }

    //统计单行
    public static void LineCode(String args) {
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表 
        String[] wordsArr1 = args.split("[^a-zA-Z]");  //过滤出只含有字母的  
        for (String word : wordsArr1) {
            if (word.length() != 0) {  //去除长度为0的行  
                lists.add(word);
            }
        }
        StatisticalCode(lists);    
    }
    
    public static void FileCode(String args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(args));
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");  //过滤出只含有字母的  
            for (String word : wordsArr1) {
                if (word.length() != 0) {  //去除长度为0的行  
                    lists.add(word);
                }
            }
        }
        br.close();
        StatisticalCode(lists);       
    }
    
    public static void StatisticalCode(List<String> lists) {
              //统计排序
          Map<String, Integer> wordsCount = new TreeMap<String, Integer>();  //存储单词计数信息，key值为单词，value为单词数                
        //单词的词频统计  
        for (String li : lists) {
            if (wordsCount.get(li) != null) {
                wordsCount.put(li, wordsCount.get(li) + 1);
            } else {
                wordsCount.put(li, 1);
            }
        }
        // System.out.println("wordcount.Wordcount.main()");
        SortMap(wordsCount);    //按值进行排序 
    }
    //按value的大小进行排序  
    public static void SortMap(Map<String, Integer> oldmap) {

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();  //降序  
            }
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println("文中的单词："+list.get(i).getKey()+"出现次数:" +list.get(i).getValue());
        }
    }
}