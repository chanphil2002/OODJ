/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.List;
/**
 *
 * @author pc
 */
public interface FileReadable<T> {
     List<T> readFromFile(String filePath);
     T parseLine(String line);
}
