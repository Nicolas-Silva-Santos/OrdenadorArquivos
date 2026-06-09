package br.edu.servidor.service;

import br.edu.servidor.model.ArquivoInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdenadorArquivos {

    public List<ArquivoInfo> ordenarPorTamanho(List<ArquivoInfo> lista, boolean ascendente) {
        if (lista == null || lista.size() <= 1) {
            return lista == null ? new ArrayList<>() : new ArrayList<>(lista);
        }

        ArquivoInfo[] arr = lista.toArray(new ArquivoInfo[0]);

        quickSort(arr, 0, arr.length - 1, ascendente);

        return new ArrayList<>(Arrays.asList(arr));
    }

    private void quickSort(ArquivoInfo[] arr, int low, int high, boolean asc) {
        if (low < high) {

            int pivotIndex = partition(arr, low, high, asc);

            quickSort(arr, low, pivotIndex - 1, asc);
            quickSort(arr, pivotIndex + 1, high, asc);
        }
    }

    private int partition(ArquivoInfo[] arr, int low, int high, boolean asc) {

        ArquivoInfo pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            int cmp = comparar(arr[j], pivot);

            boolean deveTrocar = asc ? (cmp <= 0) : (cmp >= 0);

            if (deveTrocar) {
                i++;
                trocar(arr, i, j);
            }
        }

        trocar(arr, i + 1, high);

        return i + 1;
    }

    private int comparar(ArquivoInfo a, ArquivoInfo b) {

        int cmp = Long.compare(a.getTamanho(), b.getTamanho());

        if (cmp == 0) {
            cmp = a.getNome().compareTo(b.getNome());
        }

        return cmp;
    }

    private void trocar(ArquivoInfo[] arr, int i, int j) {
        ArquivoInfo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
