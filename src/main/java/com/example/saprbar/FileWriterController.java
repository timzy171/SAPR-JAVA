package com.example.saprbar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterController extends ImageController{

    public static void writeToFile(File file) throws IOException {
        try(FileWriter writer = new FileWriter(file)){
            if(file.getName().endsWith(".cn")){
                for (List<Double> doubles : nodeInfo) {
                    for (Double aDouble : doubles) {
                        writer.write(aDouble + " ");
                    }
                    writer.write("\n");
                }
            }
            else if(file.getName().endsWith(".ld")){
                for (List<Double> doubles : forces) {
                    for (Double aDouble : doubles) {
                        writer.write(aDouble + " ");
                    }
                    writer.write("\n");
                }
            }
        }

    }
}
