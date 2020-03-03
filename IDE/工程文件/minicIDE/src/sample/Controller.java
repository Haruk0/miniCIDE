package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;
import org.fxmisc.richtext.CaretNode;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.fxmisc.flowless.*;
import org.reactfx.Subscription;

public class Controller {

    private IDEFiles ideFiles=new IDEFiles();
    private boolean[] buttonStatus=new boolean[15];
    private int findingIndex=0;
    private ArrayList<Pair<Integer,Integer>> findingWordPairs=new ArrayList<Pair<Integer, Integer>>();
    private boolean hasFindOneWord=false;
    private int replaceWhat=0;
    @FXML
    private CodeArea codeTextArea;
    @FXML
    private Text status;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private Button file0;
    @FXML
    private Button x0;
    @FXML
    private Button file1;
    @FXML
    private Button x1;
    @FXML
    private Button file2;
    @FXML
    private Button x2;
    @FXML
    private Button file3;
    @FXML
    private Button x3;
    @FXML
    private Button file4;
    @FXML
    private Button x4;
    @FXML
    private Button file5;
    @FXML
    private Button x5;
    @FXML
    private Button file6;
    @FXML
    private Button x6;
    @FXML
    private Button file7;
    @FXML
    private Button x7;
    @FXML
    private Button file8;
    @FXML
    private Button x8;
    @FXML
    private Button file9;
    @FXML
    private Button x9;
    @FXML
    private Button file10;
    @FXML
    private Button x10;
    @FXML
    private Button file11;
    @FXML
    private Button x11;
    @FXML
    private Button file12;
    @FXML
    private Button x12;
    @FXML
    private Button file13;
    @FXML
    private Button x13;
    @FXML
    private Button file14;
    @FXML
    private Button x14;
    @FXML
    private TextArea console;
    @FXML
    private TextField word;
    @FXML
    private TextField wordForReplace;

    public Controller()
    {
        for(int i=0;i<15;i++)
        {
            buttonStatus[i]=false;
        }
        //try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                //| UnsupportedLookAndFeelException e) {
            //e.printStackTrace();
        //}
        //更改弹出窗口风格为Win10风格，第一次弹出会很卡。
        //Font f=new Font("宋体",Font.PLAIN,12);
        //UIManager.put("Button.font",f);
        //UIManager.put("Label.font",f);
        codeTextArea=new CodeArea();

        treeView=new TreeView<String>();
    }

    public void newFile() {
        if (ideFiles.currentFile == 14)
            JOptionPane.showMessageDialog(null, "打开文件过多，请关闭一个后再创建", "提示", JOptionPane.INFORMATION_MESSAGE);
        else {
            JFrame titleF = new JFrame("新建文件");
            JLabel scriptionL = new JLabel("请输入路径，如d:\\minicIDE\\testFiles\\test.c");
            JLabel pathL = new JLabel("文件路径：");
            JButton okB = new JButton("确定");
            JButton cancelB = new JButton("取消");
            JTextField pathTF = new JTextField("d:\\minicIDE\\testFiles\\");
            titleF.setLayout(null);
            titleF.add(scriptionL);
            scriptionL.setBounds(110, 20, 350, 30);
            titleF.add(pathL);
            pathL.setBounds(100, 60, 40, 25);
            titleF.add(okB);
            okB.setBounds(120, 110, 70, 32);
            titleF.add(cancelB);
            cancelB.setBounds(280, 110, 70, 32);
            titleF.add(pathTF);
            pathTF.setBounds(90, 60, 350, 25);
            titleF.setBounds(500, 400, 500, 200);
            titleF.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            titleF.setVisible(true);
            okB.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    createFile(pathTF.getText());
                    titleF.setVisible(false);
                }
            });
            cancelB.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    titleF.setVisible(false);
                }
            });
            //JFrame无法关闭！！！退出程序后dyn仍然在运行
        }
    }

    public void saveFile() {
        if(ideFiles.currentFile==-1)
            JOptionPane.showMessageDialog(null, "请选中需要保存/运行的文件！", "提示", JOptionPane.INFORMATION_MESSAGE);
        else {
            String path = ideFiles.filePathes.get(ideFiles.currentFile);
            writeFile(path);
            status.setText("保存成功！路径："+path);
        }
    }

    public void saveFileAs() {
        if(ideFiles.currentFile==-1)
            JOptionPane.showMessageDialog(null,"请选中需要保存的文件","提示",JOptionPane.INFORMATION_MESSAGE);
        else {
            JFrame titleF = new JFrame("文件另存为...");
            JLabel scriptionL = new JLabel("请输入路径，如d:\\minicIDE\\testFiles\\test.c");
            JLabel pathL = new JLabel("文件路径：");
            JButton okB = new JButton("确定");
            JButton cancelB = new JButton("取消");
            JTextField pathTF = new JTextField("d:\\minicIDE\\testFiles\\");
            titleF.setLayout(null);
            titleF.add(scriptionL);
            scriptionL.setBounds(110, 20, 350, 30);
            titleF.add(pathL);
            pathL.setBounds(100, 60, 40, 25);
            titleF.add(okB);
            okB.setBounds(120, 110, 70, 32);
            titleF.add(cancelB);
            cancelB.setBounds(280, 110, 70, 32);
            titleF.add(pathTF);
            pathTF.setBounds(90, 60, 350, 25);
            titleF.setBounds(500, 400, 500, 200);
            titleF.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            titleF.setVisible(true);
            okB.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    stringToNewFile(pathTF.getText());
                    titleF.setVisible(false);
                }
            });
            cancelB.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    titleF.setVisible(false);
                }
            });
        //JFrame无法关闭！！！退出程序后仍然在运行
        }
    }

    public void runCode()
    {
        if(ideFiles.currentFile!=-1)
        {
            if(parseFileType(ideFiles.filePathes.get(ideFiles.currentFile)).compareTo(".asm")==0||parseFileType(ideFiles.filePathes.get(ideFiles.currentFile)).compareTo(".s")==0)
            {
                console.clear();
                console.appendText("开始汇编："+ideFiles.filePathes.get(ideFiles.currentFile)+"\n");
                console.appendText("汇编中···"+"\n");
                saveFile();
                Runtime runtime = Runtime.getRuntime();
                StringBuffer b=new StringBuffer();
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec("d:\\minicIDE\\assembler_v1.exe \"2\" "+ideFiles.filePathes.get(ideFiles.currentFile)+" bios.asm led2.asm key.asm").getInputStream()));
                    String line=null;
                    while ((line=br.readLine())!=null) {
                        b.append(line+"\n");
                    }
                    System.out.println(b.toString());
                } catch (Exception e) {
                    console.appendText("调用汇编程序失败！ "+e.getMessage()+"\n");
                }
                console.appendText("调用汇编程序完成,控制台输出："+"\n"+b.toString());
                StringBuffer result = new StringBuffer();
                try {
                    File file = new File("d:\\minicIDE\\minicIDE_jar\\errlog.txt");
                    //File file = new File("c:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\errlog.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s = null;
                    int count=0;
                    while ((s = br.readLine()) != null) {
                        if(count==0)
                            result.append(s);
                        else
                            result.append(System.lineSeparator() + s);
                        count++;
                    }
                    br.close();
                } catch (Exception e) {
                    console.appendText("读取汇编结果错误！ "+e.getMessage()+"\n");
                }
                if(result.toString().charAt(0)=='1')
                {
                    console.clear();
                    String path1=new String("d:\\minicIDE\\minicIDE_jar\\prgmip32.coe\n");
                    String path2=new String("d:\\minicIDE\\minicIDE_jar\\dmem32.coe\n");
                    String info3=new String("汇编成功！ 产生coe文件： \n");
                    //console.appendText("汇编成功！ 产生coe文件："+"\n"+"C:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\prgmip32.coe"+"\n"+"C:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\dmem32.coe\n");
                    console.appendText(info3);
                    console.appendText(path1);
                    console.appendText(path2);
                }else{
                    console.clear();
                    String info1=new String("汇编失败！ 未产生coe文件，错误信息：\n"+result.toString());
                    console.appendText(info1);
                }
            }
            if(parseFileType(ideFiles.filePathes.get(ideFiles.currentFile)).compareTo(".c")==0)
            {
                //在这里由c文件生成asm文件
                console.clear();
                saveFile();

                Runtime runtime = Runtime.getRuntime();
                StringBuffer b=new StringBuffer();
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec("d:\\minicIDE\\minic_compiler.exe "+ideFiles.filePathes.get(ideFiles.currentFile)).getInputStream()));
                    String line=null;
                    while ((line=br.readLine())!=null) {
                        b.append(line+"\n");
                    }
                    System.out.println(b.toString());
                } catch (Exception e) {
                    console.appendText("调用编译程序失败！ "+e.getMessage()+"\n");
                }
                console.appendText("调用编译程序完成,控制台输出："+"\n"+b.toString());
                StringBuffer result = new StringBuffer();
                try {
                    File file = new File("d:\\minicIDE\\minicIDE_jar\\errorlog.txt");
                    //File file = new File("c:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\errorlog.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s = null;
                    int count=0;
                    while ((s = br.readLine()) != null) {
                        if(count==0)
                            result.append(s);
                        else
                            result.append(System.lineSeparator() + s);
                        count++;
                    }
                    br.close();
                } catch (Exception e) {
                    console.appendText("读取编译结果错误！ "+e.getMessage()+"\n");
                }
                if(result.toString().compareTo("0 error(s) generated.")==0)
                {
                    console.clear();
                    String path1=new String("d:\\minicIDE\\minicIDE_jar\\program.asm\n");
                    String info3=new String("编译成功！ 产生.asm文件： \n");
                    //console.appendText("汇编成功！ 产生coe文件："+"\n"+"C:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\prgmip32.coe"+"\n"+"C:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\dmem32.coe\n");
                    console.appendText(result+"\n"+info3);
                    console.appendText(path1);
                    //openFileToString("d:\\minicIDE\\minicIDE_jar\\program.s");
                    //runCode();
                }else{
                    console.clear();
                    String info1=new String("编译失败！ 错误信息：\n"+result.toString());
                    console.appendText(info1);
                }
            }
        }
    }


    public void openFile() {
        if(ideFiles.currentFile==14)
            JOptionPane.showMessageDialog(null,"打开文件过多，请关闭一个后再打开","提示",JOptionPane.INFORMATION_MESSAGE);
        else{
            JFrame titleF = new JFrame("打开文件");
            JLabel scriptionL = new JLabel("请输入路径，如d:\\minicIDE\\testFiles\\test.c");
            JLabel pathL = new JLabel("文件路径：");
            JButton okB = new JButton("确定");
            JButton cancelB = new JButton("取消");
            JTextField pathTF = new JTextField("d:\\minicIDE\\testFiles\\user.asm");
            titleF.setLayout(null);
            titleF.add(scriptionL);
            scriptionL.setBounds(110, 20, 350, 30);
            titleF.add(pathL);
            pathL.setBounds(100, 60, 40, 25);
            titleF.add(okB);
            okB.setBounds(120, 110, 70, 32);
            titleF.add(cancelB);
            cancelB.setBounds(280, 110, 70, 32);
            titleF.add(pathTF);
            pathTF.setBounds(90, 60, 350, 25);
            titleF.setBounds(500, 400, 500, 200);
            titleF.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            titleF.setVisible(true);
            okB.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    openFileToString(pathTF.getText());
                    titleF.setVisible(false);
                }
            });
            cancelB.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    titleF.setVisible(false);
                }
            });
        //JFrame无法关闭！！！退出程序后仍然在运行
        }
    }

    public void openFileToString(String path) {
        boolean success = true;
        StringBuffer result = new StringBuffer();
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            int count=0;
            while ((s = br.readLine()) != null) {
                if(count==0)
                    result.append(s);
                else
                    result.append(System.lineSeparator() + s);
                count++;
            }
            br.close();

        } catch (Exception e) {
            success=false;
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
        }

        if(success==true)
        {
            ideFiles.currentAllFile += 1;
            ideFiles.currentFile = ideFiles.currentAllFile;
            mySetVisible(ideFiles.currentAllFile,true);
            changeTitle(ideFiles.currentAllFile, parse(path));
            ideFiles.filePathes.add(path);
            ideFiles.fileNames.add(parse(path));
            ideFiles.fileCaches.add(result.toString());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    codeTextArea.clear();
                    codeTextArea.appendText(result.toString());
                    codeTextArea.setParagraphGraphicFactory(LineNumberFactory.get(codeTextArea));
                    computeHighlighting(codeTextArea.getText());
                }
            });
            constructTreeView();
            status.setText("文件打开成功！路径：" + path);
        }
    }

    public void stringToNewFile(String path) {
        writeFile(path);
        status.setText("另存为成功，路径："+path);
        ideFiles.fileNames.set(ideFiles.currentFile,parse(path));
        ideFiles.filePathes.set(ideFiles.currentFile,path);
        changeTitle(ideFiles.currentFile,ideFiles.fileNames.get(ideFiles.currentFile));
        //需要加到内容里。
    }

    public void createFile(String path)
    {
        boolean success=true;
        try {
            File newFile = new File(path);
            newFile.createNewFile();
        } catch (Exception e) {
            success=false;
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
        }
        if(success==true)
        {
            ideFiles.currentAllFile += 1;
            ideFiles.currentFile = ideFiles.currentAllFile;
            mySetVisible(ideFiles.currentAllFile,true);
            changeTitle(ideFiles.currentFile, parse(path));
            ideFiles.fileNames.add(parse(path));
            ideFiles.filePathes.add(path);
            ideFiles.fileCaches.add("");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    codeTextArea.clear();
                    codeTextArea.setParagraphGraphicFactory(LineNumberFactory.get(codeTextArea));
                }
            });
            constructTreeView();
            status.setText("新建文件成功！路径：" + path);
        }
    }


    public String parse(String path)
    {
        int i=path.length()-1;
        while(path.charAt(i)!='\\')
        {
            i--;
        }
        //System.out.println("处理到"+path.charAt(i+1)+"   长度是"+path.charAt(path.length()-1));
        return path.substring(i+1);
    }


    public  void file0Action()
    {
        fileAction(0);
    }

    public void x0Action()
    {
        xAction(0);
    }
    public void file1Action()
    {
        fileAction(1);
    }

    public void x1Action()
    {
        xAction(1);
    }


    public void file2Action()
    {
        fileAction(2);
    }

    public void x2Action()
    {
        xAction(2);
    }


    public void file3Action()
    {
        fileAction(3);
    }

    public void x3Action()
    {
        xAction(3);
    }


    public void file4Action()
    {
        fileAction(4);
    }

    public void x4Action()
    {
        xAction(4);
    }


    public void file5Action()
    {
        fileAction(5);
    }

    public void x5Action()
    {
        xAction(5);
    }


    public void file6Action()
    {
        fileAction(6);
    }

    public void x6Action()
    {
        xAction(6);
    }


    public void file7Action()
    {
        fileAction(7);
    }

    public void x7Action()
    {
        xAction(7);
    }


    public void file8Action()
    {
        fileAction(8);
    }

    public void x8Action()
    {
        xAction(8);
    }


    public void file9Action()
    {
        fileAction(9);
    }

    public void x9Action()
    {
        xAction(9);
    }


    public void file10Action()
    {
        fileAction(10);
    }

    public void x10Action()
    {
        xAction(10);
    }


    public void file11Action()
    {
        fileAction(11);
    }

    public void x11Action()
    {
        xAction(11);
    }


    public void file12Action()
    {
        fileAction(12);
    }

    public void x12Action()
    {
        xAction(12);
    }


    public void file13Action()
    {
        fileAction(13);
    }

    public void x13Action()
    {
        xAction(13);
    }


    public void file14Action()
    {
        fileAction(14);
    }

    public void x14Action()
    {
        xAction(14);
    }

    public void synchroAreaAndArray(int currentFile)
    {
        if(currentFile!=-1)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ideFiles.fileCaches.set(currentFile,codeTextArea.getText());
                }
            });
        }
    }

    public void mySetVisible(int i,boolean visible)
    {
        switch (i)
        {
            case 0:
                file0.setVisible(visible);
                x0.setVisible(visible);
                break;
            case 1:
                file1.setVisible(visible);
                x1.setVisible(visible);
                break;
            case 2:
                file2.setVisible(visible);
                x2.setVisible(visible);
                break;
            case 3:
                file3.setVisible(visible);
                x3.setVisible(visible);
                break;
            case 4:
                file4.setVisible(visible);
                x4.setVisible(visible);
                break;
            case 5:
                file5.setVisible(visible);
                x5.setVisible(visible);
                break;
            case 6:
                file6.setVisible(visible);
                x6.setVisible(visible);
                break;
            case 7:
                file7.setVisible(visible);
                x7.setVisible(visible);
                break;
            case 8:
                file8.setVisible(visible);
                x8.setVisible(visible);
                break;
            case 9:
                file9.setVisible(visible);
                x9.setVisible(visible);
                break;
            case 10:
                file10.setVisible(visible);
                x10.setVisible(visible);
                break;
            case 11:
                file11.setVisible(visible);
                x11.setVisible(visible);
                break;
            case 12:
                file12.setVisible(visible);
                x12.setVisible(visible);
                break;
            case 13:
                file13.setVisible(visible);
                x13.setVisible(visible);
                break;
            case 14:
                file14.setVisible(visible);
                x14.setVisible(visible);
                break;
                default:
        }
    }

    public void changeTitle(int i,String name)
    {
        Platform.runLater(new Runnable(){
            public void run(){
                switch (i)
                {
                    case 0:
                        file0.setText(name);
                    case 1:
                        file1.setText(name);
                    case 2:
                        file2.setText(name);
                    case 3:
                        file3.setText(name);
                    case 4:
                        file4.setText(name);
                    case 5:
                        file5.setText(name);
                    case 6:
                        file6.setText(name);
                    case 7:
                        file7.setText(name);
                    case 8:
                        file8.setText(name);
                    case 9:
                        file9.setText(name);
                    case 10:
                        file10.setText(name);
                    case 11:
                        file11.setText(name);
                    case 12:
                        file12.setText(name);
                    case 13:
                        file13.setText(name);
                    case 14:
                        file14.setText(name);
                }
            }
        });

    }

    public void xAction(int x)
    {
        //未保存文件关闭提示
        /*StringBuffer result=new StringBuffer();
        try{
            File file = new File(ideFiles.filePathes.get(x));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            int count=0;
            while ((s = br.readLine()) != null) {
                if(count==0)
                    result.append(s);
                else
                    result.append(System.lineSeparator() + s);
                count++;
            }
            br.close();
        }catch(Exception e)
        {

        }
        if(ideFiles.currentFile==x)
        {
            if(codeTextArea.getText().compareTo(result.toString())!=0);
            {
                int n = JOptionPane.showOptionDialog(new JFrame(), "文件内容已经改变，是否保存？", "提示",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        new Object[] { "保存", "取消" }, JOptionPane.YES_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    saveFile();
                } else if (n == JOptionPane.NO_OPTION) {

                } else if (n == JOptionPane.CLOSED_OPTION) {
                    System.out.println("Closed by hitting the cross");
                }
            }
        }*/
        buttonStatus[x]=true;
        if(ideFiles.currentFile==x)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    codeTextArea.clear();
                    treeView.setRoot(new TreeItem<String>());
                }
            });
        }

        status.setText("已关闭文件 ： "+ideFiles.fileNames.get(x));

        if(ideFiles.currentFile==x)
            ideFiles.currentFile = -1;
        else
            ideFiles.currentFile-=1;

        ideFiles.currentAllFile -= 1;
        ideFiles.filePathes.remove(x);
        ideFiles.fileCaches.remove(x);
        ideFiles.fileNames.remove(x);

        for(int i=x;i<=ideFiles.currentAllFile;i++)
        {
            changeTitle(i,ideFiles.fileNames.get(i));
        }
        mySetVisible(ideFiles.currentAllFile+1,false);
    }

    public void fileAction(int f)
    {

        if(buttonStatus[f]==false)
        {
            synchroAreaAndArray(ideFiles.currentFile);
            ideFiles.currentFile=f;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    codeTextArea.clear();
                    codeTextArea.appendText(ideFiles.fileCaches.get(f));
                    computeHighlighting(codeTextArea.getText());
                }
            });
            constructTreeView();
            //System.out.println("currentFile:"+ideFiles.currentFile+"  AllFiles:"+ideFiles.currentAllFile);
        }
        if(buttonStatus[f]==true)
        {
            //codeTextArea.setText("又误触");
            buttonStatus[f]=false;
        }
    }

    public void writeFile(String path)
    {
        try {
            File writeInFile = new File(path);
            Writer out = new FileWriter(writeInFile);
            out.write(codeTextArea.getText());
            out.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String parseFileType(String path)
    {
        String fileType=new String();
        int i=path.length()-1;
        while(i>-1&&path.charAt(i)!='.')
        {
            i--;
        }
        //System.out.println(path.substring(i));
        if(path.substring(i).compareTo(".c")==0||path.substring(i).compareTo(".asm")==0||path.substring(i).compareTo(".s")==0)
        {
            return path.substring(i);
        }
        return null;
    }

    public String dynamicAnalyseAsm(String code)
    {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("d:\\minicIDE\\temp.asm"));
            writer.write(code);
            writer.close();
        }catch (Exception e)
        {
            status.setText("创建分析缓存失败 "+e.getMessage());
        }

        /*try {
            //Runtime.getRuntime().exec("cd /D e:\\");
            //Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "cd /D e:\\"});
            //Runtime.getRuntime().exec(new String[]{"git","diff","034hsf","2367dd","--name-status"},null,new File("e:/"));
            //Runtime.getRuntime().exec("e:\\assembler_v1.exe \"1\" "+ideFiles.filePathes.get(ideFiles.currentFile));
            Runtime.getRuntime().exec(new String[]{"assembler_v1.exe","\"1\"",ideFiles.filePathes.get(ideFiles.currentFile)},null,new File("e:\\"));
        }catch (Exception e)
        {
            status.setText("调用分析失败 "+e.getMessage());
        }*/
        Runtime runtime = Runtime.getRuntime();
                 try {
                     BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec(
                             "d:\\minicIDE\\assembler_v1.exe \"1\" "+"d:\\minicIDE\\temp.asm").getInputStream()));
                     String line=null;
                     StringBuffer b=new StringBuffer();
                     while ((line=br.readLine())!=null) {
                             b.append(line+"\n");
                         }
                     //System.out.println(b.toString());
                 } catch (Exception e) {
                     status.setText("调用分析失败 "+e.getMessage());
                 }

        StringBuffer result = new StringBuffer();
        try {
            File file = new File("d:\\minicIDE\\minicIDE_jar\\keywordP2.txt");
            //File file = new File("c:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\keywordP2.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            int count=0;
            while ((s = br.readLine()) != null) {
                if(count==0)
                    result.append(s);
                else
                    result.append(System.lineSeparator() + s);
                count++;
            }
            br.close();

        } catch (Exception e) {
            status.setText("读取分析缓存失败 "+e.getMessage());
        }
        //console.setText(result.toString());
        //status.setText(".asm文件分析成功！");
        return result.toString();
    }

    public String dynamicAnalyseC(String code)
    {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("d:\\minicIDE\\temp.c"));
            writer.write(code);
            writer.close();
        }catch (Exception e)
        {
            status.setText("创建分析缓存失败 "+e.getMessage());
        }

        /*try {
            //Runtime.getRuntime().exec("cd /D e:\\");
            //Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "cd /D e:\\"});
            //Runtime.getRuntime().exec(new String[]{"git","diff","034hsf","2367dd","--name-status"},null,new File("e:/"));
            //Runtime.getRuntime().exec("e:\\assembler_v1.exe \"1\" "+ideFiles.filePathes.get(ideFiles.currentFile));
            Runtime.getRuntime().exec(new String[]{"assembler_v1.exe","\"1\"",ideFiles.filePathes.get(ideFiles.currentFile)},null,new File("e:\\"));
        }catch (Exception e)
        {
            status.setText("调用分析失败 "+e.getMessage());
        }*/
        Runtime runtime = Runtime.getRuntime();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec("d:\\minicIDE\\minic_compiler.exe "+"d:\\minicIDE\\temp.c").getInputStream()));
            //runtime.exec("e:\\assembler_v1.exe \"1\" "+ideFiles.filePathes.get(ideFiles.currentFile));
            //StringBuffer b = new StringBuffer();
            String line=null;
            StringBuffer b=new StringBuffer();
            while ((line=br.readLine())!=null) {
                b.append(line+"\n");
            }
            //System.out.println(b.toString());
        } catch (Exception e) {
            status.setText("调用分析失败 "+e.getMessage());
        }

        StringBuffer result = new StringBuffer();
        try {
            File file = new File("d:\\minicIDE\\minicIDE_jar\\keywordinfo.txt");
            //File file = new File("c:\\Users\\admin\\Desktop\\minic-edu-app-master\\minicIDE\\keywordinfo.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            int count=0;
            while ((s = br.readLine()) != null) {
                if(count==0)
                    result.append(s);
                else
                    result.append(System.lineSeparator() + s);
                count++;
            }
            br.close();

        } catch (Exception e) {
            status.setText("读取分析缓存失败 "+e.getMessage());
        }
        //console.setText(result.toString());
        //status.setText(".asm文件分析成功！");
        return result.toString();
    }

    public void computeHighlighting(String text)
    {
        if(parseFileType(ideFiles.filePathes.get(ideFiles.currentFile)).compareTo(".asm")==0||parseFileType(ideFiles.filePathes.get(ideFiles.currentFile)).compareTo(".s")==0)
        {
            String result=dynamicAnalyseAsm(text);
            ArrayList<Integer> intArray=locateToken(text);
            int lastKwWord=0;
            StringBuffer type;
            StringBuffer firstc;
            StringBuffer lastc;
            StringBuffer firstl;
            StringBuffer lastl;
            int i=0;
            while(i<result.length())
            {
                type=new StringBuffer();
                firstc=new StringBuffer();
                lastc=new StringBuffer();
                firstl=new StringBuffer();
                lastl=new StringBuffer();

                while((result.charAt(i)<48||result.charAt(i)>57)&&result.charAt(i)!=45)
                {
                    if(i==result.length()-1)
                        break;
                    i++;
                }
                if(i==result.length()-1)
                    break;
                while((result.charAt(i)>=48&&result.charAt(i)<=57)||result.charAt(i)==45)
                {
                    type.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    firstc.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    lastc.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    firstl.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(i<result.length()&&result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    lastl.append(result.charAt(i));
                    i++;
                }

                //System.out.println(type+" "+firstc+" "+lastc+" "+firstl+" "+lastl);
                int intLine=Integer.valueOf(firstl.toString());
                int startPlace=0;

                for(int x=0;x<intLine;x++)
                {
                    startPlace+=intArray.get(x);
                }
                startPlace+=Integer.valueOf(firstc.toString());
                startPlace-=1;
                int tokenLength=Integer.valueOf(lastc.toString())-Integer.valueOf(firstc.toString());
                int intType=Integer.valueOf(type.toString());

                //for(int j=0;j<intArray.size();j++)
                //{System.out.println(intArray.get(j));}
                //System.out.println("text: "+text.substring(startPlace,startPlace+tokenLength)+" type:"+intType);
                //System.out.println(startPlace+" "+tokenLength+" "+intType);

                String styleClass;
                if(intType==-1||intType==261||(intType>=265&&intType<=275))
                {
                    //System.out.println(intType+" "+startPlace+" "+tokenLength);
                    switch (intType)
                    {
                        case -1:
                            styleClass="comment";
                            codeTextArea.setStyleClass(lastKwWord,startPlace,"other");
                            //System.out.println("设置从"+lastKwWord+"到"+startPlace+"为黑色");
                            codeTextArea.setStyleClass(startPlace,startPlace+intArray.get(intLine),styleClass);
                            //System.out.println("设置从"+startPlace+"长度"+intArray.get(intLine)+"为绿色");
                            lastKwWord=0;
                            for(int x=0;x<=intLine;x++)
                            {
                                lastKwWord+=intArray.get(x);
                            }
                            break;
                        case 261:
                            styleClass="number";
                            codeTextArea.setStyleClass(lastKwWord,startPlace,"other");
                            //System.out.println("设置从"+lastKwWord+"到"+startPlace+"为黑色");r
                            codeTextArea.setStyleClass(startPlace,startPlace+tokenLength,styleClass);
                            //System.out.println("设置从"+startPlace+"长度"+tokenLength+"为蓝色");
                            lastKwWord=startPlace+tokenLength;
                            break;
                        default:
                            styleClass="keyword";
                            codeTextArea.setStyleClass(lastKwWord,startPlace,"other");
                            //System.out.println("设置从"+lastKwWord+"到"+startPlace+"为黑色");
                            codeTextArea.setStyleClass(startPlace,startPlace+tokenLength,styleClass);
                            //System.out.println("设置从"+startPlace+"长度"+tokenLength+"为红色");
                            lastKwWord=startPlace+tokenLength;
                    }
                    //spansBuilder.add(Collections.emptyList(),startPlace-lastKwEnd );
                    //spansBuilder.add(Collections.singleton(styleClass), tokenLength);
                }
                i++;
            }
            codeTextArea.setStyleClass(lastKwWord,text.length(),"other");
            //System.out.println("设置从"+lastKwWord+"到"+text.length()+"为黑色");
        }
        if(parseFileType(ideFiles.filePathes.get(ideFiles.currentFile)).compareTo(".c")==0)
        {
            String result=dynamicAnalyseC(text);
            ArrayList<Integer> intArray=locateToken(text);
            int lastKwWord=0;
            StringBuffer type;
            StringBuffer firstc;
            StringBuffer lastc;
            StringBuffer firstl;
            StringBuffer lastl;
            int i=0;
            while(i<result.length())
            {
                type=new StringBuffer();
                firstc=new StringBuffer();
                lastc=new StringBuffer();
                firstl=new StringBuffer();
                lastl=new StringBuffer();

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    if(i==result.length()-1)
                        break;
                    i++;
                }
                if(i==result.length()-1)
                    break;
                while(result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    type.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    firstl.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    firstc.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    lastl.append(result.charAt(i));
                    i++;
                }

                while(result.charAt(i)<48||result.charAt(i)>57)
                {
                    i++;
                }
                while(i<result.length()&&result.charAt(i)>=48&&result.charAt(i)<=57)
                {
                    lastc.append(result.charAt(i));
                    i++;
                }

                //System.out.println(type+" "+firstc+" "+lastc+" "+firstl+" "+lastl);
                int intFirstLine=Integer.valueOf(firstl.toString())-1;
                int intLastLine=Integer.valueOf(lastl.toString())-1;
                int intFirstColomn=Integer.valueOf(firstc.toString())-1;
                int intLastColomn=Integer.valueOf(lastc.toString())-1;

                int startPlace=0;

                for(int x=0;x<intFirstLine;x++)
                {
                    startPlace+=intArray.get(x);
                }
                startPlace+=Integer.valueOf(firstc.toString());
                startPlace-=1;
                int tokenLength=0;
                int intType=Integer.valueOf(type.toString());
                if(intType==1&&intFirstLine!=intLastLine)
                {
                    tokenLength+=intArray.get(intFirstLine)-intFirstColomn;
                    tokenLength+=intLastColomn;
                    int z=intFirstLine+1;
                    while(z<intLastLine)
                    {
                        tokenLength+=intArray.get(z);
                        z++;
                    }
                }else{
                    tokenLength=intLastColomn-intFirstColomn;
                }

                //for(int j=0;j<intArray.size();j++)
                //{System.out.println(intArray.get(j));}
                //System.out.println("text: "+text.substring(startPlace,startPlace+tokenLength)+" type:"+intType);
                //System.out.println(startPlace+" "+tokenLength+" "+intType);

                String styleClass;
                if(intType==0)
                {
                    styleClass="number";
                    codeTextArea.setStyleClass(lastKwWord,startPlace,"other");
                    //System.out.println("设置从"+lastKwWord+"到"+startPlace+"为黑色");
                    codeTextArea.setStyleClass(startPlace,startPlace+tokenLength,styleClass);
                    //System.out.println("设置从"+startPlace+"长度"+tokenLength+"为红色");
                    lastKwWord=startPlace+tokenLength;
                }
                else if(intType==1)
                {
                    styleClass="comment";
                    codeTextArea.setStyleClass(lastKwWord,startPlace,"other");
                    //System.out.println("设置从"+lastKwWord+"到"+startPlace+"为黑色");
                    codeTextArea.setStyleClass(startPlace,startPlace+tokenLength,styleClass);
                    //System.out.println("设置从"+startPlace+"长度"+intArray.get(intLine)+"为绿色");
                    lastKwWord=startPlace+tokenLength;
                }
                i++;
            }
            if(lastKwWord!=text.length())
                codeTextArea.setStyleClass(lastKwWord,text.length(),"other");
            //System.out.println("设置从"+lastKwWord+"到"+text.length()+"为黑色");
        }
    }

    public ArrayList<Integer> locateToken(String code)
    {
        ArrayList<Integer> intArray=new ArrayList<Integer>();
        int colomn=0;
        for(int i=0;i<code.length();i++)
        {
            colomn++;
            if (code.charAt(i) == '\n')
            {
                intArray.add(colomn);
                colomn=0;
            }
        }
        intArray.add(colomn);

        return intArray;
    }

    public void typingCode(KeyEvent e)
    {
        //status.setText(e.getText());
        if(e.getCode().isArrowKey()!=true) {
            if(parseFileType(ideFiles.filePathes.get(ideFiles.currentFile))!=null)
            {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                computeHighlighting(codeTextArea.getText());

                            }
                        });
                    }
                }, 500);
            }
        }
        //System.out.println("代码区域type触发");
    }

    public ArrayList<String> getAllFileName(String path)
    {
        ArrayList<String> fileNameList=new ArrayList<String>();
        int j=path.length()-1;
        while(path.charAt(j)!='\\')
        {
            j--;
        }

        File file=new File(path.substring(0,j));
        File[] tempList = file.listFiles();

        for(int i=0;i<tempList.length;i++)
        {
            //if(tempList[i].isFile())
            //{
            fileNameList.add(tempList[i].getName());
            //}
        }
        return fileNameList;
    }

    public void constructTreeView()
    {
        int j=ideFiles.filePathes.get(ideFiles.currentFile).length()-1;
        while(ideFiles.filePathes.get(ideFiles.currentFile).charAt(j)!='\\')
        {
            j--;
        }
        //System.out.println(ideFiles.filePathes.get(ideFiles.currentFile).substring(j,formerJ));
        TreeItem<String> rootItem = new TreeItem<>(ideFiles.filePathes.get(ideFiles.currentFile).substring(0,j));
        ArrayList<String> files=getAllFileName(ideFiles.filePathes.get(ideFiles.currentFile));

        rootItem.setExpanded(true);
        for (int i=0;i<files.size();i++)
        {
            //treeView.appendText("   -"+files.get(i)+"\n");
            TreeItem<String> tempNode=new TreeItem<String>(files.get(i));
            rootItem.getChildren().add(tempNode);
        }
        //System.out.println(treeView.getRoot().);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                treeView.setRoot(rootItem);
            }
        });
    }

    public void aboutMiniC()
    {
        String message=new String("注意！！！请务必把minicIDE的文件夹放在D盘根目录，且不能修改文件夹名字\n本IDE可以同时，编辑打开多个文件，并进行保存。\n关闭文件时请注意保存。\n另存为新的文件后，再执行保存时，" +
                "就会保存到新的路径。\n最多同时打开15个文件。\n当打开的文件为.asm/.s汇编文件和.c（MiniC）文件时，会有语法高亮效果。\n对.asm/.s文件使用“运行”，会在调试信息拦输出相应的" +
                ",coe文件路径或者错误信息。\n对.c文件使用“运行”时，会在调试信息栏输出对应的.asm文件路径或者错误信息。\n    感谢Javafx开源库Richtextfx");
        //JLabel label=new JLabel(message);
        //label.setFont(new Font("宋体",Font.PLAIN,16));
        JOptionPane.showMessageDialog(null,message,"关于英英英IDE",JOptionPane.INFORMATION_MESSAGE);
    }

    public void aboutUs()
    {
        String message=new String("东南大学2020届SOC课程设计\n第五组：\n申池冉\n袁皓东\n谢荣昌\n宋天昊（作者）\n甘启昕");
        //JLabel label=new JLabel(message);
        //label.setFont(new Font("宋体",Font.PLAIN,16));
        String absolutePath="d:\\minicIDE\\mbg.jpg";
        ImageIcon ic=new ImageIcon(absolutePath);
        JOptionPane.showMessageDialog(null,message,"关于我们",JOptionPane.INFORMATION_MESSAGE,ic);
    }

    public void clearFindField()
    {
        word.clear();
        wordForReplace.clear();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                computeHighlighting(codeTextArea.getText());
            }
        });
        findingIndex=0;
        findingWordPairs.clear();
        hasFindOneWord=false;
        replaceWhat=0;
    }

    public void findWord()
    {
        if(findWordFunction(word.getText())==true&&word.getText().compareTo("")!=0);
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    computeHighlighting(codeTextArea.getText());

                    for(int i=0;i<findingWordPairs.size();i++)
                    {
                        codeTextArea.setStyleClass(findingWordPairs.get(i).getKey(),findingWordPairs.get(i).getValue(),"find");
                    }
                    replaceWhat=1;
                }
            });
        }
    }

    public void nextFind()
    {
        if(findWordFunction(word.getText())==true&&word.getText().compareTo("")!=0)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    computeHighlighting(codeTextArea.getText());
                    if(hasFindOneWord==true)
                        findingIndex = (findingIndex + 1) % findingWordPairs.size();
                    System.out.println(findingIndex);
                    codeTextArea.setStyleClass(findingWordPairs.get(findingIndex).getKey(),findingWordPairs.get(findingIndex).getValue(),"find");
                    //if(hasFindOneWord==true)
                    //{

                    //}
                    hasFindOneWord=true;
                    replaceWhat=2;
                    //System.out.println("下一个 :"+findingIndex);
                }
            });
        }
    }

    public void beforeFind()
    {
        if(findWordFunction(word.getText())==true&&word.getText().compareTo("")!=0)
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    computeHighlighting(codeTextArea.getText());
                    findingIndex=(findingIndex-1+findingWordPairs.size())%findingWordPairs.size();
                    System.out.println(findingIndex);
                    codeTextArea.setStyleClass(findingWordPairs.get(findingIndex).getKey(),findingWordPairs.get(findingIndex).getValue(),"find");
                    hasFindOneWord=true;
                    replaceWhat=2;
                    //System.out.println("上一个："+findingIndex);
                }
            });
        }
    }

    public void replaceWord()
    {
        if(replaceWhat==1)
        {
            for(int i=0;i<findingWordPairs.size();i++)
            {
                int temp = codeTextArea.getText().indexOf(word.getText());
                while (temp != -1)
                {
                    codeTextArea.replaceText(temp,temp+word.getText().length(),wordForReplace.getText());
                    //findingWordPairs.add(new Pair<Integer, Integer>(temp,temp+word.getText().length()));
                    //System.out.println(codeTextArea.getText().substring(temp,temp+word.getText().length()));
                    temp+=1;
                    temp=codeTextArea.getText().indexOf(word.getText(),temp);
                }
                //codeTextArea.replaceText(findingWordPairs.get(i).getKey()+i*(wordForReplace.getText().length()-word.getText().length()),findingWordPairs.get(i).getValue()+i*(wordForReplace.getText().length()-wordForReplace.getText().length()),wordForReplace.getText());
            }
        }else if(replaceWhat==2)
        {
            codeTextArea.replaceText(findingWordPairs.get(findingIndex).getKey(),findingWordPairs.get(findingIndex).getValue(),wordForReplace.getText());
        }
        status.setText("替换完成！");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                computeHighlighting(codeTextArea.getText());
            }
        });
    }

    public boolean findWordFunction(String findingKeyWords)
    {
        findingWordPairs.clear();
        int temp = codeTextArea.getText().indexOf(word.getText());
        if(temp==-1) {
            status.setText("未找到相关");
            return false;
        }
        else {
            while (temp != -1)
            {
                findingWordPairs.add(new Pair<Integer, Integer>(temp,temp+word.getText().length()));
                //System.out.println(codeTextArea.getText().substring(temp,temp+word.getText().length()));
                temp+=1;
                temp=codeTextArea.getText().indexOf(word.getText(),temp);
            }
        }
        return true;
    }
    public void changeFindingWord()
    {
        findingIndex=0;
        findingWordPairs.clear();
        hasFindOneWord=false;
        replaceWhat=0;
        //System.out.println("替换typing触发");
    }
}
