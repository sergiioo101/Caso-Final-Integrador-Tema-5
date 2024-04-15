package optimizacion;

public class OptimizadorQuicksort {
    // Método público para iniciar el quicksort
    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    // Método recursivo para quicksort con optimizaciones
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Insertion sort para pequeños rangos
            if (high - low < 10) {
                insertionSort(array, low, high);
                return;
            }

            // Optimización: Mediano de tres para elegir el pivote
            int pivotIndex = medianOfThree(array, low, high);
            int pivotNewIndex = partition(array, low, high, pivotIndex);
            quickSort(array, low, pivotNewIndex - 1);
            quickSort(array, pivotNewIndex + 1, high);
        }
    }

    // Método para realizar la partición
    private int partition(int[] array, int low, int high, int pivotIndex) {
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, high);  // Mover el pivote al final
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (array[i] < pivotValue) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, storeIndex, high);  // Mover pivote a su posición final
        return storeIndex;
    }

    // Método para elegir el pivote como el mediano de tres
    private int medianOfThree(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        if (array[mid] < array[low]) {
            swap(array, mid, low);
        }
        if (array[high] < array[low]) {
            swap(array, high, low);
        }
        if (array[high] < array[mid]) {
            swap(array, high, mid);
        }
        return mid;
    }

    // Método de utilidad para intercambiar elementos
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Método de insertion sort para arreglos pequeños
    private void insertionSort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int value = array[i];
            int j = i - 1;
            while (j >= low && array[j] > value) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = value;
        }
    }
}

