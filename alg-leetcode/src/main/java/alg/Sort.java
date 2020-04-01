package alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.kancloud.cn/ichenpeng/blog/1134195
 */
public class Sort {

    // 冒泡排序
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length -1; i++) {
            for (int j=0; j < arr.length -1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public void bubbleSort1(int[] arr){
        for (int i = 0; i< arr.length -1; i++) {
            boolean sorted = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //将最大数移动到最右侧
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    sorted = false;
                }
            }
            if(sorted){
                break;
            }
        }
    }

    // 选择排序
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //min记录最小值下标
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                //更新最小值位置
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //位置交换
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }

    //插入排序
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //记录当前内容
            int current = arr[i];
            //前置index
            int preIndex = i - 1;
            while (preIndex >= 0 && arr[preIndex] > current) {
                //空出位置存current
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            //存储current
            arr[preIndex + 1] = current;
        }
    }
    //希尔排序
    public void shellSort(int[] arr) {
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > tmp) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex+gap] = tmp;
            }
            gap /= 2;
        }
    }

    //归并排序
    public void mergeSort(int[] arr) {
        if (arr.length == 1) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 将arr数组,[low,high]区间的数据排好序
     */
    private void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        //计算中值
        int middle = low + (high - low) / 2;
        //将左侧排序
        mergeSort(arr, low, middle);
        //将右侧排序
        mergeSort(arr, middle + 1, high);
        //合并左右子数组
        merge(arr, low, middle, high);
    }

    //[low..m][m+1..high]
    private void merge(int[] arr, int low, int middle, int high) {
        //按照中值分隔数组
        int[] left = Arrays.copyOfRange(arr, low, middle + 1);
        int[] right = Arrays.copyOfRange(arr, middle + 1, high + 1);


        //左右索引
        int leftIndex = 0, rightIndex = 0;
        //记录已经合并的数据数
        int count = -1;
        while (++count < left.length + right.length) {

            //如果left数组已经取完，直接取right即可
            if (leftIndex >= left.length) {
                arr[low + count] = right[rightIndex];
                rightIndex++;
                continue;
            }

            //如果right数组已经取完，直接取left即可
            if (rightIndex >= right.length) {
                arr[low + count] = left[leftIndex];
                leftIndex++;
                continue;
            }

            //两边都没有取完，取最小的
            if (left[leftIndex] <= right[rightIndex]) {
                arr[low + count] = left[leftIndex++];
            } else {
                arr[low + count] = right[rightIndex++];
            }
        }
    }

    public void quickSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        //找到arr的基准，并根据基准对数组进行划分
        int pivotIndex = partition(arr, low, high);
        //对左侧进行快排
        quickSort(arr, low, pivotIndex - 1);
        //对右侧进行快排
        quickSort(arr, pivotIndex + 1, high);
    }
    //快速排序
    private int partition(int[] arr, int low, int high) {
        //index of smaller element
        int i = low - 1;
        //基准值
        int pivot = arr[high];
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                if (i != j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        int tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    //堆排序
    public void heapSort(int[] arr) {
        buildMaxHeap(arr);
        int count = 0;
        while (count < arr.length) {
            //将当前最大值放到数组末位
            swap(arr, 0, arr.length - count - 1);
            count++;
            //最后面的数据已经是有序了, 不需要再调整
            adjustMaxHeap(arr, 0, arr.length - count);
        }
    }

    /**
     * 调整最大堆
     *
     * @param arr  数组
     * @param i    这里将arr[i]定义为完全二叉树的根
     * @param size 需要调整的长度
     */
    private void adjustMaxHeap(int[] arr, int i, int size) {
        //左孩子节点
        int leftChildIndex = i * 2 + 1;
        //右孩子节点
        int rightChildIndex = i * 2 + 2;
        //临时变量
        int max = i;
        //二叉树的叶子节点总数 >= 非叶子节点,这里跳过叶子节点
        if (i >= size / 2) {
            return;
        }

        if (leftChildIndex < size && arr[max] < arr[leftChildIndex]) {
            max = leftChildIndex;
        }
        if (rightChildIndex < size && arr[max] < arr[rightChildIndex]) {
            max = rightChildIndex;
        }

        //根节点比叶子节点小,需要调整
        if (max != i) {
            swap(arr, max, i);
            adjustMaxHeap(arr, max, size);
        }
    }

    private void buildMaxHeap(int[] arr) {
        //从最后一个非叶子节点开始向上构造堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            adjustMaxHeap(arr, i, arr.length);
        }
    }

    public void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }



    // 计数排序
    public int[] countingSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        //计算最大最小值
        int min = arr[0], max = arr[0];
        for (int num : arr) {
            if (min > num) {
                min = num;
            }
            if (max < num) {
                max = num;
            }
        }
        //计算偏移量
        int bias = 0 - min;

        //计数
        int[] bucket = new int[max - min + 1];
        for (int num : arr) {
            bucket[num + bias]++;
        }

        //重新组装arr
        int index = 0;
        int bucketIndex = 0;
        while (index < arr.length) {
            if (bucket[bucketIndex] != 0) {
                arr[index++] = bucketIndex - bias;
                bucket[bucketIndex]--;
            } else {
                bucketIndex++;
            }
        }
        return arr;
    }

    //桶排序
    public void bucketSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        //计算最大最小值
        int min = arr[0], max = arr[0];
        for (int number : arr) {
            if (min > number) {
                min = number;
            }
            if (max < number) {
                max = number;
            }
        }

        //每个桶存储数据条数
        int sectionCount = ((max - min) / arr.length) + 1;

        //桶列表
        List<List<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            bucketList.add(new ArrayList<>());
        }

        //数据入桶
        for (Integer number : arr) {
            try {
                bucketList.get((number - min) / sectionCount).add(number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //桶内排序
        for (List<Integer> list : bucketList) {
            Collections.sort(list);
        }
        //写回原数组
        int index = 0;
        for (List<Integer> list : bucketList) {
            for (Integer number : list) {
                arr[index++] = number;
            }
        }
    }
}
