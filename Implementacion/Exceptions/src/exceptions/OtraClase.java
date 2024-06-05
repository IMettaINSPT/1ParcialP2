/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author IMetta
 */
public class OtraClase {
    
    public void HacerAlgo ()
    {
    
        try{
       java.io.FileReader f = new java.io.FileReader("llala.txt");
        }
        catch(java.io.FileNotFoundException ex)
        {
           System.out.print("Flasheaste amigo");
        }
    }
}
