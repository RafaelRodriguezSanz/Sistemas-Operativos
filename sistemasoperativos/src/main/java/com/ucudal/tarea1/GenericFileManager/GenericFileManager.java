package com.ucudal.tarea1.GenericFileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GenericFileManager {

    /**
     * @param fileFullName
     * @param fileLineList
     * @throws IOException
     */
    public static void writeFile(String fileFullName,
            String[] fileLineList) {
        FileWriter fw;
        try {
            fw = new FileWriter(fileFullName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < fileLineList.length; i++) {
                String actualLine = fileLineList[i];
                bw.write(actualLine);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing the file"
                    + fileFullName);
            e.printStackTrace();
        }
    }

    public static String[] readRelativeFilePath(String fileFullName, boolean ignoreHeader) {
        String path = GenericFileManager.class
                .getResource(fileFullName).getPath();
        return readFile(path, ignoreHeader);
    }

    public static String[] readFile(String fileFullName, boolean ignoreHeader) {
        FileReader fr;
        ArrayList<String> fileLineList = new ArrayList<String>();
        try {
            fr = new FileReader(fileFullName);
            BufferedReader br = new BufferedReader(fr);
            String actualLine = br.readLine();
            if (ignoreHeader)
                actualLine = br.readLine();
            while (actualLine != null) {
                fileLineList.add(actualLine);
                actualLine = br.readLine();
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file "
                    + fileFullName);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading the file "
                    + fileFullName);
            e.printStackTrace();
        }
        System.out.println("File was read succesfully!");

        return fileLineList.toArray(new String[0]);
    }
}
