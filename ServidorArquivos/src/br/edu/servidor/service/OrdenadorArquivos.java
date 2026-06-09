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
        mergesort(arr, 0, arr.length - 1, ascendente);
        return new ArrayList<>(Arrays.asList(arr));
    }

    private void mergesort(ArquivoInfo[] arr, int left, int right, boolean asc) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergesort(arr, left, mid, asc);
        mergesort(arr, mid + 1, right, asc);
        merge(arr, left, mid, right, asc);
    }

    private void merge(ArquivoInfo[] arr, int left, int mid, int right, boolean asc) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        ArquivoInfo[] L = new ArquivoInfo[n1];
        ArquivoInfo[] R = new ArquivoInfo[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            long a = L[i].getTamanho();
            long b = R[j].getTamanho();
            int cmp = Long.compare(a, b);
            if (cmp == 0) {
                // tie-breaker: use name to ensure deterministic order
                cmp = L[i].getNome().compareTo(R[j].getNome());
            }
            boolean takeLeft = asc ? (cmp <= 0) : (cmp >= 0);
            if (takeLeft) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
