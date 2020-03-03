package sample;

import javafx.scene.Cursor;

import java.util.ArrayList;

public class IDEFiles {

    public int currentFile;
    public int currentAllFile;
    public ArrayList<String> filePathes = new ArrayList<String>();
    public ArrayList<String> fileCaches = new ArrayList<String>();
    public ArrayList<String> fileNames =new ArrayList<String>();

    public IDEFiles()
    {
        currentAllFile=-1;
        currentFile=-1;
    }


}
