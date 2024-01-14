/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordenacaoexterna;

/**
 *
 * @author Levy
 */
public class HeapSort {
    
    public static void heapify(double[] array, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if(left < size && array[left] > array[largest]) {
            largest = left;
        }
        
        if(right < size && array[right] > array[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            double swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, size, largest);
        }
    }
    
    public static void heapSort(double[] array) {
        int size = array.length;
        
        for(int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
        }
        
        for(int i = size-1; i >= 0; i--) {
            double temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            
            heapify(array, i, 0);
        }
        
        
    }
}
