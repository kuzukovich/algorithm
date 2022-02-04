package sky.pro;

import java.util.Arrays;

public class IntListImpl implements IntegerList {

    private Integer[] array;
    private int index = 0;
    int size = 1000000;

    public IntListImpl() {
        array = new Integer[size];
    }


    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public Integer add(Integer item) {
        checkNotNull(item);
        checkEnoughSize(index);
        item = array[index++];
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public Integer add(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        checkEnoughSize(index);
        System.arraycopy(array, index, array, index + 1, array.length - index);
        item = array[index++];
        return item;
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс меньше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public Integer set(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        item = array[index];
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(Integer item) {
        checkNotNull(item);
        checkElementExist(item);
        int index = indexOf(item);
        removeElement(index);
        checkEnoughSize(index);
        return item;
    }

    public void removeElement(int index) {
        if (size - 1 > index) {
            System.arraycopy(array, index + 1, array, index, array.length - index);
            array[--this.index] = null;
        }
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = get(index);
        removeElement(index);
        checkEnoughSize(index);
        return item;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(Integer item) {
        checkNotNull(item);
        boolean result = false;
        result = containsBinary(array, item);
        return result;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(Integer item) {
        int index = -1;
        checkNotNull(item);
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(Integer item) {
        int index = -1;
        checkNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public Integer get(int index) {
        checkIndex(index);
        return array[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            return false;
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        return index;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, index);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not valid");
        }
    }

    private void checkNotNull(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Value is not filled");
        }
    }

    private void checkElementExist(Integer item) {
        if (indexOf(item) == -1) {
            throw new IllegalArgumentException("The element does not exist in the list");
        }
    }

    private void checkEnoughSize(int index) {
        if (index == array.length - 1) {
            array = grow();
        } else if (index == array.length / 2) {
            array = resize();
        }
    }

    private Integer[] resize() {
        return Arrays.copyOf(array, (array.length * 2 / 3));
    }

    private Integer[] grow() {
        return Arrays.copyOf(array, (int) (array.length * 1.5));
    }

    //Метод самой быстрой сортировки
    private static void sortInsertion(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    private void sort() {
        quickSort(array, index);
    }

    private static void quickSort(Integer[] array, int index) {
        quickSort(array, 0, index);

    }

    private static void quickSort(Integer[] array) {
        quickSort(array, array.length - 1);

    }

    private static void quickSort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;

                swapElements(array, i, j);
            }
        }

        swapElements(array, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }


    private static boolean containsBinary(Integer[] array, Integer item) {
        sortInsertion(array);
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == array[mid]) {
                return true;
            }

            if (item < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
